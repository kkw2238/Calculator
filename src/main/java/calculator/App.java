package calculator;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.regex.Pattern;

public class App {
    // 사칙연산에 사용될 정규식 + - / * 중 1개
    static final String REGEXP_ONLY_OPERATOR = "(.[+-/*])";

    // 숫자 입력받는 함수
    public static void inputNumbers(int[] numbers, Scanner sc) throws Exception {
        String input = "";

        for(int i = 0; i < numbers.length; ++i) {
            System.out.printf("%d번째 양의 정수를 입력해주세요 : ", i + 1);
            input = sc.nextLine();
            numbers[i] = Integer.parseInt(input);

            // 음의 정수 입력했을 경우 Throw
            if (numbers[i] < 0) {
                throw new Exception("음의 정수를 입력하셨습니다." );
            }
        }
    }

    // 연산자 입력받는 함수
    public static char inputOperator(Scanner sc) throws Exception {
        System.out.print("사칙연산 기호를 입력해주세요 : " );
        String inOperator = sc.nextLine();

        // 사칙연산 기호 확인 ( 길이가 2이상 이거나, 정규식에 포함이 안된 경우 Throw )
        if(inOperator.length() > 1 || Pattern.matches(REGEXP_ONLY_OPERATOR, inOperator)) {
            throw new Exception("사칙연산 기호를 확인해주세요.");
        }

        return inOperator.charAt(0);
    }

    // 입력 받은 데이터를 토대로 연산해주는 함수
    public static double calculation(int[] numbers, char inOperator) throws Exception {
        double result = 0;
        switch (inOperator) {
            case '+' :
                result = numbers[0] + numbers[1];
                break;
            case '-' :
                result = numbers[0] - numbers[1];
                break;
            case '/' :
                if(numbers[1] == 0)
                {
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

    // 결과 출력해주는 함수
    public static void printResult(int[] numbers, char inOperator, double calculationResult) {
        System.out.printf("%d %c %d = %f\n", numbers[0], inOperator, numbers[1], calculationResult);
    }

    public static boolean run() throws Exception  {
        int[] numbers = { 0, 0 };
        double calculationResult = 0;
        char inOperator = ' ';
        Scanner sc = new Scanner(System.in);

        inputNumbers(numbers, sc);
        inOperator = inputOperator(sc);

        calculationResult = calculation(numbers, inOperator);
        printResult(numbers, inOperator, calculationResult);

        return true;
    }

    public static void main(String[] args) {
        try {
            run();
        } catch (NumberFormatException ex) {
            System.out.println("숫자를 입력해주세요.");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
