package ctrl;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends Canvas {
	//the strategy that allows our game to run better
	private BufferStrategy strategy;
	//is set to true if the game is currently running (looping)
	private boolean gameRunning = true;
	
	public Game() {
		//Create the frame for our game
		JFrame container = new JFrame("ctrl game");
		
		//Gets the content area from the container and creates a panel for it
		//then sets the original resolution of the game
		JPanel panel = (JPanel) container.getContentPane();
		panel.setPreferredSize(new Dimension(750, 750));
		panel.setLayout(null);
		
		//setup our canvas size and puts it into the content of the frame
		setBounds(0,0,750,750);
		panel.add(this);
		
		//Tell AWT to not repaint our canvas since we will do that ourselves
		setIgnoreRepaint(true);
		
		//make the window visible on our screen
		container.pack();
		container.setResizable(false);
		container.setVisible(true);
		
		//create a buffer strategy that allows AWT to do something to make our game
		//run better
		createBufferStrategy(2);
		strategy = getBufferStrategy();
	}
	
	public void gameLoop() {
		//this starts to be the time that the first loop starts then changes to be the
		//beginning of each loop
		long lastLoopTime = System.currentTimeMillis();
		
		while(gameRunning) {
			//delta is the change in time since the beginning of the last loop
			long delta = System.currentTimeMillis() - lastLoopTime;
			lastLoopTime = System.currentTimeMillis();
			
			//gets a hold of the graphics context for the accelerated surface and makes it all black
			Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
			g.setColor(Color.black);
			g.fillRect(0,0,750,750);
			
			//since we have finished drawing we clear up the current graphics and then flip the buffer
			//i.e. we change the current "frame" to be the next one
			g.dispose();
			strategy.show();
			
			//then pause for a bit: this will allow us to have the game run at 100 fps
			try { Thread.sleep(10); } catch (Exception e) {}
		}
	}
	
	public static void main(String argv[]) {
		Game g = new Game();
		g.gameLoop();
	}
}
