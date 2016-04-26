import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.MatteBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public class CheckoutInfoPanel extends JPanel {
	private JTable itemTable;
	private MainWindow tester;
	private Sheet sheet;
	DefaultTableModel model;
	private JTextField eventTextField;
	private JOptionPane changeSuccessful = new JOptionPane();
	DateFormat dateFormatIncident = new SimpleDateFormat("MM/dd/yyyy");
	//------------LIST ITEMS FOR PROGRAM------------
	DataLists dataList;
	private ArrayList<Item> itemArray;
	private ArrayList<User> userArray;
	private ArrayList<Item> descendingItemArray;
	private ArrayList<Integer> selectedRows = new ArrayList<Integer>();
	private int totalSelected = 0;

	public CheckoutInfoPanel(DataLists dataList) 
	{
		
		this.dataList = dataList;
		this.itemArray = dataList.getItemArray();
		this.userArray = dataList.getUserArray();
		this.descendingItemArray = dataList.getdescendingItemArray();
		
		//---------------------------------------------------------------------------
	    //----------------------------***TABLE SETUP***------------------------------
	    //---------------------------------------------------------------------------
		setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(49, 35, 452, 288);
		add(scrollPane);
		
		Object[] columnNames = {"Checkout Code", "Item", "Model", "Select"};
		Object[][] data = FillItemTable();
		DefaultTableModel model = new DefaultTableModel(data, columnNames);
		
		itemTable = new JTable(model)
		{
			private static final long serialVersionUID = 1L;
            @Override
            public Class getColumnClass(int column) {
                switch (column) {
                    case 3:
                        return Boolean.class;
                    default:
                        return String.class;
                }
            }
		};
		itemTable.setRowSelectionAllowed(true);
		itemTable.getColumnModel().getColumn(3).setPreferredWidth(20);
		
		itemTable.getModel().addTableModelListener(new TableModelListener() 
		{

		      public void tableChanged(TableModelEvent e) 
		      {
		    	 int selectedRow = e.getFirstRow();
		         if(((Boolean)itemTable.getValueAt(selectedRow, e.getColumn()) == true))
        		 {
		        	 totalSelected++;
		        	 selectedRows.add(selectedRow);
        		 }
		         else if(((Boolean)itemTable.getValueAt(selectedRow, e.getColumn()) == false))
        		 {
		        	 totalSelected--;
		        	 selectedRows.remove(selectedRows.indexOf(selectedRow));
        		 }
		        	 
		      }
		});
		
	     scrollPane.setViewportView(itemTable);
	     //---------------------------------------------------------------------------
	     //----------------------------***GUI SETUP***--------------------------------
	     //---------------------------------------------------------------------------
		JLabel borrowNameLabel = new JLabel("Borrowers Name:");
		borrowNameLabel.setFont(new Font("Calibri", Font.PLAIN, 16));
		borrowNameLabel.setBounds(85, 370, 115, 16);
		add(borrowNameLabel);
		
		JLabel checkoutDateLabel = new JLabel("Checkout Date:");
		checkoutDateLabel.setFont(new Font("Calibri", Font.PLAIN, 16));
		checkoutDateLabel.setBounds(85, 417, 115, 16);
		add(checkoutDateLabel);
		
		JLabel returnDateLabel = new JLabel("Return Date:");
		returnDateLabel.setFont(new Font("Calibri", Font.PLAIN, 16));
		returnDateLabel.setBounds(85, 463, 92, 16);
		add(returnDateLabel);
		
		JLabel issuedByLabel = new JLabel("Issued By:");
		issuedByLabel.setFont(new Font("Calibri", Font.PLAIN, 16));
		issuedByLabel.setBounds(85, 551, 68, 16);
		add(issuedByLabel);
		
		JButton cancelButton = new JButton("Cancel");
		
		cancelButton.setBounds(224, 650, 97, 25);
		add(cancelButton);
		
		JButton createReportButton = new JButton("Report and Checkout");
		createReportButton.setBounds(313, 612, 153, 25);
		add(createReportButton);
		
		JList issuerList = new JList();
		issuerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		issuerList.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		String[] checkoutUsers = dataList.getAdminUsers();
		issuerList.setModel(new AbstractListModel() {
			String[] values = checkoutUsers;
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		issuerList.setBounds(292, 548, 174, 43);
		add(issuerList);
		
		JCheckBox longtermCheckbox = new JCheckBox("Long Term Checkout");
		
		longtermCheckbox.setBounds(354, 332, 147, 25);
		add(longtermCheckbox);
		
		JLabel lblEventName = new JLabel("Event Name:");
		lblEventName.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblEventName.setBounds(85, 504, 92, 16);
		add(lblEventName);
		
		eventTextField = new JTextField();
		eventTextField.setColumns(10);
		eventTextField.setBounds(292, 500, 174, 22);
		add(eventTextField);
		
		JXDatePicker checkoutDatePicker = new JXDatePicker();
		checkoutDatePicker.getEditor().setLocation(0, 360);
		checkoutDatePicker.setBounds(292, 412, 174, 24);
		add(checkoutDatePicker);
		
		JXDatePicker returnDatePicker = new JXDatePicker();
		returnDatePicker.setBounds(292, 458, 174, 24);
		add(returnDatePicker);
		
		String[] tab = dataList.getNameList();
		
		JComboBox nameComboBox = new JComboBox(tab);
		nameComboBox.setMaximumRowCount(0);
		nameComboBox.setEditable(true);
		nameComboBox.setBounds(292, 366, 174, 22);
		add(nameComboBox);
		
		AutoCompleteDecorator.decorate(nameComboBox);
		
		JButton checkoutButton = new JButton("Checkout");
		
		checkoutButton.setBounds(79, 612, 153, 25);
		add(checkoutButton);
		
		//---------------------------------------------------------------------------
	    //----------------------------***BUTTON ACTION***----------------------------
	    //---------------------------------------------------------------------------
	
		longtermCheckbox.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(longtermCheckbox.isSelected())
				{
					returnDatePicker.setEnabled(false);
				}
				else
				{
					returnDatePicker.setEnabled(true);
				}
				
			}
		});
		
		
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
		
		Date fakeDate = new Date();
		
		checkoutButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(itemTable.getSelectedRowCount() <= 8){
					
				if(checkoutDatePicker.getDate() != null)
					itemCheckedOut(tab[nameComboBox.getSelectedIndex()].toString(), returnDatePicker.getDate(), "none");
				else if(longtermCheckbox.isSelected())
					itemCheckedOut(tab[nameComboBox.getSelectedIndex()].toString(), fakeDate, "LONGTERM");
				else
					itemCheckedOut(tab[nameComboBox.getSelectedIndex()].toString(),fakeDate, " ");
				
				changeSuccessful.setLayout(getLayout());
				changeSuccessful.showMessageDialog(null, "Item(s) Successfully Checked Out!");
				
				if(tester != null)
				{
					tester.swapView("main");
					tester.setSize(340, 510);
					tester.getCards().setSize(340, 510);
				}
				}
				else
				{
					changeSuccessful.setLayout(getLayout());
					changeSuccessful.showMessageDialog(null, "Please limit checkout items to 8 or less.");
				}
				
			}
		});
		
		createReportButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				try {
					
					if(itemTable.getSelectedRowCount() <= 8){
					//create a file, a workbook, and add the workbook to the file
					FileInputStream fileIn = new FileInputStream(new File("checkoutForm.xlsx"));
					Workbook wb = new XSSFWorkbook(fileIn);
					
					sheet = wb.getSheetAt(0);
					
					Cell nameCell = sheet.getRow(21+4).getCell(3);
					Cell issuedByCell = sheet.getRow(10+4).getCell(1);
					Cell checkoutDateCell = sheet.getRow(10+4).getCell(5);
					Cell returnDateCell = sheet.getRow(11+4).getCell(5);
					Cell eventCell = sheet.getRow(16).getCell(1);
					
					//-------------ADD ITEMS TO EXCEL SHEET-----------
					clearExcelFile(nameCell,issuedByCell, checkoutDateCell, returnDateCell, eventCell);
					fillExcelFile(nameCell,issuedByCell, checkoutDateCell, returnDateCell, eventCell, fakeDate, nameComboBox,
							issuerList, checkoutDatePicker, returnDatePicker, longtermCheckbox, tab);
					addItemsToExcel();
					
					//-------------COMPLETE EXCEL WRITE TO-----------
					fileIn.close();
					FileOutputStream output_file =new FileOutputStream(new File("checkoutForm.xlsx")); 
	                wb.write(output_file); //write changes
	                output_file.close();  //close the stream 
	                launchExcelReport();
	                //-----------------------------------------------
	                
	                changeSuccessful.setLayout(getLayout());
					changeSuccessful.showMessageDialog(null, "Item(s) Successfully Checked Out!");
					
					if(tester != null)
					{
						tester.swapView("main");
						tester.setSize(340, 510);
						tester.getCards().setSize(340, 510);
					}
					}
					else
					{
						changeSuccessful.setLayout(getLayout());
						changeSuccessful.showMessageDialog(null, "Please limit checkout items to less than 8.");
					}

				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// create a new workbook
				catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

	}
	
	//---------------------------------------------------------------------------
    //----------------------------***METHOD HELPERS***--------------------------------
    //---------------------------------------------------------------------------
	
	private void clearExcelFile(Cell nameCell,Cell issuedByCell, Cell checkoutDateCell, Cell returnDateCell,Cell eventCell)
	{
		nameCell.setCellValue("");
		issuedByCell.setCellValue("");
		checkoutDateCell.setCellValue("");
		returnDateCell.setCellValue("");
		eventCell.setCellValue("");
	}
	
	private void fillExcelFile(Cell nameCell,Cell issuedByCell, Cell checkoutDateCell, Cell returnDateCell,Cell eventCell,
			Date fakeDate, JComboBox nameComboBox, JList issuerList, JXDatePicker checkoutDatePicker, JXDatePicker returnDatePicker,
			JCheckBox longtermCheckbox, String[] tab)
	{
		eventCell.setCellValue(eventTextField.getText());
		if(nameComboBox.getSelectedIndex() != -1)
			nameCell.setCellValue(tab[nameComboBox.getSelectedIndex()].toString());
		else
			nameCell.setCellValue(nameComboBox.getSelectedItem().toString());
		
		if(issuerList.isSelectionEmpty())
			issuedByCell.setCellValue("Ricky A. Martinez");
		else
			issuedByCell.setCellValue(issuerList.getSelectedValue().toString());
		
		if(checkoutDatePicker.getDate() != null)
		{
			
			checkoutDateCell.setCellValue(dateFormatIncident.format(checkoutDatePicker.getDate()));
		}
		
		if(returnDatePicker.getDate() != null)
		{
			
			returnDateCell.setCellValue(dateFormatIncident.format(returnDatePicker.getDate()));
		}
		else if(longtermCheckbox.isSelected())
		{
			
			returnDateCell.setCellValue("LONGTERM");
		}
		
		
		if(checkoutDatePicker.getDate() != null)
			itemCheckedOut(tab[nameComboBox.getSelectedIndex()].toString(), returnDatePicker.getDate(), "none");//dateFormatIncident.format(returnDatePicker.getDate()));
		else if(longtermCheckbox.isSelected())
			itemCheckedOut(tab[nameComboBox.getSelectedIndex()].toString(), fakeDate, "LONGTERM");
		else
		{
			itemCheckedOut(nameCell.getStringCellValue(), fakeDate, "nothing");
		}
	}
	
	
	/*
	 * THIS METHOD WILL ADD THE CHECKOUT ITEMS TO THE EXCEL FILE
	 */
	private void addItemsToExcel()
	{
		if(itemTable.getSelectedRowCount() <= 8)
		{
			for(int i = 0; i < totalSelected; i++ )
			{
				for(int j = 0; j < 6; j++)
				{
					Cell populate = sheet.getRow(5 + i).getCell(j);
					switch(j)
					{
						case 0: populate.setCellValue(descendingItemArray.get(selectedRows.get(i)).getCheckoutCode()); break;
						case 1: populate.setCellValue(descendingItemArray.get(selectedRows.get(i)).getItemName()); break;
						case 2: populate.setCellValue(descendingItemArray.get(selectedRows.get(i)).getModelNumber()); break;
						case 3: populate.setCellValue(descendingItemArray.get(selectedRows.get(i)).getSerialNumber()); break;
						case 4: populate.setCellValue(descendingItemArray.get(selectedRows.get(i)).getDecalNumber()); break;
						case 5: if(descendingItemArray.get(selectedRows.get(i)).getCost().equals("n/a"))
									populate.setCellValue(descendingItemArray.get(selectedRows.get(i)).getCost());
								else
									populate.setCellValue("$" + descendingItemArray.get(selectedRows.get(i)).getCost() + ".00"); 
								break;
					}
					
				}

			}
			//removes any previous entries on the list
			for(int i = totalSelected; i < 8; i++)
			{
				for(int j = 0; j < 6; j++)
				{
					Cell populate = sheet.getRow(5 + i).getCell(j);
					switch(j)
					{
						case 0: populate.setCellValue(" "); break;
						case 1: populate.setCellValue(" "); break;
						case 2: populate.setCellValue(" "); break;
						case 3: populate.setCellValue(" "); break;
						case 4: populate.setCellValue(" "); break;
						case 5: populate.setCellValue(" "); break;
					}
					
				}
			}
		}
	}
	
	
	private Object[][] FillItemTable()
	{
		int x = 0;
		Object[][] data = new Object[itemArray.size()][4];
		for(int i = 0; i < dataList.getRemainingItems(); i++)
		{
			if(itemArray.get(i).isItemIn())
			{
				descendingItemArray.add(itemArray.get(i));
				data[x][0] = itemArray.get(i).getCheckoutCode();
				data[x][1] = itemArray.get(i).getItemName();
				data[x][2] = itemArray.get(i).getModelNumber();
				data[x][3] = false;
				x++;
			}
		}
		return data;
	}
	
	public void setTester(MainWindow tester)
	{
		this.tester = tester;
	}
	
	private void launchExcelReport()
	{
		if (!Desktop.isDesktopSupported()) {
	        System.err.println("Desktop not supported");
	        // use alternative (Runtime.exec)
	        return;
	    }

	    Desktop desktop = Desktop.getDesktop();
	    if (!desktop.isSupported(Desktop.Action.EDIT)) {
	        System.err.println("EDIT not supported");
	        // use alternative (Runtime.exec)
	        return;
	    }

	    try {
	        desktop.open(new File("checkoutForm.xlsx"));
	    } catch (IOException ex) {
	        ex.printStackTrace();
	    }
	}
	
	//----------REPLACING THIS METHODS!!!
//	private void itemCheckedOut(String name, Date dueDate, String dateAlternative)
//	{
//		for(int i = 0; i < itemTable.getSelectedRowCount(); i++ )
//		{
//			itemArray.get(itemTable.getSelectedRows()[i]).checkingOut(name, dueDate, dateAlternative);
//		}
//		
//	}
	
	private void itemCheckedOut(String name, Date dueDate, String dateAlternative)
	{
		for(int i = 0; i < itemArray.size(); i++ )
		{
			for(int j = 0; j < totalSelected; j++)
			{
				if(itemArray.get(i).equals(descendingItemArray.get(selectedRows.get(j))))
				{
					itemArray.get(i).checkingOut(name, dueDate, dateAlternative);
				}
			}
//			itemArray.get(itemTable.getSelectedRows()[i]).checkingOut(name, dueDate, other);
		}
	}
}
