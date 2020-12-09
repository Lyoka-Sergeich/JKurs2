package Hwk4;
/*
Отправлять сообщения в лог по нажатию кнопки или по нажатию клавиши Enter.
Создать лог в файле (показать комментарием, где и как Вы планируете писать сообщение в файловый журнал).
*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public class ClientGUI extends JFrame implements ActionListener,  //слушает кнопки
        KeyListener,  //слушает клавиши
        Thread.UncaughtExceptionHandler {

    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    //константы опций записи в файл
    private static final int WRITE = 1;
    private static final int APPEND = 2;

    private final JTextArea log = new JTextArea();

    private final JPanel panelTop = new JPanel(new GridLayout(2, 3));
    private final JTextField tfIPAddress = new JTextField("127.0.0.1");
    private final JTextField tfPort = new JTextField("8189");
    private final JCheckBox cbAlwaysOnTop = new JCheckBox("Always on top");
    private final JTextField tfLogin = new JTextField("ivan");
    private final JPasswordField tfPassword = new JPasswordField("123");
    private final JButton btnLogin = new JButton("Login");

    private final JPanel panelBottom = new JPanel(new BorderLayout());
    private final JButton btnDisconnect = new JButton("<html><b>Disconnect</b></html>");
    private final JTextField tfMessage = new JTextField();
    private final JButton btnSend = new JButton("Send");

    private final JList<String> userList = new JList<>();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ClientGUI();
            }
        });
    }

    private ClientGUI() {
        Thread.setDefaultUncaughtExceptionHandler(this);  //используем отлавливатель исключений по умолчанию
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(WIDTH, HEIGHT);
        log.setEditable(false);
        JScrollPane scrollLog = new JScrollPane(log);
        JScrollPane scrollUsers = new JScrollPane(userList);
        String[] users = {"user1", "user2", "user3", "user4", "user5", "user6",
                            "user_with_an_exceptionally_long_nickname"};
        userList.setListData(users);
        scrollUsers.setPreferredSize(new Dimension(100, 0));
        cbAlwaysOnTop.addActionListener(this);

        panelTop.add(tfIPAddress);
        panelTop.add(tfPort);
        panelTop.add(cbAlwaysOnTop);
        panelTop.add(tfLogin);
        panelTop.add(tfPassword);
        panelTop.add(btnLogin);
        panelBottom.add(btnDisconnect, BorderLayout.WEST);
        panelBottom.add(tfMessage, BorderLayout.CENTER);
        panelBottom.add(btnSend, BorderLayout.EAST);
        //добавим слушателей для строки сообщений и кнопки передачи сообщения
        btnSend.addActionListener(this);
        tfMessage.addKeyListener(this);
        writeLogInFile(WRITE);              //обнулим файл лога

        add(scrollLog, BorderLayout.CENTER);
        add(scrollUsers, BorderLayout.EAST);
        add(panelTop, BorderLayout.NORTH);
        add(panelBottom, BorderLayout.SOUTH);



        setVisible(true);
    }
    //используя интерфейс ActionListener, переопределяем методы
    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src == cbAlwaysOnTop) {        //отметка "всегда наверху"
            setAlwaysOnTop(cbAlwaysOnTop.isSelected());
        }else if (src == btnSend) {        //кнопка "отправить сообщение"
            log.append(tfMessage.getText() + "\n");  //пишем в лог
            writeLogInFile(APPEND);                  //пишем в файл
            tfMessage.setText("");                   //после отправки очистим для новой записи
        }
        else {
            throw new RuntimeException("Undefined source: " + src);
        }
    }

    //используя интерфейс KeyListener, переопределяем методы
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            log.append(tfMessage.getText() + "\n");  //пишем в лог
            writeLogInFile(APPEND);                  //пишем в файл
            tfMessage.setText("");                   //после отправки очистим для новой записи
        }
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        e.printStackTrace();
        StackTraceElement[] ste = e.getStackTrace();
        String msg = String.format("Exception in thread \"%s\" %s: %s\n\tat %s",
                t.getName(), e.getClass().getCanonicalName(),
                e.getMessage(), ste[0]);
        JOptionPane.showMessageDialog(this, msg, "Exception", JOptionPane.ERROR_MESSAGE);
        System.exit(1);
    }

    void writeLogInFile(int mode){
        //ЗАПИСЬ СООБЩЕНИЯ В ФАЙЛ
        //поток и массив для передачи в файл
        FileOutputStream fos = null;
        byte[] b;
        //открываем
        try {
            if(mode == WRITE) fos = new FileOutputStream("SaveLogFile.txt");                //пишем с нуля
            if(mode == APPEND) fos = new FileOutputStream("SaveLogFile.txt", true);  //дописываем
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
            JOptionPane.showMessageDialog(this, "Can't open file!",
                    "Exception", JOptionPane.ERROR_MESSAGE);
        }
        //запись в файл
        b = ((tfMessage.getText() + "\n").toString()).getBytes();
        try {
            fos.write(b);
        } catch (IOException ioException) {
            ioException.printStackTrace();
            JOptionPane.showMessageDialog(this, "Can't write in file!",
                    "Exception", JOptionPane.ERROR_MESSAGE);
        }
        //закрываем
        try {
            fos.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
            JOptionPane.showMessageDialog(this, "Can't close file!",
                    "Exception", JOptionPane.ERROR_MESSAGE);
        }
    }
}
