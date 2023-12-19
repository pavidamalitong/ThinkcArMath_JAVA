package car;

import java.awt.*;

public class DrivingRedCar extends RedCar{
	
	public boolean change = true;
	
	public DrivingRedCar(){}
	
	public void vroom1(Graphics g){
		
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
		
		//grass
		g.setColor(grassGreen);
		g.fillRect(Xborder, Yborder+130, 400, 170);
		
		//road
		g.setColor(grayRoad);
		int[] xRoad = {Xborder+50,Xborder+100,Xborder+300,Xborder+350};
		int[] yRoad = {Yborder+300,Yborder+130,Yborder+130,Yborder+300};
		g.fillPolygon(xRoad,yRoad,4);

		//cloud
		g.setColor(Color.WHITE);
		g.fillOval(Xborder+270, Yborder+50, 50, 30);
		g.fillOval(Xborder+300, Yborder+50, 50, 30);
		g.fillOval(Xborder+290, Yborder+40, 40, 30);
		
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
	
	public void vroom2(Graphics g){
		
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
		
		//grass
		g.setColor(grassGreen);
		g.fillRect(Xborder, Yborder+130, 400, 170);
		
		//road
		g.setColor(grayRoad);
		int[] xRoad = {Xborder+50,Xborder+100,Xborder+300,Xborder+350};
		int[] yRoad = {Yborder+300,Yborder+130,Yborder+130,Yborder+300};
		g.fillPolygon(xRoad,yRoad,4);

		//cloud
		g.setColor(Color.WHITE);
		g.fillOval(Xborder+290, Yborder+50, 50, 30);
		g.fillOval(Xborder+320, Yborder+50, 50, 30);
		g.fillOval(Xborder+310, Yborder+40, 40, 30);
		
		//car
		g.setColor(redCar);
		g.fillArc(Xborder+120, Yborder+60, 160, 160, 0, 180);
		g.fillRoundRect(Xborder+100, Yborder+130, 200, 80, 20, 20);
		g.setColor(Color.BLACK);
		g.fillArc(Xborder+120, Yborder+190, 40, 40, 180, 180);
		g.fillArc(Xborder+240, Yborder+190, 40, 40, 180, 180);
		
		//license plate
		g.setColor(Color.WHITE);
		g.fillRoundRect(Xborder+140, Yborder+145, 120, 50, 20, 20);
	}
}