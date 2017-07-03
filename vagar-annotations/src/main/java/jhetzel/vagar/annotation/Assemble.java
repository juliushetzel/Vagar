package jhetzel.vagar.annotation;

import android.support.annotation.LayoutRes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jhetzel.vagar.Navigator;
/**
 * The Annotation Processor for this Annotation will build a simple
 * Method with which all the provided components will be assembled.
 * Classes annotated with {@link Assemble} need to be of type
 * {@link android.app.Activity} or {@link android.app.Fragment}.
 *
 * Each annotated Class can simply call {@link jhetzel.vagar.Vagar#bind}
 * to get its proper generated Subclass of {@link android.databinding.ViewDataBinding}
 * with an attached instance of the given ViewModel type.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface Assemble {

    /**
     * The id of the layout, which the ViewModel should be bound to.
     */
    @LayoutRes
    int layout();

    /**
     * The tag under which the ViewModel instance will be accessible
     * in the xml layout file.
     */
    String bindingTag() default "viewModel";

    /**
     * The Navigator Type, that should be connected to the ViewModel
     */
    Class<? extends Navigator> navigator() default Navigator.class;
}
