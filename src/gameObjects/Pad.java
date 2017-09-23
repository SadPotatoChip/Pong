package gameObjects;

import javafx.scene.shape.Rectangle;

public class Pad {
	Rectangle rect;
	public double pos;
	double shift=4f;
	
	double upLimit=0,downLimit=310f;
	
	public Pad(Rectangle rect){
		this.rect=rect;
		pos=rect.getY();
	}
	
	public void movePad(int dir){		
		if(dir>0){
			if(pos<=downLimit){
				pos+=dir*shift;
				rect.setY(pos);
			}
		}else{
			if(pos>=upLimit){
				pos+=dir*shift;
				rect.setY(pos);
			}
		}		
	}
	
}
