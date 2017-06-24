package de.juliushetzel.vagar.processor.generator;


import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.TypeName;

import de.juliushetzel.vagar.processor.environment.Environment;
import de.juliushetzel.vagar.processor.imitation.Imitations;

final class NavigatorFactoryImplementationGenerator extends Generator<TypeName, CodeBlock> {

    NavigatorFactoryImplementationGenerator(Environment environment) {
        super(environment);
    }

    @Override
    public CodeBlock generate(TypeName navigatorType) {
        if(navigatorType.equals(Imitations.Classes.UNASSIGNED_NAVIGATOR.getClassName())){
            return CodeBlock.of("$T navigatorFactory = null;\n", Imitations.Interfaces.NAVIGATOR_FACTORY.getClassName());
        }

        return CodeBlock.of(
                "$T navigatorFactory = new $T(){\n" +
                "\tpublic $T createNavigator(){\n" +
                "\t\treturn new $T();\n" +
                "\t}\n" +
                "};\n",
                Imitations.Interfaces.NAVIGATOR_FACTORY.getClassName(),
                Imitations.Interfaces.NAVIGATOR_FACTORY.getClassName(),
                Imitations.Classes.NAVIGATOR.getClassName(),
                navigatorType
        );
    }
}
