package com.github.kmizu.tsukuba_ac_calculator;
import java.util.*;

public class Splitter {
    private List<String> tank;
    public Splitter() {
        this.tank = new ArrayList<>();
    }
    public String[] split(String expression) {
        for(int i = 0; i < expression.length(); i++) {
            if(expression.charAt(i) == '+'
             || expression.charAt(i) == '-') {
              if(i == 0
               ||expression.charAt(i - 1) == '('
               || Judge.judgeKind(expression.substring(i - 1, i)) == Judge.OPERATOR) {
                expression = expression.substring(0, i) + "0" + expression.substring(i);
              }
            }
        }
        String[] splitExpression = null;
        int operandLeftIndex = 0;
        try {
            for(int i = 0; i < expression.length(); i++) {
                switch(Judge.judgeKind(expression.substring(i, i + 1))) {
                    case Judge.CLOSED_PARENTHESIS:
                    case Judge.OPEN_PARENTHESIS:
                    case Judge.OPERATOR:
                        if(i > 0 && operandLeftIndex != i) {
                            tank.add(expression.substring(operandLeftIndex, i));
                        }
                        tank.add(expression.substring(i, i + 1));
                        operandLeftIndex = i + 1;
                        break;
                    case Judge.OPERAND:
                        if(i + 1 >= expression.length()) {
                            tank.add(expression.substring(operandLeftIndex, i + 1));
                        }
                }
            }
            splitExpression = new String[tank.size()];
            for(int i = 0; i < tank.size(); i++) {
                splitExpression[i] = tank.get(i);
            }
        } finally {
            tank.clear();
        }
        return splitExpression;
    }
}