package mainPack;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import java.util.HashSet;

import javafx.scene.Scene;

public class KeyboardListenerStuff {
	public HashSet<String> currentlyActiveKeys;
	
    public boolean isPressed(char c) {
        synchronized (KeyboardListenerStuff.class) {
        	if('w'==c)
        		return (currentlyActiveKeys.contains("W") || currentlyActiveKeys.contains("UP"));
        	if('s'==c)
        		return (currentlyActiveKeys.contains("S") || currentlyActiveKeys.contains("DOWN"));
        	if('p'==c)
        		return (currentlyActiveKeys.contains("P") || currentlyActiveKeys.contains("ESCAPE"));
        	
        	System.out.println("read input error");
        	return false;
        }
    }

    public void setupKeyboardListener(Scene scene) {
    	currentlyActiveKeys = new HashSet<String>();
    	scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
	    	@Override
	    	public void handle(KeyEvent event){
	    		currentlyActiveKeys.add(event.getCode().toString());
	    	}
    	});
    	scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
	    	@Override
	    	public void handle(KeyEvent event){
	    	    currentlyActiveKeys.remove(event.getCode().toString());
	    	}
    	});
    }
}
