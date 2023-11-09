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


        if(running){
            //dikey ve yatay çizgiler için desenleri çizmek için for döngüsü.
            for(int i = 0; i < SCREEN_HEIGH / UNIT_SIZE; i++){
                g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGH);
                g.drawLine(0, i*UNIT_SIZE, SCREEN_WIDTH*1, i*UNIT_SIZE);
            }


            g.setColor(Color.red);
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

            // yılanın görününümü
            for(int i = 0; i < bodyPart; i++){
                if( i==0){
                    g.setColor(Color.green);
                    g.fillRect(x[i],y[i],UNIT_SIZE,UNIT_SIZE);
                }else{
                    g.setColor(new Color(45,180,0));
                    g.fillRect(x[i],y[i],UNIT_SIZE,UNIT_SIZE);
                }
            }

            // skor tutma.
            g.setColor(Color.red);
            g.setFont( new Font("Ink Free",Font.BOLD,40));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Score: " + applesEaten, (SCREEN_WIDTH- metrics.stringWidth("Score: " + applesEaten))/2, g.getFont().getSize()  );

        }else{
            gameOver(g);
        }

    }

    public void newApple(){
        // rastgele bölgelerde çıkması için.
        appleX = random.nextInt((int)(SCREEN_WIDTH / UNIT_SIZE))*UNIT_SIZE;
        appleY  =random.nextInt((int)(SCREEN_HEIGH /UNIT_SIZE))*UNIT_SIZE;

    }
    public void move(){
        for (int i = bodyPart ; i > 0 ; i--){
            x[i] = x[i-1]; // bir önceki konum ile değişir.-yatay
            y[i] = y[i-1]; // bir önceki konum ile değişir.-dikey
        }
        switch (direction){
            case 'U':
                y[0] = y[0] - UNIT_SIZE;
                break;
            case 'D':
                y[0] = y[0] + UNIT_SIZE;
                break;
            case 'L':
                x[0] = x[0] - UNIT_SIZE;
                break;
            case 'R':
                x[0] =x[0] + UNIT_SIZE;
                break;


        }

    }
    public void checkApple(){
        if((x[0] == appleX) && (y[0] == appleY )){
            bodyPart++;
            applesEaten++;
            newApple();
        }

    }


    public void checkCollections(){

        // yılanın kendine çarpıp çarpmadığı kontrol edilir.
        for(int i = bodyPart; i > 0 ; i--){
            if((x[0] == x[i]) && (y[0] == y[i])){
                running = false;
            }
        }
        // sol sınıra çarpma ihtimali.
        if(x[0] < 0 ){
            running = false;
        }
        // sağ sınıra çarpma ihtimali.
        if(x[0] > SCREEN_WIDTH ){
            running = false;
        }
        // üst sınıra çarpma  ihtimali.
        if(y[0] < 0){
            running = false;
        }
        // alt sınıra çarpma ihtimali.
        if(y[0] > SCREEN_HEIGH){
            running = false;
        }
        if(!running){
            timer.stop();
        }



    }
    public void gameOver(Graphics g){
        //oyun bittiğinde skorun gözükmesi için.
        g.setColor(Color.red);
        g.setFont( new Font("Ink Free",Font.BOLD,40));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Score: " + applesEaten, (SCREEN_WIDTH- metrics1.stringWidth("Score: " + applesEaten))/2, g.getFont().getSize()  );


        // Game Over metni.
        g.setColor(Color.red);
        g.setFont( new Font("Ink Free",Font.BOLD,75));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Game Over", (SCREEN_WIDTH- metrics2.stringWidth("Game Over"))/2, SCREEN_HEIGH/2  );

    }
    public class MyKeyAdapter extends KeyAdapter{

        @Override
        public void keyPressed(KeyEvent e){
            switch (e.getKeyCode()){
                case KeyEvent.VK_LEFT:
                    if(direction != 'R' ){
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if(direction != 'L' ){
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if(direction != 'D' ){
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if(direction != 'U' ){
                        direction = 'D';
                    }
                    break;

            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(running){
            move();
            checkApple();
            checkCollections();
        }
        repaint();

    }
}
