package com.invmgmt.util;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.annotation.ManagedBean;

import org.apache.pdfbox.contentstream.operator.Operator;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSString;
import org.apache.pdfbox.pdfparser.PDFStreamParser;
import org.apache.pdfbox.pdfwriter.ContentStreamWriter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.springframework.util.ResourceUtils;

import com.invmgmt.entity.OfferDetails;

@ManagedBean
public class Principal {
    // public static final String SRC =
    // "C:/Users/Uday/Desktop/Projects/Humdule/NewFolder/Invoice_No-HI-073.pdf";
    public static final String DESTINATION = System.getProperty("java.io.tmpdir") + "/TaxInvoice.pdf";

  /*  public static void main(String[] args) {
    	Principal  p = new Principal();
	p.createInvoice();
    }
    */
    public boolean createOffer(OfferDetails offerdetails) {
	PDDocument document = null;
	boolean invoiceGenerated = false;
	try {
	    File file = ResourceUtils.getFile("classpath:OfferTemplate.pdf");

	    document = PDDocument.load(file);
	   
	    document = replaceText(document, "Doc No. docNumber", String.valueOf(offerdetails.getDocNumber()));
	    document = replaceText(document, "dateVal",offerdetails.getDate());
	    document = replaceText(document, "contactName", offerdetails.getContactName());
	    document = replaceText(document, "companyName", offerdetails.getCompanyname());
	    document = replaceText(document, "address1", offerdetails.getAddress1());
	    document = replaceText(document, "address2", offerdetails.getAddress2());
	    document = replaceText(document, "address3", offerdetails.getAddress3());
	    document = replaceText(document, "subjectVal",offerdetails.getSubject());
	    document = replaceText(document, "Annexure-I (project1)", offerdetails.getProject1());
	    document = replaceText(document, "Annexure-II (project2)", "Annexure-II ");
	    document = replaceText(document, "Rate1", offerdetails.getRate1());
	    document = replaceText(document, "Rate2", "");
	    document = replaceText(document, "Amount1", offerdetails.getAmount1());
	    document = replaceText(document, "Amount2", "");
	    document = replaceText(document, "Curr", "INR");
	    document = replaceText(document, "totalVal", offerdetails.getTotal());
	    document = replaceText(document, "hsnCde", offerdetails.getHsbCde());
	    document = replaceText(document, "sacCde", offerdetails.getSacCde());
	    document = replaceText(document, "deliveryTerm", offerdetails.getDeliveryTrem());
	    document = replaceText(document, "payTerm1", offerdetails.getPayTerm1());
	    document = replaceText(document, "payTerm2", offerdetails.getPayTerm2());
	    document = replaceText(document, "term1", offerdetails.getTerm1());
	    document = replaceText(document, "term2", offerdetails.getTerm2());
	    document = replaceText(document, "term3", offerdetails.getTerm3());
	    document = replaceText(document, "term4", offerdetails.getTerm4());	    
	    document.save(DESTINATION);
	    document.close();

	    invoiceGenerated = true;
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
	return invoiceGenerated;
    }

    private PDDocument replaceText(PDDocument document, String searchString, String replacement) throws IOException {

	for (PDPage page : document.getPages()) {
	    System.out.println(page.getContents());
	    PDFStreamParser parser = new PDFStreamParser(page);
	    parser.parse();
	    List<?> tokens = parser.getTokens();

	    for (int j = 0; j < tokens.size(); j++) {
		Object next = tokens.get(j);
		if (next instanceof Operator) {
		    Operator op = (Operator) next;

		    String pstring = "";
		    int prej = 0;

		    if (op.getName().equals("Tj")) {
			COSString previous = (COSString) tokens.get(j - 1);
			String string = previous.getString();
			string = string.replaceFirst(searchString, replacement);
			previous.setValue(string.getBytes());
		    } else if (op.getName().equals("TJ")) {
			COSArray previous = (COSArray) tokens.get(j - 1);
			for (int k = 0; k < previous.size(); k++) {
			    Object arrElement = previous.getObject(k);
			    if (arrElement instanceof COSString) {
				COSString cosString = (COSString) arrElement;
				String string = cosString.getString();

				if (j == prej) {
				    pstring += string;
				} else {
				    prej = j;
				    pstring = string;
				}
			    }
			}

			if (searchString.equals(pstring.trim())) {
			    COSString cosString2 = (COSString) previous.getObject(0);
			    cosString2.setValue(replacement.getBytes());

			    int total = previous.size() - 1;
			    for (int k = total; k > 0; k--) {
				previous.remove(k);
			    }
			}
		    }
		}
	    }
	    PDStream updatedStream = new PDStream(document);
	    OutputStream out = updatedStream.createOutputStream(COSName.FLATE_DECODE);
	    ContentStreamWriter tokenWriter = new ContentStreamWriter(out);
	    tokenWriter.writeTokens(tokens);
	    out.close();
	    page.setContents(updatedStream);
	}

	return document;
    }
}