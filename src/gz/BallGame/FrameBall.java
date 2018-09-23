package gz.BallGame;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FrameBall extends JFrame{
    private static ArrayList <Ball> list = new ArrayList<Ball>();//新建的list一定要初始化
    static ArrayList<Bullet> listB=new ArrayList<Bullet>();
    public  Graphics g;


    public void showUI() {

        this.setTitle("滑稽大作战");
        this.setSize(1200, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        // 设置布局,流式布局
        this.setLayout(new FlowLayout());

        this.setVisible(true);
        g=this.getGraphics();

        //添加监听器
//      g.setColor(Color.black);
        Ball ball=new Ball(this,g);
        ball.set(600, 500, 40);
        list.add(ball);
        ListernerBall l=new ListernerBall(this,g,ball);
        l.setB(list,listB);
        this.addMouseListener(l);
        this.addMouseMotionListener(l);
    }

    public static void main(String[] args) {
        FrameBall fb=new FrameBall();
        fb.showUI();//可见才能得到画布

        DrawBall db=new DrawBall();
        db.setG(fb,fb.g,list);//传三个参数
        db.start();//启动线程，线程启动后，他会自动运行，并且只运行run方法，想要运行其他方法必须另外调用

        MoveBall mb=new MoveBall();
        mb.setL(fb, fb.g,list,listB);
        mb.start();//启动线程

    }

}
