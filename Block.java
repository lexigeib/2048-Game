import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Block extends Spaces{
	private int locX;
	private int locY;
	private int diameter;	
	private int value;

	public Block(int x, int y){//make block
		locX = x;
		locY = y;
		diameter = 100;
		value = 0;
	}
	public void setValue(int x){//set value of block
		value = x;
	}
	public int getValue(){
		return value;
	}
	
	public void draw(Graphics g){
		Color color2 = new Color(167,212,200);//the colors the blocks should be depending on value
		Color color4 = new Color(72, 149, 126);
		Color color8 = new Color(154, 188, 255);
		Color color16 = new Color(48, 118, 255);
		Color color32 = (Color.BLUE);
		Color color64 = new Color(218, 153, 254);
		Color color128 = new Color(188, 65, 255);
		Color color256 = new Color(255, 54, 168);
		Color color512 = new Color(253, 0, 143);
		Color color1024 = new Color(255, 54, 54);
		Color color2048 = new Color(255, 7, 7);
	    Font font = new Font("Arial", Font.BOLD, 25);
	    Font font2 = new Font(Font.MONOSPACED, Font.PLAIN, 155);
		
		g.setColor(Color.WHITE);
		g.drawRoundRect(locX, locY, diameter, diameter, 15, 15);//spaces
		
		g.setFont(font2);
		g.setColor(Color.WHITE);
		g.drawString("2048", 140, 150);//title
		
		g.setFont(font);

		if(value == 0){
			
		}
		else{//draw circles of blocks
			if(value == 2){
				g.setColor(color2);
				g.fillOval(locX,  locY, diameter, diameter);
				g.setColor(Color.BLACK);
				g.drawString(value + "", locX + diameter/2 - 7, locY + diameter/2 + 10);
			}
			else if(value == 4){
				g.setColor(color4);
				g.fillOval(locX,  locY, diameter, diameter);
				g.setColor(Color.BLACK);
				g.drawString(value + "", locX + diameter/2 - 7, locY + diameter/2 + 10);
			}
			else if(value == 8){
				g.setColor(color8);
				g.fillOval(locX,  locY, diameter, diameter);
				g.setColor(Color.BLACK);
				g.drawString(value + "", locX + diameter/2 - 7, locY + diameter/2 + 10);
			}
			else if(value == 16){
				g.setColor(color16);
				g.fillOval(locX,  locY, diameter, diameter);
				g.setColor(Color.BLACK);
				g.drawString(value + "", locX + diameter/2 - 15, locY + diameter/2 + 10);
			}
			else if(value == 32){
				g.setColor(color32);
				g.fillOval(locX,  locY, diameter, diameter);
				g.setColor(Color.BLACK);
				g.drawString(value + "", locX + diameter/2 - 15, locY + diameter/2 + 10);
			}
			else if(value == 64){
				g.setColor(color64);
				g.fillOval(locX,  locY, diameter, diameter);
				g.setColor(Color.BLACK);
				g.drawString(value + "", locX + diameter/2 - 15, locY + diameter/2 + 10);
			}
			else if(value == 128){
				g.setColor(color128);
				g.fillOval(locX,  locY, diameter, diameter);
				g.setColor(Color.BLACK);
				g.drawString(value + "", locX + diameter/2 - 20, locY + diameter/2 + 10);
			}
			else if(value == 256){
				g.setColor(color256);
				g.fillOval(locX,  locY, diameter, diameter);
				g.setColor(Color.BLACK);
				g.drawString(value + "", locX + diameter/2 - 20, locY + diameter/2 + 10);
			}
			else if(value == 512){
				g.setColor(color512);
				g.fillOval(locX,  locY, diameter, diameter);
				g.setColor(Color.BLACK);
				g.drawString(value + "", locX + diameter/2 - 20, locY + diameter/2 + 10);
			}
			else if(value == 1024){
				g.setColor(color1024);
				g.fillOval(locX,  locY, diameter, diameter);
				g.setColor(Color.BLACK);
				g.drawString(value + "", locX + diameter/2 - 30, locY + diameter/2 + 10);
			}
			else if(value == 2048){
				g.setColor(color2048);
				g.fillOval(locX,  locY, diameter, diameter);
				g.setColor(Color.BLACK);
				g.drawString(value + "", locX + diameter/2 - 30, locY + diameter/2 + 10);
			}
		}
	}	
}
