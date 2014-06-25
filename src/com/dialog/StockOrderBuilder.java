package com.dialog;

import com.preference.PreferenceStock;
import com.stockphone.R;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

public class StockOrderBuilder extends Builder {
	
	@SuppressWarnings("unused")
	private Context context;
	private final static int ITEM_COD_ARTICLE = 0;
	private final static int ITEM_COD_ARTICLE_PROVIDER = 1;
	private final static int ITEM_CATEGORY = 2;
	private final static int ITEM_PROVIDER = 3;
	
	public StockOrderBuilder(final Context context) {
		super(context);
		this.context = context;
		this.setTitle(R.string.lblTitleStockOrder);
		final PreferenceStock preferenceStock = new PreferenceStock();
		preferenceStock.loadPreference(context);
		this.setNegativeButton(R.string.btnCancel, new OnClickListener() {			
			
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		this.setSingleChoiceItems(R.array.stock_order, preferenceStock.getCustomOrder(), new OnClickListener() {			
			
			public void onClick(DialogInterface dialog, int which) {		
				switch(which) {
					case ITEM_COD_ARTICLE: {
						preferenceStock.setCustomOrder(PreferenceStock.VALUE_ORDER_COD_ARTICLE);
					} break;
					
					case ITEM_COD_ARTICLE_PROVIDER: {
						preferenceStock.setCustomOrder(PreferenceStock.VALUE_ORDER_COD_ARTICLE_PROVIDER);
					} break;
					
					case ITEM_CATEGORY: {
						preferenceStock.setCustomOrder(PreferenceStock.VALUE_ORDER_CATEGORY);
					} break;
					
					case ITEM_PROVIDER: {
						preferenceStock.setCustomOrder(PreferenceStock.VALUE_ORDER_PROVIDER);
					} break;
				}
				preferenceStock.savePreference(context);
				dialog.dismiss();
			}
		});
	}

}
