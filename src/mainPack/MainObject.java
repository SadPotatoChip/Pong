package mainPack;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import aiPack.*;
import gameObjects.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class MainObject extends Application{
	Scene initialGameScene;
	
	Ai ai;
	
	boolean isPaused=false;
	int scoreRight=0,scoreLeft=0;
	
	Button[] btnArray;
	
	Label scoreboard;
	public boolean stopGameLoop=false;
	public Pad right,left;
	public Ball ball;
	Group allGraphics;
	KeyboardListenerStuff kls;
	
	public enum bd{
		ur , ul , dl , dr ;		
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
	
	public void start(Stage primaryStage) throws Exception {
		primaryStage=stageSetup(primaryStage);
		gameLoop();		
	}
	
	Stage stageSetup(Stage primaryStage){
		ai=new AiEasy();
		btnArray=new Button[3];
		kls=new KeyboardListenerStuff();
		allGraphics=setupGraphics(allGraphics);
		initialGameScene= new Scene(allGraphics ,600,400);		
		kls.setupKeyboardListener(initialGameScene);
		primaryStage.setMinWidth(600);
		primaryStage.setMinHeight(400);
		primaryStage.setResizable(false);	
		primaryStage.setScene(initialGameScene);
		primaryStage.show();
		return primaryStage;
	}
	
	Group setupGraphics(Group allGraphics){
		allGraphics=new Group();
		scoreboard=new Label("0 : 0");
		scoreboard.setTranslateX(280);
		scoreboard.setFont(new Font("Arial", 30));
		Rectangle left=setupRectangle(10, 150, 100, 10);
		Rectangle right=setupRectangle(590, 150, 100, 10);
		this.right=new Pad(right);
		this.left=new Pad(left);
		ball=makeBall();
		
		Button resetScore=new Button("Reset Game");
		resetScore.setOnAction(foo->{
			scoreLeft=0;
			scoreRight=0;
			resetGame();
		});
		resetScore.setVisible(false);
		btnArray[0]=resetScore;
		
		allGraphics.getChildren().addAll(right,left,scoreboard,ball.rect,resetScore);
		return allGraphics;
	}
	
	public Ball makeBall(){
		float a,b;
		int dir;
		Random r=new Random();
		a=r.nextInt(100);
		b=r.nextInt(100);
		dir=r.nextInt(4);
		Rectangle ball=setupRectangle(250+a, 150+b, 10, 10);
		bd tmp=bd.dl;
		switch (dir){
			case 0: tmp=bd.ul;
				break;
			case 1: tmp=bd.ur;
				break;
			case 2: tmp=bd.dr;
				break;
			case 3: tmp=bd.dl;
		}		
		return new Ball(ball,tmp);
	}
	
	public static Rectangle setupRectangle(double x,double y,double a,double b){
		Rectangle r=new Rectangle();
		r.setHeight(a);
		r.setWidth(b);
		r.setX(x);
		r.setY(y);	
		return r;
	}
	
	void gameLoop(){
		Timer t=new Timer();
		t.schedule(new TimerTask() {
            public void run() {
            	if(isPaused){
            		if(kls.isPressed('p')){
            			for (Button b : btnArray){
		            		if(b!=null)
		            			b.setVisible(false);
		            	}
		               	isPaused=false;
		               	kls.currentlyActiveKeys.remove("P");
		            }
            	}else{
	            	if(ball.x<0 || ball.x>600){
	    				if(ball.x<10){
	    					scoreRight++;
	    				}else{
	    					scoreLeft++;
	    				}	
	            		resetGame();
	            	}
	            	if(kls.isPressed('w')){
		               	left.movePad(-1);
		            }
		            if(kls.isPressed('s')){
		               	left.movePad(1);
		            }
		            ball.ballMove();
		            ball.changeBallDir(left, right, ai);
		            padAI();
		            if(kls.isPressed('p')){
		            	for (Button b : btnArray){
		            		if(b!=null)
		            			b.setVisible(true);
		            	}
		               	isPaused=true;
		               	kls.currentlyActiveKeys.remove("P");
		            }
	            }
            }
        }, 0, 30);		
	}
	
	void padAI(){
		ai.executeAi(ball,right);
	}
	
	void resetGame(){
		Platform.runLater(new Runnable(){
			public void run(){
				ai.target=-1;
				scoreboard.setText(scoreLeft+ " : " + scoreRight);
				allGraphics.getChildren().remove(ball.rect);
				ball=makeBall();
				allGraphics.getChildren().add(ball.rect);
			}
		});		
	}
	

}









