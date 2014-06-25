package com.dialog;

import com.logic.model.ArticleDTO;
import com.stockphone.R;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

public class StockInformationViewBuilder extends Builder {
	
	public StockInformationViewBuilder(Context context, ArticleDTO articleDTO) {
		super(context);
		this.setTitle(R.string.lblTitleInformationArticle);
		this.setIcon(android.R.drawable.ic_dialog_info);
		String cod_artilce = context.getString(R.string.lblSubTitleCodArtile)
				.concat(" ").concat(articleDTO.getCod_article()).concat("\n\n");
		String cod_article_provider = context
				.getString(R.string.lblSubTitleCodArticleProvider).concat(" ")
				.concat(articleDTO.getCod_article_provider()).concat("\n\n");
		String description = context.getString(R.string.lblSubTitleDescription)
				.concat(" ").concat(articleDTO.getDescription()).concat("\n\n");
		String category = context.getString(R.string.lblSubTitleCategory)
				.concat(" ").concat(articleDTO.getCategoryName())
				.concat("\n\n");
		String provider = context.getString(R.string.lblSubTitleProvider)
				.concat(" ").concat(articleDTO.getProviderName());
		String message = cod_artilce.concat(cod_article_provider)
				.concat(description).concat(category).concat(provider);
		this.setMessage(message);
		this.setPositiveButton(R.string.btnOk, new OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
	}

}
