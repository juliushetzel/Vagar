package de.juliushetzel.vagar.processor.generator;


import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.util.List;

import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;

import de.juliushetzel.vagar.processor.environment.Environment;

import static de.juliushetzel.vagar.processor.environment.Environment.FRAMEWORK_ENTRY_POINT_CLASS_NAME;

final class EntryPointClassGenerator extends Generator<List<TypeElement>, TypeSpec.Builder> {

    protected EntryPointClassGenerator(Environment environment) {
        super(environment);
    }

    @Override
    public TypeSpec.Builder generate(List<TypeElement> annotatedElements) {
        getEnvironment().getLog().note("%s -> Start generating entry point class %s",
                getClass().getSimpleName(),
                FRAMEWORK_ENTRY_POINT_CLASS_NAME);

        TypeSpec.Builder builder = TypeSpec.classBuilder(FRAMEWORK_ENTRY_POINT_CLASS_NAME)
                .addModifiers(Modifier.FINAL, Modifier.PUBLIC);

        annotatedElements.stream()
                .map(Generator.forMethodBind(getEnvironment())::generate)
                .map(MethodSpec.Builder::build)
                .forEach(builder::addMethod);

        getEnvironment().getLog().note("%s -> Generating class %s finished",
                getClass().getSimpleName(),
                FRAMEWORK_ENTRY_POINT_CLASS_NAME);

        return builder;
    }
}
