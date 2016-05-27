import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

public class MainWindow 
{

	private JFrame frmCceCheckoutForm;
	private CardLayout cardLayout = new CardLayout();
	private JPanel cards = new JPanel(cardLayout);
	private CheckoutInfoPanel checkoutPanel;
	private EquipmentStatusPanel equipmentStatusPanel;
	private SettingsOptionsPanel userSelectionPanel;
	private MainWindow tester;
	private JTextField textField;
	private ArrayList<Item> itemArray = new ArrayList<Item>();
	private ArrayList<User> userArray = new ArrayList<User>();
	private DataLists dataList;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frmCceCheckoutForm.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		tester = this;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		fillEquipmentArray();
		fillUserArray();
		dataList = new DataLists(itemArray, userArray);
		try {
			serialize();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		frmCceCheckoutForm = new JFrame();
		frmCceCheckoutForm.getContentPane().setBackground(SystemColor.activeCaption);
		frmCceCheckoutForm.getContentPane().setForeground(new Color(30, 144, 255));
		frmCceCheckoutForm.setTitle("CCE Checkout Form Creator");
		frmCceCheckoutForm.setBounds(100, 100, 351, 523);
		frmCceCheckoutForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCceCheckoutForm.getContentPane().setLayout(null);
		cards.setBounds(0, 0, 333, 478);
		frmCceCheckoutForm.getContentPane().add(cards);
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
	    Dimension screenSize = toolkit.getScreenSize();
	    int screenHeight = screenSize.height;
	    int screenWidth = screenSize.width;
	    frmCceCheckoutForm.setLocation(screenWidth / 4, screenHeight / 4);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel.setBounds(50, 43, 233, 385);
		//frmCceCheckoutForm.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton checkoutFormButton = new JButton("Create Checkout Form");
		checkoutFormButton.setFont(new Font("Calibri", Font.PLAIN, 16));
		checkoutFormButton.setFocusPainted(false);
		
		checkoutFormButton.setBounds(62, 144, 208, 48);
		panel.add(checkoutFormButton);
		
		JButton equipmentStatusButton = new JButton("Check Equipment Status");
		equipmentStatusButton.setFont(new Font("Calibri", Font.PLAIN, 16));
		equipmentStatusButton.setBounds(62, 216, 208, 44);
		equipmentStatusButton.setFocusPainted(false);
		panel.add(equipmentStatusButton);
		
		
		JLabel titleLabel = new JLabel("Equipment Tracker");
		titleLabel.setBounds(85, 94, 162, 22);
		panel.add(titleLabel);
		titleLabel.setBackground(Color.WHITE);
		titleLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
		
		cards.add(panel, "main");
		
		textField = new JTextField();
		textField.setVisible(false);
		textField.setEditable(false);
		textField.setEnabled(false);
		textField.setBounds(12, 440, 116, 22);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\martinezr\\workspace\\AutoCheckoutForm\\CCELogo_Small.png"));
		lblNewLabel.setBounds(29, 13, 275, 68);
		panel.add(lblNewLabel);
		
		JButton settingsButton = new JButton("Settings");
		
		
		
		settingsButton.setFont(new Font("Calibri", Font.PLAIN, 16));
		settingsButton.setFocusPainted(false);
		settingsButton.setBounds(62, 286, 208, 44);
		panel.add(settingsButton);
		
		
		
		checkoutFormButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				checkoutPanel = new CheckoutInfoPanel(dataList);
				cards.add(checkoutPanel, "checkoutPanel");
				checkoutPanel.setTester(tester);
				swapView("checkoutPanel");		
				frmCceCheckoutForm.setSize(550,740);
				frmCceCheckoutForm.setResizable(false);
				cards.setSize(550,740);
			}
		});
		
		equipmentStatusButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				equipmentStatusPanel = new EquipmentStatusPanel(dataList);
				cards.add(equipmentStatusPanel, "equipmentStatusPanel");
				equipmentStatusPanel.setTester(tester);
				swapView("equipmentStatusPanel");
				frmCceCheckoutForm.setSize(870,400);
				cards.setSize(870,400);
			}
		});
		
		settingsButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				SettingsOptionsPanel userSelectionPanel = new SettingsOptionsPanel(dataList);
//				equipmentStatusPanel = new EquipmentStatusPanel(dataList);
				cards.add(userSelectionPanel, "userSelectionPanel");
				userSelectionPanel.setTester(tester);
				swapView("userSelectionPanel");
				frmCceCheckoutForm.setSize(450,320);
				cards.setSize(450,320);
			}
		});
		
		
	}
	
	public void fillEquipmentArray()
	{
		//conditions for importing data
		String csvFile = "equipmentList.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		
		try 
		{

			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {

			        // use comma as separator
				String[] item = line.split(cvsSplitBy);
				
				itemArray.add(new Item(item[0], item[1], item[2], item[3],
						item[4], item[5]));

		}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	//fill user array
	public void fillUserArray()
	{
		//conditions for importing data
		String csvFile = "userList.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		
		try 
		{

			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {

			        // use comma as separator
				String[] item = line.split(cvsSplitBy);
				
				userArray.add(new User(item[1], item[2], item[0], item[4], item[5]
					));
				

		}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	public void swapView(String key) 
	{
	      cardLayout.show(cards, key);
	}
	
	public JPanel getCards()
	{
		return cards;
	}
	
	public void setSize(int width, int height)
	{
		frmCceCheckoutForm.setSize(width, height);
	}
	
	 public  void deserialize() throws Exception 
	 {
		 //Employee e = null;
	      try
	      {
	         FileInputStream fileIn = new FileInputStream("./dataList.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         this.dataList = (DataLists) in.readObject();
	         in.close();
	         fileIn.close();
	      }catch(IOException i)
	      {
	         i.printStackTrace();
	         return;
	      }catch(ClassNotFoundException c)
	      {
	         System.out.println("Employee class not found");
	         c.printStackTrace();
	         return;
	      }
	 }
	
	public void serialize() throws Exception 
	{
		try
	      {
	         FileOutputStream fileOut = new FileOutputStream("./dataList.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(this.dataList);
	         out.close();
	         fileOut.close();
	         System.out.printf("Serialized data is saved in ./dataList.ser");
	      }catch(IOException i)
	      {
	          i.printStackTrace();
	      }
	 } 
}
