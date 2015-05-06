import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.*;
import java.awt.*;

import javax.swing.*;

public class EyePanel extends JPanel{

	private BufferedImage image;
	private Graphics2D g;
	private static Timer t;
	
	public static final int X_WIDTH = 800;
	public static final int Y_WIDTH = 800;
	private static final Color BACKGROUND_COLOR = new Color(79, 159, 240);
	
	private Eye eye, eye2;
	
	public EyePanel(){
		image = new BufferedImage(X_WIDTH, Y_WIDTH, BufferedImage.TYPE_INT_RGB);
		g = image.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		eye = new Eye(X_WIDTH/2 - 150, Y_WIDTH/2);
		eye2 = new Eye(X_WIDTH/2 + 150, Y_WIDTH/2);
		
		t = new Timer(5, new TimerListener());
		t.start();
		
		addKeyListener(new Key());
		setFocusable(true);
		
		g.setColor(BACKGROUND_COLOR);
		g.fillRect(0, 0, X_WIDTH, Y_WIDTH);
	}
	
	private static boolean four, two, zero;
	
	public void paintComponent(Graphics g){
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
	}
	
	private class Key extends KeyAdapter{
		
		public void keyPressed(KeyEvent e){
			if(e.getKeyCode() == KeyEvent.VK_NUMPAD4 || e.getKeyCode() == KeyEvent.VK_4){
				four = true;
				two = false;
				zero = false;
			}
			if(e.getKeyCode() == KeyEvent.VK_NUMPAD2 || e.getKeyCode() == KeyEvent.VK_2){
				if(four && !two){
					two = true;
					zero = false;
				}else{
					four = false;
					two = false;
					zero = false;
				}
			}
			if(e.getKeyCode() == KeyEvent.VK_NUMPAD0 || e.getKeyCode() == KeyEvent.VK_0){
				if(two){
					eye.setColor(new Color(255, 220, 220));
					eye2.setColor(new Color(255, 220, 220));
				}
				four = false;
				two = false;
				zero = false;
			}
			if(e.getKeyCode() == KeyEvent.VK_ENTER){
				eye.setColor(Color.white);
				eye2.setColor(Color.white);
			}
		}
	}
	
	private class TimerListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			g.setColor(BACKGROUND_COLOR);
			g.fillRect(0, 0, X_WIDTH, Y_WIDTH);
			
			eye.updateEyes((int)(MouseInfo.getPointerInfo().getLocation().getX() - getLocationOnScreen().getX()), (int)(MouseInfo.getPointerInfo().getLocation().getY() - getLocationOnScreen().getY()));
			eye2.updateEyes((int)(MouseInfo.getPointerInfo().getLocation().getX() - getLocationOnScreen().getX()), (int)(MouseInfo.getPointerInfo().getLocation().getY() - getLocationOnScreen().getY()));
				
			eye.draw(g);
			eye2.draw(g);
			repaint();
		}
	}
}
