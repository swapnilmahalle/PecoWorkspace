package com.invmgmt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.invmgmt.dao.OfferDetailDao;
import com.invmgmt.dao.ProjectDao;
import com.invmgmt.entity.OfferDetailsForm;
import com.invmgmt.entity.Project;
import com.invmgmt.pdf.ClientPdfGeneration;

@Controller
@EnableWebMvc

public class OfferController {

	@Autowired
	ClientPdfGeneration pdfClient;
	
	@Autowired
	OfferDetailDao offerDao;
	
		
	 private static String INPUTFILE = "C:/Users/Swapnil/Desktop/Projects/peco/PECO_Offer_template.pdf";
	 private static String OUTPUTFILE = "C:/Users/Swapnil/Desktop/Projects/peco/Output/PECO_Offer.pdf";
	
	@RequestMapping(value = "/generateOffer", method = {RequestMethod.POST,RequestMethod.GET})
	protected String generateOffer(OfferDetailsForm l_off) throws Exception
	{		
		System.out.println("Generating offer.");
		pdfClient.manipulatePdf(INPUTFILE, OUTPUTFILE);

		
		int project = offerDao.addOfferDetails(l_off);
		
	//	redirectAttributes.addAttribute("projectId",projectId);
	//	redirectAttributes.addAttribute("projectName",project.getProjectName());
		//redirectAttributes.addAttribute("projectDesc",project.getProjectDesc());

		
		return "redirect:/offerDetailsForm";

	}
	private static final String updateViewName = "offerDetails";

	@RequestMapping(value = "/offerDetailsForm", method = {RequestMethod.POST,RequestMethod.GET})
	protected ModelAndView updateInventoryForm() {
		ModelAndView view = new ModelAndView(updateViewName);

		return view;
	}
}
