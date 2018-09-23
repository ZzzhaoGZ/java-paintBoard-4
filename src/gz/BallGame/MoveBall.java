package gz.BallGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class MoveBall extends Thread  {
    int i = 0;
    private ArrayList<Ball> list;// =new ArrayList<Ball>();
    private ArrayList<Bullet> listB;
    private FrameBall fb;
    private Graphics g;
    private int x,y,r=20;
    Color c = new Color(238, 238, 238);//背景色
    int count=1;//控制球的数量

    public void setL( FrameBall fb, Graphics g,ArrayList<Ball> list,ArrayList<Bullet> listB) {
        this.list = list;
        this.listB=listB;
        this.fb = fb;
        this.g = g;
    }

    public void run() {

        while (true) {//创建次画布放在循环中，每次循环完一次队列中的小球之后就重新添加画布

            Image img = fb.createImage(fb.getWidth(), fb.getHeight()); // 图片要多次创建！！！！！！
            Graphics2D ig = (Graphics2D) img.getGraphics();
//          ImageIcon imgi=new ImageIcon("D:\\Pictures\\项目图片\\滑稽2.jpg");
//          ig.drawImage(imgi.getImage(), 0, 0,fb.getWidth(),fb.getHeight(),fb);//设置背景图片

            ig.setColor(Color.white);
            ig.fillRect(0, 0, fb.getWidth(), fb.getHeight());
            Ball mball=list.get(0);
            mball.draw(ig);
            mball.blood(ig);
            mball.crash(list);

            if(list.size()<7) {
                DrawBall db=new DrawBall();
                db.setG(fb,fb.g,list);//传参数
                db.start();//启动线程，线程启动后，他会自动运行，并且只运行run方法，想要运行其他方法必须另外调用
            }
            //执行队列中的小球
            for (i = 1; i < list.size(); i++) {
                // list.get(i).clear();
                Ball ball=list.get(i);
                ball.draw(ig);
                ball.blood(ig);
                ball.crash(list);
                ball.run();

            }
            //执行子弹
            for(i=0;i<listB.size();i++) {
                Bullet bullet=listB.get(i);
                bullet.draw(ig);
                bullet.crash(list);
                bullet.run();
            }

            g.drawImage(img, 0, 0, fb);//等小球画完之后一次性清除一遍，如果在for循环中会闪烁

            try {
                sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
