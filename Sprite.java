package ctrl;

import java.awt.Graphics;
import java.awt.Image;

public class Sprite {
	private Image image;
	private int w;
	private int h;
	
	public Sprite(Image image) {
		this.image = image;
		w = image.getWidth(null);
		h = image.getHeight(null);
	}
	
	public int getWidth() {
		return w;
	}
	
	public int getHeight() {
		return h;
	}
	
	public void draw(Graphics g, int x, int y) {
		g.drawImage(image, x, y, null);
	}
}
