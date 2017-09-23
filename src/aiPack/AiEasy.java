package aiPack;

import gameObjects.Ball;
import gameObjects.Pad;

public class AiEasy extends Ai {
	
	public void executeAi(Ball ball, Pad pad){
		if(ball.y>pad.pos+50){
			pad.movePad(1);
		}else{
			pad.movePad(-1);
		}	
	}

	public void onBounce(Ball ball) {
		System.out.println("SMOrc");
		
	}

}
