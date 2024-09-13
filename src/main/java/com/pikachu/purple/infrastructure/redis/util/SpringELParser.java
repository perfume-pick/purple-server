package com.pikachu.purple.infrastructure.redis.util;

import lombok.experimental.UtilityClass;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * Spring Expression Language Parser
 */
@UtilityClass
public class SpringELParser {

    public static Object getDynamicValue(
        String[] parameterNames,
        Object[] args,
        String key
    ) {
        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();

        for (int i = 0; i < parameterNames.length; i++) {
            context.setVariable(
                parameterNames[i],
                args[i]
            );
        }

        return parser.parseExpression(key).getValue(
            context,
            Object.class
        );
    }
}
