package com.amazon.app;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class App {
    public static void main(String args[]){
        System.out.println("Hi");
        try {
            // Define the URL
            String mainURL = "https://real-time-amazon-data.p.rapidapi.com/search?";
            Map<String, String> paramsURL = new HashMap<String, String>(){{
                put("query", "Bote de basura");
                put("page", "1");
                put("country", "MX");
                put("sort_by", "RELEVANCE");
                put("product_condition", "ALL");
            }};
            for (Map.Entry<String, String> entry : paramsURL.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                mainURL = mainURL + "&" + key + "=" + value;  
            }
            URL url = new URL(mainURL);
            // Open a connection to the URL
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            // Set the request method to GET
            con.setRequestMethod("GET");
            con.setRequestProperty("x-rapidapi-key", "5f5dd04520msha85586c167e52bdp141f38jsn8356613183c6");
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            con.setRequestProperty("x-rapidapi-host", "real-time-amazon-data.p.rapidapi.com");
            // Read the response
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }        
            // Close connections
            in.close();
            con.disconnect();       
            // Print the response
            JSONObject json = new JSONObject(content.toString());
            JSONObject data = json.getJSONObject("data");
            JSONArray products = data.getJSONArray("products");
            for(int i=0;i<products.length();i++){
                JSONObject product = products.getJSONObject(i);
                String title = product.getString("product_title");
                String price = product.getString("product_price").toString();
                System.out.println(title + "\n" + " price:" + price );
                System.out.println("------------------------------------------------------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }       
}
        
