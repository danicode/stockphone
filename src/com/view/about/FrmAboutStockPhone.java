package com.view.about;

import com.stockphone.R;

import android.app.Activity;
import android.os.Bundle;

public class FrmAboutStockPhone extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.initComponents();
	}

	private void initComponents() {
		this.setContentView(R.layout.lyt_about);
		this.setTitle(R.string.lblTitleAboutStockPhone);
	}
}
