package jhetzel.vagar.processor.imitation;


import jhetzel.vagar.processor.environment.Environment;

public final class Imitations {

    public static final class Classes{
        public static final ImitatedType ACTIVITY               = new ImitatedType("android.app", "Activity");
        public static final ImitatedType FRAGMENT               = new ImitatedType("android.app", "Fragment");
        public static final ImitatedType LAYOUT_INFLATER        = new ImitatedType("android.view", "LayoutInflater");
        public static final ImitatedType VIEW_GROUP             = new ImitatedType("android.view", "ViewGroup");
        public static final ImitatedType CONTEXT                = new ImitatedType("android.content", "Context");
        public static final ImitatedType DATA_BINDING_UTIL      = new ImitatedType("android.databinding", "DataBindingUtil");
        public static final ImitatedType VIEW_DATA_BINDING      = new ImitatedType("android.databinding", "ViewDataBinding");
        public static final ImitatedType VIEW_MODEL_PROVIDER    = new ImitatedType(Environment.FRAMEWORK_BASE_PACKAGE, "ViewModelProvider");
        public static final ImitatedType VIEW_MODEL             = new ImitatedType(Environment.FRAMEWORK_BASE_PACKAGE, "ViewModel");
        public static final ImitatedType NAVIGATOR              = new ImitatedType(Environment.FRAMEWORK_BASE_PACKAGE, "Navigator");
        public static final ImitatedType UNASSIGNED_NAVIGATOR   = new ImitatedType(Environment.FRAMEWORK_BASE_PACKAGE + ".annotation", "Assemble.Unassigned");
    }

    public static final class Interfaces{
        public static final ImitatedInterface INTERNAL_BINDER       = new ImitatedInterface(Environment.FRAMEWORK_BASE_PACKAGE, "Vagar.InternalBinder");
        public static final ImitatedInterface VIEW_MODEL_FACTORY    = new ImitatedInterface(Environment.FRAMEWORK_BASE_PACKAGE, "ViewModel.Factory");
        public static final ImitatedInterface NAVIGATOR_FACTORY     = new ImitatedInterface(Environment.FRAMEWORK_BASE_PACKAGE, "Navigator.Factory");
    }

    public static final class Annotations{
        public static final ImitatedAnnotation<AssembleAnnotationValues> ASSEMBLE = new AssembleAnnotation();
    }
}
