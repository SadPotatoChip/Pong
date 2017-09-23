package aiPack;

import gameObjects.Ball;
import gameObjects.Pad;

public abstract class Ai {
	public int target=-1;
	
	public abstract void onBounce(Ball ball);
	
	public abstract void executeAi(Ball ball, Pad pad);
	
}
