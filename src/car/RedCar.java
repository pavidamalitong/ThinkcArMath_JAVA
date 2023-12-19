package car;

import javax.swing.*;
import java.awt.*;

public class RedCar extends JPanel{
	
	public int Xborder = 300;
	public int Yborder = 50;
	
	protected Color skyBlue = new Color(135,206,250);
	protected Color yellowSun = new Color(255,229,124);
	protected Color grassGreen = new Color(65,145,8);
	protected Color grayRoad = new Color(194,197,204);
	protected Color redCar = new Color(177,18,38);
	
	public RedCar() {}
	
	protected void paintComponent(Graphics g) {
		
		//sky
		g.setColor(skyBlue);
		g.fillRect(Xborder, Yborder, 400, 300);
		
		//sun
		for(int i=0;i<6;i++) {
			g.setColor(yellowSun);
			g.fillArc(Xborder+15, Yborder+15, 90, 90, i*60, 30);
		}
		g.setColor(Color.ORANGE);
		g.fillOval(Xborder+30, Yborder+30, 60, 60);
		
		//cloud
		g.setColor(Color.WHITE);
		g.fillOval(Xborder+270, Yborder+50, 50, 30);
		g.fillOval(Xborder+300, Yborder+50, 50, 30);
		g.fillOval(Xborder+290, Yborder+40, 40, 30);
		
		//grass
		g.setColor(grassGreen);
		g.fillRect(Xborder, Yborder+130, 400, 170);
		
		//road
		g.setColor(grayRoad);
		int[] xRoad = {Xborder+50,Xborder+100,Xborder+300,Xborder+350};
		int[] yRoad = {Yborder+300,Yborder+130,Yborder+130,Yborder+300};
		g.fillPolygon(xRoad,yRoad,4);
		
		//car
		g.setColor(redCar);
		g.fillArc(Xborder+120, Yborder+70, 160, 160, 0, 180);
		g.fillRoundRect(Xborder+100, Yborder+140, 200, 80, 20, 20);
		g.setColor(Color.BLACK);
		g.fillArc(Xborder+120, Yborder+200, 40, 40, 180, 180);
		g.fillArc(Xborder+240, Yborder+200, 40, 40, 180, 180);
		
		//license plate
		g.setColor(Color.WHITE);
		g.fillRoundRect(Xborder+140, Yborder+155, 120, 50, 20, 20);
	}
	
}