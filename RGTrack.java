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
import javax.swing.*;
import java.awt.geom.*;

public class RGTrack extends JPanel{
  private int[] checkPointList;
  private RGCar[] vehicleList;
  /*
   * constructor initialize the checkPointList and vehicleList arrays
   */
  public RGTrack(){
    checkPointList = new int[4];
    vehicleList = new RGCar[4];
    /*
     * puts the checkPoints evenly on the track the track
     */
    setPoints();
    for(int x = 0; x < vehicleList.length; x++){
      setUpCars(x);
    }
    
  }
  
  public void setUpCars(int index){
    int tires;
    int test = (int)(Math.random()*2);
    switch(test){
      case 0: tires = 0;
      break;
      case 1: tires = 1;
      break;
      default: tires = 0;
      break;
    }
    int cyl;
    switch((int)(Math.random()*3)){
      case 0: cyl = 4;
      break;
      case 1: cyl = 6;
      break;
      case 2: cyl = 8;
      break;
      default: cyl = 4;
      break;
    }
    int engtype;
    switch((int)(Math.random()*2)){
      case 0: engtype = 0;
      break;
      case 1: engtype = 1;
      break;
      default: engtype = 0;
      break;
    }
    int drivetype;
    switch((int)(Math.random()*2)){
      case 0: drivetype = 0;
      break;
      case 1: drivetype = 1;
      break;
      default: drivetype = 0;
      break;
    }
    /*
     * sets it to an x and y position 
     * 
     */
    vehicleList[index] = new RGCar(checkPointList[(((int)(Math.random()*10))%4)],((index+1)*130)-(30*index),
    		tires,cyl,engtype,drivetype, Integer.toString(index)); //The cars files must be named 0-4 
  }
  /*
   * iterates through the checkPointsList 
   * giving checkPoints to each item
   */
  private void setPoints() {
    for(int i = 0; i < 4; i++) {
      /*
       * the points should be 2/6, 3/6, 4/6, and 5/6 on the track
       */
      checkPointList[i] = 100*(i+1);
      System.out.println(checkPointList[i]);
    }
  }
  /*
   * creates rectangles to simulate the track
   * creates cars within these rectangles
   */
  public void paintComponent(Graphics g){
    Graphics2D g2 = (Graphics2D) g;
    for(int i = 0; i < vehicleList.length; i++) {
      Rectangle2D.Double track = new Rectangle2D.Double(0, (i+1)*100, 600, 100);
      g.drawLine(checkPointList[i]+70, 100, checkPointList[i]+70, 501);
      g2.draw(track);
      vehicleList[i].paintComponent(g2);
    }
  }
  /*
   * moves it up
   * if it passes 0 it will go back to zero
   * 
   * error, the speed is to large, needs to be smaller
   * 
   * also car should keep track of it's orgine x point
   */
  public RGCar move() {
	  for(int i = 0; i < 4; i++) {
		  vehicleList[i].moveCar(vehicleList[i].speed()); // DA: updated to the new method
		  if(vehicleList[i].getX() >= 535) {
			  vehicleList[i].setLocation(0, vehicleList[i].getY());
		  }
		  System.out.println(vehicleList[i].getDistanceTraveled());
		  if(vehicleList[i].getDistanceTraveled() >= 635) { //DA: once a car distance = track lenght, that car is the winner. 
			  return vehicleList[i];
		  }
	  }
	  return null;
  }
  
  public int[] getPoint() {
    return checkPointList;
  }
  
  public RGCar[] getVehicleList() {
    return vehicleList;
  }
  
}
