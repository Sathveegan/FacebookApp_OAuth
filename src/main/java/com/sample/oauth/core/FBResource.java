package com.sample.oauth.core;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class FBResource {
	private String accessToken;

	public FBResource(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getFBResource() {
		String graph = null;
		try {

			String g = "https://graph.facebook.com/me?fields=email,name,gender";
			URL u = new URL(g);
			URLConnection c = u.openConnection();
			c.setRequestProperty ("Authorization", "Bearer " + accessToken);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					c.getInputStream()));
			String inputLine;
			StringBuffer b = new StringBuffer();
			while ((inputLine = in.readLine()) != null)
				b.append(inputLine + "\n");
			in.close();
			graph = b.toString();
			System.out.println(graph);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("ERROR in getting FB graph data. " + e);
		}
		return graph;
	}

	public HashMap<String, String> getResourceData(String fbGraph) {
		HashMap<String, String> fbProfile = new HashMap<>();
		try {
			JSONParser parser = new JSONParser();
            Object obj = parser.parse(fbGraph);
			JSONObject json = (JSONObject) obj;
			fbProfile.put("id", json.get("id").toString());
			if (json.containsKey("name"))
				fbProfile.put("name", json.get("name").toString());
			if (json.containsKey("email"))
				fbProfile.put("email", json.get("email").toString());
			if (json.containsKey("gender"))
				fbProfile.put("gender", json.get("gender").toString());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("ERROR in parsing FB graph data. " + e);
		}
		return fbProfile;
	}
}
