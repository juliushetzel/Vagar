package de.juliushetzel.vagar.processor.generator;


import com.squareup.javapoet.MethodSpec;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.lang.model.element.TypeElement;

import de.juliushetzel.vagar.processor.environment.Environment;
import de.juliushetzel.vagar.processor.imitation.Imitations;

final class BindInternalMethodImplementationsGenerator extends Generator<List<TypeElement>, List<MethodSpec.Builder>> {

    BindInternalMethodImplementationsGenerator(Environment environment) {
        super(environment);
    }

    @Override
    public List<MethodSpec.Builder> generate(List<TypeElement> annotatedClasses) {
        List<TypeElement> annotatedFragments = annotatedClasses
                .stream()
                .filter(this::isFragment)
                .collect(Collectors.toList());

        List<TypeElement> annotatedActivities = annotatedClasses
                .stream()
                .filter(this::isActivity)
                .collect(Collectors.toList());

        getEnvironment().getLog().note("%s -> %s annotated Activities and %s Fragments found.",
                getClass().getSimpleName(),
                annotatedActivities.size(),
                annotatedFragments.size());

        List<MethodSpec.Builder> bindMethodImplementations = new ArrayList<>();
        bindMethodImplementations.add(
                Generator.forBindActivityInternalMethodImplementation(getEnvironment())
                        .generate(annotatedActivities));
        bindMethodImplementations.add(
                Generator.forBindFragmentInternalMethodImplementation(getEnvironment())
                        .generate(annotatedFragments));
        return bindMethodImplementations;
    }

    private boolean isFragment(TypeElement typeElement){
        return getEnvironment()
                .getInheritanceChecker()
                .isInstanceOf(typeElement, Imitations.Classes.FRAGMENT);
    }

    private boolean isActivity(TypeElement typeElement){
        return getEnvironment()
                .getInheritanceChecker()
                .isInstanceOf(typeElement, Imitations.Classes.ACTIVITY);
    }


}
