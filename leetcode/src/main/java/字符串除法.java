import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * ���������ַ������֣�����Ҫ���������ַ������������������Ļ�������λС��
 */

public class �ַ������� {
    public static void main(String[] args) {
//        System.out.println(method1("10",2));
//        System.out.println(method1("10",3));
//        System.out.println(method1("1",8));
        // ��������
        System.out.println(method2("123456789", 1));    // 123456789
        System.out.println(method2("100", 3));         // 33.33
        System.out.println(method2("100", 4));         // 25
        System.out.println(method2("123", 456));        // 0.27
        System.out.println(method2("987654321", 123));  // 8029710
        System.out.println(method2("1", 3));           // 0.33
        System.out.println(method2("0", 123));         // 0
        System.out.println(method2("123456", 789));    // 156.47
        System.out.println(method2("-100", 3));        // -33.33
        System.out.println(method2("100", -3));        // -33.33
        System.out.println(method2("-100", -3));       // 33.33
        System.out.println(method2("000123", 4));     // 30.75
        System.out.println(method2("123000", 1000));   // 123
    }

    // ʹ��BigDecimal
    private static String method1(String s1,int s2){
        BigDecimal dividend  = new BigDecimal(s1);
        BigDecimal divisor = new BigDecimal(s2);

        if(divisor.compareTo(BigDecimal.ZERO) == 0){
            return "";
        }

        BigDecimal result = dividend.divide(divisor, 10, RoundingMode.HALF_UP);

        try{
            return result.setScale(0,RoundingMode.UNNECESSARY).toString();
        }catch (Exception e){
            return result.setScale(2,RoundingMode.HALF_UP).toString();
        }
    }

    //��ʹ��BigDecimal
    private static String method2(String dividendStr,int divisor){
        //1.��������Ϊ0
        if (divisor == 0) {
            throw new ArithmeticException("Divisor cannot be zero");
        }
        //2.����Ϊ0
        if (dividendStr.equals("0")) {
            return "0";
        }
        //3.�������
        boolean negative = false;
        if (dividendStr.startsWith("-")) {
            negative = true;
            dividendStr = dividendStr.substring(1);
        }
        if (divisor < 0) {
            negative = !negative;
            divisor = -divisor;
        }
        // 4.ȥ���ַ�����ǰ����
        dividendStr = removeLeadingZeros(dividendStr);
        if (dividendStr.isEmpty()) {
            return "0";
        }
        //5.ִ�г�������
        DivisionResult result=performDivision(dividendStr, divisor);

        // 6.��������ַ���
        StringBuilder sb = new StringBuilder();
        if (negative && !(result.integerPart.equals("0") && result.decimalPart.equals("0"))) {
            sb.append("-");
        }
        sb.append(result.integerPart);

        // ����С������
        if (!result.decimalPart.equals("0")) {
            // �������뵽2λС��
            String roundedDecimal = roundDecimal(result.decimalPart);
            if (!roundedDecimal.equals("00")) {
                sb.append(".").append(roundedDecimal);
            }
        }
        return sb.toString();
    }

    private static String roundDecimal(String decimal) {
        if (decimal.length() <= 2) {
            return decimal.length() == 1 ? decimal + "0" : decimal;
        }
        if (decimal.charAt(2) >= '5') {
            StringBuilder rounded = new StringBuilder();
            int carry = 1;
            //ǰ��λ+1
            for (int i = 1; i >= 0; i--) {
                int digit = (decimal.charAt(i) - '0') + carry;
                carry = digit / 10;
                rounded.insert(0, digit % 10);
            }
            //0.999...�����
            if (carry > 0) {
                rounded.insert(0, carry);
                // ����򻯴���ʵ�ʿ�����Ҫ������������
                return "00";
            }
            return rounded.toString();
        }else {
            return decimal.substring(0, 2);
        }
    }

    //ȥ��һ���ַ�����ǰ��0
    private static String removeLeadingZeros(String s) {
        int i = 0;
        while (i < s.length() && s.charAt(i) == '0') {
            i++;
        }
        return i == s.length() ? "0" : s.substring(i);
    }

    private static DivisionResult performDivision(String dividendStr, int divisor) {
        StringBuilder integerPart = new StringBuilder();
        StringBuilder decimalPart = new StringBuilder();

        // ������������
        int remainder = 0;
        for (int i = 0; i < dividendStr.length(); i++) {
            int digit = dividendStr.charAt(i) - '0';
            int num = remainder * 10 + digit;
            integerPart.append(num / divisor);
            remainder = num % divisor;
        }

        // ȥ���������ֵ�ǰ����
        String intPart = removeLeadingZeros(integerPart.toString());
        if (intPart.isEmpty()) {
            intPart = "0";
        }

        // ����С�����֣������㵽3λ�Ա��������룩
        if (remainder != 0) {
            for (int i = 0; i < 3; i++) { // ���㵽��3λС��
                remainder *= 10;
                decimalPart.append(remainder / divisor);
                remainder = remainder % divisor;
                if (remainder == 0) {
                    break;
                }
            }
        } else {
            decimalPart.append("0");
        }
        return new DivisionResult(intPart, decimalPart.toString());
    }
}

class DivisionResult {
    String integerPart;
    String decimalPart;

    DivisionResult(String integerPart, String decimalPart) {
        this.integerPart = integerPart;
        this.decimalPart = decimalPart;
    }
}
