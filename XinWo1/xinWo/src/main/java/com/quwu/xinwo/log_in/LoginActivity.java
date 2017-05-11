package com.quwu.xinwo.log_in;

import java.util.concurrent.Executors;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.quwu.xinwo.R;
import com.quwu.xinwo.baidumap.LocationService;
import com.quwu.xinwo.global.MyApp;
import com.quwu.xinwo.home_page.Home_Page_Activity;
import com.quwu.xinwo.mywight.MyToast;
import com.quwu.xinwo.okhttp.OKHTTP_POST;
import com.quwu.xinwo.release.Release_Activity;
import com.quwu.xinwo.swipebacklayout.SwipeBackActivity;
import com.quwu.xinwo.until.HttpGetUnit;
import com.quwu.xinwo.until.InputFilterSpace;
import com.quwu.xinwo.until.IsCheckPhoneNum;
import com.quwu.xinwo.until.MySharePreferences;
import com.quwu.xinwo.until.Tool;
import com.quwu.xinwo.weibo.openapi.AccessTokenKeeper;
import com.quwu.xinwo.weibo.openapi.Constants;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;
import com.tencent.connect.UserInfo;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

public class LoginActivity extends SwipeBackActivity implements OnClickListener {

	private ImageView imageView;// 地区背景图
	private RelativeLayout layout;

	private Button login;// 登录
	private TextView forget;// 忘记密码
	private TextView register;

	private LocationService locationService;

	private TextView provinceText;// 省份（直辖市）
	private TextView cityText;// 地级市（区）

	private EditText phoneEd;
	private EditText passwordEd;

	/**
	 * 第三方登录按钮
	 * */
	private RelativeLayout login_qqRel;// QQ
	private RelativeLayout login_weixinRel;// 微信
	private RelativeLayout login_weiboRel;// 微博

	/**
	 * 需要传入的参数
	 * */
	private String phone;// 手机号码
	private String login_ip;// 登录设备的IP
	private String password;// 密码
	private String login_machine;// 登录设备名称
	private String login_area;// 登录地区

	public static ProgressDialog mLoadingDialog = null;

	/**
	 * QQ相关
	 * */
	private Tencent mTencent;
	private String scope;
	private IUiListener loginListener;
	private UserInfo userInfo;
	private String openid;
	private static final String APP_ID = "1105725578";
	private IUiListener userInfoListener;
	private String nickName;
//	private String myJpgPath = Environment.getExternalStorageDirectory()
//			+ "/pepper/" + "3.png";

