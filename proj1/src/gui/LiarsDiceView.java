package gui;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import liarsDiceModel.Facade;

public class LiarsDiceView extends JPanel {

    private Facade facade;
    
	public LiarsDiceView(Facade f){
		facade = f;
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	}
}
