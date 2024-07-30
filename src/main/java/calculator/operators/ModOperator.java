package calculator.operators;

public class ModOperator<T extends Number, K extends Number> extends Operator<T, K> {
    @Override
    public double operate(T a, K b) throws Exception {
        return a.intValue() % b.intValue();
    }
}
