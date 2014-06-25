package com.view.initial;

import java.io.File;
import com.stockphone.R;
import android.app.Activity;
import android.app.Dialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Toast;
import com.util.Directory;
import com.util.MemorySD;
import com.view.login.FrmLogin;

public class FrmInitial extends Activity {

	private final static String APP_FOLDER = "stockphone";
	private final static String DATABASE_FOLDER = "database";
	private final static String DATABASE_NAME = "db_stockphone.db";
	private final static String REQUISITION_FOLDER = "pedidos";
	private final static String PATH_DATABASE = APP_FOLDER.concat(File.separator).concat(DATABASE_FOLDER).concat(File.separator).concat(DATABASE_NAME);
	private final static int DIALOG_NO_DATABASE = 0;
	private final static int DIALOG_NO_MONTED_SD = 1;
	
	
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.lyt_initial); 
		this.setTitle(R.string.lblTitleInitial);
		this.testApp();
	}
	
	private void testApp() {
		
		if(MemorySD.memorySDMonted()) {
			if(!this.createFolderApp() && !this.createFolderDataBase()) {
				this.createFolderRequisition();
				if(this.databaseExist()) {
					this.finish();
					Intent intent = new Intent(FrmInitial.this, FrmLogin.class);
					this.startActivity(intent);
				} else {
					this.showDialog(DIALOG_NO_DATABASE);
				}
			} else {
				this.showDialog(DIALOG_NO_DATABASE);
			}
		} else {
			this.showDialog(DIALOG_NO_MONTED_SD);
		}		
	}
	
	private boolean createFolderApp() {
		String path = MemorySD.PATH_ROOT.concat(File.separator).concat(APP_FOLDER);
		return Directory.validateDir(path, Directory.VALIDATE_CREATE_DATABASE);	
	}
	
	private boolean createFolderDataBase() {
		String path = MemorySD.PATH_ROOT.concat(File.separator).concat(APP_FOLDER).concat(File.separator).concat(DATABASE_FOLDER);
		return Directory.validateDir(path, Directory.VALIDATE_CREATE_DATABASE);	
	}
	
	private boolean createFolderRequisition() {
		String path = MemorySD.PATH_ROOT.concat(File.separator).concat(APP_FOLDER).concat(File.separator).concat(REQUISITION_FOLDER);
		return Directory.validateDir(path, Directory.VALIDATE_CREATE_DATABASE);	
	}
	
	private boolean databaseExist() {
		String path = MemorySD.PATH_ROOT.concat(File.separator).concat(PATH_DATABASE);
		return Directory.fileExist(path);		
	}
	
	
	protected Dialog onCreateDialog(int id) {
		Dialog dialog = null;
		switch(id) {
			case DIALOG_NO_DATABASE: {
				dialog = this.getDialogNoDatabase();				
			} break;
			
			case DIALOG_NO_MONTED_SD: {
				dialog = this.getDialogNoMontedSD();
			} break;
		}
		return dialog;
	}
	
	private Dialog getDialogNoDatabase() {
		Builder builder = new Builder(this);
		builder.setTitle(R.string.lblTitleWarning);
		builder.setIcon(android.R.drawable.ic_dialog_alert);
		builder.setMessage(R.string.lblMsgNoDatabase);
		builder.setPositiveButton(R.string.btnOk, new OnClickListener() {			
			
			public void onClick(DialogInterface dialog, int which) {				
				Toast toast = Toast.makeText(FrmInitial.this,R.string.lblExitMenuItem, Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show(); 		
				finish();
			}
		});
		builder.setOnCancelListener(new OnCancelListener() {			
			
			public void onCancel(DialogInterface dialog) {
				Toast toast = Toast.makeText(FrmInitial.this,R.string.lblExitMenuItem, Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show(); 		
				finish();
			}
		});
		return builder.create();
	}
	
	private Dialog getDialogNoMontedSD() {
		Builder builder = new Builder(this);
		builder.setTitle(R.string.lblTitleWarning);
		builder.setIcon(android.R.drawable.ic_dialog_alert);
		builder.setMessage(R.string.lblMsgNoMemorySDMonted);
		builder.setPositiveButton(R.string.btnOk, new OnClickListener() {			
			
			public void onClick(DialogInterface dialog, int which) {
				Toast toast = Toast.makeText(FrmInitial.this,R.string.lblExitMenuItem, Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show(); 		
				finish();
			}
		});
		builder.setOnCancelListener(new OnCancelListener() {			
			
			public void onCancel(DialogInterface dialog) {
				Toast toast = Toast.makeText(FrmInitial.this,R.string.lblExitMenuItem, Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show(); 		
				finish();
			}
		});
		return builder.create();
	}
	
	
	public void onBackPressed() {
		super.onBackPressed();
		Toast toast = Toast.makeText(FrmInitial.this,R.string.lblExitMenuItem, Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show(); 		
		finish();
	}
}
