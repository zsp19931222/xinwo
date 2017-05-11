package com.quwu.xinwo.mine;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.quwu.xinwo.R;
import com.quwu.xinwo.bean.AccountBean;
import com.quwu.xinwo.global.MyApp;
import com.quwu.xinwo.log_in.LoginActivity;
import com.quwu.xinwo.mywight.MyToast;
import com.quwu.xinwo.okhttp.OKHTTP_POST;
import com.quwu.xinwo.roll.CoverFlow;
import com.quwu.xinwo.roll.CoverFlowImageAdapter;
import com.quwu.xinwo.until.FinishActivity;
import com.quwu.xinwo.until.MySharePreferences;
import com.quwu.xinwo.until.Tool;

public class AccountActivity extends Activity implements OnClickListener {

	private CoverFlow coverFlow;

	private Button account_managementBtn;
	private Button account_withdrawBtn;

	private Button accounts_addBtn;

	private TextView account_balanceText1;
	private TextView account_add_up_priceText;
	private TextView account_recharge_priceText;
	private TextView account_exchange_priceText;
	private TextView account_pending_confirmationText;
	private TextView account_withdrawals_middleText;
	private TextView account_already_mentionedText;

	private TextView accounts_bankcardText;
	private TextView accounts_banknumText;
	private TextView accounts_bankText;

	private String account_balance;// 账户余额
	private String cash_balance;// 可提现金额
	private String accumulated_income;// 累计收入
	private String cumulative_recharge;// 累计充值
	private String integral_balance;// 兑换的累计积分
	private String determined;// 待确定
	private String middle;// 提现中
	private String already_mentioned;// 已提现
	private String alipay;// 支付宝

	private String transaction_num;// 账号
	private String account_name;// 银行名称
	private String bank_card;// 卡种
	private String transaction_id;// 收款信息ID

	private List<AccountBean> list_bank;// 装银行卡信息
	private List<AccountBean> list_ailpay;// 装银行卡信息
	private CoverFlowImageAdapter imageAdapter;

	private Intent intent1 = null;

