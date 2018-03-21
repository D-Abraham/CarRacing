
/* Created by Nikolas Brisbois
 * Creates a track
 * this track will create a car and give the car a random starting position
 * then add it to an array
 *
 * track will also loop through the cars and move them up
 * if one of the cars reaches it's starting point it will be returned
 * so that it can be displayed as the winning car
 */
import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

public class RGTrack extends JPanel {
 private int[] checkPointList;
 private ArrayList<RGCar> vehicleList;
 private int trackSize; // DA
 private int carSize; // DA
 /*
  * constructor initialize the checkPointList and vehicleList arrays
  */

 public RGTrack() {
  checkPointList = new int[4];
  /*
   * ArrayList to avoiding looping within the class
   */
  vehicleList = new ArrayList<RGCar>();
  trackSize = 565; // default size
  carSize = 65; // default car size
  /*
   * puts the checkPoints evenly on the track the track
   */
  setPoints();

 }

 /*
  * iterates through the checkPointsList giving checkPoints to each item
  */
 private void setPoints() {
  for (int i = 0; i < 4; i++) {
   /*
    * the points should be 2/6, 3/6, 4/6, and 5/6 on the track
    */
   checkPointList[i] = 100 * (i + 1);
   // System.out.println(checkPointList[i]);
  }
 }

 /*
  * creates rectangles to simulate the track creates cars within these rectangles
  */
 public void paintComponent(Graphics g) {
  Graphics2D g2 = (Graphics2D) g;

  // DA:track's outside lines: 0,100-600,100 and 0,500-600,500.
  g.setColor(Color.DARK_GRAY); // DA track color
  g.fillRect(0, 100, 600, 400);
  drawLane(g, 0, 200, 15, Color.YELLOW); // DA: broken (dashed) yellow line.
  drawLane(g, 0, 300, 15, Color.YELLOW);
  drawLane(g, 0, 400, 15, Color.YELLOW);
  g.setColor(Color.red);
  g.drawString("Your car", 0, 110);
  g.drawString("Asphalt", 0, 520);
  g.drawString("Ice", 160, 520);
  g.drawString("Snow", 260, 520);
  g.drawString("Rain", 360, 520);
  g.drawString("Gravel", 460, 520);

  Color[] colors = { Color.LIGHT_GRAY, Color.gray, Color.blue, Color.black };
  for (int i = 0; i < vehicleList.size(); i++) {
   /*
    * each item in the loop will be given a color based on it's terrain type *note
    * for some reason brown wasn't an option so i used ice and changed it to gray
    */
   drawCheckpiont(g, checkPointList[i] + 70, 110, 80, colors[i]);
   g.setColor(Color.red);
   g.drawString("Car " + Integer.toString(i + 1), vehicleList.get(i).getStartAtX() + 20,
     ((i + 1) * 130) - (30 * i)); // This is for Nic :) <DA>
   g.setColor(Color.BLACK);
   g.drawString("Car " + (i + 1) + " " + vehicleList.get(i).toString(), 5, 550 + (i * 13));// DA: Car features
   vehicleList.get(i).paintComponent(g2);
  }
 }

 /*
  * adds the cars to the track
  */
 public void addCar(int tire, int cylinder, int engine, int drive) {
  /*
   * empties the cars if there are 4 cars in the track
   * 
   * (this is for resetting) 
   */
  if (vehicleList.size() == 4) {
   vehicleList.clear();
  }
  /*
   * constructs cars
   */
  vehicleList.add(new RGCar(checkPointList[(((int) (Math.random() * 10)) % 4)],
    ((vehicleList.size() + 1) * 130) - (30 * vehicleList.size()), tire, cylinder, engine, drive,
    Integer.toString(vehicleList.size())));
 }

