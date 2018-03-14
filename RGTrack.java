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
  private int trackSize; //DA
  private int carSize; //DA
  /*
   * constructor initialize the checkPointList and vehicleList arrays
   */
  public RGTrack(){
    checkPointList = new int[4];
    vehicleList = new RGCar[4];
    trackSize = 600; //default size
    carSize = 65; // default car size
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
      //System.out.println(checkPointList[i]);
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
      g.drawString("Car " + Integer.toString(i+1), 0, ((i+1)*130)-(30*i));
      g2.draw(track);
      vehicleList[i].paintComponent(g2);
    }
  }
  /*
   * moves the cars in forwards
   * if a car reaches the end of the screen it's x will become zero
   * if the car's distance reaches the the lenght of the screen then the 
   * 	car will have gone a full distance and will have won
   */
  public boolean move() {
	  for(int i = 0; i < 4; i++) {
		  vehicleList[i].moveCar(vehicleList[i].speed()); // DA: updated to the new method
		  if(vehicleList[i].getX() >= trackSize-carSize) {
			  vehicleList[i].setLocation(0, vehicleList[i].getY());
		  }
		  //System.out.println(vehicleList[i].getDistanceTraveled());
		  if(vehicleList[i].getDistanceTraveled() >= trackSize-carSize) { //DA: once a car distance = track lenght, that car is the winner. 
			  return true;
		  }
	  }
	  return false;
  }
  
  public int[] getPoint() {
    return checkPointList;
  }
  
  public RGCar[] getVehicleList() {
    return vehicleList;
  }
  
  /**
   * Written by DA
   * Returns the winner, or the car with more distance.
   * Note: it will return the object index + 1. 
   * @return who's the winner. 
   */
  public int getWinner(){
      for(int index = 0; index< vehicleList.length; index++){
          if(vehicleList[index].getDistanceTraveled() >=trackSize-carSize){
              return index+1;
          }
      }
      return-1;
  }
  
  /**
   * Written by DA
   * Returns who came in the second place. 
   * Note: it will return the object index + 1. 
   * @return who came in the 2nd place. 
   */
  public int get2ndWinner(){
      int temp;
      int second=0;
      for(int index = 0; index< vehicleList.length; index++){
          
          if(vehicleList[index].getDistanceTraveled() < trackSize-carSize ){
              temp = index;
              for(int index2 = index+1; index2<vehicleList.length; index2++){
                  if(vehicleList[index2].getDistanceTraveled() < trackSize-carSize &&
                          vehicleList[index2].getDistanceTraveled() >=
                          vehicleList[temp].getDistanceTraveled() ){
                      temp = index2;
                      second=temp;
                  }
                  
              }
              return second+1;
          }
          
      }
      return -1;
  }
  
  /**
   * Written by DA
   * used to reset all cars locations.
   */
  public void reset(){
      for(int x = 0; x < vehicleList.length; x++){
          setUpCars(x);
          }
      }
  
}
