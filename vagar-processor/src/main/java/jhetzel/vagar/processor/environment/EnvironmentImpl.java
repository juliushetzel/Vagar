package jhetzel.vagar.processor.environment;


import java.util.Map;

import javax.annotation.processing.ProcessingEnvironment;

import jhetzel.vagar.processor.conditions.InheritanceChecker;
import jhetzel.vagar.processor.conditions.TypeChecker;
import jhetzel.vagar.processor.imitation.AnnotationValueExtractor;
import jhetzel.vagar.processor.log.Log;

/**
 * Basic Implementation of the Environment Interface
 */
class EnvironmentImpl implements Environment{
    private static final String OPTIONS_KEY_MODULE_PACKAGE = "android.databinding.modulePackage";
    private final InheritanceChecker mInheritanceChecker;
    private final TypeChecker mTypeChecker;
    private final AnnotationValueExtractor mAnnotationValueExtractor;

    private Log mLog;

    private Map<String, String> mOptions;

    EnvironmentImpl(ProcessingEnvironment processingEnvironment){
        mLog = Log.newImplementation(processingEnvironment.getMessager());
        mOptions = processingEnvironment.getOptions();
        mInheritanceChecker = InheritanceChecker.newImplementation(processingEnvironment);
        mTypeChecker = TypeChecker.newImplementation(processingEnvironment);
        mAnnotationValueExtractor = AnnotationValueExtractor.newImplementation(mTypeChecker);
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
    public TypeChecker getTypeChecker() {
        return mTypeChecker;
    }

    @Override
    public AnnotationValueExtractor getAnnotationValueExtractor() {
        return mAnnotationValueExtractor;
    }
}
