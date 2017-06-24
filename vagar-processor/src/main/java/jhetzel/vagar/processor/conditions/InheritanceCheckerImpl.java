package jhetzel.vagar.processor.conditions;


import java.util.List;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;

import jhetzel.vagar.processor.exception.MissingClassInheritanceException;
import jhetzel.vagar.processor.imitation.ImitatedType;

public class InheritanceCheckerImpl implements InheritanceChecker {

    private final ProcessingEnvironment mProcessingEnvironment;

    InheritanceCheckerImpl(ProcessingEnvironment environment) {
        mProcessingEnvironment = environment;
    }

    @Override
    public List<TypeElement> checkInheritance(List<TypeElement> types, ImitatedType... possibleSuperTypes){
        types.forEach(type -> checkInheritance(type, possibleSuperTypes));
        return types;
    }

    @Override
    public TypeElement checkInheritance(TypeElement type, ImitatedType... possibleSuperTypes){
        for(ImitatedType superClass : possibleSuperTypes){
            if(isInstanceOf(type, superClass)){
                return type;
            }
        }
        throw new MissingClassInheritanceException(type, possibleSuperTypes);
    }

    @Override
    public boolean isInstanceOf(TypeElement type, ImitatedType superType){
        TypeMirror classType = mProcessingEnvironment
                .getElementUtils()
                .getTypeElement(superType.getClassPath())
                .asType();

        return mProcessingEnvironment
                .getTypeUtils()
                .isAssignable(type.asType(), classType);
    }
}
