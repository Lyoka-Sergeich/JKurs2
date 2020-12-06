package FlyingBalls;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainCircles extends JFrame {  //главное окно, на котором будем всё располагать

    //координаты и габариты
    private static final int POS_X = 400;
    private static final int POS_Y = 200;
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;

    //набор рисуемых объектов для работы
    Sprite[] sprites = new Sprite[10];
    BackGround backG = new BackGround();
    int numCircles;  //число шариков


    public static void main(String[] args) {  //точка входа
        new MainCircles();  //создать себя
    }

    private MainCircles() {  //конструктор
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  //завершаем по закрытию
        setBounds(POS_X, POS_Y, WINDOW_WIDTH, WINDOW_HEIGHT);     //применяем геометрию
        setTitle("Circles");                                      //озаглавливаем
        GameCanvas canvas = new GameCanvas(this);  //создадим холст, назначив себя контролёром
        initApplication();                                 //создадим рисуемые объекты
        add(canvas);                                       //вставим холст - единственный объект на всё окно
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                update(e);
            }
        });
        setVisible(true);   //только в этот момент канва обретает не нулевые значения габаритов
    }

    private void initApplication() {
        for (int i = 0; i < sprites.length; i++) {
            sprites[i] = new Ball();
        }

    }

    public void onDrawFrame(GameCanvas canvas, Graphics g, float deltaTime) {
        update(canvas, deltaTime);
        render(canvas, g);
    }

    private void update(GameCanvas canvas, float deltaTime) {
        for (int i = 0; i < numCircles/*sprites.length*/; i++) {
            sprites[i].update(canvas, deltaTime);
        }
    }

    private void render(GameCanvas canvas, Graphics g) {
        if(this.isVisible()){   //форма фидима и канва приняла очертания
            backG.putBackGround(canvas);
        }
        backG.render(canvas, g);  //задний фон рисуется первым
        for (int i = 0; i < numCircles/*sprites.length*/; i++) {
            sprites[i].render(canvas, g);
        }
    }

    //обработка клика мыши
    void update(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1){
            if(numCircles < 10)   numCircles++;
        }
        if(e.getButton() == MouseEvent.BUTTON3){
            if(numCircles > 0)   numCircles--;
        }
    }
}
