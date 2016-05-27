import java.awt.Color;
import java.net.URL;
import java.util.ArrayList;
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

public class RemoveEquipmentPanel extends JPanel 
{
	
	
	private MainWindow tester;
	private JTable table;
	private DefaultTableModel defaultTable;
	private JOptionPane userRemovedDialog = new JOptionPane();
	private DataLists dataList;

	public RemoveEquipmentPanel(DataLists dataList) 
	{
		this.dataList = dataList;
		setBackground(Color.WHITE);
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 13, 396, 245);
		add(scrollPane);
		
		defaultTable = new DefaultTableModel();
		
		//"Checkout Code", "Item", "Model"
		defaultTable.addColumn("Checkout Code");
		defaultTable.addColumn("Item");
		defaultTable.addColumn("Model");
		
		table = new JTable();
		table.setModel(defaultTable);

		
		buildTable();
		
		table.setBackground(Color.WHITE);
		scrollPane.setViewportView(table);
		
		ListSelectionModel selectionModel = table.getSelectionModel();
		table.setSelectionMode(selectionModel.SINGLE_SELECTION);
		
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
					removeItemFromList();
					userRemovedDialog.setLayout(getLayout());
					userRemovedDialog.showMessageDialog(null, "Equipment successfully removed");
					buildTable();
					
				}
				else
					userRemovedDialog.showMessageDialog(null, "No equipment to remove.");
			}
		});
		
	}
	private void removeItemFromList()
	{
		Item result;
		for(int i = 0; i < dataList.getItemArray().size(); i++)
		{
			result = dataList.getItemArray().get(i);
			if(result.getCheckoutCode().equals(defaultTable.getValueAt(table.getSelectedRow(), 0)))
			{
				dataList.getItemArray().remove(i);
			}
		}
		dataList.setRemainingItems(dataList.getRemainingItems() - 1);
		dataList.setTotalItemCount(dataList.getTotalItemCount() - 1);
	}
	
	private void buildTable()
	{
		defaultTable.setRowCount(0);
		for(int i = 0; i < dataList.getItemArray().size(); i++)
		{
			Item result = dataList.getItemArray().get(i);
			defaultTable.addRow(new Object[]{result.getCheckoutCode(), result.getItemName(), result.getModelNumber()});
		}
	}

	//alows for switching between cards
	public void setTester(MainWindow tester)
	{
		this.tester = tester;
	}
}