package org.corodiak.capstonelibrary.auth.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

public class AuthUtil {

	public static User getAuthenticationInfo() {
		return (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

	public static long getAuthenticationInfoSeq() {
		return Long.parseLong(getAuthenticationInfo().getUsername());
	}

}
