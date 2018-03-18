// Vrej | March 18, 2018 | RGDriver class, this class just simply creates a JFrame and runs the game by creating a RGPanel.
import java.awt.*;
import javax.swing.*;

public class RGDriver{
  public static void main(String[] args){
    JFrame frame = new JFrame("Car Racing Game"); // Create the JFrame, which is how Java will be able to display something to the window.
    Container c = frame.getContentPane(); // Simply returns the Container object that's in the JFrame, which will be used to add RGPanel's contents.
    RGPanel p = new RGPanel(); // Create the RGPanel which will be the main GUI in this program.
    c.add(p); // Add the RGPanel to the frame's container, so it can be displayed.
    frame.pack(); // Makes it ready for display.
    frame.setVisible(true); // Make the frame visible, as in display the frame and all of its contents to the window.
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Sets the default behavior to close the frame when the program closes.
    frame.setResizable(false); // Don't allow users to resize this window.
  }
}