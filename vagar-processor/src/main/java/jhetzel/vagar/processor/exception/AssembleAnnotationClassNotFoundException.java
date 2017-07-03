package jhetzel.vagar.processor.exception;


public class AssembleAnnotationClassNotFoundException extends Exception{
    private ClassNotFoundException mBaseException;

    public AssembleAnnotationClassNotFoundException(ClassNotFoundException exception){
        mBaseException = exception;
    }

    @Override
    public String getMessage() {
        return mBaseException.getMessage();
    }
}
