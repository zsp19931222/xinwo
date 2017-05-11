package com.quwu.xinwo.until;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class HttpGetUnit {
	private static String result ;
	public static String HttpClientget(String URL){
		 HttpGet httpGet = new HttpGet(URL);
         HttpClient httpClient = new DefaultHttpClient();
         try {
			HttpResponse response = httpClient.execute(httpGet);
		if (response.getStatusLine().getStatusCode()==200) {
		 result = EntityUtils.toString(response  
                    .getEntity()); 
		 System.out.println("result="+result);
		}
         
         } catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}


}
