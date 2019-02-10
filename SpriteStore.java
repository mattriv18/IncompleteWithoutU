package ctrl;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class SpriteStore {
	private static SpriteStore single = new SpriteStore();
	
	private HashMap<String, Sprite> sprites = new HashMap<>();
	
	public static SpriteStore get() {
		return single;
	}
	
	public Sprite createSprite(String ref) {
		// if we've already got the sprite in the cache
		// then just return the existing version
		if (sprites.get(ref) != null) {
			return (Sprite) sprites.get(ref);
		}
		
		BufferedImage sourceImage = null;
		try {
			sourceImage = ImageIO.read(new File(ref));
		} catch(Exception e) {
			System.out.println(e);
		}
		
		// create an accelerated image of the right size to store our sprite in
		GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().
		                             getDefaultScreenDevice().getDefaultConfiguration();
		Image image = gc.createCompatibleImage(sourceImage.getWidth(),
		                                       sourceImage.getHeight(),
		                                       Transparency.BITMASK);
		// draw our source image into the accelerated image
		image.getGraphics().drawImage(sourceImage,0,0,null);
		
		Sprite sprite = new Sprite(image);
		sprites.put(ref, sprite);
		
		return sprite;
	}
}
