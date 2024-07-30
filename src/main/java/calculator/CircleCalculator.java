package calculator;

public class CircleCalculator<T extends Number, K extends Number> extends Calculator<T, K> {

    // 원의 넓이를 계산하는 함수 r * r * PI
    public double calculateCircleArea(T r)  {
        return r.doubleValue() * r.doubleValue() * Math.PI;
    }

    @Override
    public void addMemorize(double value) {
        memorizeCircleCalculation.add(value);
    }

    @Override
    public double[] inquiryAll(){
        return memorizeCircleCalculation.stream()
                .mapToDouble(Double::doubleValue)
                .toArray();
    }

    @Override
    public double[] inquiryUsedBenchmark(T benchmark) {
        double[] result = memorizeCircleCalculation.stream()
                .filter((d) -> d >= benchmark.doubleValue())
                .mapToDouble(Double::doubleValue)
                .toArray();

        return result;
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
