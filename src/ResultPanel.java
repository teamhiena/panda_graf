import javax.swing.*;
import java.awt.*;

class ResultPanel extends JPanel {
    GridLayout gridLayout = new GridLayout(3, 3);
    private GameFrame gf;
    private Game game;

    public void setGf(GameFrame gf) {
        this.gf = gf;
    }
    public void setGame(Game g){ game = g; }

    public void write(){
        this.setLayout(gridLayout);

        JLabel firstPlayerPanel = new JLabel("1. Player");
        add(firstPlayerPanel);
        firstPlayerPanel.setVisible(true);

        JLabel secondPlayerLabel = new JLabel("2. Player");
        add(secondPlayerLabel);
        secondPlayerLabel.setVisible(true);

        JLabel firstPlayerScore = new JLabel(Integer.toString(game.getOrangutans().get(0).getScore()));
        add(firstPlayerScore);
        firstPlayerScore.setVisible(true);

        if(game.getNumberofplayers() == 1) {
            JLabel secondPlayerScore = new JLabel("0");
            add(secondPlayerScore);
            secondPlayerScore.setVisible(true);

            JLabel firsPlayerWon = new JLabel("-");
            add(firsPlayerWon);
            firsPlayerWon.setVisible(true);

            JLabel secondPlayerWon = new JLabel("-");
            add(secondPlayerWon);
            secondPlayerWon.setVisible(true);
        }
        else{
            JLabel secondPlayerScore = new JLabel(Integer.toString(game.getOrangutans().get(1).getScore()));
            add(secondPlayerScore);
            secondPlayerScore.setVisible(true);
            if(game.getOrangutans().get(0).getScore() > game.getOrangutans().get(1).getScore() ){
                JLabel firsPlayerWon = new JLabel("You won!");
                add(firsPlayerWon);
                firsPlayerWon.setVisible(true);

                JLabel secondPlayerWon = new JLabel("-");
                add(secondPlayerWon);
                secondPlayerWon.setVisible(true);
            }
            else{
                JLabel firsPlayerWon = new JLabel("-");
                add(firsPlayerWon);
                firsPlayerWon.setVisible(true);

                JLabel secondPlayerWon = new JLabel("You won!");
                add(secondPlayerWon);
                secondPlayerWon.setVisible(true);
            }
        }

        gf.add(this);
        setVisible(true);
    }
}