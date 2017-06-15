package de.juliushetzel.vagar.processor.generator;


import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.util.List;

import javax.lang.model.element.TypeElement;

import de.juliushetzel.vagar.processor.environment.Environment;

/**
 * Internal Generator, supposed to generate code
 *
 * @param <I> the input -> usually some sort of
 *           javax.lang.model.element.*
 *
 * @param <O> the output -> usually the generated
 *           code as some sort of "JavaPoet-Spec",
 *           e.g. TypeSpec, MethodSpec...
 */
public abstract class Generator<I, O> {
    private final Environment mEnvironment;

    private Generator(Environment environment){
        mEnvironment = environment;
    }

    Environment getEnvironment(){
        return mEnvironment;
    }

    public abstract O generate(I input);

    /**
     * @param environment
     *
     * @return Generator for the binding method
     */
    public static Generator<TypeElement, MethodSpec.Builder> forBindingMethod(Environment environment){
        return new BindMethodGenerator(environment);
    }

    /**
     * @param environment
     *
     * @return Generator for the entry point class
     */
    public static Generator<List<TypeElement>, TypeSpec.Builder> forEntryPointClass(Environment environment){
        return new EntryPointClassGenerator(environment);
    }
}
