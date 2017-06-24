package jhetzel.vagar.processor.environment;


import java.util.Map;

import javax.annotation.processing.ProcessingEnvironment;

import jhetzel.vagar.processor.conditions.InheritanceChecker;
import jhetzel.vagar.processor.log.Log;

/**
 * Basic Implementation of the Environment Interface
 */
class EnvironmentImpl implements Environment{
    private static final String OPTIONS_KEY_MODULE_PACKAGE = "android.databinding.modulePackage";
    private final ProcessingEnvironment mProcessingEnvironment;
    private final InheritanceChecker mInheritanceChecker;

    private Log mLog;

    private Map<String, String> mOptions;

    EnvironmentImpl(ProcessingEnvironment processingEnvironment){
        mProcessingEnvironment = processingEnvironment;
        mLog = Log.newImplementation(mProcessingEnvironment.getMessager());
        mOptions = mProcessingEnvironment.getOptions();
        mInheritanceChecker = InheritanceChecker.newImplementation(mProcessingEnvironment);
    }

    @Override
    public String getUserModulePackagePath() {
        return mOptions.get(OPTIONS_KEY_MODULE_PACKAGE);
    }

    @Override
    public Log getLog() {
        return mLog;
    }

    @Override
    public InheritanceChecker getInheritanceChecker() {
        return mInheritanceChecker;
    }

    @Override
    public ProcessingEnvironment getProcessingEnvironment() {
        return mProcessingEnvironment;
    }
}
