import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class GUI extends JFrame {
	public int option=0;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		setTitle("keExtremePotions");
		setBackground(Color.ORANGE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 315, 278);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblOptimalHerbGui = new JLabel("keExtremePotions");
		lblOptimalHerbGui.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		lblOptimalHerbGui.setBounds(81, 11, 133, 36);
		contentPane.add(lblOptimalHerbGui);
		
		final JComboBox comboBox = new JComboBox();
		comboBox.setMaximumRowCount(10);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"-Select Potion-", "Extreme Attack", "Extreme Strength", "Extreme Defence", "Extreme Ranging", "Extreme Magic", "Overload", "Grind Mud Runes", "Overload Flasks"}));
		comboBox.setBounds(91, 58, 110, 20);
		contentPane.add(comboBox);
		
		JButton btnStart = new JButton("Start");
		
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String unfPot=comboBox.getSelectedItem().toString();
				if (unfPot.equals("Extreme Attack")){
					option=1;
				}
				else if(unfPot.equals("Extreme Strength")){
					option=2;
				}
				else if(unfPot.equals("Extreme Defence")){
					option=3;
				}
				else if(unfPot.equals("Extreme Ranging")){
					option=4;
				}
				else if(unfPot.equals("Extreme Magic")){
					option=5;
				}
				else if(unfPot.equals("Overload")){
					option=6;
				}
				else if(unfPot.equals("Grind Mud Runes")){
					option=7;
				}
				else if(unfPot.equals("Overload Flasks")){
					option=8;
				}
				unfPotMaker.option=option;
				dispose();
			}
		});
		btnStart.setBounds(103, 205, 89, 23);
		contentPane.add(btnStart);
		
		JTextPane txtpnExtremeAttackavantoesuperAttack = new JTextPane();
		txtpnExtremeAttackavantoesuperAttack.setText("Extreme Attack = Clean Avantoe+Super Attack\r\nExtreme Strength = Clean Dwarf Weed+Super Strength\r\nExtreme Defence = Clean Lantadyme+Super Defence\r\nExtreme Ranging =Ranging Potion+Grenwall Spikes(5)\r\nExtreme Magic = Ground Mud Runes+Magic Potion\r\nOverload = Clean Torstol + Above Extremes");
		txtpnExtremeAttackavantoesuperAttack.setBounds(10, 97, 279, 97);
		contentPane.add(txtpnExtremeAttackavantoesuperAttack);

	}
}
