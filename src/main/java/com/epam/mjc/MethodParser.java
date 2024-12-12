package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     * 1. access modifier - optional, followed by space: ' '
     * 2. return type - followed by space: ' '
     * 3. method name
     * 4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     * accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     * private void log(String value)
     * Vector3 distort(int x, int y, int z, float magnitude)
     * public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        StringTokenizer tokenizer = new StringTokenizer(signatureString, " (),");
        List<String> tokens = new ArrayList<>();

        while (tokenizer.hasMoreTokens()) tokens.add(tokenizer.nextToken());

        List<String> argumentTokens;

        if (isAccessModifier(tokens.get(0))) argumentTokens = tokens.subList(3, tokens.size());
        else argumentTokens = tokens.subList(2, tokens.size());

        List<MethodSignature.Argument> argumentObjects = new ArrayList<>();
        String argumentType = "";

        for (int i = 0; i < argumentTokens.size(); i++) {
            if (i % 2 != 0) {
                argumentObjects.add(new MethodSignature.Argument(argumentType, argumentTokens.get(i)));
                argumentType = "";
                continue;
            }
            argumentType = argumentTokens.get(i);
        }

        MethodSignature methodSignature;

        if (isAccessModifier(tokens.get(0))) {
            methodSignature = new MethodSignature(tokens.get(2), argumentObjects);
            methodSignature.setReturnType(tokens.get(1));
            methodSignature.setAccessModifier(tokens.get(0));
        } else {
            methodSignature = new MethodSignature(tokens.get(1), argumentObjects);
            methodSignature.setReturnType(tokens.get(0));
        }

        return methodSignature;
    }

    private boolean isAccessModifier(String access) {
        return access.equals("private") || access.equals("protected") || access.equals("public");
    }

}
