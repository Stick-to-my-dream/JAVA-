package util;

import java.awt.*;

public class Const {
    //主窗口的高和宽
    public static final int FRAME_H = 600;
    public static final int FRAME_W = 484;
    //获取屏幕的高和宽
    public static final int SCREEN_H = Toolkit.getDefaultToolkit().getScreenSize().height;
    public static final int SCREEN_W = Toolkit.getDefaultToolkit().getScreenSize().width;
    //将主窗口显示再屏幕中心的初始位置
    public static final int FRAME_X = (SCREEN_W-FRAME_W)/2;
    public static final int FRAME_Y = (SCREEN_H-FRAME_H)/2;
    public static final String TITLE = "计算器";
}
