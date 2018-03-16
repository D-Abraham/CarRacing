import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;



/**
   * A car image that can be positioned anywhere on the screen.
   * @author DA
   * @since 3/5/2018
*/
public class RGCar extends JPanel
{

    
    private int x, y,startAtX, distanceTraveled;
    private BufferedImage img;
    private int tires; // 0 = all-season & 1 high performance
    private int engineCylinder; // 1 for each cylinder 
    private int engineType; // 0 = non-turbo & 1 = turbo
    private int driveType;  // 0 = FWD & 1 = AWD
   
    /**
      * Constructs a car at the default location (0,0).
   */
   public RGCar()
   {
       x = 0;
       y = 0;
       startAtX=0;
       tires=0;
       engineCylinder = 4;
       engineType = 0;
       driveType = 0;
       distanceTraveled =0;
      try {
             
             img = ImageIO.read(new File("1.png")); // car1.png must be in 
                                                       //project folder.
         } catch (IOException e) {
         }
   }
   
    
    /**
     * Constructs a car. 
     * @param x the x coordinate.
     * @param y the y coordinate.
     * @param  tires tires type.
     * @param engineCylinder number of cylinder.
     * @param engineType non-turbo or turbo.
     * @param driveType AWD or FD.
     * @param pic to be used as a picture for the object.
   */
   public RGCar(int x, int y, int tires, int engineCylinder, int engineType,
   int driveType, String pic)
   {    

      this.x = x;
      this.y = y;
      startAtX = x;
      this.tires= tires;
      this.engineCylinder = engineCylinder;
      this.engineType = engineType;
      this.driveType = driveType;
      distanceTraveled =0;
      try {
             
             img = ImageIO.read(new File(pic+".png"));
         } catch (IOException e) {
         }
   }

   /**
      Draws a car
      @param g2 the graphics context
   */
   public void paintComponent(Graphics g)
   {
       g.drawImage(img, x, y, 70, 35, null);  // 70,35 is the car image scale
    
   }
   
   /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }
    
    /**
     * @return the startAtX
     */
    public int getStartAtX() {
        return startAtX;
    }
    
    /**
     * @return the distanceTraveled
     */
    public int getDistanceTraveled() {
        return distanceTraveled;
    }
    
    /**
     * 
     * @param velX 
     */
    public void moveCar(int velX) {
        distanceTraveled += velX; 
        x += velX;

    }
   
    /**
     * To manually change x,y without changing the distance.
     * Note: will not effect distance. 
     * @param newX new x coordinate
     * @param newY new y coordinate
     */
   public void setLocation(int newX, int newY){
       x = newX;
       y = newY;
   }
   
   /**
    * calculates and returns a random number (used as speed) according to car features.
    * @return random number according to car features  
    */
   public int speed(){
       return (int)(Math.random()*(engineType+(engineCylinder/2)+tires))+1;
   }
   
   @Override
   public String toString(){
       return "Car coordinate: " + x+", "+y+", distance traveled "+distanceTraveled;
   }
   
 @Override 
    public boolean equals(Object obj){
        if(obj == this) return true;
        if(obj == null) return false;
        if(this.getClass() == obj.getClass()){
             RGCar car = (RGCar) obj;
            return (this.getX() == car.getX()) && (this.getY() == car.getY()) &&
                    (this.getDistanceTraveled() == car.getDistanceTraveled());
    }
        else{ 
            return false;
}   
    }  
}
