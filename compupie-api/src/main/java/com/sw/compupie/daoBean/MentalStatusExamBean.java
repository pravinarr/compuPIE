package com.sw.compupie.daoBean;

import java.util.ArrayList;
import java.util.List;

public class MentalStatusExamBean {

	private int id;
	
	private int clientId;
	
	private List<MentalStatusObject> problemStr = new ArrayList<MentalStatusObject>();

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the clientId
	 */
	public int getClientId() {
		return clientId;
	}

	/**
	 * @param clientId the clientId to set
	 */
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	/**
	 * @return the problemStr
	 */
	public List<MentalStatusObject> getProblemStr() {
		return problemStr;
	}

	/**
	 * @param problemStr the problemStr to set
	 */
	public void setProblemStr(List<MentalStatusObject> problemStr) {
		this.problemStr = problemStr;
	}
	
	
	
	
}
