package de.juliushetzel.vagar.processor.exception;


public class ActivityAnnotationClassNotFoundException extends Exception{
    private ClassNotFoundException mBaseException;

    public ActivityAnnotationClassNotFoundException(ClassNotFoundException exception){
        mBaseException = exception;
    }

    @Override
    public String getMessage() {
        return mBaseException.getMessage();
    }
}
