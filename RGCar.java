
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;



/**
   * A car shape that can be positioned anywhere on the screen.
   * @author DA
*/
public class RGCar extends JPanel
{

    
    private int orgineX, orgineY;
    private int x, y;
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
       orgineX = 0;
       orgineY = 0;
       x = 0;
       y = 0;
       tires=0;
       engineCylinder = 4;
       engineType = 0;
       driveType = 0;
      try {
             
             img = ImageIO.read(new File("car1.png")); // car1.png must be in 
                                                       //project folder.
         } catch (IOException e) {
         }
   }
   
   public RGCar(String pic)
   {
       orgineX = 0;
       orgineY = 0;
      x = 0;
      y = 0;
      tires=0;
      engineCylinder = 4;
      engineType = 0;
      driveType = 0;
      try {
             
             img = ImageIO.read(new File(pic));
         } catch (IOException e) {
         }
   }
    
    /**
     * Constructs a car. 
     * @param x the x coordinate.
     * @param y the y coordinate.
     * @param  engineCylinder
     * @param engineCylinder
     * @param engineType
     * @param driveType
   */
   public RGCar(int x, int y, int tires, int engineCylinder, int engineType,
   int driveType, String pic){
       orgineX = x;
       orgineY = y;
      this.x = x;
      this.y = y;
      this.tires= tires;
      this.engineCylinder = engineCylinder;
      this.engineType = engineType;
      this.driveType = driveType;
      try {
             
             img = ImageIO.read(new File(pic));
         } catch (IOException e) {
         }
   }

   /**
      Draws a car
      @param g2 the graphics context
   */
   public void paintComponent(Graphics g)
   {
       
       
       //g.drawImage(img, x, y, null);  // no image scaleing
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
   
   public void setLocation(int newX, int newY){
       x = newX;
       y = newY;
   }
   
   public int speed(){
       return (int)(Math.random()*(engineType+(engineCylinder/2)+tires))+1;
   }
   
   /**
     * @return the orgineX
     */
    public int getOrgineX() {
        return orgineX;
    }

    /**
     * @return the orgineY
     */
    public int getOrgineY() {
        return orgineY;
    }

   
}