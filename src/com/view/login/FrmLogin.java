package com.view.login;

import android.app.Activity;
import android.app.Dialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.os.Environment;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.stockphone.R;
import com.view.login.FrmLogin;
import com.view.main.FrmMain;
import com.logic.UserBO;
import com.logic.model.UserDTO;

public class FrmLogin extends Activity {

	private EditText txtUser;
	private EditText txtPassword;
	private TextView lblErrorLogin;	
	private final static int DIALOG_NO_MONTED_SD = 0;
	
	
	protected void onCreate(Bundle savedInstanceState) { 		
		super.onCreate(savedInstanceState);		
		this.initComponents();	
	}
	
	
	public Object onRetainNonConfigurationInstance() {		
		String content = this.lblErrorLogin.getText().toString();
		return content;
	}
	
	private void restoreMe() {	
		if(this.getLastNonConfigurationInstance() != null) {	
			String content = String.valueOf(this.getLastNonConfigurationInstance());
			this.lblErrorLogin.setVisibility(View.VISIBLE);
			this.lblErrorLogin.setText(content);
		}
	}
	
	/**
	 *	@Summary method for initialize the activity  
	 */
	private void initComponents() {		
		this.setContentView(R.layout.lyt_login); 
		this.setTitle(R.string.lblLogin);
		this.txtUser = (EditText) this.findViewById(R.id.txtUser);		
	    this.txtPassword = (EditText) this.findViewById(R.id.txtPassword);
		this.lblErrorLogin = (TextView) this.findViewById(R.id.lblErrorlogin);
		this.restoreMe();
	}
	
	public void btnOkSetOnClickListener(View button) {
		String user = this.txtUser.getText().toString().trim();			
		String password = this.txtPassword.getText().toString().trim();
		
		if(user.contentEquals("") && password.contentEquals("")) {			
			
			this.lblErrorLogin.setVisibility(View.VISIBLE);
			this.lblErrorLogin.setText(R.string.lblErrorLoginUserPassword);
		} else if(user.contentEquals("")) {
			
			this.lblErrorLogin.setVisibility(View.VISIBLE);
			this.lblErrorLogin.setText(R.string.lblErrorLoginUser);
		} else if(password.contentEquals("")) {
			
			this.lblErrorLogin.setVisibility(View.VISIBLE);
			this.lblErrorLogin.setText(R.string.lblErrorLoginPassword);
		} else {
			UserBO uBO = new UserBO();
			UserDTO uDTO = new UserDTO();
			
			uDTO.setUser(user);
			uDTO.setPassword(password);
			
			try {
				if(this.memorySDMonted()) {
					if(uBO.login(uDTO)) {							
						this.finish(); 						
						Intent intent = new Intent(FrmLogin.this, FrmMain.class);
						Toast toast = Toast.makeText(FrmLogin.this,R.string.lblOkLogin,Toast.LENGTH_SHORT); 
						toast.setGravity(Gravity.CENTER, 0, 0);
						toast.show();
						intent.putExtra(UserDTO.USER_SERIALIZABLE, uDTO);				 
						this.startActivity(intent);	
					} else {							
						this.lblErrorLogin.setVisibility(View.VISIBLE);
						this.lblErrorLogin.setText(R.string.lblErrorLogin);
					}
				} else {
					this.showDialog(DIALOG_NO_MONTED_SD);
				}							
			} catch (Exception e) {
				e.printStackTrace();
			}					
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
		}
		return dialog;
	}
	
	private Dialog getDialogNoMontedSD() {
		Builder builder = new Builder(this);
		builder.setTitle(R.string.lblTitleWarning);
		builder.setIcon(android.R.drawable.ic_dialog_alert);
		builder.setMessage(R.string.lblMsgNoMemorySDMontedLogin);
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
	
	public void btnCancelSetOnClickListener(View button) {
		Toast toast = Toast.makeText(this.getBaseContext(),R.string.lblExitMenuItem, Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
		this.finishFromChild(FrmLogin.this); 
		this.finish();		
	}

	
	public void onBackPressed() {
		super.onBackPressed();
		Toast toast = Toast.makeText(this.getBaseContext(),R.string.lblExitMenuItem, Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	}
}
