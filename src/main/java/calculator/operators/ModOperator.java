package calculator.operators;

public class ModOperator extends Operator {
    @Override
    public double operate(int a, int b) throws Exception {
        return a % b;
    }
}
