package FinalProjectSkeleton.src.game;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;
import javax.swing.border.Border;

/**
 * @author Esmee Zhang
 * @date 12/13/20 6:24 下午
 * @projectName IntroToJava-NYU
 */

public class YahtzeeFrame extends JFrame {

	JMenuBar menuBar;
	JMenu menu;
	JMenuItem loadGame, saveGame, exit;
	JPanel namePanel, gameNamePanel;
	JLabel nameLabel;
	JTextField nameTextField;
	JPanel upperSectionPanel;
	JButton acesButton, twosButton, threesButton, foursButton, fivesButton, sixesButton;
	JLabel scoreLabel, bonusLabel, uppertotalLabel;
	JTextArea acesTextArea, twosTextArea, threesTextArea, foursTextArea, fivesTextArea, sixesTextArea,
	          scoreTextArea, bonusTextArea, uppertotalTextArea;
	JPanel acesPanel, twosPanel, threesPanel, foursPanel, fivesPanel, sixesPanel,
	       scorePanel, bonusPanel, uppertotalPanel;
	JPanel lowerSectionPanel;
	JButton threeButton, fourButton, fullButton, smallButton, largeButton, yahtzeeButton, chanceButton;
	JLabel yBonusLabel, lowerSectotalLabel, lowerTotalLabel;
	JTextArea threeTextArea, fourTextArea, fullTextArea, smallTextArea, largeTextArea, yahtzeeTextArea,
			chanceArea, yBonusTextArea, lowerSectotalTextArea, lowerTotalTextArea;
	JPanel threePanel, fourPanel, fullPanel, smallPanel, largePanel, yahtzeePanel, chancePanel,
			yBonusPanel, lowerSectotalPanel, lowerTotalPanel;

	JPanel gamePanel;
	JPanel gameResPanel, dicePanel;
	ImagePanel dice1, dice2, dice3, dice4, dice5;
	JCheckBox keep1, keep2, keep3, keep4, keep5;
	JLabel turnLabel, rollLabel;
	JButton rollButton;
	Date date;

	Socket socket;
	ObjectOutputStream toServer;

	SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd-hh:mm:ss");

	public static final String host = "localhost";

	//public static final int totalRound = 13;
	//public static final int totalRoll = 3;
	int round = 1;
	int rollNum = 0;
	//int rollLabelNum = 1;
	//int turnLabelNum = 1;

	int scoreSubTal = 0;
	int upperbonus = 0;
	int grandTal = 0;
	int yahtzeeBonus = 0;
	int lowerSectionTal = 0;


	int d1 = 1, d2 = 1, d3 = 1, d4 = 1, d5 = 1;
	boolean[] diceState = new boolean[]{true, true, true, true, true};
	boolean[] upperBoolean = new boolean[]{true, true, true, true, true, true};
	int[] upper = new int[]{0, 0, 0, 0, 0, 0, 0};
	boolean[] lowerBoolean = new boolean[]{true, true, true, true, true, true, true};
	int[] lower = new int[]{0, 0, 0, 0, 0, 0, 0, 0};

	private final String file = "FinalProjectSkeleton/die";
	OutputStream load;

	
	public YahtzeeFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(420,930);

		menuBar = new JMenuBar();
		menuBar.add(createMenus());
		this.setJMenuBar(menuBar);

		gameNamePanel = new JPanel();
		createNamePanel();
		gameNamePanel.add(namePanel);

		gamePanel = new JPanel();

		gameResPanel = new JPanel(new GridLayout(2, 1));
		createUpperSectionPanel();
		gameResPanel.add(upperSectionPanel);

		createLowerSectionPanel();
		gameResPanel.add(lowerSectionPanel);

