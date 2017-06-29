package jhetzel.vagar.annotation;

import android.support.annotation.LayoutRes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jhetzel.vagar.Navigator;
import jhetzel.vagar.ViewModel;
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
     * The type of the ViewModel
     */
    Class<? extends ViewModel> viewModel();

    /**
     * The id of the layout, which the ViewModel should be bound to.
     */
    @LayoutRes
    int layout();

    /**
     * The tag under which the ViewModel instance will be accessible
     * in the xml layout file.
     */
    String viewModelTag() default "viewModel";

    /**
     * The Navigator Type, that should be connected to the ViewModel
     */
    Class<? extends Navigator> navigator() default Unassigned.class;

    /**
     * An Empty implementation of the Navigator. This is only for to give
     * the Annotation a default Navigator, as its use is optional.
     */
    abstract class Unassigned extends Navigator{}
}
