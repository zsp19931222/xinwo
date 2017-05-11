package com.quwu.xinwo.wxapi;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

import com.quwu.xinwo.global.MyApp;
import com.quwu.xinwo.home_page.Home_Page_Activity;
import com.quwu.xinwo.log_in.LoginActivity;
import com.quwu.xinwo.mywight.MyToast;
import com.quwu.xinwo.okhttp.OKHTTP_POST;
import com.quwu.xinwo.until.MySharePreferences;
import com.quwu.xinwo.until.Tool;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
	private IWXAPI api;
	private BaseResp resp = null;
	private String myJpgPath = Environment.getExternalStorageDirectory()
			+ "/pepper/" + "1.png";
	private String token;
	private String access_token, expires_in, refresh_token, openid, scope,
			unionid;
	private String nickname, sex, language, city, province, country,
			headimgurl, privilege;
	private String newName = "image.jpg";
	private String uploadFile = "/sdcard/image.JPG";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		api = WXAPIFactory.createWXAPI(this, "wxdb70678fd4306b57", false);
		api.handleIntent(getIntent(), this);
	}

	@Override
	protected void onNewIntent(Intent intent) {
		// TODO Auto-generated method stub
		super.onNewIntent(intent);
		setIntent(intent);
		api.handleIntent(intent, this);
		finish();
	}

	private String isHttpGet(String tokenStr, String openid)
			throws ClientProtocolException, IOException {
		String result = null;
		String Url = "https://api.weixin.qq.com/sns/userinfo?access_token="
				+ tokenStr + "&openid=" + openid;
		HttpGet get = new HttpGet(Url);
		HttpResponse response = new DefaultHttpClient().execute(get);
		if (response.getStatusLine().getStatusCode() == 200) {
			result = EntityUtils.toString(response.getEntity(), "UTF-8");
		}
		return result;
	}

	private String isHttpGet1(String code) throws ClientProtocolException,
			IOException {
		String result = null;
		String Url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxdb70678fd4306b57&secret=6ff0f1983a0d72aa233153780ebff6ac&code="
				+ code + "&grant_type=authorization_code";
		HttpGet get = new HttpGet(Url);
		HttpResponse response = new DefaultHttpClient().execute(get);
		if (response.getStatusLine().getStatusCode() == 200) {
			result = EntityUtils.toString(response.getEntity());
		}
		return result;
	}

	@Override
	public void onReq(BaseReq arg0) {
		finish();
	}

	@Override
	public void onResp(BaseResp resp) {
		String result = "";
		if (resp != null) {
			this.resp = resp;
		}
		switch (resp.errCode) {
		case BaseResp.ErrCode.ERR_OK:
			if (LoginActivity.mLoadingDialog.isShowing()) {
				LoginActivity.mLoadingDialog.dismiss();
			}
			if (!MySharePreferences.GetWeiXin(getApplicationContext()).equals(
					"1")) {
				String code = ((SendAuth.Resp) resp).code;
				try {
					String string = isHttpGet1(code);
					try {
						JSONObject jsonObject = new JSONObject(string);
						access_token = jsonObject.getString("access_token");
						openid = jsonObject.getString("openid");
						String reString = isHttpGet(access_token, openid);
						JSONObject jsonObject2 = new JSONObject(reString);
						nickname = jsonObject2.getString("nickname");
						headimgurl = jsonObject2.getString("headimgurl");
						String string2 = OKHTTP_POST.doPost7(
								MyApp.base_address
										+ "loginandReg/ifthirdparty.do",
								"reg_mode", "3", "thirdpart_id", openid,
								"user_ip", Tool.getPhoneIp(), "user_machine",
								Tool.getInfo(), "user_area",
								Home_Page_Activity.Province
										+ Home_Page_Activity.City,"imagesurl",headimgurl,"user_name",nickname);
						if (string2 != null) {
							JSONObject jsonObject3 = new JSONObject(string2);
							String string3 = jsonObject3.getString("1");
							if (string3.equals("登录失败")) {
								MyToast.Toast(getApplicationContext(),
										"登录失败，请稍后再试。");
							}else if (string3.equals("请先选择登录方式")) {
								MyToast.Toast(getApplicationContext(),
										MyApp.Program_Exception_Prompt);
							}else if (string3.equals("注册失败")) {
								MyToast.Toast(getApplicationContext(),
										"登录失败，请稍后再试。");
							}else if (string3.equals("程序异常")) {
								MyToast.Toast(getApplicationContext(),
										MyApp.Program_Exception_Prompt);
							}else {
								String user_id = "";
								JSONArray array=new JSONArray(string3);
								for (int i = 0; i < array.length(); i++) {
									JSONObject jsonObject1=array.getJSONObject(i);
									 user_id=jsonObject1.getString("user_id");
								}
								MyToast.Toast(getApplicationContext(),
										"登录成功,欢迎"+nickname);
								MySharePreferences.Put(user_id, "3",
								WXEntryActivity.this);
								Intent intent1 = new Intent();
								intent1.setAction("微信登录成功");
								WXEntryActivity.this.sendBroadcast(intent1);
							}
//							if (string3.equals("还没有注册")) {
//								Bitmap bitmap = BitmapUtils
//										.GetLocalOrNetBitmap(headimgurl);
//								BitmapUtils.saveMyBitmap("wechat_idimage",
//										bitmap);
//								String string4 = HttpPostUnit.Third_Party(
//										myJpgPath, openid, nickname);
//								if (string4 != null) {
//									JSONObject jsonObject4 = new JSONObject(
//											string4);
//									String user_id = jsonObject4.getString("1");
//									JSONArray array=new JSONArray(user_id);
//									for (int i = 0; i < array.length(); i++) {
//										JSONObject object=array.getJSONObject(i);
//										String string5=object.getString("user_id");
//										MySharePreferences.Put(string5, "2",
//												WXEntryActivity.this);
//									}
//									// Intent intent1 = new Intent();
//									// intent1.setAction("微信登录成功");
//									// WXEntryActivity.this.sendBroadcast(intent1);
//									MyToast.Toast(getApplicationContext(),
//											"登录成功");
//								}
//							} else if (string3.equals("请先选择登录方式")) {
//								Bitmap bitmap = BitmapUtils
//										.GetLocalOrNetBitmap(headimgurl);
//								BitmapUtils.saveMyBitmap("wechat_idimage",
//										bitmap);
//								String string4 = HttpPostUnit.Third_Party(
//										myJpgPath, openid, nickname);
//								JSONObject jsonObject4 = new JSONObject(string4);
//								String user_id = jsonObject4.getString("1");
//								JSONArray array=new JSONArray(user_id);
//								for (int i = 0; i < array.length(); i++) {
//									JSONObject object=array.getJSONObject(i);
//									String string5=object.getString("user_id");
//									MySharePreferences.Put(string5, "2",
//											WXEntryActivity.this);
//								}
//								// Intent intent1 = new Intent();
//								// intent1.setAction("微信登录成功");
//								// WXEntryActivity.this.sendBroadcast(intent1);
//								MyToast.Toast(getApplicationContext(), "登录成功");
//							} else if (string3.equals("登录失败")) {
//								MyToast.Toast(getApplicationContext(),
//										MyApp.Program_Exception_Prompt);
//							} else if (string3.equals("程序异常")) {
//								MyToast.Toast(getApplicationContext(),
//										MyApp.Program_Exception_Prompt);
//							} else {
//								JSONArray array=new JSONArray(string3);
//								for (int i = 0; i < array.length(); i++) {
//									JSONObject object=array.getJSONObject(i);
//									String string5=object.getString("user_id");
//									MySharePreferences.Put(string5, "2",
//											WXEntryActivity.this);
//								}
//								// Intent intent1 = new Intent();
//								// intent1.setAction("微信登录成功");
//								// WXEntryActivity.this.sendBroadcast(intent1);
//								MyToast.Toast(getApplicationContext(), "登录成功");
//							}
						} else {
							MyToast.Toast(getApplicationContext(),
									MyApp.Program_Exception_Prompt);
						}

					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				Toast.makeText(getApplicationContext(), "分享成功",
						Toast.LENGTH_SHORT).show();
			}
			finish();
			break;
		case BaseResp.ErrCode.ERR_USER_CANCEL:
			if (LoginActivity.mLoadingDialog.isShowing()) {
				LoginActivity.mLoadingDialog.dismiss();
			}
			result = "发送取消";
			if (!MySharePreferences.GetWeiXin(WXEntryActivity.this).equals("1")) {
				Toast.makeText(this, "取消登录", Toast.LENGTH_LONG).show();
			} else {
				Toast.makeText(this, "取消分享", Toast.LENGTH_LONG).show();
			}
			finish();
			break;
		case BaseResp.ErrCode.ERR_AUTH_DENIED:
			if (LoginActivity.mLoadingDialog.isShowing()) {
				LoginActivity.mLoadingDialog.dismiss();
			}
			result = "发送被拒绝";
			Toast.makeText(this, "发送被拒绝", Toast.LENGTH_LONG).show();
			finish();
			break;
		default:
			if (LoginActivity.mLoadingDialog.isShowing()) {
				LoginActivity.mLoadingDialog.dismiss();
			}
			result = "发送返回";
			Toast.makeText(this, result, Toast.LENGTH_LONG).show();
			finish();
			break;
		}
	}

}
