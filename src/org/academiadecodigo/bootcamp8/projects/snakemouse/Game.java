package org.academiadecodigo.bootcamp8.projects.snakemouse;

import org.academiadecodigo.bootcamp8.projects.snakemouse.display.SplashScreen;
import org.academiadecodigo.bootcamp8.projects.snakemouse.gameobject.GameObject;
import org.academiadecodigo.bootcamp8.projects.snakemouse.gameobject.GameObjectsFactory;
import org.academiadecodigo.bootcamp8.projects.snakemouse.gameobject.representable.grid.Grid;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

/**
 * Created by codecadet on 25/05/17.
 */
public class Game {

    private Grid grid;
    private GameObject food;
    private SplashScreen splashScreen;
    private Keyboard k;
    //private KeyboardEvent event;
    private KeyboardClass keyboardClass;
    private boolean startYN;

    public Game(){
        keyboardClass = new KeyboardClass(this);
        //event = new KeyboardEvent();
        //keyboardClass.keyboardStart();
    }


    public SplashScreen getSplashScreen(){
        return splashScreen;
    }


    public void init() {

        splashScreen = new SplashScreen();
        grid = GameObjectsFactory.createGrid(24, 24);

    }
/*
    public void spaceKey() {

        k = new Keyboard(this);
        event = new KeyboardEvent();
        event.setKey(KeyboardEvent.KEY_SPACE);
        event.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        k.addEventListener(event);
    }

    @Override
    public void keyPressed(KeyboardEvent e) {
        splashScreen.hide();
        start();
        k.removeEventListener(event);
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }
*/
    public void show() {

        //Keyboard k3 = new Keyboard();
        splashScreen.show();
        //spaceKey();
        keyboardClass.keyboardStart();

    }

    public void start() {
        if (startYN) {
            return;
        }else {

            grid.init();
            //Hud hud = new Hud();
            food = GameObjectsFactory.createFood(grid);
            startYN=true;
        }

    }
}
