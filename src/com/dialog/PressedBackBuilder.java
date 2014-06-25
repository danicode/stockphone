package com.dialog;

import com.stockphone.R;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

public class PressedBackBuilder extends Builder {

	public PressedBackBuilder(Context context) {
		super(context);
		this.setTitle(R.string.lblTitleMessage);
		this.setMessage(R.string.lblMessagePressedBack);
		this.setIcon(android.R.drawable.ic_dialog_info);
		this.setPositiveButton(R.string.btnOk, new OnClickListener() {			
		
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();				
			}
		});
	}

}
