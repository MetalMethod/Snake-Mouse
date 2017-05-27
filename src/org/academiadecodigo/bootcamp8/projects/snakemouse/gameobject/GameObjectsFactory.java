package org.academiadecodigo.bootcamp8.projects.snakemouse.gameobject;

import org.academiadecodigo.bootcamp8.projects.snakemouse.Game;
import org.academiadecodigo.bootcamp8.projects.snakemouse.gameobject.representable.representablemovable.Snake;
import org.academiadecodigo.bootcamp8.projects.snakemouse.gameobject.representable.grid.Grid;
import org.academiadecodigo.bootcamp8.projects.snakemouse.gameobject.representable.Food;
import org.academiadecodigo.bootcamp8.projects.snakemouse.gameobject.representable.representablemovable.Mouse;
import org.academiadecodigo.bootcamp8.projects.snakemouse.simplegfx.SimpleGraphicsGrid;

/**
 * Created by codecadet on 25/05/17.
 */
public class GameObjectsFactory {

    public static Grid createGrid(int cols, int rows) {

        return new SimpleGraphicsGrid(cols,rows);
    }

    public static Food createFood(Grid grid) {

        Food food = new Food(grid.makeGridPosition());

        return food;
    }

    public static Mouse createMouse() {
        return null ;
    }

    public static Snake createSnake() {
        return null;
    }

}

