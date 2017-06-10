package de.juliushetzel.vagar.processor.generator;


import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeVariableName;

import javax.lang.model.element.TypeElement;

import de.juliushetzel.vagar.processor.classes.ActivityAnnotation;
import de.juliushetzel.vagar.processor.classes.Bundle;
import de.juliushetzel.vagar.processor.classes.DataBindingUtil;
import de.juliushetzel.vagar.processor.classes.ViewDataBinding;
import de.juliushetzel.vagar.processor.classes.ViewModelBinder;
import de.juliushetzel.vagar.processor.classes.ViewModelFactory;
import de.juliushetzel.vagar.processor.environment.Environment;

import static javax.lang.model.element.Modifier.PUBLIC;
import static javax.lang.model.element.Modifier.STATIC;

final class BindMethodGenerator extends Generator<TypeElement, MethodSpec.Builder> {
    private static final String GENERIC_BINDING = "B";
    private static final String METHOD_NAME = "bind";

    protected BindMethodGenerator(Environment environment) {
        super(environment);
    }

    @Override
    public MethodSpec.Builder generate(TypeElement annotatedElement) {
        ActivityAnnotation.Values values = ActivityAnnotation.getAnnotationValues(annotatedElement);

        getEnvironment().getLog().note("%s -> Start generating %s method for annotated class %s",
                getClass().getSimpleName(),
                METHOD_NAME,
                annotatedElement.getSimpleName());

        TypeName viewModelType = values.getViewModelTypeName();

        MethodSpec.Builder builder = MethodSpec.methodBuilder(METHOD_NAME)
                .addModifiers(PUBLIC, STATIC)
                .addParameter(TypeName.get(annotatedElement.asType()), "activity")
                .addParameter(Bundle.getClassName(), "savedInstanceState")
                .addStatement("$L binding = $T.setContentView(activity, $L)", GENERIC_BINDING, DataBindingUtil.getClassName(), values.getLayoutResourceId())
                .addCode("$T<$T> factory = new $T<$T>(){\n", ViewModelFactory.getClassName(), viewModelType, ViewModelFactory.getClassName(), viewModelType)
                .addCode("\t@Override\n")
                .addCode("\tpublic $T createViewModel(){\n", viewModelType)
                .addStatement("\t\treturn new $T()", viewModelType)
                .addCode("\t}\n")
                .addStatement("}")
                .addStatement("$T viewModel = $T.bind(activity, factory, savedInstanceState)", viewModelType, ViewModelBinder.getClassName())
                .addStatement("binding.setVariable($T.$L, viewModel)", getBindingReferenceTypeName(), values.getViewModelTag())
                .addStatement("return binding")
                .addTypeVariable(getReturnTypeVariableName())
                .returns(getReturnTypeName());

        getEnvironment().getLog().note("%s -> Generating %s method finished",
                getClass().getSimpleName(),
                annotatedElement.getSimpleName());

        return builder;
    }

    private TypeName getBindingReferenceTypeName(){
        return ClassName.get(getEnvironment().getUserModulePackagePath(), "BR");
    }

    private TypeVariableName getReturnTypeVariableName(){
        return TypeVariableName.get(GENERIC_BINDING, ViewDataBinding.getClassName());
    }

    private TypeName getReturnTypeName(){
        return TypeVariableName.get(GENERIC_BINDING);
    }
}
