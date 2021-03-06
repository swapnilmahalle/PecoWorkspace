package com.invmgmt.entity;

import java.io.File;
import java.io.IOException;

import javax.annotation.ManagedBean;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.invmgmt.dao.TaxInvoiceDetailsDao;
import com.invmgmt.util.EmailUtils;
import com.invmgmt.util.Principal;

@ManagedBean
public class TaxInvoiceGenerator {

    @Autowired
    TaxInvoiceDetailsDao taxInvoiceDetailsDao;
    
    @Autowired
    Principal invoiceGenerator;
    
    @Autowired
    EmailUtils emailUtils;
    
    public void generateAndSendTaxInvoice(TaxInvoiceDetails taxInvoiceDetails) {
	
	taxInvoiceDetailsDao.saveTaxIvoice(taxInvoiceDetails);

	//invoiceGenerator.createInvoice(taxInvoiceDetails);
	
	emailUtils.sendMessageWithAttachment(taxInvoiceDetails.getEmailAddress(),taxInvoiceDetails.getTaxInvoiceNo());
	
	try 
	{
	    FileUtils.forceDelete(new File(System.getProperty("java.io.tmpdir") + "/TaxInvoice.pdf"));
	}
	catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    
    
}
