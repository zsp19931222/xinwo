package com.quwu.xinwo.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.quwu.xinwo.R;
import com.quwu.xinwo.gson_entity.Account_ManagementEntity;
import com.quwu.xinwo.until.Tool;

public class Account_ManagementAdapter extends BaseAdapter {

	private List<Account_ManagementEntity> list;
	private Context context;
	private LayoutInflater inflater;

	public Account_ManagementAdapter(List<Account_ManagementEntity> list, Context context) {
		super();
		this.list = list;
		this.context = context;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.accounts_management_item,
					null);
			holder.textView = (TextView) convertView
					.findViewById(R.id.account_managerment_item_BankNameText);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		if (list.get(position).getAccount_name().equals("支付宝")) {
			holder.textView.setText(list.get(position).getAccount_name()+"("+list.get(position).getTransaction_phone()+")");
		}else {
			holder.textView.setText(list.get(position).getAccount_name()+"("+list.get(position).getTransaction_num()+")");
		}
		return convertView;
	}

	private class ViewHolder {
		private TextView textView;
	}
}

