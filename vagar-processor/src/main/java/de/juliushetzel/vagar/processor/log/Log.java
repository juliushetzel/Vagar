package de.juliushetzel.vagar.processor.log;


import javax.annotation.processing.Messager;

/**
 * A Log to keep track of errors.
 */
public interface Log {

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
