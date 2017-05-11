package com.quwu.xinwo.inventory;

import java.util.HashMap;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.BaseSwipeAdapter;
import com.quwu.xinwo.R;
import com.quwu.xinwo.imageloader.ImageloaderUtils;
import com.quwu.xinwo.until.MyInputMethodManager;

public class Inventory_Adapter extends BaseSwipeAdapter {

	private Handler mHandler;
	private int resourceId; // 适配器视图资源ID
	private Context context; // 上下午对象
	private List<Inventory_Bean> list; // 数据集合List
	private LayoutInflater inflater; // 布局填充器
	private static HashMap<Integer, Boolean> isSelected;// 存第一个checkbox的状态的
	private static HashMap<Integer, Boolean> isSelected2;// 存第二个checkbox的状态的
	private static HashMap<Integer, String> isSelected3;
	private static HashMap<Integer, Integer> isSelected4;// 存点击item的加减号所获得的下标
	private static HashMap<Integer, Integer> isSelected5;// 存点击item的加减号所获得的下标

	// 定义一个HashMap，用来存放EditText的值，Key是position
	HashMap<Integer, String> hashMap = new HashMap<Integer, String>();
	ViewHolder holder = null;
	int a, b;
	private Inventory_Bean bean;

	int i = -1;


	private int z;
	private InventoryActivity activity;

	@SuppressLint("UseSparseArrays")
	public Inventory_Adapter(Context context, List<Inventory_Bean> list,
			Handler mHandler, int resourceId, InventoryActivity activity) {
		this.list = list;
		this.context = context;
		this.mHandler = mHandler;
		this.resourceId = resourceId;
		this.activity = activity;
		inflater = LayoutInflater.from(context);
		isSelected = new HashMap<Integer, Boolean>();
		isSelected2 = new HashMap<Integer, Boolean>();
		isSelected3 = new HashMap<Integer, String>();
		isSelected4 = new HashMap<Integer, Integer>();
		initDate();
	}

