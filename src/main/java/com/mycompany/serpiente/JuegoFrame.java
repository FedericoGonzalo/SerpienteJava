/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.serpiente;

import javax.swing.JFrame;

/**
 *
 * @author BASSTARD
 */
public class JuegoFrame  extends JFrame{
    JuegoFrame(){
    /*JuegoPanel panel = new JuegoPanel();
    this.add(panel);
    */
    this.add(new JuegoPanel());
    this.setTitle("SERPIENTE");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setResizable(false);
    this.pack();
    this.setVisible(true);
    this.setLocationRelativeTo(null);
    
    }
}
