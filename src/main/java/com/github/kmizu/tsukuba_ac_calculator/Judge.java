package com.github.kmizu.tsukuba_ac_calculator;

public class Judge {
    private Judge() {}
    public static final int OPERATOR = 0;
    public static final int OPERAND = 1;
    public static final int OPEN_PARENTHESIS = 2;
    public static final int CLOSED_PARENTHESIS = 3;
    public static int getPriority(String operatorString) {
        char temporaryOperator = operatorString.charAt(0);
        switch (temporaryOperator) {
            case '^':
            case '~':
                return 2;
            case '*':
            case '/':
                return 1;
            case '+':
            case '-':
                return 0;
            default:
                throw new RuntimeException("Error!");
        }
    }
    public static int judgeKind(String token) {
        if(token.equals("(")) {
            return OPEN_PARENTHESIS;
        } else if(token.equals(")")) {
            return CLOSED_PARENTHESIS;
        } else if(token.equals("+")
                ||token.equals("-")
                ||token.equals("*")
                ||token.equals("/")
                ||token.equals("^")
                ||token.equals("~")) {
            return OPERATOR;
        } else {
            return OPERAND;
        }
    }
}
