package PowerSchool.src.powerschool;

import PowerSchool.src.com.greg.utils.Searchable;

public abstract class Person implements Searchable, Writable
{
	protected String username;
    protected String password;
    protected String firstName;
    protected String lastName;
    
    public Person()
    {
    	username = null;
    	password = null;
    	firstName = null;
    	lastName = null;
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
    
	@Override
    public String getString()
    {
        return firstName+" "+lastName;
    }

    @Override
    public double getNumerical()
    {
        return 0;
    }
    

}
