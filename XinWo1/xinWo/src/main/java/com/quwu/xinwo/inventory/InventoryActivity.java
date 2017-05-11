package com.quwu.xinwo.inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import me.maxwin.view.XListView;
import me.maxwin.view.XListView.IXListViewListener;
import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.quwu.xinwo.R;
import com.quwu.xinwo.mine.ShareActivity;
import com.quwu.xinwo.mywight.MyAlertDialog;
import com.quwu.xinwo.okhttp.OKHTTP_POST;
import com.quwu.xinwo.webpage.WebPage;

/**
 * 
 * 清单界面
 * 
 * @author ZhouShaoPeng
 * 
 * */
public class InventoryActivity extends Fragment implements OnClickListener {

	private XListView listView;
	private Inventory_Adapter adapter;
	private List<Inventory_Bean> list;

	private TextView titleText;
	private CheckBox checkBox;
	private LinearLayout settleLin;
	private LinearLayout shareLin;
	private TextView goodsNumText;
	private TextView priceNumText;
	private Button shareBtn;
	private Button attentionBtn;
	private Button deleteBtn;
	
	private Button inventoty_settleBtn;//结算按钮
	private WebView webView;

	private int i = 0;// 判断用户点击次数取余（0—编辑，1—完成）

	private View view;
	private boolean flag = true; // 全选或全取消

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.inventory, null);
		findID();
		isListView();
		return view;
	}

	private void findID() {
		inventoty_settleBtn=(Button) view.findViewById(R.id.inventoty_settleBtn);
		inventoty_settleBtn.setOnClickListener(this);
		webView=(WebView) view.findViewById(R.id.inventoty_weibo);
		
		listView = (XListView) view.findViewById(R.id.inventory_listview);

		titleText = (TextView) view.findViewById(R.id.inventory_titleText);
		goodsNumText = (TextView) view
				.findViewById(R.id.inventory_goodsNumText);
		priceNumText = (TextView) view
				.findViewById(R.id.inventory_allPriceText);

		checkBox = (CheckBox) view.findViewById(R.id.inventory_checkbox);
		ClickListener cl = new ClickListener();
		checkBox.setOnClickListener(cl);

		settleLin = (LinearLayout) view.findViewById(R.id.inventory_settleLin);
		shareLin = (LinearLayout) view.findViewById(R.id.inventory_shareLin);

		shareBtn = (Button) view.findViewById(R.id.inventory_shareBtn);
		attentionBtn = (Button) view.findViewById(R.id.inventory_attentionBtn);
		deleteBtn = (Button) view.findViewById(R.id.inventory_deleteBtn);

		titleText.setOnClickListener(this);
		shareBtn.setOnClickListener(this);
		attentionBtn.setOnClickListener(this);
		deleteBtn.setOnClickListener(this);
	}

	private void isListView() {
		list = new ArrayList<Inventory_Bean>();
		for (int i = 0; i < 10; i++) {
			list.add(new Inventory_Bean("1",
					"http://pic1.nipic.com/2008-12-25/2008122510134038_2.jpg",
					"总需100人次，剩余", "2", "商品描述", false, false, 1, "0", "0", "0",
					"0", "0", "0"));
		}
		adapter = new Inventory_Adapter(getActivity(), list, handler,
				R.layout.inventory_item, InventoryActivity.this);
		listView.setAdapter(adapter);
		listView.setPullRefreshEnable(true);// 刷新
		// 设置回调函数
		listView.setXListViewListener(new IXListViewListener() {
			public void onRefresh() {
				handler.postDelayed(new Runnable() {
					@Override
					public void run() {
						checkBox.setChecked(false);
						priceNumText.setText("0");
						goodsNumText.setText("0");
						listView.stopRefresh();
					}
				}, 0);
			}

			public void onLoadMore() {
				listView.stopLoadMore();
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.inventory_titleText:// 编辑按钮
			i++;
			if (i % 2 == 0) {
				titleText.setText("编辑");
				settleLin.setVisibility(View.VISIBLE);
				shareLin.setVisibility(View.GONE);
				goodsNumText.setVisibility(View.VISIBLE);
			} else {
				titleText.setText("完成");
				settleLin.setVisibility(View.GONE);
				shareLin.setVisibility(View.VISIBLE);
				goodsNumText.setVisibility(View.GONE);
			}
			break;
		case R.id.inventory_shareBtn:// 分享按钮
			Intent intent = new Intent(getActivity(), ShareActivity.class);
			getActivity().startActivity(intent);
			break;
		case R.id.inventory_attentionBtn:// 关注按钮
			String shopIndex1 = deleteOrCheckOutShop();
			if (shopIndex1.equals("")) {
				 Toast.makeText(getActivity(), "请选择一个或多个移入对象",
				 Toast.LENGTH_SHORT).show();
			} else {
			showAttention(shopIndex1);
			}
			break;
		case R.id.inventory_deleteBtn:// 删除按钮
			String shopIndex = deleteOrCheckOutShop();
			if (shopIndex.equals("")) {
				 Toast.makeText(getActivity(), "请选择一个或多个删除对象",
				 Toast.LENGTH_SHORT).show();
			} else {
				showDialogDelete(shopIndex);
				// }
			}
			break;
		case R.id.inventoty_settleBtn:
			new Taske().executeOnExecutor(Executors.newCachedThreadPool());
			break;

		default:
			break;
		}
	}

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (msg.what == 10) { // 更改选中商品的总价格
				int price = (Integer) msg.obj;
				if (price > 0) {
					priceNumText.setText("" + price);
				} else {
					priceNumText.setText("" + "0");
				}
			} else if (msg.what == 11) {
				// 所有列表中的商品全部被选中，让全选按钮也被选中
				// flag记录是否全被选中
				flag = !(Boolean) msg.obj;
				checkBox.setChecked((Boolean) msg.obj);
			} else if (msg.what == 12) {

			} else if (msg.what == 13) {
				int num = (Integer) msg.obj;
				if (num > 0) {
					goodsNumText.setText("共" + num + "件商品");
				} else {
					goodsNumText.setText("共" + 0 + "件商品");
				}
			} else if (msg.what == 15) {
				Toast.makeText(getActivity(), "网络异常", Toast.LENGTH_SHORT)
						.show();
			} else if (msg.what == 16) {
				// Gson gson = new Gson();
				// List<Snatch_Entity> ps = gson.fromJson(result,
				// new TypeToken<List<Snatch_Entity>>() {
				// }.getType());
				// if (result.equals("[]")) {
				// jiesuanLinear.clearAnimation();
				// jiesuanLinear.setVisibility(View.GONE);
				// listView.setVisibility(View.GONE);
				// lijicanyuLinear.setVisibility(View.VISIBLE);
				// lijicanyuBtn.setOnClickListener(new OnClickListener() {
				// @Override
				// public void onClick(View v) {
				// MyTabHostActivity tabHost = (MyTabHostActivity) getParent();
				// Message msg = new Message();
				// msg.what = 1;
				// // 给TabHost发送消息
				// tabHost.searchHandler.sendMessage(msg);
				// }
				// });
				// } else {
				// jiesuanLinear.setVisibility(View.VISIBLE);
				// listView.setVisibility(View.VISIBLE);
				// lijicanyuLinear.setVisibility(View.GONE);
				// }
			} else if (msg.what == 17) {
				// ShoppingCanst.list = list;
				adapter = new Inventory_Adapter(getActivity(), list, handler,
						R.layout.inventory_item, InventoryActivity.this);
				// adapter.notifyDataSetChanged();
				listView.setAdapter(adapter);
			}
		}
	};

	// 事件点击监听器
	private final class ClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.inventory_checkbox: // 全选
				if (checkBox.isChecked() == true) {
					goodsNumText.setText(String.valueOf(list.size()));
				} else {
					goodsNumText.setText("0");
				}
				selectedAll();
				break;
			// case R.id.delete: //删除
			// String shopIndex = deleteOrCheckOutShop();
			// showDialogDelete(shopIndex);
			// break;
			// case R.id.inventory_zhonggongText: //结算
			// goCheckOut();
			// break;
			// }
			}
		}
	}

	// 全选或全取消
	private void selectedAll() {
		for (int i = 0; i < list.size(); i++) {
			System.out.println("执行全选");
			System.out.println("flag=" + flag);
			Inventory_Adapter.getIsSelected().put(i, flag);
			// ShoppingCanst.list.get(i).setChoosed(flag);
			System.out.println("Inventory_Adapter.getIsSelected()="
					+ Inventory_Adapter.getIsSelected().get(i));
		}
		if (adapter != null) {
			adapter.notifyDataSetChanged();
		}
	}

	// 删除或结算商品
	public String deleteOrCheckOutShop() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			if (Inventory_Adapter.getIsSelected().get(i)) {
				sb.append(i);
				sb.append(",");
			}
		}
		if (sb.length() != 0) {
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();
	}

	// 弹出对话框询问用户是否删除被选中的商品
	public int showDialogDelete(String str) {
		final String[] delShopIndex = str.split(",");
		final MyAlertDialog alertDialog = new MyAlertDialog(getActivity());
		alertDialog.setTitle("删除商品");
		alertDialog.setMessage("是否将" + delShopIndex.length + "件商品从清单中移除");
		alertDialog.setPositiveButton("确定", new OnClickListener() {

			@Override
			public void onClick(View v) {
				StringBuffer buffer = new StringBuffer();
				for (int i = 0; i < delShopIndex.length; i++) {
					String strings = delShopIndex[delShopIndex.length - 1 - i];
					int index = Integer.valueOf(strings);
					String string = list.get(index).getOrder_id();
					buffer.append(string + ",");
					list.remove(index);
					if (ShoppingCanst.list == null) {
						ShoppingCanst.list = new ArrayList<Inventory_Bean>();
					}
					ShoppingCanst.list = list;
				}
				String string2 = buffer.toString();
				// new DeleteTask().execute(string2);
				flag = false;
				selectedAll(); // 删除商品后，取消所有的选择
				flag = true; // 刷新页面后，设置Flag为true，恢复全选功能
				checkBox.setChecked(true);
				goodsNumText.setText("0");
				priceNumText.setText("0");
				alertDialog.dismiss();
			}
		});
		alertDialog.setNegativeButton("取消", new OnClickListener() {

			public void onClick(View v) {
				alertDialog.dismiss();
			}
		});
		// new AlertDialog.Builder(getActivity())
		// .setView(LayoutInflater.from(getActivity()).inflate(R.layout.wipe_cache,
		// null))
		// .setMessage("您确定删除这" + delShopIndex.length + "商品吗？")
		// .setPositiveButton("确定", new DialogInterface.OnClickListener() {
		// public void onClick(DialogInterface arg0, int arg1) {
		// StringBuffer buffer = new StringBuffer();
		// for (int i = 0; i < delShopIndex.length; i++) {
		// String strings = delShopIndex[delShopIndex.length
		// - 1 - i];
		// int index = Integer.valueOf(strings);
		// String string = list.get(index).getOrder_id();
		// buffer.append(string + ",");
		// list.remove(index);
		// if (ShoppingCanst.list == null) {
		// ShoppingCanst.list = new ArrayList<Inventory_Bean>();
		// }
		// ShoppingCanst.list = list;
		// }
		// String string2 = buffer.toString();
		// // new DeleteTask().execute(string2);
		// flag = false;
		// selectedAll(); // 删除商品后，取消所有的选择
		// flag = true; // 刷新页面后，设置Flag为true，恢复全选功能
		// checkBox.setChecked(true);
		// goodsNumText.setText("0");
		// priceNumText.setText("0");
		//
		// }
		// }).setNegativeButton("取消", null).create().show();
		return delShopIndex.length;
	}
private void showAttention(String str) {
	final String[] delShopIndex = str.split(",");
	final MyAlertDialog alertDialog = new MyAlertDialog(getActivity());
	alertDialog.setTitle("移入关注");
	alertDialog.setMessage("是否将" + delShopIndex.length + "件商品移入关注？");
	alertDialog.setPositiveButton("确定", new OnClickListener() {

		@Override
		public void onClick(View v) {
			Toast.makeText(getActivity(), "移入成功", 10).show();
		}
	});
	alertDialog.setNegativeButton("取消", new OnClickListener() {

		public void onClick(View v) {
			alertDialog.dismiss();
		}
	});
}
private class Taske extends AsyncTask<Void, Void, Void>{
	String string;
	@Override
	protected Void doInBackground(Void... params) {
		 string=OKHTTP_POST.doPost1("http://5iquwu.6655.la/Indiana/cporder_zzfwap", "", "");
		 System.out.println("string+++++="+string);
		return null;
	}
	@Override
	protected void onPostExecute(Void result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		webView.loadUrl(string);
	}
}
}