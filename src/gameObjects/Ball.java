package gameObjects;

import aiPack.Ai;
import javafx.scene.shape.Rectangle;
import mainPack.MainObject.bd;

public class Ball {
	public double x,y;
	public Rectangle rect;
	public double shift=5f;
	public bd dir;
	
	public Ball(Rectangle rect, bd dir){
		this.rect=rect;
		x=rect.getX();
		y=rect.getY();
		this.dir=dir;
	}
	
	public void ballMove(){
		int x=1,y=1;
		switch (dir){
			case ul: x=-1; y=-1;
				break;
			case ur: x=1; y=-1;
				break;
			case dr: x=1; y=1;
				break;
			case dl: x=-1; y=1;
		}
		this.x+=shift*x;
		this.y+=shift*y;
		
		rect.setX(this.x);
		rect.setY(this.y);
	}
	
	public void changeBallDir(Pad left, Pad right, Ai ai){
		if(y<=1){
			if(bd.ul==dir){
				dir=bd.dl;				
			}else{
				dir=bd.dr;
			}
		}
		if(y>=399){
			if(bd.dl==dir){
				dir=bd.ul;				
			}else{
				dir=bd.ur;
			}
		}
		if(x<=20 && x>10 && isInPositionPad(left)){
			if(bd.ul==dir){
				dir=bd.ur;				
			}else{
				dir=bd.dr;
			}	
		}
		if(x>=580 && x<590 && isInPositionPad(right)){
			if(bd.ur==dir){
				dir=bd.ul;				
			}else{
				dir=bd.dl;
			}
			ai.onBounce(this);
		}
		
	}
	
	public boolean isInPositionPad(Pad p){
		if(p.pos<=y && y<=p.pos+100)
			return true;
		else
			return false;
	}
	

}











