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
    trackSize = 565; //default size
    carSize = 65; // default car size
    /*
     * puts the checkPoints evenly on the track the track
     */
    setPoints();
    for(int x = 0; x < vehicleList.length; x++){
      setUpCars(x);
    }
    
  }
  
  // Written by Vrej.
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
  
  /**
   * Written by DA.
   * Draws a broken line which represent a broken yellow line.
   * 
   * @param g 
   * @param startAtX
   * @param y
   * @param gap the empty gap length.
   */
  public void drawLane(Graphics g, int startAtX, int y, int gap){
      for(int x=startAtX+5; x<565; x+=25){
        g.drawLine(x,y,x+gap,y);   
    }
  }
  /**
   * Written by DA.
   * Draws checkpoint (vertical line).
   * @param g
   * @param x
   * @param startAtY
   * @param gap the empty gap length.
   */
  public void drawCheckpiont(Graphics g, int x, int startAtY, int gap, Color color){
      for(int y=startAtY; y<500; y+=100){
    	g.setColor(color);
        g.drawLine(x,y,x,y+gap);  
    }
      g.setColor(Color.BLACK);
  }
  
  
  /*
   * creates rectangles to simulate the track
   * creates cars within these rectangles
   */
  public void paintComponent(Graphics g){

    Graphics2D g2 = (Graphics2D) g;
    g.drawLine(0, 100, 565, 100); //DA:top line (sold line)
    g.drawLine(0, 500, 565, 500); //DA:botton line (sold line)
    
    drawLane(g, 0, 200, 15);
    drawLane(g, 0, 300, 15);
    drawLane(g, 0, 400, 15);
    
    g.drawString("Asphalt", 0, 520);
    g.drawString("Ice", 160, 520);
    g.drawString("Snow", 260, 520);
    g.drawString("Rain", 360, 520);
    g.drawString("Gravel", 460, 520);
	Color[] colors = { Color.LIGHT_GRAY, Color.gray, Color.blue, Color.DARK_GRAY };
    for(int i = 0; i < vehicleList.length; i++) {
      drawCheckpiont(g,checkPointList[i]+70, 110, 80, colors[i]);
      g.drawString("Car " + Integer.toString(i+1),vehicleList[i].getStartAtX()+20 , ((i+1)*130)-(30*i)); // This is for Nic :) <DA>

      vehicleList[i].paintComponent(g2);
    }
  }
  /*
   * moves the cars in forwards
   * if a car reaches the end of the screen it's x will become zero
   * if the car's distance reaches the the lenght of the screen then the 
   *  car will have gone a full distance and will have won
   */
  public boolean move() {
   for(int i = 0; i < 4; i++) {
	if(vehicleList[i].getX() >= checkPointList[0] && vehicleList[i].getX() <= checkPointList[1]) {
		vehicleList[i].moveCar(vehicleList[i].speed(0)); // DA: updated to the new method
	}
	else if(vehicleList[0].getX() >= checkPointList[1] && vehicleList[0].getX() <= checkPointList[2]) {
		vehicleList[i].moveCar(vehicleList[i].speed(1)); // DA: updated to the new method
	}
	else if(vehicleList[0].getX() >= checkPointList[2] && vehicleList[0].getX() <= checkPointList[3]) {
		vehicleList[i].moveCar(vehicleList[i].speed(2)); // DA: updated to the new method
	}
	else if(vehicleList[0].getX() >= checkPointList[3] && vehicleList[0].getX() <= trackSize) {
		vehicleList[i].moveCar(vehicleList[i].speed(3)); // DA: updated to the new method
	}
	else {
		vehicleList[i].moveCar(vehicleList[i].speed(4)); // DA: updated to the new method
	}
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
