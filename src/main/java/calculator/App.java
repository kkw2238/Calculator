package calculator;

import calculator.utility.Comunication;
import calculator.utility.Utility;
import calculators.ArithmeticCalculator;
import calculators.Calculator;
import calculators.CircleCalculator;
import calculators.MathematicalCalculator;

import java.util.Scanner;

public class App {
    // 계산기 타입 0 일반 계산기, 1 원 계산기
    static final int CALCULATOR = 0, CIRCLE_CALCULATOR = 1, MATHMATICAL_CALCULATOR = 2;

    // 결과 출력해주는 함수
    public static void printResult(Number[] numbers, char inOperator, double calculationResult) {
        System.out.println(numbers[0] + " " + inOperator + " " + numbers[1] + " = " + calculationResult);
    }

    // 반지름 너비 출력해주는 함수
    public static void printCircleResult(Number r, double calculationResult) {
        System.out.println("반지름 " + r + "인 원의 넓이 = " + calculationResult);
    }

    public static void printMaticalResult(String matical, double maticalResult) {
        System.out.println(matical + " = " + maticalResult);
    }

    // 삭제를 물어보고 처리 하는 함수
    public static void removeProcess(Calculator calculator, Scanner sc) throws Exception {
        boolean isRemove = Comunication.askRemove(sc);

        if(isRemove) {
            calculator.removeResult();
        }
    }

    // 기준에 맞는 데이터를 출력하는 함수
    public static void inquiryProcess(Calculator calculator, Scanner sc) throws Exception {
        boolean isInquiry = Comunication.askInquiry(sc);

        if(isInquiry) {
            // 기준점은 -도 가능하다
            Number benchmark = Comunication.getInputNumber(sc, true);
            double[] inquiryResult;

            // 기준 점을 기준으로 데이터를 추출
            inquiryResult = calculator.inquiryUsedBenchmark(benchmark);

            Utility.showDoubleArrays(inquiryResult);
        }
    }

    // 사칙연산 계산기를 사용하는 함수
    public static double useCalculator(ArithmeticCalculator calculator, Scanner sc) throws Exception {
        Number[] numbers = Comunication.getInputTwoNumbers(sc, false);
        char inOperator = Comunication.getInputOperator(sc);
        double calculationResult = calculator.calculate(numbers[0], numbers[1], inOperator);

        printResult(numbers, inOperator, calculationResult);

        return calculationResult;
    }

    // 원의 넓이 계산기를 사용하는 함수
    public static double useCircleCalculator(CircleCalculator calculator, Scanner sc) throws Exception {
        Number r = Comunication.getInputNumber(sc, false);
        double calculationResult = calculator.calculateCircleArea(r);

        printCircleResult(r, calculationResult);

        return calculationResult;
    }

    public static double useMathematicalCalculator(MathematicalCalculator calculator, Scanner sc) throws Exception {
        String matical = Comunication.askAndGetAnswer("수식을 입력해주세요 : ", sc);

        double calculationResult = calculator.calculate(matical);
        printMaticalResult(matical, calculationResult);

        return calculationResult;
    }

    public static void run() throws Exception  {
        int calculatorType = 0;
        boolean isRun = true, isRemove = false, isInquiry = false;
        double calculationResult = 0;

        calculators.Calculator[] calculator = { new calculators.ArithmeticCalculator(), new calculators.CircleCalculator(), new calculators.MathematicalCalculator() };
        Scanner sc = new Scanner(System.in);

        /*
            while 탈출 조건
            1. 사용자가 no / exit를 입력할 경우
        */
        while(isRun) {
            calculatorType = Comunication.askCalculatorType(sc);

            switch (calculatorType) {
                case CALCULATOR:
                    calculationResult = useCalculator((ArithmeticCalculator)calculator[CALCULATOR], sc);
                    break;
                case CIRCLE_CALCULATOR:
                    calculationResult = useCircleCalculator((CircleCalculator)calculator[CIRCLE_CALCULATOR], sc);
                    break;
                case MATHMATICAL_CALCULATOR:
                    calculationResult = useMathematicalCalculator((MathematicalCalculator)calculator[MATHMATICAL_CALCULATOR], sc);
                    break;
                default:
                    throw new Exception("계산기 종류를 확인해 주세요.");
            }

            // 삭제 여부 진행
            removeProcess(calculator[calculatorType], sc);
            // 연산 결과를 저장
            calculator[calculatorType].addMemorize(calculationResult);
            // 조회 여부 진행
            inquiryProcess(calculator[calculatorType], sc);
            isRun = Comunication.askMoreCalculation(sc);
        }
    }

    public static void main(String[] args) throws Exception {
        try {
            run();
        } catch (NumberFormatException ex) {
            System.out.println("숫자를 입력해주세요.");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
