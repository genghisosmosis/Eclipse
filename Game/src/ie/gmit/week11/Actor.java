package ie.gmit.week11;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.util.Set;

/**
 * The interface Actor extends Shape
 *  
 * @author AndrewBeatty
 *
 */

public interface Actor extends Shape{
	public boolean move();
	public void draw(Graphics2D g);
}
