package com.winry.util;

import javax.servlet.http.HttpServletRequest;

public class WebUtil {

	public static String getBaseUrl(HttpServletRequest request) {
		return String.format("%s://%s:%s/", request.getScheme(), request.getServerName(), request.getServerPort());
	}

}
