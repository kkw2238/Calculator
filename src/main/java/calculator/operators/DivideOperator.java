package calculator.operators;

public class DivideOperator extends Operator {
    @Override
    public double operate(int a, int b) throws Exception {
        if(b == 0) {
            throw new Exception("분모는 0이 될 수 없습니다.");
        }
        return (double)a / b;
    }
}