	private int card_position = 0;// 第几张银行卡

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.account);
		findID();
		FinishActivity.finish(R.id.account_returnBtn, AccountActivity.this);
		new LoadTask().executeOnExecutor(Executors.newCachedThreadPool());
	}

	private void findID() {
		FinishActivity.finish(R.id.account_bankRel, AccountActivity.this);
		coverFlow = (CoverFlow) findViewById(R.id.account_CoverFlow);
		account_managementBtn = (Button) findViewById(R.id.account_managementBtn);
		account_withdrawBtn = (Button) findViewById(R.id.account_withdrawBtn);
		accounts_addBtn = (Button) findViewById(R.id.accounts_addBtn);
		accounts_addBtn.setOnClickListener(this);
		account_managementBtn.setOnClickListener(this);
		account_withdrawBtn.setOnClickListener(this);

		account_balanceText1 = (TextView) findViewById(R.id.account_balanceText1);
		account_add_up_priceText = (TextView) findViewById(R.id.account_add_up_priceText);
		account_recharge_priceText = (TextView) findViewById(R.id.account_recharge_priceText);
		account_exchange_priceText = (TextView) findViewById(R.id.account_exchange_priceText);
		account_pending_confirmationText = (TextView) findViewById(R.id.account_pending_confirmationText);
		account_withdrawals_middleText = (TextView) findViewById(R.id.account_withdrawals_middleText);
		account_already_mentionedText = (TextView) findViewById(R.id.account_already_mentionedText);
		accounts_bankcardText = (TextView) findViewById(R.id.accounts_bankcardText);
		accounts_banknumText = (TextView) findViewById(R.id.accounts_banknumText);
		accounts_bankText = (TextView) findViewById(R.id.accounts_bankText);

		// WindowManager wm = this.getWindowManager();
		//
		// @SuppressWarnings("deprecation")
		// int width = wm.getDefaultDisplay().getWidth();
		// @SuppressWarnings("deprecation")
		// int height = wm.getDefaultDisplay().getHeight();
		// RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
		// RelativeLayout.LayoutParams.WRAP_CONTENT,
		// RelativeLayout.LayoutParams.WRAP_CONTENT);
		// lp.setMargins((int) (width / 2), height / 16, 0, 0);
		//
		// accounts_bankcardText.setLayoutParams(lp);
		// RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams(
		// RelativeLayout.LayoutParams.WRAP_CONTENT,
		// RelativeLayout.LayoutParams.WRAP_CONTENT);
		// lp1.setMargins(width / 6, height / 10, 0, 0);
		// accounts_banknumText.setLayoutParams(lp1);
		// RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams(
		// RelativeLayout.LayoutParams.WRAP_CONTENT,
		// RelativeLayout.LayoutParams.WRAP_CONTENT);
		// lp2.setMargins((int) (width / 3.9), (int) (height / 4.5), 0, 0);
		// accounts_bankText.setLayoutParams(lp2);
	}

	@Override
	public void onClick(View v) {
		if (Tool.isFastDoubleClick()) {
			return;
		} else {
			Intent intent = null;
			if (MySharePreferences.GetUser_ID(AccountActivity.this).equals("")) {
				intent = new Intent(AccountActivity.this, LoginActivity.class);
			} else {
				switch (v.getId()) {
				case R.id.account_managementBtn:
					intent = new Intent(AccountActivity.this,
							Account_ManagementActivity.class);
					intent.putExtra("state", "0");
					break;
				case R.id.account_withdrawBtn:
					if (list_bank.size() != 0) {// 有银行卡的情况下
						intent1 = new Intent(AccountActivity.this,
								WithdrawActivity.class);
						if (imageAdapter != null) {
							System.out.println("进来了");
							System.out.println(card_position + "=position");
							intent1.putExtra("account_name",
									list_bank.get(card_position)
											.getAccount_name());
							intent1.putExtra("transaction_num",
									list_bank.get(card_position)
											.getTransaction_num());
							intent1.putExtra("transaction_id",
									list_bank.get(card_position)
											.getTransaction_id());
							intent1.putExtra("money", cash_balance);
						}
					} else if (list_ailpay.size() != 0) {// 没有银行卡的情况下有支付宝
						intent1 = new Intent(AccountActivity.this,
								WithdrawActivity.class);
						intent1.putExtra("account_name", list_ailpay.get(0)
								.getAccount_name());
						intent1.putExtra("transaction_num", list_ailpay.get(0)
								.getTransaction_num());
						intent1.putExtra("transaction_id", list_ailpay.get(0)
								.getTransaction_id());
						intent1.putExtra("money", cash_balance);
					} else {// 两者都无跳转添加账户页面
						intent1 = new Intent(AccountActivity.this,
								Add_AccountActivity.class);
					}
					if (intent1 != null) {
						startActivity(intent1);
					}
					break;
				case R.id.accounts_addBtn:
					intent = new Intent(AccountActivity.this,
							Add_AccountActivity.class);
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
	 * 获取数据
	 * */
	private class LoadTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			list_bank = new ArrayList<AccountBean>();
			list_ailpay = new ArrayList<AccountBean>();
			String string = OKHTTP_POST.doPost1(MyApp.base_address
					+ "myAccount/selectAccountAll.do", "user_id",
					MySharePreferences.GetUser_ID(AccountActivity.this));
			if (string != null) {
				try {
					JSONObject jsonObject = new JSONObject(string);
					String string2 = jsonObject.getString("1");
					if (string2.equals("程序异常")) {
						handler.sendMessage(handler.obtainMessage(0,
								MyApp.Program_Exception_Prompt));
					} else if (string2.equals("该用户还没有绑定账户")) {
						handler.sendMessage(handler.obtainMessage(1,
								"您还没有绑定账户哦~请先去添加账户吧。"));
					} else {
						JSONArray array = new JSONArray(string2);
						JSONObject jsonObject2 = array.getJSONObject(0);
						account_balance = jsonObject2
								.getString("account_balance");
						cash_balance = jsonObject2.getString("cash_balance");
						accumulated_income = jsonObject2
								.getString("accumulated_income");
						cumulative_recharge = jsonObject2
								.getString("cumulative_recharge");
						integral_balance = jsonObject2
								.getString("integral_balance");
						determined = jsonObject2.getString("determined");
						middle = jsonObject2.getString("middle");
						already_mentioned = jsonObject2
								.getString("already_mentioned");
						alipay = jsonObject2.getString("alipay");
						if (alipay.equals("没有支付宝")) {
							// list_ailpay.add(new AccountBean("没有支付宝", "没有支付宝",
							// "没有支付宝"));
						} else {
							JSONArray array3 = new JSONArray(alipay);
							for (int i = 0; i < array3.length(); i++) {
								JSONObject jsonObject3 = array3
										.getJSONObject(i);
								transaction_num = jsonObject3
										.getString("transaction_num");
								account_name = jsonObject3
										.getString("account_name");
								transaction_id = jsonObject3
										.getString("transaction_id");
								list_ailpay.add(new AccountBean(
										transaction_num, account_name,
										transaction_id));
							}
						}
						JSONArray array2 = new JSONArray(
								jsonObject2.getString("bank"));

						for (int i = 0; i < array2.length(); i++) {
							JSONObject jsonObject3 = array2.getJSONObject(i);
							transaction_num = jsonObject3
									.getString("transaction_num");
							account_name = jsonObject3
									.getString("account_name");
							bank_card = jsonObject3.getString("bank_card");
							transaction_id = jsonObject3
									.getString("transaction_id");
							list_bank.add(new AccountBean(transaction_num,
									account_name, bank_card, transaction_id));
						}

					}
					handler.sendEmptyMessageDelayed(2, 10);
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
		@SuppressWarnings("deprecation")
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:
				String string = (String) msg.obj;
				MyToast.Toast(AccountActivity.this, string);
				coverFlow.setVisibility(View.INVISIBLE);
				accounts_addBtn.setVisibility(View.VISIBLE);
				break;
			case 1:
				coverFlow.setVisibility(View.INVISIBLE);
				accounts_addBtn.setVisibility(View.VISIBLE);
				break;
			case 2:
				account_balanceText1.setText(account_balance + "（可提现："
						+ cash_balance + "）");
				account_add_up_priceText.setText(accumulated_income + "");
				account_recharge_priceText.setText(cumulative_recharge + "");
				account_exchange_priceText.setText(integral_balance + "积分");
				account_pending_confirmationText.setText(determined + "");
				account_withdrawals_middleText.setText(middle + "");
				account_already_mentionedText.setText(already_mentioned + "");
				int j = list_bank.size();
				if (j == 0) {
					coverFlow.setVisibility(View.INVISIBLE);
					accounts_addBtn.setVisibility(View.VISIBLE);
				} else {
					coverFlow.setVisibility(View.VISIBLE);
					accounts_addBtn.setVisibility(View.GONE);
					final Integer[] mImageIds = new Integer[j];
					for (int i = 0; i < j; i++) {
						if (i % 3 == 0) {
							mImageIds[i] = R.drawable.card3;
						} else if (i % 2 == 0) {
							mImageIds[i] = R.drawable.card2;
						} else {
							mImageIds[i] = R.drawable.card1;
						}
					}
					final Class<?>[] target = {};
					imageAdapter = new CoverFlowImageAdapter(
							AccountActivity.this, mImageIds, target,
							AccountActivity.this, list_bank);
					// imageAdapter
					// .setCoverFlowImageAdapterClickListener(new
					// CoverFlowImageAdapterClickListener() {
					// public void send(int position) {
					// System.out.println(position+"=position");
					// card_position=position;
					// }
					// });
					coverFlow
							.setOnItemSelectedListener(new OnItemSelectedListener() {

								@Override
								public void onItemSelected(
										AdapterView<?> parent, View view,
										int position, long id) {
									card_position = position;
								}

								@Override
								public void onNothingSelected(
										AdapterView<?> parent) {

								}
							});
					coverFlow.setAdapter(imageAdapter);
					coverFlow.setSelection(0, true);
					coverFlow.setAnimationDuration(1000);
					// accounts_bankcardText.setText(account_name);
					// accounts_banknumText.setText(transaction_num);
					// accounts_bankText.setText(bank_card);
				}
				break;

			default:
				break;
			}
		};
	};
}
