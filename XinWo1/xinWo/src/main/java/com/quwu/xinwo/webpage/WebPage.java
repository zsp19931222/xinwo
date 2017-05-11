package com.quwu.xinwo.webpage;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.quwu.xinwo.R;
import com.quwu.xinwo.global.MyApp;
import com.quwu.xinwo.swipebacklayout.SwipeBackActivity;
import com.quwu.xinwo.until.FinishActivity;

public class WebPage extends SwipeBackActivity {

	private WebView view;
	private ProgressBar bar;
	private TextView titleText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webpage);
		FinishActivity.finish(R.id.webpage_returnRel, this);
		findID();
	}

	private void findID() {
		bar = (ProgressBar) findViewById(R.id.webpage_myProgressBar);
		view = (WebView) findViewById(R.id.webpage_weibo);
		titleText=(TextView) findViewById(R.id.webpage_titleText);
		if (!this.getIntent().getExtras().getString("url").equals("")) {
//			view.loadUrl(MyApp.base_address + this.getIntent().getExtras().getString("url"));
			view.loadUrl(this.getIntent().getExtras().getString("url"));
			System.out.println("url====="+this.getIntent().getExtras().getString("url"));
			titleText.setText(this.getIntent().getExtras().getString("title"));
		}
		view.setWebChromeClient(new WebChromeClient() {
			 
		    @Override
		     public void onProgressChanged(WebView view, int newProgress) {
		         if (newProgress == 100) {
		             bar.setVisibility(View.INVISIBLE);
		         } else {
		             if (View.INVISIBLE == bar.getVisibility()) {
		                 bar.setVisibility(View.VISIBLE);
		             }
		             bar.setProgress(newProgress);
		         }
		         super.onProgressChanged(view, newProgress);
		     }
		     
		});
	}
}
