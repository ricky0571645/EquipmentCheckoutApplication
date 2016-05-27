import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class SettingsOptionsPanel extends JPanel 
{

	private MainWindow tester;
	private DataLists dataList;
	
	public SettingsOptionsPanel(DataLists dataList) 
	{
		this.dataList = dataList;
		setBackground(Color.WHITE);
		setLayout(null);
		

		
		JButton addUserButton = new JButton("Add User");
		addUserButton.setBackground(Color.WHITE);
		addUserButton.setForeground(Color.BLACK);
		addUserButton.setFont(new Font("Calibri", Font.BOLD, 18));
		addUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		addUserButton.setBounds(32, 28, 167, 79);
		add(addUserButton);
		
		JButton removeUserButton = new JButton("Remove User");
		removeUserButton.setBackground(Color.WHITE);
		removeUserButton.setForeground(Color.BLACK);
		removeUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		removeUserButton.setFont(new Font("Calibri", Font.BOLD, 18));
		removeUserButton.setBounds(231, 28, 186, 79);
		add(removeUserButton);
		
		JLabel userIcon = new JLabel("");
		userIcon.setBounds(178, 11, 94, 101);
		add(userIcon);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setFont(new Font("Calibri", Font.BOLD, 18));
		cancelButton.setBackground(Color.decode("#ff6666"));
		cancelButton.setBounds(180, 229, 89, 29);
		add(cancelButton);
		
		JButton addItemButton = new JButton("Add Equipment");
		
		addItemButton.setForeground(new Color(30, 144, 255));
		addItemButton.setFont(new Font("Calibri", Font.BOLD, 18));
		addItemButton.setBackground(Color.WHITE);
		addItemButton.setBounds(32, 125, 167, 79);
		add(addItemButton);
		
		JButton removeItemButton = new JButton("Remove Equipment");
		
		removeItemButton.setForeground(new Color(30, 144, 255));
		removeItemButton.setFont(new Font("Calibri", Font.BOLD, 18));
		removeItemButton.setBackground(Color.WHITE);
		removeItemButton.setBounds(231, 125, 186, 79);
		add(removeItemButton);
		
		
		cancelButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(tester != null)
				{
					tester.swapView("main");
					tester.setSize(340, 510);
					tester.getCards().setSize(340, 510);
				}
			}
		});
		
		addUserButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				AddUserPanel addUserPanel = new AddUserPanel(dataList);
				if(tester != null)
				{
					addUserPanel.setTester(tester);
					tester.getCards().add(addUserPanel, "addUserPanel");
					tester.swapView("addUserPanel");
					tester.setSize(400, 380);
					tester.getCards().setSize(400, 380);
				}
			}
		});
		
		addItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				AddEquipmentPanel addEquipmentPanel = new AddEquipmentPanel(dataList);
				if(tester != null)
				{
					addEquipmentPanel.setTester(tester);
					tester.getCards().add(addEquipmentPanel, "addEquipmentPanel");
					tester.swapView("addEquipmentPanel");
					tester.setSize(490, 400);
					tester.getCards().setSize(490, 400);
				}
			}
		});
		
		removeUserButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				RemoveUserPanel removeUserPanel = new RemoveUserPanel(dataList);
				if(tester != null)
				{
					removeUserPanel.setTester(tester);
					tester.getCards().add(removeUserPanel, "removeUserPanel");
					tester.swapView("removeUserPanel");
					tester.setSize(460, 380);
					tester.getCards().setSize(460, 380);
				}
			}
		});
		
		removeItemButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				RemoveEquipmentPanel removeEquipmentPanel = new RemoveEquipmentPanel(dataList);
				if(tester != null)
				{
					removeEquipmentPanel.setTester(tester);
					tester.getCards().add(removeEquipmentPanel, "removeEquipmentPanel");
					tester.swapView("removeEquipmentPanel");
					tester.setSize(460, 380);
					tester.getCards().setSize(460, 380);
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