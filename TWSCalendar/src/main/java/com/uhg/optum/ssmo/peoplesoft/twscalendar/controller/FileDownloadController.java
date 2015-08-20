package com.uhg.optum.ssmo.peoplesoft.twscalendar.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.uhg.optum.ssmo.peoplesoft.twscalendar.io.ExcelGenerator;

@Controller
@RequestMapping("/viewCalendar")
public class FileDownloadController {
	@Autowired
	ServletContext context;


	private final static Logger logger = LoggerFactory
			.getLogger(FileDownloadController.class);

	private static final int BUFFER_SIZE = 4096;

	@RequestMapping(method = RequestMethod.GET)
	public void doDownload(HttpServletRequest request,
			HttpServletResponse response)
			throws IOException {
		
		String fileName =  new ExcelGenerator().generate();
		logger.debug("[generateExcel] generated fileName: " + fileName);
		String fullPath = "C:\\TWSCalendar\\"+fileName;

		File downloadFile = new File(fullPath);
		FileInputStream inputStream = new FileInputStream(downloadFile);

		// get MIME type of the file
		String mimeType = context.getMimeType(fullPath);
		if (mimeType == null) {
			// set to binary type if MIME mapping not found
			mimeType = "application/octet-stream";
		}

		// set content attributes for the response
		response.setContentType(mimeType);
		response.setContentLength((int) downloadFile.length());

		// set headers for the response
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"",
				downloadFile.getName());
		response.setHeader(headerKey, headerValue);

		// get output stream of the response
		OutputStream outStream = response.getOutputStream();

		byte[] buffer = new byte[BUFFER_SIZE];
		int bytesRead = -1;

		// write bytes read from the input stream into the output stream
		while ((bytesRead = inputStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, bytesRead);
		}

		inputStream.close();
		outStream.close();

	}
}