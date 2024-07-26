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

    // 결과 출력해주는 함수
    public static void printResult(int[] numbers, char inOperator, double calculationResult) {
        System.out.printf("%d %c %d = %f\n", numbers[0], inOperator, numbers[1], calculationResult);
    }

    // 사용자에게 계산기를 더 사용할 것인지 물어보는 함수
    public static boolean askMoreCalculation(Scanner sc) throws Exception {
        System.out.print("더 계산 하시겠습니까? yes/no/exit ( exit 입력시 종료 ) : ");
        String answer = sc.nextLine();
        answer = answer.toLowerCase();

        if(answer.equals("exit") || answer.equals("no")) {
            return false;
        }
        else if(answer.equals("yes")) {
            return true;
        }
        else {
            throw new Exception("yes/no/exit 중 하나만 입력해주십시오");
        }
    }

    // 가장 오래된 메모리를 삭제할 것인지 확인하는 함수
    public static boolean askRemove(Scanner sc) {
        System.out.print("가장 먼저 저장된 연산 결과를 삭제하시겠습니까? ( remove 입력시 삭제 ) : ");
        String answer = sc.nextLine();
        answer = answer.toLowerCase();

        return answer.equals("remove");
    }

    // Queue를 앞으로 당기는 함수
    public static void removeMemorize(Queue<Double> memorize) {
        if(!memorize.isEmpty()) {
            memorize.poll();
        } else {
            System.out.println("삭제할 값이 없습니다.");
        }
    }


    public static void printMemorize(Queue<Double> memorize) {
        for(double d : memorize) {
            System.out.print(d + " ");
        }
        System.out.println();
    }

    public static boolean run() throws Exception  {
        int[] numbers = { 0, 0 };
        Queue<Double> memorize = new LinkedList<>();
        int index = 0;
        double calculationResult = 0;
        char inOperator = ' ';
        boolean isRun = true, isRemove = false;

        Scanner sc = new Scanner(System.in);

        /*
            while 탈출 조건
            1. 사용자가 no / exit를 입력할 경우
            2. 저장공간이 꽉찬 경우
                => inRun = false로 변환
         */
        while(isRun) {
            inputNumbers(numbers, sc);
            inOperator = inputOperator(sc);
            calculationResult = calculation(numbers, inOperator);
            printResult(numbers, inOperator, calculationResult);

            isRemove = askRemove(sc);
            if(isRemove) {
                removeMemorize(memorize);
            }

            memorize.add(calculationResult);
            printMemorize(memorize);
            isRun = askMoreCalculation(sc);
        }

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
