package com.yu.snake;

import java.util.List;

/**
 * CREATED BY DY ON 2020/5/27.
 * TIME BY 9:15.
 **/
public class GameManager {
    private Snake snake;
    private Food food;
    public static GameManager Instence;
    private static List<List<GridSquare>> mGridSquare;//存放格子的二维数组

    /**
     * 单例
     */
    public static GameManager getInstance() {
        if (Instence == null) {
            synchronized (GameManager.class) {
                if (Instence == null) {
                    Instence = new GameManager();
                }
            }
        }
        return Instence;
    }
}
