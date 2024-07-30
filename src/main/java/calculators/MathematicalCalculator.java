package calculators;

import calculator.utility.Utility;

import java.util.Stack;
import java.util.StringTokenizer;

// 수식을 입력받아서 계산해주는 Class
public class MathematicalCalculator<T extends Number, K extends Number> extends ArithmeticCalculator<T, K> {
    public double calculate(String mathmatical) throws Exception {
        // 공백 제거
        mathmatical = mathmatical.replaceAll(" ", "");

        int beginIndex = mathmatical.indexOf("(");
        int endIndex = mathmatical.lastIndexOf(")");

        if(beginIndex != -1 && endIndex != -1) {
            String backetLine = mathmatical.substring(beginIndex, endIndex + 1);
            double backetResult = calculate(backetLine.substring(1, backetLine.length() - 1));

            mathmatical = mathmatical.replace(backetLine, String.valueOf(backetResult));
        }

        StringTokenizer tokens = new StringTokenizer(mathmatical, "+-*/", true);
        // 사칙연산 기준으로 분할
        Stack<Double> numberStack = new Stack<>();
        Stack<Character> operatorStack = new Stack<>();

        convertMulAndDivToPlusMinus(numberStack, operatorStack, tokens);

        return calculateSequentially(numberStack, operatorStack);
    }

    public boolean checkMathmatical(String mathmatical) {
        int leftBracketCount = 0, rightBracketCount = 0;
        for(int i = 0; i < mathmatical.length(); i++) {
            if(mathmatical.charAt(i) == '(') {
                ++leftBracketCount;
            } else if(mathmatical.charAt(i) == ')') {
                ++rightBracketCount;
            }
            // right가 left보다 큰 경우는 순서가 반대로 된 경우다.
            if(rightBracketCount > leftBracketCount) {
                return false;
            }
        }

        // right와 left짝이 맞는지 확인
        return leftBracketCount == rightBracketCount;
    }

    // stack에서 하나씩 뽑아가며 순차적으로 계산해주는 함수
    private double calculateSequentially(Stack<Double> numberStack, Stack<Character> operatorStack) throws Exception {
        Double sumNum = numberStack.pop();
        while(!operatorStack.empty()) {
            sumNum = super.calculate((T)numberStack.pop(), (K)sumNum, operatorStack.pop());
        }
        return sumNum;
    }

    // 사칙연산 중 우선순위 계산
    private void convertMulAndDivToPlusMinus(Stack<Double> numberStack, Stack<Character> operatorStack, StringTokenizer tokens) throws Exception {
        int index = 0;
        // 이번에 숫자가 나올 차례인지에 대한 여부
        boolean isAbleNumber = true;

        while(tokens.hasMoreTokens()) {
            String token = tokens.nextToken();
            char c = token.charAt(0);
            if(Utility.isOperator(c)) {
                if(index == 0) {
                    if(c == '*' || c == '/') {
                        throw new Exception("수식에 문제가 발생 하였습니다. - ( 수식은 *, /로 시작할 수 없습니다.");
                    } else {
                        numberStack.add(0.0);
                    }
                }
                if(isAbleNumber) {
                    throw new Exception("수식에 문제가 발생 하였습니다. - ( 연산자가 연속으로 나올 수 없습니다.");
                }
                operatorStack.push(c);
                isAbleNumber = true;
            } else {
                Double nowNum = Double.parseDouble(token);
                if(!operatorStack.empty() && (operatorStack.peek() == '*' || operatorStack.peek() == '/')) {
                    Double beforeNum = numberStack.pop();

                    numberStack.add(super.calculate((T)beforeNum, (K)nowNum,  operatorStack.pop()));
                } else {
                    numberStack.push(nowNum);
                }

                isAbleNumber = false;
            }

            ++index;
        }
    }
}
