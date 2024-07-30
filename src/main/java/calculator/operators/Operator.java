package calculator.operators;

public abstract class Operator<T extends Number, K extends Number> {
    Operator() {}
    abstract public double operate(T a, K b) throws Exception;
}
