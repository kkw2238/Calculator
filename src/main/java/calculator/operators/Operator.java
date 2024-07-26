package calculator.operators;

public abstract class Operator {
    Operator() {}
    abstract public double operate(int a, int b) throws Exception;
}
