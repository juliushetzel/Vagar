package de.juliushetzel.vagar.processor.exception;


import java.util.Arrays;

import javax.lang.model.element.TypeElement;

import de.juliushetzel.vagar.processor.imitation.ImitatedType;

public class MissingClassInheritanceException extends RuntimeException{
    private final TypeElement mType;
    private final ImitatedType[] mSuperTypes;

    public MissingClassInheritanceException(TypeElement type, ImitatedType... superTypes){
        mType = type;
        mSuperTypes = superTypes;
    }

    @Override
    public String getMessage() {
        StringBuilder stringBuilder = new StringBuilder()
                .append("Type ")
                .append(mType.getSimpleName().toString())
                .append(" needs to inherit from one of these types: ");

        Arrays.stream(mSuperTypes)
                .map(ImitatedType::getClassPath)
                .forEach(stringBuilder::append);

        return stringBuilder.toString();
    }

    public TypeElement getType() {
        return mType;
    }

    public ImitatedType[] getSuperTypes() {
        return mSuperTypes;
    }
}
