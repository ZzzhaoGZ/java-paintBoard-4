package gz.BallGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Bullet {
    ArrayList<Bullet>listB;
    int x,y;
    int R = 5;// 半径
    private int blood =  1;
    private int speedX =  10, speedY = 0;// 子弹运动速度
    Graphics g;
    FrameBall fb;

    public Bullet(FrameBall fb,Graphics g,ArrayList<Bullet>listB) {
        this.g = g;
        this.fb = fb;
        this.listB=listB;

    }
    public void set(int x,int y,int R) {
        this.x=x;
        this.y=y;
        this.R=R;
    }

    public void draw(Graphics g) {
        ImageIcon image =new ImageIcon("D:\\Code\\java\\dasan\\java画图板之四\\滑稽4.jpg");
        Image img=image.getImage();
        g.drawImage(img, x-R, y-R,2*R,2*R, null);
    }

    public void run() {// 子弹运动
        x += speedX;// 每一次run，就移动一次速度值
        y += speedY;
        // 只执行一次run方法,子弹是不会动的
    }

    public void crash(ArrayList<Ball> list) {
        for (int i = 1; i < list.size(); i++) {
            Ball ball = list.get(i);

            int d = (x - ball.x) * (x - ball.x) + (y - ball.y) * (y - ball.y);// 横纵坐标平方和
            int dis = (R + ball.R) * (R + ball.R);// 半径之和的平方
            if (d <= dis) {
                blood--;
                ball.blood--;
                if (ball.blood <= 0 && list != null)
                    list.remove(i);// 不能和下行调换，否则会指针溢出，因为下面的可能为空
                if (blood <= 0)
                    listB.remove(this);

            }

        }
    }

}