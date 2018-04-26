package br.com.mamr.controler;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.mamr.model.Contrato;

@RestController
@RequestMapping("/gestao")
public class ReportResource {

	@RequestMapping(value = "/gerarContrato", method = RequestMethod.POST)
	public void generate(@RequestBody Contrato contrato) {
		ReportIText report = new ReportIText();
		report.createPDF(contrato);
		
//		ReportJasper reportJasper = new ReportJasper();
//		reportJasper.generate(contrato);
	}

}
