package com.yu.snake;

import java.util.ArrayList;
import java.util.List;

/**
 * CREATED BY DY ON 2019/11/16.
 * TIME BY 17:22.
 **/
public class Snake {
    private List<GridSquare> SnakeBody;
    private int SnakeDirection;

    /**
     * @param SnakeHead      蛇头
     * @param SnakeDirection 方向
     */
    public Snake(GridSquare SnakeHead, int SnakeDirection) {
        SnakeBody = new ArrayList<>();
        SnakeHead.setType(GameType.SNAKE);
        SnakeBody.add(SnakeHead);
        this.SnakeDirection = SnakeDirection;
    }


    public void Move(GridSquare SnakeBody) {
        getSnakeTail().setType(GameType.GRID);
        this.SnakeBody.remove(getSnakeBody().get(getSnakeBody().size() - 1));
        SnakeBody.setType(GameType.SNAKE);
        this.SnakeBody.add(0, SnakeBody);
    }

//    public void MoveRight() {
//        if (getSnakeHead().getX() == SnakePanelView.food.getGridSquare().getX() && getSnakeHead().getY() == SnakePanelView.food.getGridSquare().getY()) {
//            GridSquare food = SnakePanelView.mGridSquare.get(SnakePanelView.food.getGridSquare().getX() + 1).get(SnakePanelView.food.getGridSquare().getY());
//            food.setType(GameType.SNAKE);
//            SnakeBody.add(0,food);
//            if (checkCollision != null) {
//                checkCollision.EatFood();
//            }
//        }else {
//            getSnakeTail().setType(GameType.GRID);
//            GridSquare snakeHead = SnakePanelView.mGridSquare.get(getSnakeHead().getX() + 1).get(getSnakeHead().getY());
//            snakeHead.setType(GameType.SNAKE);
//            this.SnakeBody.remove(getSnakeBody().get(getSnakeBody().size() - 1));
//            this.SnakeBody.add(0, snakeHead);
//        }
//        checkCollision();
//
//    }

//    public void MoveLeft() {
//        if (getSnakeHead().getX() == SnakePanelView.food.getGridSquare().getX() && getSnakeHead().getY() == SnakePanelView.food.getGridSquare().getY()) {
//            GridSquare food = SnakePanelView.mGridSquare.get(SnakePanelView.food.getGridSquare().getX() + 1).get(SnakePanelView.food.getGridSquare().getY());
//            food.setType(GameType.SNAKE);
//            SnakeBody.add(food);
//            if (checkCollision != null) {
//                checkCollision.EatFood();
//            }
//        }
//        checkCollision();
//        getSnakeTail().setType(GameType.GRID);
//        GridSquare snakeHead = SnakePanelView.mGridSquare.get(getSnakeHead().getX() - 1).get(getSnakeHead().getY());
//        snakeHead.setType(GameType.SNAKE);
//        this.SnakeBody.remove(getSnakeBody().get(getSnakeBody().size() - 1));
//        this.SnakeBody.add(0, snakeHead);
//
//    }

//    public void MoveUp() {
//        if (getSnakeHead().getX() == SnakePanelView.food.getGridSquare().getX() && getSnakeHead().getY() == SnakePanelView.food.getGridSquare().getY()) {
//            GridSquare food = SnakePanelView.mGridSquare.get(SnakePanelView.food.getGridSquare().getX()).get(SnakePanelView.food.getGridSquare().getY() + 1);
//            food.setType(GameType.SNAKE);
//            SnakeBody.add(food);
//            if (checkCollision != null) {
//                checkCollision.EatFood();
//            }
//        }
//        checkCollision();
//        getSnakeTail().setType(GameType.GRID);
//        GridSquare snakeHead = SnakePanelView.mGridSquare.get(getSnakeHead().getX()).get(getSnakeHead().getY() - 1);
//        snakeHead.setType(GameType.SNAKE);
//        this.SnakeBody.remove(getSnakeBody().get(getSnakeBody().size() - 1));
//        this.SnakeBody.add(0, snakeHead);
//
//    }

//    public void MoveDown() {
//        if (getSnakeHead().getX() == SnakePanelView.food.getGridSquare().getX() && getSnakeHead().getY() == SnakePanelView.food.getGridSquare().getY()) {
//            GridSquare food = SnakePanelView.mGridSquare.get(SnakePanelView.food.getGridSquare().getX()).get(SnakePanelView.food.getGridSquare().getY() - 1);
//            food.setType(GameType.SNAKE);
//            SnakeBody.add(food);
//            if (checkCollision != null) {
//                checkCollision.EatFood();
//            }
//        }
//        checkCollision();
//        getSnakeTail().setType(GameType.GRID);
//        GridSquare snakeHead = SnakePanelView.mGridSquare.get(getSnakeHead().getX()).get(getSnakeHead().getY() + 1);
//        snakeHead.setType(GameType.SNAKE);
//        this.SnakeBody.remove(getSnakeBody().get(getSnakeBody().size() - 1));
//        this.SnakeBody.add(0, snakeHead);
//
//    }

    public int getSnakeDirection() {
        return SnakeDirection;
    }

    public void setSnakeDirection(int snakeDirection) {
        SnakeDirection = snakeDirection;
    }

    public GridSquare getSnakeHead() {
        return SnakeBody.get(0);
    }

    public GridSquare getSnakeTail() {
        return SnakeBody.get(SnakeBody.size() - 1);
    }

    public List<GridSquare> getSnakeBody() {
        return SnakeBody;
    }

    public int getType() {
        return GameType.SNAKE;
    }

    public int getSnakeLength() {
        return SnakeBody.size();
    }

    public void EatFood(Food food){
        food.getGridSquare().setType(getType());
        SnakeBody.add(food.getGridSquare());
    }

}
