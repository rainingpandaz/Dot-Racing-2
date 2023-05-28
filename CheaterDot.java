import java.awt.*;
import java.util.Random;
import javax.swing.*;

public class CheaterDot extends Dot {

  private int howMuch;

  public CheaterDot(String iname, int inumber, Color icolor, int idiameter, int hm) {
    super(iname, inumber, icolor, idiameter);
    howMuch = hm;
  }

  public void moved(int n) {
    setDistance(getDistance() + n + howMuch);
  }

}
