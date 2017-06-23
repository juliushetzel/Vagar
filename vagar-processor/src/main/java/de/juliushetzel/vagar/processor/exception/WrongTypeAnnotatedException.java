package de.juliushetzel.vagar.processor.exception;


import javax.lang.model.element.TypeElement;

import de.juliushetzel.vagar.processor.imitation.ImitatedAnnotation;
import de.juliushetzel.vagar.processor.imitation.ImitatedType;

public class WrongTypeAnnotatedException extends RuntimeException {
    private final ImitatedAnnotation mImitatedAnnotation;
    private final ImitatedType[] mSuperTypes;
    private final TypeElement mAnnotetedElement;

    public WrongTypeAnnotatedException(ImitatedAnnotation annotation, MissingClassInheritanceException exception){
        mImitatedAnnotation = annotation;
        mSuperTypes = exception.getSuperTypes();
        mAnnotetedElement = exception.getType();
    }

    @Override
    public String getMessage() {
        StringBuilder stringBuilder = new StringBuilder()
                .append("Annotation ")
                .append(mImitatedAnnotation.getClassPath())
                .append(" is not assignable to ")
                .append(mAnnotetedElement.getSimpleName().toString())
                .append(". Element ")
                .append(" needs to be of type ");

        for(int index = 0 ; index < mSuperTypes.length ; index++){
            if(index > 0 && index < mSuperTypes.length - 1){
                stringBuilder.append(", ");
            }else if(index == mSuperTypes.length -1){
                stringBuilder.append(" or ");
            }
            stringBuilder.append(mSuperTypes[index].getClassPath());
        }

        return stringBuilder.append("!").toString();
    }
}
