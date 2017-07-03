package jhetzel.vagar.processor.conditions;


import com.squareup.javapoet.ClassName;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.type.TypeMirror;

class TypeCheckerImpl implements TypeChecker {

    private final ProcessingEnvironment mProcessingEnvironment;

    TypeCheckerImpl(ProcessingEnvironment environment) {
        mProcessingEnvironment = environment;
    }

    @Override
    public boolean isSameType(AnnotationMirror annotation, ClassName className) {
        TypeMirror classType = mProcessingEnvironment
                .getElementUtils()
                .getTypeElement(className.reflectionName())
                .asType();
        return classType.toString().equals(annotation.getAnnotationType().asElement().asType().toString());
    }
}
