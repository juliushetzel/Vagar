package jhetzel.vagar.processor.environment;


import jhetzel.vagar.processor.environment.EnvironmentImpl;

import javax.annotation.processing.ProcessingEnvironment;

import jhetzel.vagar.processor.conditions.InheritanceChecker;
import jhetzel.vagar.processor.log.Log;

/**
 * The Environment of the Processor
 */
public interface Environment {
    String FRAMEWORK_GENERATED_BINDER_CLASS_NAME = "GeneratedBinder";
    String FRAMEWORK_BASE_PACKAGE = "jhetzel.vagar";

    /**
     * @return The package path of the user using
     * the Vagar Framework. E.g. "com.user.myapp"
     */
    String getUserModulePackagePath();

    /**
     * @return The build Log
     */
    Log getLog();

    InheritanceChecker getInheritanceChecker();

    ProcessingEnvironment getProcessingEnvironment();

    /**
     * @param processingEnvironment
     * @return A new Implementation of the Processor Environment;
     */
    static Environment newImplementation(ProcessingEnvironment processingEnvironment){
        return new EnvironmentImpl(processingEnvironment);
    }
}
