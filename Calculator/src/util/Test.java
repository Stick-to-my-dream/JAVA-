package util;

public class Test {
    public static void main(String[] args) {
        String txt = "-78+(1.2+4)*5/6";
        System.out.println(new Calculater().evalRPN(txt));
//        System.out.println(NifToSuf.nifToSuf(txt));
    }
}
