package com.dialog;

import com.stockphone.R;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;

public class SummaryDialogCancel extends Builder {
	
	private boolean pressedButtonOPk = false;

	public SummaryDialogCancel(Context context) {
		super(context);
		this.setTitle(R.string.lblTitleWarning);
		this.setIcon(android.R.drawable.ic_dialog_alert);
		this.setMessage(R.string.lblSummaryMessageCancel);
		this.setPositiveButton(R.string.btnOk, new OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				setPressedButtonOPk(true);
				dialog.dismiss();
			}
		});
		this.setNegativeButton(R.string.btnCancel, new OnClickListener() {			
			
			public void onClick(DialogInterface dialog, int which) {
				setPressedButtonOPk(false);
				dialog.dismiss();
			}
		});
		this.setOnCancelListener(new OnCancelListener() {			
			
			public void onCancel(DialogInterface dialog) {
				setPressedButtonOPk(false);
				dialog.dismiss();
			}
		});		
	}

	public boolean isPressedButtonOPk() {
		return pressedButtonOPk;
	}

	public void setPressedButtonOPk(boolean pressedButtonOPk) {
		this.pressedButtonOPk = pressedButtonOPk;
	}	
}
