package calculators;

import calculator.operators.*;

import java.util.Map;

public class ArithmeticCalculator<T extends Number, K extends Number> extends Calculator<T, K> {

    Operator<T, K>[] operators;
    static final Map<Character, OperatorType> OPERATOR_INDEX = Map.of(
            '+', OperatorType.PLUS,
            '-', OperatorType.MINUS,
            '*', OperatorType.MULTIPLY,
            '/', OperatorType.DIVIDE,
            '%', OperatorType.MOD
    );

    public ArithmeticCalculator() {
        operators = new Operator[] {
                new AddOperator<T, K>(),
                new SubtractOperator<T, K>(),
                new MultiplyOperator<T, K>(),
                new DivideOperator<T, K>(),
                new ModOperator<T, K>()
        };
    }

    public double calculate(T number1, K number2, char inOperator) throws Exception {
        double result = 0;

        int operatorIndex = OperatorType.valueOf(OPERATOR_INDEX.get(inOperator).toString()).getValue();
        result = operators[operatorIndex].operate(number1, number2);

        return result;
    }

    @Override
    public void addMemorize(double value){
        memorizeCalculation.add(value);
    }

    @Override
    public double[] inquiryAll(){
        return memorizeCalculation.stream()
                .mapToDouble(Double::doubleValue)
                .toArray();
    }

    @Override
    public double[] inquiryUsedBenchmark(T benchmark) {
        double[] result = memorizeCalculation.stream()
                .filter((d) -> d >= benchmark.doubleValue())
                .mapToDouble(Double::doubleValue)
                .toArray();

        return result;
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
