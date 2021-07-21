package com.pagerDuty.AddressBook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class AddressBook {
	List<String> fetch(String baseUrl) throws IOException, JSONException, InterruptedException {
		boolean more = true;
		Integer offset = 0;
		List<String> result = new ArrayList<>();
		
		do {
			URL url = new URL(baseUrl + offset);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Authorization", "Token token=y_NbAkKc66ryYTWUXYEu");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			JSONObject json = new JSONObject(br.readLine());
			JSONArray users = (JSONArray) json.get("users");

			for (int i = 0; i < users.length(); ++i) {
				result.add(users.getJSONObject(i).getString("name") + "\t" + 
			               users.getJSONObject(i).getString("email"));
			}
			
			offset += users.length();
			more = json.getBoolean("more");
			conn.disconnect();
		} while (more);
		
		return result;
	}
}

