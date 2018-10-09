package com.sample.oauth.servlet;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sample.oauth.core.FBConnection;
import com.sample.oauth.core.FBResource;

public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String authCode="";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		FBConnection fbConnection = new FBConnection();
		authCode = req.getParameter("code");
		if (authCode == null || authCode.equals("")) {
			throw new RuntimeException(
					"ERROR: Didn't get code parameter in callback.");
		}
		
		String accessToken = fbConnection.getAccessToken(authCode);
		
		FBResource fbResource = new FBResource(accessToken);
		String graph = fbResource.getFBResource();
		HashMap<String, String> fbProfileData = fbResource.getResourceData(graph);
		
		System.out.println("Name: " + fbProfileData.get("name"));
	}

}