 /*
  * moves the cars in forwards if a car reaches the end of the screen it's x will
  * become zero if the car's distance reaches the the lenght of the screen then
  * the car will have gone a full distance and will have won
  */
 public boolean move() {
  /*
   * NB update the moveCar method in car class (takes an int for terrain)
   */
  for (int i = 0; i < 4; i++) {
   if (vehicleList.get(i).getX() >= checkPointList[0] && vehicleList.get(i).getX() <= checkPointList[1]) {
    vehicleList.get(i).moveCar(vehicleList.get(i).speed(0)); // DA: updated to the new method
   } else if (vehicleList.get(i).getX() >= checkPointList[1]
     && vehicleList.get(i).getX() <= checkPointList[2]) {
    vehicleList.get(i).moveCar(vehicleList.get(i).speed(1)); // DA: updated to the new method
   } else if (vehicleList.get(i).getX() >= checkPointList[2]
     && vehicleList.get(i).getX() <= checkPointList[3]) {
    vehicleList.get(i).moveCar(vehicleList.get(i).speed(2)); // DA: updated to the new method
   } else if (vehicleList.get(i).getX() >= checkPointList[3] && vehicleList.get(i).getX() <= trackSize) {
    vehicleList.get(i).moveCar(vehicleList.get(i).speed(3)); // DA: updated to the new method
   } else {
    vehicleList.get(i).moveCar(vehicleList.get(i).speed(4)); // DA: updated to the new method
   }
   if (vehicleList.get(i).getX() >= trackSize - carSize) {
    vehicleList.get(i).setLocation(0, vehicleList.get(i).getY());
   }
   // System.out.println(vehicleList[i].getDistanceTraveled());
   if (vehicleList.get(i).getDistanceTraveled() >= trackSize - carSize) { // DA: once a car distance = track
                     // lenght, that car is the winner.
    return true;
   }
  }
  return false;
 }

 public int[] getPoint() {
  return checkPointList;
 }

  public ArrayList<RGCar> getVehicleList() {
  return vehicleList;
  }

 /**
  * Written by DA. Draws a broken (dashed) line which represent a broken yellow
  * line between two lanes.
  *
  * @param g
  * @param startAtX
  * @param y
  * @param gap
  *            the empty gap length.
  * @param color
  *            line color.
  */
 public void drawLane(Graphics g, int startAtX, int y, int gap, Color color) {
  g.setColor(color);
  for (int x = startAtX + 5; x < 600; x += 25) {
   g.drawLine(x, y, x + gap, y);
  }
 }

 /**
  * Written by DA. Draws checkpoint (vertical line).
  * 
  * @param g
  * @param x
  * @param startAtY
  * @param gap
  *            the empty gap length.
  * @param color
  *            line color.
  */
 public void drawCheckpiont(Graphics g, int x, int startAtY, int gap, Color color) {
  g.setColor(color);
  for (int y = startAtY; y < 500; y += 100) {
   g.drawLine(x, y, x, y + gap);
  }
 }

 /**
  * Written by DA Returns the winner, or the car with more distance. Note: it
  * will return the object index + 1.
  * 
  * @return who's the winner.
  */
 public int getWinner() {
  for (int index = 0; index < vehicleList.size(); index++) {
   if (vehicleList.get(index).getDistanceTraveled() >= trackSize - carSize) {
    return index + 1;
   }
  }
  return -1;
 }

 /**
  * Written by DA Returns who came in the second place. Note: it will return the
  * object index + 1.
  * 
  * @return who came in the 2nd place.
  */
 public int get2ndWinner() {
  int temp;
  int second = 0;
  for (int index = 0; index < vehicleList.size(); index++) {

   if (vehicleList.get(index).getDistanceTraveled() < trackSize - carSize) {
    temp = index;
    for (int index2 = index + 1; index2 < vehicleList.size(); index2++) {
     if (vehicleList.get(index2).getDistanceTraveled() < trackSize - carSize && vehicleList.get(index2)
       .getDistanceTraveled() >= vehicleList.get(temp).getDistanceTraveled()) {
      temp = index2;
      second = temp;
     }

    }
    return second + 1;
   }
   
  }
  return -1;
 }
 
 // Written by Vrej.
 // Simply returns the value of the track size.
 public int getTrackSize(){
   return trackSize;
 }
 
 // Written by Vrej.
 // Simply returns the value of the car size.
 public int getCarSize(){
   return carSize;
 }
 
 // Written by Vrej.
 // Returns true if the both of the objects are a RGTrack object with the same values.
 public boolean equals(Object obj){
   if(obj == this) return true; // Make sure it's not this object.
   if(obj == null) return false; // Make sure the given object isn't null.
   if(obj.getClass() == this.getClass()){ // If both are the same class.
     RGTrack other = (RGTrack) obj; // Cast the given object to the same class.
     return(other.getTrackSize() == this.getTrackSize() && other.getCarSize() == this.getCarSize()); // Return true if all these statments are correct.
   }
   return false; // Return false if the previous statements didn't work.
 }
}
