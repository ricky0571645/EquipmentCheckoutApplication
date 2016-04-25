import java.util.ArrayList;

import javax.swing.JList;

public class DataLists 
{
	private ArrayList<Item> itemArray;
	private ArrayList<User> userArray;
	private String[] userNames;
	private String[] adminUsers;
	private int remainingItems;
	private ArrayList<Item> descendingItemArray;
	
	
	public DataLists(ArrayList<Item> itemArray, ArrayList<User> userArray)
	{
		this.itemArray = itemArray;
		this.userArray = userArray;
		generateListData();
	}
	
	private void generateListData()
	{
		int index = 0;
		this.userNames = new String[userArray.size()];
		this.adminUsers = new String[userArray.size()];
		for(int i = 0; i < userArray.size(); i++)
		{
			userNames[i] = userArray.get(i).getFirstName() + " " + userArray.get(i).getLastName();
			if(userArray.get(i).accountType.equalsIgnoreCase("admin"))
			{
				adminUsers[index] = userNames[i];
				index++;
			}
		}
		descendingItemArray = new ArrayList<Item>();
		remainingItems = itemArray.size();
	}
	
	public String[] getNameList()
	{
		return this.userNames;
	}
	
	public ArrayList<Item> getItemArray()
	{
		return this.itemArray;
	}
	
	public ArrayList<User> getUserArray()
	{
		return this.userArray;
	}
	
	public String[] getAdminUsers()
	{
		return adminUsers;
	}
	
	public void updateRemainingItemsList(int removedItems)
	{
		this.remainingItems = this.remainingItems - removedItems;
	}
	
	public int getRemainingItems()
	{
		return this.remainingItems;
	}
	
	public ArrayList<Item> getdescendingItemArray()
	{
		return this.descendingItemArray;
	}
	
	

}
