package com.yu.snake;

/**
 * CREATED BY DY ON 2019/11/18.
 * TIME BY 10:58.
 **/
public class Food {

    private GridSquare gridSquare;

    public GridSquare getGridSquare() {
        return gridSquare;
    }

    public Food(GridSquare gridSquare) {
        gridSquare.setType(GameType.FOOD);
        this.gridSquare = gridSquare;
    }
}
