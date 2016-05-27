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
	private int totalItemCount;
	
	
	public DataLists(ArrayList<Item> itemArray, ArrayList<User> userArray)
	{
		this.itemArray = itemArray;
		this.userArray = userArray;
		totalItemCount = itemArray.size();
		generateListData();
		generateItemData();
	}
	
	public void generateListData()
	{
		int index = 0;
		this.userNames = new String[userArray.size() + 1];
		this.adminUsers = new String[userArray.size()];
		for(int i = 0; i < userArray.size() + 1; i++)
		{
			if(i == 0)
			{
				userNames[i] = "";
			}
			else
			{
				userNames[i] = userArray.get(i - 1).getFirstName() + " " + userArray.get(i - 1).getLastName();
				if(userArray.get(i - 1).accountType.equalsIgnoreCase("admin"))
				{
					adminUsers[index] = userNames[i];
					index++;
				}
			}
		}
		
	}
	
	public void generateItemData()
	{
		descendingItemArray = new ArrayList<Item>();
		remainingItems = itemArray.size();
	}
	
	public void setTotalItemCount(int updatedTotalCount)
	{
		this.totalItemCount = updatedTotalCount;
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
	
	public int getTotalItemCount()
	{
		return this.totalItemCount;
	}
	
	public ArrayList<Item> getdescendingItemArray()
	{
		return this.descendingItemArray;
	}
	
	public void setRemainingItems(int updatedRemainingItems)
	{
		this.remainingItems = updatedRemainingItems;
	}
	
	

}
