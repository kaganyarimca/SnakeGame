import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;


public class GamePanel extends JPanel implements ActionListener {

    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGH = 600;
    static final int UNIT_SIZE = 25;

    static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGH ) / UNIT_SIZE ;
    static final  int   DELAY = 75;
    final int[] x = new int[GAME_UNITS];
    final int[] y = new int[GAME_UNITS];
    int bodyPart = 6;
    int applesEaten = 0;
    int appleX;
    int appleY;


    char direction = 'R'; // R = right, L = left, U = up, D = down
    boolean running = false;
    Timer timer;
    Random random;

    GamePanel(){
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGH)); // pencere veya boyut ayarlamak için.
        this.setBackground(Color.BLACK);
        this.setFocusable(true); // Klavye ve fare etkileşimleri kullanımı için.
        this.addKeyListener(new MyKeyAdapter());
        startGame();


    }
    //method
    public void startGame(){
        newApple();
        running  = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }
    // method
    public void paintComponent(Graphics g){
    super.paintComponent(g);
    draw(g);
    }


    public void draw(Graphics g){

        //dikey ve yatay çizgiler için desenleri çizmek için for döngüsü.
        for(int i = 0; i < SCREEN_HEIGH / UNIT_SIZE; i++){
            g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGH);
            g.drawLine(0, i*UNIT_SIZE, SCREEN_WIDTH*1, i*UNIT_SIZE);
        }

        g.setColor(Color.red);
        g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

    }
    public void newApple(){
        appleX = random.nextInt((int)(SCREEN_WIDTH / UNIT_SIZE))*UNIT_SIZE;
        appleY  =random.nextInt((int)(SCREEN_HEIGH /UNIT_SIZE))*UNIT_SIZE;

    }
    public void move(){
        for (int i = bodyPart ; i>0 ; i--){
            x[i] = x[i-1];
        }

    }
    public void checkApple(){

    }
    public void checkCollections(){

    }
    public void gameOver(Graphics g){

    }
    public class MyKeyAdapter extends KeyAdapter{

        @Override
        public void keyPressed(KeyEvent e){

        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
