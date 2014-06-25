package com.dialog;

import com.stockphone.R;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

public class SummaryErrorGenereateCSVBuilder extends Builder {

	public SummaryErrorGenereateCSVBuilder(Context context) {
		super(context);
		this.setTitle(R.string.lblTitleError);
		this.setIcon(android.R.drawable.ic_dialog_alert);
		this.setMessage(R.string.lblErrorGenerateCSV);
		this.setPositiveButton(R.string.btnOk, new OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
	}

}
