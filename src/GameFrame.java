import javax.swing.JFrame;

public class GameFrame extends JFrame implements Constants {

    GameFrame(){
        this.setSize(GAME_WIDTH,GAME_HEIGHT);
        setTitle(GAME_TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new Board());
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        GameFrame frame = new GameFrame();




    }

}
