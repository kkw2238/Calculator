package calculator;

import java.util.LinkedList;
import java.util.Queue;

public class Calculator {

    /*
    *  final 키워드를 사용할 경우, 수정이 불가능한 필드로 변경됩니다.
    *  현재 상황에서 final을 사용할 경우, 계산 결과를 추가 / 삭제할 수 없는 상황이 발생하기에
    *  final을 사용하긴 부적합 하다고 판단하였습니다.
    *
    *  static 키워드를 사용할 경우, Instance변수에서 Class변수로 변경됩니다.
    *  Class변수로 변경될 경우 모든 인스턴스가 공통으로 사용할 수 있습니다.
    *  계산기를 사용한 데이터가 계산기마다 별도로 갖고 있는 것이 아닌, 공통으로 갖고 있어야 하기 때문에
    *  static키워드를 사용하였습니다.
    */
    static private Queue<Double> memorizeCalculation;
    static private Queue<Double> memorizeCircleCalculation;

    Calculator() {
        memorizeCalculation = new LinkedList<>();
        memorizeCircleCalculation = new LinkedList<>();
    }

    Queue<Double> getMemorize() {
        return memorizeCalculation;
    }

    Queue<Double> getCircleMemorize() {
        return memorizeCircleCalculation;
    }

    void setMemorize(Queue<Double> newMemorize) {
        Calculator.memorizeCalculation = newMemorize;
    }

    void setCircleMemorize(Queue<Double> newMemorize) {
        Calculator.memorizeCircleCalculation = newMemorize;
    }

    // 원의 넓이를 계산하는 함수 r * r * PI
    public double circleCalculate(int[] r)  {
        return (double)r[0] * r[0] * Math.PI;
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
        for(double d : memorizeCalculation) {
            System.out.print(d + " ");
        }
        System.out.println();
    }

    // 과거 저장된 원의 넓이 연산 결과 전부를 출력하는 함수
    public void inquiryCircleResults() {
        for(double d : memorizeCircleCalculation) {
            System.out.print(d + " ");
        }
        System.out.println();
    }

    // 과거 데이터를 제거하는 함수
    public void removeResult() {
        if(!memorizeCalculation.isEmpty()) {
            memorizeCalculation.poll();
        } else {
            System.out.println("삭제할 값이 없습니다.");
        }
    }

    // 원의 넓이 과거 데이터를 제거하는 함수
    public void removeCircleResult() {
        if(!memorizeCircleCalculation.isEmpty()) {
            memorizeCircleCalculation.poll();
        } else {
            System.out.println("삭제할 값이 없습니다.");
        }
    }

    // 연산 결과를 저장하는 함수
    public void addMemorize(double value) {
        memorizeCalculation.add(value);
    }

    public void addCircleMemorize(double value) {
        memorizeCircleCalculation.add(value);
    }
}
