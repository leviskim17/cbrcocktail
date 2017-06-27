package nus.iss.cbr.cocktail.xml2linear;

import nus.iss.cbr.cocktail.xml2linear.gui.*;
import javax.swing.*;

public class Application {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable()
	    {
			public void run()
			{
				MainFrame mainFrame = new MainFrame("XMLTOLINEAR");
				mainFrame.setVisible(true);
			}
	    });
	}
}
