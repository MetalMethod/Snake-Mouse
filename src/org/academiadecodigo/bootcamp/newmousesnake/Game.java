package org.academiadecodigo.bootcamp.newmousesnake;

import org.academiadecodigo.bootcamp.newmousesnake.Grid.Grid;
import org.academiadecodigo.bootcamp.newmousesnake.Grid.SimplegfxGrid;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.ArrayList;

/**
 * Created by codecadet on 30/05/17.
 */
public class Game {

    private Grid grid;
    private Mouse mouse;
    private Snake snake;
    private Picture[] coins;
    private GameOver gameover;
    private ArrayList<Food> foods;
    private int mouseLifes = 3;
    private int snakeLifes = 3;

    public void init() throws InterruptedException {

        grid = new SimplegfxGrid(24, 24);
        mouse = new Mouse(grid.makeGridPosition(20, 20));
        snake = new Snake(grid.makeGridPosition(4, 4));
        foods = new ArrayList<>();
        gameover = new GameOver();

        foods.add(new Food(grid.makeGridPosition()));

        coins = new Picture[6];
        drawCoins();

        start();
    }

    public void start() throws InterruptedException {

        while (true) {

            Thread.sleep(170);

            mouse.move(mouse.getDirection());

            snake.move(snake.getDirection());

            detectMouseEatingFood();

            checkSnakeEatFoodorPoop();

            checkSnakeKillsMouse();

            eraseCoins();

            gameOver();

            cheat();
        }
    }

    public void cheat(){
        for (Food food: foods){
            for (Food food1: foods){
                if (food==food1){
                    break;
                }
                if (food.getPosition().getCol() == food1.getPosition().getCol() && food.getPosition().getRow()==food1.getPosition().getRow()){
                    food.getPicFood().delete();
                }
            }
        }
    }

    public void checkSnakeKillsMouse() {
        int snakePosCol = snake.getPosition().getCol();
        int snakePosRow = snake.getPosition().getRow();

        int mousePosCol = mouse.getPosition().getCol();
        int mousePosRow = mouse.getPosition().getRow();

        if (snakePosCol+1 == mousePosCol && snakePosRow+1 == mousePosRow ||
                snakePosCol+1 == mousePosCol + 1 && snakePosRow+1 == mousePosRow ||
                snakePosCol+1 == mousePosCol && snakePosRow+1 == mousePosRow + 1) {

            try {
                Thread.sleep(2000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            mouse.setPosition(grid.makeGridPosition(2000, 2000));
            mouse.die();
            mouse = new Mouse(grid.makeGridPosition(20, 20));
            mouseLifes--;
            snake.die();

            for (Food food1: foods){
                food1.getPicFood().delete();
                food1.getPicPoop().delete();
            }
            foods.clear();

            foods.add(new Food(grid.makeGridPosition()));
            snake = new Snake(grid.makeGridPosition(4, 4));
            System.out.println("Mouse lifes left " + mouseLifes);
        }
    }

    public void checkSnakeEatFoodorPoop() {

        for (Food food: foods){
            food.getPosition();

            // check collision to food while its not poop
            if (snake.getPosition().getCol() == (food.getPosition().getCol())
                    && snake.getPosition().getRow() == (food.getPosition().getRow())
                    && !food.isPooped() || snake.getPosition().getCol()+1 == (food.getPosition().getCol())
                    && snake.getPosition().getRow()+1 == (food.getPosition().getRow())
                    && !food.isPooped() ) {
                food.snakeEat();
                foods.add(new Food(grid.makeGridPosition()));
                return;
            }
        }

        // check collision to poop
        for (Food food: foods) {
            if (snake.getPosition().getCol() == (food.getPosition().getCol())
                    && snake.getPosition().getRow() == (food.getPosition().getRow())
                    && food.isPooped() || snake.getPosition().getCol()+1 == (food.getPosition().getCol())
                    && snake.getPosition().getRow() +1== (food.getPosition().getRow())
                    && food.isPooped() ) {

                food.snakeEatPoop();

                try {
                    Thread.sleep(2000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                snake.setPosition(grid.makeGridPosition(2000, 2000));
                snake.die();
                snake = new Snake(grid.makeGridPosition(4, 4));
                snakeLifes--;
                mouse.die();

                for (Food food1: foods){
                    food1.getPicFood().delete();
                    food1.getPicPoop().delete();
                }
                foods.clear();
                mouse = new Mouse(grid.makeGridPosition(20, 20));

                foods.add(new Food(grid.makeGridPosition()));
                return;
            }
        }
    }

    public void detectMouseEatingFood() {

        for (Food food: foods) {
            food.getPosition();

            if (mouse.getPosition().getCol() == (food.getPosition().getCol())
                    && mouse.getPosition().getRow() == (food.getPosition().getRow())&& !food.isPooped()) {

                food.makePoop();
                food.foodOrPoop();

                foods.add(new Food(grid.makeGridPosition()));
                return;
            }
        }
    }

    public void drawCoins() {

        coins[0] = new Picture(625, 80, "img/coin.png");
        coins[0].draw();
        coins[1] = new Picture(625, 110, "img/coin.png");
        coins[1].draw();
        coins[2] = new Picture(625, 140, "img/coin.png");
        coins[2].draw();
        coins[3] = new Picture(625, 480, "img/coin.png");
        coins[3].draw();
        coins[4] = new Picture(625, 510, "img/coin.png");
        coins[4].draw();
        coins[5] = new Picture(625, 540, "img/coin.png");
        coins[5].draw();
    }

    public void eraseCoins() {
        if (mouseLifes == 2) {
            coins[2].delete();
        }
        if (mouseLifes == 1) {
            coins[1].delete();
        }
        if (mouseLifes == 0) {
            coins[0].delete();
        }

        if (snakeLifes == 2) {
            coins[5].delete();
        }
        if (snakeLifes == 1) {
            coins[4].delete();
        }
        if (snakeLifes == 0) {
            coins[3].delete();
        }
    }

    public void gameOver() {
        if (snakeLifes == 0 || mouseLifes == 0) {

            mouse.setPosition(grid.makeGridPosition(2000, 2000));
            mouse.die();
            snake.setPosition(grid.makeGridPosition(2000, 2000));
            snake.die();
            gameover.init();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.exit(0);
        }
    }
}