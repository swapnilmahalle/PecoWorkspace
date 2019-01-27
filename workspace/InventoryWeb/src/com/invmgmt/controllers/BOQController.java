package com.invmgmt.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.invmgmt.dao.BOQDetailsDao;
import com.invmgmt.dao.InventoryDao;
import com.invmgmt.dao.InventoryDefinitionDao;
import com.invmgmt.dao.ProjectDao;
import com.invmgmt.entity.BOQDetails;
import com.invmgmt.entity.Inventory;
import com.invmgmt.entity.Project;
import com.invmgmt.excel.ExcelReader;
import com.invmgmt.excel.ExcelWriter;

@Controller
@EnableWebMvc
public class BOQController {

	@Autowired
	ExcelReader reader;

	@Autowired
	ExcelWriter writer;
	
	@Autowired
	InventoryDefinitionDao inventoryDefinitionDao;
	
	@Autowired
	InventoryDao inventoryDao; 

	@Autowired
	BOQDetailsDao boqDao;

	@Autowired
	ProjectDao projectDao;
	
	private static final String createBOQ = "createBOQ";


	@RequestMapping(value = "/createBOQ", method = RequestMethod.POST)
		protected ModelAndView createBOQ(String projectId) throws Exception {
			
			System.out.println("create BOQ");
					ModelAndView mav = new ModelAndView(createBOQ);
			System.out.println("BOQ created ");
			mav.addObject("page","createBOQ");
			mav.addObject("projectId", projectId);
			return mav;
		}
		
	@RequestMapping(value = "/import", method = RequestMethod.POST)
	public @ResponseBody String getBOQLine(@RequestParam(value = "location", required = true) String location) {

		List inventoryList = null;
		try {
			inventoryList = reader.readFile(location);
		} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String tableContent = createBOQContent(inventoryList);
		return tableContent;
	}

	
	
