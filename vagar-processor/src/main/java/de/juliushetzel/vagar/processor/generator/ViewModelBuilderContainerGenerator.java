package de.juliushetzel.vagar.processor.generator;


import com.squareup.javapoet.TypeSpec;

import java.util.List;
import java.util.stream.Collectors;

import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;

import de.juliushetzel.vagar.processor.imitation.ActivityAnnotation;
import de.juliushetzel.vagar.processor.environment.Environment;

final class ViewModelBuilderContainerGenerator extends Generator<List<TypeElement>, TypeSpec.Builder> {

    ViewModelBuilderContainerGenerator(Environment environment) {
        super(environment);
    }

    @Override
    public TypeSpec.Builder generate(List<TypeElement> viewModelTypeElement){
        TypeSpec.Builder builder = TypeSpec.classBuilder("Builder")
                .addModifiers(Modifier.PUBLIC);

        List<ActivityAnnotation.Values> annotationValues = viewModelTypeElement.stream()
                .map(ActivityAnnotation::getAnnotationValues)
                .collect(Collectors.toList());

        /*for (ActivityAnnotation.Values values : annotationValues) {
            try {
                builder.addType(Generator
                                .forClassViewModelBuilder(getEnvironment())
                                .generate(values.getViewModelClass())
                        .build());
            } catch (ClassNotFoundException e) {
                getEnvironment()
                        .getLog()
                        .error("%s -> Class %s was not found.", getClass(), values.getViewModelTypeName().toString());
            }
        }*/
        return builder;
    }
}
