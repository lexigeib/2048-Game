import javax.swing.*;
import java.awt.Color;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameMain extends JPanel implements KeyListener, ActionListener, Interface{
	private Block block[][] = new Block[4][4];
	private JButton upButton = new JButton();
	private JButton downButton = new JButton();
	private JButton leftButton = new JButton();
	private JButton rightButton = new JButton();
	private JButton newGame = new JButton();
	private JFrame frame = new JFrame();
	public GameMain(){ 
		
		
		frame.setSize(700, 650);
		
		Container can = frame.getContentPane();
		setSize(500, 650);
		can.setLayout(null);//can set location of arrows
		setLayout(null);
		setLocation(0,0);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addKeyListener(this);//add arrows
		frame.setTitle("Lexi");
		frame.setBackground(Color.BLACK);
		
		JPanel arrows = new JPanel();//panel for arrows
        arrows.setLayout(null);
        arrows.setLocation(500, 285);// set specific location for arrows
        arrows.setSize(150, 305);
		
		ImageIcon up = new ImageIcon("UpArrow.png");
		upButton = new JButton(up);
		upButton.setFocusable(false);
        upButton.setLocation(55, 200);
        upButton.setSize(40, 40);
	    arrows.add(upButton);
	    
	    ImageIcon down = new ImageIcon("DownArrow.png");
		downButton = new JButton(down);
		downButton.setFocusable(false);
        downButton.setLocation(55, 255);
        downButton.setSize(40, 40);
	    arrows.add(downButton);
	    
	    ImageIcon left = new ImageIcon("LeftArrow.png");
		leftButton = new JButton(left);
		leftButton.setFocusable(false);
        leftButton.setLocation(0, 255);
        leftButton.setSize(40, 40);
	    arrows.add(leftButton);
	    
	    ImageIcon right = new ImageIcon("RightArrow.png");
		rightButton = new JButton(right);
		rightButton.setFocusable(false);
        rightButton.setLocation(110, 255);
        rightButton.setSize(40, 40);
	    arrows.add(rightButton);
	    
	    newGame = new JButton("New Game");//new game button
	    newGame.setFocusable(false);
	    newGame.setLocation(20, 0);
	    newGame.setSize(100, 50);
	    arrows.add(newGame);
	    
	    rightButton.addActionListener(this);
	    leftButton.addActionListener(this);
	    upButton.addActionListener(this);
	    downButton.addActionListener(this);
	    newGame.addActionListener(this);
	    
	    can.add(arrows);//add to frame
	    can.add(this);
		
		for(int row = 0; row < block.length; row++){//make blocks
			for(int col = 0; col < block[row].length; col++){
				block[row][col] = new Block(50 + col * 100, 200 + row * 100);
			}
		}
		endGame();
		giveValueRnd();
		
		frame.setVisible(true);
		frame.requestFocus();//take focus from buttons
	}
	public boolean canMove(){//see if there are any possible moves
		int countBlock = 0;
		for(int row = 0; row < block.length; row++){
			for(int col = 0; col < block[row].length; col++){
				int blockValue = block[row][col].getValue();
				if(blockValue != 0){
					countBlock++;
				}
			}
		}
		
		if(countBlock != 16){//if all the blocks aren't filled, still canMove
			return true;
		}
		
		for(int row = 1; row < block.length; row++){//checks all directions to see if moving is still possible
			for(int col = 0; col < block[row].length; col++){
				int blockValue = block[row][col].getValue();
				int upValue = block[row - 1][col].getValue();
				if(upValue == blockValue){
					return true;
				}
			}
		}
		for(int row = block.length - 2; row >= 0; row --){
			for(int col = 0; col < block[row].length; col++){
				int blockValue = block[row][col].getValue();
				int bottomValue = block[row + 1][col].getValue();
				if(bottomValue == blockValue){	
					return true;
				}
			}
		}
		for(int col = 1; col < block.length; col++){
			for(int row = 0; row < block[col].length; row++){
				int blockValue = block[row][col].getValue();
				int leftValue = block[row][col - 1].getValue();
				if(leftValue == blockValue){
					return true;
				}
			}
		}
		for(int col = block.length - 2; col >= 0; col--){
			for(int row = 0; row <block[col].length; row++){
				int blockValue = block[row][col].getValue();
				int rightValue = block[row][col + 1].getValue();
				if(rightValue == blockValue){
					return true;
				}
			}
		}
		return false;//if nothing can happen, return can't move
	}
	public void giveValueRnd(){// give a value at a random space after each move
		boolean loop = false;
		for(int row = 0; row < block.length; row++){//if all blocks are not filled, continue
			for(int col = 0; col < block.length; col++){
				if(block[col][row].getValue() == 0){
					loop = true;
				}
			}
		}
		while(loop){
			int col = (int)(4 * Math.random() + 0); //determine whether to set the value to 4 or 2
			int row = (int)(4 * Math.random() + 0); 
			if(block[row][col].getValue() == 0){
				double rand = Math.random() * 100;
				if(rand > 75){
					block[row][col].setValue(4);
				}
				else{
					block[row][col].setValue(2);
				}
				loop = false;
			}
		}
	}
	public void endGame(){//if there are no more possible moves, endGame
		boolean endGame = true;
		if(canMove()){
			endGame = false;
		}
		if(endGame){//dialog box
			JOptionPane.showMessageDialog(this,
		    "You Lose :( ... TRY AGAIN!",
			"2048",
			JOptionPane.PLAIN_MESSAGE);
			new GameMain();//restart game
			frame.setVisible(false);
		}
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.LIGHT_GRAY);
		for(int row = 0; row < block.length; row++){//draw blocks
			for(int col = 0; col < block[row].length; col++){
				block[row][col].draw(g);
			}
		}
	}
	public void moveUp(){//move blocks up and redraw
		for(int row = 1; row < block.length; row++){
			for(int col = 0; col < block[row].length; col++){
				int blockValue = block[row][col].getValue();
				for(int up = row - 1; up >= 0; up--){
					int upValue = block[up][col].getValue();
					if(upValue == blockValue){
						block[up][col].setValue(upValue * 2);
						for(int r = up + 1; r <= row; r++){
							block[r][col].setValue(0);
						}
						break;
					}
					else if (upValue == 0){
						block[up][col].setValue(blockValue);
						
						for(int r = up + 1; r <= row; r++){
							block[r][col].setValue(0);
						}
						
					}
					else {
						break;
					}
				}
			}
		}
		endGame();
		giveValueRnd();
		repaint();
	}
	public void moveDown(){//move blocks down and redraw
		for(int row = block.length - 2; row >= 0; row --){
			for(int col = 0; col < block[row].length; col++){
				int blockValue = block[row][col].getValue();
				for(int bottom = row + 1; bottom < block.length; bottom++){
					int bottomValue = block[bottom][col].getValue();
					if(bottomValue == blockValue){			//WE ARE DONE BECAUSE ALL ARE 0, SO NO MORE TO DO
						block[bottom][col].setValue(bottomValue * 2);
						for(int r = bottom - 1; r >= row; r--){
							block[r][col].setValue(0);
						}
						break;
					}
					else if(bottomValue == 0){	//reset everything in between (might be more than 1, no break)
						block[bottom][col].setValue(blockValue);
						for(int r = bottom - 1; r >= row; r--){
							block[r][col].setValue(0);
						}
					}
					else{
						break;
					}
				}
			}
		}
		endGame();
		giveValueRnd();
		repaint();	
	}
	public void moveRight(){//move blocks right and redraw
		for(int col = block.length - 2; col >= 0; col--){
			for(int row = 0; row <block[col].length; row++){
				int blockValue = block[row][col].getValue();
				for(int right = col + 1; right < block.length; right++){
					int rightValue = block[row][right].getValue();
					if(rightValue == blockValue){
						block[row][right].setValue(rightValue * 2);
						for(int c = right - 1; c >= col; c--){
							block[row][c].setValue(0);
						}
						break;
					}
					else if(rightValue == 0){
						block[row][right].setValue(blockValue);
						for(int c = right - 1; c >= col; c--){
							block[row][c].setValue(0);
						}
					}
					else{
						break;
					}
				}
			}
		}
		endGame();
		giveValueRnd();
		repaint();
	}
	public void moveLeft(){//move blocks left and redraw
		for(int col = 1; col < block.length; col++){
			for(int row = 0; row < block[col].length; row++){
				int blockValue = block[row][col].getValue();
				for(int left = col - 1; left >= 0; left--){
					int leftValue = block[row][left].getValue();
					if(leftValue == blockValue){
						block[row][left].setValue(leftValue * 2);
						for(int c = left + 1; c <= col; c++){
							block[row][c].setValue(0);
						}
						break;
					}
					else if (leftValue == 0){
						block[row][left].setValue(blockValue);
						
						for(int c = left + 1; c <= col; c++){
							block[row][c].setValue(0);
						}
						
					}
					else {
						break;
					}
				}
			}
		}
		endGame();
		giveValueRnd();
		repaint();
	}
	public void keyPressed(KeyEvent e) {// if arrows are used
		int keyID = e.getKeyCode();
		if (keyID == KeyEvent.VK_UP){
			moveUp();
		}
		else if (keyID == KeyEvent.VK_DOWN){
			moveDown();
		}
		else if (keyID == KeyEvent.VK_LEFT){
			moveLeft();
		}
		else if (keyID == KeyEvent.VK_RIGHT){
			moveRight();
		}
	}
	public void keyReleased(KeyEvent arg0) {}
	public void keyTyped(KeyEvent arg0) {}
	public void actionPerformed(ActionEvent a) {
		Object source = a.getSource();
		if(source == newGame){//if new game button is pushed
			new GameMain();
			frame.setVisible(false);
			
		}
		if(source == upButton){//if buttons on screen are pushed
			moveUp();
		}
		else if(source == downButton){
			moveDown();
		}
		else if(source == leftButton){
			moveLeft();
		}
		else if(source == rightButton){
			moveRight();
		}
	}
	public static void main(String[]args)
	{
		new GameMain();
	}
	
}
