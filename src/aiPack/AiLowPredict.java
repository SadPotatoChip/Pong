package aiPack;

import gameObjects.Ball;
import gameObjects.Pad;
import mainPack.MainObject.bd;

public class AiLowPredict extends Ai{
	public int target=-1;
	
	public void executeAi(Ball ball, Pad pad) {
		if(target==-1){
			if(ball.y>pad.pos+50){
				pad.movePad(1);
			}else{
				pad.movePad(-1);
			}			
		}else{
			if(target>pad.pos+50){
				pad.movePad(1);
			}else{
				pad.movePad(-1);
			}		
		}
		
	}

	public void onBounce(Ball ball) {
			//not yet implemented
	}

}
