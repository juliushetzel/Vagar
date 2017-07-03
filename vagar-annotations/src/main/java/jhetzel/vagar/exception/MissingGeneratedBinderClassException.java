package jhetzel.vagar.exception;


public class MissingGeneratedBinderClassException extends RuntimeException {
    private final String mMessage;

    public MissingGeneratedBinderClassException(Exception e){
        super(e);
        mMessage = new StringBuilder()
                .append("Unable to Instantiate the generated Binding due to a ")
                .append(e.getClass().getSimpleName())
                .append(". Please check if you have Annotated your Classes from where you ")
                .append("call Vagar with @Assemble.")
                .toString();
    }

    @Override
    public String getMessage() {
        return mMessage;
    }
}
