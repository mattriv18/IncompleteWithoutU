package ctrl;

import java.util.ArrayList;

public class PlayerEntity extends Entity {
	private Game game;
	
	public PlayerEntity(Game game, String ref, int tileX, int tileY, boolean tileColor) {
		super(ref, tileX, tileY, tileColor);
		this.game = game;
	}
	
	public void move() {
		if((dx < 0) && (tileX < 10)) {
			return;
		}
		if((dx > 0) && (tileX >= 700)) {
			return;
		}
		if((dy < 0) && (tileY < 10)) {
			return;
		}
		if((dy > 0) && (tileY >= 700)) {
			return;
		}
		super.move();
	}
	
	public boolean canIMove() {
		if(game.getOtherPlayer(this).tileX == this.tileX && game.getOtherPlayer(this).tileY == this.tileY) {
			ArrayList<Entity> ents = game.getEntities();
			for(int i = 0; i < ents.size(); i++) {
				if(ents.get(i) instanceof TileEntity) {
					if(ents.get(i).tileX == this.tileX && ents.get(i).tileY == this.tileY && ents.get(i).tileColor == tileColor) {
						System.out.println("hi");
						return true;
					} else if(ents.get(i).tileX == this.tileX && ents.get(i).tileY == this.tileY && ents.get(i).tileColor != tileColor){
						return false;
					}
					
				}
			}
		}
		return true;
	}
	//checks to see if the player has the opposite color tile next to it
	//if it does, then it returns true meaning that it won't be able to move there
	public boolean getRight() {
		if(game.getOtherPlayer(this).tileX == this.tileX+50 && game.getOtherPlayer(this).tileY == this.tileY) {
			return false;
		}
		ArrayList<Entity> ents = game.getEntities();
		for(int i = 0; i < ents.size(); i++) {
			if(ents.get(i).tileX == this.tileX+50 && ents.get(i).tileY == this.tileY && ents.get(i).tileColor == tileColor) {
				return true;
			}
		}
		return false;
	}
	public boolean getLeft() {
		if(game.getOtherPlayer(this).tileX == this.tileX-50 && game.getOtherPlayer(this).tileY == this.tileY) {
			return false;
		}
		ArrayList<Entity> ents = game.getEntities();
		for(int i = 0; i < ents.size(); i++) {
			if(ents.get(i).tileX == this.tileX-50 && ents.get(i).tileY == this.tileY &&  ents.get(i).tileColor == tileColor) {
				return true;
			}
		}
		return false;
	}
	public boolean getDown() {
		if(game.getOtherPlayer(this).tileY == this.tileY+50 && game.getOtherPlayer(this).tileX == this.tileX) {
			return false;
		}
		ArrayList<Entity> ents = game.getEntities();
		for(int i = 0; i < ents.size(); i++) {
			if(ents.get(i).tileY == this.tileY+50 && ents.get(i).tileX == this.tileX && ents.get(i).tileColor == tileColor) {
				return true;
			}
		}
		return false;
	}
	public boolean getUp() {
		if(game.getOtherPlayer(this).tileY == this.tileY-50 && game.getOtherPlayer(this).tileX == this.tileX) {
			return false;
		}
		ArrayList<Entity> ents = game.getEntities();
		for(int i = 0; i < ents.size(); i++) {
			if(ents.get(i).tileY == this.tileY-50 && ents.get(i).tileX == this.tileX && ents.get(i).tileColor == tileColor) {
				return true;
			}
		}
		return false;
	}
}
