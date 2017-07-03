package jhetzel.vagar.processor.conditions;


import com.squareup.javapoet.ClassName;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.AnnotationMirror;

public interface TypeChecker {

    boolean isSameType(AnnotationMirror annotation, ClassName className);

    static TypeChecker newImplementation(ProcessingEnvironment processingEnvironment){
        return new TypeCheckerImpl(processingEnvironment);
    }
}
