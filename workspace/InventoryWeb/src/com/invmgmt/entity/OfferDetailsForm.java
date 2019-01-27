package com.invmgmt.entity;

import java.io.IOException;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

import com.invmgmt.pdf.ClientPdfGeneration;
import com.itextpdf.text.DocumentException;

@Entity
@Table(name="offerdetails")

public class OfferDetailsForm {

	//@Autowired
	ClientPdfGeneration var = new ClientPdfGeneration();
	
				@Id
		private String offerId;
	 	private String qty;
	    private String rate;
	    private String description;
	    private String UOM;
	    private String  amount;
	    private String  subject;
	    private String  annexure;
	    private String  delivery;
	    private String  HSN_Code;
	    private String  SAC_Code;
	    private String payTerms;
	    public void setAnnexure(String annexure) {
			this.annexure = annexure;
		}

		public void setPayTerms(String payTerms) {
			this.payTerms = payTerms;
		}

		public void setTermsConds(String termsConds) {
			this.termsConds = termsConds;
		}
		private String termsConds;
	    
	    
	    public ClientPdfGeneration getVar() {
			return var;
		}

		public void setVar(ClientPdfGeneration var) {
			this.var = var;
		}

		public String getSubject() {
			return subject;
		}

		public void setSubject(String subject) {
			this.subject = subject;
		}

		
		public String getDelivery() {
			return delivery;
		}

		public void setDelivery(String delivery) {
			this.delivery = delivery;
		}

		public String getHSN_Code() {
			return HSN_Code;
		}

		public void setHSN_Code(String hSN_Code) {
			HSN_Code = hSN_Code;
		}

		public String getSAC_Code() {
			return SAC_Code;
		}

		public void setSAC_Code(String sAC_Code) {
			SAC_Code = sAC_Code;
		}

	

		public String getINPUTFILE() {
			return INPUTFILE;
		}

		public void setINPUTFILE(String iNPUTFILE) {
			INPUTFILE = iNPUTFILE;
		}

		public String getOUTPUTFILE() {
			return OUTPUTFILE;
		}

		public void setOUTPUTFILE(String oUTPUTFILE) {
			OUTPUTFILE = oUTPUTFILE;
		}
		private  String INPUTFILE = "C:/Users/Swapnil/Desktop/Projects/peco/PECO_Offer_template.pdf";
	    private  String OUTPUTFILE = "C:/Users/Swapnil/Desktop/Projects/peco/PECO_Offer.pdf";

		
	    public void createOffer() 
	    {
			  
			    try {
					var.manipulatePdf(INPUTFILE,OUTPUTFILE);
				} catch (DocumentException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    
		}
	   
	    public String getOfferId() {
			return offerId;
		}
		public void setOfferId(String offerId) {
			this.offerId = offerId;
		}
	    public String getQty() {
			return qty;
		}
		public void setQty(String qty) {
			this.qty = qty;
		}
		public String getRate() {
			return rate;
		}
		public void setRate(String rate) {
			this.rate = rate;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getUOM() {
			return UOM;
		}
		public void setUOM(String uOM) {
			UOM = uOM;
		}
		public String getAmount() {
			return amount;
		}
		public void setAmount(String amount) {
			this.amount = amount;
		}
	    
}
