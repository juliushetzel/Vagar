package jhetzel.vagar.processor.exception;


public class VagarAnnotationClassNotFound extends Exception{
    private ClassNotFoundException mBaseException;

    public VagarAnnotationClassNotFound(ClassNotFoundException exception){
        mBaseException = exception;
    }

    @Override
    public String getMessage() {
        return mBaseException.getMessage();
    }
}
