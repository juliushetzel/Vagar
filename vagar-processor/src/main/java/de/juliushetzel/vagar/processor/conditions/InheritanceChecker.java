package de.juliushetzel.vagar.processor.conditions;


import java.util.List;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.TypeElement;

import de.juliushetzel.vagar.processor.imitation.ImitatedType;

public interface InheritanceChecker {
    List<TypeElement> checkInheritance(List<TypeElement> types, ImitatedType... possibleSuperTypes);

    TypeElement checkInheritance(TypeElement type, ImitatedType... possibleSuperTypes);

    boolean isInstanceOf(TypeElement type, ImitatedType superType);

    static InheritanceChecker newImplementation(ProcessingEnvironment processingEnvironment){
        return new InheritanceCheckerImpl(processingEnvironment);
    }
}
