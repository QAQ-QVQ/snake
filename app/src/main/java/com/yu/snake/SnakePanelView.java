package com.yu.snake;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * CREATED BY DY ON 2019/11/16.
 * TIME BY 14:17.
 **/
public class SnakePanelView extends View {
    private static final String TAG = "SnakePanelView";
    private int mGridSizeX = 25;//x轴格子数目
    private int mGridSizeY = 25;//y轴格子数目
    private int mRectSize = 40;//格子尺寸
    private Paint mGridPaint = new Paint();//格子画笔
    private Paint mStrokePaint = new Paint();//边缘画笔
    private Long mSpeed = 300l;//速度
    private boolean mIsEndGame = false;//是否结束
    private static List<List<GridSquare>> mGridSquare;//存放格子的二维数组
    private Snake snake;
    private static Food food;
    // 起始点和偏移点
    private float startX, startY, offsetX, offsetY;

    public SnakePanelView(Context context) {
        super(context);
    }

    public SnakePanelView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SnakePanelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private class GameMainThread extends Thread {

        @Override
        public void run() {
            while (!mIsEndGame) {
                moveSnake(snake.getSnakeDirection());
                checkCollision();
                postInvalidate();//重绘界面
                handleSpeed();
            }

        }

        private void handleSpeed() {
            try {
                sleep(mSpeed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    GridSquare snakeHead;
    private void moveSnake(int snakeDirection) {
        switch (snakeDirection) {
            case GameType.LEFT:
                if (snake.getSnakeHead().getX() <= 0) {
                    mIsEndGame = true;
                    showMessageDialog();
                } else {
//                    snake.MoveLeft();
//                    GridSquare snakeHead;
                    if (snake.getSnakeHead().getX() == SnakePanelView.food.getGridSquare().getX() && snake.getSnakeHead().getY() == SnakePanelView.food.getGridSquare().getY()) {
                        snakeHead = mGridSquare.get(SnakePanelView.food.getGridSquare().getX() - 1).get(SnakePanelView.food.getGridSquare().getY());
                        generateFood();
                    } else {
                        snake.getSnakeTail().setType(GameType.GRID);
                        snakeHead = mGridSquare.get(snake.getSnakeHead().getX() - 1).get(snake.getSnakeHead().getY());
                        snake.getSnakeBody().remove(snake.getSnakeTail());
                    }
                    snakeHead.setType(GameType.SNAKE);
                    snake.getSnakeBody().add(0, snakeHead);
                }
                break;
            case GameType.UP:
                if (snake.getSnakeHead().getY() <= 0) {
                    mIsEndGame = true;
                    showMessageDialog();
                } else {
//                    snake.MoveUp();
                    if (snake.getSnakeHead().getX() == SnakePanelView.food.getGridSquare().getX() && snake.getSnakeHead().getY() == SnakePanelView.food.getGridSquare().getY()) {
                        snakeHead = mGridSquare.get(SnakePanelView.food.getGridSquare().getX()).get(SnakePanelView.food.getGridSquare().getY() - 1);
                        generateFood();
                    } else {
                        snake.getSnakeTail().setType(GameType.GRID);
                        snakeHead = mGridSquare.get(snake.getSnakeHead().getX()).get(snake.getSnakeHead().getY() - 1);
                        snake.getSnakeBody().remove(snake.getSnakeTail());
                    }
                    snakeHead.setType(GameType.SNAKE);
                    snake.getSnakeBody().add(0, snakeHead);
                }
                break;
            case GameType.RIGHT:
                if (snake.getSnakeHead().getX() + 1 >= mGridSizeX) {
                    mIsEndGame = true;
                    showMessageDialog();
                } else {
//                    snake.MoveRight();
                    if (snake.getSnakeHead().getX() == SnakePanelView.food.getGridSquare().getX() && snake.getSnakeHead().getY() == SnakePanelView.food.getGridSquare().getY()) {
                        snakeHead = mGridSquare.get(SnakePanelView.food.getGridSquare().getX() + 1).get(SnakePanelView.food.getGridSquare().getY());
                        generateFood();
                    } else {
                        snake.getSnakeTail().setType(GameType.GRID);
                        snakeHead = mGridSquare.get(snake.getSnakeHead().getX() + 1).get(snake.getSnakeHead().getY());
                        snake.getSnakeBody().remove(snake.getSnakeTail());
                    }
                    snakeHead.setType(GameType.SNAKE);
                    snake.getSnakeBody().add(0, snakeHead);
                }
                break;
            case GameType.DOWN:
                if (snake.getSnakeHead().getY() + 1 >= mGridSizeY) {
                    mIsEndGame = true;
                    showMessageDialog();
                } else {
//                    snake.MoveDown();
                    if (snake.getSnakeHead().getX() == SnakePanelView.food.getGridSquare().getX() && snake.getSnakeHead().getY() == SnakePanelView.food.getGridSquare().getY()) {
                        snakeHead = mGridSquare.get(SnakePanelView.food.getGridSquare().getX()).get(SnakePanelView.food.getGridSquare().getY() + 1);
                        generateFood();
                    } else {
                        snake.getSnakeTail().setType(GameType.GRID);
                        snakeHead = mGridSquare.get(snake.getSnakeHead().getX()).get(snake.getSnakeHead().getY() + 1);
                        snake.getSnakeBody().remove(snake.getSnakeTail());
                    }
                    snakeHead.setType(GameType.SNAKE);
                    snake.getSnakeBody().add(0, snakeHead);
                }
                break;
        }

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //格子的宽为屏幕宽除以格子数目以及左右各空出一格
        mGridSizeX = (w / mRectSize) - 2;
        mGridSizeY = (h / mRectSize) - 2;
        init();
    }

    private void init() {
        mGridSquare = new ArrayList<>();
        List<GridSquare> squares;
        for (int i = 0; i < mGridSizeX; i++) {
            squares = new ArrayList<>();
            for (int j = 0; j < mGridSizeY; j++) {
                squares.add(new GridSquare(getContext(), i, j, GameType.GRID));//设置每个格子的属性为空白
            }
            mGridSquare.add(squares);
        }
        mIsEndGame = true;
        showMessageDialog();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
        //格子画笔
        mGridPaint.reset();
        mGridPaint.setAntiAlias(true);
        mGridPaint.setStyle(Paint.Style.FILL);
        mGridPaint.setAntiAlias(true);
        //边缘画笔
        mStrokePaint.reset();
        mStrokePaint.setColor(getResources().getColor(R.color.colorRaw));
        mStrokePaint.setStyle(Paint.Style.STROKE);
        mStrokePaint.setAntiAlias(true);

        for (int i = 0; i < mGridSizeX; i++) {
            for (int j = 0; j < mGridSizeY; j++) {
                int left = (i + 1) * mRectSize;
                int top = (j + 1) * mRectSize;
                int right = (left + mRectSize);
                int bottom = (top + mRectSize);
                canvas.drawRect(left, top, right, bottom, mStrokePaint);
                mGridPaint.setColor(mGridSquare.get(i).get(j).getColor());//更新格子的颜色
                canvas.drawRect(left, top, right, bottom, mGridPaint);
            }
        }

    }

    /**
     * 游戏开始
     */
    public void StartGame() {
        if (!mIsEndGame) return;
        //重置所有网格
        for (int i = 0; i < mGridSizeX; i++) {
            for (int j = 0; j < mGridSizeY; j++) {
                mGridSquare.get(i).get(j).setType(GameType.GRID);
            }
        }
        snake = new Snake(mGridSquare.get(10).get(10), GameType.RIGHT);
//        snake.EatFood(new Food(mGridSquare.get(9).get(10)));
//        snake.EatFood(new Food(mGridSquare.get(8).get(10)));
//        snake.EatFood(new Food(mGridSquare.get(7).get(10)));
//        snake.EatFood(new Food(mGridSquare.get(6).get(10)));
//        snake.EatFood(new Food(mGridSquare.get(5).get(10)));
//        snake.EatFood(new Food(mGridSquare.get(4).get(10)));
//        snake.EatFood(new Food(mGridSquare.get(3).get(10)));
//        snake.EatFood(new Food(mGridSquare.get(2).get(10)));
//        snake.EatFood(new Food(mGridSquare.get(1).get(10)));
//        snake.EatFood(new Food(mGridSquare.get(0).get(10)));
        generateFood();
        postInvalidate();//重绘界面
        mIsEndGame = false;
        GameMainThread thread = new GameMainThread();
        thread.start();
    }

    /**
     * 生成food
     */
    private void generateFood() {
        int foodX = new Random().nextInt(mGridSizeX);
        int foodY = new Random().nextInt(mGridSizeY);
        for (int i = 0; i < snake.getSnakeBody().size() - 1; i++) {
            if (foodX == snake.getSnakeBody().get(i).getX() && foodY == snake.getSnakeBody().get(i).getY()) {
                //不能生成在蛇身上
                foodX = new Random().nextInt(mGridSizeX);
                foodY = new Random().nextInt(mGridSizeY);
                //重新循环
                i = 0;
            }
        }
        food = new Food(mGridSquare.get(foodX).get(foodY));
//        food = new Food(mGridSquare.get(15).get(10));
    }

    private void showMessageDialog() {
        post(new Runnable() {
            @Override
            public void run() {
                new AlertDialog.Builder(getContext()).setMessage("Game " + "Over!")
                        .setCancelable(false)
                        .setPositiveButton("开始", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                StartGame();
                            }
                        })
                        .setNegativeButton("退出", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                // TODO: 2019/12/9 退出操作待定
                            }
                        })
                        .create()
                        .show();
            }
        });
    }

