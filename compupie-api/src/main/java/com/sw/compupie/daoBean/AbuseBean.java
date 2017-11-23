package com.sw.compupie.daoBean;

public class AbuseBean {

	private String past30;

	private String problem;
	
	private String lifetime;

	public String getPast30() {
		return past30;
	}
	

	public String getProblem() {
		return problem;
	}


	public void setProblem(String problem) {
		this.problem = problem;
	}


	public void setPast30(String past30) {
		this.past30 = past30;
	}

	public String getLifetime() {
		return lifetime;
	}

	public void setLifetime(String lifetime) {
		this.lifetime = lifetime;
	}

}
