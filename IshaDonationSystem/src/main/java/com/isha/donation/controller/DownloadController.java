 package com.isha.donation.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.SQLWarningException;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import com.isha.donation.Service.DonorService;
import com.isha.donation.Service.IshaDonorService;
import com.isha.donation.entity.Donor;
 


@Controller
public class DownloadController {

	@Autowired
	private DonorService donorService;
	
	@Autowired
	private IshaDonorService ishaDonorService;
	
	 @Autowired
	    private DonorService mdonorService;
	
	 
	
		@SuppressWarnings({ "unchecked", "deprecation" })
		@RequestMapping(value="/downloadnewdonor",method=RequestMethod.GET)
		public ResponseEntity download(HttpServletRequest request,HttpServletResponse response) {
			int rowNum = 0;
			int coulumncount=0;
			System.out.println("download");
	String FILE_NAME="C:/xls/isha.xls"; 
	
	File file=new File(FILE_NAME);
	 
	
	response.setContentType("application/vnd.ms-excel");
	response.setHeader("Content-disposition", "attachment;filename=isha.xls");
    
	
    response.setContentLength((int) file.length());

    HSSFWorkbook workbook=new HSSFWorkbook();
    HSSFSheet sheet=workbook.createSheet("isha");
    
   
       
       System.out.println("Creating excel");
       
       
       @SuppressWarnings("unchecked")
       String status="new";
	List<Donor> listofdonor=ishaDonorService.findAllDonor(status);
       
       for(Donor d:listofdonor){
    	   sheet.setColumnWidth(0, 3000);
    	   System.out.println(d);
       }
       int rowcoulumn=0;
     int count=0;
     sheet.setColumnWidth(0, 3000);
    
     Row rowheader = sheet.createRow(rowNum++);
     
     Cell cellcolumn = rowheader.createCell(coulumncount);
     CellStyle style = workbook.createCellStyle();
     style.setFillBackgroundColor(IndexedColors.YELLOW .getIndex());
     //style.setFillPattern(CellStyle.SOLID_FOREGROUND);
     style.setBorderBottom(CellStyle.BORDER_THIN);
     style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
     style.setBorderLeft(CellStyle.BORDER_THIN);
     style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
     style.setBorderRight(CellStyle.BORDER_THIN);
     style.setRightBorderColor(IndexedColors.BLACK.getIndex());
     style.setBorderTop(CellStyle.BORDER_THIN);
     style.setTopBorderColor(IndexedColors.BLACK.getIndex());

     
      cellcolumn=rowheader.createCell(rowcoulumn++);
     cellcolumn.setCellStyle(style);
     cellcolumn.setCellValue("Consumer Code");
     rowheader.createCell(rowcoulumn++).setCellValue("Applicant Name");
     rowheader.createCell(rowcoulumn++).setCellValue("BANKACCOUNTHOLDERNAME");
     rowheader.createCell(rowcoulumn++).setCellValue("BANKNAME");
     rowheader.createCell(rowcoulumn++).setCellValue("BRANCHNAME");
     rowheader.createCell(rowcoulumn++).setCellValue("BANKACCOUNTNUMBER");
     rowheader.createCell(rowcoulumn++).setCellValue("MICR");    
     rowheader.createCell(rowcoulumn++).setCellValue("IFSCCODE");
     rowheader.createCell(rowcoulumn++).setCellValue("ACCOUNTTYPE");
    rowheader.createCell(rowcoulumn++).setCellValue("EMAIL_ID");
    rowheader.createCell(rowcoulumn++).setCellValue("Mobile_No");  
    rowheader.createCell(rowcoulumn++).setCellValue("STARTDATE");
    rowheader.createCell(rowcoulumn++).setCellValue("ENDDATE");
         rowheader.createCell(rowcoulumn++).setCellValue("AMOUNT");
         rowheader.createCell(rowcoulumn++).setCellValue("FREQUENCY");
              
        	
        	
         

       for (Donor donor : listofdonor) {
           Row row = sheet.createRow(rowNum++);
           sheet.setColumnWidth(0, 3000);
           int colNum = 0;
            
               Cell cell = row.createCell(colNum);
                     row.createCell(colNum++).setCellValue(donor.getDonorId());
               row.createCell(colNum++).setCellValue(donor.getDonorName());
               row.createCell(colNum++).setCellValue(donor.getBankAccountholderName());
               row.createCell(colNum++).setCellValue(donor.getBankName());
               row.createCell(colNum++).setCellValue(donor.getBankBranch());
               row.createCell(colNum++).setCellValue(donor.getBankAccountNumber());
               row.createCell(colNum++).setCellValue(donor.getBankMICR());
               row.createCell(colNum++).setCellValue(donor.getBankIfscCode());
               row.createCell(colNum++).setCellValue(donor.getBankAccountType());
               row.createCell(colNum++).setCellValue(donor.getDonorEmail());
               row.createCell(colNum++).setCellValue(donor.getDonorPhoneNumber());
               row.createCell(colNum++).setCellValue(donor.getDonationStartDate());
               row.createCell(colNum++).setCellValue(donor.getDonationEndDate());
               row.createCell(colNum++).setCellValue(donor.getAmount());
               row.createCell(colNum++).setCellValue(donor.getDonationFrequency());
                          
               
       }
      try{
       
       FileOutputStream outputStream = new FileOutputStream(FILE_NAME);
       workbook.write(outputStream);  
       workbook.close();
       
       
       byte[] bytes=FileUtils.readFileToByteArray(file);
		
		FileCopyUtils.copy(bytes, response.getOutputStream()); 
		 
		OutputStream os = response.getOutputStream();
		
		
		 FileInputStream fileInputStream = new FileInputStream(file);
	       OutputStream responseOutputStream = response.getOutputStream();
	       
	       int bytess;
	       while ((bytess = fileInputStream.read()) != -1) {
	           responseOutputStream.write(bytess);
	       }
	       fileInputStream.close();
	       responseOutputStream.close();
		os.close();
		responseOutputStream.flush();
		
      }catch (Exception e) {
		 e.printStackTrace();
	}
       

       return new ResponseEntity("downloaded succesfully ",HttpStatus.OK);
	
	
}
		
