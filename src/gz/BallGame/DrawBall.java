package gz.BallGame;

import java.awt.Graphics;
import java.util.ArrayList;

public class DrawBall extends Thread{
    ArrayList <Ball> list;//注意后面的写法，写错会报空指针异常
    Graphics g;
    FrameBall fb;
    int count=0;
    public void setG(FrameBall fb,Graphics g,ArrayList <Ball> list) {
        this.g=g;
        this.fb=fb;
        this.list=list;
    }

    public void run() {
        while(true) {
            if(list.size()==10) {
                break;//设置小球存储的个数
            }

            Ball b=new Ball(fb,g);//循环中不断新建一个球

            list.add(b);//把球放进队列
        }
    }
}

