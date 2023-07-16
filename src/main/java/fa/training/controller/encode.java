package fa.training.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class encode {
	@Autowired
	private static PasswordEncoder x;
	public static void main(String[] args) {
		 x = new BCryptPasswordEncoder(10);
		 System.out.println(x.encode("12345678"));
	}
}	
