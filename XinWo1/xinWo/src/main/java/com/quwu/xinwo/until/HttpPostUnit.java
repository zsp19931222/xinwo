package com.quwu.xinwo.until;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
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
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

import com.quwu.xinwo.global.MyApp;

import android.net.ParseException;

public class HttpPostUnit {

	public static final HttpClient httpclient1 = new DefaultHttpClient();

	// /***
	// *
	// * 传入一个参数
	// * */
	// public static String HttpClientPost(String uri,String chuanshucanshu,
	// String id,Activity activity) {
	// String result = null;
	// // 1.创建 HttpClient 的实例
	//
	// // 2. 创建某种连接方法的实例，在这里是HttpPost。在 HttpPost 的构造函数中传入待连接的地址
	// HttpPost httpPost = new HttpPost(uri);
	// HttpParams params=new BasicHttpParams();
	// HttpConnectionParams.setConnectionTimeout(params, 3000);//设置超时链接
	// HttpConnectionParams.setSoTimeout(params, 1000000); //设置请求超时
	// httpPost.setParams(params);
	// try {
	// String page=client.execute(httpPost,new BasicResponseHandler());
	// } catch (ClientProtocolException e1) {
	// e1.printStackTrace();
	// } catch (IOException e1) {
	// activity.finish();
	// e1.printStackTrace();
	// }
	// try {
	// // 封装传递参数的集合
	// List<NameValuePair> parameters = new ArrayList<NameValuePair>();
	// // 往这个集合中添加你要传递的参数
	// parameters.add(new BasicNameValuePair(chuanshucanshu, id));
	// // 创建传递参数封装 实体对象
	// UrlEncodedFormEntity entity = new UrlEncodedFormEntity(parameters,
	// "UTF-8");// 设置传递参数的编码
	// // 把实体对象存入到httpPost对象中
	// httpPost.setEntity(entity);
	// // 3. 调用第一步中创建好的实例的 execute 方法来执行第二步中创建好的 method 实例
	// HttpResponse response = client.execute(httpPost); // HttpUriRequest的后代对象
	// // //在浏览器中敲一下回车
	// // 4. 读 response
	// if (response.getStatusLine().getStatusCode() == 200) {// 判断状态码
	// InputStream is = response.getEntity().getContent();// 获取内容
	// result = StreamTools.streamToStr(is); // 通过工具类转换文本
	// activity.runOnUiThread(new Runnable() { // 通过runOnUiThread方法处理
	// @Override
	// public void run() {
	// // 设置控件的内容(此内容是从服务器端获取的)
	// }
	// });
	// return result;
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// } finally {
	// // 6. 释放连接。无论执行方法是否成功，都必须释放连接
	// client.getConnectionManager().shutdown();
	// }
	// return result;
	// }
	// /***
	// *
	// * 传入两个参数
	// * */
	// public static String HttpClientPost2(String uri,String chuanshucanshu1,
	// String id,String chuanshucanshu2,String id2,Activity activity) {
	// String result = null;
	// // 1.创建 HttpClient 的实例
	// // HttpClient client = new DefaultHttpClient();
	// // 2. 创建某种连接方法的实例，在这里是HttpPost。在 HttpPost 的构造函数中传入待连接的地址
	// HttpPost httpPost = new HttpPost(uri);
	// HttpParams params=new BasicHttpParams();
	// HttpConnectionParams.setConnectionTimeout(params, 3000);//设置超时链接
	// HttpConnectionParams.setSoTimeout(params, 1000000); //设置请求超时
	// httpPost.setParams(params);
	// try {
	// String page=client.execute(httpPost,new BasicResponseHandler());
	// System.out.println("page="+page);
	// } catch (ClientProtocolException e1) {
	// e1.printStackTrace();
	// } catch (IOException e1) {
	// activity.finish();
	// e1.printStackTrace();
	// }
	// try {
	// // 封装传递参数的集合
	// List<NameValuePair> parameters = new ArrayList<NameValuePair>();
	// // 往这个集合中添加你要传递的参数
	// parameters.add(new BasicNameValuePair(chuanshucanshu1, id));
	// parameters.add(new BasicNameValuePair(chuanshucanshu2, id2));
	// // 创建传递参数封装 实体对象
	// UrlEncodedFormEntity entity = new UrlEncodedFormEntity(parameters,
	// "UTF-8");// 设置传递参数的编码
	// // 把实体对象存入到httpPost对象中
	//
	// httpPost.setEntity(entity);
	// // 3. 调用第一步中创建好的实例的 execute 方法来执行第二步中创建好的 method 实例
	// HttpResponse response = client.execute(httpPost); // HttpUriRequest的后代对象
	// // //在浏览器中敲一下回车
	// // 4. 读 response
	// if (response.getStatusLine().getStatusCode() == 200) {// 判断状态码
	// InputStream is = response.getEntity().getContent();// 获取内容
	// result = StreamTools.streamToStr(is); // 通过工具类转换文本
	// activity.runOnUiThread(new Runnable() { // 通过runOnUiThread方法处理
	// @Override
	// public void run() {
	// // 设置控件的内容(此内容是从服务器端获取的)
	//
	// }
	// });
	// return result;
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// } finally {
	// // 6. 释放连接。无论执行方法是否成功，都必须释放连接
	// client.getConnectionManager().shutdown();
	// }
	// return result;
	// }
	// /***
	// *
	// * 传入三个参数
	// * */
	// public static String HttpClientPost3(String uri,String chuanshucanshu1,
	// String id,String chuanshucanshu2,String id2,String chuanshucanshu3,String
	// id3,Activity activity) {
	// String result = null;
	// // 1.创建 HttpClient 的实例
	// // HttpClient client = new DefaultHttpClient();
	// // 2. 创建某种连接方法的实例，在这里是HttpPost。在 HttpPost 的构造函数中传入待连接的地址
	// HttpPost httpPost = new HttpPost(uri);
	// try {
	// String page=client.execute(httpPost,new BasicResponseHandler());
	// } catch (ClientProtocolException e1) {
	// e1.printStackTrace();
	// } catch (IOException e1) {
	// activity.finish();
	// e1.printStackTrace();
	// }
	// try {
	// // 封装传递参数的集合
	// List<NameValuePair> parameters = new ArrayList<NameValuePair>();
	// // 往这个集合中添加你要传递的参数
	// parameters.add(new BasicNameValuePair(chuanshucanshu1, id));
	// parameters.add(new BasicNameValuePair(chuanshucanshu2, id2));
	// parameters.add(new BasicNameValuePair(chuanshucanshu3, id3));
	// // 创建传递参数封装 实体对象
	// UrlEncodedFormEntity entity = new UrlEncodedFormEntity(parameters,
	// "UTF-8");// 设置传递参数的编码
	// // 把实体对象存入到httpPost对象中
	// httpPost.setEntity(entity);
	// // 3. 调用第一步中创建好的实例的 execute 方法来执行第二步中创建好的 method 实例
	// HttpResponse response = client.execute(httpPost); // HttpUriRequest的后代对象
	// // //在浏览器中敲一下回车
	// // 4. 读 response
	// if (response.getStatusLine().getStatusCode() == 200) {// 判断状态码
	// InputStream is = response.getEntity().getContent();// 获取内容
	// result = StreamTools.streamToStr(is); // 通过工具类转换文本
	// activity.runOnUiThread(new Runnable() { // 通过runOnUiThread方法处理
	// @Override
	// public void run() {
	// // 设置控件的内容(此内容是从服务器端获取的)
	// }
	// });
	// return result;
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// } finally {
	// // 6. 释放连接。无论执行方法是否成功，都必须释放连接
	// client.getConnectionManager().shutdown();
	// }
	// return result;
	// }
	/***
	 * 第三方http协议(一个参数)
	 * 
	 * @throws IOException
	 * @throws ClientProtocolException
	 * */
	public static String otherHttpPostString(String uri, String canshu,
			String id) {
		String result = null;
		HttpPost httpPost = new HttpPost(uri);
		// 设置传递参数
		MultipartEntity reqEntity = new MultipartEntity();
		StringBody type = null;
		try {
			type = new StringBody(id);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		reqEntity.addPart(canshu, type);
		httpPost.setEntity(reqEntity);
		// 取得默认的HttpClient
		HttpClient httpclient = new DefaultHttpClient();
		// 请求超时
		// httpclient.getParams().setParameter(
		// CoreConnectionPNames.CONNECTION_TIMEOUT, 60000);
		// 读取超时
		// httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
		// 60000);
		// 取得HttpResponse
		HttpResponse httpResponse = null;
		try {
			httpResponse = httpclient.execute(httpPost);
		} catch (ClientProtocolException e) {
			// if (URLUtils.getUrl().equals(URLUtils.url1)) {
			// URLUtils.setUrl(URLUtils.url2);
			// } else {
			// URLUtils.setUrl(URLUtils.url1);
			// }
			e.printStackTrace();
		} catch (IOException e) {
			// if (URLUtils.getUrl().equals(URLUtils.url1)) {
			// URLUtils.setUrl(URLUtils.url2);
			// } else {
			// URLUtils.setUrl(URLUtils.url1);
			// }
			e.printStackTrace();
		}
		// HttpStatus.SC_OK表示连接成功
		if (httpResponse != null
				&& httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			// 取得返回的字符串
			try {
				result = EntityUtils.toString(httpResponse.getEntity());
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
		}
		return result;
	}

	public static String otherHttpPostStringone(String uri, String canshu,
			String id) {
		String result = null;
		HttpPost httpPost = new HttpPost(uri);
		// 设置传递参数
		MultipartEntity reqEntity = new MultipartEntity();
		StringBody type = null;
		try {
			type = new StringBody(id);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		reqEntity.addPart(canshu, type);
		httpPost.setEntity(reqEntity);
		// 取得默认的HttpClient
		HttpClient httpclient = new DefaultHttpClient();
		// 请求超时
		// httpclient.getParams().setParameter(
		// CoreConnectionPNames.CONNECTION_TIMEOUT, 1000);
		// 读取超时
		// httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
		// 1000);
		// 取得HttpResponse
		HttpResponse httpResponse = null;
		try {
			httpResponse = httpclient.execute(httpPost);
		} catch (ClientProtocolException e) {
			// if (URLUtils.getUrl().equals(URLUtils.url1)) {
			// URLUtils.setUrl(URLUtils.url2);
			// } else {
			// URLUtils.setUrl(URLUtils.url1);
			// }
			e.printStackTrace();
		} catch (IOException e) {
			// if (URLUtils.getUrl().equals(URLUtils.url1)) {
			// URLUtils.setUrl(URLUtils.url2);
			// } else {
			// URLUtils.setUrl(URLUtils.url1);
			// }
			e.printStackTrace();
		}
		// HttpStatus.SC_OK表示连接成功
		if (httpResponse != null
				&& httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			// 取得返回的字符串
			try {
				result = EntityUtils.toString(httpResponse.getEntity());
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
		}
		return result;
	}

	/***
	 * 第三方http协议(三个参数)
	 * 
	 * @throws IOException
	 * @throws ClientProtocolException
	 * */
	public static String otherHttpPostString3(String uri, String canshu,
			String id, String canshu1, String id1, String canshu2, String id2) {
		String result = null;
		if (uri != null && canshu != null && id != null && canshu1 != null
				&& id1 != null && canshu2 != null && id2 != null) {
			HttpPost httpPost = new HttpPost(uri);
			// 设置传递参数
			MultipartEntity reqEntity = new MultipartEntity();
			StringBody type = null;
			try {
				type = new StringBody(id);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			reqEntity.addPart(canshu, type);
			StringBody type1 = null;
			try {
				type1 = new StringBody(id1);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			reqEntity.addPart(canshu1, type1);
			StringBody type2 = null;
			try {
				type2 = new StringBody(id2);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			reqEntity.addPart(canshu2, type2);
			httpPost.setEntity(reqEntity);
			// 取得默认的HttpClient
			HttpClient httpclient = new DefaultHttpClient();
			// 请求超时
			// httpclient.getParams().setParameter(
			// CoreConnectionPNames.CONNECTION_TIMEOUT, 1000);
			// 读取超时
			// httpclient.getParams().setParameter(
			// CoreConnectionPNames.SO_TIMEOUT, 1000);
			// 取得HttpResponse
			if (httpPost != null) {
				HttpResponse httpResponse = null;
				try {
					httpResponse = httpclient.execute(httpPost);
				} catch (ClientProtocolException e) {
					// if (URLUtils.getUrl().equals(URLUtils.url1)) {
					// URLUtils.setUrl(URLUtils.url2);
					// } else {
					// URLUtils.setUrl(URLUtils.url1);
					// }
					e.printStackTrace();
				} catch (IOException e) {
					// if (URLUtils.getUrl().equals(URLUtils.url1)) {
					// URLUtils.setUrl(URLUtils.url2);
					// } else {
					// URLUtils.setUrl(URLUtils.url1);
					// }
					e.printStackTrace();
				}
				// HttpStatus.SC_OK表示连接成功
				if (httpResponse != null
						&& httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					// 取得返回的字符串
					try {
						result = EntityUtils.toString(httpResponse.getEntity());
					} catch (ParseException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
				}
			}
		}
		return result;
	}

	/***
	 * 第三方http协议(两个参数)
	 * 
	 * @throws IOException
	 * @throws ClientProtocolException
	 * */
	public static String Login2(String uri, String canshu, String id,
			String canshu2, String id2) {
		String result = null;
		if (uri != null && canshu != null && id != null && canshu2 != null
				&& id2 != null) {
			HttpPost httpPost = new HttpPost(uri);

			// 设置传递参数
			MultipartEntity reqEntity = new MultipartEntity();
			StringBody type = null;
			try {
				type = new StringBody(id);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			reqEntity.addPart(canshu, type);
			StringBody type2 = null;
			try {
				type2 = new StringBody(id2);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			reqEntity.addPart(canshu2, type2);
			httpPost.setEntity(reqEntity);
			// 取得默认的HttpClient
			// HttpClient httpclient = new DefaultHttpClient();
			// 请求超时
			httpclient1.getParams().setParameter(
					CoreConnectionPNames.CONNECTION_TIMEOUT, 1000);
			// 读取超时
			httpclient1.getParams().setParameter(
					CoreConnectionPNames.SO_TIMEOUT, 1000);

			// 取得HttpResponse
			if (httpPost != null) {
				HttpResponse httpResponse = null;
				try {
					httpResponse = httpclient1.execute(httpPost);
				} catch (ClientProtocolException e) {
					// if (URLUtils.getUrl().equals(URLUtils.url1)) {
					// URLUtils.setUrl(URLUtils.url2);
					// } else {
					// URLUtils.setUrl(URLUtils.url1);
					// }
					e.printStackTrace();
				} catch (IOException e) {
					// if (URLUtils.getUrl().equals(URLUtils.url1)) {
					// URLUtils.setUrl(URLUtils.url2);
					// } else {
					// URLUtils.setUrl(URLUtils.url1);
					// }
					e.printStackTrace();
				}
				// HttpStatus.SC_OK表示连接成功
				if (httpResponse != null
						&& httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					// 取得返回的字符串
					try {
						result = EntityUtils.toString(httpResponse.getEntity());
					} catch (ParseException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
				}
			}
		}
		return result;
	}

	public static String Login3(String uri, String canshu, String id,
			String canshu2, String id2, String canshu3, String id3) {
		String result = null;
		if (uri != null && canshu != null && id != null && canshu3 != null
				&& id3 != null && canshu2 != null && id2 != null) {
			HttpPost httpPost = new HttpPost(uri);

			// 设置传递参数
			MultipartEntity reqEntity = new MultipartEntity();
			StringBody type = null;
			try {
				type = new StringBody(id);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			reqEntity.addPart(canshu, type);
			StringBody type2 = null;
			try {
				type2 = new StringBody(id2);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			reqEntity.addPart(canshu2, type2);
			StringBody type3 = null;
			try {
				type3 = new StringBody(id3);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			reqEntity.addPart(canshu3, type3);
			httpPost.setEntity(reqEntity);
			// 取得默认的HttpClient
			// HttpClient httpclient = new DefaultHttpClient();
			// 请求超时
			httpclient1.getParams().setParameter(
					CoreConnectionPNames.CONNECTION_TIMEOUT, 1000);
			// 读取超时
			httpclient1.getParams().setParameter(
					CoreConnectionPNames.SO_TIMEOUT, 1000);

			// 取得HttpResponse
			if (httpPost != null) {
				HttpResponse httpResponse = null;
				try {
					httpResponse = httpclient1.execute(httpPost);
				} catch (ClientProtocolException e) {
					// if (URLUtils.getUrl().equals(URLUtils.url1)) {
					// URLUtils.setUrl(URLUtils.url2);
					// } else {
					// URLUtils.setUrl(URLUtils.url1);
					// }
					e.printStackTrace();
				} catch (IOException e) {
					// if (URLUtils.getUrl().equals(URLUtils.url1)) {
					// URLUtils.setUrl(URLUtils.url2);
					// } else {
					// URLUtils.setUrl(URLUtils.url1);
					// }
					e.printStackTrace();
				}
				// HttpStatus.SC_OK表示连接成功
				if (httpResponse != null
						&& httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					// 取得返回的字符串
					try {
						result = EntityUtils.toString(httpResponse.getEntity());
					} catch (ParseException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
				}
			}
		}
		return result;
	}

	public static String Login1(String uri, String canshu, String id) {
		String result = null;
		if (uri != null && canshu != null && id != null) {
			HttpPost httpPost = new HttpPost(uri);
			// 设置传递参数
			MultipartEntity reqEntity = new MultipartEntity();
			StringBody type = null;
			try {
				type = new StringBody(id);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			reqEntity.addPart(canshu, type);
			httpPost.setEntity(reqEntity);
			// 取得默认的HttpClient
			// HttpClient httpclient = new DefaultHttpClient();
			// 请求超时
			httpclient1.getParams().setParameter(
					CoreConnectionPNames.CONNECTION_TIMEOUT, 1000);
			// 读取超时
			httpclient1.getParams().setParameter(
					CoreConnectionPNames.SO_TIMEOUT, 1000);

			// 取得HttpResponse
			if (httpPost != null) {
				HttpResponse httpResponse = null;
				try {
					httpResponse = httpclient1.execute(httpPost);
				} catch (ClientProtocolException e) {
					// if (URLUtils.getUrl().equals(URLUtils.url1)) {
					// URLUtils.setUrl(URLUtils.url2);
					// } else {
					// URLUtils.setUrl(URLUtils.url1);
					// }
					e.printStackTrace();
				} catch (IOException e) {
					// if (URLUtils.getUrl().equals(URLUtils.url1)) {
					// URLUtils.setUrl(URLUtils.url2);
					// } else {
					// URLUtils.setUrl(URLUtils.url1);
					// }
					e.printStackTrace();
				}
				// HttpStatus.SC_OK表示连接成功
				if (httpResponse != null
						&& httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					// 取得返回的字符串
					try {
						result = EntityUtils.toString(httpResponse.getEntity());
					} catch (ParseException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
				}
			}
		}
		return result;
	}

	public static String otherHttpPostString2(String uri, String canshu,
			String id, String canshu2, String id2) {
		String result = null;
		if (uri != null && canshu != null && id != null && canshu2 != null
				&& id2 != null) {
			HttpPost httpPost = new HttpPost(uri);
			// 设置传递参数
			MultipartEntity reqEntity = new MultipartEntity();
			StringBody type = null;
			try {
				type = new StringBody(id);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			reqEntity.addPart(canshu, type);
			StringBody type2 = null;
			try {
				type2 = new StringBody(id2);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			reqEntity.addPart(canshu2, type2);
			httpPost.setEntity(reqEntity);
			// 取得默认的HttpClient
			HttpClient httpclient = new DefaultHttpClient();
			// 请求超时
			// httpclient.getParams().setParameter(
			// CoreConnectionPNames.CONNECTION_TIMEOUT, 1000);
			// 读取超时
			// httpclient.getParams().setParameter(
			// CoreConnectionPNames.SO_TIMEOUT, 1000);

			if (httpPost != null) {
				HttpResponse httpResponse = null;
				try {
					httpResponse = httpclient.execute(httpPost);
				} catch (ClientProtocolException e) {
					// if (URLUtils.getUrl().equals(URLUtils.url1)) {
					// URLUtils.setUrl(URLUtils.url2);
					// } else {
					// URLUtils.setUrl(URLUtils.url1);
					// }
					e.printStackTrace();
				} catch (IOException e) {
					// if (URLUtils.getUrl().equals(URLUtils.url1)) {
					// URLUtils.setUrl(URLUtils.url2);
					// } else {
					// URLUtils.setUrl(URLUtils.url1);
					// }
					e.printStackTrace();
				}
				// HttpStatus.SC_OK表示连接成功
				if (httpResponse != null
						&& httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					// 取得返回的字符串
					try {
						result = EntityUtils.toString(httpResponse.getEntity());
					} catch (ParseException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
				}
			}
		}
		return result;
	}

	/***
	 * 第三方http协议(四个参数)
	 * 
	 * @throws IOException
	 * @throws ClientProtocolException
	 * */
	public static String otherHttpPostString4(String uri, String canshu,
			String id, String canshu2, String id2, String canshu3, String id3,
			String canshu4, String id4) {
		String result = null;
		if (uri != null && canshu != null && id != null && canshu3 != null
				&& id3 != null && canshu2 != null && id2 != null
				&& canshu4 != null && id4 != null) {
			HttpPost httpPost = new HttpPost(uri);

			// 设置传递参数
			MultipartEntity reqEntity = new MultipartEntity();
			StringBody type = null;
			try {
				type = new StringBody(id);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			reqEntity.addPart(canshu, type);
			StringBody type2 = null;
			try {
				type2 = new StringBody(id2);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			reqEntity.addPart(canshu2, type2);
			StringBody type3 = null;
			try {
				type3 = new StringBody(id3);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			reqEntity.addPart(canshu3, type3);
			StringBody type4 = null;
			try {
				type4 = new StringBody(id4);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			reqEntity.addPart(canshu4, type4);
			httpPost.setEntity(reqEntity);
			// 取得默认的HttpClient
			HttpClient httpclient = new DefaultHttpClient();
			// 请求超时
			// httpclient.getParams().setParameter(
			// CoreConnectionPNames.CONNECTION_TIMEOUT, 1000);
			// 读取超时
			// httpclient.getParams().setParameter(
			// CoreConnectionPNames.SO_TIMEOUT, 1000);

			// 取得HttpResponse
			if (httpPost != null) {
				HttpResponse httpResponse = null;
				try {
					httpResponse = httpclient.execute(httpPost);
				} catch (ClientProtocolException e) {
					// if (URLUtils.getUrl().equals(URLUtils.url1)) {
					// URLUtils.setUrl(URLUtils.url2);
					// } else {
					// URLUtils.setUrl(URLUtils.url1);
					// }
					e.printStackTrace();
				} catch (IOException e) {
					// if (URLUtils.getUrl().equals(URLUtils.url1)) {
					// URLUtils.setUrl(URLUtils.url2);
					// } else {
					// URLUtils.setUrl(URLUtils.url1);
					// }
					e.printStackTrace();
				}
				// HttpStatus.SC_OK表示连接成功
				if (httpResponse != null
						&& httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					// 取得返回的字符串
					try {
						result = EntityUtils.toString(httpResponse.getEntity());
					} catch (ParseException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
				}
			}
		}
		return result;
	}

	/***
	 * 第三方http协议(五个参数)
	 * 
	 * @throws IOException
	 * @throws ClientProtocolException
	 * */
	public static String otherHttpPostString5(String uri, String canshu,
			String id, String canshu2, String id2, String canshu3, String id3,
			String canshu4, String id4, String canshu5, String id5) {
		String result = null;
		if (uri != null && canshu != null && id != null && canshu3 != null
				&& id3 != null && canshu2 != null && id2 != null
				&& canshu4 != null && id4 != null && canshu5 != null
				&& id5 != null) {
			HttpPost httpPost = new HttpPost(uri);
			// 设置传递参数
			MultipartEntity reqEntity = new MultipartEntity();
			StringBody type = null;
			try {
				type = new StringBody(id);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			reqEntity.addPart(canshu, type);
			StringBody type2 = null;
			try {
				type2 = new StringBody(id2);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			reqEntity.addPart(canshu2, type2);
			StringBody type3 = null;
			try {
				type3 = new StringBody(id3);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			reqEntity.addPart(canshu3, type3);
			StringBody type4 = null;
			try {
				type4 = new StringBody(id4);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			reqEntity.addPart(canshu4, type4);
			StringBody type5 = null;
			try {
				type5 = new StringBody(id5);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			reqEntity.addPart(canshu5, type5);
			httpPost.setEntity(reqEntity);
			// 取得默认的HttpClient
			HttpClient httpclient = new DefaultHttpClient();
			// 请求超时
			// httpclient.getParams().setParameter(
			// CoreConnectionPNames.CONNECTION_TIMEOUT, 60000);
			// 读取超时
			// httpclient.getParams().setParameter(
			// CoreConnectionPNames.SO_TIMEOUT, 60000);
			if (httpPost != null) {
				HttpResponse httpResponse = null;
				try {
					httpResponse = httpclient.execute(httpPost);
				} catch (ClientProtocolException e) {
					// if (URLUtils.getUrl().equals(URLUtils.url1)) {
					// URLUtils.setUrl(URLUtils.url2);
					// } else {
					// URLUtils.setUrl(URLUtils.url1);
					// }
					e.printStackTrace();
				} catch (IOException e) {
					// if (URLUtils.getUrl().equals(URLUtils.url1)) {
					// URLUtils.setUrl(URLUtils.url2);
					// } else {
					// URLUtils.setUrl(URLUtils.url1);
					// }
					e.printStackTrace();
				}
				// HttpStatus.SC_OK表示连接成功
				if (httpResponse != null
						&& httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					// 取得返回的字符串
					try {
						result = EntityUtils.toString(httpResponse.getEntity());
					} catch (ParseException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
				}
			}
		}
		return result;
	}

	/***
	 * 第三方http协议(六个参数)
	 * 
	 * @throws IOException
	 * @throws ClientProtocolException
	 * */
	public static String otherHttpPostString6(String uri, String canshu,
			String id, String canshu2, String id2, String canshu3, String id3,
			String canshu4, String id4, String canshu5, String id5,
			String canshu6, String id6) {
		String result = null;
		if (uri != null && canshu != null && id != null && canshu3 != null
				&& id3 != null && canshu2 != null && id2 != null
				&& canshu4 != null && id4 != null && canshu5 != null
				&& id5 != null && canshu6 != null && id6 != null) {
			HttpPost httpPost = new HttpPost(uri);

			// 设置传递参数
			MultipartEntity reqEntity = new MultipartEntity();
			StringBody type = null;
			try {
				type = new StringBody(id);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			reqEntity.addPart(canshu, type);
			StringBody type2 = null;
			try {
				type2 = new StringBody(id2);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			reqEntity.addPart(canshu2, type2);
			StringBody type3 = null;
			try {
				type3 = new StringBody(id3);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			reqEntity.addPart(canshu3, type3);
			StringBody type4 = null;
			try {
				type4 = new StringBody(id4);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			reqEntity.addPart(canshu4, type4);
			StringBody type5 = null;
			try {
				type5 = new StringBody(id5);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			reqEntity.addPart(canshu5, type5);
			StringBody type6 = null;
			try {
				type6 = new StringBody(id6);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			reqEntity.addPart(canshu6, type6);
			httpPost.setEntity(reqEntity);
			// 取得默认的HttpClient
			HttpClient httpclient = new DefaultHttpClient();
			// 请求超时
			// httpclient.getParams().setParameter(
			// CoreConnectionPNames.CONNECTION_TIMEOUT, 1000);
			// 读取超时
			// httpclient.getParams().setParameter(
			// CoreConnectionPNames.SO_TIMEOUT, 1000);
			if (httpPost != null) {
				HttpResponse httpResponse = null;
				try {
					httpResponse = httpclient.execute(httpPost);
				} catch (ClientProtocolException e) {
					// if (URLUtils.getUrl().equals(URLUtils.url1)) {
					// URLUtils.setUrl(URLUtils.url2);
					// } else {
					// URLUtils.setUrl(URLUtils.url1);
					// }
					e.printStackTrace();
				} catch (IOException e) {
					// if (URLUtils.getUrl().equals(URLUtils.url1)) {
					// URLUtils.setUrl(URLUtils.url2);
					// } else {
					// URLUtils.setUrl(URLUtils.url1);
					// }
					e.printStackTrace();
				}
				// HttpStatus.SC_OK表示连接成功
				if (httpResponse != null
						&& httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					// 取得返回的字符串
					try {
						result = EntityUtils.toString(httpResponse.getEntity());
					} catch (ParseException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
				}
			}
		}
		return result;
	}

	public static boolean sendPOSTRequest(String path, Map<Object, Object> map)
			throws Exception {
		boolean success = false;
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
		// 刷新、关闭
		out.flush();
		out.close();

		if (conn.getResponseCode() == 200) {
			success = true;
		}
		if (conn != null)
			conn.disconnect();
		return success;
	}

	/**
	 * 第三方登录(第一次注册)
	 * */
	public static String Third_Party(String imageurl, String openid,
			String nickname) throws ClientProtocolException, IOException {
		// 上传图片到服务器
		String strResult = null;
		HttpPost httpPost = new HttpPost(MyApp.base_address
				+ "loginandReg/thirdpartyreg.do");
		// 设置传递参数
		MultipartEntity reqEntity = new MultipartEntity();

		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
		File file1 = new File(imageurl);

		if (!file1.getAbsoluteFile().equals("")) {

			FileBody fileBody = new FileBody(file1);
			reqEntity.addPart("file", fileBody);
		}
		StringBody type = new StringBody("3");
		reqEntity.addPart("reg_mode", type);
		StringBody temp = new StringBody(openid);
		reqEntity.addPart("thirdpart_id", temp);
		StringBody temp1 = new StringBody(nickname);
		reqEntity.addPart("user_name", temp1);
		StringBody temp2 = new StringBody(Tool.getPhoneIp());
		reqEntity.addPart("reg_ip", temp2);
		StringBody temp3 = new StringBody(Tool.getInfo());
		reqEntity.addPart("reg_machine", temp3);
		httpPost.setEntity(reqEntity);
		// 取得默认的HttpClient
		HttpClient httpclient = new DefaultHttpClient();
		// 取得HttpResponse
		HttpResponse httpResponse = httpclient.execute(httpPost);
		// HttpStatus.SC_OK表示连接成功
		if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			// 取得返回的字符串
			strResult = EntityUtils.toString(httpResponse.getEntity());
			System.out.println("strResult" + strResult);
		} else {
			System.out.println("no!");
		}
		return strResult;
	}
}