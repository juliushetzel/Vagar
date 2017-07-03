package jhetzel.vagar.processor.conditions;


import com.squareup.javapoet.ClassName;

import java.util.List;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;

import jhetzel.vagar.processor.exception.MissingClassInheritanceException;

class InheritanceCheckerImpl implements InheritanceChecker {

    private final ProcessingEnvironment mProcessingEnvironment;

    InheritanceCheckerImpl(ProcessingEnvironment environment) {
        mProcessingEnvironment = environment;
    }

    @Override
    public List<TypeElement> checkInheritance(List<TypeElement> types, ClassName... possibleSuperTypes){
        types.forEach(type -> checkInheritance(type, possibleSuperTypes));
        return types;
    }

    @Override
    public TypeElement checkInheritance(TypeElement type, ClassName... possibleSuperTypes){
        for(ClassName superClass : possibleSuperTypes){
            if(isInstanceOf(type, superClass)){
                return type;
            }
        }
        throw new MissingClassInheritanceException(type, possibleSuperTypes);
    }

    @Override
    public boolean isInstanceOf(TypeElement type, ClassName superType){
        TypeMirror classType = mProcessingEnvironment
                .getElementUtils()
                .getTypeElement(superType.reflectionName())
                .asType();

        return mProcessingEnvironment
                .getTypeUtils()
                .isAssignable(type.asType(), classType);
    }
}
