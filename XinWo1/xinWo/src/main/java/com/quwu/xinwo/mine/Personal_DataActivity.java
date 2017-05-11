package com.quwu.xinwo.mine;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.quwu.xinwo.R;
import com.quwu.xinwo.cropimage.ChooseDialog;
import com.quwu.xinwo.cropimage.CropHelper;
import com.quwu.xinwo.global.MyApp;
import com.quwu.xinwo.gson_entity.Personal_DataEntity;
import com.quwu.xinwo.imageloader.ImageloaderUtils;
import com.quwu.xinwo.mywight.CircleImageView;
import com.quwu.xinwo.mywight.MyToast;
import com.quwu.xinwo.okhttp.OKHTTP_POST;
import com.quwu.xinwo.pickerview.OptionsPickerView;
import com.quwu.xinwo.pickerview.RegionDAO;
import com.quwu.xinwo.pickerview.RegionInfo;
import com.quwu.xinwo.swipebacklayout.SwipeBackActivity;
import com.quwu.xinwo.until.FinishActivity;
import com.quwu.xinwo.until.MySharePreferences;
import com.quwu.xinwo.until.OSUtils;
import com.quwu.xinwo.until.Tool;

/**
 * 
 * 个人资料界面
 * 
 * @author ZhouShaoPeng
 * 
 * */
