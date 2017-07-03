package jhetzel.vagar.exception;


/**
 * An Exception that will be thrown if the Vagar class is used
 * within a Class that is not annotated with {@link jhetzel.vagar.annotation.Assemble}
 */
public class AssembleAnnotationMissingException extends RuntimeException {
    private final String mMessage;

    public AssembleAnnotationMissingException(Object object){
        mMessage = generateMessage(object);
    }

    private String generateMessage(Object object){
        return new StringBuilder()
                .append("No generated Binder Method found for ")
                .append(object.getClass().getSimpleName())
                .append(". To use the Vagar class from activities or fragments,")
                .append(" they need to be annotated with @Assemble.")
                .toString();

    }

    @Override
    public String getMessage() {
        return mMessage;
    }
}
