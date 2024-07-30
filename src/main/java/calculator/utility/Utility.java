package calculator.utility;

public class Utility {
    // 2개의 문자에 대해 첫번째 문자와 일치할 경우 True, 두번째 문자와 일치할 경우 False, 그 외 예외로 반환하는 함수
    static public boolean judgeTwoStringOtherThrowing(String str, String trueStr, String falseStr) throws Exception {
        if(str.equals(trueStr)) {
            return true;
        }
        else if(str.equals(falseStr)) {
            return false;
        }
        else {
            throw new Exception(trueStr + "/" + falseStr + " 중 하나만 입력해주십시오");
        }
    }

    // 문자열을 Number형태의 자료형으로 변환해주는 함수
    static public Number convertToNumber(String str, boolean isAbleMinusInt) throws Exception {
        Number number = 0;

        // 문자열에 .이 있으면 Double타입으로 치환
        if(str.contains(".")) {
            number = Double.parseDouble(str);
        }
        else {
            number = Integer.parseInt(str);
            // 음의 정수 입력했을 경우 Throw
            if (isAbleMinusInt && number.intValue() < 0) {
                throw new Exception("음의 정수를 입력하셨습니다." );
            }
        }

        return number;
    }

    // array내용을 보여주는 함수
    static public void showDoubleArrays(double[] arrays) {
        if(arrays.length == 0) {
            System.out.println("데이터가 존재하지 않습니다.");
            return;
        }
        for(int i = 0; i < arrays.length; i++) {
            System.out.println(i + " : " + arrays[i]);
        }
    }

    // 연산자인지 체크하는 함수
    static public boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }
}
