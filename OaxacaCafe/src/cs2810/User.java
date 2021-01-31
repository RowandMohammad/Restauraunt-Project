package cs2810;


class User {
	private String account;
	private String pwd;
	public User(String account, String pwd) {
		super();
		this.account = account;
		this.pwd = pwd;
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
	@Override
	public boolean equals(Object obj) { 
  
		if (obj instanceof User) {
			if (this.getAccount().equals(((User) obj).getAccount())
					&& this.getPwd().equals(((User) obj).getPwd())) {
				return true;
			}
			else {
				return false;
			}
		}
		return false;
	}
 
	}


