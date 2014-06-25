package com.dialog;

import com.logic.model.ArticleDTO;
import com.stockphone.R;
import android.content.DialogInterface.OnClickListener;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class StockAddRequisitionListBuilder extends Builder {
	
	private String txtAddArt;
	private EditText txtAddArts;
	
	public StockAddRequisitionListBuilder(Context context, ArticleDTO articleDTO) {
		super(context);
		
		int maxLength = 9;  
		InputFilter[] FilterArray = new InputFilter[1];  
		FilterArray[0] = new InputFilter.LengthFilter(maxLength);
		
		// load some kind of a view
		LayoutInflater li = LayoutInflater.from(context);
		View view = li.inflate(R.layout.lyt_promt, null);
		this.txtAddArts = (EditText) view.findViewById(R.id.txtPrompt);
		this.txtAddArts.setFilters(FilterArray);
		this.setView(view);
		String cod_article = context.getString(R.string.lblSubTitleCodArtile)
				.concat(" ").concat(articleDTO.getCod_article());
		String title = context.getString(R.string.lblTitleAddQuantity).concat("\n");
		title = title.concat(cod_article);
		this.setTitle(title);
		this.setIcon(R.drawable.ic_menu_add);
		this.setPositiveButton(R.string.btnOk, new OnClickListener() {			
			
			public void onClick(DialogInterface dialog, int which) {
				setTxtAddArt(String.valueOf(txtAddArts.getText()));
				dialog.dismiss();
			}
		});
		this.setNegativeButton(R.string.btnCancel, new OnClickListener() {			
			
			public void onClick(DialogInterface dialog, int which) {
				setTxtAddArt(null);
				dialog.dismiss();
			}
		});
		this.setOnCancelListener(new OnCancelListener() {			
			
			public void onCancel(DialogInterface dialog) {
				setTxtAddArt(null);
				dialog.dismiss();
			}
		});
	}

	public String getTxtAddArt() {
		return txtAddArt;
	}

	public void setTxtAddArt(String txtAddArt) {
		this.txtAddArt = txtAddArt;
	}

	public EditText getTxtAddArts() {
		return txtAddArts;
	}

	public void setTxtAddArts(EditText txtAddArts) {
		this.txtAddArts = txtAddArts;
	}		
}
