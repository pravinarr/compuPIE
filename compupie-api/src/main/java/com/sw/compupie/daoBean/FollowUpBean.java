package com.sw.compupie.daoBean;

public class FollowUpBean {

	private int clientid;
	
	private int id;
	
	private int stage;
	
	private String date;
	
	private String accessedBy;
	
	private String notes;
	/**
	 * @return the clientid
	 */
	public int getClientid() {
		return clientid;
	}

	/**
	 * @param clientid the clientid to set
	 */
	public void setClientid(int clientid) {
		this.clientid = clientid;
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
	 * @return the stage
	 */
	public int getStage() {
		return stage;
	}

	/**
	 * @param stage the stage to set
	 */
	public void setStage(int stage) {
		this.stage = stage;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the accessedBy
	 */
	public String getAccessedBy() {
		return accessedBy;
	}

	/**
	 * @param accessedBy the accessedBy to set
	 */
	public void setAccessedBy(String accessedBy) {
		this.accessedBy = accessedBy;
	}

	/**
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * @param notes the notes to set
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}


	
}
