// Written by Vrej | March 21, 2018 | RGPanel class, this class extends JPanel and implements ActionListener. It's a very essential class, it create most of the GUI (Including the customization) and makes the RGTrack, this is then used to start running the game itself.
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.concurrent.TimeUnit;

public class RGPanel extends JPanel implements ActionListener{
  JButton quitb = new JButton("Quit"); // Initialize the quitb variable which simply just holds a JButton that quits the game when pressed.
  JButton restartb = new JButton("Restart"); // Initialize the restartb variable which holds a JButton that resets and restarts the game when pressed.
  JButton startb = new JButton("Start"); // Initialize the startb variable holds a JButton that starts/continues the timer and the cars.
  JButton stopb = new JButton("Stop"); // Initialize the stopb variable which holds a JButton that stops the game, it will pause the timer and the cars.
  Timer timer = new Timer(100, this); // Initialize the timer that's used to determine how long the game took to finish, it runs every 100 milliseconds.
  private long timerStart = 0; // Initialize the timerStart variable which holds the initial time that the game began running, it's used to calculate the total number of seconds the game took to finish.
  private long timerCurrent = 0; // Initialize the timerCurrent variable which holds the current number of seconds the game has been running.
  private long timerPrevious = 0; // Initialize the timerPrevious variable which holds the total number of seconds the game has been running after the stop button has been pressed.
  private RGTrack track = new RGTrack(); // Initialize the track variable which holds the essential object that creates the track and all the cars.
  JTextField firstWinner = new JTextField(""); // Initialize the firstWinner variable, which is a JTextField used to display the first winner.
  JTextField secondWinner = new JTextField(""); // Initialize the secondWinner variable, which is a JTextField used to display the second winner.
  private JButton constructCar = new JButton("Construct Car"); // Initialize the button that constructs the car when pressed.
  private String[] tires = {"Tire: All-Season", "Tire: High performance", "Tire: Snow Tires"}; // Initialize the array that holds the tire types.
  private String[] cylinder = {"Engine Cylinder: 4", "Engine Cylinder: 6", "Engine Cylinder: 8"}; // Initialize the array that holds the cylinder types.
  private String[] engType = {"Engine Type: Non-turbo", "Engine Type: Turbo"}; // Initialize the array that holds the engine types.
  private String[] driveType = {"Drive Type: Front wheel drive", "Drive Type: All wheel drive"}; // Initialize the array that holds the drive types.
  JComboBox<String> tiresMenu = new JComboBox<String>(tires); // Initialize the drop down menu for the tires.
  JComboBox<String> cylinderMenu = new JComboBox<String>(cylinder); // Initialize the drop down menu for the number of cylinders.
  JComboBox<String> engineMenu = new JComboBox<String>(engType); // Initialize the drop down menu for the engine type.
  JComboBox<String> driveMenu = new JComboBox<String>(driveType); // Initialize the drop down menu for the drive type.
  
  // The main constructor for the class which takes no parameters and initializes very important variables.
  public RGPanel(){
    setPreferredSize(new Dimension(565, 601)); // Set the size of the panel.
    setBackground(Color.white); // Set the background color to white.
    setLayout(null); // Set the layout to null, this will allow the placement of components in any location, rather than grid-like layout.
    
    // Create the initial customization items:
    tiresMenu.setBounds(0, 0, 210, 60); // Set the location and size of the tires drop down menu.
    this.add(tiresMenu); // Add the tires drop down menu to the panel.
    tiresMenu.addActionListener(this); // Make this class capture all the events of the tires drop down menu.
    cylinderMenu.setBounds(300, 60, 200, 60); // Set the location and size of the number of cylinders drop down menu.
    this.add(cylinderMenu); // Add the number of cylinders drop down menu to the panel.
    cylinderMenu.addActionListener(this); // Make this class capture all the events of the number of cylinder drop down menu.
    engineMenu.setBounds(100, 60, 200, 60); // Set the location and size of the engine type drop down menu.
    this.add(engineMenu); // Add the engine type drop down menu to the panel.
    engineMenu.addActionListener(this); // Make this class capture all the events of the engine type drop down menu.
    driveMenu.setBounds(370, 0, 205, 60); // Set the location and size of the drive type drop down menu.
    this.add(driveMenu); // Add the drive type drop down menu to the panel.
    driveMenu.addActionListener(this); // Make this class capture all the events of the drive type drop down menu.
    constructCar.setBounds(210, 0, 160, 60); // Set the location and size of the construct car button.
    this.add(constructCar); // Add the construct car button to the panel.
    constructCar.addActionListener(this); // Make this class capture all the events of the construct car button.
  }
  
  // Creates all the cars, including the one customized by the user. Takes no parameters and returns nothing.
  private void createCars(){
    // Written by NB:
    track.addCar(tiresMenu.getSelectedIndex(), (cylinderMenu.getSelectedIndex() * 2) + 4, engineMenu.getSelectedIndex(), driveMenu.getSelectedIndex());
    for (int i = 0; i < 3; i++) {
      track.addCar((((int)(Math.random() * 10)) % 3), (((int)(Math.random() * 10) % 3) * 2) + 4, (((int)(Math.random() * 10)) % 2), (((int)(Math.random() * 10)) % 2));
    }
  }
  
