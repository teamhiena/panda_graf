import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ResultPanel extends JPanel {
    private Dimension size=new Dimension(400,400);
    GridLayout grid=new GridLayout(4,2);
    JButton bMainMenu=new JButton("Main menu");

    Menu menu;

    void setMenu(Menu m){menu=m;}

    public ResultPanel(){
        setPreferredSize(size);
        setLayout(grid);
        add(new JLabel("asd"));
        add(new JLabel("asd"));
        add(new JLabel("asd"));
        add(new JLabel("asd"));
        add(new JLabel("asd"));
        add(new JLabel("asd"));
        add(bMainMenu);

        bMainMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);

                menu.setVisible(true);
            }
        });
    }

    public void setLabel(int row, int col, String text){
        
    }
}