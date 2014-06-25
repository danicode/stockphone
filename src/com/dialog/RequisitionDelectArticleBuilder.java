package com.dialog;

import com.logic.model.ArticleDTO;
import com.stockphone.R;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;

public class RequisitionDelectArticleBuilder extends Builder {
	
	private boolean pressedButtonOPk = false;

	public RequisitionDelectArticleBuilder(Context context, ArticleDTO articleDTO) {
		super(context);
		this.setTitle(R.string.lblTitleDeleteArticle);
		this.setIcon(R.drawable.ic_delete);
		String message = context.getString(R.string.lblMessageDeleteArticle).concat("\n\n");
		String cod_article = context.getString(R.string.lblSubTitleCodArtile)
				.concat(" ").concat(articleDTO.getCod_article());
		message = message.concat(cod_article);
		this.setMessage(message);
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
