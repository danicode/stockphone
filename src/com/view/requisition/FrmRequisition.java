package com.view.requisition;

import java.util.ArrayList;

import com.dialog.PressedBackBuilder;
import com.dialog.RequisitionDelectArticleBuilder;
import com.dialog.RequisitionEditArticleBuilder;
import com.dialog.StockFilterBuilder;
import com.dialog.StockInformationViewBuilder;
import com.dialog.StockOrderBuilder;
import com.dialog.StockRequisitionListSizeZeroBuilder;
import com.logic.model.ArticleDTO;
import com.logic.model.UserDTO;
import com.preference.PreferenceStock;
import com.stockphone.R;
import com.view.stock.FrmStock;
import com.view.summary.FrmSummary;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;

public class FrmRequisition extends Activity {

	private UserDTO uDTO;
	private ListView lstvArticle;
	private RequisitionAdapter requisitionAdapter;
	private EditText txtSerchRequisitonArticle;
	private ArticleDTO articleDTO;
	private ArrayList<ArticleDTO> requisitionList;
	private final static int DIALOG_ORDER = 0;
	private final static int DIALOG_FILTER = 1;
	private final static int DIALOG_INFORMATION_VIEW = 2;
	private final static int DIALOG_EDIT_REQUISITION_LIST = 3;
	private final static int DIALOG_DELETE_ARTICLE = 4;
	private final static int DIALOG_VOID_LIST = 5;
	private final static int DIALOG_PRESSED_BACK = 6;
	private final static int POSITION_ARTICLE = 0;
	private final static int POSITION_DIALOG_NO_ADD_QUANTITY = 1;
	private final static int POSITON_DIALOG_NUM_ZERO = 2;
	private final static int POSITION_REQUISITION_LIST = 3;
	private final static int LIST_SIZE = 4;
	private AlertDialog.Builder builderNoAddQuantity;
	private AlertDialog.Builder builderNumZero;
	
