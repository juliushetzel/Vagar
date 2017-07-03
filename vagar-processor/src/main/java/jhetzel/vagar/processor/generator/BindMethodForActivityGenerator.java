package jhetzel.vagar.processor.generator;


import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeVariableName;

import javax.lang.model.element.TypeElement;

import jhetzel.vagar.processor.environment.Environment;
import jhetzel.vagar.processor.imitation.AssembleAnnotationValues;
import jhetzel.vagar.processor.imitation.Imitations;

final class BindMethodForActivityGenerator extends Generator<TypeElement, MethodSpec.Builder> {
    protected static final String GENERIC_BINDING = "B";
    protected static final String GENERIC_VIEW_MODEL = "V";
    protected static final String BINDING_VARIABLE_NAME = "binding";
    private static final String METHOD_NAME = "bind";

    BindMethodForActivityGenerator(Environment environment) {
        super(environment);
    }

    @Override
    public MethodSpec.Builder generate(TypeElement annotatedElement) {
        AssembleAnnotationValues values = AssembleAnnotationValues.get(annotatedElement, getEnvironment());

        getEnvironment().getLog().note("%s -> Start generating '%s' method for annotated class '%s'",
                getClass().getSimpleName(),
                METHOD_NAME,
                annotatedElement.getSimpleName());

        MethodSpec.Builder builder = MethodSpec.methodBuilder(METHOD_NAME)
                .addParameter(TypeName.get(annotatedElement.asType()), "component")
                .addParameter(ParameterizedTypeName.get(Imitations.Interfaces.VIEW_MODEL_FACTORY, TypeVariableName.get(GENERIC_VIEW_MODEL)), "factory")
                .addCode(getLayoutBindingCodeBlock(values.getLayoutResourceId()))
                .addCode(Generator.forNavigatorFactoryImplementation(getEnvironment()).generate(values.getNavigatorTypeName()))
                .addStatement("$T tag = $S", String.class, Generator.forViewModelTags(getEnvironment()).generate(annotatedElement))
                .addStatement("$L viewModel = $T.get(component, factory, tag, navigatorFactory)", GENERIC_VIEW_MODEL, Imitations.Classes.VIEW_MODEL_PROVIDER)
                .addStatement("$L.setVariable($T.$L, viewModel)", BINDING_VARIABLE_NAME, getBindingReferenceTypeName(), values.getViewModelBindingId())
                .addStatement("return $L", BINDING_VARIABLE_NAME)
                .addTypeVariable(TypeVariableName.get(GENERIC_VIEW_MODEL, Imitations.Classes.VIEW_MODEL))
                .addTypeVariable(getReturnTypeVariableName())
                .returns(getReturnTypeName());

        getEnvironment().getLog().note("%s -> Generating 'bind' method for '%s' finished",
                getClass().getSimpleName(),
                annotatedElement.getSimpleName());

        return builder;
    }

    private CodeBlock getLayoutBindingCodeBlock(int layoutResourceId){
        return CodeBlock.of(
                "$L $L = $T.setContentView(component, $L);\n",
                GENERIC_BINDING,
                BINDING_VARIABLE_NAME,
                Imitations.Classes.DATA_BINDING_UTIL,
                layoutResourceId
        );
    }

    private TypeName getBindingReferenceTypeName(){
        return ClassName.get(getEnvironment().getUserModulePackagePath(), "BR");
    }

    private TypeVariableName getReturnTypeVariableName(){
        return TypeVariableName.get(GENERIC_BINDING, Imitations.Classes.VIEW_DATA_BINDING);
    }

    private TypeName getReturnTypeName(){
        return TypeVariableName.get(GENERIC_BINDING);
    }
}
