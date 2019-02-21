package com.invmgmt.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.invmgmt.dao.BOQDetailsDao;
import com.invmgmt.dao.OfferDetailDao;
import com.invmgmt.entity.OfferDetails;
import com.invmgmt.entity.OfferGenerator;

@Controller
@EnableWebMvc

public class OfferController {

	@Autowired
	OfferGenerator offergenerator;
	
	@Autowired
	OfferDetailDao offerDao;
	
	@Autowired
	BOQDetailsDao boqDao;
	
	
	 private static String INPUTFILE = "C:/Users/Swapnil/Desktop/Projects/peco/PECO_Offer_template.pdf";
	 private static String OUTPUTFILE = "C:/Users/Swapnil/Desktop/Projects/peco/Output/PECO_Offer.pdf";
	
	@RequestMapping(value = "/generateOffer", method = {RequestMethod.POST,RequestMethod.GET})
	protected String generateOffer(OfferDetails offerDetailsForm) throws Exception
	{		
		System.out.println("Generating offer.");
		Float rates =(float) 0;
		/*ArrayList<String> boqNameList = boqDao.getAssociatedBOQNames(offerDetailsForm.getProject1());
		
		StringBuffer boqNameListString = new StringBuffer();
		
		for(String name : boqNameList)
		{
			boqNameListString.append(name+",");
		}
	*/
		
	/*	rates = offerDao.getRates(offerDetailsForm.getProject1());
	//	pdfClient.manipulatePdf(INPUTFILE, OUTPUTFILE ,offerDetailsForm);
		
		offerDetailsForm.setRate1(rates.toString());
		offerDetailsForm.setTotal(rates.toString());
		*/
		offergenerator.generateOffer(offerDetailsForm);
		int project = offerDao.addOfferDetails(offerDetailsForm);
		
		
	//	redirectAttributes.addAttribute("projectId",projectId);
	//	redirectAttributes.addAttribute("projectName",project.getProjectName());
		//redirectAttributes.addAttribute("projectDesc",project.getProjectDesc());

		
		return "redirect:/offerDetailsForm";

	}
	private static final String updateViewName = "offerDetails";

	@RequestMapping(value = "/offerDetailsForm", method = {RequestMethod.POST,RequestMethod.GET})
	protected ModelAndView updateInventoryForm(String projectId) {
		ModelAndView view = new ModelAndView(updateViewName);
		
		ArrayList<String> boqNameList = boqDao.getAssociatedBOQNames(projectId);
		
		StringBuffer boqNameListString = new StringBuffer();
		
		for(String name : boqNameList)
		{
			boqNameListString.append(name+",");
		}
		
		view.addObject("boqNameList", boqNameListString);
		
		return view;
	}
	
	/*@RequestMapping(value = "/getRates", method = RequestMethod.POST)
	protected float getRates(String boqName) throws Exception {

	//	List<Project> projectList = null;
		float rates =(float) 0;
		//StringBuilder projectRows = new StringBuilder();
		rates = offerDao.getRates(boqName);

		//ModelAndView mav = new ModelAndView(searchProjectviewName);

		for (int i = 0; i < projectList.size(); i++) {
			String projectRowSingle = projectRow;

			projectRowSingle = projectRowSingle.replace("projectNameVal", projectList.get(i).getProjectName());
			projectRowSingle = projectRowSingle.replace("projectDescVal", projectList.get(i).getProjectDesc());
			projectRowSingle = projectRowSingle.replace("projectIdVal", String.valueOf(projectList.get(i).getProjectId()));
			
			projectRows.append(projectRowSingle);

		}
	//	mav.addObject("rates", projectRows);

		return rates;
	}*/

}
