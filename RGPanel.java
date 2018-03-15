// Vrej | March 14, 2018 | RGPanel class, this class extends JPanel and implements ActionListener. It's a very essential class, it create most of the GUI and makes the RGTrack, this is then used to start running the game itself.
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.concurrent.TimeUnit;

public class RGPanel extends JPanel implements ActionListener{
  JButton quitb = new JButton("Quit"); // Initialize the quitb variable which simply just holds a JButton that quits the game when pressed.
  JButton restartb = new JButton("Restart"); // Initialize the restartb variable which holds a JButton that resets and restarts the game when pressed.
  JButton startb = new JButton("Start"); // Initialize the startb variable holds a JButton that starts/continues the timer and the cars.
  JButton stopb = new JButton("Stop"); // Initialize the stopb variable which holds a JButton that stops the game, it will pause the timer and the cars.
  Timer timer = new Timer(100,this); // Initialize the timer that's used to determine how long the game took to finish, it runs every 100 miliseconds.
  private long timerStart = 0; // Initialize the timerStart variable which holds the initial time that the game began running, it's used to calculate the total number of seconds the game took to finish.
  private long timerCurrent = 0; // Initialize the timerCurrent variable which holds the current number of seconds the game has been running.
  private long timerPrevious = 0; // Initialize the timerPrevious variable which holds the total number of seconds the game has been running after the stop button has been pressed.
  private RGTrack track = new RGTrack(); // Initialize the track variable which holds the essential object that creates the track and all the cars.
  JTextField winningCar = new JTextField("");
  JTextField secondWinner = new JTextField("");
  
  public RGPanel(){
    setPreferredSize(new Dimension(601,501)); // Set the size of the panel.
    setBackground(Color.white); // Set the background color to white.
    setLayout(null); // Set the layout to null, this will allow the placement of components in any location, rather than grid-like layout.
    quitb.setBounds(20,0,80,60); // Set the location and size of the quit button.
    this.add(quitb); // Add the quit button to the panel.
    quitb.addActionListener(this); // Make this class capture all the events of the quit button.
    restartb.setBounds(110,0,80,60); // Set the location and size of the restart button.
    this.add(restartb); // Add the restart button to the panel.
    restartb.addActionListener(this); // Make this class capture all the events of the restart button.
    startb.setBounds(200,0,80,60); // Set the location and size of the start button.
    this.add(startb); // Add the start button to the panel.
    startb.addActionListener(this); // Make this class capture all the events of the start button.
    stopb.setBounds(290,0,80,60); // Set the location and size of the stop button.
    this.add(stopb); // Add the stop button to the panel.
    stopb.addActionListener(this); // Make this class capture all the events of the stop button.
    
    winningCar.setBounds(500,30,100,30);
    this.add(winningCar);
    secondWinner.setBounds(500,60,100,30);
    this.add(secondWinner);
  }
  
// A simple method that can draw things using an Graphics object and displays them to the window. Doesn't return anything and is called internally.
  public void paintComponent(Graphics g){
    super.paintComponent(g); // Call the parent object to draw any graphics that are entered below.
    g.drawString("Total Running Time: " + timerCurrent + " Seconds",380,20); // Draw the text that displays how many seconds the game has been running.
    Graphics2D g2 = (Graphics2D)g; // Cast the Graphics object as Graphics2D and assign it to the "g2" variable.
    track.paintComponent(g2); // Call the paintComponent method of the track object to draw the track and all of the cars.
  }
  
  // The important method that captures all of the events that the buttons call. Has a parameter that holds the ActionEvent object which has all of the information of what was pressed.
  public void actionPerformed(ActionEvent e){
    if(e.getSource() == quitb){ // If source is the quit button, then continue.
      System.exit(0); // Exit the program.
    }
    if(e.getSource() == startb){ // If the source is the start button, then continue.
      if(!timer.isRunning()){ // If the timer isn't running, then continue.
        timerStart = System.currentTimeMillis(); // Set the timerStart value to the current system time.
        timer.start(); // Start the timer.
      }
    }
    if(e.getSource() == stopb){ // If the source is the stop button, then continue.
      timerPrevious = timerCurrent; // Set the timerPrevious value to the current number of seconds.
      timer.stop(); // Stop the timer.
    }
    if(e.getSource() == restartb){ // If the source is the restart button, then continue.
      track.reset(); // Reset the track.
      timer.stop(); // Stop the timer.
      timerStart = 0; // Set the timerStart back to default, which is zero.
      timerCurrent = 0; // Set the timerCurrent back to default, which is zero.
      timerPrevious = 0; // Set the timerPrevious back to default, which is zero.
      winningCar.setText("");
      secondWinner.setText("");
      repaint(); // Run the repaint() method which redraws all the graphics in the panel, which also calls the method, paintComponent().
    }
    if(e.getSource() == timer){ // If the source is the timer than continue.
      if(track.move()){ // If a car has reached its last location, than end the game.
        timer.stop(); // Stop the timer.
        winningCar.setText("1st place: Car " + track.getWinner());
        secondWinner.setText("2nd place: Car " + track.get2ndWinner());
      }
      timerCurrent = timerPrevious + TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - timerStart); // Calculate and set the number to the variable that holds the number of seconds that game has been running.
      repaint(); // Run the repaint() method which redraws all the graphics in the panel, which also calls the method, paintComponent().
    }
  }
}
