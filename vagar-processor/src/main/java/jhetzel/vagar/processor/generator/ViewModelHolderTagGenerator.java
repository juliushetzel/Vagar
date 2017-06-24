package jhetzel.vagar.processor.generator;

import java.util.concurrent.atomic.AtomicLong;

import javax.lang.model.element.TypeElement;

import jhetzel.vagar.processor.environment.Environment;

final class ViewModelHolderTagGenerator extends Generator<TypeElement, String>{
    private static final String TAG_PREFIX = "vagar:internal:generated:view_model_holder:";
    private static final AtomicLong NEXT_TAG = new AtomicLong(0);

    ViewModelHolderTagGenerator(Environment environment) {
        super(environment);
    }

    @Override
    public String generate(TypeElement typeElement) {
        return new StringBuilder(TAG_PREFIX)
                .append(NEXT_TAG.getAndIncrement())
                .append(":")
                .append(typeElement.getSimpleName().toString())
                .toString();
    }
}