    // 识别手势
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        /**
         * 交互逻辑 :我们其实只要知道两点，用户手指按下的坐标点和手指离开的坐标点，然后进行比对，就能识别出用户的意图了
         */
        switch (event.getAction()) {
            // 手指按下
            case MotionEvent.ACTION_DOWN:
                // 记录按下的x,y坐标
                startX = event.getX();
                startY = event.getY();
                break;
            // 手指离开
            case MotionEvent.ACTION_UP:
                // 手指离开之后计算偏移量(离开的位置-按下的位置在进行判断是往哪个方向移动)
                offsetX = event.getX() - startX;
                offsetY = event.getY() - startY;
                // 开始识别方向
                // offsetX 的绝对值大于offsetY的绝对值 说明在水平方向
                if (Math.abs(offsetX) > Math.abs(offsetY)) {
                    // (直接<0 会有些许误差，我们可以 <-5)
                    if (offsetX < -5) {
                        // 左
                        if (!(snake.getSnakeDirection() == GameType.RIGHT))
                            snake.setSnakeDirection(GameType.LEFT);
                    } else if (offsetX > 5) {
                        // 右
                        if (!(snake.getSnakeDirection() == GameType.LEFT))
                            snake.setSnakeDirection(GameType.RIGHT);
                    }
                    // 开始计算垂直方向上下的滑动
                } else {
                    if (offsetY < -5) {
                        // 上
                        if (!(snake.getSnakeDirection() == GameType.DOWN))
                            snake.setSnakeDirection(GameType.UP);
                    } else if (offsetY > 5) {
                        // 下
                        if (!(snake.getSnakeDirection() == GameType.UP))
                            snake.setSnakeDirection(GameType.DOWN);
                    }
                }
                break;
        }
        return true;
    }

    //检测碰撞
    private void checkCollision() {
        //检测是否咬到自己
        for (int i = 1; i <= snake.getSnakeBody().size() - 1; i++) {
            if (snake.getSnakeHead().getX() == snake.getSnakeBody().get(i).getX() && snake.getSnakeHead().getY() == snake.getSnakeBody().get(i).getY()) {
                //咬到自己 停止游戏
                mIsEndGame = true;
                showMessageDialog();
                return;
            }
        }
    }
}
