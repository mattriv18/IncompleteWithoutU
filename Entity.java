package ctrl;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

public class Entity {
	private float x;
	private float y;
	private Sprite sprite;
	private Map map;
	private float size = 0.3f;
	private float dx = 1;
	private float dy = 1;
	
	public Entity(String ref, Map map, float x, float y) {
		this.sprite = SpriteStore.get().getSprite(ref);
		this.map = map;
		this.x = x;
		this.y = y;
	}
	public boolean move() {
		float newx = x+dx;
		float newy = y+dy;
		if(validLocation(newx, newy)) {
			x = newx;
			y = newy;
			return true;
		}
		return false;
		
	}
	
	public void setHorizontalMovement(float dx) {
		this.dx = dx;
	}

	public void setVerticalMovement(float dy) {
		this.dy = dy;
	}
	
	public double getHorizontalMovement() {
		return dx;
	}

	public double getVerticalMovement() {
		return dy;
	}
	
	public void draw(Graphics g) {
		sprite.draw(g,(int) x,(int) y);
	}
	
	public void doLogic() {
	}
	
	public int getX() {
		return (int) x;
	}

	public int getY() {
		return (int) y;
	}
	
	public boolean validLocation(float newx, float newy) {
		if(map.white(newx - size, newy - size)) {
			return false;
		}
		if(map.white(newx + size, newy - size)) {
			return false;
		}
		if(map.white(newx - size, newy + size)) {
			return false;
		}
		if(map.white(newx + size, newy + size)) {
			return false;
		}
		return true;
	}
}