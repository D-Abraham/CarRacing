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
    private int tires; // 0 = all-season, 1 high performance & 2 snowtires
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
    * @param terrain 
    * @return random number according to car features and terrain.
    */
   public int speed(int terrain){
       
       switch(terrain){
           case 0: //asphalt
               return (int)(Math.random()*(engineType+(engineCylinder/2)+tires))+1;
               
           case 1: // Ice
               if(tires == 2){ //snowtires: adds (2) + (1) as a bonus
                   return (int)(Math.random()*(engineType+(engineCylinder/2)+
                           (tires+1)+driveType))+1;
               }
               else{ // adds -(tires)-1 as penalty 
                   return (int)(Math.random()*(engineType+(engineCylinder/2)+
                           driveType))+1;
               }
               
           case 2: //snow
               if(tires == 2){ // snowtires: adds (2) + (1) as a bonus
                   return (int)(Math.random()*(engineType+(engineCylinder/2)+
                           (tires+1)+driveType))+1;
               }
               else if(tires == 0){ //adds (1) as a bonus for all-season. 
                   return (int)(Math.random()*(engineType+(engineCylinder/2)+
                           driveType+1))+1;
               }
               else{ // Adds -1 as penalty
                   return (int)(Math.random()*(engineType+(engineCylinder/2)-1))+1;
               }
               
           case 3: //Rain
               if (tires == 1){ // high performance: adds (1 for tires) + (1) as a bonus
                   return (int)(Math.random()*(engineType+(engineCylinder/2)+
                           (tires+1)+driveType))+1;
               }
               else{
                   return (int)(Math.random()*(engineType+(engineCylinder/2)+
                           tires+driveType))+1;
               }
               
           case 4: // Gravel
               return (int)(Math.random()*(engineType+(engineCylinder/2)+tires+
                       driveType))+1;           
       }
      
    return-1;
   }
   
   @Override
   public String toString(){ // 0 is false and 1 is ture
       String tires="snowtires";
       String driveT="FD";
       String turbo="non-turbo";
       if(this.tires == 0){tires = "all-season";}
       if(this.tires == 1){tires = "high performance";}
       if(this.engineType == 1){turbo = "turbo";}
       if(this.driveType == 1){driveT = "AWD";}
       return "Features: "+engineCylinder+" Cyl, "+ turbo+", "+ tires+ " tires"+
               " and "+driveT+" drive.";
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
