package com.sample.oauth.servlet;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
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
	private String authCode = "";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		authCode = req.getParameter("code");
		if (authCode == null || authCode.equals("")) {
			throw new RuntimeException("ERROR: Didn't get code parameter in callback.");
		}

		FBConnection fbConnection = new FBConnection();
		String accessToken = fbConnection.getAccessToken(authCode);

		FBResource fbResource = new FBResource(accessToken);
		String data = fbResource.getFBResource();
		HashMap<String, String> fbProfileData = fbResource.getResourceData(data);

		req.setAttribute("id", fbProfileData.get("id"));
		req.setAttribute("first_name", fbProfileData.get("first_name"));
		req.setAttribute("last_name", fbProfileData.get("last_name"));
		req.setAttribute("birthday", fbProfileData.get("birthday"));
		req.setAttribute("hometown", fbProfileData.get("hometown"));
		req.setAttribute("location", fbProfileData.get("location"));
		req.setAttribute("email", fbProfileData.get("email"));
		req.setAttribute("gender", fbProfileData.get("gender"));
		req.setAttribute("profile-picture", fbProfileData.get("profile-picture"));
		RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
		dispatcher.forward(req, resp);
	}

}
