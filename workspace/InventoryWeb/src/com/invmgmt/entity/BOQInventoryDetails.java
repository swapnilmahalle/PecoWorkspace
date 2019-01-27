package com.invmgmt.entity;

public class BOQInventoryDetails {

	private String standardType;
	private String grade;
	private String schedule;
	private String materialSpec;
	private String ends;
	private String size;
	private String availableQuantity;
	private String requiredQuantity;
	private String netQuantity;
	private String purchaseRate;
	private String supplyRate;
	
	@Override
	public String toString() {
		return "BOQInventoryDetails [standardType=" + standardType + ", grade=" + grade + ", schedule=" + schedule
				+ ", materialSpec=" + materialSpec + ", ends=" + ends + ", size=" + size + ", availableQuantity="
				+ availableQuantity + ", requiredQuantity=" + requiredQuantity + ", netQuantity=" + netQuantity
				+ ", purchaseRate=" + purchaseRate + ", supplyRate=" + supplyRate + "]";
	}

	public BOQInventoryDetails()
	{
		
	}
	
	public BOQInventoryDetails(String standardType, String grade, String schedule, String materialSpec, String ends,
			String size, String availableQuantity, String requiredQuantity, String netQuantity, String purchaseRate,
			String supplyRate) {
		super();
		this.standardType = standardType;
		this.grade = grade;
		this.schedule = schedule;
		this.materialSpec = materialSpec;
		this.ends = ends;
		this.size = size;
		this.availableQuantity = availableQuantity;
		this.requiredQuantity = requiredQuantity;
		this.netQuantity = netQuantity;
		this.purchaseRate = purchaseRate;
		this.supplyRate = supplyRate;
	}


	public String getStandardType() {
		return standardType;
	}


	public void setStandardType(String standardType) {
		this.standardType = standardType;
	}


	public String getGrade() {
		return grade;
	}


	public void setGrade(String grade) {
		this.grade = grade;
	}


	public String getSchedule() {
		return schedule;
	}


	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}


	public String getMaterialSpec() {
		return materialSpec;
	}


	public void setMaterialSpec(String materialSpec) {
		this.materialSpec = materialSpec;
	}


	public String getEnds() {
		return ends;
	}


	public void setEnds(String ends) {
		this.ends = ends;
	}


	public String getSize() {
		return size;
	}


	public void setSize(String size) {
		this.size = size;
	}


	public String getAvailableQuantity() {
		return availableQuantity;
	}


	public void setAvailableQuantity(String availableQuantity) {
		this.availableQuantity = availableQuantity;
	}


	public String getRequiredQuantity() {
		return requiredQuantity;
	}


	public void setRequiredQuantity(String requiredQuantity) {
		this.requiredQuantity = requiredQuantity;
	}


	public String getNetQuantity() {
		return netQuantity;
	}


	public void setNetQuantity(String netQuantity) {
		this.netQuantity = netQuantity;
	}


	public String getPurchaseRate() {
		return purchaseRate;
	}


	public void setPurchaseRate(String purchaseRate) {
		this.purchaseRate = purchaseRate;
	}


	public String getSupplyRate() {
		return supplyRate;
	}


	public void setSupplyRate(String supplyRate) {
		this.supplyRate = supplyRate;
	}
	
	
}
