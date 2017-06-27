package nus.iss.cbr.cocktail.xml2linear.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import nus.iss.cbr.cocktail.xml2linear.xml.*;

public class MainFrame extends JFrame implements ActionListener {
  
	private JButton xmlBtn = new JButton("Open  XML");
	private JButton txtBtn = new JButton("XMLToTXT");
	private JLabel xmlLbl = new JLabel("XML");
	private JLabel txtLbl = new JLabel("TXT");
	private JTextField xmlTxtFld = new JTextField(60);
	private JTextField txtTxtFld = new JTextField(60);
	private JFileChooser jfc = new JFileChooser();
    
    public MainFrame(String title){
        super(title);

        this.setSize(830,150);
        this.setLocation(200, 200);
        
        JPanel openPnl = new JPanel(new FlowLayout(FlowLayout.LEFT));
        openPnl.add(xmlLbl);
        openPnl.add(xmlTxtFld);
        openPnl.add(xmlBtn);
        
        
        JPanel savePnl = new JPanel(new FlowLayout(FlowLayout.LEFT));
        savePnl.add(txtLbl);
        savePnl.add(txtTxtFld);
        savePnl.add(txtBtn);
        
        JPanel borderPnl = new JPanel(new BorderLayout());
        borderPnl.add(openPnl, BorderLayout.NORTH);
        borderPnl.add(savePnl, BorderLayout.SOUTH);
        
        JPanel container = new JPanel(new FlowLayout(FlowLayout.LEFT));
        container.add(borderPnl);
        
        this.getContentPane().add(container);
        
        xmlBtn.addActionListener(this);
        txtBtn.addActionListener(this);
        
        jfc.setFileFilter(new FileNameExtensionFilter("xml", "xml"));
        jfc.setMultiSelectionEnabled(false);
    }

    public void actionPerformed(ActionEvent arg0) {

		if(arg0.getSource() == xmlBtn) {
	        if(jfc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
	        	xmlTxtFld.setText(jfc.getSelectedFile().toString());
	        	
	        	String xmlFile = xmlTxtFld.getText();
	        	XMLParser xmlParser = new XMLParser();
	        	xmlParser.parse(xmlFile);
	        }
		} else if(arg0.getSource() == txtBtn) {
	        if(jfc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
	        	txtTxtFld.setText(jfc.getSelectedFile().toString() + "." + jfc.getFileFilter().getDescription());
	        }
		}
    }
}