package jhetzel.vagar.processor.generator;


import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.TypeName;

import jhetzel.vagar.processor.environment.Environment;
import jhetzel.vagar.processor.imitation.Imitations;

final class NavigatorFactoryImplementationGenerator extends Generator<TypeName, CodeBlock> {

    NavigatorFactoryImplementationGenerator(Environment environment) {
        super(environment);
    }

    @Override
    public CodeBlock generate(TypeName navigatorType) {
        if(navigatorType.equals(Imitations.Classes.NAVIGATOR)){
            return CodeBlock.of("$T navigatorFactory = null;\n", Imitations.Interfaces.NAVIGATOR_FACTORY);
        }

        return CodeBlock.of(
                "$T navigatorFactory = new $T(){\n" +
                "\tpublic $T createNavigator(){\n" +
                "\t\treturn new $T();\n" +
                "\t}\n" +
                "};\n",
                Imitations.Interfaces.NAVIGATOR_FACTORY,
                Imitations.Interfaces.NAVIGATOR_FACTORY,
                Imitations.Classes.NAVIGATOR,
                navigatorType
        );
    }
}
