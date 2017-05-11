package com.quwu.xinwo.mine;

/**
 * 
 * 我的界面
 * 
 * @author ZhouShaoPeng
 * 
 * */
import java.util.List;
import java.util.concurrent.Executors;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.quwu.xinwo.R;
import com.quwu.xinwo.global.MyApp;
import com.quwu.xinwo.gson_entity.MineEntity;
import com.quwu.xinwo.imageloader.ImageloaderUtils;
import com.quwu.xinwo.log_in.LoginActivity;
import com.quwu.xinwo.mywight.CircleImageView;
import com.quwu.xinwo.mywight.MyToast;
import com.quwu.xinwo.okhttp.OKHTTP_POST;
import com.quwu.xinwo.until.MySharePreferences;
import com.quwu.xinwo.until.Tool;

public class Mine_Activity extends Fragment implements OnClickListener {

	private View view;
	private RelativeLayout mine_news_numRel;// 消息按钮
	private RelativeLayout mine_settingRel;// 设置按钮

	private TextView mine_user_nameText;// 用户名文本
	private TextView mine_wowotounumText;// 窝窝头数目文本

	private Button mine_sexBtn;// 性别图标
	private Button mine_rechargeBtn;// 充值按钮
	private Button mine_news_numBtn;// 消息提示
	private CircleImageView mine_head_portraitBtn;// 头像

	private LinearLayout mine_releaseLin;// 我的发布
	private LinearLayout mine_saleLin;// 我的卖出
	private LinearLayout mine_attentionLin;// 我的关注
	private LinearLayout mine_Lin;// 头像背景

	private LinearLayout mine_underwayLin;// 我的发布
	private LinearLayout mine_announcedLin;// 已经揭晓
	private LinearLayout mine_lucky_recordLin;// 幸运记录
	private LinearLayout mine_trading_recordLin;// 交易记录

