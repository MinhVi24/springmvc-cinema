package fa.training.security;

import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import fa.training.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		/*
		 * Tắt tính năng chống tấn công CSRFC của Spring security (Cross-Site Request
		 * Forgery là một kỹ thuật tấn công web mà kẻ tấn công có thể gửi một request từ
		 * một trang web khác đến ứng dụng của bạn để thực hiện một hành động không mong
		 * muốn)
		 */
		http.csrf().disable();

		// Các url được khai báo ở đây không yêu cầu đăng nhập, ai cũng có thể vào được.
		http.authorizeRequests().antMatchers("/trangchu", "/dangnhap", "/dangxuat","/khachhang/**,/taikhoan").permitAll();

		// Các url bắt đầu bằng /admin yêu cầu đăng nhập tài khoản có quyền Admin
		http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN");

		// Các url bắt đầu bằng /user yêu cầu đăng nhập tài khoản có quyền Admin hoặc
		// User
		http.authorizeRequests().antMatchers("/khachhang/control/**").hasRole("USER");

		// Khi có truy cập vượt quyền sẽ chuyển về trang báo lỗi 403
		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/dang-nhap?error=403");

		// Cấu hình cho quá trình đăng nhập
		http.authorizeRequests().and().formLogin().loginProcessingUrl("/j_spring_security_check") // URL nhận submit ở
																									// trang Đăng nhập,
																									// URL này có sẵn
																									// trong Spring
																									// security.
				.loginPage("/dangnhap")
				.usernameParameter("account")
				.passwordParameter("password")
				.defaultSuccessUrl("/role") // URL được chuyển hướng tới khi đăng nhập thành công
				.failureUrl("/dangnhap?login-error=true")
				.and()
				.rememberMe()
				.tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21))
				.key("somethingverysecured")
				.rememberMeParameter("remember-me")
				.and()
				.logout()
				.logoutUrl("/dangxuat")
				.logoutSuccessUrl("/trangchu");
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
	}

	@Bean
	public AuthenticationManager authManager() throws Exception {
		return this.authenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
