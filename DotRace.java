import java.awt.*;
import java.util.Random;
import javax.swing.*;

public class DotRace extends JPanel {

  private JFrame myFrame;
  private Random randGen;
  private int whoseTurn = 0;

  // the length in pixels of the track
  int trackLength = 300;
  int startLine = 50;
  // number of milliseconds (one thousanth of a second) to pause between each cycle
  // slower computers might lower this number...
  int pauseTime = 50;
  Dot winnaWinnaWinna; // who has won (or null if nobody)
  int winnaEffectDiameter; // for the special effects at end
  int defaultDiameter = 70; // default diameter for the dot

  Dot racer0 = new Dot("Fred", 0, Color.red, defaultDiameter);
  Dot racer1 = new CheaterDot("Maria", 1, Color.green, defaultDiameter, 2);
  Dot racer2 = new Dot("Darren", 2, Color.orange, defaultDiameter);
  Dot racer3 = new HareDot("Hoppy", 3, Color.cyan, defaultDiameter);
  int racerCount = 4;

  int topBorder = 10; // allow for the menu bar at the top of the frame
  int border = 10; // the border around the dots
  int sideBorder = startLine; // space after the finishLine
  int windowWidth = trackLength + startLine + 50;
  int windowHeight = topBorder + (racerCount * (defaultDiameter + border));

  // constructor
  public DotRace() {
    myFrame = new JFrame("Dot Racing!");
    myFrame.add(this);
    myFrame.setSize(windowWidth, windowHeight);
    myFrame.setVisible(true);
    randGen = new Random();

    runRace();
  }

  public void runRace() {
    Dot mover;
    int distance;

    winnaWinnaWinna = null;
    while (winnaWinnaWinna == null) {
      mover = whoMoves();
      distance = howFar();
      mover.moved(distance);
      if (racer0.getDistance() > racer1.getDistance()
          && racer0.getDistance() > racer2.getDistance()) {
        racer0.setColor(Color.blue);
        racer1.setColor(Color.green);
        racer2.setColor(Color.orange);
        racer3.setColor(Color.cyan);
      } else if (racer1.getDistance() > racer0.getDistance()
          && racer1.getDistance() > racer2.getDistance()) {
        racer0.setColor(Color.red);
        racer1.setColor(Color.blue);
        racer2.setColor(Color.orange);
        racer3.setColor(Color.cyan);
      } else {
        racer0.setColor(Color.red);
        racer1.setColor(Color.green);
        racer2.setColor(Color.blue);
        racer3.setColor(Color.cyan);
      }
      if (mover.getDistance() + mover.getDiameter() >= trackLength + border) {
        winnaWinnaWinna = mover;
      }
      repaint();
      delay(pauseTime);
    }
  }

  // This method returns the 'Dot' (either racer0, racer1, or racer2)
  // whose turn it is to move.
  // finish this
  private Dot whoMoves() {
    if (whoseTurn == 0) {
      whoseTurn = 1;
      return racer0;
    } else if (whoseTurn == 1) {
      whoseTurn = 2;
      return racer1;
    } else if (whoseTurn == 2){
      whoseTurn = 3;
      return racer2;
    }
    else{
      whoseTurn = 0;
      return racer3;
    }
  }

  //  This method returns the number of pixels that the dot moves
  // finish this
  private int howFar() {
    return randGen.nextInt(3) + 1;
  }

  // Draw the dots in raceTrack.
  // We keep track of who just moved to give an animation effect.
  // Also when there's a winner, we circle the dot that won.
  public void paintComponent(Graphics g) {
    super.repaint(); // don't worry about this right now -- it 'clears the screen', though.

    g.setColor(Color.white);
    g.fillRect(0, 0, windowWidth, windowHeight); // draw a nice white background
    g.setColor(Color.black);
    g.drawLine(startLine, 0, startLine, windowHeight); // draw a starting line
    g.drawLine(startLine + trackLength, 0, startLine + trackLength, windowHeight); // finish

    drawRacer(racer0, g);
    drawRacer(racer1, g);
    drawRacer(racer2, g);
    drawRacer(racer3, g);
  }

  public void drawRacer(Dot racer, Graphics g) {
    int topX = racer.getDistance() + border + startLine;
    int topY = topBorder + (racer.getRacerNumber() * defaultDiameter) + border;
    int drawDiam = racer.getDiameter() - (2 * border);
    g.setColor(racer.getColor());
    g.fillOval(topX, topY, drawDiam, drawDiam);
    g.setColor(Color.black);
    g.drawOval(topX, topY, drawDiam, drawDiam);
  }

  // This method causes your program to pause for a certain number of milliseconds
  // Don't worry about the details of the class Thread, the weird 'try/catch' keywords,
  //  we won't cover them in this course.
  private void delay(int ms) {
    try {
      Thread.sleep(ms);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public static void main(String[] args) {
    DotRace race = new DotRace();
  }
} // end DotRace
