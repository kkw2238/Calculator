package calculator.utility;

import java.util.Scanner;
import java.util.regex.Pattern;

/*
    사용자에게 입력을 요구하고 받는 처리를 담당하는 Class
 */
public class Comunication {
    static final String REGEXP_ONLY_OPERATOR = "(.[+-/*%])";

    // 가장 오래된 메모리를 삭제할 것인지 확인하는 함수
    public static boolean askRemove(Scanner sc) throws Exception {
        String answer = Comunication.askAndGetAnswer("가장 먼저 저장된 연산 결과를 삭제하시겠습니까? no/remove ( remove 입력시 삭제 ) : ", sc);
        answer = answer.toLowerCase();

        return Utility.judgeTwoStringOtherThrowing(answer, "remove", "no");
    }

    // 사용할 계산기 타입을 입력받는 함수
    public static int askCalculatorType(Scanner sc) throws Exception {
        String input = Comunication.askAndGetAnswer("사용할 계산기를 골라주세요. (0 : 사칙연산 계산기, 1 : 원의 넓이 계산기) : ", sc);
        return Integer.parseInt(input);
    }

    // 사용자에게 계산기를 더 사용할 것인지 물어보는 함수
    public static boolean askMoreCalculation(Scanner sc) throws Exception {
        String answer = Comunication.askAndGetAnswer("더 계산 하시겠습니까? yes/exit ( exit 입력시 종료 ) : ", sc);
        answer = answer.toLowerCase();

        return Utility.judgeTwoStringOtherThrowing(answer, "yes", "exit");
    }

    // 과거 저장 내역을 출력해 줄 것인지 물어보는 함수
    public static boolean askInquiry(Scanner sc) throws Exception {
        String answer = Comunication.askAndGetAnswer("저장된 연산 결과를 조회하시겠습니까? no/inquiry ( inquiry 입력시 출력 ) : ", sc);
        answer = answer.toLowerCase();

        return Utility.judgeTwoStringOtherThrowing(answer, "inquiry", "no");
    }

    // 유저의 input값을 가져오는 함수
    static public String askAndGetAnswer(String question, Scanner sc) {
        System.out.print(question);
        String answer = sc.nextLine();

        return answer;
    }

    // 연산자 입력받는 함수
    public static char getInputOperator(Scanner sc) throws Exception {
        String inOperator = Comunication.askAndGetAnswer("사칙연산 기호를 입력해주세요 : ", sc);

        // 사칙연산 기호 확인 ( 길이가 2이상 이거나, 정규식에 포함이 안된 경우 Throw )
        if(inOperator.length() > 1 || Pattern.matches(REGEXP_ONLY_OPERATOR, inOperator)) {
            throw new Exception("사칙연산 기호를 확인해주세요.");
        }

        return inOperator.charAt(0);
    }

    // 1개의 숫자를 입력받는 함수
    public static Number getInputNumber(Scanner sc, boolean isAbleMinusInt) throws Exception {
        String question = "정수 혹은 실수를 입력해주세요 : ";
        if(!isAbleMinusInt) {
            question = "양의 " + question;
        }

        String input = askAndGetAnswer(question, sc);
        return Utility.convertToNumber(input, isAbleMinusInt);
    }

    // 2개의 숫자를 입력받는 함수
    public static Number[] getInputTwoNumbers(Scanner sc, boolean isAbleMinusInt) throws Exception {
        String input = "";
        Number[] result = new Number[2];
        for(int i = 0; i < result.length; ++i) {
            input = askAndGetAnswer(String.format("%d번째 양의 정수 혹은 실수를 입력해주세요 : ", i + 1), sc);
            result[i] = Utility.convertToNumber(input, false);
        }

        return result;
    }
}