	// 初始化isSelected的数据
	private void initDate() {
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				getIsSelected().put(i, false);
				getIsSelected2().put(i, false);
			}
		}
	}

	public static HashMap<Integer, Boolean> getIsSelected() {
		return isSelected;
	}

	public static void setIsSelected(HashMap<Integer, Boolean> isSelected) {
		Inventory_Adapter.isSelected = isSelected;
	}

	public static HashMap<Integer, Boolean> getIsSelected2() {
		return isSelected2;
	}

	public static void setIsSelected2(HashMap<Integer, Boolean> isSelected2) {
		Inventory_Adapter.isSelected2 = isSelected2;
	}

	public static HashMap<Integer, String> getIsSelected3() {
		return isSelected3;
	}

	public static void setIsSelected3(HashMap<Integer, String> isSelected3) {
		Inventory_Adapter.isSelected3 = isSelected3;
	}

	public static HashMap<Integer, Integer> getIsSelected4() {
		return isSelected4;
	}

	public static void setIsSelected4(HashMap<Integer, Integer> isSelected4) {
		Inventory_Adapter.isSelected4 = isSelected4;
	}

	public static HashMap<Integer, Integer> getIsSelected5() {
		return isSelected4;
	}

	public static void setIsSelected5(HashMap<Integer, Integer> isSelected5) {
		Inventory_Adapter.isSelected5 = isSelected5;
	}

	@Override
	public int getCount() {
		if (list != null) {
			return list.size();
		}
		return 0;
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public int getSwipeLayoutResourceId(int position) {
		return R.id.swipe;
	}

//	private class DeleteTask extends AsyncTask<String, Void, Void> {
//		private String string2;
//		@Override
//		protected Void doInBackground(String... params) {
//			try {
//				System.out.println("params[0]=" + params[0]);
//				String string = HttpPostUnit.otherHttpPostString(URLUtils.url
//						+ "order_deleteorder", "order_id", params[0]);
//				System.out.println("string=" + string);
//				if (string != null) {
//					JSONObject jsonObject = new JSONObject(string);
//					string2 = jsonObject.getString("1");
//				} else {
//					Message message = new Message();
//					message.what = 15;
//					handler.sendMessage(message);
//				}
//			} catch (JSONException e) {
//				e.printStackTrace();
//			}
//			return null;
//		}
//
//		@Override
//		protected void onPostExecute(Void result) {
//			super.onPostExecute(result);
//			if (string2.equals("删除成功")) {
//				list.remove(z);
//				if (list.size() == 0) {
//					System.out.println("进来了1");
//					activity.Gowu();
//				}
//			mHandler.sendMessage(mHandler.obtainMessage(10, getTotalPrice()));
//			mHandler.sendMessage(mHandler.obtainMessage(13, getNum()));
//			// 如果所有的物品全部被选中，则全选按钮也默认被选中
//			mHandler.sendMessage(mHandler.obtainMessage(11, isAllSelected()));
//			mHandler.sendMessage(mHandler.obtainMessage(12, null));
//			notifyDataSetChanged();
//			MyTabHostActivity tabHost = (MyTabHostActivity) activity
//					.getParent();
//			Message msg = new Message();
//			msg.what = 4;
//			// 给TabHost发送消息
//			tabHost.searchHandler.sendMessage(msg);
//			}else {
//				Toast.makeText(context, string2, 10).show();
//			}
//		}
//	}

//	@Override
//	public View getView(final int position, View convertView, ViewGroup parent) {
//		bean = list.get(position);
//		if (convertView == null) {
//			view = convertView = inflater
//					.inflate(R.layout.inventory_item, null);
//			holder = new ViewHolder();
//
//			holder.ll_menu = (LinearLayout) view.findViewById(R.id.ll_menu);
//			holder.shop_photo = (ImageView) convertView
//					.findViewById(R.id.inventory_item_image);
//			holder.shop_zongxu = (TextView) convertView
//					.findViewById(R.id.inventory_item_totalText);
//			holder.shop_description = (TextView) convertView
//					.findViewById(R.id.inventory_item_describeText);
//			holder.shop_shengyu = (TextView) convertView
//					.findViewById(R.id.inventory_item_residueText);
//			holder.shop_check = (CheckBox) convertView
//					.findViewById(R.id.inventory_item_checkbox);
//			holder.shop_saowei = (CheckBox) convertView
//					.findViewById(R.id.inventory_item_undefinedBox);
//			jia = (Button) convertView.findViewById(R.id.inventory_item_addBtn);
//			holder.jia = (Button) convertView
//					.findViewById(R.id.inventory_item_addBtn);
//			holder.jian = (Button) convertView
//					.findViewById(R.id.inventory_item_minusBtn);
//			holder.xianshi = (EditText) convertView
//					.findViewById(R.id.inventory_item_centerNumEd);
//			// xianshi = (EditText) convertView
//			// .findViewById(R.id.inventory_item_xianshibtn);
//			holder.xianshi.setOnTouchListener(new OnTouchListener() {
//
//				@Override
//				public boolean onTouch(View v, MotionEvent event) {
//					if (event.getAction() == MotionEvent.ACTION_UP) {
//						i = (Integer) v.getTag();
//						System.out.println("i===" + i);
//						// i = position;
//					}
//					return false;
//				}
//			});
//			class MyT implements TextWatcher {
//				public MyT(ViewHolder holder) {
//					mHolder = holder;
//				}
//
//				private ViewHolder mHolder;
//
//				@Override
//				public void onTextChanged(CharSequence s, int start,
//						int before, int count) {
//					String string = holder.xianshi.getText().toString();
//					holder.xianshi.setSelection(string.length());
//					for (int i = start; i < count; i++) {
//						if (s.toString().equals("0")) {
//							return;
//						}
//					}
//				}
//
//				@Override
//				public void beforeTextChanged(CharSequence s, int start,
//						int count, int after) {
//
//				}
//
//				@Override
//				public void afterTextChanged(Editable s) {
//					System.out.println("i++++" + i);
//					int position = (Integer) mHolder.xianshi.getTag();
//					if (s.toString().equals("") != true) {
//						if (Integer.valueOf(s.toString()) > Integer
//								.valueOf(list.get(position).getShengyu())) {
//							list.get(position).setCount(
//									Integer.valueOf(list.get(position)
//											.getShengyu()));
//							notifyDataSetChanged();
//						} else {
//							if (list.get(position).getPrefecture().equals("1")) {
//								int z = Integer.valueOf(s.toString());
//								if (z / 10 != 0) {
//									z = z / 10 * 10;
//									list.get(position).setCount(z);
//								}
//							} else {
//								list.get(position).setCount(
//										Integer.valueOf(s.toString()));
//							}
//							ShoppingCanst.isConut.put(list.get(position)
//									.getStages_id(), Integer.valueOf(list.get(
//									position).getCount()));
//						}
//					} else {
//						if (list.get(i).getPrefecture().equals("0")) {
//							list.get(i).setCount(1);
//						} else if (list.get(i).getPrefecture().equals("1")) {
//							list.get(i).setCount(10);
//						}
//					}
//					mHandler.sendMessage(mHandler.obtainMessage(10,
//							getTotalPrice()));
//					// 如果所有的物品全部被选中，则全选按钮也默认被选中
//					mHandler.sendMessage(mHandler.obtainMessage(11,
//							isAllSelected()));
//					mHandler.sendMessage(mHandler.obtainMessage(12, null));
//					mHandler.sendMessage(mHandler.obtainMessage(13, getNum()));
//				}
//			}
//			holder.xianshi.addTextChangedListener(new MyT(holder) {
//			});
//
//			convertView.setTag(holder);
//		} else {
//			holder = (ViewHolder) convertView.getTag();
//		}
//		ImageloaderUtils.MyImageLoader2(bean.getShopPicture(),
//				holder.shop_photo, context);
//		holder.shop_description.setText(bean.getShopDescription());
//		holder.shop_zongxu.setText(bean.getZongxu());
//		holder.shop_shengyu.setText(bean.getShengyu());
//		holder.shop_check.setTag(position);
//		holder.ll_menu.setTag(position);
//		holder.jia.setTag(position);
//		holder.jian.setTag(position);
//		holder.xianshi.setTag(position);
//		holder.shop_saowei.setTag(position);
//		// holder.shop_check.setChecked(bean.isChoosed());
//		if (getIsSelected2().get(position) == null) {
//		} else {
//			holder.shop_saowei.setChecked(getIsSelected2().get(position));
//		}if (getIsSelected().get(position) == null) {
//		}else {
//			holder.shop_check.setChecked(getIsSelected().get(position));
//			
//		}
//		holder.shop_check
//				.setOnCheckedChangeListener(new CheckBoxChangedListener());
//		holder.shop_saowei
//				.setOnCheckedChangeListener(new CheckBoxChangedListener2());
//
//		holder.jia.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				a = (Integer) v.getTag();
//				if (list.get(a).getPrefecture().equals("0")) {
//					int count = Integer.valueOf((list.get(a).getCount()));
//					count++;
//					if (count > Integer.valueOf(list.get(a).getShengyu())) {
//						return;
//					} else {
//						list.get(a).setCount(count);
//						ShoppingCanst.isConut.put(list.get(a).getStages_id(),
//								count);
//						mHandler.sendMessage(mHandler.obtainMessage(10,
//								getTotalPrice()));
//						// 如果所有的物品全部被选中，则全选按钮也默认被选中
//						mHandler.sendMessage(mHandler.obtainMessage(11,
//								isAllSelected()));
//						mHandler.sendMessage(mHandler.obtainMessage(12, null));
//						notifyDataSetChanged();
//					}
//				} else if (list.get(a).getPrefecture().equals("1")) {
//					int count = Integer.valueOf((list.get(a).getCount()));
//					count += 10;
//					if (count > Integer.valueOf(list.get(a).getShengyu())) {
//						return;
//					} else {
//						list.get(a).setCount(count);
//						ShoppingCanst.isConut.put(list.get(a).getStages_id(),
//								count);
//						mHandler.sendMessage(mHandler.obtainMessage(10,
//								getTotalPrice()));
//						// 如果所有的物品全部被选中，则全选按钮也默认被选中
//						mHandler.sendMessage(mHandler.obtainMessage(11,
//								isAllSelected()));
//						mHandler.sendMessage(mHandler.obtainMessage(12, null));
//						notifyDataSetChanged();
//					}
//				}
//			}
//		});
//		holder.jian.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				a = (Integer) v.getTag();
//				if (list.get(a).getPrefecture().equals("0")) {
//					int count = Integer.valueOf((list.get(a).getCount()));
//					if (count == 1) {
//						return;
//					} else {
//						count--;
//						list.get(a).setCount(count);
//						ShoppingCanst.isConut.put(list.get(a).getStages_id(),
//								count);
//						mHandler.sendMessage(mHandler.obtainMessage(10,
//								getTotalPrice()));
//						// 如果所有的物品全部被选中，则全选按钮也默认被选中
//						mHandler.sendMessage(mHandler.obtainMessage(11,
//								isAllSelected()));
//						mHandler.sendMessage(mHandler.obtainMessage(12, null));
//						notifyDataSetChanged();
//					}
//				} else if (list.get(a).getPrefecture().equals("1")) {
//					int count = Integer.valueOf((list.get(a).getCount()));
//					if (count == 10) {
//						return;
//					} else {
//						count -= 10;
//						list.get(a).setCount(count);
//						ShoppingCanst.isConut.put(list.get(a).getStages_id(),
//								count);
//						mHandler.sendMessage(mHandler.obtainMessage(10,
//								getTotalPrice()));
//						// 如果所有的物品全部被选中，则全选按钮也默认被选中
//						mHandler.sendMessage(mHandler.obtainMessage(11,
//								isAllSelected()));
//						mHandler.sendMessage(mHandler.obtainMessage(12, null));
//						notifyDataSetChanged();
//					}
//				}
//			}
//		});
//
//		holder.xianshi.setText(list.get(position).getCount() + "");
////		if (bean.isSaowe() == true) {
////			// bean.setCount(Integer.valueOf(bean.getShengyu()));
////			holder.xianshi.setText(bean.getCount());
////		} else {
////			// holder.xianshi.setText("1");
////		}
//		// 滑动删除
//		final SwipeLayout swipeLayout = (SwipeLayout) view
//				.findViewById(getSwipeLayoutResourceId(position));
//
//		holder.ll_menu.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View arg0) {
//				String string = list.get((Integer) arg0.getTag()).getOrder_id();
////				new DeleteTask().execute(string);
//				z = position;
//				// 点击完成之后，关闭删除menu
//				swipeLayout.close();
//			}
//		});
//		
//		convertView.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				MyInputMethodManager.MyInputMethodManager1(view, context);
//			}
//		});
//		holder.xianshi.clearFocus();
//		if (i != -1 && i == position) {
//			holder.xianshi.requestFocus();
//		}
//		return convertView;
//	}

	public final class ViewHolder {
		public ImageView shop_photo; // 商品图片
		public TextView shop_zongxu; // 商品总需
		public TextView shop_description; // 商品描述
		public TextView shop_shengyu; // 商品剩余
		public CheckBox shop_check; // 商品选择按钮
		public CheckBox shop_saowei; // 扫尾选择按钮
		private Button jia, jian;
		private EditText xianshi;
		private Button delete;
		private LinearLayout ll_menu;
	}

	private int number = 0; // 记录对话框中的数量
	// private TextView xianshi;
	Button jia, jian;

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (msg.what == 1) { // 更改商品数量
				((TextView) msg.obj).setText(String.valueOf(number));
				// 更改商品数量后，通知Activity更新需要付费的总金额
				mHandler.sendMessage(mHandler
						.obtainMessage(10, getTotalPrice()));
			} else if (msg.what == 2) {// 更改对话框中的数量
				// String b = getIsSelected3().get(a);

			}
		}
	};

	// CheckBox选择改变监听器
	private final class CheckBoxChangedListener implements
			OnCheckedChangeListener {
		@Override
		public void onCheckedChanged(CompoundButton cb, boolean flag) {
			int position = (Integer) cb.getTag();
			getIsSelected().put(position, flag);
			Inventory_Bean bean = list.get(position);
			bean.setChoosed(flag);
			bean.setShopNumber(1);
			mHandler.sendMessage(mHandler.obtainMessage(10, getTotalPrice()));
			mHandler.sendMessage(mHandler.obtainMessage(13, getNum()));
			// 如果所有的物品全部被选中，则全选按钮也默认被选中
			mHandler.sendMessage(mHandler.obtainMessage(11, isAllSelected()));
			mHandler.sendMessage(mHandler.obtainMessage(12, null));
		
		}
	}

	// 扫尾CheckBox选择改变监听器
	private final class CheckBoxChangedListener2 implements
			OnCheckedChangeListener {
		@Override
		public void onCheckedChanged(CompoundButton cb, boolean flag) {
			int position = (Integer) cb.getTag();
			System.out.println("扫尾下标="+position);
			holder.shop_shengyu.setTag(position);
			getIsSelected2().put(position, flag);
			Inventory_Bean bean = list.get(position);
			bean.setSaowe(flag);
			bean.setCount(Integer.valueOf(bean.getShengyu()));
//			getIsSelected().put(position, flag);
//			bean.setChoosed(flag);
//			bean.setShopNumber(1);
			if (flag == false) {
				if (bean.getPrefecture().equals("0")) {
					bean.setCount(1);
				} else if (bean.getPrefecture().equals("1")) {
					bean.setCount(10);
				}
//				mHandler.sendMessage(mHandler
//						.obtainMessage(10, getTotalPrice()));
//				// 如果所有的物品全部被选中，则全选按钮也默认被选中
//				mHandler.sendMessage(mHandler
//						.obtainMessage(11, isAllSelected()));
//				mHandler.sendMessage(mHandler.obtainMessage(12, null));
			}
			mHandler.sendMessage(mHandler.obtainMessage(10, getTotalPrice()));
			// 如果所有的物品全部被选中，则全选按钮也默认被选中
			mHandler.sendMessage(mHandler.obtainMessage(11, isAllSelected()));
			mHandler.sendMessage(mHandler.obtainMessage(12, null));
			notifyDataSetChanged();
		}
	}

	/**
	 * 计算选中商品的金额
	 * 
	 * @return 返回需要付费的总金额
	 */
	private int getTotalPrice() {
		Inventory_Bean bean = null;
		int totalPrice = 0;
		for (int i = 0; i < list.size(); i++) {
			bean = list.get(i);
			if (getIsSelected().get(i) != null) {
				if (getIsSelected().get(i) == true) {
					totalPrice += bean.getCount();
				}
			}
		}
		return totalPrice;
	}

	private int getNum() {
		int num = 0;
		for (int i = 0; i < list.size(); i++) {
			bean = list.get(i);
			if (getIsSelected().get(i) != null) {
				if (getIsSelected().get(i) == true) {
					num += 1;
				}
			}
		}
		return num;
	}

	/**
	 * 判断是否购物车中所有的商品全部被选中
	 * 
	 * @return true所有条目全部被选中 false还有条目没有被选中
	 */
	private boolean isAllSelected() {
		boolean flag = true;
		for (int i = 0; i < list.size(); i++) {
			if (getIsSelected().get(i) != null) {
				if (!getIsSelected().get(i)) {
					flag = false;
					break;
				}
			}
		}
		return flag;
	}

	@Override
	public View generateView(final int position, ViewGroup parent) {
		
		View convertView = inflater
					.inflate(R.layout.inventory_item, null);
		// 滑动删除
		final SwipeLayout swipeLayout = (SwipeLayout) convertView
				.findViewById(getSwipeLayoutResourceId(position));
//		holder.ll_menu = (LinearLayout) convertView.findViewById(R.id.ll_menu);
		convertView.findViewById(R.id.ll_menu).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
//				String string = list.get((Integer) arg0.getTag()).getOrder_id();
//				new DeleteTask().execute(string);
				z = position;
				// 点击完成之后，关闭删除menu
				swipeLayout.close();
			}
		});
	
		return convertView;
	}

	@Override
	public void fillValues(int position, View convertView) {
		bean = list.get(position);
			holder = new ViewHolder();
			holder.shop_photo = (ImageView) convertView
					.findViewById(R.id.inventory_item_image);
			holder.shop_zongxu = (TextView) convertView
					.findViewById(R.id.inventory_item_totalText);
			holder.shop_description = (TextView) convertView
					.findViewById(R.id.inventory_item_describeText);
			holder.shop_shengyu = (TextView) convertView
					.findViewById(R.id.inventory_item_residueText);
			holder.shop_check = (CheckBox) convertView
					.findViewById(R.id.inventory_item_checkbox);
			holder.shop_saowei = (CheckBox) convertView
					.findViewById(R.id.inventory_item_undefinedBox);
			jia = (Button) convertView.findViewById(R.id.inventory_item_addBtn);
			holder.jia = (Button) convertView
					.findViewById(R.id.inventory_item_addBtn);
			holder.jian = (Button) convertView
					.findViewById(R.id.inventory_item_minusBtn);
			holder.xianshi = (EditText) convertView
					.findViewById(R.id.inventory_item_centerNumEd);
			// xianshi = (EditText) convertView
			// .findViewById(R.id.inventory_item_xianshibtn);
			holder.xianshi.setOnTouchListener(new OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					if (event.getAction() == MotionEvent.ACTION_UP) {
						i = (Integer) v.getTag();
						System.out.println("i===" + i);
						// i = position;
					}
					return false;
				}
			});
			class MyT implements TextWatcher {
				public MyT(ViewHolder holder) {
					mHolder = holder;
				}

				private ViewHolder mHolder;

				@Override
				public void onTextChanged(CharSequence s, int start,
						int before, int count) {
					String string = holder.xianshi.getText().toString();
					holder.xianshi.setSelection(string.length());
					for (int i = start; i < count; i++) {
						if (s.toString().equals("0")) {
							return;
						}
					}
				}

				@Override
				public void beforeTextChanged(CharSequence s, int start,
						int count, int after) {

				}

				@Override
				public void afterTextChanged(Editable s) {
					System.out.println("i++++" + i);
					int position = (Integer) mHolder.xianshi.getTag();
					if (s.toString().equals("") != true) {
						if (Integer.valueOf(s.toString()) > Integer
								.valueOf(list.get(position).getShengyu())) {
							list.get(position).setCount(
									Integer.valueOf(list.get(position)
											.getShengyu()));
							notifyDataSetChanged();
						} else {
							if (list.get(position).getPrefecture().equals("1")) {
								int z = Integer.valueOf(s.toString());
								if (z / 10 != 0) {
									z = z / 10 * 10;
									list.get(position).setCount(z);
								}
							} else {
								list.get(position).setCount(
										Integer.valueOf(s.toString()));
							}
							ShoppingCanst.isConut.put(list.get(position)
									.getStages_id(), Integer.valueOf(list.get(
									position).getCount()));
						}
					} else {
						if (list.get(i).getPrefecture().equals("0")) {
							list.get(i).setCount(1);
						} else if (list.get(i).getPrefecture().equals("1")) {
							list.get(i).setCount(10);
						}
					}
					mHandler.sendMessage(mHandler.obtainMessage(10,
							getTotalPrice()));
					// 如果所有的物品全部被选中，则全选按钮也默认被选中
					mHandler.sendMessage(mHandler.obtainMessage(11,
							isAllSelected()));
					mHandler.sendMessage(mHandler.obtainMessage(12, null));
					mHandler.sendMessage(mHandler.obtainMessage(13, getNum()));
				}
			}
			holder.xianshi.addTextChangedListener(new MyT(holder) {
			});

		ImageloaderUtils.MyImageLoader4(bean.getShopPicture(),
				holder.shop_photo, context);
		holder.shop_description.setText(bean.getShopDescription());
		holder.shop_zongxu.setText(bean.getZongxu());
		holder.shop_shengyu.setText(bean.getShengyu());
		holder.shop_check.setTag(position);
