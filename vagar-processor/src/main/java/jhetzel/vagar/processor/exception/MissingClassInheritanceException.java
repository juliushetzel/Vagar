package jhetzel.vagar.processor.exception;


import com.squareup.javapoet.ClassName;

import java.util.Arrays;

import javax.lang.model.element.TypeElement;

public class MissingClassInheritanceException extends RuntimeException{
    private final TypeElement mType;
    private final ClassName[] mSuperTypes;

    public MissingClassInheritanceException(TypeElement type, ClassName... superTypes){
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
                .map(ClassName::reflectionName)
                .forEach(stringBuilder::append);

        return stringBuilder.toString();
    }

    public TypeElement getType() {
        return mType;
    }

    public ClassName[] getSuperTypes() {
        return mSuperTypes;
    }
}
