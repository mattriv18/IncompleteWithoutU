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
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends Canvas {
	//the strategy that allows our game to run better
	private BufferStrategy strategy;
	//is set to true if the game is currently running (looping)
	private boolean gameRunning = true;
	private ArrayList<Entity> entities = new ArrayList<>();
	private Map map;
	private Entity whitePlayer;
	private Entity blackPlayer;
	private Entity blackGoal;
	private Entity whiteGoal;
	private boolean leftPressed1;
	private boolean rightPressed1;
	private boolean upPressed1;
	private boolean downPressed1;
	private boolean leftPressed0;
	private boolean rightPressed0;
	private boolean upPressed0;
	private boolean downPressed0;
	private int moveSpeed = 50;
	
	public Entity getOtherPlayer(Entity player) {
		if(player.tileColor == true) {
			return whitePlayer;
		}
		return blackPlayer;
	}
	
	private void initEntities() {
		ArrayList<String> lines = new ArrayList<>();
		try(Scanner sc = new Scanner(new File("resources/lvl1.txt"))){
			while(sc.hasNextLine()) {
				lines.add(sc.nextLine());
			}
		}
		catch(FileNotFoundException e){
			System.out.println("File '" + "lvl1.txt" + "' not found, initializing both 'list' and 'array' to be empty\n");
		}
		catch(Exception e){
			System.out.println("Error occurred while extracting data:\n");

		}
		int currentX = 0;
		int currentY = 0;
		for(String s : lines) {
			char[] chars = s.toCharArray();
			for(char c : chars) {
				if(c == '0') {
					entities.add(new TileEntity(this, "whitetile.png", currentX, currentY, false, false));
				} else if(c == '1') {
					entities.add(new TileEntity(this, "blacktile.png", currentX, currentY, true, false));
				} else if(c == 'b') {
					whitePlayer = new PlayerEntity(this, "blacktileplayer.png", currentX, currentY, false);
					entities.add(whitePlayer);
					entities.add(new TileEntity(this, "blacktile.png", currentX, currentY, true, false));
				} else if(c == 'w') {
					blackPlayer = new PlayerEntity(this, "whitetilePlayer.png", currentX, currentY, true);
					entities.add(blackPlayer);
					entities.add(new TileEntity(this, "whitetile.png", currentX, currentY, false, false));
				} else if(c == 'W') {
					whiteGoal = new TileEntity(this, "WhiteExit.png", currentX, currentY, false, true);
					entities.add(whiteGoal);
				} else if(c == 'B') {
					blackGoal = new TileEntity(this, "BlackExit.png", currentX, currentY, true, true);
					entities.add(blackGoal);
				}
				currentX += 50;
			}
			currentX = 0;
			currentY += 50;
			
		}
	}
	
	public ArrayList<Entity> getEntities(){
		return entities;
	}
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
		
		addKeyListener(new KeyInputHandler());
		
		//create a buffer strategy that allows AWT to do something to make our game
		//run better
		createBufferStrategy(2);
		strategy = getBufferStrategy();
		map = new Map();
		//player = new Entity(sprite, map, 0, 0);
		initEntities();
	}
	
	public void gameLoop() {
		while(gameRunning) {
			//gets a hold of the graphics context for the accelerated surface and makes it all black
			Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
			g.setColor(Color.black);
			g.fillRect(0,0,750,750);
			
			for (int i=0;i<entities.size();i++) {
				Entity entity = (Entity) entities.get(i);
				entity.move();
			}
			// cycle round drawing all the entities we have in the game
			int blackPlayerIndex = 0;
			int whitePlayerIndex = 0;
			for (int i=0;i<entities.size();i++) {
				Entity entity = (Entity) entities.get(i);
				if(entity instanceof TileEntity) {
					entity.draw(g);
				} else if(entity instanceof PlayerEntity) {
					if(entity.tileColor) {
						blackPlayerIndex = i;
					} else {
						whitePlayerIndex = i;
					}
				}
			}
			entities.get(blackPlayerIndex).draw(g);
			entities.get(whitePlayerIndex).draw(g);
			
			whitePlayer.setHorizontalMovement(0);
			whitePlayer.setVerticalMovement(0);
			// cycle round asking each entity to move itself
			if(leftPressed1 && !rightPressed1) {
				if(whitePlayer.canIMove() && !whitePlayer.getLeft()) {
					whitePlayer.setHorizontalMovement(-moveSpeed);
				}
				leftPressed1 = !leftPressed1;
			}
			if(rightPressed1 && !leftPressed1) {
				if(whitePlayer.canIMove() && !whitePlayer.getRight()) {
					whitePlayer.setHorizontalMovement(moveSpeed);
				}
				rightPressed1 = !rightPressed1;
			}
			if(upPressed1 && !downPressed1) {
				if(whitePlayer.canIMove() && !whitePlayer.getUp()) {
					whitePlayer.setVerticalMovement(-moveSpeed);
				}
				upPressed1 = !upPressed1;
			}
			if(downPressed1 && !upPressed1) {
				if(whitePlayer.canIMove() && !whitePlayer.getDown()) {
					whitePlayer.setVerticalMovement(moveSpeed);
				}
				downPressed1 = !downPressed1;
			}
			
			blackPlayer.setHorizontalMovement(0);
			blackPlayer.setVerticalMovement(0);
			// cycle round asking each entity to move itself
			if(leftPressed0 && !rightPressed0) {
				if(blackPlayer.canIMove() && !blackPlayer.getLeft()) {
					blackPlayer.setHorizontalMovement(-moveSpeed);
				}
				leftPressed0 = !leftPressed0;
			}
			if(rightPressed0 && !leftPressed0) {
				if(blackPlayer.canIMove() && !blackPlayer.getRight()) {
					blackPlayer.setHorizontalMovement(moveSpeed);
				}
				rightPressed0 = !rightPressed0;
			}
			if(upPressed0 && !downPressed0) {
				if(blackPlayer.canIMove() && !blackPlayer.getUp()) {
					blackPlayer.setVerticalMovement(-moveSpeed);
				}
				upPressed0 = !upPressed0;
			}
			if(downPressed0 && !upPressed0) {
				if(blackPlayer.canIMove() && !blackPlayer.getDown()) {
					blackPlayer.setVerticalMovement(moveSpeed);
				}
				downPressed0 = !downPressed0;
			}
			
			if(((PlayerEntity) blackPlayer).didIFinish() && ((PlayerEntity) whitePlayer).didIFinish()) {
				System.out.println("YOU WON");
			}
			//since we have finished drawing we clear up the current graphics and then flip the buffer
			//i.e. we change the current "frame" to be the next one
			g.dispose();
			strategy.show();
			
			//then pause for a bit: this will allow us to have the game run at 100 fps
			try { Thread.sleep(10); } catch (Exception e) {}
		}
	}
	
	private class KeyInputHandler extends KeyAdapter{			
		public void keyReleased(KeyEvent e) {			
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				leftPressed1 = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				rightPressed1 = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				upPressed1 = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				downPressed1 = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_A) {
				leftPressed0 = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_D) {
				rightPressed0 = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_W) {
				upPressed0 = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_S) {
				downPressed0 = true;
			}
		}

		public void keyTyped(KeyEvent e) {
			// if we hit escape, then quit the game
			if (e.getKeyChar() == 27) {
				System.exit(0);
			}
		}
	}
	
	public static void main(String argv[]) {
		Game g = new Game();
		g.gameLoop();
	}
}