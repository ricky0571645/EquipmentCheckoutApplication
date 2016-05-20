import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;

public class EquipmentStatusPanel extends JPanel 
{
	private JTable checkedOutTable;
	private JTable overdueTable;
	private MainWindow tester;
	private ArrayList<Item> itemArray;
	private ArrayList<User> userArray;
	DefaultTableModel checkedOutTableModel;
	DefaultTableModel overdueTableModel;
	DataLists dataList;
	DateFormat dateFormatIncident = new SimpleDateFormat("MM/dd/yyyy");
	
	

	/**
	 * Create the panel.
	 */
	public EquipmentStatusPanel(DataLists dataList) 
	{
		setBackground(Color.WHITE);
		
		this.itemArray = dataList.getItemArray();
		this.userArray = dataList.getUserArray();
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(34, 51, 379, 228);
		add(scrollPane);
		
		checkedOutTable = new JTable();
		checkedOutTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Checkout Code", "Borrower", "Due Date"
			}
		));
		scrollPane.setViewportView(checkedOutTable);
		checkedOutTableModel = (DefaultTableModel) checkedOutTable.getModel();
		fillCheckedOutTable();
		
		JLabel lblNewLabel = new JLabel("Items Currently Checked Out");
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 18));
		lblNewLabel.setBounds(115, 22, 217, 16);
		add(lblNewLabel);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(447, 51, 379, 228);
		add(scrollPane_1);
		
		overdueTable = new JTable();
		overdueTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Checkout Code", "Borrower", "Due Date"
			}
		));
		scrollPane_1.setViewportView(overdueTable);
		overdueTableModel = (DefaultTableModel) overdueTable.getModel();
		fillOverdueTable();
		
		JLabel lblItemsOverdue = new JLabel("Items Overdue");
		lblItemsOverdue.setFont(new Font("Calibri", Font.BOLD, 18));
		lblItemsOverdue.setBounds(580, 22, 113, 16);
		add(lblItemsOverdue);
		
		JButton btnCheckIn = new JButton("Check In");
		
		btnCheckIn.setBounds(212, 317, 121, 25);
		add(btnCheckIn);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setBounds(545, 317, 97, 25);
		add(cancelButton);
		
		/*-----------------------------------------
		 * -------------BUTTON ACTIONS-------------
		 -----------------------------------------*/
		
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
		
		btnCheckIn.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				for(int i = checkedOutTable.getSelectedRowCount()-1; i >= 0; i--)
				{
					itemArray.get(checkedOutTable.getSelectedRows()[i]).checkingIn();
					int selectedRow = checkedOutTable.getSelectedRows()[i];
					for(int j = 0; j < overdueTable.getRowCount(); j++)
					{
						if(checkedOutTable.getValueAt(selectedRow, 0).equals(overdueTable.getValueAt(j, 0)))
						{
							overdueTableModel.removeRow(j);
						}
					}
					checkedOutTableModel.removeRow(selectedRow);
//					overdueTableModel.removeRow(selectedRow);
					
				}
			}
		});

	}
	
	private void fillCheckedOutTable()
	{
//		model.addRow(new Object[]{itemArray.get(0).getCheckoutCode(), itemArray.get(0).getItemName(), itemArray.get(0).getModelNumber()});
		for(int i = 0; i < itemArray.size(); i++)
		{
			if(!itemArray.get(i).isItemIn())
			{
				Date itemDate = itemArray.get(i).getDueDate();
				if(itemDate != null)
					checkedOutTableModel.addRow(new Object[]{itemArray.get(i).getCheckoutCode(), itemArray.get(i).getBorrowerName(), dateFormatIncident.format(itemDate)});
				else
					checkedOutTableModel.addRow(new Object[]{itemArray.get(i).getCheckoutCode(), itemArray.get(i).getBorrowerName(), "n/a"});
			}
		}
	}
	
	
	private void fillOverdueTable()
	{
//		model.addRow(new Object[]{itemArray.get(0).getCheckoutCode(), itemArray.get(0).getItemName(), itemArray.get(0).getModelNumber()});
		Date currentDate = new Date();
		for(int i = 0; i < itemArray.size(); i++)
		{
			if(!itemArray.get(i).isItemIn() )// && itemArray.get(i).getDueDate().before(currentDate))
			{
				Date itemDate = itemArray.get(i).getDueDate();
				if(itemDate != null && itemDate.before(currentDate))
					overdueTableModel.addRow(new Object[]{itemArray.get(i).getCheckoutCode(), itemArray.get(i).getBorrowerName(), dateFormatIncident.format(itemArray.get(i).getDueDate())});
				
			}
		}
	}
	
	
	public void setTester(MainWindow tester)
	{
		this.tester = tester;
	}
	

}
