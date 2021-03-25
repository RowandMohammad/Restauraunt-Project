package cs2810;

/**
 * Utility class for managing all data for Login Account and Password
 * data members 
 * 		account: String   ==> Login Account
 * 		pwd: String  ==> Login Password
 * 		staff: String   ==> Login Staff
 *
 */

class User {
	private String account;
	private String pwd;
	private String staff;
	public User(String account, String pwd, String staff) {
		super();
		this.account = account;
		this.pwd = pwd;
		this.staff = staff;
 }
	
     /**
     * @return String object of Login Account 
     */
	public String getAccount() {
		return account;
	}
	
    /**
    *
    * @param Account
    */
	public void setAccount(String account) {
		this.account = account;
	}
	
	
    /**
    *
    * @return
    */
	public String getPwd() {
		return pwd;
	}
	
    
	
    /**
    *
    * @param password
    */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
    /**
    *
    * @return
    */
	public String getStaff() {
    return staff;
	}
	
	
	
    /**
     * Check if passes object is equal to current object or not
     * @param object to be checked
     * @return true if object instance of User
     */
	@Override
	public boolean equals(Object obj) { 
  
		if (obj instanceof User) {
			if (this.getAccount().equals(((User) obj).getAccount())
					&& this.getPwd().equals(((User) obj).getPwd()) 
					&& this.getStaff().equals(((User) obj).getStaff())) {
				return true;
			}
			else {
				return false;
			}
		}
		return false;
	}
 
	}


