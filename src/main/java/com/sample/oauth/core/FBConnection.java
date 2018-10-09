package com.sample.oauth.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class FBConnection {
	public static final String FB_APP_ID = "1710550392401115";
	public static final String FB_APP_SECRET = "ce8bc42988bd2fd31b57e74315811cb4";
	public static final String REDIRECT_URI = "https://localhost:8443/FacebookApp/login";
	public static final String ACCESS_TOKEN_ENDPOINT = "https://graph.facebook.com/oauth/access_token";
	public static final String SCOPE = "email user_birthday user_gender user_hometown user_location";

	public String getFBAuthUrl() {
		String fbAuthUrl = "";
		try {
			fbAuthUrl = "http://www.facebook.com/dialog/oauth?" + "client_id=" + FB_APP_ID + "&redirect_uri="
					+ URLEncoder.encode(REDIRECT_URI, StandardCharsets.UTF_8.name()) + "&response_type=code" + "&scope="
					+ URLEncoder.encode(SCOPE, StandardCharsets.UTF_8.name());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return fbAuthUrl;
	}

	public String getAccessToken(String authCode) throws ClientProtocolException, IOException {
		String accessToken = "";
		HttpPost httpPost = new HttpPost(ACCESS_TOKEN_ENDPOINT + "?grant_type="
				+ URLEncoder.encode("authorization_code", StandardCharsets.UTF_8.name()) + "&code="
				+ URLEncoder.encode(authCode, StandardCharsets.UTF_8.name()) + "&redirect_uri="
				+ URLEncoder.encode(REDIRECT_URI, StandardCharsets.UTF_8.name()) + "&client_id="
				+ URLEncoder.encode(FB_APP_ID, StandardCharsets.UTF_8.name()));

		String appCredentials = FB_APP_ID + ":" + FB_APP_SECRET;
		String encodedAppCredentials = new String(Base64.encodeBase64(appCredentials.getBytes()));
		httpPost.setHeader("Authorization", "Basic " + encodedAppCredentials);

		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpResponse httpResponse = httpClient.execute(httpPost);
		Reader reader = new InputStreamReader(httpResponse.getEntity().getContent());
		BufferedReader bufferedReader = new BufferedReader(reader);
		String line = bufferedReader.readLine();

		try {
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(line);
			JSONObject jsonobj = (JSONObject) obj;
			accessToken = jsonobj.get("access_token").toString();

		} catch (ParseException e) {
			e.getMessage();
		}

		return accessToken;
	}
}
