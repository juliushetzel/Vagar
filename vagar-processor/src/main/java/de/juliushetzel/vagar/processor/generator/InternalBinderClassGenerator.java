package de.juliushetzel.vagar.processor.generator;


import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.util.List;
import java.util.stream.Collectors;

import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;

import de.juliushetzel.vagar.processor.environment.Environment;
import de.juliushetzel.vagar.processor.imitation.Imitations;

import static de.juliushetzel.vagar.processor.environment.Environment.FRAMEWORK_GENERATED_BINDER_CLASS_NAME;

final class InternalBinderClassGenerator extends Generator<List<TypeElement>, TypeSpec.Builder> {

    protected InternalBinderClassGenerator(Environment environment) {
        super(environment);
    }

    @Override
    public TypeSpec.Builder generate(List<TypeElement> annotatedElements) {

        getEnvironment().getLog().note("%s -> Start generating internal binder class '%s'",
                getClass().getSimpleName(),
                FRAMEWORK_GENERATED_BINDER_CLASS_NAME);

        TypeSpec.Builder builder = TypeSpec.classBuilder(FRAMEWORK_GENERATED_BINDER_CLASS_NAME)
                .addModifiers(Modifier.FINAL, Modifier.PUBLIC)
                .addSuperinterface(Imitations.Interfaces.INTERNAL_BINDER.getClassName())
                .addMethod(getConstructor())
                .addMethods(getBindInternalMethodImplementations(annotatedElements));

        annotatedElements.stream()
                .filter(typeElement -> getEnvironment().getInheritanceChecker().isInstanceOf(typeElement, Imitations.Classes.ACTIVITY))
                .map(Generator.forActivityBindingMethod(getEnvironment())::generate)
                .map(MethodSpec.Builder::build)
                .forEach(builder::addMethod);

        annotatedElements.stream()
                .filter(typeElement -> getEnvironment().getInheritanceChecker().isInstanceOf(typeElement, Imitations.Classes.FRAGMENT))
                .map(Generator.forFragmentBindingMethod(getEnvironment())::generate)
                .map(MethodSpec.Builder::build)
                .forEach(builder::addMethod);

        getEnvironment().getLog().note("%s -> Generating class '%s' finished",
                getClass().getSimpleName(),
                FRAMEWORK_GENERATED_BINDER_CLASS_NAME);

        return builder;
    }

    private MethodSpec getConstructor(){
        return MethodSpec.constructorBuilder()
                .build();
    }

    private List<MethodSpec> getBindInternalMethodImplementations(List<TypeElement> annotatedElements){
        return Generator
                .forBindInternalMethodImplementations(getEnvironment())
                .generate(annotatedElements)
                .stream()
                .map(MethodSpec.Builder::build)
                .collect(Collectors.toList());
    }
}
