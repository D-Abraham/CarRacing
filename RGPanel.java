/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dan
 */
import java.awt.*;
import javax.swing.*;
import java.awt.event.*; 

public  class RGPanel extends JPanel{
  
  /**
   * constructor
   * @param width is the width of the panel.
   * @param height is the height of the panel.
   */
  public RGPanel(int width, int height ){
    setPreferredSize(new Dimension(width,height));
    
    setBackground(Color.white);
    
  }
  /*
   * PaintComponent will do all the work, create and and call the draw method of each class.
   * @param g is the graphics context.
   */
  
  public void paintComponent(Graphics g){
    super.paintComponent(g);
    
    Graphics2D g2 = (Graphics2D)g;
    
    RGCar car=new RGCar(250,250);
    car.draw(g2);
      
      
    }
  
  public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();
				frame.add(new RGPanel(700,700));
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}
  }
  
  
  

