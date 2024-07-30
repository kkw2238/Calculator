package calculator;

public class CircleCalculator<T extends Number, K extends Number> extends Calculator<T, K> {

    // 원의 넓이를 계산하는 함수 r * r * PI
    @Override
    public double calculateCircleArea(T[] r)  {
        return r[0].doubleValue() * r[0].doubleValue() * Math.PI;
    }

    @Override
    public void addMemorize(double value) {
        memorizeCircleCalculation.add(value);
    }

    @Override
    public void inquiry(){
        for(double d : memorizeCircleCalculation) {
            System.out.print(d + " ");
        }
        System.out.println();
    }

    @Override
    public void removeResult() {
        if(!memorizeCircleCalculation.isEmpty()) {
            memorizeCircleCalculation.poll();
        } else {
            System.out.println("삭제할 값이 없습니다.");
        }
    }
}
