package com.dialog;

import com.logic.model.ArticleDTO;
import com.stockphone.R;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class RequisitionEditArticleBuilder extends Builder {
	
	private String txtAddArt;
	private final static String ONLY_NUMBER_REGULAR_EXPRESSION = "[0-9]";
	private EditText txtAddArts;

	public RequisitionEditArticleBuilder(Context context, ArticleDTO articleDTO) {
		super(context);
		InputFilter filterLowerCase = new InputFilter() {
			
			
			public CharSequence filter(CharSequence source, int start, int end,
					Spanned dest, int dstart, int dend) {
				
				String aux = String.valueOf(source).toLowerCase();

				if(!aux.matches(ONLY_NUMBER_REGULAR_EXPRESSION)) {
					source = "";
				}
				return source;
			}
		};
		InputFilter[] filters = {filterLowerCase};
		// load some kind of a view
		LayoutInflater li = LayoutInflater.from(context);
		View view = li.inflate(R.layout.lyt_promt, null);
		this.txtAddArts = (EditText) view.findViewById(R.id.txtPrompt);
		String quantity = String.valueOf(articleDTO.getQuantity());
		this.txtAddArts.setText(quantity);
		this.txtAddArts.setFilters(filters);
		this.setView(view);
		String cod_article = context.getString(R.string.lblSubTitleCodArtile)
				.concat(" ").concat(articleDTO.getCod_article());
		String title = context.getString(R.string.lblEditQuantity).concat("\n");
		title = title.concat(cod_article);
		this.setTitle(title);
		this.setIcon(android.R.drawable.ic_menu_edit);
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
