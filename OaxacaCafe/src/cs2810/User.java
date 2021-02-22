package cs2810;


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
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getStaff() {
    return staff;
	}
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


