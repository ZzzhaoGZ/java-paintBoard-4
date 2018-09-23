package gz.BallGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Ball {
    Random rand = new Random();
    int R = rand.nextInt(70) + 20;// 半径
    int x = rand.nextInt(1200);
    int y = rand.nextInt(600);
    int blood = rand.nextInt(5) + 3;
    int fblood = blood;//满血值
    int choose=rand.nextInt(4);
    //球的几种颜色的不停变换
    int []arr= {255,192,128,64,0};
    int i=rand.nextInt(arr.length);
    int j=rand.nextInt(arr.length);
    int k=rand.nextInt(arr.length);

    int speedX =  70-R-20, speedY = 70-R-20;// 小球运动速度

    private int r = 1200, d = 700;// 右限，下限
    public Graphics g;
    public FrameBall fb;

    public Ball(FrameBall fb,Graphics g) {//这个是必须要传的，放在构造方法中
        this.g = g;
        this.fb = fb;

    }
    public void set(int x,int y,int R) {//这个是可选传的
        this.x=x;
        this.y=y;
        this.R=R;
    }


    public void draw(Graphics g) {
        Color C=new Color(arr[k],arr[j],arr[i]);
        g.setColor(C);

        ImageIcon image = null;
        //这里可以使用不同的图片，从而出现不同的滑稽表情，为了方便我全部用了一种
        if(choose==0) image = new ImageIcon("D:\\Code\\java\\dasan\\java画图板之四\\滑稽1.jpg");
        else if(choose==1)image = new ImageIcon("D:\\Code\\java\\dasan\\java画图板之四\\滑稽2.jpg");
        else if(choose==2)image = new ImageIcon("D:\\Code\\java\\dasan\\java画图板之四\\滑稽3.jpg");
        else if(choose==3)image = new ImageIcon("D:\\Code\\java\\dasan\\java画图板之四\\滑稽1.jpg");

        Image img=image.getImage();
        g.drawImage(img, x-R, y-R,2*R,2*R, null);
//      g.fillOval(x - R, y - R, 2*R, 2*R);//这里是宽和高，所以要写直径，否则就会在奇怪的地方相撞

        //设置字体，将血量值显示在图片内部
        Font mf=new Font(Font.DIALOG,Font.BOLD,15);
        g.setFont(mf);
        g.setColor(Color.black);
        String  Blood=String.valueOf(blood);//同步球的血量和血值
        g.drawString(Blood, x-6, y+5);

    }

    public void run() {// 表示小球的运动，可反弹，必须放在while循环中才能跑起来

        if (y >= d)// 当y接触到下限时
            speedY *= -1;// 速度反向
        else if (y <= 0)
            speedY *= -1;
        if (x >= r)
            speedX *= -1;
        else if (x <= 0)
            speedX *= -1;
        x += speedX;// 每一次run，就移动一次速度值
        y += speedY;
        // 只执行一次run方法,小球是不会动的

    }

    public void crash(ArrayList<Ball> list) {
        for (int i = 0; i < list.size(); i++) {
            Ball ball = list.get(i);
            if (this != ball) {
                int d = (x - ball.x) * (x - ball.x) + (y - ball.y) * (y - ball.y);// 横纵坐标平方和
                int dis = (R + ball.R) * (R + ball.R);// 半径之和的平方
                if (d <= dis) {
                    int sx=speedX;
                    int sy=speedY;
                    speedX=ball.speedX;
                    speedY=ball.speedY;
                    ball.speedX=sx;
                    ball.speedY=sy;
                }
            }
        }
    }

    public void blood(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(x - R, y - 5 - R, blood * 10, 5);
        g.setColor(Color.gray);//失血时空去的血条
        g.fillRect(x - R + blood * 10, y - R - 5, (fblood - blood) * 10, 5);
    }
}