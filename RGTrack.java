/* Created by Nikolas Brisbois
 * Creates a track
 * this track will create a car and give the car a random starting position
 * then add it to an array
 * 
 * track will also loop through the cars and move them up
 * if one of the cars reaches it's starting point it will be returned 
 * so that it can be displayed as the winning car
 */

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

public class RGTrack implements Drawable {
  private int[] checkPointList;
  private RGCar[] vehicleList;
  /*
   * constructor initialize the checkPointList and vehicleList arrays
   */
  public RGTrack(){
    checkPointList = new int[4];
    vehicleList = new RGCar[4];
    for(int x = 0; x <= vehicleList.length; x++){
      setUpCars(x);
      System.out.println("Okk");
    }
    /*
     * puts the checkPoints evenly on the track the track
     */
    setPoints();
    
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
    vehicleList[index] = new RGCar(1,1,tires,cyl,engtype,drivetype);
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
      checkPointList[i] = 100*(i+2);
    }
  }
  /*
   * creates rectangles to simulate the track
   * creates cars within these rectangles
   */
  public void draw(Graphics2D g2) {
    for(int i = 0; i < vehicleList.length; i++) {
      Rectangle2D.Double track = new Rectangle2D.Double(0, i*175, 600, 175);
      g2.draw(track);
      //vehicleList[i].draw(g2); 
    }
  }
  
  public int[] getPoint() {
    return checkPointList;
  }
  
  public RGCar[] getVehicleList() {
    return vehicleList;
  }
  
}
