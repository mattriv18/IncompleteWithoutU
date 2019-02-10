package ctrl;

import java.awt.Color;
import java.awt.Graphics2D;

public class Map {
	private static final int BLACK = 0;
	private static final int WHITE = 1;
	private static final int WIDTH = 15;
	private static final int HEIGHT = 15;
	public static final int TILE_SIZE = 50;
	private int[][] data = new int[WIDTH][HEIGHT];
	
	public Map() {
		for (int y = 0; y < HEIGHT; y++) {
			data[0][y] = WHITE;
			data[2][y] = WHITE;
			data[7][y] = WHITE;
			data[11][y] = WHITE;
			data[WIDTH-1][y] = WHITE;
		}
		for (int x = 0; x < WIDTH; x++) {
			if((x > 0) && (x < WIDTH-1)) {
				data[x][10] = BLACK;
			}
			if(x > 2) {
				data[x][9] = WHITE;
			}
			data[x][0] = WHITE;
			data[x][HEIGHT-1] = WHITE;
		}
		data[4][9] = BLACK;
		data[7][5] = BLACK;
		data[7][4] = BLACK;
		data[11][7] = BLACK;
	}
	public void paint(Graphics2D g) {
		for(int x = 0; x < WIDTH; x++) {
			for(int y = 0; y < HEIGHT; y++) {
				g.setColor(Color.black);
				if(data[x][y] == WHITE) {
					g.setColor(Color.white);
				}
				g.fillRect(x*TILE_SIZE,  y*TILE_SIZE, TILE_SIZE, TILE_SIZE);
				g.setColor(g.getColor().darker());
				g.drawRect(x*TILE_SIZE,  y*TILE_SIZE, TILE_SIZE, TILE_SIZE);
			}
		}
	}
	public boolean white(float x, float y) {
		return data[(int) x][(int) y] == WHITE;
	}
}