	@RequestMapping(value = "/showInventory", method = RequestMethod.POST)
	public @ResponseBody String getAvailableInventory() {

		List inventoryList = null;
		try {
			inventoryList = inventoryDao.getAvailableInventory();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String tableContent = createInvtTable(inventoryList);
		return tableContent;
	}
	@RequestMapping(value = "/generate", method = RequestMethod.POST)
	protected String generateBOQ(String projectId, String boqName, String[] standardType, String[] grade, String[] schedule, String[] materialSpec,
			String[] ends, String[] size, String[] quantity, String[] supplyRate, String[] erectionRate,
			String[] supplyAmount, String[] erectionAmount, RedirectAttributes redirectAttributes) 
	{
		ArrayList<String> boqRevisions = boqDao.getMatchingBOQNames(boqName+"_R");

		String boqNameRevisionStr = "";
		
		for(int i=1;i<20;i++)
		{
			boqNameRevisionStr = boqName+"_R"+String.valueOf(i);
			if(boqRevisions.contains(boqNameRevisionStr))
				continue;
			else
				break;
		}

		ArrayList<BOQDetails> boqInventoryDetails = getBOQDetailsList(projectId,
				boqName,
				standardType,
				grade,
				schedule,
				materialSpec,
				ends,
				size,
				quantity,
				supplyRate,
				erectionRate,
				supplyAmount,
				erectionAmount);
		
		try 
		{
			writer.writeExcel(boqInventoryDetails);
			
			for (BOQDetails boqDetails : boqInventoryDetails) 
			{
				boqDao.saveBOQ(boqDetails);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Project project = projectDao.getProject(Integer.valueOf(projectId));
		
		redirectAttributes.addAttribute("projectId",projectId);
		redirectAttributes.addAttribute("projectName",project.getProjectName());
		redirectAttributes.addAttribute("projectDesc",project.getProjectDesc());

		
		return "redirect:/projectDetails";
	}

	
	private ArrayList<BOQDetails> getBOQDetailsList(String projectId, String boqName, String[] standardType, String[] grade, String[] schedule,
			String[] materialSpec, String[] ends, String[] size, String[] quantity, String[] supplyRate,
			String[] erectionRate, String[] supplyAmount, String[] erectionAmount) 
	{
		int noOfEntries = standardType.length;
		ArrayList<BOQDetails> boqInventoryDetails = new ArrayList<>();
		
		for (int i = 0; i < noOfEntries; i++) 
		{
			boqInventoryDetails.add(new BOQDetails(projectId,
					boqName,
					standardType[i],
					grade[i],
					schedule[i],
					materialSpec[i],
					ends[i],
					size[i],
					quantity[i],
					supplyRate[i],
					erectionRate[i],
					supplyAmount[i],
					erectionAmount[i]));
		System.out.println(boqInventoryDetails.get(i).toString());		
		}
		
		return boqInventoryDetails;
	}

	@RequestMapping(value = "/getDropdown", method = RequestMethod.POST)
	public @ResponseBody String getNextDropDownContent(@RequestParam(value = "value", required = true) String value,
			@RequestParam(value = "currentTag", required = true) String currentTag,
			@RequestParam(value = "nextTagName", required = true) String nextTagName) {

		StringBuilder dropdownContent = new StringBuilder();

		try {

			ArrayList<String> results = (ArrayList<String>) inventoryDefinitionDao.getAssociatedOptions(currentTag,
					value, nextTagName);

			for (int i = 0; i < results.size(); i++) {
				String option =  String.valueOf(results.get(i));
				
				dropdownContent.append("<option value=\"" + option + "\">" + option + "</option>" + "\n");

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dropdownContent.toString();
	}

	private String createBOQContent(List<Inventory> inventoryList) {

		StringBuilder tableContent = new StringBuilder();

		for (Inventory inv : inventoryList) {

			tableContent.append(createInventoryRow(inv));
		}

		return tableContent.toString();
	}

	private String createInventoryRow(Inventory inv) {
		String template = "<tr>" 
				+ "    <td><input type=\"text\" name=\"standardType\" value=\"standardType\" /></td>"
				+ "    <td><input type=\"text\" name=\"grade\" value=\"grade\" /></td>"
				+ "    <td><input type=\"text\" name=\"schedule\" value=\"schedule\" /></td>"
				+ "    <td><input type=\"text\" name=\"materialSpec\" value=\"materialSpec\" /></td>"
				+ "    <td><input type=\"text\" name=\"ends\" value=\"ends\" /></td>"
				+ "    <td><input type=\"text\" name=\"size\" value=\"size\" /></td>"
				+ "    <td><input type=\"text\" name=\"availableQuantity\" value=\"availableQuantity\" /></td>"
				+ "    <td><input type=\"text\" name=\"requiredQuantity\" value=\"requiredQuantity\" /></td>"
				+ "    <td><input type=\"text\" name=\"netQuantity\" value=\"netQuantity\" /></td>"
				+ "    <td><input type=\"text\" name=\"purchaseRate\" value=\"purchaseRate\" /></td>"
				+ "    <td><input type=\"text\" name=\"supplyRate\" value=\"65\" /></td>" + "</tr>";

		String rowToReturn = template;
		rowToReturn = rowToReturn.replace("value=\"standardType", "value=\""+inv.getInventorySpec().getStandardType());
		rowToReturn = rowToReturn.replace("value=\"grade", "value=\""+inv.getInventorySpec().getGrade());
		rowToReturn = rowToReturn.replace("value=\"schedule", "value=\""+inv.getInventorySpec().getSchedule());
		rowToReturn = rowToReturn.replace("value=\"materialSpec", "value=\""+inv.getInventorySpec().getMaterialSpec());
		rowToReturn = rowToReturn.replace("value=\"ends", "value=\""+inv.getInventorySpec().getEnds());
		rowToReturn = rowToReturn.replace("value=\"size", "value=\""+Integer.toString(inv.getInventorySpec().getSize()));
		
		//check available quantity		
		int availableQuantity = inventoryDao.getAvailableQuantity(inv);
		
		String netQuantity = Integer.toString(inv.getQuantity()- availableQuantity);
		
		String requiredQuantity = Integer.toString(inv.getQuantity());
		rowToReturn = rowToReturn.replace("value=\"availableQuantity", "value=\""+Integer.toString(availableQuantity));
		rowToReturn = rowToReturn.replace("value=\"requiredQuantity", "value=\""+requiredQuantity);
			
		rowToReturn = rowToReturn.replace("value=\"netQuantity", "value=\""+netQuantity);
		
		//Purchse Rate
		rowToReturn = rowToReturn.replace("value=\"purchaseRate", "value=\""+Integer.toString(inventoryDao.getPurchaseRate(inv)));
		return rowToReturn;
	}

	private String createInvtTable(List<Inventory> inventoryList) {

		StringBuilder tableContent = new StringBuilder();

		for (Inventory inv : inventoryList) {

			tableContent.append(createInventoryRowTable(inv));
		}

		return tableContent.toString();
	}

	private String createInventoryRowTable(Inventory inv) {
		String template = "<tr>" 
				+ "    <td>standardType</td>"
				+ "    <td>grade</td>"
				+ "    <td>schedule</td>"
				+ "    <td>materialSpec</td>"
				+ "    <td>ends</td>"
				+ "    <td>size</td>"
				+ "    <td>availableQuantity</td>"
				+ "    <td>purchaseRate</td>";

		String rowToReturn = template;
		rowToReturn = rowToReturn.replace("standardType", inv.getInventorySpec().getStandardType());
		rowToReturn = rowToReturn.replace("grade", inv.getInventorySpec().getGrade());
		rowToReturn = rowToReturn.replace("schedule", inv.getInventorySpec().getSchedule());
		rowToReturn = rowToReturn.replace("materialSpec", inv.getInventorySpec().getMaterialSpec());
		rowToReturn = rowToReturn.replace("ends", inv.getInventorySpec().getEnds());
		rowToReturn = rowToReturn.replace("size", Integer.toString(inv.getInventorySpec().getSize()));
		
		//check available quantity		
		int availableQuantity = inventoryDao.getAvailableQuantity(inv);
		
		rowToReturn = rowToReturn.replace("availableQuantity", Integer.toString(availableQuantity));
		
		//Purchse Rate
		rowToReturn = rowToReturn.replace("purchaseRate", Integer.toString(inventoryDao.getPurchaseRate(inv)));
		return rowToReturn;
	}
}