//		holder.ll_menu.setTag(position);
		holder.jia.setTag(position);
		holder.jian.setTag(position);
		holder.xianshi.setTag(position);
		holder.shop_saowei.setTag(position);
		// holder.shop_check.setChecked(bean.isChoosed());
		if (getIsSelected2().get(position) == null) {
		} else {
			holder.shop_saowei.setChecked(getIsSelected2().get(position));
		}if (getIsSelected().get(position) == null) {
		}else {
			holder.shop_check.setChecked(getIsSelected().get(position));
			
		}
		holder.shop_check
				.setOnCheckedChangeListener(new CheckBoxChangedListener());
		holder.shop_saowei
				.setOnCheckedChangeListener(new CheckBoxChangedListener2());

		holder.jia.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				a = (Integer) v.getTag();
				if (list.get(a).getPrefecture().equals("0")) {
					int count = Integer.valueOf((list.get(a).getCount()));
					count++;
					if (count > Integer.valueOf(list.get(a).getShengyu())) {
						return;
					} else {
						list.get(a).setCount(count);
						ShoppingCanst.isConut.put(list.get(a).getStages_id(),
								count);
						mHandler.sendMessage(mHandler.obtainMessage(10,
								getTotalPrice()));
						// 如果所有的物品全部被选中，则全选按钮也默认被选中
						mHandler.sendMessage(mHandler.obtainMessage(11,
								isAllSelected()));
						mHandler.sendMessage(mHandler.obtainMessage(12, null));
						notifyDataSetChanged();
					}
				} else if (list.get(a).getPrefecture().equals("1")) {
					int count = Integer.valueOf((list.get(a).getCount()));
					count += 10;
					if (count > Integer.valueOf(list.get(a).getShengyu())) {
						return;
					} else {
						list.get(a).setCount(count);
						ShoppingCanst.isConut.put(list.get(a).getStages_id(),
								count);
						mHandler.sendMessage(mHandler.obtainMessage(10,
								getTotalPrice()));
						// 如果所有的物品全部被选中，则全选按钮也默认被选中
						mHandler.sendMessage(mHandler.obtainMessage(11,
								isAllSelected()));
						mHandler.sendMessage(mHandler.obtainMessage(12, null));
						notifyDataSetChanged();
					}
				}
			}
		});
		holder.jian.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				a = (Integer) v.getTag();
				if (list.get(a).getPrefecture().equals("0")) {
					int count = Integer.valueOf((list.get(a).getCount()));
					if (count == 1) {
						return;
					} else {
						count--;
						list.get(a).setCount(count);
						ShoppingCanst.isConut.put(list.get(a).getStages_id(),
								count);
						mHandler.sendMessage(mHandler.obtainMessage(10,
								getTotalPrice()));
						// 如果所有的物品全部被选中，则全选按钮也默认被选中
						mHandler.sendMessage(mHandler.obtainMessage(11,
								isAllSelected()));
						mHandler.sendMessage(mHandler.obtainMessage(12, null));
						notifyDataSetChanged();
					}
				} else if (list.get(a).getPrefecture().equals("1")) {
					int count = Integer.valueOf((list.get(a).getCount()));
					if (count == 10) {
						return;
					} else {
						count -= 10;
						list.get(a).setCount(count);
						ShoppingCanst.isConut.put(list.get(a).getStages_id(),
								count);
						mHandler.sendMessage(mHandler.obtainMessage(10,
								getTotalPrice()));
						// 如果所有的物品全部被选中，则全选按钮也默认被选中
						mHandler.sendMessage(mHandler.obtainMessage(11,
								isAllSelected()));
						mHandler.sendMessage(mHandler.obtainMessage(12, null));
						notifyDataSetChanged();
					}
				}
			}
		});

		holder.xianshi.setText(list.get(position).getCount() + "");
//		if (bean.isSaowe() == true) {
//			// bean.setCount(Integer.valueOf(bean.getShengyu()));
//			holder.xianshi.setText(bean.getCount());
//		} else {
//			// holder.xianshi.setText("1");
//		}
		convertView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
//				MyInputMethodManager.MyInputMethodManager1(convertView, context);
			}
		});
		holder.xianshi.clearFocus();
		if (i != -1 && i == position) {
			holder.xianshi.requestFocus();
		}
	}

}
