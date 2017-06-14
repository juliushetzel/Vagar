package de.juliushetzel.vagar.processor.environment;


import javax.annotation.processing.ProcessingEnvironment;

import de.juliushetzel.vagar.processor.log.Log;

/**
 * The Environment of the Processor
 */
public interface Environment {
    String FRAMEWORK_ENTRY_POINT_CLASS_NAME = "Vagar";
    String FRAMEWORK_BASE_PACKAGE = "de.juliushetzel.vagar";

    /**
     * @return The package path of the user using
     * the Vagar Framework. E.g. "com.user.myapp"
     */
    String getUserModulePackagePath();

    /**
     * @return The build Log
     */
    Log getLog();

    /**
     * @param processingEnvironment
     * @return A new Implementation of the Processor Environment;
     */
    static Environment newImplementation(ProcessingEnvironment processingEnvironment){
        return new EnvironmentImpl(processingEnvironment);
    }
}
