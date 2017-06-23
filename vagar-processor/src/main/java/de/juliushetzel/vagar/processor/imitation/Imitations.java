package de.juliushetzel.vagar.processor.imitation;


import com.squareup.javapoet.TypeName;

import javax.lang.model.element.TypeElement;

public interface Imitations {

    interface Classes{
        ImitatedType BUNDLE             = new BundleClass();
        ImitatedType DATA_BINDING_UTIL  = new DataBindingUtilClass();
        ImitatedType VIEW_DATA_BINDING  = new ViewDataBindingClass();
        ImitatedType VIEW_MODEL_BINDER  = new ViewModelBinderClass();
        ImitatedType VIEW_MODEL         = new ViewModelInterface();
        ImitatedType ACTIVITY           = new ActivityClass();
        ImitatedType ENTRY_POINT        = new EntryPointClass();
        ImitatedType FRAGMENT           = new FragmentClass();
        ImitatedType LAYOUT_INFLATER    = new LayoutInflaterClass();
        ImitatedType CONTEXT            = new ContextClass();
        ImitatedType VIEW_GROUP         = new ViewGroupClass();
    }

    interface Interfaces{
        ImitatedInterface INTERNAL_BINDER = new InternalBinderInterface();
        ImitatedInterface VIEW_MODEL_FACTORY    = new ViewModelFactoryInterface();
    }

    interface Annotations{
        ImitatedAnnotation<VagarAnnotationValues> VAGAR = new VagarAnnotation();

    }

    abstract class VagarAnnotationValues extends ImitatedAnnotation.Values{
        VagarAnnotationValues(TypeElement annotatedClass) {
            super(annotatedClass);
        }

        public abstract TypeName getViewModelTypeName();

        public abstract int getLayoutResourceId();

        public abstract String getViewModelTag();
    }
}
