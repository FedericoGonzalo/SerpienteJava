/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.serpiente;

import java.awt.*;
import java.awt.event.*;


import javax.swing.*;
import java.util.Random;



/**
 *
 * @author BASSTARD
 */
public class JuegoPanel extends JPanel implements ActionListener{
    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT =600;
    static final int UNIT_SIZE =25;
    static final int GAME_UNITS=(SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
    static final int DELAY=75;
    final int x[]=new int[GAME_UNITS];
    final int y[]=new int[GAME_UNITS];
    int partesCuerpo =6;
    int manzanasComidas;
    int manzanaX;
    int manzanaY;
    char direccion= 'R';
    boolean seMueve=false;
    Timer reloj;
    Random azar;
    
 
    JuegoPanel(){
        azar=new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new botones());
        comienzoJuego();
    
    }
    public void comienzoJuego(){
      nuevaManzana();
      seMueve=true;
      reloj= new Timer(DELAY, this);
      reloj.start();
   }
    @Override
    public void paintComponent(Graphics g){
      super.paintComponent(g);
       dibujo(g);
    }
    
    public void dibujo(Graphics g){
        if(seMueve){
         for(int i=0;i<SCREEN_HEIGHT/UNIT_SIZE;i++){
        g.drawLine(i*UNIT_SIZE,0,i*UNIT_SIZE,SCREEN_HEIGHT);
        g.drawLine(0, i*UNIT_SIZE, SCREEN_WIDTH, i*UNIT_SIZE);
        }
        
        g.setColor(Color.red);
        g.fillOval(manzanaX, manzanaY, UNIT_SIZE, UNIT_SIZE);
        
        for(int i =0;i< partesCuerpo;i++){
             if(i==0){
                 g.setColor(Color.green);
                 g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
              }
             else{
            //   g.setColor(new Color(45,176,0));   
               g.setColor(new Color(azar.nextInt(255),azar.nextInt(255),azar.nextInt(255)));   
           
               
               g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
             }
        }
          g.setColor(Color.red);
    g.setFont(new Font("Ink Free", Font.BOLD, 25));
    FontMetrics metrics=getFontMetrics(g.getFont());
    g.drawString("PUNTAJE:"+manzanasComidas, (SCREEN_WIDTH -metrics.stringWidth("PUNTAJE:"+manzanasComidas))/2,g.getFont().getSize());
  
        }
        else{
         fin(g);
        }
       
    }
    
    
    public void nuevaManzana(){
    manzanaX=azar.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
    
    manzanaY=azar.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
    }
    
    
    public void mover(){
    for (int i=partesCuerpo;i>0;i--){
        x[i]=x[i-1];
        y[i]=y[i-1];
    }
   switch(direccion) {
		case 'U':
			y[0] = y[0] - UNIT_SIZE;
			break;
		case 'D':
			y[0] = y[0] + UNIT_SIZE;
			break;
		case 'L':
			x[0] = x[0] - UNIT_SIZE;
			break;
		case 'R':
			x[0] = x[0] + UNIT_SIZE;
			break;
		}
     }
    public void checkManzana(){
        if((x[0]== manzanaX)&&(y[0]== manzanaY)){
            partesCuerpo++;
            manzanasComidas++;
            nuevaManzana();
        }
    }
    public void checkColision(){
       //si cabeza toca cuerpo
        for(int i=partesCuerpo;i>0;i--){
        if((x[0]==x[i])&&(y[0]==y[i])){
          seMueve=false;
        }
        }
      //si toca borde izq
      if(x[0]<0){
      seMueve=false; 
      }
      //si toca borde der
      if(x[0]>SCREEN_WIDTH){
       seMueve=false;
      }
       //si toca borde sup
      if(y[0]<0){
      seMueve=false; 
      }
      //si toca borde INF
      if(y[0]>SCREEN_HEIGHT){
       seMueve=false;
      }
    if(!seMueve){
     reloj.stop();
    }
      
    }
    public void fin(Graphics g){
    //puntaje
    g.setColor(Color.red);
    g.setFont(new Font("Ink Free", Font.BOLD, 25));
    FontMetrics metrics1=getFontMetrics(g.getFont());
    g.drawString("PUNTAJE:"+manzanasComidas, (SCREEN_WIDTH -metrics1.stringWidth("PUNTAJE:"+manzanasComidas))/2,g.getFont().getSize());
  

    //texto final
    g.setColor(Color.red);
    g.setFont(new Font("Ink Free", Font.BOLD, 25));
    FontMetrics metrics2=getFontMetrics(g.getFont());
    g.drawString("ESTE ES EL FIN, MI UNICO AMIGO EL FINAL", (SCREEN_WIDTH -metrics2.stringWidth("ESTE ES EL FIN, MI UNICO AMIGO EL FINAL"))/2,SCREEN_HEIGHT/2);
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(seMueve){
            mover();
            checkManzana();
            checkColision();
          }
        repaint();
    }
    public class botones extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				if(direccion != 'R') {
					direccion = 'L';
				}
				break;
			case KeyEvent.VK_RIGHT:
				if(direccion != 'L') {
					direccion = 'R';
				}
				break;
			case KeyEvent.VK_UP:
				if(direccion != 'D') {
					direccion = 'U';
				}
				break;
			case KeyEvent.VK_DOWN:
				if(direccion != 'U') {
					direccion = 'D';
				}
				break;
			}
		}

   }
     
}
