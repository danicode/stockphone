package com.dialog;

import com.logic.model.ArticleDTO;
import com.stockphone.R;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

public class StockDuplicateArticleBuilder extends Builder {

	public StockDuplicateArticleBuilder(Context context, ArticleDTO articleDTO) {
		super(context);
		this.setTitle(R.string.lblTitleWarning);
		this.setIcon(android.R.drawable.ic_dialog_alert);
		String cod_article = context.getString(R.string.lblSubTitleCodArtile)
				.concat(" ").concat(articleDTO.getCod_article()).concat("\n\n");
		String message = context.getString(R.string.lblWarningAddArticle);
		cod_article = cod_article.concat(message);
		this.setMessage(cod_article);		
		this.setPositiveButton(R.string.btnOk, new OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
	}

}
