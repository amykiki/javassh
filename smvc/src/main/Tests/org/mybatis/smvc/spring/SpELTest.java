package org.mybatis.smvc.spring;

import org.junit.Test;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * Created by Amysue on 2016/6/16.
 */
public class SpELTest {
    private ExpressionParser  parser     = new SpelExpressionParser();
    private EvaluationContext evaContext = new StandardEvaluationContext();

    @Test
    public void testParse1() {
        Expression expression = parser.parseExpression("('page').concat(#pageNum)");
        evaContext.setVariable("pageNum", "3");

        System.out.println(expression.getValue(evaContext, String.class));
    }

    @Test
    public void testParse2() {
        Expression expression = parser.parseExpression("T(org.mybatis.smvc.service.CacheConstants).depsKey");
        System.out.println(expression.getValue(String.class));
    }

    @Test
    public void testParse3() {
        Expression expression = parser.parseExpression("'deps'");
        System.out.println(expression.getValue(String.class));
    }
}
