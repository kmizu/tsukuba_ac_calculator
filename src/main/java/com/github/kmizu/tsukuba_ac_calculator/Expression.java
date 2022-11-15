package com.github.kmizu.tsukuba_ac_calculator;

import java.util.Stack;

public class Expression {
    private String element;
    private Expression leftChild, rightChild;

    public Expression(String element) {
        this.element = element;
    }
    public boolean isLeaf() {
        return leftChild == null && rightChild == null;
    }

    public int evaluate() {
        if(isLeaf()) {
            return Integer.parseInt(element);
        } else {
            if(element.equals("+")) {
                return leftChild.evaluate() + rightChild.evaluate();
            } else if(element.equals("-")) {
                return leftChild.evaluate() - rightChild.evaluate();
            } else if(element.equals("*")) {
                return leftChild.evaluate() * rightChild.evaluate();
            } else if(element.equals("/")) {
                return leftChild.evaluate() / rightChild.evaluate();
            } else if(element.equals("^")) {
                int left = leftChild.evaluate();
                int leftTmp = left;
                int right = rightChild.evaluate();
                if(right == 0) return 1;
                for(int i = 1; i < right; i++) {
                    left *= leftTmp;
                }
                return left;
            } else if(element.equals("~")) {
                int left = leftChild.evaluate();
                int leftTmp = left;
                int right = rightChild.evaluate();
                int n = 0;
                if(right == 1) return 0;
                while(left <= right) {
                    n++;
                    left *= leftTmp;
                }
                return n;
            } else {
                throw new RuntimeException("Illegal Expression");
            }
        }
    }

    public void setElement(String element) {
        this.element = element;
    }

    public void setLeftChild(Expression leftChild) {
        this.leftChild = leftChild;
    }

    public void setRightChild(Expression rightChild) {
        this.rightChild = rightChild;
    }

    public String getElement() {
        return element;
    }

    public Expression getLeftChild() {
        return leftChild;
    }

    public Expression getRightChild() {
        return rightChild;
    }

    public static Expression makeNode(String[] source, int startIndex, int lastIndex) {
        Stack<Expression> stack = new Stack<>();
        Expression out = null;
        for(int i = startIndex; i <= lastIndex; i++) {
            if(Judge.judgeKind(source[i]) == Judge.OPEN_PARENTHESIS) {
                int indexClosed = findIndex(source, i);
                out = makeNode(source, i + 1, indexClosed - 1);
                i = indexClosed;
            } else if(Judge.judgeKind(source[i]) == Judge.OPERAND) {
                out = new Expression(source[i]);
            } else if(Judge.judgeKind(source[i]) == Judge.OPERATOR) {
                Expression now = new Expression(source[i]);
                if(stack.empty()) {
                    now.setLeftChild(out);
                    stack.push(now);
                } else {
                    Expression previous;
                    while(!stack.empty()) {
                        previous = stack.pop();
                        if(Judge.getPriority(previous.getElement()) >= Judge.getPriority(now.getElement())) {
                            previous.setRightChild(out);
                            out = previous;
                        } else {
                            stack.push(previous);
                            break;
                        }
                    }
                    now.setLeftChild(out);
                    stack.push(now);
                }
            }
        }
        while(!stack.empty()) {
            Expression tmp = stack.pop();
            tmp.setRightChild(out);
            out = tmp;
        }
        return out;
    }
    public static int findIndex(String[] source, int startIndex) {
        int countOpen = 0;
        int countClosed = 0;
        for(int i = startIndex; i < source.length; i++) {
            if(Judge.judgeKind(source[i]) == Judge.CLOSED_PARENTHESIS) {
                countClosed++;
            } else if(Judge.judgeKind(source[i]) == Judge.OPEN_PARENTHESIS) {
                countOpen++;
            }

            if(countOpen == countClosed) return i;
        }
        throw new RuntimeException("not found");
    }
}
