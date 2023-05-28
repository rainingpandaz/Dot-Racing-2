import java.awt.*;
import java.util.Random;
import javax.swing.*;

public class HareDot extends Dot {

  private int quitDistance;

  public HareDot(String iname, int iracerNumber, Color icolor, int idiameter) {
    super(iname, iracerNumber, icolor, idiameter);
    quitDistance = 180;
    }

  public void moved(int n) { 

  	     if(getDistance() <= quitDistance) {
   		   setDistance(getDistance() + 10);
  } 
}

}