  // A simple method that can draw things using an Graphics object and displays them to the window. Doesn't return anything and is called internally.
  public void paintComponent(Graphics g){
    super.paintComponent(g); // Call the parent object to draw any graphics that are entered below.
    g.drawString("Total Running Time: " + timerCurrent + " Seconds", 380, 20); // Draw the text that displays how many seconds the game has been running.
    Graphics2D g2 = (Graphics2D) g; // Cast the Graphics object as Graphics2D and assign it to the "g2" variable.
    track.paintComponent(g2); // Call the paintComponent method of the track object to draw the track and all of the cars.
  }
  
  // The important method that captures all of the events that the buttons call. Has a parameter that holds the ActionEvent object which has all of the information of what was pressed.
  public void actionPerformed(ActionEvent e){
    if (e.getSource() == quitb){ // If source is the quit button, then continue.
      System.exit(0); // Exit the program.
    }
    
    if (e.getSource() == startb){ // If the source is the start button, then continue.
      if (!timer.isRunning()) { // If the timer isn't running, then continue.
        timerStart = System.currentTimeMillis(); // Set the timerStart value to the current system time.
        timer.start(); // Start the timer.
      }
    }
    
    if (e.getSource() == stopb){ // If the source is the stop button, then continue.
      timerPrevious = timerCurrent; // Set the timerPrevious value to the current number of seconds.
      timer.stop(); // Stop the timer.
    }
    
    if (e.getSource() == restartb){ // If the source is the restart button, then continue.
      createCars(); // Recreate all of the cars.
      timer.stop(); // Stop the timer.
      timerStart = 0; // Set the timerStart back to default, which is zero.
      timerCurrent = 0; // Set the timerCurrent back to default, which is zero.
      timerPrevious = 0; // Set the timerPrevious back to default, which is zero.
      firstWinner.setText(""); // Set the text for the firstWinner to an empty String.
      secondWinner.setText(""); // Set the text for the secondWinner to an empty String.
      repaint(); // Run the repaint() method which redraws all the graphics in the panel, which also calls the method, paintComponent().
    }
    
    if (e.getSource() == timer){ // If the source is the timer than continue.
      if (track.move()) { // If a car has reached its last location, than end the game.
        timer.stop(); // Stop the timer.
        firstWinner.setText("1st place: Car " + track.getWinner()); // Set the winning text for the firstWinner.
        secondWinner.setText("2nd place: Car " + track.get2ndWinner()); // Set the winning text for the secondWinner.
      }
      timerCurrent = timerPrevious + TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - timerStart); // Calculate and set the number to the variable that holds the number of seconds that game has been running.
      repaint(); // Run the repaint() method which redraws all the graphics in the panel, which also calls the method, paintComponent().
    }
    
    if (e.getSource() == constructCar){ // If the source is the construct button than continue.
      createCars(); // Create all of the cars.
      
      // Create the main items and add it the panel:
      quitb.setBounds(20, 0, 80, 60); // Set the location and size of the quit button.
      this.add(quitb); // Add the quit button to the panel.
      quitb.addActionListener(this); // Make this class capture all the events of the quit button.
      restartb.setBounds(110, 0, 80, 60); // Set the location and size of the restart button.
      this.add(restartb); // Add the restart button to the panel.
      restartb.addActionListener(this); // Make this class capture all the events of the restart button.
      startb.setBounds(200, 0, 80, 60); // Set the location and size of the start button.
      this.add(startb); // Add the start button to the panel.
      startb.addActionListener(this); // Make this class capture all the events of the start button.
      stopb.setBounds(290, 0, 80, 60); // Set the location and size of the stop button.
      this.add(stopb); // Add the stop button to the panel.
      stopb.addActionListener(this); // Make this class capture all the events of the stop button.
      firstWinner.setBounds(380, 30, 160, 30); // Set the location and size of the firstWinner text.
      firstWinner.setBorder(javax.swing.BorderFactory.createEmptyBorder()); // Make the text field not to have any borders.
      firstWinner.setEditable(false); // Make the text field not be editable.
      this.add(firstWinner); // Add the firstWinner text to the panel.
      secondWinner.setBounds(380, 60, 160, 30); // Set the location and size of the secondWinner text.
      secondWinner.setBorder(javax.swing.BorderFactory.createEmptyBorder()); // Make the text field not to have any borders.
      secondWinner.setEditable(false); // Make the text field not be editable.
      this.add(secondWinner); // Add the secondWinner text to the panel.
      
      // Remove all of the customizations items:
      this.remove(tiresMenu); // Remove the drop down menu for the tires.
      this.remove(cylinderMenu); // Remove the drop down menu for the cylinder types.
      this.remove(engineMenu); // Remove the drop down menu for the engine types.
      this.remove(driveMenu);  // Remove the drop down menu for the drive type.
      this.remove(constructCar); // Remove the button that constructs the car.
      repaint(); // Run the repaint() method which redraws all the graphics in the panel, which also calls the method, paintComponent().
    }
  }
}