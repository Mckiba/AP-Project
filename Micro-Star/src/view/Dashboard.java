package mIcroDashboard;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Dashboard {
	
	
	public void Layout () {
		// will be easier i think to add a single function to generate frame 
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Micro Star Cabel company");
		JPanel head = new JPanel();
		JLabel logo = new JLabel("");
		ImageIcon icon = new ImageIcon("logo.png");
		logo.setIcon(icon);
		head.setBounds(0, 0, 100, 100);
		head.add(logo);
		
		
		
		// first panel
		JPanel ComboPanel = new JPanel();
		
		
		ComboPanel.setLayout(new FlowLayout ());
		ComboPanel.setBounds(0, 100, 150, 250);
		
		
		
		
		JLabel RegisComp = new JLabel();
		RegisComp.setText("Register Complaints");
		
		String complaints[] = {"Channel Lisitng","Pay Per View","VR"};
		JComboBox<String> combobox = new JComboBox<>(complaints);
		combobox.setSelectedIndex(0);
		
		JButton submit = new JButton("Submit");
		JTextField text = new JTextField(10);
		
		
		// second panel
		JPanel flowpanel = new JPanel();
		flowpanel.setBounds(250, 100, 200, 300);
		
		JLabel other = new JLabel("Other Actions");
		other.setText("Other Actions");
		JButton status = new JButton ("View Account Status");		
		JButton lastPay = new JButton ("Veiw Last Payment");
		JButton veiwComp = new JButton ("Veiw complaints");
		JButton veiwAllPay = new JButton ("Veiw all Payments");
		
		flowpanel.add(other);
		flowpanel.add(status);
		flowpanel.add(lastPay);
		flowpanel.add(veiwComp);
		flowpanel.add(veiwAllPay);
		
		
		
		//casting to frame
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setSize(600, 400);
		frame.setVisible(true);
		
		
		
		ComboPanel.add(RegisComp);
		ComboPanel.add(combobox);
		ComboPanel.add(text);
		ComboPanel.add(submit);
		frame.add(head);
		frame.add(ComboPanel);
		frame.add(flowpanel);
		
		
		
		
		
		

	}

}
