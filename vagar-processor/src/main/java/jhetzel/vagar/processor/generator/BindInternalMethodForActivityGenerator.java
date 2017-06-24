package jhetzel.vagar.processor.generator;


import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeVariableName;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;

import jhetzel.vagar.processor.environment.Environment;
import jhetzel.vagar.processor.imitation.Imitations;

final class BindInternalMethodForActivityGenerator extends Generator<List<TypeElement>, MethodSpec.Builder> {
    private static final String GENERIC_BINDING = "B";
    private static final String GENERIC_VIEW_MODEL = "V";
    private static final String METHOD_NAME = "bindInternal";

    BindInternalMethodForActivityGenerator(Environment environment) {
        super(environment);
    }

    @Override
    public MethodSpec.Builder generate(List<TypeElement> annotatedClasses) {
        getEnvironment().getLog().note("%s -> Start generating '%s' method implementation",
                getClass().getSimpleName(), METHOD_NAME);

        MethodSpec.Builder builder = MethodSpec.methodBuilder(METHOD_NAME)
                .addModifiers(Modifier.PUBLIC)
                .addParameter(Imitations.Classes.ACTIVITY.getClassName(), "activity")
                .addParameter(ParameterizedTypeName.get(Imitations.Interfaces.VIEW_MODEL_FACTORY.getClassName(), TypeVariableName.get(GENERIC_VIEW_MODEL)), "factory")
                .addTypeVariable(TypeVariableName.get(GENERIC_VIEW_MODEL, Imitations.Classes.VIEW_MODEL.getClassName()))
                .addTypeVariable(TypeVariableName.get(GENERIC_BINDING, Imitations.Classes.VIEW_DATA_BINDING.getClassName()))
                .addCode(generateInstanceChecks(annotatedClasses))
                .returns(TypeVariableName.get(GENERIC_BINDING));

        getEnvironment().getLog().note("%s -> Generating implementation of method '%s' finished",
                getClass().getSimpleName(), METHOD_NAME);

        return builder;
    }

    private CodeBlock generateInstanceChecks(List<TypeElement> annotatedClasses){
        List<TypeName> arguments = new ArrayList<>();
        StringBuilder builder = new StringBuilder();

        for(int index = 0 ; index < annotatedClasses.size() ; index++){
            TypeElement annotatedElement = annotatedClasses.get(index);
            Imitations.VagarAnnotationValues values = Imitations.Annotations.VAGAR.getValues(annotatedElement);

            if(index == 0){
                builder.append("if(activity instanceof $T){")
                        .append("\n\treturn bind(($T) activity, ($T<$T>) factory);")
                        .append("\n}");
            }else{
                builder.append("else if(activity instanceof $T){")
                        .append("\n\treturn bind(($T) activity, ($T<$T>) factory);")
                        .append("\n}");
            }
            arguments.add(TypeName.get(annotatedElement.asType()));
            arguments.add(TypeName.get(annotatedElement.asType()));

            arguments.add(Imitations.Interfaces.VIEW_MODEL_FACTORY.getClassName());
            arguments.add(values.getViewModelTypeName());
        }

        builder.append("\nreturn null;\n");
        return CodeBlock.of(builder.toString(), arguments.toArray());
    }
}
