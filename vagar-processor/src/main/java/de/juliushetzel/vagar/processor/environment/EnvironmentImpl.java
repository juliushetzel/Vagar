package de.juliushetzel.vagar.processor.environment;


import java.util.Map;

import javax.annotation.processing.ProcessingEnvironment;

import de.juliushetzel.vagar.processor.log.Log;

/**
 * Basic Implementation of the Environment Interface
 */
class EnvironmentImpl implements Environment{
    private static final String OPTIONS_KEY_MODULE_PACKAGE = "android.databinding.modulePackage";

    private Log mLog;

    private Map<String, String> mOptions;

    EnvironmentImpl(ProcessingEnvironment processingEnvironment){
        mLog = Log.newImplementation(processingEnvironment.getMessager());
        mOptions = processingEnvironment.getOptions();
    }

    @Override
    public String getUserModulePackagePath() {
        return mOptions.get(OPTIONS_KEY_MODULE_PACKAGE);
    }

    @Override
    public Log getLog() {
        return mLog;
    }
}
