package com.dialog;

import com.stockphone.R;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;

public class SummaryConfirmRequisition extends Builder {
	
	private boolean pressedButtonOPk = false;

	public SummaryConfirmRequisition(Context context) {
		super(context);
		this.setTitle(R.string.lblTitleConfirm);
		this.setIcon(R.drawable.ic_menu__list_confirm);
		this.setMessage(R.string.lblMessageConfirm);	
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
	public boolean getPressedButtonOPk() {
		return pressedButtonOPk;
	}

	public void setPressedButtonOPk(boolean pressedButtonOPk) {
		this.pressedButtonOPk = pressedButtonOPk;
	}	
}
