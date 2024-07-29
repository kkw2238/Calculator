package calculator;

import calculator.operators.*;

import java.util.Map;

public class ArithmeticCalculator extends Calculator {

    static Operator[] operators;
    static final Map<Character, OperatorType> OPERATOR_INDEX = Map.of(
            '+', OperatorType.PLUS,
            '-', OperatorType.MINUS,
            '*', OperatorType.MULTIPLY,
            '/', OperatorType.DIVIDE,
            '%', OperatorType.MOD
    );

    ArithmeticCalculator() {
        operators = new Operator[]{
                new AddOperator(),
                new SubtractOperator(),
                new MultiplyOperator(),
                new DivideOperator(),
                new ModOperator()
        };
    }

    @Override
    public double calculate(int[] numbers, char inOperator) throws Exception {
        double result = 0;

        int operatorIndex = OperatorType.valueOf(OPERATOR_INDEX.get(inOperator).toString()).getValue();
        result = operators[operatorIndex].operate(numbers[0], numbers[1]);

        return result;
    }

    @Override
    public void addMemorize(double value){
        memorizeCalculation.add(value);
    }

    @Override
    public void inquiry(){
        for(double d : memorizeCalculation) {
            System.out.print(d + " ");
        }
        System.out.println();
    }

    @Override
    public void removeResult() {
        if(!memorizeCalculation.isEmpty()) {
            memorizeCalculation.poll();
        } else {
            System.out.println("삭제할 값이 없습니다.");
        }
    }
}
