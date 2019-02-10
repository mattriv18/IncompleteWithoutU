package ctrl;

import java.awt.Graphics2D;
import java.awt.Image;

public class Entity {
	private float x;
	private float y;
	private Sprite sprite;
	private Map map;
	private float size = 0.3f;
	
	public Entity(String ref, Map map, float x, float y) {
		this.sprite = SpriteStore.get().getSprite(ref);
		this.map = map;
		this.x = x;
		this.y = y;
	}
	public boolean move(float dx, float dy) {
		float newx = x+dx;
		float newy = y+dy;
		
		if(validLocation(newx, newy)) {
			x = newx;
			y = newy;
			return true;
		}
		return false;
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
