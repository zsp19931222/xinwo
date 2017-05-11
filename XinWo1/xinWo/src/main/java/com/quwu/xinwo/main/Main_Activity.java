package com.quwu.xinwo.main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.quwu.xinwo.R;
import com.quwu.xinwo.bean.HP_Good_GoodsBean;
import com.quwu.xinwo.bean.HP_NearbyBean;
import com.quwu.xinwo.discover.Discover_Activity;
import com.quwu.xinwo.global.MyApp;
import com.quwu.xinwo.gson_entity.AdvertisementEntity;
import com.quwu.xinwo.gson_entity.HP_BannerEntity;
import com.quwu.xinwo.home_page.Home_Page_Activity;
import com.quwu.xinwo.imageloader.ImageloaderUtils;
import com.quwu.xinwo.inventory.InventoryActivity;
import com.quwu.xinwo.log_in.LoginActivity;
import com.quwu.xinwo.mine.Mine_Activity;
import com.quwu.xinwo.mywight.CustomProgressDialog3;
import com.quwu.xinwo.okhttp.OKHTTP_POST;
import com.quwu.xinwo.until.Tool;
import com.quwu.xinwo.webpage.WebPage;

/**
 * 
 * 主界面
 * 
 * @author ZhouShaoPeng
 * 
 * */

public class Main_Activity extends Activity implements OnClickListener {
	private Discover_Activity fragment2;// 发现页面
	private Home_Page_Activity fragment1;// 首页页面
	private InventoryActivity fragment4;// 清单页面
	private Mine_Activity fragment5;// 我的页面
	// private Release_Activity fragment3;// 发布页面
	private RadioGroup radioGroup;
	private ImageView rb3Image;// 发布按钮
	private RadioButton rb1;
	private RadioButton rb2;
	private RadioButton rb3;
	private RadioButton rb4;
	private RadioButton rb5;

	private ImageView imageView1;
	private ImageView imageView2;
	private ImageView imageView4;
	private ImageView imageView5;

	private LinearLayout lin1, lin2, lin3, lin4, lin5;

	private TextView textView1, textView2, textView3, textView4, textView5;

	private RelativeLayout mainRel;
	private RelativeLayout adRel;
	private ImageView ad_imageView;
	private TextView ad_Text;

	/**
	 * 服务器广告时间
	 * */
	private String cover_time;// 广告时间
	private String cover_url;// 广告链接
	private String cover_pictrue;// 广告图片
	private String cover_title;// 广告标题
	/**
	 * 导航栏图标
	 * */
	private String sixshowpictures_id;// 自增ID
	private String sixshowpictures_url;// 图片路径
	private String sixshowpictures_title;// 图片对应的标题
	private String sixshowpictures_state;// 0-显示，1-不显示(是否使用该图标)
	private List<AdvertisementEntity> list;
	private List<AdvertisementEntity> json_list;

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:
				ImageloaderUtils.BannerImageLoader(cover_pictrue, ad_imageView,
						Main_Activity.this);
				ad_imageView.setOnClickListener(Main_Activity.this);
				long l = Long.valueOf(cover_time) * 1000;

				break;