		@RequestMapping(value="/downloaddonation",method=RequestMethod.GET)
		public ResponseEntity<?> downloadRemittances(HttpServletRequest request,HttpServletResponse response){
			
			int rowNum = 0;
			int coulumncount=0;
			System.out.println("download");
	String FILE_NAME="C:/xls/isharemitance.xls"; 
	
	File file=new File(FILE_NAME);
	 
	
	response.setContentType("application/vnd.ms-excel");
	response.setHeader("Content-disposition", "attachment;filename=isharemitance.xls");
    
	
    response.setContentLength((int) file.length());

    HSSFWorkbook workbook=new HSSFWorkbook();
    HSSFSheet sheet=workbook.createSheet("isha");
    
    
       
       System.out.println("Creating excel");
       
       
       @SuppressWarnings("unchecked")
       String status="active";
	List<Donor> listofdonor=ishaDonorService.findDonorRemitance(status);
       
       for(Donor d:listofdonor){
    	   sheet.setColumnWidth(0, 3000);
    	   System.out.println(d);
       }
       int rowcoulumn=0;
     int count=0;
     sheet.setColumnWidth(0, 3000);
     
     Row rowheader = sheet.createRow(rowNum++);
     
     Cell cellcolumn = rowheader.createCell(coulumncount);
     CellStyle style = workbook.createCellStyle();
     style.setFillBackgroundColor(IndexedColors.YELLOW .getIndex());
     
     style.setBorderBottom(CellStyle.BORDER_THIN);
     style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
     style.setBorderLeft(CellStyle.BORDER_THIN);
     style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
     style.setBorderRight(CellStyle.BORDER_THIN);
     style.setRightBorderColor(IndexedColors.BLACK.getIndex());
     style.setBorderTop(CellStyle.BORDER_THIN);
     style.setTopBorderColor(IndexedColors.BLACK.getIndex());

     
      cellcolumn=rowheader.createCell(rowcoulumn++);
     cellcolumn.setCellStyle(style);
     cellcolumn.setCellValue("Consumer Code");
     rowheader.createCell(rowcoulumn++).setCellValue("Applicant Name");
     rowheader.createCell(rowcoulumn++).setCellValue("BANKACCOUNTHOLDERNAME");
     rowheader.createCell(rowcoulumn++).setCellValue("BANKNAME");
     rowheader.createCell(rowcoulumn++).setCellValue("BRANCHNAME");
     rowheader.createCell(rowcoulumn++).setCellValue("BANKACCOUNTNUMBER");
     rowheader.createCell(rowcoulumn++).setCellValue("MICR");    
     rowheader.createCell(rowcoulumn++).setCellValue("IFSCCODE");
     rowheader.createCell(rowcoulumn++).setCellValue("ACCOUNTTYPE");
    rowheader.createCell(rowcoulumn++).setCellValue("EMAIL_ID");
    rowheader.createCell(rowcoulumn++).setCellValue("Mobile_No");  
    rowheader.createCell(rowcoulumn++).setCellValue("STARTDATE");
    rowheader.createCell(rowcoulumn++).setCellValue("ENDDATE");
         rowheader.createCell(rowcoulumn++).setCellValue("AMOUNT");
         rowheader.createCell(rowcoulumn++).setCellValue("FREQUENCY");
              
        	
        	
         

       for (Donor donor : listofdonor) {
           Row row = sheet.createRow(rowNum++);
           sheet.setColumnWidth(0, 3000);
           int colNum = 0;
            
               Cell cell = row.createCell(colNum);
                     row.createCell(colNum++).setCellValue(donor.getDonorId());
               row.createCell(colNum++).setCellValue(donor.getDonorName());
               row.createCell(colNum++).setCellValue(donor.getBankAccountholderName());
               row.createCell(colNum++).setCellValue(donor.getBankName());
               row.createCell(colNum++).setCellValue(donor.getBankBranch());
               row.createCell(colNum++).setCellValue(donor.getBankAccountNumber());
               row.createCell(colNum++).setCellValue(donor.getBankMICR());
               row.createCell(colNum++).setCellValue(donor.getBankIfscCode());
               row.createCell(colNum++).setCellValue(donor.getBankAccountType());
               row.createCell(colNum++).setCellValue(donor.getDonorEmail());
               row.createCell(colNum++).setCellValue(donor.getDonorPhoneNumber());
               row.createCell(colNum++).setCellValue(donor.getDonationStartDate());
               row.createCell(colNum++).setCellValue(donor.getDonationEndDate());
               row.createCell(colNum++).setCellValue(donor.getAmount());
               row.createCell(colNum++).setCellValue(donor.getDonationFrequency());
                          
               
       }
      try{
       
       FileOutputStream outputStream = new FileOutputStream(FILE_NAME);
       workbook.write(outputStream);  
       workbook.close();
       
       
       byte[] bytes=FileUtils.readFileToByteArray(file);
		
		FileCopyUtils.copy(bytes, response.getOutputStream()); 
		 
		OutputStream os = response.getOutputStream();
		
		
		 FileInputStream fileInputStream = new FileInputStream(file);
	       OutputStream responseOutputStream = response.getOutputStream();
	       
	       int bytess;
	       while ((bytess = fileInputStream.read()) != -1) {
	           responseOutputStream.write(bytess);
	       }
	       fileInputStream.close();
	       responseOutputStream.close();
		os.close();
		responseOutputStream.flush();
		
      }catch (Exception e) {
		 e.printStackTrace();
	}
  

       return new ResponseEntity("downloaded succesfully ",HttpStatus.OK);
	

			
		}

		
		
