package fa.training.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fa.training.entities.TaiKhoan;
import fa.training.repository.TaiKhoanRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	TaiKhoanRepository taiKhoanRepository;
	
	@Override
	public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
		
		// Lấy dữ liệu từ database lên thông qua số điện thoại người dùng nhập từ client
		final TaiKhoan taiKhoan = taiKhoanRepository.findByAccount(account);
		
		// Kiểm tra có tồn tại không và ném ra exception
		if (taiKhoan == null) {
			throw new UsernameNotFoundException(account);
		}
		
		// Tạo list của GrantedAuthority và đưa role của người dùng vào đối tượng SimpleGrantedAuthority sau đó add đối tượng GrantedAuthority vào list
		List<GrantedAuthority> grantedList = new ArrayList<>();
		GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(taiKhoan.getRole());
		grantedList.add(grantedAuthority);
		
		// Tạo đối tượng UserDetails thông qua constructor của User (Đây là class triển khai của UserDetails interface)
		// Đối tượng UserDetails sẽ chứa các thông tin của người dùng được lấy từ database để khớp với thông tin người dùng cung cấp
		UserDetails userDetails = (UserDetails) new User(taiKhoan.getAccount(), taiKhoan.getPassword(), grantedList);
		return userDetails;
	}

}
