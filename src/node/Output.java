package node;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Output extends JFrame {
	
	public Output(){
		init();
	}

	private void init() {
		this.pack();
		this.setSize(666, 666);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
	
	public void showResult(double power){
		JPanel powerColor = new JPanel();
		powerColor.setBackground(getColor(power));
		this.add(powerColor);
	}
	
	private Color getColor(double power){
		return new Color(new Double(255-255*power).intValue(), new Double(255*power).intValue(), 0);
	}
}
