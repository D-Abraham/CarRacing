import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
   * A car shape that can be positioned anywhere on the screen.
   * @author DA
*/
public class RGCar implements Drawable
{
    
    private int x;
    private int y;
    
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
      tires=0;
      engineCylinder = 4;
      engineType = 0;
      driveType = 0;
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
   int driveType){
      this.x = x;
      this.y = y;
      this.tires= tires;
      this.engineCylinder = engineCylinder;
      this.engineType = engineType;
      this.driveType = driveType;
   }

   /**
      Draws a car
      @param g2 the graphics context
   */
   public void draw(Graphics2D g2)
   {
      Rectangle2D.Double body 
         = new Rectangle2D.Double(x, y + 10, 60, 10);      
      Ellipse2D.Double frontTire 
         = new Ellipse2D.Double(x + 10, y + 20, 10, 10);
      Ellipse2D.Double rearTire
         = new Ellipse2D.Double(x + 40, y + 20, 10, 10);

      // the bottom of the front windshield
      Point2D.Double r1 
         = new Point2D.Double(x + 10, y + 10);
      // the front of the roof
      Point2D.Double r2 
         = new Point2D.Double(x + 20, y);
      // the rear of the roof
      Point2D.Double r3 
         = new Point2D.Double(x + 40, y);
      // the bottom of the rear windshield
      Point2D.Double r4 
         = new Point2D.Double(x + 50, y + 10);

      Line2D.Double frontWindshield 
         = new Line2D.Double(r1, r2);
      Line2D.Double roofTop 
         = new Line2D.Double(r2, r3);
      Line2D.Double rearWindshield
         = new Line2D.Double(r3, r4);
   
      g2.draw(body);
      g2.draw(frontTire);
      g2.draw(rearTire);
      g2.draw(frontWindshield);      
      g2.draw(roofTop);      
      g2.draw(rearWindshield);      
   }

   
}