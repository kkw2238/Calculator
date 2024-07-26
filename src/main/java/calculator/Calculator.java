package calculator;

import java.util.LinkedList;
import java.util.Queue;

public class Calculator {
    private Queue<Double> memorize;

    Calculator() {
        memorize = new LinkedList<>();
    }

    // 입력 받은 데이터를 토대로 연산해주는 함수
    public double calculate(int[] numbers, char inOperator) throws Exception {
        double result = 0;
        switch (inOperator) {
            case '+' :
                result = numbers[0] + numbers[1];
                break;
            case '-' :
                result = numbers[0] - numbers[1];
                break;
            case '/' :
                if(numbers[1] == 0) {
                    throw new Exception("분모는 0이 될 수 없습니다.");
                }
                result = (double)numbers[0] / numbers[1];
                break;
            case '*' :
                result = numbers[0] * numbers[1];
                break;
            default :
                break;
        }

        return result;
    }

    public void addMemorize(double value) {
        memorize.add(value);
    }
}
