import javax.swing.*;

public class GameFrame extends JFrame {
    GameFrame(){

        this.add(new GamePanel());
        this.setTitle("Snake Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack(); // pencere boyutunu içerikler uyumlu hale getirir.
        this.setVisible(true);
        this.setLocationRelativeTo(null); // pencereyi ekranın ortasında yerleştirmek için kullanılır.

    }
}