			default:
				break;
			}
		};
	};

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		findID();
		// isRadioGroup();
		// new Task().executeOnExecutor(Executors.newCachedThreadPool());
		new ADTask().executeOnExecutor(Executors.newCachedThreadPool());
		isBroadcastReceiver();
	}

	// 广播用于异步接收广播Intent
	private void isBroadcastReceiver() {
		IntentFilter filter2 = new IntentFilter();
		// 接收信息的action
		filter2.addAction("微信登录成功");
		Main_Activity.this.registerReceiver(broadcastReceiver, filter2);

		IntentFilter filter3 = new IntentFilter();
		// 接收信息的action
		filter3.addAction("QQ登录成功");
		Main_Activity.this.registerReceiver(broadcastReceiver, filter3);

		IntentFilter filter4 = new IntentFilter();
		// 接收信息的action
		filter4.addAction("微博登录成功");
		Main_Activity.this.registerReceiver(broadcastReceiver, filter4);

	}

	private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			FragmentManager manager = getFragmentManager();
			FragmentTransaction transaction = manager.beginTransaction();
			String a = intent.getAction();
			if (a.equals("微信登录成功")) {
				if (fragment5 == null) {
					fragment5 = new Mine_Activity();
				}
				if (!fragment5.isAdded()) {
					transaction.add(R.id.main_frame, fragment5);
				} else {
					if (fragment1 != null || fragment2 != null
							|| fragment4 != null) {
						transaction.hide(fragment1);
						transaction.hide(fragment2);
						transaction.hide(fragment4);
						// transaction.hide(fragment3);
					}
					transaction.show(fragment5);
				}
			} else if (a.equals("QQ登录成功")) {
				if (fragment5 == null) {
					fragment5 = new Mine_Activity();
				}
				if (!fragment5.isAdded()) {
					transaction.add(R.id.main_frame, fragment5);
				} else {
					if (fragment1 != null || fragment2 != null
							|| fragment4 != null) {
						transaction.hide(fragment1);
						transaction.hide(fragment2);
						transaction.hide(fragment4);
						// transaction.hide(fragment3);
					}
					transaction.show(fragment5);
				}
			} else if (a.equals("微博登录成功")) {
				if (fragment5 == null) {
					fragment5 = new Mine_Activity();
				}
				if (!fragment5.isAdded()) {
					transaction.add(R.id.main_frame, fragment5);
				} else {
					if (fragment1 != null || fragment2 != null
							|| fragment4 != null) {
						transaction.hide(fragment1);
						transaction.hide(fragment2);
						transaction.hide(fragment4);
						// transaction.hide(fragment3);
					}
					transaction.show(fragment5);
				}
			}
			if (transaction != null) {
				/**
				 * 如果用transaction.commit();这里会报错（Can not perform this action
				 * after onSaveInstanceState）大致意思是说我使用的
				 * commit方法是在Activity的onSaveInstanceState()之后调用的，这样会出错，因为
				 * onSaveInstanceState方法是在该Activity即将被销毁前调用
				 * ，来保存Activity数据的，如果在保存玩状态后
				 * 再给它添加Fragment就会出错。解决办法就是把commit（）方法替换成
				 * commitAllowingStateLoss()就行 了，其效果是一样的。
				 * */
				transaction.commitAllowingStateLoss();
			}
		}
	};

	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(broadcastReceiver);
	};

	private void findID() {

		mainRel = (RelativeLayout) findViewById(R.id.main_rel);

		adRel = (RelativeLayout) findViewById(R.id.main_advertisement_Rel);
		ad_imageView = (ImageView) findViewById(R.id.main_advertisement_Image);
		ad_Text = (TextView) findViewById(R.id.main_advertisement_Text);
		ad_Text.setOnClickListener(this);
		new CountDownTimer(5000, 1000) {

			public void onTick(long millisUntilFinished) {
				ad_Text.setText("跳过：" + millisUntilFinished / 1000 + "S");
			}

			@Override
			public void onFinish() {
				adRel.setVisibility(View.GONE);
			}
		}.start();

		imageView1 = (ImageView) findViewById(R.id.main_Image1);
		imageView2 = (ImageView) findViewById(R.id.main_Image2);
		imageView4 = (ImageView) findViewById(R.id.main_Image4);
		imageView5 = (ImageView) findViewById(R.id.main_Image5);

		lin1 = (LinearLayout) findViewById(R.id.main_LIn1);
		lin2 = (LinearLayout) findViewById(R.id.main_LIn2);
		lin3 = (LinearLayout) findViewById(R.id.main_LIn3);
		lin4 = (LinearLayout) findViewById(R.id.main_LIn4);
		lin5 = (LinearLayout) findViewById(R.id.main_LIn5);

		lin1.setOnClickListener(this);
		lin2.setOnClickListener(this);
		lin4.setOnClickListener(this);
		lin5.setOnClickListener(this);

		textView1 = (TextView) findViewById(R.id.main_Text1);
		textView2 = (TextView) findViewById(R.id.main_Text2);
		textView3 = (TextView) findViewById(R.id.main_Text3);
		textView4 = (TextView) findViewById(R.id.main_Text4);
		textView5 = (TextView) findViewById(R.id.main_Text5);

		radioGroup = (RadioGroup) findViewById(R.id.main_rg);
		fragment2 = new Discover_Activity();
		fragment1 = new Home_Page_Activity();
		fragment4 = new InventoryActivity();
		fragment5 = new Mine_Activity();
		// fragment3 = new Release_Activity();

		rb1 = (RadioButton) findViewById(R.id.main_rb1);
		rb2 = (RadioButton) findViewById(R.id.main_rb2);
		rb3 = (RadioButton) findViewById(R.id.main_rb3);
		rb4 = (RadioButton) findViewById(R.id.main_rb4);
		rb5 = (RadioButton) findViewById(R.id.main_rb5);

		rb3Image = (ImageView) findViewById(R.id.main_rb3Image);
		rb3Image.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				if (Tool.isFastDoubleClick()) {
					return;
				} else {
					Intent intent = new Intent(Main_Activity.this,
							LoginActivity.class);
					startActivity(intent);
				}
			}
		});
		// rb3Image.setOnClickListener();
	}

	// // 点击切换页面
	// private void isRadioGroup() {
	//
	// radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
	//
	// public void onCheckedChanged(RadioGroup group, int checkedId) {
	// FragmentManager manager = getFragmentManager();
	// FragmentTransaction transaction = manager.beginTransaction();
	// switch (checkedId) {
	// case R.id.main_rb1:
	// // 如果tabFragment1为空，说明还没创建Tab1
	// if (fragment1 == null) {
	//
	// fragment1 = new Home_Page_Activity();
	// }
	// // 如果isAdded == true 表示 tab1 已加入布局中
	// if (!fragment1.isAdded()) {
	// transaction.add(R.id.main_frame, fragment1);
	// } else {
	// // 如果tab2不为空，把tab2隐藏就是、
	// if (fragment2 != null || fragment4 != null
	// || fragment5 != null) {
	// // //transaction.hide(fragment3);
	// transaction.hide(fragment2);
	// transaction.hide(fragment4);
	// transaction.hide(fragment5);
	// }
	// // Log.v("rush_yu", "hh");
	// // 显示tab1
	// transaction.show(fragment1);
	// }
	// // transaction.replace(R.id.main_frame, fragment1);
	// break;
	// case R.id.main_rb2:
	// if (fragment2 == null) {
	//
	// fragment2 = new Discover_Activity();
	// }
	// if (!fragment2.isAdded()) {
	// dialog3.show();
	// transaction.add(R.id.main_frame, fragment2);
	// } else {
	// dialog3.show();
	// if (fragment1 != null || fragment4 != null
	// || fragment5 != null) {
	// transaction.hide(fragment1);
	// // transaction.hide(fragment3);
	// transaction.hide(fragment4);
	// transaction.hide(fragment5);
	// }
	// transaction.show(fragment2);
	// }
	//
	// break;
	// case R.id.main_rb4:
	// if (fragment4 == null) {
	// fragment4 = new InventoryActivity();
	// }
	// if (!fragment4.isAdded()) {
	// transaction.add(R.id.main_frame, fragment4);
	// } else {
	// if (fragment1 != null || fragment2 != null
	// || fragment5 != null) {
	// transaction.hide(fragment1);
	// transaction.hide(fragment2);
	// // transaction.hide(fragment3);
	// transaction.hide(fragment5);
	// }
	// transaction.show(fragment4);
	// }
	//
	// break;
	// case R.id.main_rb5:
	// if (fragment5 == null) {
	// fragment5 = new Mine_Activity();
	// }
	// if (!fragment5.isAdded()) {
	// transaction.add(R.id.main_frame, fragment5);
	// } else {
	// if (fragment1 != null || fragment2 != null
	// || fragment4 != null) {
	// transaction.hide(fragment1);
	// transaction.hide(fragment2);
	// transaction.hide(fragment4);
	// // transaction.hide(fragment3);
	// }
	// transaction.show(fragment5);
	// }
	// break;
	//
	// default:
	// break;
	// }
	// transaction.commit();
	// }
	// });
	// }

	// 进来加载首页
	private void isFrist() {
		FragmentManager manager = getFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		transaction.replace(R.id.main_frame, fragment1);
		transaction.commit();
	}

	// class Task extends AsyncTask<Void, Void, Void> {
	//
	// protected Void doInBackground(Void... params) {
	// Map<Object, Object> map = new HashMap<Object, Object>();
	// map.put("citypagesdto.normal_use", "1");
	// map.put("citypagesdto.warranty_period", "10");
	// map.put("citypagesdto.no_repair", "100");
	// map.put("citypagesdto.brand_new", "1000");
	// map.put("citypagesdto.jingdong", "10000");
	// map.put("citypagesdto.mainland_licensed", "100000");
	// map.put("citypagesdto.since", "1000000");
	// map.put("citypagesdto.good_region", "10000000");
	// map.put("citypagesdto.twolevel_id", "1000000000");
	// map.put("citypagesdto.sortorder_id", "10000000000");
	// try {
	// String string = OKHTTP_POST.doPostMap(
	// "http://47.89.27.93:9003/", map);
	// Log.e("", string);
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// return null;
	//
	// }
	//
	// protected void onPostExecute(Void result) {
	// // TODO Auto-generated method stub
	// super.onPostExecute(result);
	// ImageloaderUtils
	// .MyImageLoader2(
	// "http://192.168.1.105:8080/Precordium/showpictures//homepage//btn_home.png",
	// imageView1, getApplicationContext());
	// ImageloaderUtils
	// .MyImageLoader2(
	// "http://192.168.1.105:8080/Precordium/showpictures//homepage//btn_finder.png",
	// imageView2, getApplicationContext());
	// ImageloaderUtils
	// .MyImageLoader2(
	// "http://192.168.1.105:8080/Precordium/showpictures//homepage//btn_add.png",
	// rb3Image, getApplicationContext());
	// ImageloaderUtils
	// .MyImageLoader2(
	// "http://192.168.1.105:8080/Precordium/showpictures//homepage//btn_buy_car.png",
	// imageView4, getApplicationContext());
	// ImageloaderUtils
	// .MyImageLoader2(
	// "http://192.168.1.105:8080/Precordium/showpictures//homepage//btn_mine.png",
	// imageView5, getApplicationContext());
	// }
	// }

	public void onClick(View v) {
		if (Tool.isFastDoubleClick()) {
			return;
		} else {
			FragmentManager manager = getFragmentManager();
			FragmentTransaction transaction = manager.beginTransaction();
			// transaction.setCustomAnimations(
			// R.anim.fragment_slide_left_enter,
			// R.anim.fragment_slide_left_exit);

			switch (v.getId()) {
			case R.id.main_LIn1:
				Log.e("main_LIn1", "main_LIn1");
				// 如果tabFragment1为空，说明还没创建Tab1
				if (fragment1 == null) {
					fragment1 = new Home_Page_Activity();
				}
				// 如果isAdded == true 表示 tab1 已加入布局中
				if (!fragment1.isAdded()) {
					transaction.add(R.id.main_frame, fragment1);
				} else {
					// 如果tab2不为空，把tab2隐藏就是、
					if (fragment2 != null || fragment4 != null
							|| fragment5 != null) {
						// transaction.hide(fragment3);
						transaction.hide(fragment2);
						transaction.hide(fragment4);
						transaction.hide(fragment5);
					}
					// Log.v("rush_yu", "hh");
					// 显示tab1
					transaction.show(fragment1);
				}
				// transaction.replace(R.id.main_frame, fragment1);
				break;
			case R.id.main_LIn2:
				// Log.e("main_LIn1", "main_LIn20");
				// if (fragment2 == null) {
				// fragment2 = new Discover_Activity();
				// Log.e("main_LIn1", "main_LIn21");
				// }
				// if (!fragment2.isAdded()) {
				// transaction.add(R.id.main_frame, fragment2);
				// } else {
				// if (fragment1 != null || fragment4 != null
				// || fragment5 != null) {
				// Log.e("main_LIn1", "main_LIn23");
				// transaction.hide(fragment1);
				// // transaction.hide(fragment3);
				// transaction.hide(fragment4);
				// transaction.hide(fragment5);
				// }
				// transaction.show(fragment2);
				// }
				new Task1().executeOnExecutor(Executors.newCachedThreadPool(),
						transaction);
				break;
			case R.id.main_LIn3:

				break;
			case R.id.main_LIn4:
				Log.e("main_LIn1", "main_LIn4");
				if (fragment4 == null) {
					fragment4 = new InventoryActivity();
				}
				if (!fragment4.isAdded()) {
					transaction.add(R.id.main_frame, fragment4);
				} else {
					if (fragment1 != null || fragment2 != null
							|| fragment5 != null) {
						transaction.hide(fragment1);
						transaction.hide(fragment2);
						// transaction.hide(fragment3);
						transaction.hide(fragment5);
					}
					transaction.show(fragment4);
				}

				break;
			case R.id.main_LIn5:
				Log.e("main_LIn1", "main_LIn5");
				if (fragment5 == null) {
					fragment5 = new Mine_Activity();
				}
				if (!fragment5.isAdded()) {
					transaction.add(R.id.main_frame, fragment5);
				} else {
					if (fragment1 != null || fragment2 != null
							|| fragment4 != null) {
						transaction.hide(fragment1);
						transaction.hide(fragment2);
						transaction.hide(fragment4);
						// transaction.hide(fragment3);
					}
					transaction.show(fragment5);
				}
				break;
			case R.id.main_advertisement_Image:
				Intent intent = new Intent(Main_Activity.this, WebPage.class);
				intent.putExtra("url", cover_url);
				intent.putExtra("title", cover_title);
				startActivity(intent);
				break;
			case R.id.main_advertisement_Text:
				adRel.setVisibility(View.GONE);
				break;

			default:
				break;
			}
			if (transaction != null) {
				transaction.commit();
			}
		}
	}

	public Object clientSocket() {
		Socket socket = null;
		try {
			socket = new Socket("192.168.1.19", 8888);
			// 用于向客户端发送数据的输出流
			DataOutputStream dos = new DataOutputStream(
					socket.getOutputStream());
			// 服务器向客户端发送连接成功确认信息
			PrintWriter output = new PrintWriter(socket.getOutputStream());
			String a = "你的账号已在其他地方登陆";
			output.print(a);
			dos.writeUTF(a);
			String mes = null;
			while (true) {
				DataInputStream dis = new DataInputStream(
						socket.getInputStream());
				mes = dis.readUTF(dis);
				System.out.println("这是服务器返回的数据============" + mes);
				Log.e("这是服务器返回的数据============", mes);
			}
		} catch (Exception e) {
			return "";
		}
	}

	private class Task1
			extends
			AsyncTask<FragmentTransaction, FragmentTransaction, FragmentTransaction> {

		FragmentTransaction fragmentTransaction;

		protected FragmentTransaction doInBackground(
				FragmentTransaction... params) {
			Log.e("main_LIn1", "main_LIn20");
			if (fragment2 == null) {
				fragment2 = new Discover_Activity();
				Log.e("main_LIn1", "main_LIn21");
			}
			if (!fragment2.isAdded()) {
				params[0].add(R.id.main_frame, fragment2);
			} else {
				if (fragment1 != null || fragment4 != null || fragment5 != null) {
					Log.e("main_LIn1", "main_LIn23");
					params[0].hide(fragment1);
					// transaction.hide(fragment3);
					params[0].hide(fragment4);
					params[0].hide(fragment5);
				}
				params[0].show(fragment2);
			}
			return null;
		}

		protected void onPostExecute(FragmentTransaction result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			// fragmentTransaction.addToBackStack(null);
			// fragmentTransaction.commit();
		}
	}

	/**
	 * 加载广告
	 * */
	private class ADTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			isFrist();
			String string = OKHTTP_POST.doPost1(MyApp.base_address
					+ "coveraction/cover.do", "", "");
			if (string != null) {
				JSONObject jsonObject;
				try {
					jsonObject = new JSONObject(string);
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
									new TypeToken<List<AdvertisementEntity>>() {
									}.getType());
							for (int i = 0; i < json_list.size(); i++) {
								cover_time = json_list.get(i).getCover_time();
								cover_url = json_list.get(i).getCover_url();
								cover_pictrue = json_list.get(i)
										.getCover_pictrue();
								handler.sendEmptyMessageDelayed(0, 10);
							}
						}
						handler.sendEmptyMessageAtTime(0, 10);
					} else {
						handler.sendEmptyMessageDelayed(
								MyApp.Program_Exception, 10);
					}
				} catch (JSONException e) {
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
			new TAB_Icon().executeOnExecutor(Executors.newCachedThreadPool());
		}
	}

	private class TAB_Icon extends AsyncTask<Void, Void, Void> {
		private String TAB_Icon_string;// 众筹好货第二部分的数据

		@Override
		protected Void doInBackground(Void... params) {
			list = new ArrayList<AdvertisementEntity>();
			String string = OKHTTP_POST.doPost1(MyApp.base_address
					+ "showpictures/selectshowpictures.do", "", "");
			if (string != null) {
				JSONObject jsonObject;
				try {
					jsonObject = new JSONObject(string);
					TAB_Icon_string = jsonObject.getString("6");
					if (TAB_Icon_string != null) {
						if (TAB_Icon_string.equals("程序异常")) {
							handler.sendEmptyMessageDelayed(
									MyApp.Program_Exception, 10);
						} else if (TAB_Icon_string.equals("没有数据")) {
							handler.sendEmptyMessageDelayed(MyApp.NODATA, 10);
						} else {
							Gson gson = new Gson();
							json_list = gson.fromJson(TAB_Icon_string,
									new TypeToken<List<AdvertisementEntity>>() {
									}.getType());
							for (int i = 0; i < json_list.size(); i++) {
								sixshowpictures_id = json_list.get(i)
										.getSixshowpictures_id();
								sixshowpictures_url = json_list.get(i)
										.getSixshowpictures_url();
								sixshowpictures_title = json_list.get(i)
										.getSixshowpictures_title();
								sixshowpictures_state = json_list.get(i)
										.getSixshowpictures_state();
								list.add(new AdvertisementEntity(
										sixshowpictures_id, MyApp.base_address
												+ sixshowpictures_url,
										sixshowpictures_title,
										sixshowpictures_state));
							}
						}
					}
				} catch (JSONException e) {
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
			if (list.size() != 0) {
				ImageloaderUtils.BannerImageLoader(list.get(0)
						.getSixshowpictures_url(), imageView1,
						Main_Activity.this);
				textView1.setText(list.get(0).getSixshowpictures_title());
				ImageloaderUtils.BannerImageLoader(list.get(1)
						.getSixshowpictures_url(), imageView2,
						Main_Activity.this);
				textView2.setText(list.get(0).getSixshowpictures_title());
				ImageloaderUtils
						.BannerImageLoader(
								list.get(2).getSixshowpictures_url(), rb3Image,
								Main_Activity.this);
				textView3.setText(list.get(0).getSixshowpictures_title());
				ImageloaderUtils.BannerImageLoader(list.get(3)
						.getSixshowpictures_url(), imageView4,
						Main_Activity.this);
				textView4.setText(list.get(0).getSixshowpictures_title());
				ImageloaderUtils.BannerImageLoader(list.get(4)
						.getSixshowpictures_url(), imageView5,
						Main_Activity.this);
				textView5.setText(list.get(0).getSixshowpictures_title());
			}
		}
	}
}
