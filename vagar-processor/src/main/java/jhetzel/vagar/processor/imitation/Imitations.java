package jhetzel.vagar.processor.imitation;


import com.squareup.javapoet.ClassName;

import jhetzel.vagar.processor.environment.Environment;

public final class Imitations {

    public static final class Classes{
        public static final ClassName ACTIVITY               = ClassName.get("android.app", "Activity");
        public static final ClassName FRAGMENT               = ClassName.get("android.app", "Fragment");
        public static final ClassName LAYOUT_INFLATER        = ClassName.get("android.view", "LayoutInflater");
        public static final ClassName VIEW_GROUP             = ClassName.get("android.view", "ViewGroup");
        public static final ClassName DATA_BINDING_UTIL      = ClassName.get("android.databinding", "DataBindingUtil");
        public static final ClassName VIEW_DATA_BINDING      = ClassName.get("android.databinding", "ViewDataBinding");
        public static final ClassName VIEW_MODEL_PROVIDER    = ClassName.get(Environment.FRAMEWORK_BASE_PACKAGE, "ViewModelProvider");
        public static final ClassName VIEW_MODEL             = ClassName.get(Environment.FRAMEWORK_BASE_PACKAGE, "ViewModel");
        public static final ClassName NAVIGATOR              = ClassName.get(Environment.FRAMEWORK_BASE_PACKAGE, "Navigator");
    }

    public static final class Interfaces{
        public static final ClassName INTERNAL_BINDER       = ClassName.get(Environment.FRAMEWORK_BASE_PACKAGE, "Vagar", "InternalBinder");
        public static final ClassName VIEW_MODEL_FACTORY    = ClassName.get(Environment.FRAMEWORK_BASE_PACKAGE, "ViewModel", "Factory");
        public static final ClassName NAVIGATOR_FACTORY     = ClassName.get(Environment.FRAMEWORK_BASE_PACKAGE, "Navigator", "Factory");
    }

    public static final class Annotations{
        public static final ClassName ASSEMBLE = ClassName.get(Environment.FRAMEWORK_BASE_PACKAGE + ".annotation", "Assemble");
    }
}
