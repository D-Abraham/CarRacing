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
import java.util.ArrayList;

public class RGTrack implements Drawable {
	
	/*
	 * constructor initialize the checkPointList and vehicleList arrays
	 */
	public RGTrack() {
		checkPointList = new int[4];
		vehicleList = new ArrayList<RGCar>();
		/*
		 * puts the checkPoints evenly on the track the track
		 */
		setPoints();
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
		for(int i = 0; i < 4; i++) {
			Rectangle2D.Double track = new Rectangle2D.Double(0, i*175, 600, 175);
			g2.draw(track);
			vehicleList.get(i).draw(g2); 
		}
	}
	/*
	 * creates a car
	 */
	public void createCar(int engCyl, int engType, int wheels) { //the panel can put these items in
																 // the method to give it a speed
		
		/*
		 * if the car's constructor gives it a x y position for the car this will
		 * set the car (based on the current size of the track) and randomly use
		 * the values in checkPointList to set it's starting point. 
		 * 
		 * I will also like the car to store the winning point so that i can check if the car won
		 * 
		 * if the constructor does not set the car's x y position, this will not work
		 */
		vehicleList.add(new RGCar((vehicleList.size()*58.3), checkPointList[(int) Math.random()%4]));
	}
	/*
	 * moveUp needs to get the Speed of the car, i would also need 
	 * to know more about the movement of the cars, this method might 
	 * not be needed
	 */
//	public RGCar moveUp() {	
//		for(int i = 0; i < 4; i++) {
//			vehicleList.get(i).setX(vehicleList.get(i).getSpeed());
//			if(vehicleList.get(i).getX >= 700) {
//				vehicleList.get(i).setX = 0;
//			}
//			if(vehicleList.get(i).getX >= vehicleList.get(i).findOrgine()) {
//				return vehicleList.get(i);
//			}
//		}
//		return null;
//	}
	
	public int[] getPoint() {
		return checkPointList;
	}
	
	public ArrayList<RGCar> getVehicleList() {
		return vehicleList;
	}
	
	private int[] checkPointList;
	private ArrayList<RGCar> vehicleList;
	
}
