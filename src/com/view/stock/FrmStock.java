package com.view.stock;

import java.util.ArrayList;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnFocusChangeListener;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import com.dialog.ConfirmPressedBack;
import com.dialog.StockAddRequisitionListBuilder;
import com.dialog.StockDuplicateArticleBuilder;
import com.dialog.StockFilterBuilder;
import com.dialog.StockInformationViewBuilder;
import com.dialog.StockOrderBuilder;
import com.dialog.StockRequisitionListSizeZeroBuilder;
import com.logic.ArticleBO;
import com.logic.model.ArticleDTO;
import com.logic.model.UserDTO;
import com.preference.PreferenceStock;
import com.stockphone.R;
import com.view.requisition.FrmRequisition;
import com.view.summary.FrmSummary;

public class FrmStock extends Activity {
	
	private ListView lstvArticle;
	private StockAdapter stockAdapter;
	private UserDTO uDTO;
	private EditText txtSerchArticle;
	private ArticleDTO articleDTO;
	private ArrayList<ArticleDTO> requisitionList;
	private final static int DIALOG_ORDER = 0;
	private final static int DIALOG_FILTER = 1;
	private final static int DIALOG_INFORMATION_VIEW = 2;
	private final static int DIALOG_ADD_REQUISITION_LIST = 3;
	private final static int DIALOG_DUPLICATE_ARTICLE = 4;
	private final static int DIALOG_VOID_LIST = 5;	
	private final static int DIALOG_CONFIRM_PRESSED_BACK = 6;
	private final static int DIALOG_NO_MONTED_SD = 7;		
	private final static int DIALOG_PROGRESSBAR = 8;
	private AlertDialog.Builder builderNoAddQuantity;
	private AlertDialog.Builder builderNumZero;
//	private final static int POSITION_ARTICLE = 0;
//	private final static int POSITION_DIALOG_NO_ADD_QUANTITY = 1;
//	private final static int POSITON_DIALOG_NUM_ZERO = 2;
//	private final static int POSITION_REQUISITION_LIST = 3;
//	private final static int POSITION_DIALOG_NO_MONTED_SD = 4;
//	private final static int POSITION_ADAPTER = 5;
//	private final static int POSITION_PROBRESSBAR = 6;
//	private final static int LIST_SIZE = 7;
	public final static String REQUSITION_LIST_SERIALIZABLE = "RequisitionList";
	public final static String LIST_ARTICLES_SERIALIZABLE = "Articles";
	private Dialog dialogNoMontedSD;
//	private ProgressDialog progDialog;
	
	@SuppressWarnings("unchecked")
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		Bundle bundle = this.getIntent().getExtras(); 
		this.uDTO = (UserDTO) bundle.getSerializable(UserDTO.USER_SERIALIZABLE);
		this.requisitionList = (ArrayList<ArticleDTO>) bundle
				.getSerializable(REQUSITION_LIST_SERIALIZABLE);		
//		this.restoreMe();
		this.requisitionList = new ArrayList<ArticleDTO>();
		this.initComponents();	
	}
	
	private void initComponents() {			
		this.setContentView(R.layout.lyt_stock);		
		this.setTitle(R.string.lblTitleStock);		
		this.lstvArticle = (ListView) this.findViewById(R.id.lstvArticle);
		this.loadArticle();
		this.registerForContextMenu(this.lstvArticle);	
		this.txtSerchArticle = (EditText) this.findViewById(R.id.txtSerchArticle);	
		if(this.memorySDMonted()) {	
			if(this.stockAdapter != null) {
				this.txtSerchArticle.addTextChangedListener(new TextWatcher() {			
					
					public void onTextChanged(CharSequence s, int start, int before, int count) {
						stockAdapter.getFilter().filter(s);									
					}			
					
					public void beforeTextChanged(CharSequence s, int start, int count,	int after) {}			
					
					public void afterTextChanged(Editable s) {}
				});	
			}			
		}										
	}	
	
	
	protected void onStart() {
		super.onStart();
		this.loadPreference();
	}
	
