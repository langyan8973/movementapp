package com.movement.resource;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.movement.bussiness.User;
import com.movement.service.UserService;
import com.movement.sns.AccessToken;
import com.movement.sns.SNSException;
import com.movement.sns.SdkDouban;
import com.movement.sns.SdkQzone;
import com.movement.sns.SdkRenren;
import com.movement.sns.SdkTqq;
import com.movement.sns.SdkWeibo;
import com.movement.sns.UserInfo;
import com.movement.util.CodeSNSType;
import com.movement.util.CodeUserType;
import com.movement.util.PublicHelper;


@Component
@Path("/thirdlogin")
public class ThirdLoginResource {
	@Autowired
	private UserService snsService;
//https://api.weibo.com/oauth2/authorize?client_id=3695231363&response_type=code&redirect_uri=http://127.0.0.1:8083/rest/thirdlogin/weibo
	@GET
	@Path("/weibo")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response weiboRedirect(@Context HttpServletRequest request,
			@Context UriInfo uriInfo) {
		if (!StringUtils.isEmpty(request.getParameter("code"))) {
			String code = request.getParameter("code");

			try {
				// 1根据code获取token和openid
				SdkWeibo sdk = new SdkWeibo();
				AccessToken at = sdk.getAccessTokenByCode(code);

				// 2根据token获取用户基本信息
				UserInfo user = sdk.showUserById(at.getUid(),
						at.getAccessToken());

				return successResponse(at, user, CodeSNSType.WEIBO, uriInfo);

			} catch (SNSException e) {
				return errorResponse("weibo错误了：" + e.getMessage());
			}

		} else if (!StringUtils.isEmpty(request.getParameter("error"))) {
			return errorResponse("授权错误："
					+ request.getParameter("error_description"));
		}

		return errorResponse("地址错误");
	}

	@GET
	@Path("/qzone")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response qzoneRedirect(@Context HttpServletRequest request,
			@Context UriInfo uriInfo) {
		if (!StringUtils.isEmpty(request.getParameter("code"))) {
			String code = request.getParameter("code");
			try {
				SdkQzone sdk = new SdkQzone();
				AccessToken at = sdk.getAccessTokenByCode(code);
				UserInfo user = sdk.showUserById(at.getUid(),
						at.getAccessToken());
				return successResponse(at, user, CodeSNSType.QZONE, uriInfo);
			} catch (SNSException e) {
				return errorResponse("qzone错误了：" + e.getMessage());
			}

		} else if (!StringUtils.isEmpty(request.getParameter("usercancel"))) {
			return errorResponse("授权错误：用户取消授权");
		}

		return errorResponse("地址错误");
	}

	@GET
	@Path("/tqq")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response tqqRedirect(@Context HttpServletRequest request,
			@Context UriInfo uriInfo) {
		if (!StringUtils.isEmpty(request.getParameter("code"))) {
			String code = request.getParameter("code");
			try {
				SdkTqq sdk = new SdkTqq();
				AccessToken at = sdk.getAccessTokenByCode(code);

				UserInfo user = sdk.showUserById(at.getUid(),
						at.getAccessToken());
				return successResponse(at, user, CodeSNSType.TQQ, uriInfo);
			} catch (SNSException e) {
				return errorResponse("qzone错误了：" + e.getMessage());
			}

		} else if (!StringUtils.isEmpty(request.getParameter("usercancel"))) {
			return errorResponse("授权错误：用户取消授权");
		}

		return errorResponse("地址错误");
	}

	@GET
	@Path("/douban")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response doubanRedirect(@Context HttpServletRequest request,
			@Context UriInfo uriInfo) {
		if (!StringUtils.isEmpty(request.getParameter("code"))) {
			String code = request.getParameter("code");
			try {
				SdkDouban sdk = new SdkDouban();
				AccessToken at = sdk.getAccessTokenByCode(code);

				UserInfo user = sdk.showUserById(at.getUid(),
						at.getAccessToken());
				return successResponse(at, user, CodeSNSType.DOUBAN, uriInfo);
			} catch (SNSException e) {
				return errorResponse("douban错误了：" + e.getMessage());
			}

		} else if (!StringUtils.isEmpty(request.getParameter("error"))) {
			return errorResponse("授权错误：用户取消授权");
		}

		return errorResponse("地址错误");
	}

	@GET
	@Path("/renren")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response renrenRedirect(@Context HttpServletRequest request,
			@Context UriInfo uriInfo) {
		if (!StringUtils.isEmpty(request.getParameter("code"))) {
			String code = request.getParameter("code");
			try {
				SdkRenren sdk = new SdkRenren();
				AccessToken at = sdk.getAccessTokenByCode(code);

				UserInfo user = sdk.showUserById(at.getUid(),
						at.getAccessToken());
				return successResponse(at, user, CodeSNSType.RENREN, uriInfo);
			} catch (SNSException e) {
				return errorResponse("renren错误了：" + e.getMessage());
			}

		} else if (!StringUtils.isEmpty(request.getParameter("error"))) {
			return errorResponse("授权错误：用户取消授权"
					+ request.getParameter("error_description"));
		}
		return errorResponse("地址错误");
	}

	private Response successResponse(AccessToken at, UserInfo userinfo,
			Integer snstype, UriInfo uriInfo) {
		User user = snsService.getByOpenid(at.getUid(), snstype);
		if (user == null) {
			user = snsService.create(at.getUid(), userinfo.getName(),
					userinfo.getThumbnail(), at.getAccessToken(),
					at.getRefreshToken(), at.getExpireIn(), snstype);
		} else {
			snsService.update(userinfo.getName(), userinfo.getThumbnail(),
					at.getAccessToken(), at.getRefreshToken(),
					at.getExpireIn(), user);
		}

		String encry = PublicHelper
				.encryptUser(user.getId(), user.getOpenid()
						+ user.getSns_type(), CodeUserType.USER);

		// TODO 这里以后需要更正
		UriBuilder ub = uriInfo.getAbsolutePathBuilder().replacePath(
				"rest/thirdlogin/callback");
		URI listUri = ub.queryParam("Authorization", encry)
				.queryParam("openid", user.getOpenid())
				.queryParam("access_token", user.getAccess_token())
				.queryParam("expires_in", user.getExpires_in().getTime())
				.queryParam("name", user.getName())
				.queryParam("thumbnail", user.getThumbnail())
				.queryParam("snstype", user.getSns_type()).build();
		return Response.seeOther(listUri).build();
	}

	public Response errorResponse(String error) {
		return Response
				.status(Status.BAD_REQUEST)
				.entity(error)
				.header(HttpHeaders.CONTENT_TYPE,
						MediaType.TEXT_PLAIN + "; charset=UTF-8")
				.type(MediaType.TEXT_PLAIN).build();
	}

	@GET
	@Path("/callback")
	@Produces({ MediaType.TEXT_PLAIN })
	public Response getCallback(@Context HttpServletRequest request) {
		if (!StringUtils.isEmpty(request.getParameter("openid"))) {
			String uid = request.getParameter("openid");
			return Response.status(Status.OK).entity(uid)
					.type(MediaType.TEXT_PLAIN).build();
		}
		return Response.status(Status.BAD_REQUEST).type(MediaType.TEXT_PLAIN)
				.entity("参数错误").build();
	}
}
