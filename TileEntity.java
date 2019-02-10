package ctrl;

public class TileEntity extends Entity {
	private Game game;
	
	public TileEntity(Game game, String ref, int x, int y, boolean tileColor) {
		super(ref, x, y, tileColor);
		this.game = game;
	}
}
