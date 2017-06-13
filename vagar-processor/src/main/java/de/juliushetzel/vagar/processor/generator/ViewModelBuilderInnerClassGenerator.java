package de.juliushetzel.vagar.processor.generator;


import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.util.Arrays;
import java.util.stream.Stream;

import javax.lang.model.element.Modifier;

import de.juliushetzel.vagar.processor.environment.Environment;

import static de.juliushetzel.vagar.processor.environment.Environment.FRAMEWORK_BASE_PACKAGE;

final class ViewModelBuilderInnerClassGenerator extends Generator<Class<?>, TypeSpec.Builder> {

    ViewModelBuilderInnerClassGenerator(Environment environment) {
        super(environment);
    }

    @Override
    public TypeSpec.Builder generate(Class<?> viewModelClass) {
        String builderClassName = viewModelClass.getSimpleName();
        TypeSpec.Builder builder = TypeSpec.classBuilder(builderClassName);
        try {
            Stream<Class<?>> parameters = Arrays.stream(viewModelClass.getConstructor().getParameterTypes());

            parameters.map(this::getParameterFieldSpec)
                    .forEach(builder::addField);

            parameters.map(parameterClass -> getParameterSetter(parameterClass, builderClassName))
                    .forEach(builder::addMethod);

        } catch (NoSuchMethodException e) {
            getEnvironment().getLog().note("%s -> Class %s has no constructor, builder will be empty.", this.getClass(), viewModelClass.getSimpleName());
        }
        return builder;
    }

    private FieldSpec getParameterFieldSpec(Class<?> parameterClass){
        return FieldSpec.builder(ClassName.get(parameterClass), parameterClass.getSimpleName().toLowerCase(), Modifier.PRIVATE)
                .build();
    }

    private MethodSpec getParameterSetter(Class<?> parameterClass, String builderClass){
        return MethodSpec.methodBuilder(String.format("inject%s", parameterClass.getSimpleName()))
                .addModifiers(Modifier.PUBLIC)
                .addParameter(ClassName.get(parameterClass), String.format("p%s", parameterClass.getSimpleName()))
                .addStatement("m$L = p$L", parameterClass.getSimpleName(), parameterClass.getSimpleName())
                .addStatement("return this")
                .returns(ClassName.get(FRAMEWORK_BASE_PACKAGE + "Builder", builderClass))
                .build();
    }
}
