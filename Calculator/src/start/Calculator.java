package start;

import util.Calculater;
import util.Const;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Calculator extends JFrame implements ActionListener {
    /*====================上方控件=====================*/
    private JPanel jPanel_north = new JPanel();
    public JTextField input_text = new JTextField();

    /*====================下方控件=====================*/
    private JPanel jPanel_center = new JPanel();

    /*====================表达式运算=====================*/
    private Calculater calculater = new Calculater();

    public Calculator(){
        this.init();    //主窗口初始化
        this.addNorthComponent();   //显示框初始化
        this.addCenterComponent();  //操作部分初始化
    }

    //主窗口初始化
    public void init(){
        this.setTitle(Const.TITLE);
        this.setSize(Const.FRAME_W,Const.FRAME_H);
        this.setLayout(new BorderLayout()); //设置为边界布局
        this.setResizable(false);   //设置窗口大小不可缩放
        this.setLocation(Const.FRAME_X,Const.FRAME_Y);  //设置窗口显示在屏幕正中间
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


    }

    //JFrame上方显示框
    public void addNorthComponent(){
        this.input_text.setPreferredSize(new Dimension(484,100)); //使用setSize设置会失效
        this.input_text.setFont(new Font("Serief",Font.BOLD,26));
        jPanel_north.add(this.input_text);

        this.add(jPanel_north,BorderLayout.NORTH);

    }

    public void addCenterComponent(){
        String btn_text = "()C⇦123+456-789*.0/=";   //20个按钮
        String regex = "[\\+\\-*/.=()CX⇦]";
        this.jPanel_center.setLayout(new GridLayout(5,4));  //设置面板布局为网格布局(5行4列)
        for(int i=0;i<20;i++){  //循环将20个按钮添加进jpanel_center
            String temp = btn_text.substring(i,i+1);    //依次取出按钮标签
            JButton jbt = new JButton(temp);
            jbt.addActionListener(this);    //添加事件监听，重写方法

            if(temp.matches(regex)){ // 正则匹配
                // 若是除数字以外的字符
                jbt.setForeground(Color.GRAY);  //灰色
                jbt.setFont(new Font("Serief",Font.PLAIN,26));  //设置字体
            }else {
                //若是数字
                jbt.setFont(new Font("粗体",Font.BOLD,26)); //设置字体

            }
            //将按钮加入Jpanel_center
            this.jPanel_center.add(jbt);
        }
        //将jPanel_center加入JFrame
        this.add(jPanel_center);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String clickStr = e.getActionCommand(); //获取点击的按钮的标签
        if(".1234567890+-*/()".indexOf(clickStr) != -1){    //若输入不是"="
            //取出文本框里的字符再和clickStr拼接
            this.input_text.setText(this.input_text.getText() + clickStr);
            this.input_text.setHorizontalAlignment(JTextField.RIGHT);   //设置文本框字符从右侧开始显示
//            JOptionPane.showMessageDialog(this,clickStr);
        }else if("=".equals(clickStr)){ //计算
            Double result = this.calculater.evalRPN(this.input_text.getText()); //调用表达式计算
            this.input_text.setText(this.input_text.getText()+"="+result);
        }else if("C".equals(clickStr)){ //清空
            this.input_text.setText("");
        }else {//clickStr="⇦",删除右侧1位
            String temp = this.input_text.getText();
            if(temp.length()>0){    //边界判断
                this.input_text.setText(temp.substring(0,temp.length()-1));
            }
        }

    }
}
