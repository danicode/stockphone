package com.logic.mail;

import java.io.File;
import com.dao.FileDAORequisition;
import com.logic.model.RequisitionDTO;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;

public class Mail {

	private RequisitionDTO requisitionDTO;
	private String subject;
	private String text;
	
	public Mail(RequisitionDTO requisitionDTO) {
		this.requisitionDTO = requisitionDTO;
	}
	
	public Intent preapareToSend() {
		String fileName = requisitionDTO.getFileName();
		String pathRoot = Environment.getExternalStorageDirectory().getPath();
		pathRoot = pathRoot.concat(File.separator).concat(FileDAORequisition.APP_FOLDER);
		File file = new File(pathRoot,fileName);
    	Uri U = Uri.fromFile(file);
		
		Intent itSend = new Intent(Intent.ACTION_SEND);
		itSend.setType("plain/text");
		itSend.setType("application/csv");
		itSend.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{requisitionDTO.getUserDTO().getEmail()});                            
        itSend.putExtra(android.content.Intent.EXTRA_SUBJECT, this.getSubject());
        itSend.putExtra(android.content.Intent.EXTRA_TEXT, this.getText());    	
    	itSend.putExtra(Intent.EXTRA_STREAM, U);
    	return itSend;    	
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}	
}
