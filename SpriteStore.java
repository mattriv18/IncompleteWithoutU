package ctrl;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class SpriteStore {
	private static SpriteStore single = new SpriteStore();
	
	public static SpriteStore get() {
		return single;
	}
}
