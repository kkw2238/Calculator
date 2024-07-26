package calculator;

public class CircleCalculator extends Calculator {

    // 원의 넓이를 계산하는 함수 r * r * PI
    @Override
    public double calculateCircleArea(int[] r)  {
        return (double)r[0] * r[0] * Math.PI;
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
