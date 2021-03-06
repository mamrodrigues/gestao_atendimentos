package br.com.mamr.controler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.util.Date;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;

import br.com.mamr.factory.ReportFactory;
import br.com.mamr.model.Contrato;
import br.com.mamr.util.ReportUtil;

public class ReportIText {

	private static final String HTML_FILE = ".//res//contrato.html";
	private static final String PDF_PATH = ".//pdf-result-test//";

	public void convertHTMLToPDF(Contrato contrato) {

		String filePathPDF = ReportUtil.getFilePath(PDF_PATH, new Date(), contrato.getContratante().getNome());

		try {
			OutputStream file = new FileOutputStream(new File(filePathPDF));
			Document document = new Document();
			PdfWriter pdfWriter = PdfWriter.getInstance(document, file);

			document.setPageSize(PageSize.A4);

			document.open();

			HTMLWorker htmlWorker = new HTMLWorker(document);

			String str = "";
			StringBuilder contentBuilder = new StringBuilder();
			BufferedReader in = null;

			try {
				in = new BufferedReader(new FileReader(HTML_FILE));

				while ((str = in.readLine()) != null) {
					contentBuilder.append(str);
				}
			} catch (IOException e) {
				System.out.print("HTML file close problem:" + e.getMessage());
			} finally {
				in.close();
				System.gc();
			}

			String content = contentBuilder.toString();
			htmlWorker.parse(new StringReader(content));
			document.close();
			pdfWriter.close();
			file.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public File createPDF(Contrato contrato) {

		String filePathPDF = ReportUtil.getFilePath(PDF_PATH, new Date(), contrato.getContratante().getNome());

		try {
			Document document = new Document();
			
			File pdfFile = new File(filePathPDF);
			PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(pdfFile));

			// open
			document.open();

			ReportFactory reportFactory = new ReportFactory();
			reportFactory.formatDocument(document, contrato);

			// close
			document.close();
			pdfWriter.close();
			
			return pdfFile;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
