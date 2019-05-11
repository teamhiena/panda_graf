import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Menu extends JFrame{

	public Menu(Game g, GameFrame f) {
		
		JButton bStart =new JButton("Start Game");  
		JButton bFinitTime = new JButton("Finit Time");  
		JButton bFinitPanda =new JButton("Finit Panda");  
		
		bStart.setEnabled(false);
		
		bStart.setBounds(150,80,100, 60); 
		bFinitTime.setBounds(80,180,100, 60); 
		bFinitPanda.setBounds(210,180,100, 60);
		
		bStart.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
		             setVisible(false);
		             f.setVisible(true);
		         }  
		    });
 
		bFinitTime.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
					g.setSelectedMode(Game.GameMode.FinitTime);
					bFinitTime.setEnabled(false);
					bFinitPanda.setEnabled(true);
					bStart.setEnabled(true);
		         }  
		    });
		
		bFinitPanda.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				g.setSelectedMode(Game.GameMode.FinitPanda);
				bFinitTime.setEnabled(true);
				bFinitPanda.setEnabled(false);
				bStart.setEnabled(true);
		         }  
		    });
  
		add(bStart);
		add(bFinitTime);
		add(bFinitPanda);
		setSize(400,400);
		setLayout(null);    
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

