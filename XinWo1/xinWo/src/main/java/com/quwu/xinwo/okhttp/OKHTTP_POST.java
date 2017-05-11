package com.quwu.xinwo.okhttp;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.util.Log;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

public class OKHTTP_POST {
	static OkHttpClient mOkHttpClient = new OkHttpClient();
	static String result = "";

	/**
	 * 一个参数
	 * */
	public static String doPost1(String url, String parameter_name,
			String parameter) {
		// mOkHttpClient.setConnectTimeout(30, TimeUnit.SECONDS);
		// mOkHttpClient.setReadTimeout(15, TimeUnit.SECONDS);
		// mOkHttpClient.setWriteTimeout(30, TimeUnit.SECONDS);
		RequestBody formBody = new FormEncodingBuilder().add(parameter_name,
				parameter).build();

		// 当写请求头的时候，使用 header(name, value)
		// 可以设置唯一的name、value。如果已经有值，旧的将被移除，然后添加新的。使用 addHeader(name, value)
		// 可以添加多值（添加，不移除已有的）。
		Request request = new Request.Builder().url(url)
				.header("User-Agent", "OkHttp Headers.java")
				.addHeader("Accept", "application/json; q=0.5")
				.addHeader("Accept", "application/vnd.github.v3+json")
				.post(formBody).build();
		Response response;
		try {
			response = mOkHttpClient.newCall(request).execute();
			if (response.isSuccessful()) {
				System.out.println(response.code());
				// System.out.println(response.body().string());
				String body = response.body().string();
				System.out.println(body);
				result = "doPost|" + response.code() + "|" + body;
				// mHandler.sendEmptyMessage(MSG_WHAT);
				return body;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}// execute

		return null;
	}

	/**
	 * 两个参数
	 * */
	public static String doPost2(String url, String parameter_name,
			String parameter, String parameter_name1, String parameter1) {

		RequestBody formBody = new FormEncodingBuilder()
				.add(parameter_name, parameter)
				.add(parameter_name1, parameter1)
				.build();
		// 当写请求头的时候，使用 header(name, value)
		// 可以设置唯一的name、value。如果已经有值，旧的将被移除，然后添加新的。使用 addHeader(name, value)
		// 可以添加多值（添加，不移除已有的）。
		Request request = new Request.Builder().url(url)
				.header("User-Agent", "OkHttp Headers.java")
				.addHeader("Accept", "application/json; q=0.5")
				.addHeader("Accept", "application/vnd.github.v3+json")
				.post(formBody).build();
		Response response;
		try {
			response = mOkHttpClient.newCall(request).execute();
			if (response.isSuccessful()) {
				System.out.println(response.code());
				// System.out.println(response.body().string());
				String body = response.body().string();
				System.out.println(body);
				result = "doPost|" + response.code() + "|" + body;
				// mHandler.sendEmptyMessage(MSG_WHAT);
				return body;
			}
		} catch (IOException e) {
			Log.e("", e + "");
			e.printStackTrace();
		}// execute

		return null;
	}

	/**
	 * 3个参数
	 * */
	public static String doPost3(String url, String parameter_name,
			String parameter, String parameter_name1, String parameter1,
			String parameter_name2, String parameter2) {

		RequestBody formBody = new FormEncodingBuilder()
				.add(parameter_name, parameter)
				.add(parameter_name1, parameter1)
				.add(parameter_name2, parameter2).build();

		// 当写请求头的时候，使用 header(name, value)
		// 可以设置唯一的name、value。如果已经有值，旧的将被移除，然后添加新的。使用 addHeader(name, value)
		// 可以添加多值（添加，不移除已有的）。
		Request request = new Request.Builder().url(url)
				.header("User-Agent", "OkHttp Headers.java")
				.addHeader("Accept", "application/json; q=0.5")
				.addHeader("Accept", "application/vnd.github.v3+json")
				.post(formBody).build();
		Response response;
		try {
			response = mOkHttpClient.newCall(request).execute();
			if (response.isSuccessful()) {
				System.out.println(response.code());
				// System.out.println(response.body().string());
				String body = response.body().string();
				System.out.println(body);
				result = "doPost|" + response.code() + "|" + body;
				// mHandler.sendEmptyMessage(MSG_WHAT);

				return body;
			}
		} catch (IOException e) {

			e.printStackTrace();
		}// execute

		return null;
	}

	/**
	 * 4个参数
	 * */
	public static String doPost4(String url, String parameter_name,
			String parameter, String parameter_name1, String parameter1,
			String parameter_name2, String parameter2, String parameter_name3,
			String parameter3) {

		RequestBody formBody = new FormEncodingBuilder()
				.add(parameter_name, parameter)
				.add(parameter_name1, parameter1)
				.add(parameter_name2, parameter2)
				.add(parameter_name3, parameter3).build();

		// 当写请求头的时候，使用 header(name, value)
		// 可以设置唯一的name、value。如果已经有值，旧的将被移除，然后添加新的。使用 addHeader(name, value)
		// 可以添加多值（添加，不移除已有的）。
		Request request = new Request.Builder().url(url)
				.header("User-Agent", "OkHttp Headers.java")
				.addHeader("Accept", "application/json; q=0.5")
				.addHeader("Accept", "application/vnd.github.v3+json")
				.post(formBody).build();
		Response response;
		try {
			response = mOkHttpClient.newCall(request).execute();
			if (response.isSuccessful()) {
				System.out.println(response.code());
				// System.out.println(response.body().string());
				String body = response.body().string();
				System.out.println(body);
				result = "doPost|" + response.code() + "|" + body;
				// mHandler.sendEmptyMessage(MSG_WHAT);
				return body;
			}
		} catch (IOException e) {

			e.printStackTrace();
		}// execute

		return null;
	}

	/**
	 * 5个参数
	 * */
	public static String doPost5(String url, String parameter_name,
			String parameter, String parameter_name1, String parameter1,
			String parameter_name2, String parameter2, String parameter_name3,
			String parameter3, String parameter_name4, String parameter4) {

		RequestBody formBody = new FormEncodingBuilder()
				.add(parameter_name, parameter)
				.add(parameter_name1, parameter1)
				.add(parameter_name2, parameter2)
				.add(parameter_name3, parameter3)
				.add(parameter_name4, parameter4)
				.build();

		// 当写请求头的时候，使用 header(name, value)
		// 可以设置唯一的name、value。如果已经有值，旧的将被移除，然后添加新的。使用 addHeader(name, value)
		// 可以添加多值（添加，不移除已有的）。
		Request request = new Request.Builder().url(url)
				.header("User-Agent", "OkHttp Headers.java")
				.addHeader("Accept", "application/json; q=0.5")
				.addHeader("Accept", "application/vnd.github.v3+json")
				.post(formBody).build();
		Response response;
		try {
			response = mOkHttpClient.newCall(request).execute();
			if (response.isSuccessful()) {
				System.out.println(response.code());
				// System.out.println(response.body().string());
				String body = response.body().string();
				System.out.println(body);
				result = "doPost|" + response.code() + "|" + body;
				// mHandler.sendEmptyMessage(MSG_WHAT);

				return body;
			}
		} catch (IOException e) {

			e.printStackTrace();
		}// execute

		return null;
	}
	/**
	 * 6个参数
	 * */
	public static String doPost6(String url, String parameter_name,
			String parameter, String parameter_name1, String parameter1,
			String parameter_name2, String parameter2, String parameter_name3,
			String parameter3, String parameter_name4, String parameter4, String parameter_name5, String parameter5) {
		
		RequestBody formBody = new FormEncodingBuilder()
		.add(parameter_name, parameter)
		.add(parameter_name1, parameter1)
		.add(parameter_name2, parameter2)
		.add(parameter_name3, parameter3)
		.add(parameter_name4, parameter4)
		.add(parameter_name5, parameter5)
		.build();
		
		// 当写请求头的时候，使用 header(name, value)
		// 可以设置唯一的name、value。如果已经有值，旧的将被移除，然后添加新的。使用 addHeader(name, value)
		// 可以添加多值（添加，不移除已有的）。
		Request request = new Request.Builder().url(url)
				.header("User-Agent", "OkHttp Headers.java")
				.addHeader("Accept", "application/json; q=0.5")
				.addHeader("Accept", "application/vnd.github.v3+json")
				.post(formBody).build();
		Response response;
		try {
			response = mOkHttpClient.newCall(request).execute();
			if (response.isSuccessful()) {
				System.out.println(response.code());
				// System.out.println(response.body().string());
				String body = response.body().string();
				System.out.println(body);
				result = "doPost|" + response.code() + "|" + body;
				// mHandler.sendEmptyMessage(MSG_WHAT);
				
				return body;
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}// execute
		
		return null;
	}
	/**
	 * 7个参数
	 * */
	public static String doPost7(String url, String parameter_name,
			String parameter, String parameter_name1, String parameter1,
			String parameter_name2, String parameter2, String parameter_name3,
			String parameter3, String parameter_name4, String parameter4, String parameter_name5, String parameter5, String parameter_name6, String parameter6) {
		RequestBody formBody = new FormEncodingBuilder()
		.add(parameter_name, parameter)
		.add(parameter_name1, parameter1)
		.add(parameter_name2, parameter2)
		.add(parameter_name3, parameter3)
		.add(parameter_name4, parameter4)
		.add(parameter_name5, parameter5)
		.add(parameter_name6, parameter6)
		.build();
		
		// 当写请求头的时候，使用 header(name, value)
		// 可以设置唯一的name、value。如果已经有值，旧的将被移除，然后添加新的。使用 addHeader(name, value)
		// 可以添加多值（添加，不移除已有的）。
		Request request = new Request.Builder().url(url)
				.header("User-Agent", "OkHttp Headers.java")
				.addHeader("Accept", "application/json; q=0.5")
				.addHeader("Accept", "application/vnd.github.v3+json")
				.post(formBody).build();
		Response response;
		try {
			response = mOkHttpClient.newCall(request).execute();
			if (response.isSuccessful()) {
				System.out.println(response.code());
				// System.out.println(response.body().string());
				String body = response.body().string();
				System.out.println(body);
				result = "doPost|" + response.code() + "|" + body;
				// mHandler.sendEmptyMessage(MSG_WHAT);
				
				return body;
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}// execute
		
		return null;
	}
	/**
	 * 8个参数
	 * */
	public static String doPost8(String url, String parameter_name,
			String parameter, String parameter_name1, String parameter1,
			String parameter_name2, String parameter2, String parameter_name3,
			String parameter3, String parameter_name4, String parameter4, String parameter_name5, String parameter5, 
			String parameter_name6, String parameter6,
			String parameter_name7, String parameter7
			) {
		
		RequestBody formBody = new FormEncodingBuilder()
		.add(parameter_name, parameter)
		.add(parameter_name1, parameter1)
		.add(parameter_name2, parameter2)
		.add(parameter_name3, parameter3)
		.add(parameter_name4, parameter4)
		.add(parameter_name5, parameter5)
		.add(parameter_name6, parameter6)
		.add(parameter_name7, parameter7)
		.build();
		
		// 当写请求头的时候，使用 header(name, value)
		// 可以设置唯一的name、value。如果已经有值，旧的将被移除，然后添加新的。使用 addHeader(name, value)
		// 可以添加多值（添加，不移除已有的）。
		Request request = new Request.Builder().url(url)
				.header("User-Agent", "OkHttp Headers.java")
				.addHeader("Accept", "application/json; q=0.5")
				.addHeader("Accept", "application/vnd.github.v3+json")
				.post(formBody).build();
		Response response;
		try {
			response = mOkHttpClient.newCall(request).execute();
			if (response.isSuccessful()) {
				System.out.println(response.code());
				// System.out.println(response.body().string());
				String body = response.body().string();
				System.out.println(body);
				result = "doPost|" + response.code() + "|" + body;
				// mHandler.sendEmptyMessage(MSG_WHAT);
				
				return body;
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}// execute
		
		return null;
	}
	/**
	 * 9个参数
	 * */
	public static String doPost9(String url, String parameter_name,
			String parameter, String parameter_name1, String parameter1,
			String parameter_name2, String parameter2, String parameter_name3,
			String parameter3, String parameter_name4, String parameter4, String parameter_name5, String parameter5, 
			String parameter_name6, String parameter6,
			String parameter_name7, String parameter7,
			String parameter_name8, String parameter8
			) {
		
		RequestBody formBody = new FormEncodingBuilder()
		.add(parameter_name, parameter)
		.add(parameter_name1, parameter1)
		.add(parameter_name2, parameter2)
		.add(parameter_name3, parameter3)
		.add(parameter_name4, parameter4)
		.add(parameter_name5, parameter5)
		.add(parameter_name6, parameter6)
		.add(parameter_name7, parameter7)
		.add(parameter_name8, parameter8)
		.build();
		
		// 当写请求头的时候，使用 header(name, value)
		// 可以设置唯一的name、value。如果已经有值，旧的将被移除，然后添加新的。使用 addHeader(name, value)
		// 可以添加多值（添加，不移除已有的）。
		Request request = new Request.Builder().url(url)
				.header("User-Agent", "OkHttp Headers.java")
				.addHeader("Accept", "application/json; q=0.5")
				.addHeader("Accept", "application/vnd.github.v3+json")
				.post(formBody).build();
		Response response;
		try {
			response = mOkHttpClient.newCall(request).execute();
			if (response.isSuccessful()) {
				System.out.println(response.code());
				// System.out.println(response.body().string());
				String body = response.body().string();
				System.out.println(body);
				result = "doPost|" + response.code() + "|" + body;
				// mHandler.sendEmptyMessage(MSG_WHAT);
				
				return body;
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}// execute
		
		return null;
	}


	/**
	 * 上传文件
	 * */
	public static String uploading(String uri, String key1,String user_id,String key, String path)
			throws ClientProtocolException, IOException {
		String result = null;
		HttpPost httpPost = new HttpPost(uri);
		// httpPost.addHeader("Content-Type", "video/mpeg");
		// httpPost.addHeader("User-Agent", "imgfornote");
		// 设置传递参数
		MultipartEntity reqEntity = new MultipartEntity();
		File file1 = new File(path);
		if (!file1.getAbsoluteFile().equals("")) {
			FileBody fileBody = new FileBody(file1);
			reqEntity.addPart(key, fileBody);
		}
		StringBody type = new StringBody(user_id);
		reqEntity.addPart(key1, type);
		httpPost.setEntity(reqEntity);
		// 取得默认的HttpClient
		HttpClient httpclient = new DefaultHttpClient();
		// 取得HttpResponse
		HttpResponse httpResponse = httpclient.execute(httpPost);
		// HttpStatus.SC_OK表示连接成功
		if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			// 取得返回的字符串
			result = EntityUtils.toString(httpResponse.getEntity());
		} else {
			System.out.println("no!");
		}
		return result;
	}

	/**
	 * 传递集合
	 * */
	public static String sendMap(String path, Map<Object, Object> map)
			throws Exception {
		// StringBuilder是用来组拼请求参数
		StringBuilder sb = new StringBuilder();
		if (map != null) {
			for (Entry<Object, Object> entry : map.entrySet()) {
				sb.append(entry.getKey())
						.append("=")
						.append(URLEncoder.encode((String) entry.getValue(),
								"utf-8"));
				sb.append("&");
			}
			sb.deleteCharAt(sb.length() - 1);
		}
		// entity为请求体部分内容
		// 如果有中文则以UTF-8编码为username=%E4%B8%AD%E5%9B%BD&password=123
		byte[] entity = sb.toString().getBytes();
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		// 取得HttpResponse
		conn.setConnectTimeout(2000);
		// 设置以POST方式
		conn.setRequestMethod("POST");
		// Post 请求不能使用缓存
		// urlConn.setUseCaches(false);
		// 要向外输出数据，要设置这个
		conn.setDoOutput(true);
		// 配置本次连接的Content-type，配置为application/x-www-form-urlencoded
		// 设置content－type获得输出流，便于想服务器发送信息。
		// POST请求这个一定要设置
		conn.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");
		conn.setRequestProperty("Content-Length", entity.length + "");
		// 要注意的是connection.getOutputStream会隐含的进行connect。
		OutputStream out = conn.getOutputStream();
		// 写入参数值
		out.write(entity);
		// 获得服务端的返回数据
		InputStreamReader read = new InputStreamReader(conn.getInputStream());
		BufferedReader reader = new BufferedReader(read);
		String line = "";
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}
		if (reader != null) {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (out != null) {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (conn.getResponseCode() == 200) {
			Log.e("", sb.toString());
			return sb.toString();
		}
		if (conn != null)
			conn.disconnect();
		return null;
	}
	/**
	 * 两个参数
	 * */
	public static String doPostMap(String url,Map<Object, Object> map) throws Exception{

//		RequestBody formBody = new FormEncodingBuilder()
//				.add(parameter_name, parameter)
//				.add(parameter_name1, parameter1)
//				.build();
		// StringBuilder是用来组拼请求参数
				StringBuilder sb = new StringBuilder();
				if (map != null) {
					for (Entry<Object, Object> entry : map.entrySet()) {
						sb.append(entry.getKey())
								.append("=")
								.append(URLEncoder.encode((String) entry.getValue(),
										"utf-8"));
						sb.append("&");
					}
					sb.deleteCharAt(sb.length() - 1);
				}
				// entity为请求体部分内容
				// 如果有中文则以UTF-8编码为username=%E4%B8%AD%E5%9B%BD&password=123
				byte[] entity = sb.toString().getBytes();
		RequestBody formBody1=RequestBody.create(MediaType.parse("application/octet-stream; charset=utf-8"), entity);
		// 当写请求头的时候，使用 header(name, value)
		// 可以设置唯一的name、value。如果已经有值，旧的将被移除，然后添加新的。使用 addHeader(name, value)
		// 可以添加多值（添加，不移除已有的）。
		Log.e("request", formBody1+"");
		Request request = new Request.Builder().url(url)
//				.header("User-Agent", "OkHttp Headers.java")
//				.addHeader("Accept", "application/json; q=0.5")
//				.addHeader("Accept", "application/vnd.github.v3+json")
				.post(formBody1).build();
		Log.e("request", request+"");
		Response response;
		try {
			response = mOkHttpClient.newCall(request).execute();
			Log.e("request", response+"");
			if (response.isSuccessful()) {
				System.out.println(response.code());
				// System.out.println(response.body().string());
				String body = response.body().string();
				System.out.println(body);
				result = "doPost|" + response.code() + "|" + body;
				Log.e("result", result);
				// mHandler.sendEmptyMessage(MSG_WHAT);
				return body;
			}
		} catch (IOException e) {
			Log.e("", e + "");
			e.printStackTrace();
		}// execute

		return null;
	}



}