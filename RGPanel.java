import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class RGPanel extends JPanel implements ActionListener{
  //private RGTrack track = new RGTrack();
  
  public RGPanel(){
    setPreferredSize(new Dimension(700,470)); // Set the size of the panel.
    setBackground(Color.white); // Set the background color to white.
    setLayout(null); // Set the layout to null, this will allow the placement of components in any location, rather than grid-like layout.
  }
  
  // A simple method that can draw things using an Graphics object and displays them to the window. Doesn't return anything and is called internally.
  public void paintComponent(Graphics g){
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D)g; // Cast the Graphics object as Graphics2D and assign it to the "g2" variable.
    RGCar car = new RGCar();
    car.paintComponent(g);
  }
  
  // The important method that captures all of the events that the buttons call. Has a parameter that holds the ActionEvent object which has all of the information of what was pressed.
  public void actionPerformed(ActionEvent e){
    
  }
}