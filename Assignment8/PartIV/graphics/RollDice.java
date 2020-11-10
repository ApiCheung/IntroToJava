package Assignment8.PartIV.graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RollDice extends JFrame {
	private final String file = "Assignment8/PartIV/graphics/die";
	public int d1 = 2, d2 = 2;
	JLabel label;
	ImagePanel dice1;
	ImagePanel dice2;
	JPanel mainPanel;
	JButton button;
	JPanel other;




	public RollDice() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBackground(Color.LIGHT_GRAY);
		setupPanels();
		setSize(400, 300);
		setVisible(true);
		this.setLayout(new BorderLayout());
	}

	public void setupPanels(){
		class ButtonAction implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				d1 = (int)(Math.random()*6)+1;
				d2 = (int)(Math.random()*6)+1;

				dice1.repaintDice(d1);
				dice2.repaintDice(d2);
				System.out.println(d1);
				System.out.println(d2);
				label.setText("Result is " + (d1 + d2));

			}
		}



		mainPanel= new JPanel(new GridLayout(1,2));
		other = new JPanel(new GridLayout(1,2));
		dice1 = new ImagePanel(file + d1 + ".png");
		dice2 = new ImagePanel(file + d2 + ".png");
		mainPanel.add(dice1);
		mainPanel.add(dice2);
		other = new JPanel();
		button = new JButton("Roll dice");
		button.addActionListener(new ButtonAction());
		label = new JLabel();
		label.setText("Result is " + (d1 + d2));

		other.add(label);
		other.add(button);

		this.add(mainPanel, BorderLayout.CENTER);
		this.add(other, BorderLayout.SOUTH);

;	}




	public static void main(String[] args) {
		RollDice rollDice = new RollDice();
	
	}
}
