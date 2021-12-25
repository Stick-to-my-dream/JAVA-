package util;

import java.util.List;
import java.util.Stack;

/**
 * 由后缀表达式计算结果
 */
public class Calculater {
    private boolean isOperation(String token){
        //判断是否为运算符
        return "+-*/".contains(token);
    }

    /**
     * 这里涉及到整型和小数的计算，偷懒就全部转成小数去算了
     * @param old
     * @return
     */
    public double evalRPN(String old){
        List<String> list = NifToSuf.nifToSuf(old); //将中缀表达式转为后缀表达式
//        System.out.println(list);
        Stack<Double> stack = new Stack<>();    //用来计算的栈
        for(int i=0;i<list.size();i++){
            System.out.println(stack);
            if(this.isOperation(list.get(i))){  //如果是运算符，就弹出两个操作数计算，再把结果入栈stack
                Double right = stack.pop(); //先弹出来的是右操作数，减除法搞反操作数结果就错了
                Double left = stack.pop();
                stack.push(calculater(left,right,list.get(i)));
            }else{  //如果不是运算符就直接入栈stack
                stack.push(Double.parseDouble(list.get(i)));
            }
        }
        return stack.pop();
    }

    private Double calculater(Double left, Double right, String s) {
        switch (s){
            case"+": return left+right;
            case"-": return left-right;
            case"/": return left/right;
            default: return left*right;
        }
    }
}
