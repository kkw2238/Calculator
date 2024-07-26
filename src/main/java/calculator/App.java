package calculator;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        int a = 0, b = 0;
        String aString, bString;

        Scanner sc = new Scanner(System.in);

        try {
            System.out.println("첫번째 양의 정수를 입력해주세요.");
            aString = sc.nextLine();
            a = Integer.parseInt(aString);

            System.out.println("두번째 양의 정수를 입력해주세요.");
            bString = sc.nextLine();
            b = Integer.parseInt(bString);

            // 음의 정수 입력했을 경우 Throw
            if(a < 0 || b < 0)  {
                throw new Exception();
            }

        } catch (Exception e) {
            System.out.println("숫자가 아닌 내용을 입력하셨거나, 음의 정수를 입력하셨습니다.");
        }
    }
}
