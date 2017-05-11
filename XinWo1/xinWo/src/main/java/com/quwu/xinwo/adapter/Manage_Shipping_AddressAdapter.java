package com.quwu.xinwo.adapter;

import java.util.List;
import java.util.concurrent.Executors;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.quwu.xinwo.R;
import com.quwu.xinwo.bean.Manage_Shipping_AddressBean;
import com.quwu.xinwo.global.MyApp;
import com.quwu.xinwo.mine.Add_Shipping_AddressActivity;
import com.quwu.xinwo.mywight.MyToast;
import com.quwu.xinwo.okhttp.OKHTTP_POST;

public class Manage_Shipping_AddressAdapter extends BaseAdapter {

	private Context context;
	private LayoutInflater inflater;
	private List<Manage_Shipping_AddressBean> list;
	private Activity activity;

	public Manage_Shipping_AddressAdapter(Context context,
			List<Manage_Shipping_AddressBean> list, Activity activity) {
		super();
		this.context = context;
		this.list = list;
		this.activity = activity;
		inflater = LayoutInflater.from(context);
	}

	public int getCount() {

		return list.size();
	}

	public Object getItem(int position) {

		return null;
	}

	public long getItemId(int position) {

		return 0;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(
					R.layout.manage_shipping_address_item, null);
			holder.name = (TextView) convertView
					.findViewById(R.id.manage_shipping_address_item_nameText);
			holder.phoneNum = (TextView) convertView
					.findViewById(R.id.manage_shipping_address_item_phoneNumText);
			holder.area = (TextView) convertView
					.findViewById(R.id.manage_shipping_address_item_areaText);
			holder.compile = (TextView) convertView
					.findViewById(R.id.manage_shipping_address_item_compileText);
			holder.delete = (TextView) convertView
					.findViewById(R.id.manage_shipping_address_item_deleteText);
			holder.box = (CheckBox) convertView
					.findViewById(R.id.manage_shipping_address_item_checkbox);
			holder.compile.setTag(position);
			holder.delete.setTag(position);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.name.setText(list.get(position).getName());
		holder.phoneNum.setText(list.get(position).getPhoneNum());
		holder.area.setText(list.get(position).getArea());
		holder.box.setChecked(list.get(position).isChecked());
		holder.compile.setOnClickListener(new compileClickListener());
		holder.delete.setOnClickListener(new deleteClickListener());
		return convertView;
	}

	private class ViewHolder {
		private TextView name, phoneNum, area;
		private CheckBox box;
		private TextView compile;
		private TextView delete;
	}

	private class compileClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			int i = (Integer) v.getTag();
			Intent intent = new Intent(context,
					Add_Shipping_AddressActivity.class);
			intent.putExtra("name", list.get(i).getName());
			intent.putExtra("phoneNum", list.get(i).getPhoneNum());
			intent.putExtra("area", list.get(i).getReceipt_area());
			intent.putExtra("particular_area", list.get(i).getArea());
			intent.putExtra("state", "1");
			activity.startActivity(intent);
		}
	}

	private class deleteClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			int i = (Integer) v.getTag();
			if (list.get(i).isChecked()) {
				MyToast.Toast(context, "不能删除默认地址哦~");
			}else {
				new DeleteTask().executeOnExecutor(Executors.newCachedThreadPool(),
						i);
			}
		}
	}

	private class DeleteTask extends AsyncTask<Integer, Void, Void> {
		String string;
		int i;

		@Override
		protected Void doInBackground(Integer... params) {
			i = params[0];
			String result = OKHTTP_POST.doPost1(MyApp.base_address
					+ "usersaction/delreceiptaddress.do", "address_id", list
					.get(params[0]).getAddress_id());
			if (result != null) {
				try {
					JSONObject jsonObject = new JSONObject(result);
					string = jsonObject.getString("1");

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			if (string != null) {
				if (string.equals("删除成功")) {
					list.remove(i);
					notifyDataSetChanged();
				} else {
					MyToast.Toast(context, string);
				}
			}
		}
	}

}
