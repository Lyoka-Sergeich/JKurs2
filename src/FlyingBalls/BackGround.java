package FlyingBalls;

import java.awt.*;
import java.util.Random;

public class BackGround extends Sprite {  //задний фон поверх канвы

    Random rnd = new Random();

    long lastChangeTime = System.nanoTime();  //начинаем отсчёт времени   //время последнего изменения цвета
    private Color color;   //цвет, разумеется, финальным не будет, в отличие от мячей

    void putBackGround(GameCanvas canvas){  //накладываем фон
        x = canvas.getWidth() / 2f;
        y = canvas.getHeight() / 2f;
        halfHeight = canvas.getHeight() / 2f;
        halfWidth = canvas.getWidth() / 2f;
    }

    @Override
    void render(GameCanvas canvas, Graphics g) {

        //нововведение
        long currentTime = System.nanoTime();                            //засекаем время
        if((currentTime - lastChangeTime)* 0.000000001f > 5f){  //каждые 5 с
            color = new Color(rnd.nextInt());                    //меняем цвет
            lastChangeTime = currentTime;                        //и начинаем новый отсчёт интервала
        }
        //в общих чертах похоже на мяч
        g.setColor(color);
        g.fillRect((int) getLeft(), (int) getTop(),
                (int) getWidth(), (int) getHeight());
    }
/*
    //перепишем метод рисования, поскольку теперь в нём участвует ещё и цвет
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        long currentTime = System.nanoTime();                            //засекаем время
        float deltaTime = (currentTime - lastFrameTime) * 0.000000001f;  //прошедшее время
        lastFrameTime = currentTime;
        //нововведение
        if((currentTime - lastChangeTime)* 0.000000001f > 10f){  //каждые 10 с
            color = new Color(rnd.nextInt());                    //меняем цвет
            lastChangeTime = currentTime;                        //и начинаем новый отсчёт интервала
        }
        controller.onDrawFrame(this, g, deltaTime);
        try {                                    //бездействуем, остановив поток
            Thread.sleep(17); // ~60 fps
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        repaint();                               //перерисовываем (библиотечная)
    }

 */
}
