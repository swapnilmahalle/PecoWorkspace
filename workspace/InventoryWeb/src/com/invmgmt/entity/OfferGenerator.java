package com.invmgmt.entity;

import javax.annotation.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;

import com.invmgmt.dao.OfferDetailDao;
import com.invmgmt.util.EmailUtils;
import com.invmgmt.util.Principal;

@ManagedBean
public class OfferGenerator {
	    @Autowired
	    OfferDetailDao offerDetailsDao;
	    
	    @Autowired
	    Principal offerGen;
	    
	    @Autowired
	    EmailUtils emailUtils;
	    
	    public void generateOffer(OfferDetails offerDetails) {
	    	Float rates =(float) 0;
			//StringBuilder projectRows = new StringBuilder();
			rates = offerDetailsDao.getRates(offerDetails.getProject1());
	    	
	    	//offerDetailsDao.(taxInvoiceDetails);
			offerDetails.setRate1(rates.toString());
			offerDetails.setTotal(rates.toString());
			
			offerGen.createOffer(offerDetails);
		
	//	emailUtils.sendMessageWithAttachment(taxInvoiceDetails.getEmailAddress(),taxInvoiceDetails.getTaxInvoiceNo());
		
	/*	try 
		{
		    FileUtils.forceDelete(new File(System.getProperty("java.io.tmpdir") + "/TaxInvoice.pdf"));
		}
		catch (IOException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
	*/    }

	    
}

	
	
