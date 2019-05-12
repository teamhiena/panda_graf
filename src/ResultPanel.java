import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class ResultPanel extends JPanel {
    private Dimension size=new Dimension(400,400);
    private GridLayout grid=new GridLayout(4,2);
    private JButton bMainMenu=new JButton("Main menu");
    private GameFrame gf;

    public void setGf(GameFrame gf) {
        this.gf = gf;
    }

    Menu menu;

    private ArrayList<JLabel> labels = new ArrayList<JLabel>();

    void setMenu(Menu m){menu=m;}

    public ResultPanel(){
        setPreferredSize(size);
        for(int i=0;i<6;i++)
            labels.add(new JLabel("asd"));

        add(bMainMenu);

        bMainMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gf.setVisible(false);
                menu.setVisible(true);
            }
        });
    }

    public JLabel getLabel(int n){
        return labels.get(n);
    }
}