package com.dialog;

import com.stockphone.R;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

public class StockRequisitionListSizeZeroBuilder extends Builder {

	public StockRequisitionListSizeZeroBuilder(Context context) {
		super(context);
		this.setTitle(R.string.lblTitleWarning);
		this.setIcon(android.R.drawable.ic_dialog_alert);
		this.setMessage(R.string.lblMsgVoidList);
		this.setPositiveButton(R.string.btnOk, new OnClickListener() {			
			
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
	}

}
