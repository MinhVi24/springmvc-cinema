package fa.training.model;


/**
 * Project:Cinema WebApp
 * Dùng để lấy dữ liệu từ form Reset pass
 */
public class ResetPassword {
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getOldpassword() {
		return oldpassword;
	}

	public void setOldpassword(String oldpassword) {
		this.oldpassword = oldpassword;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}

	public ResetPassword() {
		super();
	}

	public ResetPassword(String account, String oldpassword, String password, String repassword) {
		super();
		this.account = account;
		this.oldpassword = oldpassword;
		this.password = password;
		this.repassword = repassword;
	}

	String account;

	String oldpassword;
	
	String password;

	String repassword;


}
