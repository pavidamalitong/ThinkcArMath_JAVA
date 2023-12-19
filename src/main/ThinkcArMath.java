package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import calculator.*;
import car.*;

public class ThinkcArMath extends JPanel implements ActionListener, ItemListener, MouseListener{
	
	//imported classes
	RedCar picCar = new RedCar();
	RedCar movingCar = new DrivingRedCar();
	TheCalculator calculator = new TheCalculator();
	
	//General setting
	JFrame f = new JFrame("Think cAr Math");
	static String playerName;
	int[] current_Q;
	String symbol1,symbol2;
	int show = 0;
	int correct = 0;
	int time = 30;
	
	Timer playTime = new Timer(1000,this);
	Timer timerCar = new Timer(500, this);
	
	Font fontTitle = new Font("OCR A Extended",Font.BOLD,48);
	Font fontSubtitle = new Font("OCR A Extended",Font.BOLD,24);
	Font fontTw = new Font("Tw Cen MT",Font.BOLD,24);
	Font fontOnigiri = new Font("FC Onigiri Outline Color",Font.BOLD,90);
	
	//Components in Main menu page
	JButton bt_Play = new JButton("Play");;
	JButton bt_HowToPlay = new JButton("How To Play");
	JButton bt_ResetName = new JButton("Reset Name");
	JPanel panel_Menu = new JPanel();
	
	//Components in How to play page
	ImageIcon howtoplay = new ImageIcon(new ImageIcon("C:\\Users\\HUAWEI\\OneDrive\\[1.2] SIIT\\LAB OOP\\ThinkcArMath_HowToPlay.jpg").getImage().getScaledInstance(900, 650, Image.SCALE_DEFAULT));
	JLabel HowToPlay = new JLabel();
	JButton bt_Back = new JButton("Back");
	
	//Components in Reset name page
	JLabel label_Name = new JLabel("Type your name:");
	JTextField tf_Name = new JTextField("",20);
	JButton bt_Confirm = new JButton("Confirm");
	
	//Components in Playing page
	JLabel label_numbers = new JLabel("");
	String [] mathSymbol = new String[] {"+","-","x","/"," "};
	JComboBox<String> cb_symbol1 = new JComboBox<String>(mathSymbol);
	JComboBox<String> cb_symbol2 = new JComboBox<String>(mathSymbol);
	JButton bt_GiveUp = new JButton("Give Up");
	JLabel label_signal = new JLabel();
	JButton bt_End = new JButton("End");
	JLabel label_playerName = new JLabel("Name: " + playerName);
	JLabel label_Score = new JLabel("Score: " + correct*100);
	JLabel label_Time = new JLabel("Time: " + time);
	JPanel panel_console = new JPanel();
	
	//Components in Result page
	JLabel label_Result = new JLabel("Result");
	JLabel label_details = new JLabel();
	JButton bt_Replay = new JButton("Replay");
	JButton bt_turnback = new JButton("Turn to Main Menu");
	
	protected ThinkcArMath() {
		
		//Setup Main menu page		
		panel_Menu.setLayout(new GridLayout(3,1));
		panel_Menu.add(bt_Play);
		panel_Menu.add(bt_HowToPlay);
		panel_Menu.add(bt_ResetName);
		
		bt_Play.addActionListener(this);
		bt_HowToPlay.addActionListener(this);
		bt_ResetName.addActionListener(this);
		
		//setup How to play page
		HowToPlay.setIcon(howtoplay);
		bt_Back.addActionListener(this);
		
		//Setup Reset name page
		bt_Confirm.addActionListener(this);
		
		//Setup Playing page
		panel_console.setLayout(new GridLayout(2,3));
		panel_console.add(bt_GiveUp);
		panel_console.add(label_signal);
		panel_console.add(bt_End);
		panel_console.add(label_playerName);
		panel_console.add(label_Score);
		panel_console.add(label_Time);
		
		bt_GiveUp.addActionListener(this);
		bt_End.addActionListener(this);
		cb_symbol1.addItemListener(this);
		cb_symbol2.addItemListener(this);
		
		//Setup Result page
		bt_Replay.addActionListener(this);
		bt_turnback.addActionListener(this);
		
		//Setup main JFrame
		f.add(this);
		f.addMouseListener(this);
		f.setSize(1000, 750);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Set the player's name when the program starts
		openSetPlayerName();
	}
	
