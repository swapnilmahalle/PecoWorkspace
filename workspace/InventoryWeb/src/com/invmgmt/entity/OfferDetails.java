package com.invmgmt.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class OfferDetails {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int docNumber;
	String date;
	String boqName;
	
	String contactName;
	String companyname;
	String address1;
	String address2;
	String address3;
	String subject;
	String project1;
	String project2;
	String Rate1;
	String Rate2;
	String Amount1;
	String Amount2;
	String Total;
	String hsbCde;
	String sacCde;
	String deliveryTrem;
	String payTerm1;
	String payTerm2;
	String payTerm3;
	String payterm4;
	String term1;
	String term2;
	String term3;
	String term4;

	public String getBoqName() {
		return boqName;
	}
	public void setBoqName(String boqName) {
		this.boqName = boqName;
	}
	/*String payTerm2;
//	String payTerm3;
	String payTerm4;
	String term1;
	String term2;
	String term3;
	String term4;
	String payTerm1;
	*/
	public int getDocNumber() {
		return docNumber;
	}
	public void setDocNumber(int docNumber) {
		this.docNumber = docNumber;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getAddress3() {
		return address3;
	}
	public void setAddress3(String address3) {
		this.address3 = address3;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getProject1() {
		return project1;
	}
	public void setProject1(String project1) {
		this.project1 = project1;
	}
	public String getProject2() {
		return project2;
	}
	public void setProject2(String project2) {
		this.project2 = project2;
	}
	public String getRate1() {
		return Rate1;
	}
	public void setRate1(String rate1) {
		Rate1 = rate1;
	}
	public String getRate2() {
		return Rate2;
	}
	public void setRate2(String rate2) {
		Rate2 = rate2;
	}
	public String getAmount1() {
		return Amount1;
	}
	public void setAmount1(String vAmount1) {
		this.Amount1 = vAmount1;
	}
	public String getAmount2() {
		return Amount2;
	}
	public void setAmount2(String amount2) {
		Amount2 = amount2;
	}
	public String getTotal() {
		return Total;
	}
	public void setTotal(String total) {
		Total = total;
	}
	public String getHsbCde() {
		return hsbCde;
	}
	public void setHsbCde(String hsbCde) {
		this.hsbCde = hsbCde;
	}
	public String getSacCde() {
		return sacCde;
	}
	public void setSacCde(String sacCde) {
		this.sacCde = sacCde;
	}
	public String getDeliveryTrem() {
		return deliveryTrem;
	}
	public void setDeliveryTrem(String deliveryTrem) {
		this.deliveryTrem = deliveryTrem;
	}
	public String getPayTerm1() {
		return payTerm1;
	}
	public void setPayTerm1(String payTerm1) {
		this.payTerm1 = payTerm1;
	}
	public String getPayTerm2() {
		return payTerm2;
	}
	public void setPayTerm2(String payTerm2) {
		this.payTerm2 = payTerm2;
	}
	public String getPayTerm3() {
		return payTerm3;
	}
	public void setPayTerm3(String payTerm3) {
		this.payTerm3 = payTerm3;
	}

	public String getPayterm4() {
		return payterm4;
	}
	public void setPayterm4(String payterm4) {
		this.payterm4 = payterm4;
	}
	public String getTerm1() {
		return term1;
	}
	public void setTerm1(String term1) {
		this.term1 = term1;
	}
	public String getTerm2() {
		return term2;
	}
	public void setTerm2(String term2) {
		this.term2 = term2;
	}
	public String getTerm3() {
		return term3;
	}
	public void setTerm3(String term3) {
		this.term3 = term3;
	}
	public String getTerm4() {
		return term4;
	}
	public void setTerm4(String term4) {
		this.term4 = term4;
	}
	


}