		@RequestMapping(value=("/uploadxls"),method=RequestMethod.POST)
		@ResponseBody 
		 public ResponseEntity<?> upload(@RequestParam("file") MultipartFile multipartFile,UriComponentsBuilder ucBuilder){
			int count=1;
			ArrayList<String> uploadList=new ArrayList<String>();
			try{
				
			 HSSFWorkbook workbook=new HSSFWorkbook(multipartFile.getInputStream());
			    HSSFSheet sheet=workbook.getSheetAt(0);
			    
			    int countcolumn=0;
			    while(countcolumn<=0){
			    	int cell=0;
			    	HSSFRow row=sheet.getRow(countcolumn++);
			    	String st=row.getCell(cell++).getStringCellValue();
			    	System.out.println("--------"+st+"--------");
			    	if(st.equals("Applicant Name")){
			    		
			    	}else{
			    		return new ResponseEntity("need  Applicant Name",HttpStatus.CONFLICT);
			    	}
			    	st=row.getCell(cell++).getStringCellValue();
			    	System.out.println(st);
			    	if(st.equals("BANKACCOUNTHOLDERNAME")){
			    		
			    	}else{
			    		return new ResponseEntity("need  BANKACCOUNTHOLDERNAME",HttpStatus.CONFLICT);
			    	}
			    	
			    	st=row.getCell(cell++).getStringCellValue();
			    	System.out.println(st);
			    	if(st.equals("BANKNAME")){
			    		
			    	}else{
			    		return new ResponseEntity("need  BANKNAME",HttpStatus.CONFLICT);
			    	}
			    	st=row.getCell(cell++).getStringCellValue();
			    	System.out.println(st);
			    	if(st.equals("BRANCHNAME")){
			    		
			    	}else{
			    		return new ResponseEntity("need  BRANCHNAME",HttpStatus.CONFLICT);
			    	}
			    	 
			    	
			    	st=row.getCell(cell++).getStringCellValue();
			    	System.out.println(st);
			    	if(st.equals("BANKACCOUNTNUMBER")){
			    		
			    	}else{
			    		return new ResponseEntity("need  BANKACCOUNTNUMBER",HttpStatus.CONFLICT);
			    	}
			    	st=row.getCell(cell++).getStringCellValue();
			    	System.out.println(st);
			    	if(st.equals("MICR")){
			    		
			    	}else{
			    		return new ResponseEntity("need  MICR",HttpStatus.CONFLICT);
			    	}
			    	st=row.getCell(cell++).getStringCellValue();
			    	if(st.equals("IFSCCODE")){
			    		
			    	}else{
			    		return new ResponseEntity("need  IFSCCODE",HttpStatus.CONFLICT);
			    	}
			    	st=row.getCell(cell++).getStringCellValue();
			    	System.out.println(st);
			    	if(st.equals("ACCOUNTTYPE")){
			    		
			    	}else{
			    		return new ResponseEntity("need  ACCOUNTTYPE",HttpStatus.CONFLICT);
			    	}
			    	st=row.getCell(cell++).getStringCellValue();
			    	System.out.println(st);
			    	if(st.equals("EMAIL_ID")){
			    		
			    	}else{
			    		return new ResponseEntity("need  EMAIL_ID",HttpStatus.CONFLICT);
			    	}
			    	st=row.getCell(cell++).getStringCellValue();
			    	System.out.println(st);
			    	if(st.equals("Mobile_No")){
			    		
			    	}else{
			    		return new ResponseEntity("need  Mobile_No",HttpStatus.CONFLICT);
			    	}
			    	
			    	st=row.getCell(cell++).getStringCellValue();
			    	System.out.println(st);
			    	if(st.equals("STARTDATE")){
			    		
			    	}else{
			    		return new ResponseEntity("need  STARTDATE",HttpStatus.CONFLICT);
			    	}
			    	
			     
			    	st=row.getCell(cell++).getStringCellValue();
			    	System.out.println(st);
			    	if(st.equals("ENDDATE")){
			    		
			    	}else{
			    		return new ResponseEntity("need  ENDDATE",HttpStatus.CONFLICT);
			    	}
			    	
			    	st=row.getCell(cell++).getStringCellValue();
			    	System.out.println(st);
			    	if(st.equals("AMOUNT")){
			    		
			    	}else{
			    		return new ResponseEntity("need  AMOUNT",HttpStatus.CONFLICT);
			    	}
			    	
			    	
			    	 
			    	st=row.getCell(cell++).getStringCellValue();
			    	System.out.println(st);
			    	if(st.equals("FREQUENCY")){
			    		
			    	}else{
			    		return new ResponseEntity("need  FREQUENCY",HttpStatus.CONFLICT);
			    	}
			    	
			    }
			   //try{
			    
			    while(count<=sheet.getLastRowNum()){
			    	int cell=0;
			    	HSSFRow row = sheet.getRow(count++);
			    	
			    	Donor donor=new Donor();
			    	//UploadDonor uploaddonor=new UploadDonor();
			    	 
			    	System.out.println("name:"+row.getCell(cell).getStringCellValue());
			    	donor.setName(row.getCell(cell++).getStringCellValue());
			    	
			    	System.out.println("accholdernumber:"+row.getCell(cell).getStringCellValue());
			    	donor.setBankAccountholderName(row.getCell(cell++).getStringCellValue());
			    	
			    	System.out.println("bankname"+row.getCell(cell).getStringCellValue());
			    	donor.setBankName(row.getCell(cell++).getStringCellValue());
			    	
			    	System.out.println("branchname"+row.getCell(cell).getStringCellValue());
			    	donor.setBranchName(row.getCell(cell++).getStringCellValue());
			   
			    	 
			    	
			    	int accnum=(int)row.getCell(cell++).getNumericCellValue();
			    	String accnumber=String.valueOf(accnum);
			    	
			     
			    	donor.setBankAccountNumber(accnumber);
			   System.out.println((int)row.getCell(cell).getNumericCellValue());
			    	donor.setBankMICR((int)row.getCell(cell++).getNumericCellValue());
			    	String ifsc=row.getCell(cell++).getStringCellValue();
			    	System.out.println("ifs"+ifsc);
			    	donor.setBankIfscCode(ifsc);
			    	
			  System.out.println("accounttype"+row.getCell(cell).getStringCellValue());
			    	donor.setBankAccountType(row.getCell(cell++).getStringCellValue());
			    	 
			    	System.out.println("email"+row.getCell(cell).getStringCellValue());
			    	donor.setEmail(row.getCell(cell++).getStringCellValue());
			    	
			   long mobile=(long)row.getCell(cell++).getNumericCellValue();
			    	String mob=String.valueOf(mobile);
			    	System.out.println("mobile"+mob);
			    	donor.setDonorPhoneNumber(mob);
			    	 
			    	
			    	 System.out.println("startdate"+row.getCell(cell).getDateCellValue());
			    	 donor.setStartDate((Date)row.getCell(cell++).getDateCellValue());
			    	
			    	 
			    	 			    
			    	 System.out.println("endate"+row.getCell(cell).getDateCellValue());
			    	
			    	 donor.setEndDate((Date)row.getCell(cell++).getDateCellValue());
			    	
			    	  
			    	System.out.println("amoutn"+row.getCell(cell).getNumericCellValue());
			    	donor.setAmount((double)row.getCell(cell++).getNumericCellValue());
			    	  	
			    	   
			    	  	System.out.println("frequency"+row.getCell(cell).getStringCellValue());
			    	  	donor.setFrequency(row.getCell(cell++).getStringCellValue());
			    	 
			    			    	
			    	  	Date createdate=row.getCell(cell).getDateCellValue();
			    	  	 DateFormat df=new SimpleDateFormat("mm-dd-yyyy");
			    	  	 String cdate=df.format(createdate);
			 
			    	System.out.println("create Date"+cdate);
			    	
			    	 donor.setCreateDate(cdate);
			 try{	
				  System.out.println("uploadmobile number->"+donor.getDonorPhoneNumber());
			    	  Donor donorinfo=ishaDonorService.findDonorMobile(donor.getDonorPhoneNumber());
			            System.out.println("*******************************");
			            System.out.println(donorinfo);
			            System.out.println("*******************************");
			            if(donorinfo==null){
			            	mdonorService.save(donor);
			            }else{
			            	uploadList.add(donorinfo.getDonorPhoneNumber());
			            	if(count==sheet.getLastRowNum()){
			            		return new ResponseEntity<String>("duplicate entry"+"\t"+uploadList+"is duplicate entry", HttpStatus.CONFLICT);
				            } 
			            	//return new ResponseEntity<String>("duplicate entry"+"\t"+donorinfo.getUpdatemobileNumber()+"is duplicate entry", HttpStatus.CONFLICT);
			            }
			            
			    	
			    	
			    }catch (SQLWarningException e) {
			    	return new ResponseEntity<String>("exception", HttpStatus.CONFLICT);
				}  
			    }
			    
			    }catch(Exception e){
			    	return new ResponseEntity<String>("exception arised when reading a document"+e, HttpStatus.CONFLICT);
			    }
			    
			/*    
			}catch(Exception e){
				e.printStackTrace();
			}*/
			
			 HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/uploadxls").buildAndExpand("successfully uploaded").toUri());
			 
			
			return new ResponseEntity<String>(headers, HttpStatus.CREATED);
			
			
		}

		 
			 
		  
 

 

		
		 
		
		
	
}





	 
 
