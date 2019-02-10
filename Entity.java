package ctrl;

import java.awt.Graphics;

public class Entity {
	protected float tileX;
	protected float tileY;
	private Sprite sprite;
	protected float dx = 0;
	protected float dy = 0;
	protected boolean tileColor; // true: black ----- false: white
	
	public Entity(String ref, float tileX, float tileY, boolean tileColor) {
		this.sprite = SpriteStore.get().getSprite(ref);
		this.tileX = tileX;
		this.tileY = tileY;
		this.tileColor = tileColor;
	}
	public void move() {
		// update the location of the entity based on move speeds
		tileX += dx;
		tileY += dy;
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
		sprite.draw(g,(int) tileX,(int) tileY);
	}
	
	public void doLogic() {
	}
	
	public int getX() {
		return (int) tileX;
	}

	public int getY() {
		return (int) tileY;
	}
	
	public boolean getRight() {
		return false;
	}
	public boolean getLeft() {
		return false;
	}
	public boolean getDown() {
		return false;
	}
	public boolean getUp() {
		return false;
	}
	public boolean canIMove() {
		return false;
	}
	public boolean getGoal() {
		return false;
	}
}