//	@SuppressWarnings("unchecked")
//	private void restoreMe() {		
//		if(this.getLastNonConfigurationInstance() != null) {	
//			Object [] list = (Object []) this.getLastNonConfigurationInstance();
//			for(int i = 0; i < LIST_SIZE; i++) {
//				switch(i) {
//					case POSITION_ARTICLE: {
//						this.articleDTO = (ArticleDTO) list[i];
//					} break;					
//					case POSITION_DIALOG_NO_ADD_QUANTITY: {
//						this.builderNoAddQuantity = (AlertDialog.Builder) list[i];
//						if(this.builderNoAddQuantity != null) {
//							AlertDialog ad = this.builderNoAddQuantity.create();
//							ad.setOnDismissListener(new OnDismissListener() {							
//								
//								public void onDismiss(DialogInterface dialog) {								
//									showDialog(DIALOG_ADD_REQUISITION_LIST);
//									builderNoAddQuantity = null;
//									dialog.dismiss();
//								}
//							});							
//							ad.show();
//						}
//					} break;					
//					case POSITON_DIALOG_NUM_ZERO: {
//						this.builderNumZero = (AlertDialog.Builder) list[i];
//						if(this.builderNumZero != null) {
//							AlertDialog ad = this.builderNumZero.create();
//							ad.setOnDismissListener(new OnDismissListener() {							
//								
//								public void onDismiss(DialogInterface dialog) {								
//									showDialog(DIALOG_ADD_REQUISITION_LIST);
//									builderNumZero = null;
//									dialog.dismiss();
//								}
//							});							
//							ad.show();
//						}
//					} break;					
//					case POSITION_REQUISITION_LIST: {
//						this.requisitionList = (ArrayList<ArticleDTO>) list[i];
//					} break;					
//					case POSITION_DIALOG_NO_MONTED_SD: {
//						this.dialogNoMontedSD = (Dialog) list[i];
//					} break;	
//					case POSITION_ADAPTER: {
//						this.stockAdapter = (StockAdapter) list[i];
//					} break;
//					case POSITION_PROBRESSBAR: {
//						this.progDialog = (ProgressDialog) list[i];
//					} break;
//				}
//			}
//		} else {
//			if(this.requisitionList == null) {
//				this.requisitionList = new ArrayList<ArticleDTO>();
//			}			
//		}
//	}
//	
//	
//	public Object onRetainNonConfigurationInstance() {
//		Object [] list = new Object[LIST_SIZE];
//		list[POSITION_ARTICLE] = this.articleDTO;
//		list[POSITION_DIALOG_NO_ADD_QUANTITY] = this.builderNoAddQuantity;
//		list[POSITON_DIALOG_NUM_ZERO] = this.builderNumZero;
//		list[POSITION_REQUISITION_LIST] = this.requisitionList;
//		list[POSITION_DIALOG_NO_MONTED_SD] = this.dialogNoMontedSD;
//		list[POSITION_ADAPTER] = this.stockAdapter;
//		list[POSITION_PROBRESSBAR] = this.progDialog;
//		return list;
//	}
	
	private void loadPreference() {
		PreferenceStock preferenceStock = new PreferenceStock();
		boolean initialized = preferenceStock.isInitialized(this);
		if(!initialized) {	
			preferenceStock.firstTime(this);
		}
		preferenceStock.loadPreference(this);
		
		TextView lblOrderValue = (TextView) this.findViewById(R.id.lblOrderValue);
	
		switch(preferenceStock.getCustomOrder()) {
			case  PreferenceStock.VALUE_ORDER_CATEGORY: {
				lblOrderValue.setText(R.string.lblValuePreferenceStockCategory);
			} break;
			
			case PreferenceStock.VALUE_ORDER_COD_ARTICLE: {
				lblOrderValue.setText(R.string.lblValuePreferenceStockCodArticle);
			} break;
			
			case PreferenceStock.VALUE_ORDER_COD_ARTICLE_PROVIDER: {
				lblOrderValue.setText(R.string.lblValuePreferenceStockCodArticleProvider);
			} break; 
			
			case PreferenceStock.VALUE_ORDER_PROVIDER: {
				lblOrderValue.setText(R.string.lblValuePreferecnceStockProvider);
			} break;
		}
		
		TextView lblFilterValue = (TextView) this.findViewById(R.id.lblFilterValue);
		
		switch(preferenceStock.getCustomFilter()) {
			case PreferenceStock.VALUE_FILTER_CATEGORY: {
				lblFilterValue.setText(R.string.lblValuePreferenceStockCategory);
			} break;
			
			case PreferenceStock.VALUE_FILTER_COD_ARTICLE: {
				lblFilterValue.setText(R.string.lblValuePreferenceStockCodArticle);
			} break;
			
			case PreferenceStock.VALUE_FILTER_COD_ARTICLE_PROVIDER: {
				lblFilterValue.setText(R.string.lblValuePreferenceStockCodArticleProvider);
			} break;
			
			case PreferenceStock.VALUE_FILTER_PROVIDER: {
				lblFilterValue.setText(R.string.lblValuePreferecnceStockProvider);
			} break;
			
			case PreferenceStock.VALUE_FILTER_DESCRIPTION: {
				lblFilterValue.setText(R.string.lblValuePreferenceStockDescription);
			} break;
		}
	}
	
	private void loadArticle() {		
		if(this.memorySDMonted()) {				
//			if(this.stockAdapter == null) {	
				try {				
					new DoInBackground().execute();					
				} catch (Exception e) {
					e.printStackTrace();
				}
//			} 
			this.lstvArticle.setAdapter(this.stockAdapter);	
		} else {
			this.showDialog(DIALOG_NO_MONTED_SD);																	
		}			
	}
	
	private boolean memorySDMonted() {
		String state = Environment.getExternalStorageState();
		return (Environment.MEDIA_MOUNTED.equals(state));
	}
	
	
	public boolean onCreateOptionsMenu(Menu menu) {
		boolean result = super.onCreateOptionsMenu(menu);
		MenuInflater inflater = this.getMenuInflater();
		inflater.inflate(R.menu.mn_stock, menu);
		return result;
	}
	
	
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);		
		switch(item.getItemId()) {		
			case R.id.tmFilterStock: {
				this.showDialog(DIALOG_FILTER);
			} break;
			
			case R.id.tmOrderStock: {
				this.showDialog(DIALOG_ORDER);
			} break;			
			case R.id.tmListViewStock: {
				if(this.requisitionList.size() > 0) {
					this.finish();
					Intent intent = new Intent(this, FrmRequisition.class);
					intent.putExtra(REQUSITION_LIST_SERIALIZABLE, this.requisitionList);
					intent.putExtra(UserDTO.USER_SERIALIZABLE, this.uDTO);
					startActivity(intent);
				} else {
					this.showDialog(DIALOG_VOID_LIST);
				}				
			} break;			
			case R.id.tmListConfirmStock: {
				if(this.requisitionList.size() > 0) {
					this.finish();
					Intent intent = new Intent(this, FrmSummary.class);
					intent.putExtra(REQUSITION_LIST_SERIALIZABLE, this.requisitionList);
					intent.putExtra(UserDTO.USER_SERIALIZABLE, this.uDTO);
					startActivity(intent);
				} else {
					this.showDialog(DIALOG_VOID_LIST);
				}				
			} break;		
		}		
		return super.onOptionsItemSelected(item);
	}
	
	
	protected Dialog onCreateDialog(int id) {
		Dialog dialog = null;
		switch(id) {
			case DIALOG_ORDER: {
				dialog = this.getDialogOrder();
			} break;
			
			case DIALOG_FILTER: {
				dialog = this.getDialogFilter();
			} break;
			
			case DIALOG_INFORMATION_VIEW: {
				dialog = this.getDialogInformationView();
			} break;
			
			case DIALOG_ADD_REQUISITION_LIST: {
				dialog = this.getDialogAddRequisitionList();
			} break;
			
			case DIALOG_DUPLICATE_ARTICLE: {
				dialog = this.getDialogDuplicateArticle();
			} break;
			
			case DIALOG_VOID_LIST: {
				dialog = this.getDialogVoidList();
			} break;
			case DIALOG_CONFIRM_PRESSED_BACK: {
				dialog = this.getConfirmPressedBack();
			} break;
			case DIALOG_NO_MONTED_SD: {
				dialog = this.getDialogNoMontedSD();
			} break;
			case DIALOG_PROGRESSBAR: {
				dialog = this.getProgressbar();
			} break;
		}
		return dialog;
	}
	
	private ProgressDialog getProgressbar() {
//		this.progDialog = new ProgressDialog(this);
//		this.progDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//	    String message = this.getString(R.string.lblMessageProgressbar);
//	    this.progDialog.setMessage(message);
//	    this.progDialog.setCancelable(false);
		ProgressDialog progDialog = new ProgressDialog(this);
		progDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
	    String message = this.getString(R.string.lblMessageProgressbar);
	    progDialog.setMessage(message);
	    progDialog.setCancelable(false);
	    progDialog.setOnDismissListener(new OnDismissListener() {			
	    	
	    	public void onDismiss(DialogInterface dialog) {	
	    		dialog.dismiss();				
	    	}
		});				
		return progDialog;
	}
	
	private Dialog getDialogNoMontedSD() {
		if(this.dialogNoMontedSD == null) {
			Builder builderNoMontedSD = new Builder(this);
			builderNoMontedSD.setTitle(R.string.lblTitleWarning);
			builderNoMontedSD.setIcon(android.R.drawable.ic_dialog_alert);
			builderNoMontedSD.setMessage(R.string.lblMsgNoMemorySDMontedRotate);
			builderNoMontedSD.setPositiveButton(R.string.btnOk, new OnClickListener() {			
				
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			});
			builderNoMontedSD.setOnCancelListener(new OnCancelListener() {			
				
				public void onCancel(DialogInterface dialog) {
					dialog.dismiss();
				}
			});			
			this.dialogNoMontedSD = builderNoMontedSD.create();
		}				
		return this.dialogNoMontedSD;
	}
	
	private Dialog getDialogOrder() {
		StockOrderBuilder stockOrderBuilder = new StockOrderBuilder(this);
		Dialog dialog = stockOrderBuilder.create();
		dialog.setOnDismissListener(new OnDismissListener() {			
			
			public void onDismiss(DialogInterface dialog) {
				loadPreference();
				dialog.dismiss();
			}
		});
		return dialog;
	}
	
	private Dialog getDialogFilter() {
		StockFilterBuilder stockFilterBuilder = new StockFilterBuilder(this);
		Dialog dialog = stockFilterBuilder.create();
		dialog.setOnDismissListener(new OnDismissListener() {			
			
			public void onDismiss(DialogInterface dialog) {
				loadPreference();
				dialog.dismiss();
			}
		});
		return dialog;
	}
	
	private Dialog getDialogInformationView() {						
		StockInformationViewBuilder stockInformationViewBuilder = new StockInformationViewBuilder(
				this, articleDTO);
		Dialog dialog = stockInformationViewBuilder.create();
		
		dialog.setOnDismissListener(new OnDismissListener() {
			
			public void onDismiss(DialogInterface dialog) {
				removeDialog(DIALOG_INFORMATION_VIEW);
				dialog.dismiss();				
			}
		});
		return dialog;
	}
	
	private Dialog getDialogAddRequisitionList() {			
		final StockAddRequisitionListBuilder stockAddRequisitionListBuilder = new StockAddRequisitionListBuilder(
				this, this.articleDTO);
		EditText txtAddArts = stockAddRequisitionListBuilder.getTxtAddArts();		
		final Dialog dialog = stockAddRequisitionListBuilder.create();
		txtAddArts.setOnFocusChangeListener(new OnFocusChangeListener() {			
			
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					dialog.getWindow()
							.setSoftInputMode(
									WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
		        }
			}
		});
		dialog.setOnDismissListener(new OnDismissListener() {			
			
			public void onDismiss(DialogInterface dialog) {		
				String quantity = stockAddRequisitionListBuilder.getTxtAddArt();
				if(quantity != null) {
					if(quantity.contentEquals("")) {
						builderNoAddQuantity = new AlertDialog.Builder(FrmStock.this);
						builderNoAddQuantity.setTitle(R.string.lblTitleWarning);
						builderNoAddQuantity.setIcon(android.R.drawable.ic_dialog_alert);
						builderNoAddQuantity.setMessage(R.string.lblMsgWarningNoAddQuantity);
						builderNoAddQuantity.setPositiveButton(R.string.btnOk, new OnClickListener() {					
							
							public void onClick(DialogInterface dialog, int which) {
								dialog.dismiss();						
							}
						});
						AlertDialog ad = builderNoAddQuantity.create();
						ad.setOnDismissListener(new OnDismissListener() {							
							
							public void onDismiss(DialogInterface dialog) {								
								showDialog(DIALOG_ADD_REQUISITION_LIST);
								dialog.dismiss();
							}
						});						
						ad.show();
					} else {
						Integer num = Integer.parseInt(quantity);
						if(num > 0) {
							articleDTO.setQuantity(num);
							requisitionList.add(articleDTO);
							Toast toast = Toast.makeText(FrmStock.this,
									R.string.lblMessageOkAddArticle,
									Toast.LENGTH_SHORT);
							toast.setGravity(Gravity.CENTER, 0, 0);
							toast.show();
						} else {
							builderNumZero = new AlertDialog.Builder(FrmStock.this);
							builderNumZero.setTitle(R.string.lblTitleWarning);
							builderNumZero.setIcon(android.R.drawable.ic_dialog_alert);
							builderNumZero.setMessage(R.string.lblMsgWarningNumZero);
							builderNumZero.setPositiveButton(R.string.btnOk, new OnClickListener() {					
								
								public void onClick(DialogInterface dialog, int which) {
									dialog.dismiss();						
								}
							});
							AlertDialog ad = builderNumZero.create();
							ad.setOnDismissListener(new OnDismissListener() {							
								
								public void onDismiss(DialogInterface dialog) {								
									showDialog(DIALOG_ADD_REQUISITION_LIST);
									dialog.dismiss();
								}
							});							
							ad.show();
						}
					}
				}				
				removeDialog(DIALOG_ADD_REQUISITION_LIST);
				dialog.dismiss();
			}
		});
		
		return dialog;
	}
	
	private Dialog getDialogDuplicateArticle() {						
		StockDuplicateArticleBuilder stockDuplicateArticleBuilder = new StockDuplicateArticleBuilder(
				this, this.articleDTO);
		Dialog dialog = stockDuplicateArticleBuilder.create();
		
		dialog.setOnDismissListener(new OnDismissListener() {
			
			public void onDismiss(DialogInterface dialog) {
				removeDialog(DIALOG_DUPLICATE_ARTICLE);
				dialog.dismiss();				
			}
		});
		return dialog;
	}
	
	private Dialog getDialogVoidList() {
		StockRequisitionListSizeZeroBuilder stockRequisitionListSizeZeroBuilder = new StockRequisitionListSizeZeroBuilder(
				this);
		Dialog dialog = stockRequisitionListSizeZeroBuilder.create();
		dialog.setOnDismissListener(new OnDismissListener() {			
			
			public void onDismiss(DialogInterface dialog) {				
				dialog.dismiss();
			}
		});
		return dialog;
	}
	
	private Dialog getConfirmPressedBack() {
		final ConfirmPressedBack confirmPressedBack = new ConfirmPressedBack(this);
		Dialog dialog = confirmPressedBack.create();
		dialog.setOnDismissListener(new OnDismissListener() {			
			
			public void onDismiss(DialogInterface dialog) {
				if(confirmPressedBack.getPressedButtonOPk()) {
					finish();
				} 
			}
		});
		return dialog;
	}
	
	
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) menuInfo;
		int position = info.position;
		this.articleDTO = this.stockAdapter.getItem(position);
		MenuInflater menuInflater = this.getMenuInflater();
		String title = this.getString(R.string.lblTitleOption);
		String cod_artilce = this.getString(R.string.lblSubTitleCodArtile)
				.concat(" ").concat(articleDTO.getCod_article());
		title = title.concat("\n").concat(cod_artilce);
		menuInflater.inflate(R.menu.mn_stock_item_selected, menu.setHeaderTitle(title));
	}
	
	
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		int position = info.position;
		this.articleDTO = this.stockAdapter.getItem(position);
		int id = item.getItemId();
		switch(id) {
			case R.id.tm_stock_add_requisition_list: {
				if(this.checkAddArticle(this.articleDTO)) {
					this.showDialog(DIALOG_DUPLICATE_ARTICLE);
				} else {
					this.showDialog(DIALOG_ADD_REQUISITION_LIST);
				}				
			} break;
			
			case R.id.tm_stock_information_view: {
				this.showDialog(DIALOG_INFORMATION_VIEW);
			} break;
		}
		return true;
	}
	
	private boolean checkAddArticle(ArticleDTO articleDTO) {
		return this.requisitionList.contains(articleDTO);		
	}
	
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if (keyCode == KeyEvent.KEYCODE_BACK) {
	        moveTaskToBack(false);	        
	        if(this.requisitionList.size() > 0) {
	        	this.showDialog(DIALOG_CONFIRM_PRESSED_BACK);
	        } else {
	        	finish();
	        }
	        return true;
	    }
	    return super.onKeyDown(keyCode, event);
	}	
	
	private class DoInBackground extends AsyncTask<Void, Void, Void> implements
			DialogInterface.OnCancelListener {
		
		private ArrayList<ArticleDTO> articles;

		protected void onPreExecute() {
//			if(progDialog == null) {
				showDialog(DIALOG_PROGRESSBAR);
//			}			
		}

		protected Void doInBackground(Void... unused) {
			ArticleBO articleBO = new ArticleBO();
			try {
				articles = articleBO.recoveryAll();
			} catch (Exception e) {
					e.printStackTrace();
			}
			return(null);
		}

		protected void onPostExecute(Void unused) {
			stockAdapter = new StockAdapter(FrmStock.this,
					R.layout.lyt_stock_item, articles);
			lstvArticle.setAdapter(stockAdapter);
			stockAdapter = new StockAdapter(FrmStock.this, R.layout.lyt_stock_item, articles);
			lstvArticle.setAdapter(stockAdapter);
			txtSerchArticle.addTextChangedListener(new TextWatcher() {			
				
				public void onTextChanged(CharSequence s, int start, int before, int count) {
					stockAdapter.getFilter().filter(s);									
				}			
				
				public void beforeTextChanged(CharSequence s, int start, int count,	int after) {}			
				
				public void afterTextChanged(Editable s) {}
			});	
			removeDialog(DIALOG_PROGRESSBAR);	
		}

		public void onCancel(DialogInterface dialog) {
			this.cancel(false);
		}
	}
}
