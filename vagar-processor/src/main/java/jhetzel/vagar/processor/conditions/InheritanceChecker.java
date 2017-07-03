package jhetzel.vagar.processor.conditions;


import com.squareup.javapoet.ClassName;

import java.util.List;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.TypeElement;

public interface InheritanceChecker {
    List<TypeElement> checkInheritance(List<TypeElement> types, ClassName... possibleSuperTypes);

    TypeElement checkInheritance(TypeElement type, ClassName... possibleSuperTypes);

    boolean isInstanceOf(TypeElement type, ClassName superType);

    static InheritanceChecker newImplementation(ProcessingEnvironment processingEnvironment){
        return new InheritanceCheckerImpl(processingEnvironment);
    }
}
