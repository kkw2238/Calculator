package calculator.operators;

public enum OperatorType {
    PLUS(0), MINUS(1), MULTIPLY(2), DIVIDE(3), MOD(4);

    private final int value;
    OperatorType(int value) { this.value = value; };

    public int getValue() { return value; }
};
