package RandomGamePicker;

import java.awt.Color;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class RandomGamePicker
{
	private JFrame window = new JFrame();
	private Container con;
	private JPanel titlePanel = new JPanel(), buttonPanel = new JPanel(), addingGamePanel = new JPanel(), textFieldPanel = new JPanel();
	private JLabel title = new JLabel("Random Game Picker"), addingGameText = new JLabel("Copy and paste the file directory here");
	private Font Titlefont = new Font("Arial", Font.PLAIN, 20), font = new Font("Arial", Font.PLAIN, 14);
	private JButton gamePickerButton = new JButton("open a random game"), addingGameButton = new JButton("add a new game");
	private JTextField fileNameTF = new JTextField(30);
	
	//this list keeps duplicating files for some reason and unfortunately I don't know how to fix it
	private ArrayList<File> games = new ArrayList<File>();
	
	private ArrayList<File> nonDuplicatedGames = new ArrayList<File>();
	private GamePickerHandler gpHandler = new GamePickerHandler();
	private AddGameHandler agHandler = new AddGameHandler();
	private TextHandler tHandler = new TextHandler();
	private Desktop desktop = Desktop.getDesktop();
	
	public RandomGamePicker()
	{
		//creating window
		window.setSize(300, 300);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setBackground(Color.darkGray);
		window.setLayout(null);
		con = window.getContentPane();
		
		//creating title panel
		titlePanel.setBounds(40, 40, 200, 30);
		titlePanel.setBackground(Color.darkGray);
		
		//creating title
		title.setForeground(Color.white);
		title.setFont(Titlefont);
		
		//creating button panel
		buttonPanel.setBounds(60, 110, 170, 70);
		buttonPanel.setBackground(Color.blue);
		buttonPanel.setLayout(new GridLayout(2, 1));
		
		//creating the top button
		gamePickerButton.setFont(font);
		gamePickerButton.addActionListener(gpHandler);
		gamePickerButton.setFocusPainted(false);
		
		//creating the bottom button
		addingGameButton.setFont(font);
		addingGameButton.addActionListener(agHandler);
		addingGameButton.setFocusPainted(false);
		
		titlePanel.add(title);
		buttonPanel.add(gamePickerButton);
		buttonPanel.add(addingGameButton);
		
		con.add(titlePanel);
		con.add(buttonPanel);
		window.setVisible(true);
		
		//creating panels and text fields for "add a new game" section
		addingGamePanel.setVisible(false);
    	textFieldPanel.setVisible(false);
    	
		addingGamePanel.setBounds(20, 40, 240, 30);
		addingGamePanel.setBackground(Color.darkGray);
		con.add(addingGamePanel);
		
		addingGameText.setForeground(Color.white);
		addingGameText.setFont(font);
		
		addingGamePanel.add(addingGameText);
		
		textFieldPanel.setBounds(20, 80, 240, 30);
		textFieldPanel.setBackground(Color.darkGray);
		con.add(textFieldPanel);
			
		textFieldPanel.add(fileNameTF);
		
		
	}
	
	//if "pick random game" is pressed
	public class GamePickerHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			for(File file : games)
			{
				if(!nonDuplicatedGames.contains(file))
					nonDuplicatedGames.add(file);
			}
			
			System.out.println(games);
			System.out.println(nonDuplicatedGames);
			
			try {
				desktop.open(nonDuplicatedGames.get((int)(Math.random() * ((nonDuplicatedGames.size() - 1)))));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	//if "add a new game" is pressed
	public class AddGameHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			titlePanel.setVisible(false);
			buttonPanel.setVisible(false);
			
			addingGamePanel.setVisible(true);
        	textFieldPanel.setVisible(true);
        	
        	fileNameTF.setText("");
			
			// Action to be performed when "Enter" key is pressed
			fileNameTF.addActionListener(tHandler);
		}
	}
	
	//if enter key is pressed
		public class TextHandler implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				games.add(new File(fileNameTF.getText()));
            	addingGamePanel.setVisible(false);
            	textFieldPanel.setVisible(false);
            	
            	titlePanel.setVisible(true);
    			buttonPanel.setVisible(true);
			}
		}

	public static void main(String[] args)
	{
		new RandomGamePicker();
	}

}
