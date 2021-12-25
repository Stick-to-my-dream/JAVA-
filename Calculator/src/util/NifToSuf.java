package util;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class NifToSuf {
    /**
     * 中缀表达式转后缀表达式
     * @param old 中缀表达式
     * @return 后缀表达式
     * exp 存储后缀表达式
     * op 运算符栈
     * sb(StringBuffer) 暂存多位数字
     */
    public static List<String> nifToSuf(String old){
        List<String> exp = new ArrayList<String>();
        Stack<Character> op = new Stack<Character>();
        StringBuffer sb = new StringBuffer();   //记录多位数字
        char[] char_old = old.toCharArray();    //将中缀表达式String转为字符数组
        for(int i=0;i<old.length();i++){
            // 如果是数字
            if((i==0 && char_old[i]!='(') || (i!=0 && isDigit(char_old[i],char_old[i-1]))){
                sb.append(char_old[i]);
                //如果是最后一个字符或者下一个为运算符，则将sb里的数字直接入exp栈
                if(i==char_old.length-1 || (i+1<char_old.length && isSymbol(char_old[i+1]))){
                    exp.add(sb.toString());
                    sb = new StringBuffer();    //sb入栈后，清空sb用于下一个数字的记录
                }
            }else if(isBracket(char_old[i])){//如果是括号
                //左括号优先级最低直接入op栈
                if(char_old[i]=='('){
                    op.push(char_old[i]);
                }else {
                    //如果是右括号，则将op出栈，直到遇到右括号，同时丢弃这对括号
                    char temp;
                    while((temp=op.pop())!='('){
                        exp.add(temp+""); //将char类型转为String
                    }
                }
            }else if(isOperation(char_old[i])){//如果是运算符
                while(true){
                    //如果op栈为空，直接入栈
                    if(op.isEmpty()){
                        op.push(char_old[i]);
                        break;
                    }else if(getPriority(op.peek())<getPriority(char_old[i])){//peek获取栈顶元素的值，但不弹出
                        // 如果当前运算符的优先级大于op栈顶的优先级 则将当前运算符如op栈
                        op.push(char_old[i]);
//                        System.out.println("char:"+char_old[i]);
                        break;
                    }else {
                        exp.add(op.pop()+"");
                    }
                }
            }
        }
        //将op栈剩余运算符入exp
        while(!op.isEmpty()){
            exp.add(op.pop()+"");
        }
        return exp;
    }

    private static int getPriority(Character peek) {
        switch (peek){
            case '(':
                return 0;
            case '+':
            case '-':
                return 1;
            default:    //乘除的优先级最高
                return 2;
        }
    }

    private static boolean isSymbol(char c) {
        return isOperation(c) || isBracket(c);
    }

    private static boolean isBracket(char c) {
        return c=='(' || c==')';
    }

    private static boolean isOperation(char c) {
        return c=='+' || c=='-' || c=='/' || c=='*';
    }

    private static boolean isDigit(char c, char leftBracket) {
        //前一个是左括号，那么下面一个可能是负数
        if(leftBracket=='('){
            return c=='-' || (c>=48 && c<=57);
        }
        //前一个不是左括号，只可能是小数点或者数字
        return c=='.' || (c>=48 && c<=57);
    }


}
