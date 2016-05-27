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
	private JTextField firstNameTextField;
	private JTextField lastNameTextfield;
	private JTextField emailTextfield;
	
	private MainWindow tester;
	private DataLists dataList;
	private JTextField departmentTextfield;
	private boolean noErrors = true;
	
	

	private JOptionPane userRemovedDialog = new JOptionPane();
	/**
	 * Create the panel.
	 */
	public AddUserPanel(DataLists dataList) 
	{
		this.dataList = dataList;
		setBackground(Color.WHITE);
		setLayout(null);
		
		URL url1 = getClass().getResource("add_user_medium.png");
		
		
		JPanel userFieldPanel = new JPanel();
		userFieldPanel.setBackground(new Color(255, 255, 204));
		userFieldPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		userFieldPanel.setBounds(11, 18, 358, 263);
		add(userFieldPanel);
		userFieldPanel.setLayout(null);
		
		JLabel firstNameLabel = new JLabel("First Name:");
		firstNameLabel.setFont(new Font("Calibri", Font.BOLD, 16));
		firstNameLabel.setBounds(35, 23, 87, 14);
		userFieldPanel.add(firstNameLabel);
		
		JLabel lastNameLabel = new JLabel("Last Name:");
		lastNameLabel.setFont(new Font("Calibri", Font.BOLD, 16));
		lastNameLabel.setBounds(35, 66, 87, 14);
		userFieldPanel.add(lastNameLabel);
		
		JLabel departmentLabel = new JLabel("Department");
		departmentLabel.setFont(new Font("Calibri", Font.BOLD, 16));
		departmentLabel.setBounds(35, 107, 87, 14);
		userFieldPanel.add(departmentLabel);
		
		JLabel userTypeLabel = new JLabel("User Type:");
		userTypeLabel.setFont(new Font("Calibri", Font.BOLD, 16));
		userTypeLabel.setBounds(35, 186, 87, 14);
		userFieldPanel.add(userTypeLabel);
		
		JLabel emailLabel = new JLabel("Email:");
		emailLabel.setFont(new Font("Calibri", Font.BOLD, 16));
		emailLabel.setBounds(35, 144, 87, 14);
		userFieldPanel.add(emailLabel);
		
		firstNameTextField = new JTextField();
		firstNameTextField.setBounds(144, 20, 161, 20);
		userFieldPanel.add(firstNameTextField);
		firstNameTextField.setColumns(10);
		
		lastNameTextfield = new JTextField();
		lastNameTextfield.setColumns(10);
		lastNameTextfield.setBounds(144, 63, 161, 20);
		userFieldPanel.add(lastNameTextfield);
		
		departmentTextfield = new JTextField();
		departmentTextfield.setColumns(10);
		departmentTextfield.setBounds(144, 104, 161, 20);
		userFieldPanel.add(departmentTextfield);
		
		final JList userTypeList = new JList();
		userTypeList.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
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
		
		emailTextfield = new JTextField();
		emailTextfield.setColumns(10);
		emailTextfield.setBounds(144, 140, 161, 20);
		userFieldPanel.add(emailTextfield);
		
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setFont(new Font("Calibri", Font.BOLD, 18));
		cancelButton.setBackground(Color.decode("#ff6666"));
		cancelButton.setBounds(221, 294, 89, 23);
		add(cancelButton);
		
		JButton confirmButton = new JButton("Confirm");
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		confirmButton.setFont(new Font("Calibri", Font.BOLD, 16));
		confirmButton.setBackground(Color.decode("#98ff98"));
		confirmButton.setBounds(73, 294, 89, 23);
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
		
		
		
		confirmButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				//if any of the fields are empty then 
				if(firstNameTextField.getText().equals("") || lastNameTextfield.getText().equals("") || departmentTextfield.getText().equals("")
						|| emailTextfield.getText().equals(""))
				{
					userRemovedDialog.setLayout(getLayout());
					userRemovedDialog.showMessageDialog(null, "Please fill out all fields.");
				}
				else
				{
					String nameList[] = dataList.getNameList();
					userRemovedDialog.setLayout(getLayout());
					
					
					for(int i = 0; i < nameList.length; i++)
					{
						if((firstNameTextField.getText() + " " + lastNameTextfield.getText()).equalsIgnoreCase(nameList[i]))
						{
							noErrors = false;
							userRemovedDialog.showMessageDialog(null, "Name already taken, please choose another. ");
						}
					}
					
					if(noErrors)
					{
//						dataList.getUserArray().add(new User(firstNameTextField.getText(), lastNameTextfield.getText(), departmentTextfield.getText(),
//								emailTextfield.getText(), userTypeList.getSelectedValue().toString()));
						
						for(int i = 0; i < dataList.getUserArray().size(); i++)
						{
							User result = dataList.getUserArray().get(i);
							if(Character.toUpperCase(result.getFirstName().charAt(0)) == Character.toUpperCase(firstNameTextField.getText().charAt(0)))
							{
								dataList.getUserArray().add(i, new User(firstNameTextField.getText(), lastNameTextfield.getText(), departmentTextfield.getText(),
								emailTextfield.getText(), userTypeList.getSelectedValue().toString()));
								break;
							}
						}
						
						userRemovedDialog.setLayout(getLayout());
						userRemovedDialog.showMessageDialog(null, "User Added!");
						
						
						if(tester != null)
						{
							tester.swapView("userSelectionPanel");
							tester.setSize(450,320);
							tester.getCards().setSize(450,320);
						}
						
						dataList.generateListData();
					}
					
				}
			}
		});

	}
	
	
	//alows for switching between cards
	public void setTester(MainWindow tester)
	{
		this.tester = tester;
	}
}