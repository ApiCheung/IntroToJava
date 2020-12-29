package FinalProjectSkeleton.src.server;

import FinalProjectSkeleton.src.game.Game;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.*;

/**
 * @author Esmee Zhang
 * @date 12/14/20 1:48 下午
 * @projectName IntroToJava-NYU
 */


public class SaveServer extends JFrame {

	private ObjectOutputStream outputToFile;
	private ObjectInputStream  inputFromClient;

	private JTextArea wordsBox;
	private String CONNECTION_DB = "/Users/esmeezhang/Documents/GitHub/IntroToJava-NYU/" +
			"FinalProjectSkeleton/yahtzee.db";
	private Connection conn;
	private PreparedStatement queryName;
	private PreparedStatement queryInsert;
	private PreparedStatement queryLoad;
	private PreparedStatement finalLoad;

	ResultSet resultSet;
	
	public SaveServer() {
		createMainPanel();
		wordsBox.append("Ready to Accept Connections");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600,400);
		setVisible(true);

		try{
			ServerSocket serverSocket = new ServerSocket(8000);
			System.out.println("Server started");

			//outputToFile = new ObjectOutputStream(new FileOutputStream(""))
			while(true) {
				Socket socket = serverSocket.accept();

				inputFromClient = new ObjectInputStream(socket.getInputStream());

				try{
					conn = DriverManager.getConnection("jdbc:sqlite:" + CONNECTION_DB);
				}catch (SQLException ex){
					System.out.println(ex);
				}
				System.out.println("------");

				Object object = inputFromClient.readObject();
				//load\//
				if(chackLoad(object)){
					System.out.println("load");
					wordsBox.append("\n" + "load to client");

					try {


						String loadAll = "Select playername, date from game";
						queryLoad = conn.prepareStatement(loadAll);
						PreparedStatement s = queryLoad;
						resultSet = s.executeQuery();
						ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
						int numCol = resultSetMetaData.getColumnCount();
						StringBuilder col = new StringBuilder(" ");
						StringBuilder row = new StringBuilder(" ");
						for (int i = 1; i <= numCol; i++) {
							col.append(resultSetMetaData.getColumnName(i) + "\t");
						}
						col.append("\n");

						while (resultSet.next()) {
							for (int i = 1; i <= numCol; i++) {
								Object o = resultSet.getObject(i);
								row.append(o.toString() + "\t");
							}
							row.append("\n");
						}

						ObjectOutputStream toClientPalyerRes = new ObjectOutputStream(socket.getOutputStream());
						toClientPalyerRes.writeObject(col.toString() + row.toString());


						System.out.println(col.toString() + row.toString());


						conn.close();
						//fixme
						//socket.close();


					}catch(SQLException e){
						System.out.println(e);
					}


				}else if(checkObjectLoac(object)){
					System.out.println("loadsect");
					String queryNametime = (String) object;
					System.out.println(queryNametime);
					try {
						System.out.println("????????");

						String[] playerNametime = queryNametime.split(" ");
						String pname = playerNametime[1];
						String ptime = playerNametime[2];

						finalLoad = conn.prepareStatement("Select * from game where playerName = ? and date = ?");
						PreparedStatement s = finalLoad;
						s.setString(1, pname);
						s.setString(2, ptime);


						//if(s.executeQuery() == null){
						//ObjectOutputStream toClientFinal = new ObjectOutputStream(socket.getOutputStream());
						//toClientFinal.writeObject(new String("No record"));
						//}
						ResultSet finalLoad, norecord;


						if(!s.executeQuery().next()){
							//norecord = s.executeQuery();
							//ObjectOutputStream toClientFinalNoRecord = new ObjectOutputStream(socket.getOutputStream());
							//toClientFinalNoRecord.writeObject("No record");
							System.out.println("no record");
							ObjectOutputStream toClientFinal = new ObjectOutputStream(socket.getOutputStream());
							toClientFinal.writeObject(new Game(null,null, 0,0,
									0,0,0,0,0,0,0,
									0,0,0,0,00,0
									,0,0,0,0,0));

							//socket.close();
						}else {
							finalLoad = s.executeQuery();


							Game sendGame = new Game(
									finalLoad.getString(1), finalLoad.getString(2), finalLoad.getInt(3),
									finalLoad.getInt(4), finalLoad.getInt(5), finalLoad.getInt(6), finalLoad.getInt(7),
									finalLoad.getInt(8), finalLoad.getInt(9), finalLoad.getInt(10), finalLoad.getInt(11),
									finalLoad.getInt(12), finalLoad.getInt(13), finalLoad.getInt(14), finalLoad.getInt(15),
									finalLoad.getInt(16), finalLoad.getInt(17), finalLoad.getInt(18), finalLoad.getInt(19),
									finalLoad.getInt(20), finalLoad.getInt(21), finalLoad.getInt(22)
							);

							ObjectOutputStream toClientFinal = new ObjectOutputStream(socket.getOutputStream());
							toClientFinal.writeObject(sendGame);


							//Game outputGame = new Game();

							System.out.println("???????????");
							conn.close();
						}

					}catch(SQLException ex){
						System.out.println(ex);
					}


				}
				else {
					//save game
					Game g = (Game) object;

					System.out.println("got object" + object.toString());

					System.out.println("write");

					try {
						//conn = DriverManager.getConnection("jdbc:sqlite:" + CONNECTION_DB);
						queryName = conn.prepareStatement("Select * from game where playerName = ?");
						String playerName = g.getPlayerName();
						String saveddate = g.getSavedDate();
						int aces = g.getAces();
						int twos = g.getTwos();
						int threes = g.getThrees();
						int fours = g.getFours();
						int fives = g.getFives();
						int sixes = g.getSixes();
						int scoreTotal = g.getScoreTotal();
						int Bonus = g.getBonus();
						int threeOfKind = g.getThreeOfKind();
						int fourOfKind = g.getFourOfKind();
						int fullHouse = g.getFullHouse();
						int smallHouse = g.getSmallHouse();
						int largeStraight = g.getLargeStraight();
						int yahtzee = g.getYahtzee();
						int chance = g.getChance();
						int yahtzeeBonus = g.getBonus();
						int totalLower = g.getTotalLower();
						int grandTotal = g.getGrandTotal();
						int roll = g.getRoll();
						int round = g.getRound();

						String insertStatement = "INSERT INTO Game(playerName, date, aces, twos, threes, fours, fives, sixes,\n" +
								"                scoreTotal, bonus, threeOfKind, fourOfKind,fullHouse,\n" +
								"                smallHouse, largeStraight, yahtzee, chance, yahtzeeBonus,\n" +
								"                totalLower, grandTotal, roll, round) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
						queryInsert = conn.prepareStatement(insertStatement);
						PreparedStatement statementInsert = queryInsert;
						statementInsert.setString(1, playerName);
						statementInsert.setString(2, saveddate);
						statementInsert.setInt(3, aces);
						statementInsert.setInt(4, twos);
						statementInsert.setInt(5, threes);
						statementInsert.setInt(6, fours);
						statementInsert.setInt(7, fives);
						statementInsert.setInt(8, sixes);
						statementInsert.setInt(9, scoreTotal);
						statementInsert.setInt(10, Bonus);
						statementInsert.setInt(11, threeOfKind);
						statementInsert.setInt(12, fourOfKind);
						statementInsert.setInt(13, fullHouse);
						statementInsert.setInt(14, smallHouse);
						statementInsert.setInt(15, largeStraight);
						statementInsert.setInt(16, yahtzee);
						statementInsert.setInt(17, chance);
						statementInsert.setInt(18, yahtzeeBonus);
						statementInsert.setInt(19, totalLower);
						statementInsert.setInt(20, grandTotal);
						statementInsert.setInt(21, roll);
						statementInsert.setInt(22, round);
						validate();

						statementInsert.execute();
						conn.close();
					} catch (SQLException e) {
						System.out.println("Connection error " + e);
					}

				}

			}
		}catch(ClassNotFoundException ex){
			ex.printStackTrace();
		}catch(IOException ex){
			ex.printStackTrace();
		}finally{
			try{
				inputFromClient.close();
				//outputToFile.close();
			}catch (Exception ex){
				ex.printStackTrace();
			}
		}


	}

	public boolean checkObjectLoac(Object o){

		if(o instanceof String){
			String temp = (String) o;
			temp = temp.substring(0, 10);
			if(temp.equals("loadSelect")){
				return true;
			}else{
				return false;
			}
		}
		return false;
	}

	public boolean chackLoad(Object o){
		if(o instanceof  String){
			String temp = (String) o;
			if(temp.equals("loadGame")){
				return true;
			}else{
				return false;
			}
		}
		return false;
	}



	
	public void createMainPanel() {
		wordsBox = new JTextArea(35,10);

		JScrollPane listScroller = new JScrollPane(wordsBox);
		this.add(listScroller, BorderLayout.CENTER);
		listScroller.setPreferredSize(new Dimension(250, 80));
	}
	
	public static void main(String[] main) {

		SaveServer saveServer = new SaveServer();
	}
}