	//Functions to open and close Main menu page
	private void openMainMenu() {
		this.setLayout(null);
		bt_Play.setFont(fontSubtitle);
		bt_HowToPlay.setFont(fontSubtitle);
		bt_ResetName.setFont(fontSubtitle);
		picCar.setBounds(0, 0, 1000, 400);
		panel_Menu.setBounds(350, 450, 300, 250);
		
		this.add(picCar);
		this.add(panel_Menu);
		show = 1;
		
		this.revalidate();
		this.repaint();
	}
	
	private void closeMainMenu() {
		this.remove(picCar);
		this.remove(panel_Menu);
		label_signal.setText("");
		show = 0;
	}
	
	//Functions to open and close Playing page
	private void openPlayingPage() {
		//random the first question
		nextQuestion();
		
		//reset the previous game to the new one
		playTime.start();
		timerCar.start();
		time = 30;
		correct = 0;
		cb_symbol1.setSelectedIndex(0);
		cb_symbol2.setSelectedIndex(0);
		symbol1 = cb_symbol1.getSelectedItem().toString();
		symbol2 = cb_symbol2.getSelectedItem().toString();
		
		label_playerName.setText("Name: " + playerName);
		label_signal.setText("");
		label_Score.setText("Score: " + correct*100);
		
		cb_symbol1.setFont(fontTw);
		cb_symbol2.setFont(fontTw);
		label_playerName.setFont(fontSubtitle);
		bt_GiveUp.setFont(fontSubtitle);
		bt_End.setFont(fontSubtitle);
		label_Score.setFont(fontSubtitle);
		label_Time.setFont(fontSubtitle);
		label_signal.setFont(fontSubtitle);
		
		this.setLayout(null);
		label_numbers.setBounds(0, 380, 1000, 80);
		label_numbers.setHorizontalAlignment(JLabel.CENTER);
		panel_console.setBounds(0, 500, 1000, 250);
		cb_symbol1.setBounds(295, 400, 70, 60);
		cb_symbol2.setBounds(635, 400, 70, 60);
		label_signal.setHorizontalAlignment(JLabel.CENTER);
		
		this.add(cb_symbol1);
		this.add(cb_symbol2);
		this.add(label_numbers);
		this.add(panel_console);
		show = 2;
		
		this.revalidate();
		this.repaint();
	}
	
	private void closePlayingPage() {
		timerCar.stop();
		this.remove(panel_console);
		this.remove(cb_symbol1);
		this.remove(cb_symbol2);
		this.remove(label_numbers);
		show = 0;
	}
	
	//Functions to open and close How to play page
	private void openHowToPlay() {
		bt_Back.setFont(fontSubtitle);
		
		this.setLayout(new FlowLayout());
		
		this.add(HowToPlay);
		this.add(bt_Back);
		
		this.revalidate();
		this.repaint();
	}
	
	private void closeHowToPlay() {
		this.remove(HowToPlay);
		this.remove(bt_Back);
	}
	
	//Functions to open and close Reset name page
	private void openSetPlayerName() {
		label_Name.setFont(fontSubtitle);
		tf_Name.setFont(fontSubtitle);
		bt_Confirm.setFont(fontSubtitle);
		
		this.setLayout(null);
		label_Name.setBounds(250, 300, 250, 30);
		tf_Name.setBounds(500, 300, 250, 30);
		bt_Confirm.setBounds(400, 350, 200, 60);
		
		this.add(label_Name);
		this.add(tf_Name);
		this.add(bt_Confirm);
		
		this.revalidate();
		this.repaint();
	}
	
	private void closeSetPlayerName() {
		this.remove(label_Name);
		this.remove(tf_Name);
		this.remove(bt_Confirm);
	}
	
	//Functions to open and close Result page
	private void openResult() {
		label_details.setText("You got " + correct*100 + " points ( " + correct + " equation(s) )");
		
		label_Result.setFont(fontTitle);
		label_details.setFont(fontTw);
		bt_Replay.setFont(fontSubtitle);
		bt_turnback.setFont(fontSubtitle);
		
		this.setLayout(null);
		label_Result.setBounds(400, 200, 200, 100);
		label_details.setBounds(320, 250, 400, 200);
		bt_Replay.setBounds(300, 450, 400, 100);
		bt_turnback.setBounds(300, 570, 400, 100);
		
		this.add(label_Result);
		this.add(label_details);
		this.add(bt_Replay);
		this.add(bt_turnback);
		
		this.revalidate();
		this.repaint();
	}
	
	private void closeResult() {
		this.remove(label_Result);
		this.remove(label_details);
		this.remove(bt_Replay);
		this.remove(bt_turnback);
	}
	
