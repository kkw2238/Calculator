package calculator.operators;

public class AddOperator<T extends Number, K extends Number> extends Operator<T, K> {
    @Override
    public double operate(T a, K b) throws Exception {
        return a.doubleValue() + b.doubleValue();
    }
}
