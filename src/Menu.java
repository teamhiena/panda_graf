import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * A jatekhoz tartozo menut megvalosito osztaly.
 */
public class Menu extends JFrame{

	boolean gameModePicked=false;
	boolean nrOfPlayersPicked=false;
	Timer timer;
	public Menu(Game g, GameFrame f, Timer t) {

		timer = t;
		setContentPane(new JLabel(new ImageIcon("png/veryredpanda.png")));
		getContentPane().setBounds(0,0,400,400);

		JButton bStart =new JButton("Start Game");
		JButton bFinitTime = new JButton("Finit Time");
		//JButton bFinitPanda =new JButton("Finit Panda");
		JButton b1Player=new JButton("1 Player");
		JButton b2Players=new JButton("2 Players");

		bStart.setEnabled(false);

		/*bStart.setBounds(145,80,100, 60);
		bFinitTime.setBounds(145,180,100, 60);
		//bFinitPanda.setBounds(210,180,100, 60);
		b1Player.setBounds(80,280, 100, 60);
		b2Players.setBounds(210,280,100,60);*/

		bStart.setBounds(150,80,100, 40);
		bFinitTime.setBounds(20,20,100, 40);
		//bFinitPanda.setBounds(210,180,100, 60);
		b1Player.setBounds(150,20, 100, 40);
		b2Players.setBounds(280,20,100,40);


		bStart.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				f.getGp().loadMap("pandaMap.txt");

				setVisible(false);
				f.setVisible(true);

				if(g.getNumberofplayers() == 1)
				{
					f.getV().getDrawables().remove(g.getOrangutans().get(1));
					g.getOrangutans().remove(g.getOrangutans().get(1));
				}
				timer.setRunning(true);
			}
		});

		bFinitTime.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				bFinitTime.setEnabled(false);
				//bFinitPanda.setEnabled(true);
				gameModePicked=true;
				if(nrOfPlayersPicked)
					bStart.setEnabled(true);
			}
		});

		/*bFinitPanda.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				g.setSelectedMode(Game.GameMode.FinitPanda);
				bFinitTime.setEnabled(true);
				bFinitPanda.setEnabled(false);
				gameModePicked=true;
				if(nrOfPlayersPicked)
					bStart.setEnabled(true);
			}
		});*/

		b1Player.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				g.setNumberofplayers(1);
				b1Player.setEnabled(false);
				b2Players.setEnabled(true);
				nrOfPlayersPicked=true;
				if(gameModePicked)
					bStart.setEnabled(true);
			}
		});

		b2Players.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				g.setNumberofplayers(2);
				b1Player.setEnabled(true);
				b2Players.setEnabled(false);
				nrOfPlayersPicked=true;
				if(gameModePicked)
					bStart.setEnabled(true);
			}
		});

		add(bStart);
		add(bFinitTime);
		//add(bFinitPanda);
		add(b1Player);
		add(b2Players);
		setSize(400,400);
		setLayout(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

