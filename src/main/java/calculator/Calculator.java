package calculator;

import java.util.LinkedList;
import java.util.Queue;

public class Calculator {
    private Queue<Double> memorize;

    Calculator() {
        
        memorize = new LinkedList<>();
    }

    Queue<Double> getMemorize() {
        return memorize;
    }

    void setMemorize(Queue<Double> newMemorize) {
        this.memorize = newMemorize;
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

    // 과거 저장된 연산 결과 전부를 출력하는 함수
    public void inquiryResults() {
        for(double d : memorize) {
            System.out.print(d + " ");
        }
        System.out.println();
    }

    // 과거 데이터를 제거하는 함수
    public void removeResult() {
        if(!memorize.isEmpty()) {
            memorize.poll();
        } else {
            System.out.println("삭제할 값이 없습니다.");
        }
    }

    // 연산 결과를 저장하는 함수
    public void addMemorize(double value) {
        memorize.add(value);
    }
}
