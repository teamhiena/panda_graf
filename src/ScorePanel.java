import javax.swing.*;
import java.awt.*;

public class ScorePanel extends JPanel {
    private GameMap gm;
    private Game g;
    private View v;
    private Timer timer;
    protected JLabel time1ab;
    public ScorePanel(GameMap gamemap, Game game, View view, Timer t){
        super();
        gm = gamemap;
        g = game;
        v = view;
        timer = t;
        JLabel score1 = new JLabel("play1");
        score1.setPreferredSize(new Dimension(300, 50));
        /*String scoreboard2 = "";
        if(g.getOrangutans().size() ==2)
            scoreboard2 += "Player2: "; // + g.getOrangutans().get(1).getScore();*/
        JLabel score2 = new JLabel("");
        score2.setPreferredSize(new Dimension(300, 50));
        String time = "" + timer.getTime() ; //kene valami idő getter
        JLabel time1ab = new JLabel("ido");
        time1ab.setPreferredSize(new Dimension(300, 50));
        setLayout(new BorderLayout());
        score1.setText("Player1: " + g.getOrangutans().get(0).getScore());
        score2.setText("Player2: ");
        time1ab.setText("" + timer.getTime());
        add(score1, BorderLayout.WEST);
        add(score2, BorderLayout.EAST);
        add(time1ab, BorderLayout.CENTER);

        repaint();
    }
}
