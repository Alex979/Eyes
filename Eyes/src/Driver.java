import javax.swing.JFrame;


public class Driver {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Eyes");
		frame.setSize(800 + 6, 800 + 28);
		frame.setLocation(100,10);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setContentPane(new EyePanel());
		frame.setVisible(true);
	}
}