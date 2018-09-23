package gz.BallGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class ListernerBall implements MouseListener,MouseMotionListener{
    private int x,y,r=20;
    public Graphics g;
    FrameBall fb;
    Ball ball=new Ball(fb,g);

    ArrayList<Bullet>listB;
    ArrayList<Ball> list;
    public ListernerBall(FrameBall fb,Graphics g,Ball ball) {
        // TODO Auto-generated constructor stub
        this.g=g;
        this.fb=fb;
        this.ball=ball;

    }
    public void setB(ArrayList<Ball> list,ArrayList<Bullet>listB) {
        this.list=list;
        this.listB=listB;
    }
    public void mouseClicked(MouseEvent e) {

    }

    public void mouseDragged(MouseEvent e) {
//      x=ball.x;
//      y=ball.y;
    }
    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        ball.x=e.getX();
        ball.y=e.getY();
    }
    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        Bullet bullet=new Bullet(fb,g,listB);
        bullet.x=e.getX();
        bullet.y=e.getY();
        listB.add(bullet);
    }
    @Override
    public void mouseReleased(MouseEvent e) {

    }
    @Override
    public void mouseEntered(MouseEvent e) {

    }
    @Override
    public void mouseExited(MouseEvent e) {

    }

}