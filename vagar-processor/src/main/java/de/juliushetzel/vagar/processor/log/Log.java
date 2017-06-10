package de.juliushetzel.vagar.processor.log;


import javax.annotation.processing.Messager;

/**
 * A Log to keep track of errors.
 */
public interface Log {
    boolean NOTE_ENABLED = true;
    boolean ERROR_ENABLED = true;
    boolean WARNING_ENABLED = true;

    void note(String message, Object... arguments);

    void error(String message, Object... arguments);

    void warning(String message, Object... arguments);

    /**
     * @param messager
     * @return A new Implementation of the Log
     */
    static Log newImplementation(Messager messager){
        return new LogImpl(messager);
    }

}
