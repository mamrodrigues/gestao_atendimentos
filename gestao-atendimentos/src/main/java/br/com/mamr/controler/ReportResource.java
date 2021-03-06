package br.com.mamr.controler;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
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

@RestController
@RequestMapping("/gestao")
@CrossOrigin(origins = "http://localhost:5000")
public class ReportResource {

	@RequestMapping(value = "/gerarContrato", method = RequestMethod.POST, produces = "application/json")
	public Resposta generate(@RequestBody Contrato contrato) throws IOException {
		ReportIText report = new ReportIText();
		File pdfFile = report.createPDF(contrato);
		
		byte[] byteArrayFile = FileUtils.readFileToByteArray(pdfFile);
		String value = Base64.encodeBytes(byteArrayFile);
		Resposta resposta = new Resposta();
		resposta.setArquivo(byteArrayFile);
		resposta.setMensagem("Contrato gerado com sucesso");
		resposta.setBase64(value);
		return resposta;
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
