//Needed for the key
import java.awt.event.*;
//Needed for the graphics
import java.awt.*;
/**
 * The last defender of earth...
 *
 * Notice how this class has methods equivalent to those in the Alien class.
 */
public class Ship implements ActionListener, KeyListener {

    public static int SHIP_HEIGHT = 25;
    public static int SHIP_WIDTH = 15;

    private int x = 0;
    private int heightPosition = 0;
    private int xVel = 0;

    SpaceInvaders spaceInvaders = null;

    //We are only going to allow one shot at a time
    Shot shot = null;

    boolean hitState = false;

    /**
     *
     */
    public Ship(SpaceInvaders si) {
        spaceInvaders = si;
        //Dynamically work out the starting position of the ship
        x = (int)((SpaceInvaders.WIDTH/2)+(SHIP_WIDTH/2));
        heightPosition = SpaceInvaders.HEIGHT-SHIP_HEIGHT-20;
    }
    
    public void move(KeyEvent e) {
    	x += xVel;
    }
	
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	
	public void keyPressed(KeyEvent e) {
		// Left arrow key press
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            x -= 15;
        }
        // Right arrow key press
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            x += 15;
        }
        
        // Move from edge to edge without stopping
        if (x > 600) {
            x = -50;
        }
        if (x < -50) {
            x = 600;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			AlienArmy army = spaceInvaders.getAlienArmy();
	        shot = new Shot(x+(int)(SHIP_WIDTH/2), heightPosition, army);
		}
	}

	
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            x += 0;
        }
        // Right arrow key press
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            x += 0;
        }
		
	}

	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
    /**
     * Draw the image of the ship
     */ 
    public void drawShip(Graphics g) {
        g.setColor(Color.yellow);
        g.fillRect(x, heightPosition, SHIP_WIDTH, SHIP_HEIGHT);
        //If the shot is still alive, i.e. still on the screen
        if ((shot != null) && (shot.getShotState())) {
            shot.drawShot(g);
        }
    }

    /**
     * Check if a shot fired by an alien hit the ship
     */
    public boolean checkShot(int xShot, int yShot) {

        //Is the ship currently alive?
        //if (hitState) {
        //If it's alreay been shot then return false;
        // return false;
        //}

        //First lets check the X range
        if ((xShot >= x) && (xShot <= (x+SHIP_WIDTH))) {
            //X is ok, now lets check the Y range
            if ((yShot >= heightPosition) && (yShot <= (heightPosition+SHIP_HEIGHT))) {
                //The ship was hit!
                hitState = true;
                return true;
            }
        } 
        return false;
    }

    public void hitByAlien() {
        spaceInvaders.shotShip();
    }

}