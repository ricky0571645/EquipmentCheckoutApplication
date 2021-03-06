import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class RemoveUserPanel extends JPanel 
{
	
	
	private MainWindow tester;
	private JTable table;
	private DefaultTableModel defaultTable;
	private JOptionPane userRemovedDialog = new JOptionPane();
	private DataLists dataList;
	private boolean noErrors = true;

	public RemoveUserPanel(DataLists dataList) 
	{
		this.dataList = dataList;
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

		
		buildTable();
		
		table.setBackground(Color.WHITE);
		scrollPane.setViewportView(table);
		
		ListSelectionModel selectionModel = table.getSelectionModel();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setFont(new Font("Calibri", Font.BOLD, 18));
		cancelButton.setBackground(Color.decode("#ff6666"));
		cancelButton.setBounds(268, 291, 89, 31);
		add(cancelButton);
		
		JButton removeButton = new JButton("Remove");
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		removeButton.setFont(new Font("Calibri", Font.BOLD, 18));
		removeButton.setBackground(Color.decode("#98ff98"));
		removeButton.setBounds(82, 291, 98, 31);
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
		removeButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(dataList.getUserArray().size() - 1 > 0)
				{
					removeUserFromList();
					userRemovedDialog.setLayout(getLayout());
					userRemovedDialog.showMessageDialog(null, "User successfully removed");
					buildTable();
					
				}
				else
					userRemovedDialog.showMessageDialog(null, "No users to remove.");
			}
		});
		
	}
	private void removeUserFromList()
	{
		User result;
		for(int i = 0; i < dataList.getUserArray().size(); i++)
		{
			result = dataList.getUserArray().get(i);
			if((result.getFirstName() + " " + result.getLastName()).equals(defaultTable.getValueAt(table.getSelectedRow(), 0)))
			{
				dataList.getUserArray().remove(i);
			}
		}
		dataList.generateListData();
	}
	
	private void buildTable()
	{
		defaultTable.setRowCount(0);
		ArrayList<User> array = dataList.getUserArray();
		for(int i = 0; i < dataList.getUserArray().size(); i++)
		{
			User result = dataList.getUserArray().get(i);
			defaultTable.addRow(new Object[]{result.getFirstName() + " " + result.getLastName(), result.getDepartment(), result.getAccountType()});
		}
	}
	

	//alows for switching between cards
	public void setTester(MainWindow tester)
	{
		this.tester = tester;
	}
}