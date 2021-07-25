package com.stackroute.newz.model;

import java.util.Date;

public class NewsSource {
	
	/*
	 * This class should have five fields
	 * (newssourceId,newssourceName,newssourceDesc,newssourceCreatedBy,newssourceCreationDate).
	 * This class should also contain the getters and setters for the 
	 * fields along with the parameterized	constructor and toString method.
	 * The value of newssourceCreationDate should not be accepted from the user but should be
	 * always initialized with the system date.
	 */
	
    private String newssourceId;
	private String newssourceName;
	private String newssourceDesc;
	private String newssourceCreatedBy;
	private Date newssourceCreationDate;
	
	 public NewsSource() {
			
		}
		
		public NewsSource(String sourceId,String sourceName,String sourceDesc,String sourceCreatedBy) {
			this.newssourceId = sourceId;
			this.newssourceName = sourceName;
			this.newssourceDesc = sourceDesc;
			this.newssourceCreatedBy = sourceCreatedBy;
			this.newssourceCreationDate = new Date();
		}

	public String getNewsSourceId() {
		return newssourceId;
	}

	public void setNewsSourceId(String newssourceId) {
		this.newssourceId = newssourceId;
	}

	public String getNewsSourceName() {
		return newssourceName;
	}

	public void setNewsSourceName(String newssourceName) {
		this.newssourceName = newssourceName;
	}

	public String getNewsSourceDesc() {
		return newssourceDesc;
	}

	public void setNewsSourceDesc(String newssourceDesc) {
		this.newssourceDesc = newssourceDesc;
	}

	public String getNewsSourceCreatedBy() {
		return newssourceCreatedBy;
	}

	public void setNewsSourceCreatedBy(String newssourceCreatedBy) {
		this.newssourceCreatedBy = newssourceCreatedBy;
	}

	public Date getNewsSourceCreationDate() {
		return newssourceCreationDate;
	}

	public void setNewsSourceCreationDate() {
		this.newssourceCreationDate = new Date();
	}

	@Override
	public String toString() {
		return "NewsSource [newssourceId=" + newssourceId + ", newssourceName=" + newssourceName + ", newssourceDesc="
				+ newssourceDesc + ", newssourceCreatedBy=" + newssourceCreatedBy + ", newssourceCreationDate="
				+ newssourceCreationDate + "]";
	}

}
