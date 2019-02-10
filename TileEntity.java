package ctrl;

public class TileEntity extends Entity {
	private Game game;
	private boolean goal;
	
	public TileEntity(Game game, String ref, int x, int y, boolean tileColor, boolean goal) {
		super(ref, x, y, tileColor);
		this.game = game;
		this.goal = goal;
	}
	
	public boolean getGoal() {
		return goal;
	}
}
