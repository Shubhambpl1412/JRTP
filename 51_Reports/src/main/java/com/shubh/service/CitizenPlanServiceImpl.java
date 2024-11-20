package com.shubh.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfDeviceNColor;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.shubh.binding.SearchCriteria;
import com.shubh.entity.CitizenPlan;
import com.shubh.repo.CitizenPlanRepo;
import com.shubh.utils.EmailUtils;

import io.micrometer.common.util.StringUtils;
import jakarta.persistence.Table;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class CitizenPlanServiceImpl implements CitizenPlanService {
	@Autowired
	private CitizenPlanRepo repo;
	
	
	@Autowired
	private EmailUtils emailUtils;

	@Override
	public List<String> getPlanNames() {
		return repo.getPlanNames();
	}

	@Override
	public List<String> getPlanStatus() {
		return repo.getPlanStatus();
	}

	@Override
	public List<CitizenPlan> searchCitizens(SearchCriteria criteria) {
		CitizenPlan entity = new CitizenPlan();

		if (StringUtils.isNotBlank(criteria.getPlanName())) {
			entity.setPlanName(criteria.getPlanName());
		}
		if (StringUtils.isNotBlank(criteria.getPlanStatus())) {
			entity.setPlanStatus(criteria.getPlanStatus());
		}
		if (StringUtils.isNotBlank(criteria.getGender())) {
			entity.setGender(criteria.getGender());
		}

		if (null != criteria.getStartDate()) {
			entity.setPlanStartDate(criteria.getStartDate());
		}
		if (null != criteria.getEndDate()) {
			entity.setPlanEndDate(criteria.getEndDate());
		}
		Example<CitizenPlan> of = Example.of(entity);   
		return repo.findAll(of);
	}

	@Override
	public void generatedExcel(HttpServletResponse response) throws IOException {
		List<CitizenPlan> records = repo.findAll();

		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Data");
		HSSFRow headerRow = sheet.createRow(0);

		headerRow.createCell(0).setCellValue("Name");
		headerRow.createCell(1).setCellValue("Email");
		headerRow.createCell(2).setCellValue("Gender");
		headerRow.createCell(3).setCellValue("Plan Name");
		headerRow.createCell(4).setCellValue("Plan Status");
		headerRow.createCell(5).setCellValue("SSN");

		int rowIndex = 1;

		for (CitizenPlan record : records) {
			HSSFRow dataRow = sheet.createRow(rowIndex);

			dataRow.createCell(0).setCellValue(record.getName());
			dataRow.createCell(1).setCellValue(record.getEmail());
			dataRow.createCell(2).setCellValue(record.getGender());
			dataRow.createCell(3).setCellValue(record.getSsn());
			dataRow.createCell(4).setCellValue(record.getPlanName());
			dataRow.createCell(5).setCellValue(record.getPlanStatus());

			rowIndex++;

		}
		//to send file email attachment
		File f=new File("data.xls");
		FileOutputStream fos=new FileOutputStream(f);
		workbook.write(fos);
		emailUtils.sendEmail(f);
		
		//to download file in browser 
		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
	}

	@Override
	public void generatedPdf(HttpServletResponse response) throws Exception {
	    List<CitizenPlan> records = repo.findAll();
	    File f=new File("data.pdf");
	    FileOutputStream fos=new FileOutputStream(f);
        
	    Document pdfDoc = new Document(PageSize.A4);
	    ServletOutputStream outputStream = response.getOutputStream();
	    PdfWriter.getInstance(pdfDoc, outputStream);
	    PdfWriter.getInstance(pdfDoc, fos);
	    pdfDoc.open();

	    // Title
	    Font fontTitle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
	    fontTitle.setSize(20);
	    Paragraph p = new Paragraph("Citizen Plans Info");
	    p.setAlignment(Paragraph.ALIGN_CENTER);
	    pdfDoc.add(p);

	    // Table setup
	    PdfPTable table = new PdfPTable(6);
	    table.setWidthPercentage(100);
	    table.setWidths(new int[]{3, 3, 3, 3, 3, 3});
	    table.setSpacingBefore(5);

	    // Header cells
	    Font fontHeader = FontFactory.getFont(FontFactory.TIMES_ROMAN);
	    fontHeader.setColor(CMYKColor.WHITE);
	    
	    String[] headers = {"Name", "Email", "Gender", "SSN", "Plan Name", "Plan Status"};
	    for (String header : headers) {
	        PdfPCell cell = new PdfPCell(new Phrase(header, fontHeader));
	        cell.setBackgroundColor(CMYKColor.BLUE);
	        cell.setPadding(5);
	        table.addCell(cell);
	    }

	    // Data rows
	    for (CitizenPlan record : records) {
	        table.addCell(record.getName());
	        table.addCell(record.getEmail());
	        table.addCell(record.getGender());
	        table.addCell(String.valueOf(record.getSsn())); // Convert to String
	        table.addCell(record.getPlanName());
	        table.addCell(record.getPlanStatus());
	    }

	    pdfDoc.add(table);
	    emailUtils.sendEmail(f);
	    pdfDoc.close();
	    outputStream.close();
	    fos.close();
	    
	    
	}


}
