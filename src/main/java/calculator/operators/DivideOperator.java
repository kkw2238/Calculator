package calculator.operators;

public class DivideOperator<T extends Number, K extends Number> extends Operator<T, K> {
    @Override
    public double operate(T a, K b) throws Exception {
        if(b.intValue() == 0) {
            throw new Exception("분모는 0이 될 수 없습니다.");
        }
        return a.doubleValue() / b.doubleValue();
    }
}
