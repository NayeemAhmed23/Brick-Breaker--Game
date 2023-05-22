/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.game;

/**
 *
 * @author DELL
 */
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePlay extends JPanel implements KeyListener, ActionListener{
    private boolean play = false;
    private int score = 0;
    private int totalBricks = 21;
    private Timer Timer;
    private int delay = 8;
    private int playerX = 310;
    private int ballposX = 120;
    private int ballposY = 350;
    private int ballXdir = -1;
    private int ballYdir = -2;
    private MapGenerator map;
    
    public GamePlay(){
        map = new MapGenerator(3,7);
        addKeyListener(this);
        setFocusable(true);
        Timer = new Timer(delay, this); // Timer is required to introduce delay
        Timer.start();
    }
    public void paint(Graphics g){
        g.setColor(Color.black);//for panel
        g.fillRect(1,1,692,592);
        map.draw((Graphics2D) g);
        
        g.setColor(Color.yellow);//for 3 borders
        g.fillRect(0,0,3,592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(691,0, 3, 592);
        
        g.setColor(Color.white);//for score
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString(""+ score, 590, 30);
        
        g.setColor(Color.yellow);//for slider
        g.fillRect(playerX, 550,100,8);
        
        g.setColor(Color.GREEN);
        g.fillOval(ballposX, ballposY, 20, 20);
        if(ballposY > 570) { //stopped at certain condition
            play = false;
            ballXdir = 0;
            ballYdir = 0;
            g.setColor(Color.red);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("  Game over Score : " + score, 190,300);
            
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("  press Enter to Restart", 190, 340);
        }
        if(totalBricks == 0) {//Restart the game
            play = false;
            ballXdir = -1;
            ballYdir = -2;
            g.setColor(Color.red);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("  your Score : " + score, 190,300);
            
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("  press Enter to Restart", 190, 340); 
        }
        g.dispose(); //it kill the graphic program 
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Timer.start();//now i have start the timer 
        if(play) { //if my play is true then only it run otherwise it won't run
            if(new Rectangle(ballposX, ballposY,20, 20).intersects(new Rectangle(playerX, 550, 100,8 ))) {
            ballYdir = -ballYdir;
            }
            A:
            for(int i = 0; i < map.map.length; i++) {
                for(int j = 0; j < map.map[0].length; j++) {
                    if(map.map[i][j] > 0) {
                        int brickX = (j * map.bricksWidth) + 80;
                        int brickY = (i * map.bricksHeight) + 50;
                        int bricksWidth = map.bricksWidth;
                        int  bricksHeight = map.bricksHeight;
                        
                        Rectangle rect = new Rectangle(brickX, brickY, bricksWidth, bricksHeight); // this is the rectangle where we haave to fill the rectangle
                        Rectangle ballrect = new Rectangle(ballposX, ballposY, 20, 20);
                        Rectangle brickrect = rect;
                        
                        if(ballrect.intersects(brickrect)) {
                            map.setBricksValue(0, i, j);
                            totalBricks--;
                            score += 5;
                            if(ballposX + 19 <= brickrect.x || ballposX + 1 >= brickrect.x + bricksWidth) {
                                ballXdir = -ballXdir;
                            }
                            else {
                                ballYdir = -ballYdir;
                            }
                            break A;
                        }
                    }
                }
            }
            ballposX += ballXdir;
            ballposY += ballYdir;
            if(ballposX < 0) { //left
                ballXdir = -ballXdir;
            }
            if(ballposY < 0) { //top
                ballYdir = -ballYdir;
            }
            if(ballposX > 670) { //right
                ballXdir = -ballXdir;
            }
        }
        repaint();//when play is false or it will run when the play variable is true and where ever the ball hits the places will filled with black 
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if(playerX >= 600) {
                playerX = 600;
            }
            else {
                moveRight();
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            if(playerX < 10) {
                playerX = 10;
            }
            else {
                moveLeft();
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_ENTER) {
            if(play == false) {
                ballposX = 120;
                ballposY = 350;
                ballXdir = -1;
                ballYdir = -2;
                score = 0;
                playerX = 310;
                totalBricks =  21;
                map = new MapGenerator(3, 7);
                repaint();
            }
        }
    }


    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    public void moveRight() {
        play = true;
        playerX += 20;
    }
    public void moveLeft() {
           play = true;
        playerX -= 20;
    }
    
}
