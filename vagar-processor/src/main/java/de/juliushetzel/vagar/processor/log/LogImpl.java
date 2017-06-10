package de.juliushetzel.vagar.processor.log;


import javax.annotation.processing.Messager;

import static javax.tools.Diagnostic.Kind.ERROR;
import static javax.tools.Diagnostic.Kind.NOTE;
import static javax.tools.Diagnostic.Kind.WARNING;

class LogImpl implements Log{

    private final Messager mMessager;

    LogImpl(Messager messager) {
        mMessager = messager;
    }

    @Override
    public void note(String message, Object... arguments) {
        mMessager.printMessage(NOTE, String.format(message, arguments));
    }

    @Override
    public void error(String message, Object... arguments) {
        mMessager.printMessage(ERROR, String.format(message, arguments));
    }

    @Override
    public void warning(String message, Object... arguments) {
        mMessager.printMessage(WARNING, String.format(message, arguments));
    }
}
