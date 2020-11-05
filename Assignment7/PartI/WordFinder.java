package Assignment7.PartI;

import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class WordFinder extends JFrame {

	JFileChooser jFileChooser;
	WordList wordList;
	JList list;
	JMenuBar menuBar;
	JTextField input;
	JPanel panelForTextFields;
	JLabel findLabel;
	JButton clear;
	JScrollPane scrollPane;
	JMenu menu;
	JMenuItem file;
	JMenuItem exit;
	JScrollBar bar;

	private DefaultListModel model;
	private JPanel topPanel;
	private List word;



	public WordFinder() {


		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// set the size correctly
		this.setSize(500, 300);

		jFileChooser = new JFileChooser(".");
		wordList = new WordList();

		panelForTextFields = new JPanel();
		panelForTextFields.setSize(400, 180);

		findLabel = new JLabel("Find: ");
		input = new JTextField();
		input.setPreferredSize(new Dimension(200, 25));
		input.getDocument().addDocumentListener(new Update());

		clear = new JButton("Clear");

		clear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				try {
					input.setText(null);
				}catch(NullPointerException e){
					input.setText(null);
				}
			}
		});

		topPanel = new JPanel();

		topPanel.add(findLabel);
		topPanel.add(input);
		topPanel.add(clear);
		menuBar = new JMenuBar();
		menuBar.add(createMenus());
		this.setJMenuBar(menuBar);
		this.add(topPanel, BorderLayout.NORTH);




		model = new DefaultListModel();
		list = new JList(model);



		scrollPane = new JScrollPane(list);
		scrollPane.setPreferredSize(new Dimension(500, 95));
		bar = scrollPane.getVerticalScrollBar();


		this.add(scrollPane);


	}

	class Update implements DocumentListener{

		@Override
		public void insertUpdate(DocumentEvent documentEvent) {
			if(word == null){
				return;
			}
			try{
				find();
			}catch(NullPointerException e){
				System.out.println("No file read");
			}

		}

		@Override
		public void removeUpdate(DocumentEvent documentEvent) {
			if(word == null){
				return;
			}
			try {
				find();
			}catch(NullPointerException e){
				System.out.println("No file read");
			}


		}

		@Override
		public void changedUpdate(DocumentEvent documentEvent) {

		}
	}
	
	private JMenu createMenus() {
		/* add a "File" menu with:
		 * "Open" item which allows you to choose a new file
		 * "Exit" item which ends the process with System.exit(0);
		 * Key shortcuts are optional
		 */
		menu = new JMenu("File");
		file = new JMenuItem("Open");
		exit = new JMenuItem("Exit");

		//exit listener
		class MenuExitListener implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				System.exit(0);
			}
		}

		/* OpenActionListener that will open the file chooser */
		class OpenActionListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				OpenFileListener myFileListener = new OpenFileListener();
				myFileListener.actionPerformed(e);
			}
		}

		ActionListener exitListener = new MenuExitListener();
		ActionListener openFile = new OpenActionListener();
		exit.addActionListener(exitListener);
		file.addActionListener(openFile);
		menu.add(file);
		menu.add(exit);

		return menu;
	}

	private void find(){
		List searchResult = wordList.find(input.getText()); // figure out from WordList how to get this

		// you're going to want to get the words in the
		model.clear();
		model.addAll(searchResult);

		// searchResult list and put them in the textbox.
	}
	
	class OpenFileListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int returnVal = jFileChooser.showOpenDialog(getParent());
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				try {
					model.clear();
					System.out.println("You chose to open this file: " + jFileChooser.getSelectedFile().getAbsolutePath());

					InputStream in = new FileInputStream(jFileChooser.getSelectedFile().getAbsolutePath());
					wordList.load(in);
					word = wordList.getWords();
					bar.setValue(0);
					model.addAll(word);

					List searchResult = null;

				} catch (IOException error){
					error.printStackTrace();
				}
			}
		}
	}


	public static void main(String[] args) {

		WordFinder wordFinder = new WordFinder();
		wordFinder.setVisible(true);
	}
}
