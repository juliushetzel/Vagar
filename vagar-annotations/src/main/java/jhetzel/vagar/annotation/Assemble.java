package jhetzel.vagar.annotation;

import android.support.annotation.LayoutRes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jhetzel.vagar.Navigator;
import jhetzel.vagar.ViewModel;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface Assemble {

    Class<? extends ViewModel> viewModel();

    @LayoutRes
    int layout();

    String viewModelTag() default "viewModel";

    Class<? extends Navigator> navigator() default Navigator.Unassigned.class;
}
