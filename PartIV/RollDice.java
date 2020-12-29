package PartIV;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RollDice extends JFrame {

	private JPanel mainPanel;
	private JPanel controlPanel;
	private JButton rollButton;
	private ImagePanel die1Panel;
	private ImagePanel die2Panel;
	private int die1Value = 1;
	private int die2Value = 1;
	private Image[] diceImages = new Image[6];
	private final String file = "PartIV/";
	
	
	public RollDice() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBackground(Color.LIGHT_GRAY);
		
		for (int i=0;i<6;i++) {
			diceImages[i] = new ImageIcon(file + "die"+(i+1) +".png").getImage();
		}
		
		setupPanels();
		add(mainPanel, BorderLayout.CENTER);
		add(controlPanel, BorderLayout.SOUTH);
		setSize(500,250);
		setVisible(true);
	}
	
	private void rollDie(int die) {
		/* it's probably smarter to have the image panels
		 * in an arraylist to support more than just two
		 * dice, but we'll go with this for now.
		 */
		Random rand = new Random();
		ArrayList<Integer> al = new ArrayList<Integer>();
		ImagePanel p = null;
		if (die == 1) {
			p = die1Panel;
		} else if (die == 2) {
			p = die2Panel;
		}
		int numberOfRolls = rand.nextInt(20) + 1;
		int nextDieNumber = -1;
		for (int i = 0; i < numberOfRolls; i++) {
			nextDieNumber = rand.nextInt(6);
			al.add(nextDieNumber);
		}
		if (die == 1) {
			die1Value = nextDieNumber;
			die1Panel.setSequence(al); 
		} else if (die == 2) {
			die2Value = nextDieNumber;
			die2Panel.setSequence(al); 
		}
		
	}
	
	private void rollBothDice() {
		Random rand = new Random();
		int numberOfRolls = rand.nextInt(20) + 1;
		int nextDie1Number = -1;
		int nextDie2Number = -1;
		ArrayList<Integer> al1 = new ArrayList<Integer>();
		ArrayList<Integer> al2 = new ArrayList<Integer>();
		
		for (int i = 0; i < numberOfRolls; i++) {
			nextDie1Number = rand.nextInt(6);
			nextDie2Number = rand.nextInt(6);
			al1.add(nextDie1Number);
			al2.add(nextDie2Number);
			
		}
		System.out.println("setting die1 sequence to " + al1);
		System.out.println("setting die2 sequence to " + al2);
		die1Panel.setSequence(al1);
		die2Panel.setSequence(al2);
		
	}
	
	public class RollDiceListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			rollBothDice();
			
		}
		
	}
	
	class ImagePanelMouseListener implements MouseListener {

		private int die;
		
		public ImagePanelMouseListener(int die) {
			this.die = die;
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			rollDie(this.die);
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public void setupPanels() {
		JLabel resultLabel = new JLabel("Result: ");
		JPanel resultPanel = new JPanel();
		resultPanel.add(resultLabel);
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(1,2));
		controlPanel = new JPanel();
		controlPanel.setLayout(new GridLayout(2,1));
		die1Panel = new ImagePanel("die1.png");
		die2Panel = new ImagePanel("die1.png");
		die1Panel.addMouseListener(new ImagePanelMouseListener(1));
		die2Panel.addMouseListener(new ImagePanelMouseListener(2));
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		p1.add(die1Panel);
		p2.add(die2Panel);
		mainPanel.add(die1Panel);
		mainPanel.add(die2Panel);
		rollButton = new JButton("Roll Dice");
		rollButton.addActionListener(new RollDiceListener());
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(rollButton);
		controlPanel.add(resultPanel);
		controlPanel.add(buttonPanel);
		
	}

	public static void main(String[] args) {
		RollDice rollDice = new RollDice();
	
	}
}
