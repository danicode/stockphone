package com.logic;

import com.dao.FileDAORequisition;
import com.logic.model.RequisitionDTO;

public class RequisitionBO {
	public void generateCSV(RequisitionDTO requisitionDTO) throws Exception {
		FileDAORequisition daoRequisition = new FileDAORequisition();
		daoRequisition.generationCSV(requisitionDTO);
	}
}
