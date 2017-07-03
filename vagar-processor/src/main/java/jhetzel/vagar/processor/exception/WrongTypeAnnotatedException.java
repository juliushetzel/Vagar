package jhetzel.vagar.processor.exception;


import com.squareup.javapoet.ClassName;

import javax.lang.model.element.TypeElement;

public class WrongTypeAnnotatedException extends RuntimeException {
    private final ClassName mImitatedAnnotation;
    private final ClassName[] mSuperTypes;
    private final TypeElement mAnnotetedElement;

    public WrongTypeAnnotatedException(ClassName annotation, MissingClassInheritanceException exception){
        mImitatedAnnotation = annotation;
        mSuperTypes = exception.getSuperTypes();
        mAnnotetedElement = exception.getType();
    }

    @Override
    public String getMessage() {
        StringBuilder stringBuilder = new StringBuilder()
                .append("Annotation ")
                .append(mImitatedAnnotation.reflectionName())
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
            stringBuilder.append(mSuperTypes[index].reflectionName());
        }

        return stringBuilder.append("!").toString();
    }
}
