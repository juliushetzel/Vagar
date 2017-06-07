package de.juliushetzel.vagar.processor;


import java.util.List;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.SimpleAnnotationValueVisitor7;

public class TypeMirrorVisitor extends SimpleAnnotationValueVisitor7<Void, Void> {

    private TypeMirror mResult;

    public TypeMirror getResult(){
        return mResult;
    }

    @Override
    public Void visitInt(int i, Void p) {
        return p;
    }

    @Override
    public Void visitString(String s, Void p) {
        return p;
    }

    @Override
    public Void visitEnumConstant(VariableElement c, Void p) {
        return p;
    }

    @Override
    public Void visitAnnotation(AnnotationMirror a, Void p) {
        return p;
    }

    @Override
    public Void visitType(TypeMirror t, Void p) {
        mResult = t;
        return p;
    }

    @Override
    public Void visitArray(List<? extends AnnotationValue> vals, Void p) {
        return p;
    }
}
