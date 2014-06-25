package com.view.main;

import java.util.ArrayList;
import android.app.Activity;
import android.app.Dialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.os.Bundle;
import android.os.Environment;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;
import com.view.about.FrmAboutStockPhone;
import com.view.login.FrmLogin;
import com.view.main.FrmMain;
import com.stockphone.R;
import com.dialog.ConfirmPressedBackOnFrmMainBuilder;
import com.dialog.ConfirmPressedExitOnFrmMain;
import com.logic.model.UserDTO;
import com.view.main.MainAdapter;
import com.view.main.MenuItem;
import com.view.stock.FrmStock;

public class FrmMain extends Activity implements OnItemClickListener {
	
	private MainAdapter mainAdapter;
	private ListView lstvMain;
	private UserDTO uDTO;
	private final static int ITEM_ARTICLE = 0;
	private final static int ITEM_ABOUT_STOCKER = 1;
	private final static int ITEM_EXIT = 2;
	private final static int DIALOG_NO_MONTED_SD = 0;
	private final static int DIALOG_PRESSED_BACK = 1;
	private final static int DIALOG_PRESSED_EXIT = 2;
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle bundle = getIntent().getExtras();
		this.uDTO = (UserDTO) bundle.getSerializable(UserDTO.USER_SERIALIZABLE);
		this.initComponents();	
	}
	
	private void initComponents() {		
		this.setContentView(R.layout.lyt_main);
		this.setTitle(R.string.lblMainMenu);
		this.loadItem();
	}
	
	private void loadItem() {
		MenuItem menu1 = new MenuItem();
		MenuItem menu2 = new MenuItem();
		MenuItem menu3 = new MenuItem();
		
		menu1.setImagenItem(R.drawable.ic_article);
		menu1.setNameItem(this.getString(R.string.lblMainArticle));
		
		menu2.setImagenItem(R.drawable.ic_about);
		menu2.setNameItem(this.getString(R.string.lblMainAboutStockPhone));
		
		menu3.setImagenItem(R.drawable.ic_exit);
		menu3.setNameItem(this.getString(R.string.lblMainExit));
		
		ArrayList<MenuItem> listMenuItem = new ArrayList<MenuItem>();
		
		listMenuItem.add(menu1);
		listMenuItem.add(menu2);
		listMenuItem.add(menu3);
		
		this.lstvMain = (ListView) this.findViewById(R.id.lstvMain);
		this.mainAdapter = new MainAdapter(listMenuItem, this);
		this.lstvMain.setAdapter(this.mainAdapter);
		this.lstvMain.setOnItemClickListener(this);			
	}
	
	
	public void onBackPressed() {
		this.showDialog(DIALOG_PRESSED_BACK);
	}

	
	public void onItemClick(AdapterView<?> list, View view, int index, long id) {
		switch(index) {		
			case ITEM_ARTICLE: {
				if(this.memorySDMonted()) {
					Intent intent = new Intent(this,FrmStock.class);
					intent.putExtra(UserDTO.USER_SERIALIZABLE, uDTO);
					startActivity(intent);
				} else {
					this.showDialog(DIALOG_NO_MONTED_SD);
				}				
			} break;
		
			case ITEM_ABOUT_STOCKER: {
				Intent intent = new Intent(FrmMain.this,FrmAboutStockPhone.class);
				startActivity(intent);
			} break;
	
			case ITEM_EXIT: {
				this.showDialog(DIALOG_PRESSED_EXIT);				
			} break;
		}	
	}
	
	private boolean memorySDMonted() {
		String state = Environment.getExternalStorageState();
		return (Environment.MEDIA_MOUNTED.equals(state));
	}
	
	
	protected Dialog onCreateDialog(int id) {
		Dialog dialog = null;
		switch(id) {
			case DIALOG_NO_MONTED_SD: {
				dialog = this.getDialogNoMontedSD();
			} break;
			case DIALOG_PRESSED_BACK: {
				dialog = this.getConfirmPressedBack();
			} break;
			case DIALOG_PRESSED_EXIT: {
				dialog = this.getConfirmPressedExit();
			} break;
		}
		return dialog;
	}
	
	private Dialog getConfirmPressedBack() {
		final ConfirmPressedBackOnFrmMainBuilder confirmPressedBackOnFrmMainBuilder = new ConfirmPressedBackOnFrmMainBuilder(this);
		Dialog dialog = confirmPressedBackOnFrmMainBuilder.create();
		dialog.setOnDismissListener(new OnDismissListener() {			
			
			public void onDismiss(DialogInterface dialog) {
				if(confirmPressedBackOnFrmMainBuilder.getPressedButtonOPk()) {
					finish();
					Intent intent = new Intent(FrmMain.this,FrmLogin.class);
					startActivity(intent);	
				} 
			}
		});
		return dialog;
	}
	
	private Dialog getConfirmPressedExit() {
		final ConfirmPressedExitOnFrmMain confirmPressedExitOnFrmMain = new ConfirmPressedExitOnFrmMain(this);
		Dialog dialog = confirmPressedExitOnFrmMain.create();
		dialog.setOnDismissListener(new OnDismissListener() {			
			
			public void onDismiss(DialogInterface dialog) {
				if(confirmPressedExitOnFrmMain.getPressedButtonOPk()) {
					finish();
					Toast toast = Toast.makeText(FrmMain.this,R.string.lblExitMenuItem, Toast.LENGTH_SHORT);
					toast.setGravity(Gravity.CENTER, 0, 0);
					toast.show();	
				} 
			}
		});
		return dialog;
	}
	
	private Dialog getDialogNoMontedSD() {
		Builder builder = new Builder(this);
		builder.setTitle(R.string.lblTitleWarning);
		builder.setIcon(android.R.drawable.ic_dialog_alert);
		builder.setMessage(R.string.lblMsgNoMemorySDMontedArticleList);
		builder.setPositiveButton(R.string.btnOk, new OnClickListener() {			
			
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();		
			}
		});
		builder.setOnCancelListener(new OnCancelListener() {			
			
			public void onCancel(DialogInterface dialog) {
				dialog.dismiss(); 		
			}
		});
		return builder.create();
	}
}
