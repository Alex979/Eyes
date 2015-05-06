import java.awt.*;

public class Eye {
	
	private int xPos;
	private int yPos;
	private int width = 200;
	private int height = 200;
	private Color color = Color.white;
	
	private int lookX;
	private int lookY;
	private int pupilX;
	private int pupilY;
	private int pupilWidth;
	private int pupilHeight;

	public Eye(){
		xPos = 250;
		yPos = 250;
		pupilX = xPos;
		pupilY = yPos;
		pupilWidth = (int)(width * 0.6);
		pupilHeight = (int)(height * 0.6);
	}
	
	public Eye(int x, int y){
		xPos = x;
		yPos = y;
		pupilX = xPos;
		pupilY = yPos;
		pupilWidth = (int)(width * 0.6);
		pupilHeight = (int)(height * 0.6);
	}
	
	public void setWidth(int width){
		this.width = width;
		pupilWidth = (int)(width * 0.6);
	}
	
	public void setHeight(int height){
		this.height = height;
		pupilHeight = (int)(height * 0.6);
	}
	
	public void setColor(Color c){
		color = c;
	}
	
	public void updateEyes(int lookX, int lookY){
		this.lookX = lookX;
		this.lookY = lookY;
	}
	
	public void draw(Graphics2D g){
		//Eye
		g.setColor(color);
		g.fillOval(xPos - width/2, yPos - height/2, width, height);
		g.setColor(new Color(20, 20, 20));
		
		//Pupil
		double angle = Math.atan2((double)(lookY - yPos),(double)(lookX - xPos));
		double distance = Math.sqrt(Math.pow(lookY - yPos, 2) + Math.pow(lookX - xPos, 2));
		if(distance > Math.abs(width/2 - pupilWidth/2))
			distance = Math.abs(width/2 - pupilWidth/2);
		if(distance > Math.abs(height/2 - pupilHeight/2))
			distance = Math.abs(height/2 - pupilHeight/2);
		int pupilXPos = pupilX + (int)(Math.cos(angle) * distance);
		int pupilYPos = pupilY + (int)(Math.sin(angle) * distance);
		g.fillOval(pupilXPos - pupilWidth/2, pupilYPos - pupilHeight/2, pupilWidth, pupilHeight);
		
//		g.setColor(Color.black);
//		g.drawLine(xPos, yPos, lookX, yPos);
//		g.drawLine(lookX, yPos, lookX, lookY);
//		g.drawLine(xPos, yPos, lookX, lookY);
	}
}