public class Personal_DataActivity extends SwipeBackActivity implements
		OnClickListener {
	// 地区选择器
	@SuppressWarnings("rawtypes")
	private OptionsPickerView pvOptions;
	private static ArrayList<RegionInfo> item1;
	private static ArrayList<ArrayList<RegionInfo>> item2 = new ArrayList<ArrayList<RegionInfo>>();
	private static ArrayList<ArrayList<ArrayList<RegionInfo>>> item3 = new ArrayList<ArrayList<ArrayList<RegionInfo>>>();

	private LinearLayout headIamge;
	private LinearLayout nameLin;
	private LinearLayout sexLin;
	private LinearLayout areaLin;
	private LinearLayout phoneNumLin;
	private LinearLayout shippingAddressLin;
	private LinearLayout introduceLin;

	private LinearLayout identityLin;

	private CircleImageView circleImageView;
	private TextView nameText;
	private TextView sexText;
	private TextView areaText;
	private TextView phoneNumText;

	private TextView identityText;

	/**
	 * 头像
	 * */
	private CropHelper mCropHelper;
	private ChooseDialog mDialog;
	private LinearLayout layout;

	/**
	 * 服务器返回参数
	 * */
	private String user_pictrue;// 头像
	private String user_name;// 昵称
	private String user_sex;// 性别
	private String address;// 地区
	private String binding_phone;// 手机号
	private String receipt_address;// 收货地址
	private String describe;// 个人介绍
	private String authentication;// 实名认证
	private List<Personal_DataEntity> json_list;

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.personal_data);
		FinishActivity.finish(R.id.personal_data_returnRel,
				Personal_DataActivity.this);
		findID();
		isAreaSelector();// 地区选择
	}

	@Override
	protected void onResume() {
		super.onResume();
		new LoadDataTask().executeOnExecutor(Executors.newCachedThreadPool());
	}

	public static String getTime(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return format.format(date);
	}

	private void findID() {
		layout = (LinearLayout) findViewById(R.id.personal_data_Lin);

		headIamge = (LinearLayout) findViewById(R.id.personal_data_headIamgeLin);
		nameLin = (LinearLayout) findViewById(R.id.personal_data_nameLin);
		sexLin = (LinearLayout) findViewById(R.id.personal_data_sexLin);
		areaLin = (LinearLayout) findViewById(R.id.personal_data_areaLin);
		phoneNumLin = (LinearLayout) findViewById(R.id.personal_data_phoneNumLin);
		shippingAddressLin = (LinearLayout) findViewById(R.id.personal_data_shippingAddressLin);
		introduceLin = (LinearLayout) findViewById(R.id.personal_data_introduceLin);

		identityLin = (LinearLayout) findViewById(R.id.personal_data_identityLin);

		headIamge.setOnClickListener(this);
		nameLin.setOnClickListener(this);
		sexLin.setOnClickListener(this);
		phoneNumLin.setOnClickListener(this);
		shippingAddressLin.setOnClickListener(this);
		introduceLin.setOnClickListener(this);
		identityLin.setOnClickListener(this);

		circleImageView = (CircleImageView) findViewById(R.id.personal_data_headIamge);
		mCropHelper = new CropHelper(this,
				OSUtils.getSdCardDirectory(getApplicationContext())
						+ "/head.png");
		mDialog = new ChooseDialog(this, mCropHelper);

		nameText = (TextView) findViewById(R.id.personal_data_nameText);
		sexText = (TextView) findViewById(R.id.personal_data_sexText);
		areaText = (TextView) findViewById(R.id.personal_data_areaText);
		phoneNumText = (TextView) findViewById(R.id.personal_data_phoneNumText);
		identityText = (TextView) findViewById(R.id.personal_data_identityText);

	}

	public void onClick(View v) {
		Intent intent = null;
		if (Tool.isFastDoubleClick()) {
			return;
		} else {
			switch (v.getId()) {
			case R.id.personal_data_headIamgeLin:// 头像
				mDialog.popSelectDialog(layout);
				break;
			case R.id.personal_data_nameLin:// 昵称
				intent = new Intent(Personal_DataActivity.this,
						Change_NickeNameActivity.class);
				intent.putExtra("nickname", nameText.getText().toString()
						.trim());
				break;
			case R.id.personal_data_sexLin:// 性别
				intent = new Intent(Personal_DataActivity.this,
						Change_SexActivity.class);
				break;
			case R.id.personal_data_areaLin:// 地区
				break;
			case R.id.personal_data_phoneNumLin:// 手机号码
				if (phoneNumText.getText().toString().equals("")) {
					intent = new Intent(Personal_DataActivity.this,
							Binding_PhoneNumActivity.class);
				} else {
					intent = new Intent(Personal_DataActivity.this,
							Change_Bind_PhoneNumActivity.class);
					intent.putExtra("oldNum", phoneNumText.getText().toString()
							.toString());
				}
				break;
			case R.id.personal_data_shippingAddressLin:// 收货地址
				if (receipt_address != null) {
					if (receipt_address.equals("")) {
						// 增加收货地址
						intent = new Intent(Personal_DataActivity.this,
								Add_Shipping_AddressActivity.class);
						intent.putExtra("state", "0");
					} else {
						// 管理收货地址
						intent = new Intent(Personal_DataActivity.this,
								Manage_Shipping_AddressActivity.class);// 管理收货地址
					}
				}
				break;
			case R.id.personal_data_introduceLin:// 个人介绍
				intent = new Intent(Personal_DataActivity.this,
						IntroduceActivity.class);
				break;
			case R.id.personal_data_identityLin:// 身份认证
				intent = new Intent(Personal_DataActivity.this,
						Identity_AuthenticationActivity.class);
				break;

			default:
				break;
			}
		}
		if (intent != null) {
			startActivity(intent);
		}
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == 20) {
			// String string = data.getExtras().getString("name");
			// nametext.setText(string);
		}
		Log.e("onActivityResult", requestCode + "**" + resultCode);
		if (requestCode == RESULT_CANCELED) {
			return;
		} else {
			switch (requestCode) {
			case CropHelper.HEAD_FROM_ALBUM:
				mCropHelper.getDataFromAlbum(data);
				Log.e("onActivityResult", "接收到图库图片");
				break;
			case CropHelper.HEAD_FROM_CAMERA:
				mCropHelper.getDataFromCamera(data);
				Log.e("onActivityResult", "接收到拍照图片");
				break;
			case CropHelper.HEAD_SAVE_PHOTO:
				if (data != null && data.getParcelableExtra("data") != null) {
					circleImageView.setImageBitmap((Bitmap) data
							.getParcelableExtra("data"));
					mCropHelper.savePhoto(data,
							OSUtils.getSdCardDirectory(getApplicationContext())
									+ "/myHead.png");
					new UpImageTask().executeOnExecutor(
							Executors.newCachedThreadPool(),
							OSUtils.getSdCardDirectory(getApplicationContext())
									+ "/myHead.png");
				}
				break;
			default:
				break;
			}
		}
	}

	private Handler handler = new Handler() {
		@SuppressWarnings("unchecked")
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 10000:
				MyToast.Toast(getApplicationContext(),
						MyApp.Program_Exception_Prompt);
				break;
			case 10001:
				MyToast.Toast(getApplicationContext(), MyApp.NODATA_Prompt);
				break;
			case 0:
				ImageloaderUtils.BannerImageLoader(MyApp.base_address
						+ user_pictrue, circleImageView,
						Personal_DataActivity.this);
				nameText.setText(user_name);
				if (user_sex.equals("0")) {
					sexText.setText("保密");
				} else if (user_sex.equals("1")) {
					sexText.setText("男");
				} else if (user_sex.equals("2")) {
					sexText.setText("女");
				}
				areaText.setText(address);
				if (!TextUtils.isEmpty(binding_phone)
						&& binding_phone.length() > 6) {
					StringBuilder sb = new StringBuilder();
					for (int i = 0; i < binding_phone.length(); i++) {
						char c = binding_phone.charAt(i);
						if (i >= 3 && i <= 6) {
							sb.append('*');
						} else {
							sb.append(c);
						}
					}
					phoneNumText.setText(sb.toString());
				}
				if (authentication.equals("0")) {
					identityText.setText("等待认证");
				} else if (authentication.equals("1")) {
					identityText.setText("已经认证");
				} else if (authentication.equals("2")) {
					identityText.setText("认证未通过");
				} else if (authentication.equals("3")) {
					identityText.setText("未认证");
				}
				break;
			case 1:
				System.out.println(System.currentTimeMillis());
				// 三级联动效果

				pvOptions.setPicker(item1, item2, item3, true);
				pvOptions.setCyclic(true, true, true);
				pvOptions.setSelectOptions(0, 0, 0);
				areaLin.setClickable(true);
				break;
			case 2:
				String string = (String) msg.obj;
				MyToast.Toast(getApplicationContext(), string);
				break;

			default:
				break;
			}
		};
	};

	@SuppressWarnings("rawtypes")
	private void isAreaSelector() {
		// 选项选择器
		pvOptions = new OptionsPickerView(Personal_DataActivity.this);

		new Thread(new Runnable() {

			public void run() {
				// TODO Auto-generated method stub
				System.out.println(System.currentTimeMillis());
				if (item1 != null && item2 != null && item3 != null) {
					handler.sendEmptyMessageDelayed(1, 10);
					return;
				}
				item1 = (ArrayList<RegionInfo>) RegionDAO.getProvencesOrCity(1);
				for (RegionInfo regionInfo : item1) {
					item2.add((ArrayList<RegionInfo>) RegionDAO
							.getProvencesOrCityOnParent(regionInfo.getId()));
				}

				for (ArrayList<RegionInfo> arrayList : item2) {
					ArrayList<ArrayList<RegionInfo>> list2 = new ArrayList<ArrayList<RegionInfo>>();
					for (RegionInfo regionInfo : arrayList) {

						ArrayList<RegionInfo> q = (ArrayList<RegionInfo>) RegionDAO
								.getProvencesOrCityOnParent(regionInfo.getId());
						list2.add(q);

					}
					item3.add(list2);
				}

				handler.sendEmptyMessageDelayed(1, 10);

			}
		}).start();
		// 设置选择的三级单位
		// pwOptions.setLabels("省", "市", "区");
		pvOptions.setTitle("选择城市");

		// 设置默认选中的三级项目
		// 监听确定选择按钮

		pvOptions
				.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {

					public void onOptionsSelect(int options1, int option2,
							int options3) {
						// 返回的分别是三个级别的选中位置
						String tx = item1.get(options1).getPickerViewText()
								+ " "
								+ item2.get(options1).get(option2)
										.getPickerViewText()
								+ " "
								+ item3.get(options1).get(option2)
										.get(options3).getPickerViewText();
						areaText.setText(tx);

						new ChangeAddressTask().executeOnExecutor(
								Executors.newCachedThreadPool(), tx);
					}
				});
		// 点击弹出选项选择器
		areaLin.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				pvOptions.show();
			}
		});

		areaLin.setClickable(false);
	}

	private class LoadDataTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			String result = OKHTTP_POST.doPost1(MyApp.base_address
					+ "usersaction/personaldata.do", "user_id", MySharePreferences.GetUser_ID(getApplicationContext()));
			if (result != null) {
				JSONObject jsonObject;
				try {
					jsonObject = new JSONObject(result);
					String result1 = jsonObject.getString("1");
					if (result1 != null) {
						if (result1.equals("程序异常")) {
							handler.sendEmptyMessageDelayed(
									MyApp.Program_Exception, 10);
						} else if (result1.equals("没有数据")) {
							handler.sendEmptyMessageDelayed(MyApp.NODATA, 10);
						} else {
							Gson gson = new Gson();
							json_list = gson.fromJson(result1,
									new TypeToken<List<Personal_DataEntity>>() {
									}.getType());
							for (int i = 0; i < json_list.size(); i++) {
								user_pictrue = json_list.get(i)
										.getUser_pictrue();
								user_name = json_list.get(i).getUser_name();
								user_sex = json_list.get(i).getUser_sex();
								address = json_list.get(i).getAddress();
								binding_phone = json_list.get(i)
										.getBinding_phone();
								receipt_address = json_list.get(i)
										.getReceipt_address();
								describe = json_list.get(i).getDescribe();
								authentication = json_list.get(i)
										.getAuthentication();
								handler.sendEmptyMessageDelayed(0, 10);
							}
						}
					} else {
						handler.sendEmptyMessageDelayed(
								MyApp.Program_Exception, 10);
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				handler.sendEmptyMessageDelayed(MyApp.Program_Exception, 10);
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
		}
	}

	private class UpImageTask extends AsyncTask<String, Void, Void> {

		@Override
		protected Void doInBackground(String... params) {
			try {
				String string = OKHTTP_POST.uploading(MyApp.base_address
						+ "usersaction/upduserspicture.do", "user_id",
						MySharePreferences.GetUser_ID(getApplicationContext()), "photo",
						OSUtils.getSdCardDirectory(getApplicationContext())
								+ "/myHead.png");
				if (string != null) {
					try {
						JSONObject jsonObject = new JSONObject(string);
						String string2 = jsonObject.getString("1");
						if (string2.equals("未知错误")) {
							handler.sendEmptyMessageDelayed(
									MyApp.Program_Exception, 10);
						} else if (string2.equals("程序异常")) {
							handler.sendEmptyMessageDelayed(
									MyApp.Program_Exception, 10);
						} else {
							handler.sendMessage(handler.obtainMessage(2,
									string2));
						}
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					handler.sendEmptyMessageDelayed(MyApp.Program_Exception, 10);
				}
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			new LoadDataTask().executeOnExecutor(Executors
					.newCachedThreadPool());
		}
	}

	private class ChangeAddressTask extends AsyncTask<String, Void, Void> {

		@Override
		protected Void doInBackground(String... params) {
			String result = OKHTTP_POST.doPost2(MyApp.base_address
					+ "usersaction/updusersaddress.do", "user_id",
					MySharePreferences.GetUser_ID(getApplicationContext()), "addres", params[0]);
			if (result != null) {
				try {
					JSONObject jsonObject = new JSONObject(result);
					String string = jsonObject.getString("1");
					if (string.equals("程序异常")) {
						handler.sendEmptyMessageDelayed(
								MyApp.Program_Exception, 10);
					} else {
						handler.sendMessage(handler.obtainMessage(0, string));
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				handler.sendEmptyMessageDelayed(MyApp.Program_Exception, 10);
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			new LoadDataTask().executeOnExecutor(Executors
					.newCachedThreadPool());
		}
	}
}
