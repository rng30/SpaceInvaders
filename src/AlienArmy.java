import java.awt.*;
import java.util.*;

public class AlienArmy {

    //The army has 3 rows of aliens
    Alien rowOne[] = new Alien[10];
    Alien rowTwo[] = new Alien[10];
    Alien rowThree[] = new Alien[10];

    //A container to store details of the current alien shots
    Vector alienShots = new Vector();    

    private Ship ship;

    private SpaceInvaders spaceInvaders;

    Image alienImage = null;
	
    public AlienArmy(Ship s, SpaceInvaders si, Image ai) {
        ship = s;
        spaceInvaders = si;
        alienImage = ai;

        alienImage = new javax.swing.ImageIcon("alienFull.jpg").getImage();

	
        createArmy();
        setStartingPositions();
    }

    /**
     * In this method we initialise the 3 rows of aliens
     * that make up the army.
     */
    private void createArmy() {
        //Creating an army using a nested loop
	for (int i = 0; i < 10; i++) {
		rowOne[i] = new Alien(alienImage, spaceInvaders);
		rowTwo[i] = new Alien(alienImage, spaceInvaders);    
		rowThree[i] = new Alien(alienImage, spaceInvaders);//Finally set the third row
	}	
    }

    /**
     * Set where our alien army will start attacking from
     */
    private void setStartingPositions() {

        int rowHeight = 50;//Set the height of the top row
        int leftStart = 50;//Sets the furtherest position to the left
        for (int i = 0; i < 10; i++) {
            rowOne[i].setPosition(leftStart, rowHeight);
            leftStart += 40;
        }
        rowHeight += 50;//Ready for the next row
        leftStart = 50;//Reset the left position
        for (int i = 0; i < 10; i++) {
            rowTwo[i].setPosition(leftStart, rowHeight);
            leftStart += 40;
        }
        rowHeight += 50;//Ready for the third row
        leftStart = 50;//Reset the left position
        for (int i = 0; i < 10; i++) {
            rowThree[i].setPosition(leftStart, rowHeight);
            leftStart += 40;
        }	
    }
    
    public void randomAlienShot(){
    	//Start some random alien firing!
    	Random generator = new Random();
    	int rnd1 = generator.nextInt(10);
    	int rnd2 = generator.nextInt(10);
    	int rnd3 = generator.nextInt(10);
    	if (!rowOne[rnd1].hasBeenHit()) {
    	    AlienShot as = new AlienShot(rowOne[rnd1].getXPos()+(int)(Alien.ALIEN_WIDTH/2), rowOne[rnd1].getYPos(), ship);
            alienShots.addElement(as);
    	}
    	if (!rowOne[rnd2].hasBeenHit()) {	
    	    AlienShot as = new AlienShot(rowTwo[rnd2].getXPos()+(int)(Alien.ALIEN_WIDTH/2), rowTwo[rnd2].getYPos(), ship);
            alienShots.addElement(as);
    	}
    	if (!rowOne[rnd3].hasBeenHit()) {	
        	AlienShot as = new AlienShot(rowThree[rnd3].getXPos()+(int)(Alien.ALIEN_WIDTH/2), rowThree[rnd3].getYPos(), ship);
            alienShots.addElement(as);
    	}
    }
    public void drawArmy(Graphics g) {
        //Draw the first row
	for (int i = 0; i < 10; i++) {
            rowOne[i].drawAlien(g);//Draw the first row
            rowTwo[i].drawAlien(g);//Draw the second row
            rowThree[i].drawAlien(g);//Draw the third row
	} 
        //Now we need to draw any of the shots the aliens have fired
	Vector tmp = new Vector();
	for (int i = 0; i < alienShots.size(); i++) {
            AlienShot as = (AlienShot)alienShots.elementAt(i);
	    //Need to remove old shots at this point!
	    if (as.getShotState()) {
                //This is NOT an old shot
		tmp.addElement(as);
	    }
	    
	    as.drawShot(g);
	    
	    
	}
	alienShots = tmp;
    }

    /**
     * This is where the collision detection takes place
     */
    public boolean checkShot(int x, int y) {
        for (int i = 0; i < 10; i++) {
            if (rowOne[i].hitAlien(x, y)) {
            	spaceInvaders.hitAlienScore();
                return true;
	    }
            if (rowTwo[i].hitAlien(x, y)) {
            	spaceInvaders.hitAlienScore();		    
                return true;
	    }
            if (rowThree[i].hitAlien(x, y)) {
            	spaceInvaders.hitAlienScore();		    
                return true;
	    }	    
	}
	return false;
    }

}
