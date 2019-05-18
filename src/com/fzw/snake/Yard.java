package com.fzw.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
/**
 * @author Administrator
 * @date 2019/5/18/13:43
 */

public class Yard extends JFrame {
    public static final int NodeSize = 15;//每个格子的大小
    public static final int NodeCount = 30;
    //活动范围长度
    public static final int AreaSize = NodeSize*NodeCount;
    //蛇的活动范围长和高
    static int x = AreaSize/2;
    static int y = AreaSize/2;

    //创建蛇
    Snake s = new Snake();
    //创建蛋蛋
    Egg egg = new Egg(10,10);
    public static void main(String[] args) {
        new Yard();
    }

    public Yard(){
        this.setSize(AreaSize*2,AreaSize*2);
        this.setLocation(500,200);
        this.setVisible(true);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        //设置键盘监听
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                s.keyPressed(e);
            }
        });
        while(true){
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.repaint();
        }
    }

    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.fillRect(0,0,this.getWidth(),this.getHeight());

        g.setColor(Color.BLACK);

        //1画网格
        for (int i = 0 ; i<=NodeCount;i++){
            g.drawLine(x,y+NodeSize*i,x+AreaSize,y+NodeSize*i);
            g.drawLine(x+NodeSize*i,y,x+NodeSize*i,y+AreaSize);
        }
        //2画蛇
        s.paint(g);
        //画蛋
        egg.paint(g);
        //吃蛋
        s.eat(egg);
        g.setColor(c);
    }

    //为了消除闪烁，使用双缓冲
    Image offScreenImage = null;
    @Override
    public void update(Graphics g) {
        if (offScreenImage == null){
            offScreenImage = this.createImage(this.getWidth(),this.getHeight());
            Graphics goff = offScreenImage.getGraphics();
            paint(goff);
            g.drawImage(offScreenImage,0,0,null);
        }
    }
}