	/**
	 * 微博相关
	 * */
	private SsoHandler ssoHandler;
	/** 微博登陆认证对应的listener */
	private AuthListener mLoginListener = new AuthListener();
	private AuthInfo mAuthInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.log_in);
		findID();
		initView();
		isBroadcastReceiver();

		mAuthInfo = new AuthInfo(this, Constants.APP_KEY,
				Constants.REDIRECT_URL, Constants.SCOPE);

	}

	// 广播用于异步接收广播Intent
	private void isBroadcastReceiver() {
		IntentFilter filter2 = new IntentFilter();
		// 接收信息的action
		filter2.addAction("微信登录成功");
		LoginActivity.this.registerReceiver(broadcastReceiver, filter2);
	}

	private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			String a = intent.getAction();
			if (a.equals("微信登录成功")) {
				finish();
			}
		}
	};

	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(broadcastReceiver);
	};

	@Override
	protected void onStart() {

		super.onStart();
		// -----------location config ------------
		locationService = ((MyApp) getApplication()).locationService;
		// 获取locationservice实例，建议应用中只初始化1个location实例，然后使用，可以参考其他示例的activity，都是通过此种方式获取locationservice实例的
		locationService.registerListener(mListener);
		// 注册监听
		int type = getIntent().getIntExtra("from", 0);
		if (type == 0) {
			locationService.setLocationOption(locationService
					.getDefaultLocationClientOption());
		} else if (type == 1) {
			locationService.setLocationOption(locationService.getOption());
		}
		locationService.start();// 定位SDK
	}

	/*****
	 * @see copy funtion to you project
	 *      定位结果回调，重写onReceiveLocation方法，可以直接拷贝如下代码到自己工程中修改
	 * 
	 */
	private BDLocationListener mListener = new BDLocationListener() {

		@Override
		public void onReceiveLocation(BDLocation location) {
			if (null != location
					&& location.getLocType() != BDLocation.TypeServerError) {
				logMsg(location.getProvince(), location.getCity(),
						location.getDistrict());
			}
		}
	};

	/**
	 * 显示请求字符串
	 * 
	 * @param str
	 */
	public void logMsg(String province, String city, String district) {
		try {
			if (province != null) {
				String[] province_strings = province.split("");
				String[] city_strings = city.split("");
				String[] district_strings = district.split("");
				Log.e("", city_strings.length + "");
				Log.e("", district_strings.length + "");
				StringBuffer province_sb = new StringBuffer(256);
				StringBuffer city_sb = new StringBuffer(256);
				StringBuffer district_sb = new StringBuffer(256);
				for (int i = 0; i < province_strings.length - 1; i++) {
					if (province_strings[i].equals("省")) {
						province_strings[i] = "";
					}
					if (i < province_strings.length - 2) {
						province_sb.append(province_strings[i] + "\n");
					} else {
						province_sb.append(province_strings[i]);
					}
				}
				for (int i = 0; i < city_strings.length - 1; i++) {
					if (city_strings[i].equals("市")) {
						city_strings[i] = "";
					}
					if (i < city_strings.length - 2) {
						city_sb.append(city_strings[i] + "\n");
					} else {
						city_sb.append(city_strings[i]);
					}
				}

				for (int i = 0; i < district_strings.length - 1; i++) {
					if (district_strings[i].equals("区")) {
						district_strings[i] = "";
					}
					district_sb.append(district_strings[i] + "\n");
				}
				if (province.equals(city)) {
					provinceText.setText(city_sb.toString());
					cityText.setText(district_sb.toString());
				} else {
					provinceText.setText(province_sb.toString());
					cityText.setText(city_sb.toString());
				}
				locationService.stop();
			} else {
				provinceText.setText("定\n位\n失\n败");
				cityText.setText("");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (ssoHandler != null) {
			ssoHandler.authorizeCallBack(requestCode, resultCode, data);
		}

	}

	private void findID() {
		login_weixinRel = (RelativeLayout) findViewById(R.id.login_weixinRel);
		login_qqRel = (RelativeLayout) findViewById(R.id.login_qqRel);
		login_weiboRel = (RelativeLayout) findViewById(R.id.login_weiboRel);
		login_qqRel.setOnClickListener(this);
		login_weixinRel.setOnClickListener(this);
		login_weiboRel.setOnClickListener(this);

		passwordEd = (EditText) findViewById(R.id.login_passwordEd);
		phoneEd = (EditText) findViewById(R.id.login_phoneEd);

		InputFilterSpace.inputFilterSpace(passwordEd, 15);
		InputFilterSpace.inputFilterSpace(phoneEd, 11);
		phoneEd.setText(MySharePreferences.GetPhone(LoginActivity.this));

		provinceText = (TextView) findViewById(R.id.login_provinceText);
		cityText = (TextView) findViewById(R.id.login_cityText);

		imageView = (ImageView) findViewById(R.id.login_araeImage);
		layout = (RelativeLayout) findViewById(R.id.login_Rel);

		ViewTreeObserver vto = imageView.getViewTreeObserver();
		vto.addOnGlobalLayoutListener(new OnGlobalLayoutListener() { // 获取图片高度
			@SuppressWarnings("deprecation")
			public void onGlobalLayout() {
				imageView.getViewTreeObserver().removeGlobalOnLayoutListener(
						this);
				RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
						RelativeLayout.LayoutParams.WRAP_CONTENT,
						RelativeLayout.LayoutParams.WRAP_CONTENT);
				lp.setMargins(0, imageView.getHeight() / 2, 0, 0); // 根据图片高度获取距离顶部的多少
				layout.setLayoutParams(lp);
			}
		});

		login = (Button) findViewById(R.id.login_btn);
		login.setOnClickListener(this);

		forget = (TextView) findViewById(R.id.login_forget_pasewordText);
		forget.setOnClickListener(this);

		register = (TextView) findViewById(R.id.login_registerText);
		register.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (Tool.isFastDoubleClick()) {
			return;
		} else {
			Intent intent = null;
			switch (v.getId()) {
			case R.id.login_btn:
				phone = phoneEd.getText().toString().trim();
				password = passwordEd.getText().toString().trim();
				login_ip = Tool.getPhoneIp();
				login_machine = Tool.getInfo();
				login_area = provinceText.getText().toString()
						+ cityText.getText().toString();
				if (login_area.equals("定\n位\n中")
						|| login_area.equals("定\n位\n失\n败")) {
					login_area = "";
				}
				if (!IsCheckPhoneNum.judgePhoneNums(phone)) {
					MyToast.Toast(getApplicationContext(), "请输入正确的手机号码！");
				} else if (password.equals("")) {
					MyToast.Toast(getApplicationContext(), "请输入密码！");
				} else if (password.length() < 8) {
					MyToast.Toast(getApplicationContext(), "请输入8-15位密码！");
				} else {
					MySharePreferences.PutPhone(phone, LoginActivity.this);
					new ConfirmTask().executeOnExecutor(Executors
							.newCachedThreadPool());
					intent = new Intent(LoginActivity.this,
							Release_Activity.class);
				}
				break;
			case R.id.login_forget_pasewordText:
				intent = new Intent(LoginActivity.this,
						ForgetPasswardActivity.class);
				break;
			case R.id.login_registerText:
				intent = new Intent(LoginActivity.this, RegisterActivity.class);
				break;
			case R.id.login_weixinRel:
				mLoadingDialog = ProgressDialog.show(LoginActivity.this, // context
						"", // title
						"微信登录中，请稍候...", // message
						true); // 进度是否是不确定的，这只和创建进度条有关
				loginWeiXIn();
				break;
			case R.id.login_qqRel:
				mLoadingDialog = ProgressDialog.show(LoginActivity.this, // context
						"", // title
						"QQ登录中，请稍候...", // message
						true); // 进度是否是不确定的，这只和创建进度条有关
				loginQQ();
				break;
			case R.id.login_weiboRel:
				mLoadingDialog = ProgressDialog.show(LoginActivity.this, // context
						"", // title
						"微博登录中，请稍候...", // message
						true); // 进度是否是不确定的，这只和创建进度条有关
				loginWeiBo();
				break;

			default:
				break;
			}
			if (intent != null) {
				startActivity(intent);
			}
		}
	}

	/**
	 * 微信登录
	 * */
	private void loginWeiXIn() {
		MySharePreferences.PutWeiXin("3", LoginActivity.this);
		IWXAPI api;
		api = WXAPIFactory.createWXAPI(this, "wxdb70678fd4306b57", true);
		api.registerApp("wxdb70678fd4306b57");
		final SendAuth.Req req = new SendAuth.Req();
		req.scope = "snsapi_userinfo";
		req.state = "wechat_sdk_demo_test";
		api.sendReq(req);

	}

	/**
	 * QQ登录
	 * */
	private void loginQQ() {
		// if (!mTencent.isSessionValid()) {
		mTencent.login(LoginActivity.this, scope, loginListener);
		// }
	}

	/**
	 * 微博登录
	 * */
	private void loginWeiBo() {
		System.out.println("进来了");
		// LoginButton mLoginBtnDefault = (LoginButton)
		// findViewById(R.id.log_in_weibo);
		ssoHandler = new SsoHandler(LoginActivity.this, mAuthInfo);
		ssoHandler.authorize(mLoginListener);
		// mLoginBtnDefault.setWeiboAuthInfo(mAuthInfo, mLoginListener);
		// mLoginBtnDefault.setExternalOnClickListener(mButtonClickListener);
	}

	private class AuthListener implements WeiboAuthListener {
		String user_name;
		String photoUrl;
		String openid;

		@Override
		public void onCancel() {
			if (mLoadingDialog != null) {
				mLoadingDialog.dismiss();
			}
			Toast.makeText(getApplicationContext(),
					R.string.weibosdk_demo_toast_auth_canceled,
					Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onComplete(Bundle values) {
			if (mLoadingDialog != null) {
				mLoadingDialog.dismiss();
			}
			Oauth2AccessToken accessToken = Oauth2AccessToken
					.parseAccessToken(values);
			if (accessToken != null && accessToken.isSessionValid()) {
				// String date = new
				// SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(
				// new java.util.Date(accessToken.getExpiresTime()));
				// String format =
				// getString(R.string.weibosdk_demo_token_to_string_format_1);
				// mTokenView.setText(String.format(format,
				// accessToken.getToken(), date));
				System.out.println("accessToken.getToken()="
						+ accessToken.getToken());
				String UID = accessToken.getUid();
				System.out.println("UID=" + UID);
				AccessTokenKeeper.writeAccessToken(getApplicationContext(),
						accessToken);
				String weiboResult = HttpGetUnit
						.HttpClientget("https://api.weibo.com/2/users/show.json?access_token="
								+ accessToken.getToken() + "&uid=" + UID);
				System.out.println("weiboResult=" + weiboResult);
				if (weiboResult!=null) {
				try {
					JSONObject weibojsonObject = new JSONObject(weiboResult);
					user_name = weibojsonObject.getString("screen_name");
					System.out.println("user_name=" + user_name);// 用户名
					photoUrl = weibojsonObject.getString("profile_image_url");
					openid = weibojsonObject.getString("id");
					System.out.println("photoUrl=" + photoUrl);// 头像地址
					String string2 = OKHTTP_POST.doPost7(MyApp.base_address
							+ "loginandReg/ifthirdparty.do", "reg_mode", "4",
							"thirdpart_id", openid, "user_ip",
							Tool.getPhoneIp(), "user_machine", Tool.getInfo(),
							"user_area", Home_Page_Activity.Province
									+ Home_Page_Activity.City, "imagesurl",
							photoUrl, "user_name", user_name);
					if (string2 != null) {
						JSONObject jsonObject3 = new JSONObject(string2);
						String string3 = jsonObject3.getString("1");
						if (string3.equals("登录失败")) {
							MyToast.Toast(getApplicationContext(),
									"登录失败，请稍后再试。");
						} else if (string3.equals("请先选择登录方式")) {
							MyToast.Toast(getApplicationContext(),
									MyApp.Program_Exception_Prompt);
						} else if (string3.equals("注册失败")) {
							MyToast.Toast(getApplicationContext(),
									"登录失败，请稍后再试。");
						} else if (string3.equals("程序异常")) {
							MyToast.Toast(getApplicationContext(),
									MyApp.Program_Exception_Prompt);
						} else {
							String user_id = "";
							JSONArray array = new JSONArray(string3);
							for (int i = 0; i < array.length(); i++) {
								JSONObject jsonObject1 = array.getJSONObject(i);
								user_id = jsonObject1.getString("user_id");
							}
							MyToast.Toast(getApplicationContext(), "登录成功,欢迎"
									+ user_name);
							MySharePreferences.Put(user_id, "4",
									LoginActivity.this);
							Intent intent = new Intent();
							intent.setAction("微博登录成功");
							LoginActivity.this.sendBroadcast(intent);
							finish();
						}
					} else {
						MyToast.Toast(getApplicationContext(),
								MyApp.Program_Exception_Prompt);
					}
				} catch (JSONException e1) {
					e1.printStackTrace();
				}
			}
			}else {
				MyToast.Toast(getApplicationContext(),
						"登录失败，请稍后再试。");
			}
		}

		@Override
		public void onWeiboException(WeiboException e) {
			Toast.makeText(getApplicationContext(), e.getMessage(),
					Toast.LENGTH_SHORT).show();
			if (mLoadingDialog != null) {
				mLoadingDialog.dismiss();
			}
		}
	}

	public void initView() {
		// Tencent类是SDK的主要实现类，通过此访问腾讯开放的OpenAPI。
		// mQQAuth = QQAuth.createInstance(APP_ID,
		// this.getApplicationContext());
		// 实例化
		if (mTencent == null) {
			mTencent = Tencent.createInstance(APP_ID, this);
			SharedPreferences sharedPreferences = this.getSharedPreferences(
					"savemsg", Context.MODE_PRIVATE);
			String expires = sharedPreferences.getString("expires", "-1");// token是否有效
			if (!expires.equals("-1")) {
			}
			mTencent.setAccessToken(sharedPreferences.getString("token", " "),
					expires);
			mTencent.setOpenId(sharedPreferences.getString("openid", ""));
		}
		// mTencent = Tencent.createInstance(APP_ID,
		// this.getApplicationContext());
		scope = "all";
		loginListener = new IUiListener() {

			@Override
			public void onError(UiError arg0) {
				if (mLoadingDialog != null) {
					mLoadingDialog.dismiss();
				}
			}

			/**
			 * {"ret":0,"pay_token":"D3D678728DC580FBCDE15722B72E7365",
			 * "pf":"desktop_m_qq-10000144-android-2002-",
			 * "query_authority_cost":448, "authority_cost":-136792089,
			 * "openid":"015A22DED93BD15E0E6B0DDB3E59DE2D",
			 * "expires_in":7776000, "pfkey":"6068ea1c4a716d4141bca0ddb3df1bb9",
			 * "msg":"", "access_token":"A2455F491478233529D0106D2CE6EB45",
			 * "login_cost":499}
			 */

			@Override
			public void onComplete(Object value) {

				if (value == null) {
					return;
				}

				try {
					JSONObject jo = (JSONObject) value;

					openid = jo.getString("openid");

					// 保存QQ返回的openID 到时候在退出登录的时候用

					new Thread(new Runnable() {

						@Override
						public void run() {
							Message message = new Message();
							message.what = 2;
							handler.sendMessage(message);
						}
					}).start();
				} catch (Exception e) {
				}

			}

			@Override
			public void onCancel() {
				if (mLoadingDialog != null) {
					mLoadingDialog.dismiss();
				}
			}
		};
		userInfoListener = new IUiListener() {

			@Override
			public void onError(UiError arg0) {

			}

			/**
			 * {"is_yellow_year_vip":"0","ret":0, "figureurl_qq_1":
			 * "http:\/\/q.qlogo.cn\/qqapp\/1104732758\/015A22DED93BD15E0E6B0DDB
			 * 3 E 5 9 D E 2 D \ / 4 0 " , "figureurl_qq_2":
			 * "http:\/\/q.qlogo.cn\/qqapp\/1104732758\/015A22DED93BD15E0E6B0DDB
			 * 3 E 5 9 D E 2 D \ / 1 0 0 " ,
			 * "nickname":"攀爬←蜗牛","yellow_vip_level":"0","is_lost":0,"msg":"",
			 * "city":"黄冈","
			 * figureurl_1":"http:\/\/qzapp.qlogo.cn\/qzapp\/1104732758
			 * \/015A22DED93BD15E0E6B0DDB3E59DE2D\/50", "vip":"0","level":"0",
			 * "figureurl_2":
			 * "http:\/\/qzapp.qlogo.cn\/qzapp\/1104732758\/015A22DED93BD15E0E6B0DD
			 * B 3 E 5 9 D E 2 D \ / 1 0 0
			 * " , "province":"湖北", "is_yellow_vip":"0","gender":"男",
			 * "figureurl":
			 * "http:\/\/qzapp.qlogo.cn\/qzapp\/1104732758\/015A22DED93BD15E0E6B0D
			 * D B 3 E 5 9 D E 2 D \ / 3 0 " }
			 */
			@Override
			public void onComplete(Object arg0) {
				if (arg0 == null) {
					return;
				}
				try {
					JSONObject jo = (JSONObject) arg0;
					int ret = jo.getInt("ret");
					if (ret == 100030) {
						// 权限不够，需要增量授权
						Runnable r = new Runnable() {
							public void run() {
								mTencent.reAuth(LoginActivity.this, "all",
										new IUiListener() {

											@Override
											public void onError(UiError arg0) {
												if (mLoadingDialog != null) {
													mLoadingDialog.dismiss();
												}
											}

											@Override
											public void onComplete(Object arg0) {
												if (mLoadingDialog != null) {
													mLoadingDialog.dismiss();
												}
											}

											@Override
											public void onCancel() {
												if (mLoadingDialog != null) {
													mLoadingDialog.dismiss();
												}
											}
										});
							}
						};
						LoginActivity.this.runOnUiThread(r);
					} else {
						if (mLoadingDialog != null) {
							mLoadingDialog.dismiss();
						}
						nickName = jo.getString("nickname");
						String picture = jo.getString("figureurl_qq_1");
						// String string2 = HttpPostUnit.HttpClientPost2(
						// URLUtils.url + "users_loginState", "state",
						// "3", "qq_id", openid, LoginActivity.this);
						String string2 = OKHTTP_POST.doPost7(MyApp.base_address
								+ "loginandReg/ifthirdparty.do", "reg_mode",
								"2", "thirdpart_id", openid, "user_ip",
								Tool.getPhoneIp(), "user_machine",
								Tool.getInfo(), "user_area",
								Home_Page_Activity.Province
										+ Home_Page_Activity.City, "imagesurl",
								picture, "user_name", nickName);
						if (string2 != null) {
							JSONObject jsonObject3 = new JSONObject(string2);
							String string3 = jsonObject3.getString("1");
							if (string3.equals("登录失败")) {
								MyToast.Toast(getApplicationContext(),
										"登录失败，请稍后再试。");
							} else if (string3.equals("请先选择登录方式")) {
								MyToast.Toast(getApplicationContext(),
										MyApp.Program_Exception_Prompt);
							} else if (string3.equals("注册失败")) {
								MyToast.Toast(getApplicationContext(),
										"登录失败，请稍后再试。");
							} else if (string3.equals("程序异常")) {
								MyToast.Toast(getApplicationContext(),
										MyApp.Program_Exception_Prompt);
							} else {
								String user_id = "";
								JSONArray array = new JSONArray(string3);
								for (int i = 0; i < array.length(); i++) {
									JSONObject jsonObject = array
											.getJSONObject(i);
									user_id = jsonObject.getString("user_id");
								}
								MyToast.Toast(getApplicationContext(),
										"登录成功,欢迎" + nickName);
								MySharePreferences.Put(user_id, "2",
										LoginActivity.this);
								Intent intent1 = new Intent();
								intent1.setAction("QQ登录成功");
								LoginActivity.this.sendBroadcast(intent1);
								finish();
							}
							// if (string3.equals("还没有注册")) {
							// Bitmap bitmap = BitmapUtils
							// .GetLocalOrNetBitmap(picture);
							// BitmapUtils.saveMyBitmap("wechat_idimage",
							// bitmap);
							// String string4 = HttpPostUnit.Third_Party(
							// myJpgPath, openid, nickName);
							// if (string4 != null) {
							// JSONObject jsonObject4 = new JSONObject(
							// string4);
							// String user_id = jsonObject4.getString("1");
							// JSONArray array = new JSONArray(user_id);
							// for (int i = 0; i < array.length(); i++) {
							// JSONObject jsonObject = array
							// .getJSONObject(i);
							// String string = jsonObject
							// .getString("user_id");
							// MySharePreferences.Put(string, "2",
							// LoginActivity.this);
							// }
							// // Intent intent1 = new Intent();
							// // intent1.setAction("微信登录成功");
							// // WXEntryActivity.this.sendBroadcast(intent1);
							// MyToast.Toast(getApplicationContext(),
							// "登录成功");
							// if (mLoadingDialog != null) {
							// mLoadingDialog.dismiss();
							// }
							// }
							// } else if (string3.equals("请先选择登录方式")) {
							// Bitmap bitmap = BitmapUtils
							// .GetLocalOrNetBitmap(picture);
							// BitmapUtils.saveMyBitmap("wechat_idimage",
							// bitmap);
							// String string4 = HttpPostUnit.Third_Party(
							// myJpgPath, openid, nickName);
							// JSONObject jsonObject4 = new JSONObject(string4);
							// String user_id = jsonObject4.getString("1");
							// JSONArray array = new JSONArray(user_id);
							// for (int i = 0; i < array.length(); i++) {
							// JSONObject jsonObject = array
							// .getJSONObject(i);
							// String string = jsonObject
							// .getString("user_id");
							// MySharePreferences.Put(string, "2",
							// LoginActivity.this);
							// }
							// // Intent intent1 = new Intent();
							// // intent1.setAction("微信登录成功");
							// // WXEntryActivity.this.sendBroadcast(intent1);
							// MyToast.Toast(getApplicationContext(), "登录成功");
							// if (mLoadingDialog != null) {
							// mLoadingDialog.dismiss();
							// }
							// } else if (string3.equals("登录失败")) {
							// MyToast.Toast(getApplicationContext(),
							// MyApp.Program_Exception_Prompt);
							// if (mLoadingDialog != null) {
							// mLoadingDialog.dismiss();
							// }
							// } else if (string3.equals("程序异常")) {
							// MyToast.Toast(getApplicationContext(),
							// MyApp.Program_Exception_Prompt);
							// if (mLoadingDialog != null) {
							// mLoadingDialog.dismiss();
							// }
							// } else {
							// JSONArray array = new JSONArray(string3);
							// for (int i = 0; i < array.length(); i++) {
							// JSONObject jsonObject = array
							// .getJSONObject(i);
							// String string = jsonObject
							// .getString("user_id");
							// MySharePreferences.Put(string, "2",
							// LoginActivity.this);
							// }
							// // Intent intent1 = new Intent();
							// // intent1.setAction("微信登录成功");
							// // WXEntryActivity.this.sendBroadcast(intent1);
							// MyToast.Toast(getApplicationContext(), "登录成功");
							// if (mLoadingDialog != null) {
							// mLoadingDialog.dismiss();
							// }
							// }
						} else {
							MyToast.Toast(getApplicationContext(),
									MyApp.Program_Exception_Prompt);
							if (mLoadingDialog != null) {
								mLoadingDialog.dismiss();
							}
						}
					}
				} catch (Exception e) {
				}

			}

			@Override
			public void onCancel() {
				if (mLoadingDialog != null) {
					mLoadingDialog.dismiss();
				}
			}
		};
	}

	/**
	 * 确定
	 * */
	private class ConfirmTask extends AsyncTask<Void, Void, Void> {
		String result;

		@Override
		protected Void doInBackground(Void... params) {
			result = OKHTTP_POST.doPost5(MyApp.base_address
					+ "loginandReg/loginphone.do", "phone", phone, "login_ip",
					login_ip, "password", password, "login_machine",
					login_machine, "login_area", login_area);
			if (result != null) {
				try {
					JSONObject jsonObject = new JSONObject(result);
					String string = jsonObject.getString("1");
					if (string.equals("登录失败")) {
						handler.sendMessage(handler.obtainMessage(1, string));
					} else if (string.equals("程序异常")) {
						handler.sendMessage(handler.obtainMessage(1,
								"服务器异常，请稍后重试。"));
					} else if (string.equals("该手机号码还未注册")) {
						handler.sendMessage(handler.obtainMessage(1,
								"该手机号码还未注册过,请重新选择手机号。"));
					} else if (string.equals("密码或账户不正确")) {
						handler.sendMessage(handler.obtainMessage(1, string));
					} else {
						handler.sendMessage(handler.obtainMessage(1, "登录成功"));
						MySharePreferences.Put(string, "0", LoginActivity.this);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			} else {
				handler.sendEmptyMessageDelayed(MyApp.Program_Exception, 10);
			}
			return null;
		}
	}

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 1:
				String string = (String) msg.obj;
				if (string.equals("注册成功")) {
					MyToast.Toast(getApplicationContext(), "注册成功");
					finish();
				}
				MyToast.Toast(getApplicationContext(), string);
				break;
			case 2:
				if (mTencent.getQQToken() == null) {
				}
				userInfo = new UserInfo(LoginActivity.this,
						mTencent.getQQToken());
				userInfo.getUserInfo(userInfoListener);
				break;

			default:
				break;
			}
		};
	};
}
