/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.game;

import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Color;

/**
 *
 * @author DELL
 */
public class MapGenerator {
    public int[][] map;
    public int bricksWidth;
    public int bricksHeight;
    public MapGenerator(int row, int col){
        map = new int[row][col];
        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j<map[0].length; j++) {
                map[i][j] = 1;
            }
        }
        bricksWidth = 540 / col;
        bricksHeight = 150 / row;
    }
    public void draw(Graphics2D g) { //To specify the computer which type of graphics we need
        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j<map[0].length; j++) {
                if(map[i][j] > 0) { 
                    g.setColor(Color.red);
                    g.fillRect(j * bricksWidth + 80, i * bricksHeight + 50, bricksWidth, bricksHeight);
                    
                    g.setStroke(new BasicStroke(3));
                    g.setColor(Color.black);
                    g.drawRect(j * bricksWidth + 80, i * bricksHeight + 50, bricksWidth, bricksHeight);
                    
                }
            }
        }
    }
    public void setBricksValue(int value, int row, int col) {//to change value from 1 tto 0
        map[row][col] = value;
    }
}
