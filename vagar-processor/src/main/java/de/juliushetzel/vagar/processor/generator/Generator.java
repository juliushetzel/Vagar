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

    Generator(Environment environment){
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
    public static Generator<TypeElement, MethodSpec.Builder> forActivityBindingMethod(Environment environment){
        return new BindMethodForActivityGenerator(environment);
    }

    /**
     * @param environment
     *
     * @return Generator for the binding method
     */
    public static Generator<TypeElement, MethodSpec.Builder> forFragmentBindingMethod(Environment environment){
        return new BindMethodForFragmentGenerator(environment);
    }

    /**
     * @param environment
     *
     * @return Generator for the entry point class
     */
    public static Generator<List<TypeElement>, TypeSpec.Builder> forEntryPointClass(Environment environment){
        return new InternalBinderClassGenerator(environment);
    }

    /**
     * @param environment
     *
     * @return Generator for the entry point class
     */
    public static Generator<TypeElement, String> forViewModelTags(Environment environment){
        return new ViewModelHolderTagGenerator(environment);
    }

    /**
     * @param environment
     *
     * @return Generator for the entry point class
     */
    public static Generator<List<TypeElement>, MethodSpec.Builder> forBindActivityInternalMethodImplementation(Environment environment){
        return new BindInternalMethodForActivityGenerator(environment);
    }

    /**
     * @param environment
     *
     * @return Generator for the entry point class
     */
    public static Generator<List<TypeElement>, MethodSpec.Builder> forBindFragmentInternalMethodImplementation(Environment environment){
        return new BindInternalMethodForFragmentGenerator(environment);
    }

    public static Generator<List<TypeElement>, List<MethodSpec.Builder>> forBindInternalMethodImplementations(Environment environment){
        return new BindInternalMethodImplementationsGenerator(environment);
    }
}
