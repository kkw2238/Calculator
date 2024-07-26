package calculator;

public class ArithmeticCalculator extends Calculator {

    @Override
    public double calculate(int[] numbers, char inOperator) throws Exception {
        double result = 0;
        switch (inOperator) {
            case '+' :
                result = numbers[0] + numbers[1];
                break;
            case '-' :
                result = numbers[0] - numbers[1];
                break;
            case '/' :
                if(numbers[1] == 0) {
                    throw new Exception("분모는 0이 될 수 없습니다.");
                }
                result = (double)numbers[0] / numbers[1];
                break;
            case '*' :
                result = numbers[0] * numbers[1];
                break;
            default :
                break;
        }

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
