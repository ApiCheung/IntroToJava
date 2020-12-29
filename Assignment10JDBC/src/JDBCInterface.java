package Assignment10JDBC.src; /**
 * @author Esmee Zhang
 * @date 12/1/20 5:26 下午
 * @projectName IntroToJava-NYU
 */
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.*;


public class JDBCInterface extends JFrame {

	private JPanel controlPanel;
	private JPanel insertPanel;
	private JPanel namePanel;
	private JPanel ageCityPanel;
	private JTextArea textQueryArea;
	private JTextField lastNameQuery;
	private JButton queryButton;
	private JButton insertButton;
	private JTextField lastNameInsert;
	private JTextField firstNameInsert;
	private JTextField ageInsert;
	private JTextField cityInsert;
	private JPanel textPanel;
	private JScrollPane scrollPane;
	private Connection conn;
	private PreparedStatement queryLastName;
	private PreparedStatement queryInsert;

	private static final int FRAME_WIDTH = 600;
	private static final int FRAME_HEIGHT = 400;
	private static final int AREA_ROWS = 20;
	private static final int AREA_COLUMNS = 40;
	private static final String CONNECTION_DB = "/Users/esmeezhang/Documents/GitHub/IntroToJava-NYU/" +
												"Assignment10JDBC/assignment.db";

	public JDBCInterface() {


		try {
			conn = DriverManager.getConnection("jdbc:sqlite:" + CONNECTION_DB);
			queryLastName = conn.prepareStatement("Select * from People WHERE Last = ?");

		} catch (SQLException e) {
			System.err.println("Connection error: " + e);
			System.exit(1);
		}

		createControlPanel();
		queryButton.addActionListener(new QueryButtonListener());
		insertButton.addActionListener(new InsertButtonListener());
		textQueryArea = new JTextArea(AREA_ROWS, AREA_COLUMNS);
		textQueryArea.setEditable(false);

		/* scrollPanel is optional */
		scrollPane = new JScrollPane(textQueryArea);
		textPanel = new JPanel();
		textPanel.add(scrollPane);
		this.add(textPanel,BorderLayout.CENTER);
		this.add(controlPanel,BorderLayout.NORTH);
	}

	private JPanel createControlPanel() {

		/* you are going to have to create a much more fully-featured layout */

		controlPanel = new JPanel();
		controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));

		JPanel queryPanel = new JPanel();

		JPanel inputPanel = new JPanel();
		JLabel lbl = new JLabel("Last Name:");
		inputPanel.add(lbl);
		lastNameQuery = new JTextField(10);
		inputPanel.add(lastNameQuery);

		JPanel buttonPanel = new JPanel();
		queryButton = new JButton("Execute Query");
		buttonPanel.add(queryButton);

		queryPanel.add(inputPanel);
		queryPanel.add(buttonPanel);
		controlPanel.add(createInsertPanel());
		controlPanel.add(queryPanel);

		return controlPanel;


	}
	private JPanel createInsertPanel() {
		insertPanel = new JPanel();
		insertPanel.setLayout(new BoxLayout(insertPanel, BoxLayout.Y_AXIS));

		namePanel = new JPanel();
		JLabel lbl = new JLabel("Last Name:");
		namePanel.add(lbl);
		lastNameInsert = new JTextField(10);
		namePanel.add(lastNameInsert);

		JLabel fbl = new JLabel("First Name:");
		namePanel.add(fbl);
		firstNameInsert = new JTextField(10);
		namePanel.add(firstNameInsert);

		insertPanel.add(namePanel);

		ageCityPanel = new JPanel();
		JLabel age = new JLabel("Age:");
		ageCityPanel.add(age);
		ageInsert = new JTextField(10);
		ageCityPanel.add(ageInsert);

		JLabel city = new JLabel("City:");
		ageCityPanel.add(city);
		cityInsert = new JTextField(10);
		ageCityPanel.add(cityInsert);

		insertPanel.add(ageCityPanel);

		insertButton = new JButton("Insert");
		JPanel insertBtnPanel = new JPanel();
		insertBtnPanel.add(insertButton);
		insertPanel.add(insertBtnPanel);
		return insertPanel;


	}

	class InsertButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			/* You will have to implement this */
			try{

				String insertStatement = "INSERT INTO People (Last, First, Age, City) VALUES(?, ?, ?, ?)";
				queryInsert = conn.prepareStatement(insertStatement);
				String lastNameTf = lastNameInsert.getText();
				String firstNameTf = firstNameInsert.getText();
				String ageTf = ageInsert.getText();
				String cityTf = cityInsert.getText();

				if (lastNameTf.equals("") || firstNameTf.equals("") || ageTf.equals("") || cityTf.equals("")) {

					JOptionPane.showMessageDialog(null,"Fill all field!");
				}
				else {
					PreparedStatement statementInsert = queryInsert;
					statementInsert.setString(1, lastNameTf);
					statementInsert.setString(2, firstNameTf);
					statementInsert.setString(3, ageTf);
					statementInsert.setString(4, cityTf);
					statementInsert.execute();
					JOptionPane.showMessageDialog(null,"Successfully insert!");

				}

				textQueryArea.setText("");
				lastNameInsert.setText("");
				firstNameInsert.setText("");
				ageInsert.setText("");
				cityInsert.setText("");
				lastNameQuery.setText("");

			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
	}

	class QueryButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event)
		{
			
			try {
				textQueryArea.setText("");
				PreparedStatement statement = queryLastName;
				ResultSet resultSet;
				String lastNameText = lastNameQuery.getText();

				statement.setString(1, lastNameText);

				if (lastNameText.equals("")) {
					statement = conn.prepareStatement("Select * from People");
				}

				resultSet = statement.executeQuery();
				ResultSetMetaData resSetMetaData = resultSet.getMetaData();
				int numColumns = resSetMetaData.getColumnCount();
				StringBuilder col = new StringBuilder(" ");
				StringBuilder row = new StringBuilder(" ");

				for (int i = 1; i <= numColumns; i++) {
					col.append(resSetMetaData.getColumnName(i) + "\t");
				}
				col.append("\n");

				while (resultSet.next()) {
					for (int i = 1; i <= numColumns; i++) {
						Object o = resultSet.getObject(i);
						row.append(o.toString() + "\t");
					}
					row.append("\n");
				}

				textQueryArea.setText(col.toString() + row.toString());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args)
	{
		JFrame frame = new JDBCInterface();
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
