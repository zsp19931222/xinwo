package com.quwu.xinwo.discover;

import java.util.concurrent.Executors;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.RelativeLayout;

import com.quwu.xinwo.R;
import com.quwu.xinwo.popupwindow.Loading_Pop;

/**
 * 
 * 发现界面
 * 
 * @author ZhouShaoPeng
 * 
 * */

public class Discover_Activity extends Fragment implements OnClickListener {
	private View view;
	private RelativeLayout discoverRelative;
	private RelativeLayout auctionRelative;
	private ImageView discoverImage;
	private ImageView auctionImage;
	private Discover_Fragment1 discover_Fragment1;
	private Discover_Fragment2 discover_Fragment2;


	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.discover, null);
		new Task().executeOnExecutor(Executors.newCachedThreadPool());
		return view;
	}

	private class Task extends AsyncTask<Void, Void, Void> {

		protected Void doInBackground(Void... params) {
			findID();
			isDiscover_Fragment3();
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
		}
	};

	private void findID() {
		discoverRelative = (RelativeLayout) view
				.findViewById(R.id.discover_discoverRelative);
		auctionRelative = (RelativeLayout) view
				.findViewById(R.id.discover_auctionRelative);
		discoverImage = (ImageView) view
				.findViewById(R.id.discover_discoverImage);
		auctionImage = (ImageView) view
				.findViewById(R.id.discover_auctionImage);

		discover_Fragment1 = new Discover_Fragment1();
		discover_Fragment2 = new Discover_Fragment2();

		discoverRelative.setOnClickListener(this);
		auctionRelative.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		FragmentManager manager = getFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		switch (v.getId()) {
		// 发现
		case R.id.discover_discoverRelative:
			// discoverImage.setVisibility(View.VISIBLE);
			// auctionImage.setVisibility(View.INVISIBLE);
			// if (discover_Fragment1 == null) {
			// discover_Fragment1 = new Discover_Fragment1();
			// }
			// if (!discover_Fragment1.isAdded()) {
			// transaction.add(R.id.main_frame, discover_Fragment1);
			// } else {
			// if (discover_Fragment2 != null) {
			// transaction.hide(discover_Fragment2);
			// // transaction.hide(fragment3);
			// }
			// transaction.show(discover_Fragment1);
			// }
			isDiscover_Fragment1(transaction);
			break;
		// 拍卖
		case R.id.discover_auctionRelative:
			// discoverImage.setVisibility(View.INVISIBLE);
			// auctionImage.setVisibility(View.VISIBLE);
			// if (discover_Fragment2 == null) {
			// discover_Fragment2 = new Discover_Fragment2();
			// }
			// if (!discover_Fragment2.isAdded()) {
			// transaction.add(R.id.main_frame, discover_Fragment2);
			// } else {
			// if (discover_Fragment1 != null) {
			// transaction.hide(discover_Fragment1);
			// // transaction.hide(fragment3);
			// }
			// transaction.show(discover_Fragment2);
			// }
			isDiscover_Fragment2(transaction);
			break;

		default:
			break;
		}
		transaction.commit();

	}

	private void isDiscover_Fragment1(FragmentTransaction transaction) {
		discoverImage.setVisibility(View.VISIBLE);
		auctionImage.setVisibility(View.INVISIBLE);
		if (discover_Fragment1 == null) {
			discover_Fragment1 = new Discover_Fragment1();
		}
		if (!discover_Fragment1.isAdded()) {
			transaction.add(R.id.discover_frame, discover_Fragment1);
		} else {
			if (discover_Fragment2 != null) {
				transaction.hide(discover_Fragment2);
			}
			transaction.show(discover_Fragment1);
		}
	}

	private void isDiscover_Fragment3() {
		discoverImage.setVisibility(View.VISIBLE);
		auctionImage.setVisibility(View.INVISIBLE);
		FragmentManager manager = getFragmentManager();
		FragmentTransaction transaction1 = manager.beginTransaction();
		transaction1.replace(R.id.discover_frame, discover_Fragment1);
		transaction1.commit();
	}

	private void isDiscover_Fragment2(FragmentTransaction transaction) {
		discoverImage.setVisibility(View.INVISIBLE);
		auctionImage.setVisibility(View.VISIBLE);
		if (discover_Fragment2 == null) {
			discover_Fragment2 = new Discover_Fragment2();
		}
		if (!discover_Fragment2.isAdded()) {
			transaction.add(R.id.discover_frame, discover_Fragment2);
		} else {
			if (discover_Fragment1 != null) {
				transaction.hide(discover_Fragment1);
			}
			transaction.show(discover_Fragment2);
		}
	}
}