	//Function to call a method from the class 'TheCalculator' to random the new of numbers on the car's license plate (new question)
	public void nextQuestion() {
		current_Q = calculator.getNewQuestion();
		label_numbers.setFont(fontOnigiri);
		label_numbers.setText(current_Q[0] + "     " + current_Q[1] + "  =  " + current_Q[2] + "     " + current_Q[3]);
	}
	
	
	//Override the method in ActionListener
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//Timers
		if(e.getSource() == playTime) {
			time = time - 1;
			label_Time.setText("Time:" + time);
			if(time == 0) {
				playTime.stop();
				closePlayingPage();
				openResult();
			}
			repaint();
		}
		
		if(e.getSource() == timerCar) {
			((DrivingRedCar)movingCar).change = !((DrivingRedCar)movingCar).change;
			repaint();
		}
		
		//All buttons in each page
		if(e.getSource()==bt_Confirm) {
			String text = tf_Name.getText().toString();
			if(!tf_Name.getText().isEmpty()) {
				playerName = text;
				closeSetPlayerName();
				tf_Name.setText("");
				openMainMenu();
			}
		}
		
		if(e.getSource()==bt_Play) {
			closeMainMenu();
			openPlayingPage();
		}
		
		if(e.getSource()==bt_HowToPlay) {
			closeMainMenu();
			openHowToPlay();
		}
		
		if(e.getSource()==bt_ResetName) {
			closeMainMenu();
			openSetPlayerName();
		}
		
		if(e.getSource()==bt_Back) {
			closeHowToPlay();
			openMainMenu();
		}
		
		if(e.getSource()==bt_GiveUp) {
			nextQuestion();
		}
		
		if(e.getSource()==bt_End) {
			closePlayingPage();
			openResult();
		}
		
		if(e.getSource()==bt_Replay) {
			closeResult();
			openPlayingPage();
		}
		
		if(e.getSource()==bt_turnback) {
			closeResult();
			openMainMenu();
		}
	}
	
	//Override the method in ItemListener
	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource() == cb_symbol1 && e.getStateChange() == 1) {
			symbol1 = cb_symbol1.getSelectedItem().toString();
		}
		if(e.getSource() == cb_symbol2 && e.getStateChange() == 1) {
			symbol2 = cb_symbol2.getSelectedItem().toString();
		}
	}
	
	//Override the method in MouseListener
	@Override
	public void mouseClicked(MouseEvent e) {
		//Right click to submit the answer and check the result
		if (e.getButton() == 3) {
			boolean r = false;
			
			if(calculator.checkEquation(current_Q, symbol1, symbol2) == 1) {
				label_signal.setText("Correct!");
				correct += 1;
				label_Score.setText("Score: " + correct*100);
				r = true;
			}
			if(calculator.checkEquation(current_Q, symbol1, symbol2) == 2) {
				label_signal.setText("Wrong, try again");
			}
			if(calculator.checkEquation(current_Q, symbol1, symbol2) == 3) {
				label_signal.setText("Can't divide by ZERO");
			}
			
			if(r)
				nextQuestion();
			
			repaint();
        }
	}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		//show = 1: On the Main menu page
		if(playerName != null && show == 1) {
			g.setFont(fontTitle);
			CenteredText(g, "Think cAr Math", 400);
			g.setFont(fontTw);
			CenteredText(g, "Hello " + playerName + ", let's go!", 440);
		}
		
		//show = 2: On the Playing page
		if(show == 2) {
			g.setFont(fontTitle);
			g.drawString("\"Right", 740, 150);
			g.drawString(" Click\"", 740, 200);
			g.setFont(fontSubtitle);
			g.drawString("to submit", 750, 240);
			g.drawString("the answer", 750, 260);
			
			//show numbers on the car's license plate
			g.setFont(fontTw);
			
			if(((DrivingRedCar)movingCar).change == true) {
				((DrivingRedCar)movingCar).vroom1(g);
				g.setColor(Color.BLACK);
				g.drawString("XX-" + current_Q[0] + current_Q[1] + current_Q[2] + current_Q[3], picCar.Xborder+155, picCar.Yborder+190);
			}	
			else {
				((DrivingRedCar)movingCar).vroom2(g);	
				g.setColor(Color.BLACK);
				g.drawString("XX-" + current_Q[0] + current_Q[1] + current_Q[2] + current_Q[3], picCar.Xborder+155, picCar.Yborder+180);
			}
		}
	}
	
	//additional function to draw text at the center position
	public void CenteredText(Graphics g, String text, int y) {
	    FontMetrics mt = g.getFontMetrics();
	    int x = (getWidth() - mt.stringWidth(text))/2;
	    g.drawString(text, x, y);
	}

}
