// Vrej | March 9, 2018 | RGPanel class, this class extends JPanel and implements ActionListener. It's a very essential class, it create most of the GUI and makes the RGTrack, this is then used to start running the game itself.
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.concurrent.TimeUnit;

public class RGPanel extends JPanel implements ActionListener{
  JButton quitb = new JButton("Quit"); // Initialize the quitb variable which simply just holds a JButton that quits the game when pressed.
  JButton restartb = new JButton("Restart"); // Initialize the restartb variable which holds a JButton that resets and restarts the game when pressed.
  JButton startb = new JButton("Start"); // Initialize the startb variable holds a JButton that starts/continues the timer and the cars.
  JButton stopb = new JButton("Stop"); // Initialize the stopb variable which holds a JButton that stops the game, it will pause the timer and the cars.
  Timer timer = new Timer(100,this);
  private long startTime = 0;
  private RGTrack track = new RGTrack();
  JLabel timerText = new JLabel();
  
  public RGPanel(){
    setPreferredSize(new Dimension(601,501)); // Set the size of the panel.
    setBackground(Color.white); // Set the background color to white.
    setLayout(null); // Set the layout to null, this will allow the placement of components in any location, rather than grid-like layout.
    quitb.setBounds(50,0,80,60); // Set the location and size of the quit button.
    this.add(quitb); // Add the quit button to the panel.
    quitb.addActionListener(this); // Make this class capture all the events of the quit button.
    restartb.setBounds(150,0,80,60); // Set the location and size of the restart button.
    this.add(restartb); // Add the restart button to the panel.
    restartb.addActionListener(this); // Make this class capture all the events of the restart button.
    startb.setBounds(250,0,80,60); // Set the location and size of the start button.
    this.add(startb); // Add the start button to the panel.
    startb.addActionListener(this); // Make this class capture all the events of the start button.
    stopb.setBounds(350,0,80,60); // Set the location and size of the stop button.
    this.add(stopb); // Add the stop button to the panel.
    stopb.addActionListener(this); // Make this class capture all the events of the stop button.
    timerText.setLocation(570, 0); //trying to have it display the time (doesn't work)
    this.add(timerText);
  }
  
//  // A simple method that can draw things using an Graphics object and displays them to the window. Doesn't return anything and is called internally.
  public void paintComponent(Graphics g){
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D)g; // Cast the Graphics object as Graphics2D and assign it to the "g2" variable.
    track.paintComponent(g2);
  }
  
  // The important method that captures all of the events that the buttons call. Has a parameter that holds the ActionEvent object which has all of the information of what was pressed.
  public void actionPerformed(ActionEvent e){
    if(e.getSource() == timer){
     if(track.move() != null) { //moves the car and stops if a car wins
    	 timer.stop();
    }//Long.toString(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - startTime)) to add in later
     timerText.setText("anything!"); //the text should print something
     repaint();
    }
    if(e.getSource() == quitb){ // If source is the quit button, then continue.
      System.exit(0); // Exit the program.
    }
    if(e.getSource() == startb){
      startTime = System.currentTimeMillis();
      timer.start();
    }
    if(e.getSource() == stopb){
      timer.stop();
    }
  }
}