	@SuppressWarnings("unchecked")
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle bundle = this.getIntent().getExtras(); 
		this.uDTO = (UserDTO) bundle.getSerializable(UserDTO.USER_SERIALIZABLE);
		this.requisitionList = (ArrayList<ArticleDTO>) bundle.getSerializable(FrmStock.REQUSITION_LIST_SERIALIZABLE);
		this.restoreMe();
		this.initComponents();
	}
	
	private void initComponents() {		
		this.setContentView(R.layout.lyt_requisition);
		this.setTitle(R.string.lblTitleRequisition);
		this.loadPreference();
		this.txtSerchRequisitonArticle = (EditText) this.findViewById(R.id.txtSerchRequsitionArticle);
		this.txtSerchRequisitonArticle.addTextChangedListener(new TextWatcher() {			
			
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				requisitionAdapter.getFilter().filter(s);
			}			
			
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {}			
			
			public void afterTextChanged(Editable s) {}
		});
		this.loadArticle();
		this.registerForContextMenu(this.lstvArticle);
	}
	
	
	public Object onRetainNonConfigurationInstance() {
		Object [] list = new Object[LIST_SIZE];
		list[POSITION_ARTICLE] = this.articleDTO;
		list[POSITION_DIALOG_NO_ADD_QUANTITY] = this.builderNoAddQuantity;
		list[POSITON_DIALOG_NUM_ZERO] = this.builderNumZero;
		list[POSITION_REQUISITION_LIST] = this.requisitionList;
		return list;
	}
	
	@SuppressWarnings("unchecked")
	private void restoreMe(){
		if(this.getLastNonConfigurationInstance() != null) {
			Object [] list = (Object []) this.getLastNonConfigurationInstance();
			for(int i = 0; i < LIST_SIZE; i++) {
				switch(i) {
					case POSITION_ARTICLE: {
						this.articleDTO = (ArticleDTO) list[i];
					} break;
					
					case POSITION_DIALOG_NO_ADD_QUANTITY: {
						this.builderNoAddQuantity = (AlertDialog.Builder) list[i];
						if(this.builderNoAddQuantity != null) {
							AlertDialog ad = this.builderNoAddQuantity.create();
							ad.setOnDismissListener(new OnDismissListener() {							
								
								public void onDismiss(DialogInterface dialog) {								
									showDialog(DIALOG_EDIT_REQUISITION_LIST);
									builderNoAddQuantity = null;
									dialog.dismiss();
								}
							});
							
							// show
							ad.show();
						}
					} break;
					
					case POSITION_REQUISITION_LIST: {
						this.requisitionList = (ArrayList<ArticleDTO>) list[i];
					} break;
					
					case POSITON_DIALOG_NUM_ZERO: {
						this.builderNumZero = (AlertDialog.Builder) list[i];
						if(this.builderNumZero != null) {
							AlertDialog ad = this.builderNumZero.create();
							ad.setOnDismissListener(new OnDismissListener() {							
								
								public void onDismiss(DialogInterface dialog) {								
									showDialog(DIALOG_EDIT_REQUISITION_LIST);
									builderNumZero = null;
									dialog.dismiss();
								}
							});
							
							// show
							ad.show();
						}
					} break;
				}
			}
		}
	}
	
	private void loadPreference() {
		PreferenceStock preferenceStock = new PreferenceStock();
		boolean initialized = preferenceStock.isInitialized(this);
		if(!initialized) {	
			preferenceStock.firstTime(this);
		}
		preferenceStock.loadPreference(this);
		
		TextView lblOrderValue = (TextView) this.findViewById(R.id.lblOrderRequisitionValue);
	
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
		
		TextView lblFilterValue = (TextView) this.findViewById(R.id.lblFilterRequsitionValue);
		
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
		this.lstvArticle = (ListView) this.findViewById(R.id.lstvRequsitionArticle);
		this.requisitionAdapter = new RequisitionAdapter(this, R.layout.lyt_requisition_item, this.requisitionList);
		this.lstvArticle.setAdapter(this.requisitionAdapter);
	}
	
	
	public boolean onCreateOptionsMenu(Menu menu) {
		boolean result = super.onCreateOptionsMenu(menu);
		MenuInflater inflater = this.getMenuInflater();
		inflater.inflate(R.menu.mn_requisition, menu);
		return result;
	}
	
	
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		
		switch(item.getItemId()) {
		
			case R.id.tmFilterRequisition: {
				this.showDialog(DIALOG_FILTER);
			} break;
			
			case R.id.tmOrderRequisiotn: {
				this.showDialog(DIALOG_ORDER);
			} break;
			
			case R.id.tmReturnFrmStock: {
				this.finish();
				Intent intent = new Intent(this, FrmStock.class);
				intent.putExtra(FrmStock.REQUSITION_LIST_SERIALIZABLE, this.requisitionList);
				intent.putExtra(UserDTO.USER_SERIALIZABLE, this.uDTO);
				startActivity(intent);
			} break;
			
			case R.id.tmConfirmListRequisition: {
				if(this.requisitionList.size() > 0) {
					this.finish();
					Intent intent = new Intent(this, FrmSummary.class);
					intent.putExtra(FrmStock.REQUSITION_LIST_SERIALIZABLE, this.requisitionList);
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
			case DIALOG_FILTER: {
				dialog = this.getDialogFilter();
			} break;
			
			case DIALOG_ORDER: {
				dialog = this.getDialogOrder();
			} break;
			
			case DIALOG_INFORMATION_VIEW: {
				dialog = this.getDialogInformationView();
			} break;
			
			case DIALOG_EDIT_REQUISITION_LIST: {
				dialog = this.getDialogEditRequisitionList();
			} break;
			
			case DIALOG_DELETE_ARTICLE: {
				dialog = this.getDeletArticle();
			} break;
			case DIALOG_VOID_LIST: {
				dialog = this.getDialogVoidList();
			} break;
			case DIALOG_PRESSED_BACK: {
				dialog = this.getDialogPressedBack();
			} break;
		}
		return dialog;
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
		StockInformationViewBuilder stockInformationViewBuilder = new StockInformationViewBuilder(this, articleDTO);
		Dialog dialog = stockInformationViewBuilder.create();
		
		dialog.setOnDismissListener(new OnDismissListener() {
			
			public void onDismiss(DialogInterface dialog) {
				removeDialog(DIALOG_INFORMATION_VIEW);
				dialog.dismiss();				
			}
		});
		return dialog;
	}
	
	private Dialog getDialogEditRequisitionList() {			
		final RequisitionEditArticleBuilder requisitionEditArticle = new RequisitionEditArticleBuilder(
				this, this.articleDTO);
		EditText txtAddArts = requisitionEditArticle.getTxtAddArts();
		final Dialog dialog = requisitionEditArticle.create();
		txtAddArts.setOnFocusChangeListener(new OnFocusChangeListener() {			
			
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
		            dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
		        }
			}
		});
		dialog.setOnDismissListener(new OnDismissListener() {			
			
			public void onDismiss(DialogInterface dialog) {		
				String quantity = requisitionEditArticle.getTxtAddArt();
				if(quantity != null) {
					if(quantity.contentEquals("")) {
						builderNoAddQuantity = new AlertDialog.Builder(FrmRequisition.this);
						builderNoAddQuantity.setTitle(R.string.lblTitleWarning);
						builderNoAddQuantity.setIcon(android.R.drawable.ic_dialog_alert);
						builderNoAddQuantity.setMessage(R.string.lblMsgWarningNoAddQuantity);
						builderNoAddQuantity.setPositiveButton(R.string.btnOk, new OnClickListener() {					
							
							public void onClick(DialogInterface dialog, int which) {
								dialog.dismiss();						
							}
						});

						// get the dialog
						AlertDialog ad = builderNoAddQuantity.create();
						ad.setOnDismissListener(new OnDismissListener() {							
							
							public void onDismiss(DialogInterface dialog) {								
								showDialog(DIALOG_EDIT_REQUISITION_LIST);
								dialog.dismiss();
							}
						});
						
						// show
						ad.show();
					} else {
						Integer num = Integer.parseInt(quantity);
						if(num > 0) {
							articleDTO.setQuantity(num);
							loadArticle();
							registerForContextMenu(lstvArticle);
							Toast toast = Toast.makeText(FrmRequisition.this,R.string.lblSummaryMessageCancelLisRequisition, Toast.LENGTH_SHORT);
							toast.setGravity(Gravity.CENTER, 0, 0);
							toast.show();
						} else {
							builderNumZero = new AlertDialog.Builder(FrmRequisition.this);
							builderNumZero.setTitle(R.string.lblTitleWarning);
							builderNumZero.setIcon(android.R.drawable.ic_dialog_alert);
							builderNumZero.setMessage(R.string.lblMsgWarningNumZero);
							builderNumZero.setPositiveButton(R.string.btnOk, new OnClickListener() {					
								
								public void onClick(DialogInterface dialog, int which) {
									dialog.dismiss();						
								}
							});

							// get the dialog
							AlertDialog ad = builderNumZero.create();
							ad.setOnDismissListener(new OnDismissListener() {							
								
								public void onDismiss(DialogInterface dialog) {								
									showDialog(DIALOG_EDIT_REQUISITION_LIST);
									dialog.dismiss();
								}
							});
							
							// show
							ad.show();
						}
					}
				}				
				removeDialog(DIALOG_EDIT_REQUISITION_LIST);
				dialog.dismiss();
			}
		});
		return dialog;
	}
	
	private Dialog getDeletArticle() {
		final RequisitionDelectArticleBuilder requisitionDelectArticleBuilder = new RequisitionDelectArticleBuilder(this, this.articleDTO);
		Dialog dialog = requisitionDelectArticleBuilder.create();
		dialog.setOnDismissListener(new OnDismissListener() {			
			
			public void onDismiss(DialogInterface dialog) {
				if(requisitionDelectArticleBuilder.getPressedButtonOPk()) {
					requisitionList.remove(articleDTO);
					loadArticle();
					registerForContextMenu(lstvArticle);
					Toast toast = Toast.makeText(FrmRequisition.this,R.string.lblMessageDeleteArticleOk, Toast.LENGTH_SHORT);
					toast.setGravity(Gravity.CENTER, 0, 0);
					toast.show();					
				} 
			}
		});
		return dialog;
	}
	
	private Dialog getDialogVoidList() {
		StockRequisitionListSizeZeroBuilder stockRequisitionListSizeZeroBuilder = new StockRequisitionListSizeZeroBuilder(this);
		Dialog dialog = stockRequisitionListSizeZeroBuilder.create();
		dialog.setOnDismissListener(new OnDismissListener() {			
			
			public void onDismiss(DialogInterface dialog) {				
				dialog.dismiss();
			}
		});
		return dialog;
	}
	
	private Dialog getDialogPressedBack() {
		PressedBackBuilder pressedBackBuilder = new PressedBackBuilder(this);
		Dialog dialog = pressedBackBuilder.create();
		dialog.setOnDismissListener(new OnDismissListener() {			
			
			public void onDismiss(DialogInterface dialog) {
				dialog.dismiss();
			}
		});
		return dialog;
	}
	
	
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) menuInfo;
		int position = info.position;
		this.articleDTO = this.requisitionAdapter.getItem(position);
		MenuInflater menuInflater = this.getMenuInflater();
		String title = this.getString(R.string.lblTitleOption);
		String cod_artilce = this.getString(R.string.lblSubTitleCodArtile)
				.concat(" ").concat(articleDTO.getCod_article());
		title = title.concat("\n").concat(cod_artilce);
		menuInflater.inflate(R.menu.mn_requisition_item_selected, menu.setHeaderTitle(title));
	}
	
	
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		int position = info.position;
		this.articleDTO = this.requisitionAdapter.getItem(position);
		int id = item.getItemId();
		switch(id) {
		
			case R.id.tm_requisition_edit_quantity: {
				this.showDialog(DIALOG_EDIT_REQUISITION_LIST);
			} break;
			
			case R.id.tm_requisition_information_view: {
				this.showDialog(DIALOG_INFORMATION_VIEW);
			} break;
			
			case R.id.tm_requisition_delet_article: {
				this.showDialog(DIALOG_DELETE_ARTICLE);
			} break;
		
		}
		return true;
	}
	
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if (keyCode == KeyEvent.KEYCODE_BACK) {
	        moveTaskToBack(false);	        
			this.showDialog(DIALOG_PRESSED_BACK);
	        return true;
	    }
	    return super.onKeyDown(keyCode, event);
	}

}
