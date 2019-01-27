package com.invmgmt.pdf;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.ManagedBean;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

@ManagedBean
public class ClientPdfGeneration {
	  
	    private static String INPUTFILE = "C:/Users/Swapnil/Desktop/Projects/peco/PECO_Offer_template.pdf";
	    private static String OUTPUTFILE = "C:/Users/Swapnil/Desktop/Projects/peco/PECO_Offer.pdf";

	    static PdfImportedPage page;
    	
	   /* public static void main(String[] args) {          
	   
	    	try {*/
			//	manipulatePdf(INPUTFILE,OUTPUTFILE);
			//} catch (DocumentException | IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
		//	}
		
	 //   public void getOfferPdf(){
		 //   List<DataObject> dataObjList = getDataObjectList();
		   //     Document document = null;
		        //PdfReader reader,reader1;
		        
		        //try {
		        //	  document = new Document();

		           //  PdfWriter writer = PdfWriter.getInstance(document,
		             //        new FileOutputStream(OUTPUTFILE));
		             //document.open();
		             // reader = new PdfReader(INPUTFILE);
		             //int n = reader.getNumberOfPages();
		             
		            /* page = writer.getImportedPage(reader, 1);
		                Image instance = Image.getInstance(page);
		                document.add(instance);
			        	PdfOfferGenerator.addContent(document, dataObjList);
				      */  
		      //  	PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(
		    //                new File(TITLE + PDF_EXTENSION)));
		         //   HeaderFooter event = new HeaderFooter();
		           
		           
		      //      reader = new PdfReader("C:/Users/Swapnil/Desktop/Projects/peco/PECO_Offer.pdf");
		    //        String textFromPage = PdfTextExtractor.getTextFromPage(reader, 1);
		  //          document = new Document(PageSize.A4);            
		//            document.open();
	//		          
//		            PdfOfferGenerator.addContent(document, dataObjList);
		        
		            
		          //  FileOutputStream fos = new FileOutputStream(new File("C:/Users/Swapnil/Desktop/Projects/peco", "GrandizerMerge.pdf")))
		         
		               // List<PdfReader> inputs = Arrays.asList(readerA, readerB, readerC);
		            //    tool.merge(fos, inputs);
		            
		       //     reader.close();
		         

		        // try {
		        /*//Document is not auto-closable hence need to close it separately
		            document = new Document(PageSize.A4);            
		            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(
		                    new File(TITLE + PDF_EXTENSION)));
		            HeaderFooter event = new HeaderFooter();
		           // String Header_Adr= "Regd. Office-A -601 ,Silver Oaks ,gat No 275 ,Borhadevadi, Moshi ,Pune -412105	"
		           // 					+"\n"+ "C:+91 20 65300352 | E: info@pecoprojects.com | CIN : U74900PN2015PTC155310";
		        	Image image1 = Image.getInstance("E:/socialAngels/Workspace/workspace/InventoryWeb/WebContent/loginPage/img/PECO_HEADER.png");
	        		
		            event.setHeader(image1);
		            writer.setPageEvent(event);
		            document.open();
		            PdfOfferGenerator.addMetaData(document, TITLE);
		            PdfOfferGenerator.addTitlePage(document, TITLE);
		            PdfOfferGenerator.addContent(document, dataObjList);
		        }catch (DocumentException | FileNotFoundException e) {
		            e.printStackTrace();
		            System.out.println("FileNotFoundException occurs.." + e.getMessage());
		        } catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
		            if(null != document){
		                document.close();
		            }
		        }
	    
		        } catch (DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally{  document.close();
			            }
		     } */  	
	    public static List<DataObject> getOfferDetails(){
	        List<DataObject> dataObjList = new ArrayList<DataObject>();
	        DataObject d1 = new DataObject();
	        d1.setDescription("Charges for Supply, Fabrication, & Installation of AHU Condensate Recovery(ACR) Project as per Annexure - II	"+'\n'+	
	        					"HSN Code for Supply:- 7304"		+'\n'+
	        					"SAC Code for Labor:- 9987");
	        d1.setUOM("UOM");
	        d1.setQty("1");
	        d1.setRate("10000");
	        d1.setAmount("10000");
	        dataObjList.add(d1);
	        return dataObjList;    
	    }
	    
	    public  void manipulatePdf(String src, String dest) throws DocumentException, IOException {
	        PdfReader reader = new PdfReader(src);
	        Rectangle pagesize = reader.getPageSize(1);
	        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
	        AcroFields form = stamper.getAcroFields();
	        List<DataObject> dataObjList = getOfferDetails();
			       Document document = null;
			        try {
			          document = new Document();

			             document.open();
			             

			        }finally{  document.close();
		            }
	        /*
	        form.setField("Name", "Jennifer");
	        form.setField("Company", "iText's next customer");
	        form.setField("Country", "No Man's Land");
	        PdfPTable table = new PdfPTable(2);
	        table.addCell("#");
	        table.addCell("description");
	        table.setHeaderRows(1);
	        table.setWidths(new int[]{ 1, 15 });
	        for (int i = 1; i <= 150; i++) {
	            table.addCell(String.valueOf(i));
	            table.addCell("test " + i);
	        }*/
			        
			        
			        
			        
	        ColumnText column = new ColumnText(stamper.getOverContent(1));
	        Rectangle rectPage1 = new Rectangle(36, 36, 559, 540);
	       column.setSimpleColumn(36, 36, 559, 540);
	        column.addElement(PdfOfferGenerator.addContent( dataObjList));
	        int pagecount = 1;
	        Rectangle rectPage2 = new Rectangle(36, 36, 559, 806);
	        int status = column.go();
	        while (ColumnText.hasMoreText(status)) {
	            status = triggerNewPage(stamper, pagesize, column, rectPage2, ++pagecount);
	        }
	        stamper.setFormFlattening(true);
	        stamper.close();
	        reader.close(); 
	    }

	    public static int triggerNewPage(PdfStamper stamper, Rectangle pagesize, ColumnText column, Rectangle rect, int pagecount) throws DocumentException {
	        stamper.insertPage(pagecount, pagesize);
	        PdfContentByte canvas = stamper.getOverContent(pagecount);
	        column.setCanvas(canvas);
	        column.setSimpleColumn(36, 36, 559, 540);
	        return column.go();
	    }
	    
	    
	    
	    
	}