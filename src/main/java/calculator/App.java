package calculator;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.regex.Pattern;

public class App {
    // 사칙연산에 사용될 정규식 + - / * 중 1개
    static final String REGEXP_ONLY_OPERATOR = "(.[+-/*])";

    // 숫자 입력받는 함수
    public static void inputNumbers(int[] container, Scanner sc) throws Exception
    {
        String input = "";

        for(int i = 0; i < container.length; ++i) {
            System.out.printf("%d번째 양의 정수를 입력해주세요.", i + 1);
            input = sc.nextLine();
            container[i] = Integer.parseInt(input);

            // 음의 정수 입력했을 경우 Throw
            if (container[i] < 0) {
                throw new Exception("음의 정수를 입력하셨습니다.");
            }
        }
    }

    // 연산자 입력받는 함수
    public static void inputOperator(String inOperator, Scanner sc) throws Exception
    {
        System.out.println("사칙연산 기호를 입력해주세요.");
        inOperator = sc.nextLine();

        // 사칙연산 기호 확인 ( 길이가 2이상 이거나, 정규식에 포함이 안된 경우 Throw )
        if(inOperator.length() > 1 || Pattern.matches(REGEXP_ONLY_OPERATOR, inOperator)) {
            throw new Exception("사칙연산 기호를 확인해주세요.");
        }
    }

    public static boolean run() {
        int[] numbers = { 0, 0 };
        String inOperator = "";
        Scanner sc = new Scanner(System.in);

        try {
            inputNumbers(numbers, sc);
            inputOperator(inOperator, sc);
        } catch (NumberFormatException ex) {
            System.out.println("숫자를 입력해주세요.");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return true;
    }

    public static void main(String[] args) {
        run();
    }
}
