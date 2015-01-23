package com.movement.auth;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.UriInfo;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;

@Component
public class SecurityFilter implements ContainerRequestFilter {

	@Context
	UriInfo uriInfo;

//	@Autowired
//	private UserService userService;
//	
//	@Autowired 
//	private UserSNSService snsService;
//	
//	@Autowired
//	private OwnerService ownerService;
//	
//	@Autowired
//	private WaiterService waiterService;
	
	@Override
	public ContainerRequest filter(ContainerRequest request) {

		//TODO 以后这里只需判断http head
		String authHead = request.getHeaderValue("Authorization");
		Cookie authCookie = request.getCookies().get("Authorization");

		String auth = "";
		 if (StringUtils.isNotBlank(authHead)) {
			// 手机端登录
			auth = authHead;
		}else if (authCookie != null) {
			// 网页登录
			auth = authCookie.getValue();
		}

		// 手机端登录，暂时用于服务员
//		if (StringUtils.isNotBlank(auth)) {
//			String[] tmp = StringUtils.split(auth, '|');
//			if (tmp.length == 3) {
//				int uid = Integer.parseInt(tmp[0]);
//				int utype = Integer.parseInt(tmp[2]);
//				String token = tmp[1];
//
//				if (utype == CodeUserType.USER) {
//					User user = userService.findById(uid);
//					if (user != null) {
//						if(user.getFromsns() > 0){
//							UserSNS sns = snsService.findByUidSnsType(user.getId(), user.getFromsns());
//							String encry = PublicHelper.encryptPassword(sns.getUser().getId(), sns.getOpenid() + sns.getUser().getFromsns());
//							if (token.equals(encry)) {
//								request.setSecurityContext(new Authorizer(user, uriInfo));
//							}
//						}else {
//							String encry = PublicHelper.encryptPassword(user.getId(),
//									user.getPassword());
//							if (token.equals(encry)) {
//								request.setSecurityContext(new Authorizer(user, uriInfo));
//							}
//						}
//					}
//					
//				} else if (utype == CodeUserType.WAITER) {
//					Waiter waiter = waiterService.findById(uid);
//
//					if (waiter != null &&token.equals(PublicHelper.encryptPassword(
//							waiter.getId(), waiter.getPassword()))) {
//						request.setSecurityContext(new Authorizer(waiter,
//								uriInfo));
//					}
//				} else if (utype == CodeUserType.OWNER) {
//					Owner owner = ownerService.findById(uid);
//
//					if (owner != null &&token.equals(PublicHelper.encryptPassword(
//							owner.getId(), owner.getPassword()))) {
//						request.setSecurityContext(new Authorizer(owner, uriInfo));
//					}
//				}
//			}
//		}

		return request;
	}

}
