package com.movement.web.authentication;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component
public class MyUserDetailsService implements UserDetailsService{
//	@Autowired
//	private OwnerService ownerService;
//	@Autowired
//	private NotifyService notifyService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Owner user = ownerService.findByName(username);
//        if (user == null) {
//            throw new UsernameNotFoundException("用户不存在");
//        }
//        int count = 0;
//        List<Notify> notifies = notifyService.findNotifiesByUserId(user.getId());
//        if (notifies!=null && notifies.size()>0) {
//			count = notifies.size();
//		}
//        return new OwnerUserDetails(user,count);
    	UserDetails details = new UserDetails() {
			
			@Override
			public boolean isEnabled() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean isCredentialsNonExpired() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean isAccountNonLocked() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean isAccountNonExpired() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public String getUsername() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getPassword() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Collection<? extends GrantedAuthority> getAuthorities() {
				// TODO Auto-generated method stub
				return null;
			}
		};
    	return details;
    }
    
}
