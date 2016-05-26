import java.awt.Color;
import java.net.URL;
import java.util.LinkedList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;


import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class RemoveUserPanel extends JPanel 
{
	
	
	private MainWindow tester;
	private JTable table;
	private DefaultTableModel defaultTable;
	private JOptionPane userRemovedDialog = new JOptionPane();
	private DataLists dataList;

	public RemoveUserPanel(DataLists dataList) 
	{
		setBackground(Color.WHITE);
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 13, 396, 245);
		add(scrollPane);
		
		defaultTable = new DefaultTableModel();
		
		defaultTable.addColumn("Name");
		defaultTable.addColumn("Department");
		defaultTable.addColumn("User Type");
		
		table = new JTable();
		table.setModel(defaultTable);

		
//		buildTable(employeeList, currentUser);
		
		table.setBackground(Color.WHITE);
		scrollPane.setViewportView(table);
		
		ListSelectionModel selectionModel = table.getSelectionModel();
		table.setSelectionMode(selectionModel.SINGLE_SELECTION);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setFont(new Font("Calibri", Font.BOLD, 18));
		cancelButton.setBackground(Color.decode("#ff6666"));
		cancelButton.setBounds(85, 291, 89, 31);
		add(cancelButton);
		
		JButton removeButton = new JButton("Remove");
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		removeButton.setFont(new Font("Calibri", Font.BOLD, 18));
		removeButton.setBackground(Color.decode("#98ff98"));
		removeButton.setBounds(259, 291, 98, 31);
		add(removeButton);
		
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
		
		
		//remove a user from the "Employee List"
//		removeButton.addActionListener(new ActionListener() 
//		{
//			public void actionPerformed(ActionEvent e) 
//			{
//				if(employeeList.getUserList().size() - 1 > 0)
//				{
//					removeUserFromList(employeeList);
//					userRemovedDialog.setLayout(getLayout());
//					userRemovedDialog.showMessageDialog(null, "User successfully removed");
//					buildTable(employeeList, currentUser);
//				}
//				else
//					userRemovedDialog.showMessageDialog(null, "No users to remove.");
//			}
//		});
		
	}
//	private void removeUserFromList(UserList employeeList)
//	{
//		LinkedList<User> userList = employeeList.getUserList();
//		User result;
//		for(int i = 0; i < userList.size(); i++)
//		{
//			result = userList.get(i);
//			if(result.getName().equals(defaultTable.getValueAt(table.getSelectedRow(), 0)))
//				employeeList.getUserList().remove(i);
//		}
//	}
//	
//	private void buildTable(UserList employeeList, User currentUser)
//	{
//		defaultTable.setRowCount(0);
//		LinkedList<User> userList = employeeList.getUserList();
//		for(int i = 0; i < userList.size(); i++)
//		{
//			User result = userList.get(i);
//			if(!result.getName().equals(currentUser.getName()))
//				defaultTable.addRow(new Object[]{result.getName(), result.getBadgeNumber(), result.getUserType()});
//		}
//	}
	

	//alows for switching between cards
	public void setTester(MainWindow tester)
	{
		this.tester = tester;
	}
}