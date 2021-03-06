package br.com.mamr.controler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

import org.apache.commons.io.FileUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.pdf.codec.Base64;

import br.com.mamr.model.Contratada;
import br.com.mamr.model.Contratante;
import br.com.mamr.model.Contrato;
import br.com.mamr.model.Resposta;
import ch.qos.logback.core.util.FileUtil;

@RestController
@RequestMapping("/gestao")
@CrossOrigin(origins = "http://localhost:5000")
public class ReportResource {

	@RequestMapping(value = "/gerarContrato", method = RequestMethod.POST, produces = "application/json")
	public Resposta generate(@RequestBody Contrato contrato) throws IOException {
		ReportIText report = new ReportIText();
		File pdfFile = report.createPDF(contrato);
		
//		ReportJasper reportJasper = new ReportJasper();
//		reportJasper.generate(contrato);
		
		byte[] byteArrayFile = FileUtils.readFileToByteArray(pdfFile);
		String value = Base64.encodeBytes(byteArrayFile);
		
//		FileInputStream fileInput = new FileInputStream(pdfFile);
//		OutputBlob blobOutput = new OutputBlob(fileInput, file.length());

		Resposta resposta = new Resposta();
		resposta.setArquivo(byteArrayFile);
		resposta.setMensagem("deu certo");
		resposta.setBase64(value);
		return resposta;
		
//		InputStreamResource resource = new InputStreamResource(new FileInputStream(pdfFile));
//
//		HttpHeaders headers = new HttpHeaders();
//		headers.add("content-disposition", "inline;filename=" + "teste");
//		try {
//			ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(
//					Files.readAllBytes(pdfFile.toPath()), headers, HttpStatus.OK);
//		} catch (IOException e) {
//			e.printStackTrace();
//
//			return null;
//		}
//		return null;
	}
	
	@RequestMapping(value = "/listarContratos", method = RequestMethod.GET)
	public Contrato generate() {
		Contrato contrato =  new Contrato();
		Contratada contratada = new Contratada();
		Contratante contratante = new Contratante();
		
		contratada.setNome("Elaine Serpa");
		contratante.setNome("cliente");
		
		contrato.setContratada(contratada);
		contrato.setContratante(contratante);
		return contrato;
	}

}
