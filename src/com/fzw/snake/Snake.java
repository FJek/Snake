package com.fzw.snake;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * @author Administrator
 * @date 2019/5/18/13:42
 */

public class Snake  {
    Dir dir = Dir.L;
    Node head,tail;//头和尾巴

    public Snake() {
        head = new Node(20,20);
        tail = head;
    }

    //加头
    public void addTohead(){
        Node node = null;
        switch (dir){
            case L:
                node = new Node(head.row,head.col-1);
                break;
            case R:
                node = new Node(head.row,head.col+1);
                break;
            case U:
                node = new Node(head.row-1,head.col);
                break;
            case D:
                node = new Node(head.row+1,head.col);
                break;
        }
        //加头
        head.prev = node;
        node.next = head;
        head = node;
    }
    //删尾巴
    private void deleteTail(){
        if (tail == null){
            return;
        }
        tail = tail.prev;
        tail.next.prev = null;//不写会内存泄露
        tail.next = null;
    }
    //画蛇方法
    public void paint(Graphics g) {
        Node n = head;
        while (n != null){
            //画节点
            n.paint(g);
            n = n.next;
        }
        move();
    }
    private void move(){
        addTohead();
        deleteTail();
        //边界监测
        boundaryCheck();
    }
    private void boundaryCheck(){
        if(head.row < 0) head.row = Yard.NodeCount-1;
        if(head.col < 0) head.col = Yard.NodeCount-1;
        if(head.row > Yard.NodeCount-1) head.row = 0;
        if(head.col > Yard.NodeCount-1) head.col = 0;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key){
            case KeyEvent.VK_LEFT:
                dir = Dir.L;
                break;
            case KeyEvent.VK_RIGHT:
                dir = Dir.R;
                break;
            case KeyEvent.VK_UP:
                dir = Dir.U;
                break;
            case KeyEvent.VK_DOWN:
                dir = Dir.D;
                break;
        }
    }

    public void eat(Egg egg) {
        //判断吃到没
        if(head.row == egg.row && head.col == egg.col){
            addTohead();
            egg.reAppear();
        }
    }
}