		createDicePanel();
		gamePanel.add(gameResPanel);
		gamePanel.add(dicePanel, BorderLayout.EAST);
		this.add(gameNamePanel, BorderLayout.NORTH);
		this.add(gamePanel);

	}

	private JMenu createMenus(){

		menu = new JMenu("Game");
		loadGame = new JMenuItem("Load Game");
		saveGame = new JMenuItem("Save Game");
		exit = new JMenuItem("Exit");

		class MenuExitListener implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent actionEvent) {

				System.exit(0);
			}
		}

		class LoadGameListener implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent actionEvent) {


				JPanel control = new JPanel();
				JFrame select = new JFrame();
				JTextArea textQueryArea = new JTextArea(20, 40);
				JPanel namePane = new JPanel();
				JPanel timePane = new JPanel();
				JPanel nameAndTime = new JPanel();
				JPanel textPanel = new JPanel();
				JScrollPane scrollPane = new JScrollPane(textQueryArea);
				JLabel nameLabel = new JLabel("Player Name: ");
				JLabel timeLabel = new JLabel("Time: ");
				JTextField queryName = new JTextField(10);
				JTextField queryTime = new JTextField(10);
				JButton load = new JButton("Load Game");


				namePane.add(nameLabel);
				namePane.add(queryName);

				timePane.add(timeLabel);
				timePane.add(queryTime);

				nameAndTime.setLayout(new BoxLayout(nameAndTime, BoxLayout.Y_AXIS));
				nameAndTime.add(namePane);
				nameAndTime.add(timePane);
				nameAndTime.add(load);

				textPanel.add(scrollPane);
				textQueryArea.setEditable(false);

				control.setLayout(new BoxLayout(control, BoxLayout.Y_AXIS));
				control.add(nameAndTime);
				control.add(textPanel);

				select.add(control);

				select.setSize(600, 400);
				//select.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				select.setVisible(true);

				try{

					socket = new Socket(host, 8000);
					String loadGame = new String("loadGame");
					toServer = new ObjectOutputStream(socket.getOutputStream());
					toServer.writeObject(loadGame);
					System.out.println("--------");

					ObjectInputStream fromServerPlayerRes = new ObjectInputStream(socket.getInputStream());
					Object res = fromServerPlayerRes.readObject();
					String result = (String) res;

					//System.out.println(result);
					textQueryArea.setText(result);
					//fixme
					//socket.close();


				}
				catch(IOException ex){
					System.out.println(ex);
				}
				catch(ClassNotFoundException ex) {
					ex.printStackTrace();
				}


				load.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent actionEvent) {

						try{
							socket = new Socket(host, 8000);
							String loadSelect = new String("loadSelect");
							toServer = new ObjectOutputStream(socket.getOutputStream());

							if(!queryName.getText().equals("") && !queryTime.getText().equals("")){

								String queryTimeAndDate = queryName.getText().trim() + " " + queryTime.getText().trim();
								toServer.writeObject(loadSelect + " " + queryTimeAndDate);
								System.out.println("----------");
								ObjectInputStream finalfromServer = new ObjectInputStream(socket.getInputStream());
//


									//System.out.println("sadkfbha");
								Object game = finalfromServer.readObject();

								Game finalGame = (Game) game;
								//System.out.println(finalGame.getPlayerName());
								if(finalGame.getPlayerName() == null){
									JOptionPane.showMessageDialog(null,"No Record");
								}
								nameTextField.setText(finalGame.getPlayerName());
								if (finalGame.getAces() != 0) {
									upper[0] = finalGame.getAces();
									upperBoolean[0] = false;
									acesTextArea.setText(String.valueOf(upper[0]));
								} else {
									upper[0] = 0;
									acesTextArea.setText(String.valueOf(upper[0]));
								}
								if (finalGame.getTwos() != 0) {
									upper[1] = finalGame.getTwos();
									upperBoolean[1] = false;
									twosTextArea.setText(String.valueOf(upper[1]));
								} else {
									upper[1] = 0;
									twosTextArea.setText(String.valueOf(upper[1]));
								}
								if (finalGame.getThrees() != 0) {
									upper[2] = finalGame.getThrees();
									upperBoolean[2] = false;
									threesTextArea.setText(String.valueOf(upper[2]));
								} else {
									upper[2] = 0;
									threesTextArea.setText(String.valueOf(upper[2]));
								}
								if (finalGame.getFours() != 0) {
									upper[3] = finalGame.getFours();
									upperBoolean[3] = false;
									foursTextArea.setText(String.valueOf(upper[3]));
								} else {
									upper[3] = 0;
									foursTextArea.setText(String.valueOf(upper[3]));
								}
								if (finalGame.getFives() != 0) {
									upper[4] = finalGame.getFives();
									upperBoolean[4] = false;
									fivesTextArea.setText(String.valueOf(upper[4]));
								} else {
									upper[4] = 0;
									fivesTextArea.setText(String.valueOf(upper[4]));
								}
								if (finalGame.getFives() != 0) {
									upper[5] = finalGame.getSixes();
									upperBoolean[5] = false;
									sixesTextArea.setText(String.valueOf(upper[5]));
								} else {
									upper[5] = 0;
									upper[5] = 0;
									sixesTextArea.setText(String.valueOf(upper[5]));
								}

								scoreTextArea.setText(String.valueOf(finalGame.getScoreTotal()));
								bonusTextArea.setText(String.valueOf(finalGame.getBonus()));
								uppertotalTextArea.setText(String.valueOf(finalGame.getGrandTotal()));

								if (finalGame.getThreeOfKind() != 0) {
									lower[0] = finalGame.getThreeOfKind();
									lowerBoolean[0] = false;
									threeTextArea.setText(String.valueOf(lower[0]));
								} else {
									lower[0] = 0;
									threeTextArea.setText(String.valueOf(lower[0]));
								}
								if (finalGame.getFourOfKind() != 0) {
									lower[1] = finalGame.getFourOfKind();
									lowerBoolean[1] = false;
									fourTextArea.setText(String.valueOf(lower[1]));
								} else {
									lower[1] = 0;
									fourTextArea.setText(String.valueOf(lower[1]));
								}
								if (finalGame.getFullHouse() != 0) {
									lower[2] = finalGame.getFullHouse();
									lowerBoolean[2] = false;
									fullTextArea.setText(String.valueOf(lower[2]));
								} else {
									lower[2] = 0;
									fullTextArea.setText(String.valueOf(lower[2]));
								}
								if (finalGame.getSmallHouse() != 0) {
									lower[3] = finalGame.getSmallHouse();
									lowerBoolean[3] = false;
									smallTextArea.setText(String.valueOf(lower[3]));
								} else {
									lower[3] = 0;
									smallTextArea.setText(String.valueOf(lower[3]));
								}
								if (finalGame.getLargeStraight() != 0) {
									lower[4] = finalGame.getLargeStraight();
									lowerBoolean[4] = false;
									largeTextArea.setText(String.valueOf(lower[4]));
								} else {
									lower[4] = 0;
									largeTextArea.setText(String.valueOf(lower[4]));
								}
								if (finalGame.getYahtzee() != 0) {
									lower[5] = finalGame.getYahtzee();
									lowerBoolean[5] = false;
									yahtzeeTextArea.setText(String.valueOf(lower[5]));
								} else {
									lower[5] = 0;
									yahtzeeTextArea.setText(String.valueOf(lower[5]));
								}
								if (finalGame.getChance() != 0) {
									lower[6] = finalGame.getChance();
									lowerBoolean[6] = false;
									chanceArea.setText(String.valueOf(lower[6]));
								} else {
									lower[6] = 0;
									chanceArea.setText(String.valueOf(lower[6]));
								}
								yBonusTextArea.setText(String.valueOf(finalGame.getYahtzeeBonus()));
								lowerSectotalTextArea.setText(String.valueOf(finalGame.getTotalLower()));
								lowerTotalTextArea.setText(String.valueOf(finalGame.getGrandTotal()));

								turnLabel.setText("Turn: " + String.valueOf(finalGame.getRound()));
								rollLabel.setText("Roll: " + String.valueOf(finalGame.getRoll()));


								socket.close();





							}else{
								JOptionPane.showMessageDialog(null,"All feild must be filled");
							}
						}catch (IOException e){
							System.out.println(e);
						}catch (ClassNotFoundException ex){
							System.out.println(ex);
						}

					}
				});



			}

		}

		class SaveGameListener implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent actionEvent) {

				if(nameTextField.getText().equals("")){
					JOptionPane.showMessageDialog(null,"Name must be filled");
				}else{

					int[] saveupper = new int[6];
					int[] savelower = new int[7];
					for(int i = 0; i < 6; i ++){
						if(upperBoolean[i] == false){
							saveupper[i] = upper[i];
						}else{
							saveupper[i] = 0;
						}
					}
					for(int i = 0; i < 7; i++){
						if(lowerBoolean[i] == false){
							savelower[i] = lower[i];
						}else{
							savelower[i] = 0;
						}
					}
					int savetal = calScoreSubTal() + calLowerTal();

					Game game = new Game(nameTextField.getText(), ft.format((date = new Date())).toString(), saveupper[0], saveupper[1],
							saveupper[2], saveupper[3], saveupper[4], saveupper[5], calScoreSubTal(),
							Integer.parseInt(bonusTextArea.getText()), savelower[0], savelower[1], savelower[2],
							savelower[3], savelower[4], savelower[5], savelower[6],
							(yBonusTextArea.getText()).equals("") ? 0 : Integer.parseInt(yBonusTextArea.getText()),
							calLowerTal(), savetal, rollNum, round);

					try {
						socket = new Socket(host, 8000);

						toServer = new ObjectOutputStream(socket.getOutputStream());

						toServer.writeObject(game);
						//socket.close();
					}catch (IOException ex){
						ex.printStackTrace();
					}
				}
			}
		}

		ActionListener exitListener = new MenuExitListener();
		ActionListener loadGameListener = new LoadGameListener();
		ActionListener saveGameListener = new SaveGameListener();

		exit.addActionListener(exitListener);
		loadGame.addActionListener(loadGameListener);
		saveGame.addActionListener(saveGameListener);

		menu.add(loadGame);
		menu.add(saveGame);
		menu.add(exit);

		return menu;
	}

	private JPanel createNamePanel(){

		namePanel = new JPanel();
		nameLabel = new JLabel("Player Name: ");
		nameTextField = new JTextField(25);
		namePanel.add(nameLabel);
		namePanel.add(nameTextField);

		return namePanel;
	}

	private JPanel createUpperSectionPanel(){
		Border upperBorderTitle = BorderFactory.createTitledBorder("Upper Section");
		upperSectionPanel = new JPanel(new GridLayout(9,1));
		upperSectionPanel.setBorder(upperBorderTitle);

		acesButton = new JButton("Aces");

		acesButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				acesButton.setEnabled(false);
				if(rollNum != 3) {
					round++;
					turnLabel.setText("Turn: " + round);
					rollNum = 0;
				}
				rollButton.setEnabled(true);
				for(boolean b : diceState){
					b = true;
				}
				keep1.setSelected(false);
				keep2.setSelected(false);
				keep3.setSelected(false);
				keep4.setSelected(false);
				keep5.setSelected(false);
				if((acesTextArea.getText()).equals("")){
					upper[0] = 0;
				}else {
					upper[0] = Integer.parseInt((acesTextArea.getText()));
				}
				upperBoolean[0] = false;
				rollNum = 0;
				Arrays.fill(diceState, true);
			}
		});

		acesTextArea = new JTextArea(1, 6);
		acesPanel = new JPanel(new FlowLayout());
		acesPanel.add(acesButton);
		acesPanel.add(acesTextArea);
		upperSectionPanel.add(acesPanel);

		twosButton = new JButton("Twos");
		twosButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				twosButton.setEnabled(false);
				if(rollNum != 3) {
					round++;
					turnLabel.setText("Turn: " + round);
					rollNum = 0;
				}
				rollButton.setEnabled(true);
				for(boolean b : diceState){
					b = true;
				}
				keep1.setSelected(false);
				keep2.setSelected(false);
				keep3.setSelected(false);
				keep4.setSelected(false);
				keep5.setSelected(false);
				if((twosTextArea.getText()).equals("")){
					upper[1] = 0;
				}else{
					upper[1] = Integer.parseInt(twosTextArea.getText());
				}

				upperBoolean[1] = false;
				rollNum = 0;
				Arrays.fill(diceState, true);
			}
		});

		twosTextArea = new JTextArea(1, 6);
		twosPanel = new JPanel();
		twosPanel.add(twosButton);
		twosPanel.add(twosTextArea);
		upperSectionPanel.add(twosPanel);

		threesButton = new JButton("Threes");
		threesButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				threesButton.setEnabled(false);

				if(rollNum != 3) {
					round++;
					turnLabel.setText("Turn: " + round);
					rollNum = 0;
				}
				rollButton.setEnabled(true);
				for(boolean b : diceState){
					b = true;
				}
				keep1.setSelected(false);
				keep2.setSelected(false);
				keep3.setSelected(false);
				keep4.setSelected(false);
				keep5.setSelected(false);
				if((threesTextArea.getText()).equals("")){
					upper[2] = 0;
				}else{
					upper[2] = Integer.parseInt(threesTextArea.getText());
				}

				upperBoolean[2] = false;
				rollNum = 0;
				Arrays.fill(diceState, true);
			}
		});

		threesTextArea = new JTextArea(1, 6);
		threesPanel = new JPanel();
		threesPanel.add(threesButton);
		threesPanel.add(threesTextArea);
		upperSectionPanel.add(threesPanel);

		foursButton = new JButton("Fours");
		foursButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				foursButton.setEnabled(false);

				if(rollNum != 3) {
					round++;
					turnLabel.setText("Turn: " + round);
					rollNum = 0;
				}

				rollButton.setEnabled(true);
				for(boolean b : diceState){
					b = true;
				}
				keep1.setSelected(false);
				keep2.setSelected(false);
				keep3.setSelected(false);
				keep4.setSelected(false);
				keep5.setSelected(false);
				if((foursTextArea.getText()).equals("")){
					upper[3] = 0;
				}else{
					upper[3] = Integer.parseInt(foursTextArea.getText());
				}

				upperBoolean[3] = false;
				rollNum = 0;
				Arrays.fill(diceState, true);
			}
		});

		foursTextArea = new JTextArea(1, 6);
		foursPanel = new JPanel();
		foursPanel.add(foursButton);
		foursPanel.add(foursTextArea);
		upperSectionPanel.add(foursPanel);

		fivesButton = new JButton("fives");
		fivesButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				fivesButton.setEnabled(false);
				if(rollNum != 3) {
					round++;
					turnLabel.setText("Turn: " + round);
					rollNum = 0;
				}
				rollButton.setEnabled(true);
				for(boolean b : diceState){
					b = true;
				}
				keep1.setSelected(false);
				keep2.setSelected(false);
				keep3.setSelected(false);
				keep4.setSelected(false);
				keep5.setSelected(false);
				if((fivesTextArea.getText()).equals("")){
					upper[4] = 0;
				}else{
					upper[4] = Integer.parseInt(fivesTextArea.getText());
				}

				upperBoolean[4] = false;
				rollNum = 0;
				Arrays.fill(diceState, true);
			}
		});

		fivesTextArea = new JTextArea(1, 6);
		fivesPanel = new JPanel();
		fivesPanel.add(fivesButton);
		fivesPanel.add(fivesTextArea);
		upperSectionPanel.add(fivesPanel);

		sixesButton = new JButton("sixes");
		sixesButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				sixesButton.setEnabled(false);

				if(rollNum != 3) {
					round++;
					turnLabel.setText("Turn: " + round);
					rollNum = 0;
				}

				rollButton.setEnabled(true);
				for(boolean b : diceState){
					b = true;
				}
				keep1.setSelected(false);
				keep2.setSelected(false);
				keep3.setSelected(false);
				keep4.setSelected(false);
				keep5.setSelected(false);
				if((sixesTextArea.getText()).equals("")){
					upper[5] = 0;
				}else{
					upper[5] = Integer.parseInt(sixesTextArea.getText());
				}

				upperBoolean[5] = false;
				rollNum = 0;
				Arrays.fill(diceState, true);
			}
		});

		sixesTextArea = new JTextArea(1, 6);
		sixesPanel = new JPanel();
		sixesPanel.add(sixesButton);
		sixesPanel.add(sixesTextArea);
		upperSectionPanel.add(sixesPanel);

		scoreLabel = new JLabel("Score Subtotal");
		scoreTextArea = new JTextArea(1, 6);
		scorePanel = new JPanel();
		scorePanel.add(scoreLabel);
		scorePanel.add(scoreTextArea);
		upperSectionPanel.add(scorePanel);

		bonusLabel = new JLabel("Bonus");
		bonusTextArea = new JTextArea(1, 6);
		bonusPanel = new JPanel();
		bonusPanel.add(bonusLabel);
		bonusPanel.add(bonusTextArea);
		upperSectionPanel.add(bonusPanel);

		uppertotalLabel = new JLabel("Grand Total");
		uppertotalTextArea = new JTextArea(1, 6);
		uppertotalPanel = new JPanel();
		uppertotalPanel.add(uppertotalLabel);
		uppertotalPanel.add(uppertotalTextArea);
		upperSectionPanel.add(uppertotalPanel);




		return upperSectionPanel;
	}

	private JPanel createLowerSectionPanel(){
		Border lowerBorderTitle = BorderFactory.createTitledBorder("Lower Section");
		lowerSectionPanel = new JPanel(new GridLayout(10, 1));
		lowerSectionPanel.setBorder(lowerBorderTitle);

		threeButton = new JButton("3 of a kind");
		threeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				threeButton.setEnabled(false);

				if(rollNum != 3) {
					round++;
					turnLabel.setText("Turn: " + round);
					rollNum = 0;
				}

				rollButton.setEnabled(true);
				for(boolean b : diceState){
					b = true;
				}
				keep1.setSelected(false);
				keep2.setSelected(false);
				keep3.setSelected(false);
				keep4.setSelected(false);
				keep5.setSelected(false);

				lower[0] = Integer.parseInt(threeTextArea.getText());
				lowerBoolean[0] = false;
				rollNum = 0;
				Arrays.fill(diceState, true);
			}
		});

		threeTextArea = new JTextArea(1, 6);
		threePanel = new JPanel();
		threePanel.add(threeButton);
		threePanel.add(threeTextArea);
		lowerSectionPanel.add(threePanel);

		fourButton = new JButton("4 of a kind");
		fourButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				fourButton.setEnabled(false);
				if(rollNum != 3) {
					round++;
					turnLabel.setText("Turn: " + round);
					rollNum = 0;
				}
				rollButton.setEnabled(true);
				for(boolean b : diceState){
					b = true;
				}
				keep1.setSelected(false);
				keep2.setSelected(false);
				keep3.setSelected(false);
				keep4.setSelected(false);
				keep5.setSelected(false);
				lower[1] = Integer.parseInt(fourTextArea.getText());
				lowerBoolean[1] = false;
				rollNum = 0;
				Arrays.fill(diceState, true);
			}
		});

		fourTextArea = new JTextArea(1, 6);
		fourPanel = new JPanel();
		fourPanel.add(fourButton);
		fourPanel.add(fourTextArea);
		lowerSectionPanel.add(fourPanel);

		fullButton = new JButton("Full House");
		fullButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				fullButton.setEnabled(false);
				if(rollNum != 3) {
					round++;
					turnLabel.setText("Turn: " + round);
					rollNum = 0;
				}
				rollButton.setEnabled(true);
				for(boolean b : diceState){
					b = true;
				}
				keep1.setSelected(false);
				keep2.setSelected(false);
				keep3.setSelected(false);
				keep4.setSelected(false);
				keep5.setSelected(false);
				lower[2] = Integer.parseInt(fullTextArea.getText());
				lowerBoolean[2] = false;
				rollNum = 0;
				Arrays.fill(diceState, true);
			}
		});

		fullTextArea = new JTextArea(1, 6);
		fullPanel = new JPanel();
		fullPanel.add(fullButton);
		fullPanel.add(fullTextArea);
		lowerSectionPanel.add(fullPanel);

		smallButton = new JButton("Small Straight");
		smallButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				smallButton.setEnabled(false);
				if(rollNum != 3) {
					round++;
					turnLabel.setText("Turn: " + round);
					rollNum = 0;
				}
				rollButton.setEnabled(true);
				for(boolean b : diceState){
					b = true;
				}
				keep1.setSelected(false);
				keep2.setSelected(false);
				keep3.setSelected(false);
				keep4.setSelected(false);
				keep5.setSelected(false);
				lower[3] = Integer.parseInt(smallTextArea.getText());
				lowerBoolean[3] = false;
				rollNum = 0;
				Arrays.fill(diceState, true);
			}
		});

		smallTextArea = new JTextArea(1, 6);
		smallPanel = new JPanel();
		smallPanel.add(smallButton);
		smallPanel.add(smallTextArea);
		lowerSectionPanel.add(smallPanel);

		largeButton = new JButton("Large Straight");
		largeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				largeButton.setEnabled(false);
				if(rollNum != 3) {
					round++;
					turnLabel.setText("Turn: " + round);
					rollNum = 0;
				}
				rollButton.setEnabled(true);
				for(boolean b : diceState){
					b = true;
				}
				keep1.setSelected(false);
				keep2.setSelected(false);
				keep3.setSelected(false);
				keep4.setSelected(false);
				keep5.setSelected(false);
				lower[4] = Integer.parseInt(largeTextArea.getText());
				lowerBoolean[4] = false;
				rollNum = 0;
				Arrays.fill(diceState, true);
			}
		});

		largeTextArea = new JTextArea(1, 6);
		largePanel = new JPanel();
		largePanel.add(largeButton);
		largePanel.add(largeTextArea);
		lowerSectionPanel.add(largePanel);

		yahtzeeButton = new JButton("Yahtzee");
		yahtzeeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				yahtzeeButton.setEnabled(false);
				if(rollNum != 3) {
					round++;
					turnLabel.setText("Turn: " + round);
					rollNum = 0;
				}
				rollButton.setEnabled(true);
				for(boolean b : diceState){
					b = true;
				}
				keep1.setSelected(false);
				keep2.setSelected(false);
				keep3.setSelected(false);
				keep4.setSelected(false);
				keep5.setSelected(false);
				lower[5] = Integer.parseInt(yahtzeeTextArea.getText());
				lowerBoolean[5] = false;
				rollNum = 0;
				Arrays.fill(diceState, true);
			}
		});

		yahtzeeTextArea = new JTextArea(1, 6);
		yahtzeePanel = new JPanel();
		yahtzeePanel.add(yahtzeeButton);
		yahtzeePanel.add(yahtzeeTextArea);
		lowerSectionPanel.add(yahtzeePanel);

		chanceButton = new JButton("Chance");
		chanceButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				chanceButton.setEnabled(false);
				if(rollNum != 3) {
					round++;
					turnLabel.setText("Turn: " + round);
					rollNum = 0;
				}
				rollButton.setEnabled(true);
				for(boolean b : diceState){
					b = true;
				}
				keep1.setSelected(false);
				keep2.setSelected(false);
				keep3.setSelected(false);
				keep4.setSelected(false);
				keep5.setSelected(false);
				lower[6] = Integer.parseInt(chanceArea.getText());
				lowerBoolean[6] = false;
				rollNum = 0;
				Arrays.fill(diceState, true);
			}
		});

		chanceArea = new JTextArea(1, 6);
		chancePanel = new JPanel();
		chancePanel.add(chanceButton);
		chancePanel.add(chanceArea);
		lowerSectionPanel.add(chancePanel);

		yBonusLabel = new JLabel("Yahtzee Bonus");
		yBonusTextArea = new JTextArea(1, 6);
		yBonusPanel = new JPanel();
		yBonusPanel.add(yBonusLabel);
		yBonusPanel.add(yBonusTextArea);
		lowerSectionPanel.add(yBonusPanel);

		lowerSectotalLabel = new JLabel("Total of lower section");
		lowerSectotalTextArea = new JTextArea(1, 6);
		lowerSectotalPanel = new JPanel();
		lowerSectotalPanel.add(lowerSectotalLabel);
		lowerSectotalPanel.add(lowerSectotalTextArea);
		lowerSectionPanel.add(lowerSectotalPanel);

		lowerTotalLabel = new JLabel("Grand Total");
		lowerTotalTextArea = new JTextArea(1, 6);
		lowerTotalPanel = new JPanel();
		lowerTotalPanel.add(lowerTotalLabel);
		lowerTotalPanel.add(lowerTotalTextArea);
		lowerSectionPanel.add(lowerTotalPanel);



		return lowerSectionPanel;

	}

	private JPanel createDicePanel(){

		dicePanel = new JPanel();
		dicePanel.setLayout(new BoxLayout(dicePanel, BoxLayout.Y_AXIS));

		dice1 = new ImagePanel(file + d1 + ".png");
		dice1.scaleImage(0.65);
		dice2 = new ImagePanel(file + d2 + ".png");
		dice2.scaleImage(0.65);
		dice3 = new ImagePanel(file + d3 + ".png");
		dice3.scaleImage(0.65);
		dice4 = new ImagePanel(file + d4 + ".png");
		dice4.scaleImage(0.65);
		dice5 = new ImagePanel(file + d5 + ".png");
		dice5.scaleImage(0.65);

		keep1 = new JCheckBox("Keep");
		keep1.addActionListener(actionEvent -> {
			diceState[0] = false;
			keep1.setSelected(true);
		});

		keep2 = new JCheckBox("Keep");
		keep2.addActionListener(actionEvent -> {
			diceState[1] = false;
			keep2.setSelected(true);
		});

		keep3 = new JCheckBox("Keep");
		keep3.addActionListener(actionEvent -> {
			diceState[2] = false;
			keep3.setSelected(true);
		});

		keep4 = new JCheckBox("Keep");
		keep4.addActionListener(actionEvent -> {
			diceState[3] = false;
			keep4.setSelected(true);
		});

		keep5 = new JCheckBox("Keep");
		keep5.addActionListener(actionEvent -> {
			diceState[4]= false;
			keep5.setSelected(true);
		});

		turnLabel = new JLabel("Turn: " + round);
		rollLabel = new JLabel("Roll: " + rollNum);

		rollButton = new JButton("Roll");
		rollButton.addActionListener(new rollMethod());

		dicePanel.add(dice1);
		dicePanel.add(keep1);
		dicePanel.add(dice2);
		dicePanel.add(keep2);
		dicePanel.add(dice3);
		dicePanel.add(keep3);
		dicePanel.add(dice4);
		dicePanel.add(keep4);
		dicePanel.add(dice5);
		dicePanel.add(keep5);
		dicePanel.add(turnLabel);
		dicePanel.add(rollLabel);
		dicePanel.add(rollButton);

		return dicePanel;
	}

	public class rollMethod implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent actionEvent) {

		if(round < 14) {
			if (rollNum < 4) {

				if(diceState[0] == true) {
					d1 = (int) (Math.random() * 6) + 1;
				}
				if(diceState[1] == true) {
					d2 = (int) (Math.random() * 6) + 1;
				}
				if(diceState[2] == true) {
					d3 = (int) (Math.random() * 6) + 1;
				}
				if(diceState[3] == true) {
					d4 = (int) (Math.random() * 6) + 1;
				}
				if(diceState[4] == true) {
					d5 = (int) (Math.random() * 6) + 1;
				}

				dice1.repaintDice(d1);
				dice2.repaintDice(d2);
				dice3.repaintDice(d3);
				dice4.repaintDice(d4);
				dice5.repaintDice(d5);
				rollNum ++;

				//aces
				int[] dices = new int[]{d1, d2, d3, d4, d5};
				Arrays.sort(dices);
				int[] nums = new int[]{0, 0, 0, 0, 0, 0};
				int[] lowerNums = new int[]{0, 0, 0, 0, 0, 0, 0};

				//lower
				Map<Integer, Integer> map = new HashMap<>();

				for(int d : dices){
					map.put(d, map.get(d) == null ? 1 : map.get(d) + 1);
				}

				if(acesButton.isEnabled()) {
					nums[0] = map.getOrDefault(1, 0) * 1;
				}
				if(twosButton.isEnabled()) {
					nums[1] = map.getOrDefault(2, 0) * 2;
				}
				if(threeButton.isEnabled()) {
					nums[2] = map.getOrDefault(3, 0) * 3;
				}
				if(fourButton.isEnabled()) {
					nums[3] = map.getOrDefault(4, 0) * 4;
				}
				if(fivesButton.isEnabled()) {
					nums[4] = map.getOrDefault(5, 0) * 5;
				}
				if(sixesButton.isEnabled()) {
					nums[5] = map.getOrDefault(6, 0) * 6;
				}

				int upperToal = calScoreSubTal();
				if(upperToal == 63){
					upperbonus = 35;
				}else{
					upperbonus = 0;
				}

				for(int key : map.keySet()){
					if(threeButton.isEnabled()) {
						if (map.get(key) == 3) {
							for (int i : dices) {
								lowerNums[0] += i;
							}
						}
					}
					if(fourButton.isEnabled()) {
						if (map.get(key) == 4) {
							for (int i : dices) {
								lowerNums[1] += i;
							}
						}
					}
					if(yahtzeeButton.isEnabled()) {
						if (map.get(key) == 5) {
							if (!yahtzeeButton.isEnabled()) {
								yahtzeeBonus = 100;
							} else {
								lowerNums[5] = 50;
							}
						}
					}
				}
				if(fullButton.isEnabled()) {
					if (map.containsValue(2) && map.containsValue(3)) {
						lowerNums[2] = 25;
					}
				}


				if(smallButton.isEnabled()){
					if((map.containsKey(1) && map.containsKey(2) && map.containsKey(3) && map.containsKey(4))
					||(map.containsKey(2) && map.containsKey(3) && map.containsKey(4) && map.containsKey(5))
					||(map.containsKey(3) && map.containsKey(4) && map.containsKey(5) && map.containsKey(6))){
						lowerNums[3] = 30;
					}
				}

				//large house
				if(largeButton.isEnabled()) {
					if ((dices[0] == 1 && dices[1] == 2 && dices[2] == 3 && dices[3] == 4 && dices[4] == 5)
							|| (dices[0] == 2 && dices[1] == 3 && dices[2] == 4 && dices[3] == 5 && dices[4] == 6)) {
						lowerNums[4] = 40;
					}
				}

				if(chanceButton.isEnabled()) {
					for (int i : dices) {
						lowerNums[6] += i;
					}
				}


				grandTal = scoreSubTal + lowerSectionTal;

				if(upperBoolean[0] == true) {
					acesTextArea.setText(Integer.toString(nums[0]));
				}
				if(upperBoolean[1] == true) {
					twosTextArea.setText(Integer.toString(nums[1]));
				}
				if(upperBoolean[2] == true) {
					threesTextArea.setText(Integer.toString(nums[2]));
				}
				if(upperBoolean[3] == true) {
					foursTextArea.setText(Integer.toString(nums[3]));
				}
				if(upperBoolean[4] == true){
					fivesTextArea.setText(Integer.toString(nums[4]));
				}
				if(upperBoolean[5] == true) {
					sixesTextArea.setText(Integer.toString(nums[5]));
				}

				scoreTextArea.setText(Integer.toString(calScoreSubTal()));
				bonusTextArea.setText(Integer.toString(upperbonus));
				uppertotalTextArea.setText(Integer.toString(calScoreSubTal() + calLowerTal()));

				if(lowerBoolean[0] == true) {
					threeTextArea.setText(Integer.toString(lowerNums[0]));
				}
				if(lowerBoolean[1] == true) {
					fourTextArea.setText(Integer.toString(lowerNums[1]));
				}
				if(lowerBoolean[2] == true) {
					fullTextArea.setText(Integer.toString(lowerNums[2]));
				}
				if(lowerBoolean[3] == true) {
					smallTextArea.setText(Integer.toString(lowerNums[3]));
				}
				if(lowerBoolean[4] == true) {
					largeTextArea.setText(Integer.toString(lowerNums[4]));
				}
				if(lowerBoolean[5] == true) {
					yahtzeeTextArea.setText(Integer.toString(lowerNums[5]));
				}
				if(lowerBoolean[6] == true) {
					chanceArea.setText(Integer.toString(lowerNums[6]));
				}

				yahtzeeTextArea.setText(Integer.toString(yahtzeeBonus));
				lowerSectotalTextArea.setText(Integer.toString(calLowerTal()));
				lowerTotalTextArea.setText(Integer.toString(calScoreSubTal() + calLowerTal()));


				rollLabel.setText("Roll: " + rollNum);
			}

			if (rollNum == 3 && round < 14) {
				rollButton.setEnabled(false);
				rollNum = 0;
				//round ++;
				turnLabel.setText("Turn: " + round);

			}

		}

		}



	}
	public int calScoreSubTal(){
		int subTotal = 0;
		for(int i = 0; i < 6; i++){
			if(upperBoolean[i] == false){
				subTotal += upper[i];
			}
		}
		return subTotal;
	}

	public int calLowerTal(){
		int subToal = 0;
		for(int i = 0; i < 7; i++){
			if(lowerBoolean[i] == false){
				subToal += lower[i];
			}
		}
		return subToal;
	}

	public static void main(String args[]) {
		YahtzeeFrame yahtzee = new YahtzeeFrame();
		yahtzee.setVisible(true);
	}
}
