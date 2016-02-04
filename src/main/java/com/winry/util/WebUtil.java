package com.winry.util;

import javax.servlet.http.HttpServletRequest;

public class WebUtil {

	public static String getBaseUrl(HttpServletRequest request) {
		return String.format("%s://%s:%d/", request.getScheme(), request.getServerName(),
				request.getServerPort());
	}

}
