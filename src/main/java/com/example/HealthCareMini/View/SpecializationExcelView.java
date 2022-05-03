package com.example.HealthCareMini.View;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.example.HealthCareMini.Entity.Specialization;

public class SpecializationExcelView extends AbstractXlsxView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		response.addHeader("Content-Disposition", "attachment;fileName=SPEC.xlsx");

		// read the data from controller
		@SuppressWarnings("unchecked")
		List<Specialization> list = (List<Specialization>) model.get("list");

		Sheet sheet = workbook.createSheet("SPECIALIZATION");

		// add row header

		setRowHeader(sheet);

		// setbody for list

		setRowData(list, sheet);

	}

	public void setRowHeader(Sheet sheet) {

		Row row = sheet.createRow(0);
		row.createCell(0).setCellValue("ID");
		row.createCell(1).setCellValue("NAME");
		row.createCell(2).setCellValue("NOTE");
		row.createCell(3).setCellValue("CODE");

	}

	public void setRowData(List<Specialization> list, Sheet sheet) {
		int rowNo = 1;
		for (Specialization spe : list) {
			Row row = sheet.createRow(rowNo++);
			row.createCell(0).setCellValue(spe.getId());
			row.createCell(1).setCellValue(spe.getName());
			row.createCell(2).setCellValue(spe.getNote());
			row.createCell(3).setCellValue(spe.getCode());
		}
	}

}