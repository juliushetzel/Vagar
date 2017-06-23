package de.juliushetzel.vagar.annotation;

import android.support.annotation.LayoutRes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import de.juliushetzel.vagar.ViewModel;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface Vagar {

    Class<? extends ViewModel> viewModel();

    @LayoutRes
    int layout();

    String viewModelTag() default "viewModel";

}
