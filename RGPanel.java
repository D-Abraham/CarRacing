// Vrej | March 9, 2018 | RGPanel class, this class extends JPanel and implements ActionListener. It's a very essential class, it create most of the GUI and makes the RGTrack, this is then used to start running the game itself.
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class RGPanel extends JPanel implements ActionListener{
  JButton quitb = new JButton("Quit"); // Initialize the quitb variable which simply just holds a JButton that quits the game when pressed.
  //private RGTrack track = new RGTrack();
  
  public RGPanel(){
    setPreferredSize(new Dimension(700,470)); // Set the size of the panel.
    setBackground(Color.white); // Set the background color to white.
    setLayout(null); // Set the layout to null, this will allow the placement of components in any location, rather than grid-like layout.
    quitb.setBounds(50,30,80,60); // Set the location and size of the quit button.
    this.add(quitb); // Add the quit button to the panel.
    quitb.addActionListener(this); // Make this class capture all the events of the quit button.
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
    if(e.getSource() == quitb){ // If source is the quit button, then continue.
      System.exit(0); // Exit the program.
    }
  }
}