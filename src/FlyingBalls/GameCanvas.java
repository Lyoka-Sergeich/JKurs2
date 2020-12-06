package FlyingBalls;

import javax.swing.*;
import java.awt.*;

public class GameCanvas extends JPanel {

    long lastFrameTime;
    MainCircles controller;

    GameCanvas(MainCircles controller) {    //конструктор
        lastFrameTime = System.nanoTime();  //начинаем отсчёт времени
        this.controller = controller;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        long currentTime = System.nanoTime();                            //засекаем время
        float deltaTime = (currentTime - lastFrameTime) * 0.000000001f;  //прошедшее время
        lastFrameTime = currentTime;
        controller.onDrawFrame(this, g, deltaTime);
        try {                                    //бездействуем, остановив поток
            Thread.sleep(17); // ~60 fps
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        repaint();                               //перерисовываем (библиотечная)
    }

    //функции запроса свойств
    public int getLeft() { return 0; }
    public int getRight() { return getWidth() - 1; }
    public int getTop() { return 0; }
    public int getBottom() { return getHeight() - 1; }
}