	private RelativeLayout mine_orderRel;// 订单
	private RelativeLayout mine_red_packetRel;// 红包
	private RelativeLayout mine_sales_returnRel;// 退货
	private RelativeLayout mine_accountRel;// 账户
	private RelativeLayout mine_welfareRel;// 每日福利
	private RelativeLayout mine_cooperationRel;// 合作
	private RelativeLayout mine_recommendRel;// 推荐
	private RelativeLayout mine_shareRel;// 分享

	
	/**
	 * 返回的用户头像等数据
	 * */
	private String user_pictrue;// 用户头像
	private String user_name;// 用户名
	private String balance;// 余额
	private String user_sex;// 性别
	private String num;// 消息数量
	private List<MineEntity> json_list;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.mine, null);
		findID();
		return view;
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		new PersonalInformationTask().executeOnExecutor(Executors
				.newCachedThreadPool());
	}

	
	// 绑定ID
	private void findID() {
		mine_underwayLin = (LinearLayout) view
				.findViewById(R.id.mine_underwayLin);
		mine_announcedLin = (LinearLayout) view
				.findViewById(R.id.mine_announcedLin);
		mine_lucky_recordLin = (LinearLayout) view
				.findViewById(R.id.mine_lucky_recordLin);
		mine_trading_recordLin = (LinearLayout) view
				.findViewById(R.id.mine_trading_recordLin);

		mine_underwayLin.setOnClickListener(this);
		mine_announcedLin.setOnClickListener(this);
		mine_lucky_recordLin.setOnClickListener(this);
		mine_trading_recordLin.setOnClickListener(this);

		mine_news_numRel = (RelativeLayout) view
				.findViewById(R.id.mine_news_numRel);
		mine_settingRel = (RelativeLayout) view
				.findViewById(R.id.mine_settingRel);

		mine_user_nameText = (TextView) view
				.findViewById(R.id.mine_user_nameText);
		mine_wowotounumText = (TextView) view
				.findViewById(R.id.mine_wowotounumText);

		mine_head_portraitBtn = (CircleImageView) view
				.findViewById(R.id.mine_head_portraitBtn);
		mine_head_portraitBtn.setOnClickListener(this);

		mine_sexBtn = (Button) view.findViewById(R.id.mine_sexBtn);
		mine_rechargeBtn = (Button) view.findViewById(R.id.mine_rechargeBtn);
		mine_news_numBtn = (Button) view.findViewById(R.id.mine_news_numBtn);

		if (MySharePreferences.GetState(getActivity()).equals("")) {
			mine_rechargeBtn.setText("登录");
		} else {
			mine_rechargeBtn.setText("充值");
		}

		mine_Lin = (LinearLayout) view.findViewById(R.id.mine_Lin);

		// InputStream is =
		// getResources().openRawResource(R.drawable.personal_head_portrait);
		// Bitmap mBitmap = BitmapFactory.decodeStream(is);
		// mine_Lin.setBackgroundDrawable(BlurImageview.BlurImages(mBitmap,
		// getActivity()));
		// mine_Lin.setBackground(BoxBlurFilter.BoxBlurFilter(mBitmap));

		mine_releaseLin = (LinearLayout) view
				.findViewById(R.id.mine_releaseLin);
		mine_saleLin = (LinearLayout) view.findViewById(R.id.mine_saleLin);
		mine_attentionLin = (LinearLayout) view
				.findViewById(R.id.mine_attentionLin);

		mine_orderRel = (RelativeLayout) view.findViewById(R.id.mine_orderRel);
		mine_red_packetRel = (RelativeLayout) view
				.findViewById(R.id.mine_red_packetRel);
		mine_sales_returnRel = (RelativeLayout) view
				.findViewById(R.id.mine_sales_returnRel);
		mine_accountRel = (RelativeLayout) view
				.findViewById(R.id.mine_accountRel);
		mine_welfareRel = (RelativeLayout) view
				.findViewById(R.id.mine_welfareRel);
		mine_cooperationRel = (RelativeLayout) view
				.findViewById(R.id.mine_cooperationRel);
		mine_recommendRel = (RelativeLayout) view
				.findViewById(R.id.mine_recommendRel);
		mine_shareRel = (RelativeLayout) view.findViewById(R.id.mine_shareRel);

		mine_news_numRel.setOnClickListener(this);
		mine_settingRel.setOnClickListener(this);
		mine_rechargeBtn.setOnClickListener(this);
		mine_releaseLin.setOnClickListener(this);
		mine_saleLin.setOnClickListener(this);
		mine_attentionLin.setOnClickListener(this);
		mine_orderRel.setOnClickListener(this);
		mine_red_packetRel.setOnClickListener(this);
		mine_sales_returnRel.setOnClickListener(this);
		mine_accountRel.setOnClickListener(this);
		mine_welfareRel.setOnClickListener(this);
		mine_cooperationRel.setOnClickListener(this);
		mine_recommendRel.setOnClickListener(this);
		mine_shareRel.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (Tool.isFastDoubleClick()) {
			return;
		} else {
			Intent intent = null;
			if (MySharePreferences.GetUser_ID(getActivity()).equals("")) {
				intent = new Intent(getActivity(), LoginActivity.class);
			} else {
				switch (v.getId()) {
				case R.id.mine_underwayLin:
					// 跳转到正在进行
					intent = new Intent(getActivity(), UnderwayActivity.class);
					break;
				case R.id.mine_announcedLin:
					// 跳转到已经揭晓
					intent = new Intent(getActivity(), AnnounceActivity.class);
					break;
				case R.id.mine_lucky_recordLin:
					// 跳转到幸运记录
					intent = new Intent(getActivity(),Lucky_RecordActivity.class);
					break;
				case R.id.mine_trading_recordLin:
					// 跳转到交易记录
					intent = new Intent(getActivity(),Deal_RecordActivity.class);
					break;
				case R.id.mine_news_numRel:
					// 跳转到消息界面
					intent = new Intent(getActivity(), Mine_NewsActivity.class);
					break;
				case R.id.mine_settingRel:
					// 跳转到设置界面
					intent = new Intent(getActivity(),
							Mine_SettingActivity.class);
					break;
				case R.id.mine_rechargeBtn:
					// 跳转到充值界面
					intent = new Intent(getActivity(), RechargeActivity.class);
					break;
				case R.id.mine_releaseLin:
					// 跳转到我的发布界面
					intent = new Intent(getActivity(), My_ReleaseActivity.class);
					break;
				case R.id.mine_saleLin:
					// 跳转到我的卖出界面
					intent = new Intent(getActivity(), My_SaleActivity.class);
					break;
				case R.id.mine_attentionLin:
					// 跳转到我的关注界面
					intent = new Intent(getActivity(),
							My_AttentionActivity.class);
					break;
				case R.id.mine_orderRel:
					// 跳转到我的订单界面
					intent = new Intent(getActivity(), My_OrderActivity.class);
					break;
				case R.id.mine_red_packetRel:
					// 跳转到我的红包界面
					intent = new Intent(getActivity(),
							My_RedpacketActivity.class);
					break;
				case R.id.mine_sales_returnRel:
					// 跳转到退货界面
					intent = new Intent(getActivity(), After_SaleActivity.class);
					break;
				case R.id.mine_accountRel:
					// 跳转到我的账户界面
					intent = new Intent(getActivity(), AccountActivity.class);
					break;
				case R.id.mine_welfareRel:
					// 跳转到我的福利界面
					intent = new Intent(getActivity(), WelfareActivity.class);
					break;
				case R.id.mine_cooperationRel:
					// 跳转到合作界面
					intent = new Intent(getActivity(),
							CooperationActivity.class);
					break;
				case R.id.mine_recommendRel:
					// 跳转到我的推荐界面
					intent = new Intent(getActivity(), RecommActivity.class);
					break;
				case R.id.mine_shareRel:
					// 跳转到我的分享界面
					intent = new Intent(getActivity(), ShareActivity.class);
					break;
				case R.id.mine_head_portraitBtn:
					// 跳转到个人中心
					intent = new Intent(getActivity(),
							Personal_CenterActvity.class);
					break;
				default:
					break;
				}
			}
			if (intent != null) {
				startActivity(intent);
			}
		}
	}

	/**
	 * 获取用户头像等消息
	 * */
	private class PersonalInformationTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			String string = OKHTTP_POST.doPost1(MyApp.base_address
					+ "usersaction/getusersinfo.do", "user_id",
					MySharePreferences.GetUser_ID(getActivity()));
			if (string != null) {
				try {
					JSONObject jsonObject = new JSONObject(string);
					String string2 = jsonObject.getString("1");
					if (string2.equals("程序异常")) {
						handler.sendMessage(handler.obtainMessage(0,
								MyApp.Program_Exception_Prompt));
					} else {
						Gson gson = new Gson();
						json_list = gson.fromJson(string2,
								new TypeToken<List<MineEntity>>() {
								}.getType());
						for (int i = 0; i < json_list.size(); i++) {
							user_pictrue = json_list.get(i).getUser_pictrue();
							user_name = json_list.get(i).getUser_name();
							balance = json_list.get(i).getBalance();
							user_sex = json_list.get(i).getUser_sex();
							num = json_list.get(i).getNum();
						}
						handler.sendEmptyMessageDelayed(1, 10);
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				handler.sendMessage(handler.obtainMessage(0,
						MyApp.Program_Exception_Prompt));
			}
			return null;
		}
	}

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:
				String string = (String) msg.obj;
				MyToast.Toast(getActivity(), string);
				break;
			case 1:
				ImageloaderUtils.MyImageLoaderCircleImageView(
						MyApp.base_address + user_pictrue,
						mine_head_portraitBtn, getActivity());
				mine_user_nameText.setText(user_name);
				mine_wowotounumText.setText("窝窝头：" + balance + "个");
				if (user_sex.equals("0")) {
					mine_sexBtn.setVisibility(View.GONE);
				} else if (user_sex.equals("1")) {
					mine_sexBtn.setVisibility(View.VISIBLE);
					mine_sexBtn
							.setBackgroundResource(R.drawable.mine_icn_sex_nv);
				} else if (user_sex.equals("2")) {
					mine_sexBtn.setVisibility(View.VISIBLE);
					mine_sexBtn
							.setBackgroundResource(R.drawable.mine_icn_sex_nan);
				}
				if (num.equals("0")) {
					mine_news_numBtn.setVisibility(View.GONE);
				} else {
					mine_news_numBtn.setVisibility(View.VISIBLE);
					mine_news_numBtn.setText(num);
				}
				break;

			default:
				break;
			}
		};
	};
}
