package com.fzw.snake;

import java.awt.*;

/**
 * 蛇的节点
 * @author Administrator
 * @date 2019/5/18/13:42
 */

public class Node {
    int row,col;//位于哪一行那一列

    Node prev,next;
    public Node(int row, int col) {
        this.row = row;
        this.col = col;
    }
    public void paint(Graphics g){
        int x = Yard.x+col*Yard.NodeSize;
        int y = Yard.y+row*Yard.NodeSize;
        Color c = g.getColor();
        g.setColor(Color.BLACK);
        g.fillRect(x,y,Yard.NodeSize,Yard.NodeSize);
        g.setColor(c);
    }
}
