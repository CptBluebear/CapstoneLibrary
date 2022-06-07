package org.corodiak.capstonelibrary.auth.util;

import org.corodiak.capstonelibrary.Exception.UnAuthorizeException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

public class AuthUtil {

	public static User getAuthenticationInfo() {
		return (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

	public static long getAuthenticationInfoSeq() throws UnAuthorizeException {
		try {
			return Long.parseLong(getAuthenticationInfo().getUsername());
		} catch (Exception e) {
			throw new UnAuthorizeException();
		}
	}

}
