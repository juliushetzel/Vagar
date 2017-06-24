package de.juliushetzel.vagar.processor.generator;


import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeVariableName;

import javax.lang.model.element.TypeElement;

import de.juliushetzel.vagar.processor.environment.Environment;
import de.juliushetzel.vagar.processor.imitation.Imitations;

final class BindMethodForFragmentGenerator extends Generator<TypeElement, MethodSpec.Builder> {
    protected static final String GENERIC_BINDING = "B";
    protected static final String BINDING_VARIABLE_NAME = "binding";
    private static final String METHOD_NAME = "bind";

    BindMethodForFragmentGenerator(Environment environment) {
        super(environment);
    }

    @Override
    public MethodSpec.Builder generate(TypeElement annotatedElement) {
        Imitations.VagarAnnotationValues values = Imitations.Annotations.VAGAR.getValues(annotatedElement);

        getEnvironment().getLog().note("%s -> Start generating '%s' method for annotated class '%s'",
                getClass().getSimpleName(),
                METHOD_NAME,
                annotatedElement.getSimpleName());

        TypeName viewModelType = values.getViewModelTypeName();

        MethodSpec.Builder builder = MethodSpec.methodBuilder(METHOD_NAME)
                .addParameter(TypeName.get(annotatedElement.asType()), "component")
                .addParameter(getViewModelFactoryParameter(viewModelType))
                .addParameter(Imitations.Classes.VIEW_GROUP.getClassName(), "container")
                .addCode(getLayoutBindingCodeBlock(values.getLayoutResourceId()))
                .addCode(Generator.forNavigatorFactoryImplementation(getEnvironment()).generate(values.getNavigatorTypeName()))
                .addStatement("$T tag = $S", String.class, Generator.forViewModelTags(getEnvironment()).generate(annotatedElement))
                .addStatement("$T viewModel = $T.get(component.getActivity().getFragmentManager(), factory, tag, navigatorFactory)", viewModelType, Imitations.Classes.VIEW_MODEL_PROVIDER.getClassName())
                .addStatement("$L.setVariable($T.$L, viewModel)", BINDING_VARIABLE_NAME, getBindingReferenceTypeName(), values.getViewModelTag())
                .addStatement("return $L", BINDING_VARIABLE_NAME)
                .addTypeVariable(getReturnTypeVariableName())
                .returns(getReturnTypeName());

        getEnvironment().getLog().note("%s -> Generating 'bind' method for '%s' finished",
                getClass().getSimpleName(),
                annotatedElement.getSimpleName());

        return builder;
    }

    private CodeBlock getLayoutBindingCodeBlock(int layoutResourceId) {
        return CodeBlock.of(
                "$T inflater = ($T) component.getActivity().getSystemService($T.LAYOUT_INFLATER_SERVICE);\n" +
                        "$L $L = $T.inflate(inflater, $L, container, false);\n",
                Imitations.Classes.LAYOUT_INFLATER.getClassName(),
                Imitations.Classes.LAYOUT_INFLATER.getClassName(),
                Imitations.Classes.CONTEXT.getClassName(),
                GENERIC_BINDING,
                BINDING_VARIABLE_NAME,
                Imitations.Classes.DATA_BINDING_UTIL.getClassName(),
                layoutResourceId
        );
    }

    private ParameterSpec getViewModelFactoryParameter(TypeName viewModelTypeName) {
        TypeName factoryTypeName = ParameterizedTypeName.get(Imitations.Interfaces.VIEW_MODEL_FACTORY.getClassName(), viewModelTypeName);
        return ParameterSpec.builder(factoryTypeName, "factory").build();
    }

    private TypeName getBindingReferenceTypeName() {
        return ClassName.get(getEnvironment().getUserModulePackagePath(), "BR");
    }

    private TypeVariableName getReturnTypeVariableName() {
        return TypeVariableName.get(GENERIC_BINDING, Imitations.Classes.VIEW_DATA_BINDING.getClassName());
    }

    private TypeName getReturnTypeName() {
        return TypeVariableName.get(GENERIC_BINDING);
    }
}