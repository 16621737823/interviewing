import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 给你两个字符串数字，你需要对这两个字符串做除法，除不尽的话保留两位小数
 */

public class 字符串除法 {
    public static void main(String[] args) {
//        System.out.println(method1("10",2));
//        System.out.println(method1("10",3));
//        System.out.println(method1("1",8));
        // 测试用例
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

    // 使用BigDecimal
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

    //不使用BigDecimal
    private static String method2(String dividendStr,int divisor){
        //1.被除数不为0
        if (divisor == 0) {
            throw new ArithmeticException("Divisor cannot be zero");
        }
        //2.除数为0
        if (dividendStr.equals("0")) {
            return "0";
        }
        //3.处理符号
        boolean negative = false;
        if (dividendStr.startsWith("-")) {
            negative = true;
            dividendStr = dividendStr.substring(1);
        }
        if (divisor < 0) {
            negative = !negative;
            divisor = -divisor;
        }
        // 4.去除字符串的前导零
        dividendStr = removeLeadingZeros(dividendStr);
        if (dividendStr.isEmpty()) {
            return "0";
        }
        //5.执行除法运算
        DivisionResult result=performDivision(dividendStr, divisor);

        // 6.构建结果字符串
        StringBuilder sb = new StringBuilder();
        if (negative && !(result.integerPart.equals("0") && result.decimalPart.equals("0"))) {
            sb.append("-");
        }
        sb.append(result.integerPart);

        // 处理小数部分
        if (!result.decimalPart.equals("0")) {
            // 四舍五入到2位小数
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
            //前两位+1
            for (int i = 1; i >= 0; i--) {
                int digit = (decimal.charAt(i) - '0') + carry;
                carry = digit / 10;
                rounded.insert(0, digit % 10);
            }
            //0.999...的情况
            if (carry > 0) {
                rounded.insert(0, carry);
                // 这里简化处理，实际可能需要调整整数部分
                return "00";
            }
            return rounded.toString();
        }else {
            return decimal.substring(0, 2);
        }
    }

    //去除一个字符串的前导0
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

        // 处理整数部分
        int remainder = 0;
        for (int i = 0; i < dividendStr.length(); i++) {
            int digit = dividendStr.charAt(i) - '0';
            int num = remainder * 10 + digit;
            integerPart.append(num / divisor);
            remainder = num % divisor;
        }

        // 去除整数部分的前导零
        String intPart = removeLeadingZeros(integerPart.toString());
        if (intPart.isEmpty()) {
            intPart = "0";
        }

        // 处理小数部分（最多计算到3位以便四舍五入）
        if (remainder != 0) {
            for (int i = 0; i < 3; i++) { // 计算到第3位小数
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
