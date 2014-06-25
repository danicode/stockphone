package com.view.summary;

import java.util.ArrayList;
import java.util.Date;
import com.dialog.PressedBackBuilder;
import com.dialog.SummaryConfirmRequisition;
import com.dialog.SummaryDialogCancel;
import com.dialog.SummaryErrorGenereateCSVBuilder;
import com.logic.RequisitionBO;
import com.logic.mail.Mail;
import com.logic.model.ArticleDTO;
import com.logic.model.RequisitionDTO;
import com.logic.model.UserDTO;
import com.stockphone.R;
import com.util.MyDate;
import com.view.requisition.FrmRequisition;
import com.view.requisition.RequisitionAdapter;
import com.view.stock.FrmStock;
import android.app.Activity;
import android.app.Dialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class FrmSummary extends Activity {

	private UserDTO uDTO;
	private ListView lstvArticle;
	private RequisitionAdapter requisitionAdapter;
	private ArrayList<ArticleDTO> requisitionList;
	private final static int DIALOG_CANCEL = 0;
	private final static int DIALOG_GENERATION_CSV = 1;
	private final static int DIALOG_ERROR_GENERATE_CSV = 2;
	private final static int DIALOG_NO_MONTED_SD = 4;
	private final static int DIALOG_PRESSED_BACK = 5;
	private TextView lblDate;
	private TextView lblHour;
	private final static String SEPARATE_FILE_NAME = "_";
	private final static String FILE_EXTENSION_CSV = ".csv";
	
	@SuppressWarnings("unchecked")
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle bundle = this.getIntent().getExtras(); 
		this.uDTO = (UserDTO) bundle.getSerializable(UserDTO.USER_SERIALIZABLE);
		this.requisitionList = (ArrayList<ArticleDTO>) bundle.getSerializable(FrmStock.REQUSITION_LIST_SERIALIZABLE);
		this.initComponents();
	}
	
	private void initComponents() {
		this.setContentView(R.layout.lyt_summary);
		this.setTitle(R.string.lblSummaryTitle);
		
		TextView lblUser = (TextView) this.findViewById(R.id.lblSummaryUser);
		lblUser.setText(this.uDTO.getUser());
		
		this.lblDate = (TextView) this.findViewById(R.id.lblSummaryDate);
		Date datex = new Date();
		String date = MyDate.formatDate(datex);
		this.lblDate.setText(date);
		
		String hour = String.valueOf(datex.getHours());
		String minute = String.valueOf(datex.getMinutes());
		
		hour = hour.concat(":").concat(minute);
		
		this.lblHour = (TextView) this.findViewById(R.id.lblSummaryHour);
		this.lblHour.setText(hour);
		
		this.loadArticle();
	}
	
	private void loadArticle() {
		this.lstvArticle = (ListView) this.findViewById(R.id.lstvSummaryArticle);
		this.requisitionAdapter = new RequisitionAdapter(this, R.layout.lyt_summary_item, this.requisitionList);
		this.lstvArticle.setAdapter(this.requisitionAdapter);
	}
	
	
	public boolean onCreateOptionsMenu(Menu menu) {
		boolean result = super.onCreateOptionsMenu(menu);
		MenuInflater inflater = this.getMenuInflater();
		inflater.inflate(R.menu.mn_summary, menu);
		return result;
	}
	
	
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		
		switch(item.getItemId()) {
			case R.id.tmSummaryReturnFrmStock: {
				this.finish();
				Intent intent = new Intent(this, FrmStock.class);
				intent.putExtra(FrmStock.REQUSITION_LIST_SERIALIZABLE, this.requisitionList);
				intent.putExtra(UserDTO.USER_SERIALIZABLE, this.uDTO);
				startActivity(intent);
			} break;
			
			case R.id.tmSummaryReturnFrmRequisition: {
				this.finish();
				Intent intent = new Intent(this, FrmRequisition.class);
				intent.putExtra(FrmStock.REQUSITION_LIST_SERIALIZABLE, this.requisitionList);
				intent.putExtra(UserDTO.USER_SERIALIZABLE, this.uDTO);
				startActivity(intent);
			} break;
			
			case R.id.tmSummaryGenerateCSV: {
				this.showDialog(DIALOG_GENERATION_CSV);
			} break;
				
			case R.id.tmSummaryCancelRequisition: {
				this.showDialog(DIALOG_CANCEL);
			} break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	protected Dialog onCreateDialog(int id) {
		Dialog dialog = null;
		switch(id) {
			case DIALOG_CANCEL: {
				dialog = this.getDialogCancel();
			} break;
			case DIALOG_GENERATION_CSV: {
				dialog = this.getDialogGenerateCSV();
			} break;
			case DIALOG_ERROR_GENERATE_CSV: {
				dialog = this.getDialogErrorGenerateCSV();
			} break;
			case DIALOG_NO_MONTED_SD: {
				dialog = this.getDialogNoMontedSD();
			} break;
			case DIALOG_PRESSED_BACK: {
				dialog = this.getDialogPressedBack();
			} break;
		}
		return dialog;
	}
	
	private Dialog getDialogCancel() {
		final SummaryDialogCancel summaryDialogCancel = new SummaryDialogCancel(this);
		Dialog dialog = summaryDialogCancel.create();
		dialog.setOnDismissListener(new OnDismissListener() {			
			
			public void onDismiss(DialogInterface dialog) {
				if(summaryDialogCancel.isPressedButtonOPk()) {
					Toast toast = Toast.makeText(FrmSummary.this,R.string.lblSummaryMessageCancelLisRequisition, Toast.LENGTH_SHORT);
					toast.setGravity(Gravity.CENTER, 0, 0);
					toast.show();
					finish();
				}
				dialog.dismiss();
			}
		});
		return dialog;
	}
	
	private Dialog getDialogGenerateCSV() {
		final SummaryConfirmRequisition summaryConfirmRequisition = new SummaryConfirmRequisition(this);
		Dialog dialog = summaryConfirmRequisition.create();
		dialog.setOnDismissListener(new OnDismissListener() {			
			
			public void onDismiss(DialogInterface dialog) {
				if(summaryConfirmRequisition.getPressedButtonOPk()) {
					generateCSV();
					dialog.dismiss();	
				}
			}
		});
		return dialog;
	}
	
	private Dialog getDialogErrorGenerateCSV() {
		SummaryErrorGenereateCSVBuilder summaryErrorGenereateCSVBuilder = new SummaryErrorGenereateCSVBuilder(this);
		Dialog dialog = summaryErrorGenereateCSVBuilder.create();
		dialog.setOnDismissListener(new OnDismissListener() {			
			
			public void onDismiss(DialogInterface dialog) {
				dialog.dismiss();	
			}
		});
		return dialog;
	}
	
	private Dialog getDialogNoMontedSD() {
		Builder builder = new Builder(this);
		builder.setTitle(R.string.lblTitleWarning);
		builder.setIcon(android.R.drawable.ic_dialog_alert);
		builder.setMessage(R.string.lblMsgNoMemorySDMontedCSVGenerated);
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
	
	private void generateCSV() {
		if(this.memorySDMonted()) {		
			
			RequisitionDTO requisitionDTO = new RequisitionDTO();			
			
			requisitionDTO.setArticles(this.requisitionList);
			String user = this.getString(R.string.lblUser);
			requisitionDTO.setUser(user);
			requisitionDTO.setUserDTO(this.uDTO);
			String date = this.getString(R.string.lblDate);
			requisitionDTO.setDate(date);
			String dateValue = String.valueOf(this.lblDate.getText());
			requisitionDTO.setDateValue(dateValue);
			String hour = this.getString(R.string.lblHour);
			requisitionDTO.setTime(hour);
			String hourValue = String.valueOf(this.lblHour.getText());
			requisitionDTO.setTimeValue(hourValue);
			
			String fileName = this.getString(R.string.lblSummaryTitle).concat(SEPARATE_FILE_NAME);
			String dateAux = requisitionDTO.getDateValue().concat(SEPARATE_FILE_NAME);
			String hourAux = requisitionDTO.getTimeValue().concat(FILE_EXTENSION_CSV);
			hourAux = hourAux.replace(':', '-');
			fileName = fileName.concat(dateAux).concat(hourAux);
			requisitionDTO.setFileName(fileName);
			
			String cod_art = this.getString(R.string.lblSummaryCodArticle);
			requisitionDTO.setCod_art(cod_art);
			String description = this.getString(R.string.lblSummaryDescription);
			requisitionDTO.setDescription(description);
			String provider = this.getString(R.string.lblSummaryProvider);
			requisitionDTO.setProvider(provider);
			String cod_art_prov = this.getString(R.string.lblSummaryCodArticleProvider);
			requisitionDTO.setCod_art_prov(cod_art_prov);
			String quantity = this.getString(R.string.lblSummaryQuantity);
			requisitionDTO.setQuantity(quantity);
			String articleQuantity = this.getString(R.string.lblSummaryArticleQuantity);
			requisitionDTO.setArticleQuantity(articleQuantity);
			String articleTotal = this.getString(R.string.lblSummaryTotalQuantity);
			requisitionDTO.setArticleTotal(articleTotal);
			
			RequisitionBO requisitionBO = new RequisitionBO();
			try {
				requisitionBO.generateCSV(requisitionDTO);		
				Mail mail = new Mail(requisitionDTO);
				mail.setSubject(this.getString(R.string.lblSubject));
				mail.setText(this.getString(R.string.lblText));
				Intent itSend = mail.preapareToSend();
				this.startActivity(itSend);
				
				Toast toast = Toast.makeText(this,R.string.lblMessageGenerateCSVOK, Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();				
			} catch (Exception e) {
				this.showDialog(DIALOG_ERROR_GENERATE_CSV);
				e.printStackTrace();
			} finally {
				this.finish();
			}			
		} else {
			this.showDialog(DIALOG_NO_MONTED_SD);
		}		
	}
	
	private boolean memorySDMonted() {
		String state = Environment.getExternalStorageState();
		return (Environment.MEDIA_MOUNTED.equals(state));
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
