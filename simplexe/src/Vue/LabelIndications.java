package Vue;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class LabelIndications extends JLabel {
	
	public LabelIndications(String str) {
		super(str);
		this.setHorizontalAlignment(CENTER);
		this.setBorder(BorderFactory.createLoweredBevelBorder());
	}

}