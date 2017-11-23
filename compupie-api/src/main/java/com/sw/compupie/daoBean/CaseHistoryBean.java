package com.sw.compupie.daoBean;

public class CaseHistoryBean {

	private int id;
	private int clientId;
	private String reasonForRefer;
	private String currentSituation;
	private String relevantHistory;
	private String traumaHistory;
	private String notes;
	
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
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
	 * @param clientId2 the clientId to set
	 */
	public void setClientId(int clientId2) {
		this.clientId = clientId2;
	}
	/**
	 * @return the reasonForRefer
	 */
	public String getReasonForRefer() {
		return reasonForRefer;
	}
	/**
	 * @param reasonForRefer the reasonForRefer to set
	 */
	public void setReasonForRefer(String reasonForRefer) {
		this.reasonForRefer = reasonForRefer;
	}
	/**
	 * @return the currentSituation
	 */
	public String getCurrentSituation() {
		return currentSituation;
	}
	/**
	 * @param currentSituation the currentSituation to set
	 */
	public void setCurrentSituation(String currentSituation) {
		this.currentSituation = currentSituation;
	}
	/**
	 * @return the relevantHistory
	 */
	public String getRelevantHistory() {
		return relevantHistory;
	}
	/**
	 * @param relevantHistory the relevantHistory to set
	 */
	public void setRelevantHistory(String relevantHistory) {
		this.relevantHistory = relevantHistory;
	}
	/**
	 * @return the traumaHistory
	 */
	public String getTraumaHistory() {
		return traumaHistory;
	}
	/**
	 * @param traumaHistory the traumaHistory to set
	 */
	public void setTraumaHistory(String traumaHistory) {
		this.traumaHistory = traumaHistory;
	}
}
