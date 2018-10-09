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
		String profile = null;
		try {
			String g = "https://graph.facebook.com/me?fields=first_name,last_name,birthday,hometown,location,email,gender";
			URL u = new URL(g);
			URLConnection c = u.openConnection();
			c.setRequestProperty("Authorization", "Bearer " + accessToken);
			BufferedReader in = new BufferedReader(new InputStreamReader(c.getInputStream()));
			String inputLine;
			StringBuffer b = new StringBuffer();
			while ((inputLine = in.readLine()) != null)
				b.append(inputLine + "\n");
			in.close();
			profile = b.toString();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("ERROR in getting FB graph data. " + e);
		}
		return profile;
	}

	public HashMap<String, String> getResourceData(String profile) {
		HashMap<String, String> fbProfile = new HashMap<>();
		try {
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(profile);
			JSONObject json = (JSONObject) obj;
			fbProfile.put("id", json.get("id").toString());
			if (json.containsKey("first_name"))
				fbProfile.put("first_name", json.get("first_name").toString());
			if (json.containsKey("last_name"))
				fbProfile.put("last_name", json.get("last_name").toString());
			if (json.containsKey("birthday"))
				fbProfile.put("birthday", json.get("birthday").toString());
			if (json.containsKey("hometown")) {
				Object hometown_obj = parser.parse(json.get("hometown").toString());
				JSONObject hometown_json = (JSONObject) hometown_obj;
				fbProfile.put("hometown", hometown_json.get("name").toString());
			}
			if (json.containsKey("location")) {
				Object location_obj = parser.parse(json.get("location").toString());
				JSONObject location_json = (JSONObject) location_obj;
				fbProfile.put("location", location_json.get("name").toString());
			}
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
