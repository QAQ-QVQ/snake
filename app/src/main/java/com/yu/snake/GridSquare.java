package com.yu.snake;

import android.content.Context;
import android.graphics.Color;


/**
 * 网格
 */
public class GridSquare {
    private int mType;//元素类型
    private int x;//x坐标
    private int y;//y坐标
    private Context context;

    /**
     * @param context 上下文
     * @param x       x坐标
     * @param y       y坐标
     * @param type    方格类型
     */
    public GridSquare(Context context,int x, int y, int type) {
        mType = type;
        this.x = x;
        this.y = y;
        this.context = context;
    }

    /**
     * @param x x坐标
     * @param y y坐标
     */
    public GridSquare(int x, int y) {
        this.x = x;
        this.y = y;
    }


    public GridSquare(){

    }
    public int getColor() {
        switch (mType) {
            case GameType.GRID://空格子
                return context.getResources().getColor(R.color.colorBox);
            case GameType.FOOD://食物
                return context.getResources().getColor(R.color.colorFood);
            case GameType.SNAKE://蛇
                return context.getResources().getColor(R.color.colorSnake);
        }
        return Color.WHITE;
    }

    public void setType(int type) {
        mType = type;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
