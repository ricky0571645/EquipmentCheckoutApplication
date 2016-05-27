import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;



public class AddEquipmentPanel extends JPanel 
{
	private JTextField checkoutCodeTextfield;
	private JTextField itemNameTextfield;
	private JTextField serialNumberTextfield;
	
	private MainWindow tester;
	private DataLists dataList;
	private JTextField modelNumberTextfield;
	
	

	private JOptionPane userRemovedDialog = new JOptionPane();
	private JTextField decalNumberTextField;
	private JTextField costTextField;
	private boolean noErrors = true;
	private int cost;
	/**
	 * Create the panel.
	 */
	public AddEquipmentPanel(DataLists dataList) 
	{
		setBackground(Color.WHITE);
		setLayout(null);
		
		URL url1 = getClass().getResource("add_user_medium.png");
		
		
		JPanel userFieldPanel = new JPanel();
		userFieldPanel.setBackground(new Color(204, 204, 204));
		userFieldPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		userFieldPanel.setBounds(11, 18, 443, 290);
		add(userFieldPanel);
		userFieldPanel.setLayout(null);
		
		JLabel checkoutCodeLabel = new JLabel("Checkout Code:");
		checkoutCodeLabel.setFont(new Font("Calibri", Font.BOLD, 16));
		checkoutCodeLabel.setBounds(36, 44, 120, 14);
		userFieldPanel.add(checkoutCodeLabel);
		
		JLabel itemNameLabel = new JLabel("Item Name:");
		itemNameLabel.setFont(new Font("Calibri", Font.BOLD, 16));
		itemNameLabel.setBounds(36, 87, 87, 14);
		userFieldPanel.add(itemNameLabel);
		
		JLabel modelNumberLabel = new JLabel("Model Number:");
		modelNumberLabel.setFont(new Font("Calibri", Font.BOLD, 16));
		modelNumberLabel.setBounds(36, 128, 110, 14);
		userFieldPanel.add(modelNumberLabel);
		
		JLabel decalNumberLabel = new JLabel("Decal Number:");
		decalNumberLabel.setFont(new Font("Calibri", Font.BOLD, 16));
		decalNumberLabel.setBounds(36, 205, 110, 14);
		userFieldPanel.add(decalNumberLabel);
		
		JLabel serialNumberLabel = new JLabel("Serial Number:");
		serialNumberLabel.setFont(new Font("Calibri", Font.BOLD, 16));
		serialNumberLabel.setBounds(36, 164, 110, 14);
		userFieldPanel.add(serialNumberLabel);
		
		checkoutCodeTextfield = new JTextField();
		checkoutCodeTextfield.setBounds(168, 40, 243, 20);
		userFieldPanel.add(checkoutCodeTextfield);
		checkoutCodeTextfield.setColumns(10);
		
		itemNameTextfield = new JTextField();
		itemNameTextfield.setColumns(10);
		itemNameTextfield.setBounds(168, 83, 243, 20);
		userFieldPanel.add(itemNameTextfield);
		
		modelNumberTextfield = new JTextField();
		modelNumberTextfield.setColumns(10);
		modelNumberTextfield.setBounds(168, 124, 243, 20);
		userFieldPanel.add(modelNumberTextfield);
		
		serialNumberTextfield = new JTextField();
		serialNumberTextfield.setColumns(10);
		serialNumberTextfield.setBounds(168, 160, 243, 20);
		userFieldPanel.add(serialNumberTextfield);
		
		decalNumberTextField = new JTextField();
		decalNumberTextField.setColumns(10);
		decalNumberTextField.setBounds(168, 201, 243, 20);
		userFieldPanel.add(decalNumberTextField);
		
		JLabel costLabel = new JLabel("Cost:");
		costLabel.setFont(new Font("Calibri", Font.BOLD, 16));
		costLabel.setBounds(36, 248, 87, 14);
		userFieldPanel.add(costLabel);
		
		costTextField = new JTextField();
		costTextField.setColumns(10);
		costTextField.setBounds(168, 244, 243, 20);
		userFieldPanel.add(costTextField);
		
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setFont(new Font("Calibri", Font.BOLD, 18));
		cancelButton.setBackground(Color.decode("#ff6666"));
		cancelButton.setBounds(277, 323, 89, 23);
		add(cancelButton);
		
		JButton confirmButton = new JButton("Confirm");
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		confirmButton.setFont(new Font("Calibri", Font.BOLD, 16));
		confirmButton.setBackground(Color.decode("#98ff98"));
		confirmButton.setBounds(129, 323, 89, 23);
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
				if(checkoutCodeTextfield.getText().equals("") || itemNameTextfield.getText().equals("") || serialNumberTextfield.getText().equals("")
						|| modelNumberTextfield.getText().equals("") || decalNumberTextField.getText().equals("") || costTextField.getText().equals(""))
				{
					userRemovedDialog.setLayout(getLayout());
					userRemovedDialog.showMessageDialog(null, "Please fill out all fields.");
				}
				else
				{
					try {
					    cost = Integer.parseInt(costTextField.getText());
					    
					    dataList.getItemArray().add(new Item(checkoutCodeTextfield.getText(), itemNameTextfield.getText(), modelNumberTextfield.getText(),
								serialNumberTextfield.getText(), decalNumberTextField.getText(), costTextField.getText()));
						
						userRemovedDialog.setLayout(getLayout());
						userRemovedDialog.showMessageDialog(null, "New Item Added!");
						
						if(tester != null)
						{
							tester.swapView("userSelectionPanel");
							tester.setSize(450,320);
							tester.getCards().setSize(450,320);
						}
						
						dataList.setRemainingItems(dataList.getRemainingItems() + 1);
						dataList.setTotalItemCount(dataList.getTotalItemCount() + 1);
					} 
					catch (NumberFormatException x) 
					{
//					    System.out.println("Wrong number");
					    userRemovedDialog.setLayout(getLayout());
						userRemovedDialog.showMessageDialog(null, "Please enter a valid number.");
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