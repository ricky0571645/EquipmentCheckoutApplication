import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.AbstractListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;



public class AddUserPanel extends JPanel 
{
	private JTextField usernameTextfield;
	private JTextField passwordTextfield;
	private JTextField badgeNumberTextfield;
	
	private MainWindow tester;
	private DataLists dataList;
	private JTextField nameTextfield;
	
	

	private JOptionPane userRemovedDialog = new JOptionPane();
	/**
	 * Create the panel.
	 */
	public AddUserPanel(DataLists dataList) 
	{
		setBackground(Color.WHITE);
		setLayout(null);
		
		URL url1 = getClass().getResource("add_user_medium.png");
		
		
		JPanel userFieldPanel = new JPanel();
		userFieldPanel.setBackground(new Color(135, 206, 235));
		userFieldPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		userFieldPanel.setBounds(11, 18, 358, 263);
		add(userFieldPanel);
		userFieldPanel.setLayout(null);
		
		JLabel usernameLabel = new JLabel("First Name:");
		usernameLabel.setFont(new Font("Calibri", Font.BOLD, 16));
		usernameLabel.setBounds(35, 23, 87, 14);
		userFieldPanel.add(usernameLabel);
		
		JLabel passwordLabel = new JLabel("Last Name:");
		passwordLabel.setFont(new Font("Calibri", Font.BOLD, 16));
		passwordLabel.setBounds(35, 66, 87, 14);
		userFieldPanel.add(passwordLabel);
		
		JLabel fullNameLabel = new JLabel("Department");
		fullNameLabel.setFont(new Font("Calibri", Font.BOLD, 16));
		fullNameLabel.setBounds(35, 107, 87, 14);
		userFieldPanel.add(fullNameLabel);
		
		JLabel userTypeLabel = new JLabel("User Type:");
		userTypeLabel.setFont(new Font("Calibri", Font.BOLD, 16));
		userTypeLabel.setBounds(35, 186, 87, 14);
		userFieldPanel.add(userTypeLabel);
		
		JLabel badgeNumberLabel = new JLabel("Email:");
		badgeNumberLabel.setFont(new Font("Calibri", Font.BOLD, 16));
		badgeNumberLabel.setBounds(35, 144, 87, 14);
		userFieldPanel.add(badgeNumberLabel);
		
		usernameTextfield = new JTextField();
		usernameTextfield.setBounds(144, 20, 161, 20);
		userFieldPanel.add(usernameTextfield);
		usernameTextfield.setColumns(10);
		
		passwordTextfield = new JTextField();
		passwordTextfield.setColumns(10);
		passwordTextfield.setBounds(144, 63, 161, 20);
		userFieldPanel.add(passwordTextfield);
		
		nameTextfield = new JTextField();
		nameTextfield.setColumns(10);
		nameTextfield.setBounds(144, 104, 161, 20);
		userFieldPanel.add(nameTextfield);
		
		final JList userTypeList = new JList();
		userTypeList.setModel(new AbstractListModel() {
			String[] values = new String[] {"Administrator", "Standard"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		userTypeList.setBounds(144, 183, 161, 42);
		userFieldPanel.add(userTypeList);
		
		badgeNumberTextfield = new JTextField();
		badgeNumberTextfield.setColumns(10);
		badgeNumberTextfield.setBounds(144, 140, 161, 20);
		userFieldPanel.add(badgeNumberTextfield);
		
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setFont(new Font("Calibri", Font.BOLD, 18));
		cancelButton.setBackground(Color.decode("#ff6666"));
		cancelButton.setBounds(67, 294, 89, 23);
		add(cancelButton);
		
		JButton confirmButton = new JButton("Confirm");
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		confirmButton.setFont(new Font("Calibri", Font.BOLD, 16));
		confirmButton.setBackground(Color.decode("#98ff98"));
		confirmButton.setBounds(223, 295, 89, 23);
		add(confirmButton);
		
		cancelButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(tester != null)
				{
					tester.swapView("userSelectionPanel");
					tester.setSize(450,320);
					tester.getCards().setSize(450,320);
				}
			}
		});
		
		
		
//		confirmButton.addActionListener(new ActionListener() 
//		{
//			public void actionPerformed(ActionEvent e) 
//			{
//				//if any of the fields are empty then 
//				if(usernameTextfield.getText().equals("") || passwordTextfield.getText().equals("") || badgeNumberTextfield.getText().equals("")
//						|| nameTextfield.getText().equals(""))
//				{
//					userRemovedDialog.setLayout(getLayout());
//					userRemovedDialog.showMessageDialog(null, "Please fill out all fields.");
//				}
//				else
//				{
//					if(!employeeList.userExists(nameTextfield.getText(), usernameTextfield.getText()))
//					{
//					newEmployee = new User(nameTextfield.getText(), userTypeList.getSelectedValue().toString(), badgeNumberTextfield.getText(), 
//							usernameTextfield.getText(), passwordTextfield.getText());
//					
//					employeeList.addUser(newEmployee);
//					
//					userRemovedDialog.setLayout(getLayout());
//					userRemovedDialog.showMessageDialog(null, "User added!");
//					}
//					
//					if(tester != null)
//					{
//						tester.swapView("userModPanel");
//						tester.setSize(450, 320);
//					}
//				}
//			}
//		});

	}
	
	
	//alows for switching between cards
	public void setTester(MainWindow tester)
	{
		this.tester = tester;
	}
}