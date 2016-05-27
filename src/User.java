
public class User 
{
	String firstName;
	String lastName;
	String department;
	String email;
	String accountType;
	
	public User(String firstName, String lastName, String department, String email, String accountType) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.department = department;
		this.email = email;
		this.accountType = accountType;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getAccountType()
	{
		return this.accountType;
	}
	
	

}
