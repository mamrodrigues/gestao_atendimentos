package br.com.mamr.controler;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.mamr.model.Contratada;
import br.com.mamr.model.Contratante;
import br.com.mamr.model.Contrato;
import br.com.mamr.model.Endereco;
import br.com.mamr.util.ReportUtil;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

public class ReportJasper {

	private static final String JRXML = ".//jasper//report.jrxml";
	private static final String PDF_PATH = ".//pdf-result-test//";

	public void generate(Contrato contrato) {
		JasperReport jasperReport;
		JasperPrint jasperPrint;
		try {
			jasperReport = JasperCompileManager.compileReport(JRXML);

			HashMap<String, String> map = paramsBuilder(contrato);

			jasperPrint = JasperFillManager.fillReport(jasperReport, map, new JREmptyDataSource());

			String filePathPDF = ReportUtil.getFilePath(PDF_PATH, new Date(), contrato.getContratante().getNome());
			JasperExportManager.exportReportToPdfFile(jasperPrint, filePathPDF);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/detalharContrato", method = RequestMethod.GET)
	public Contrato get(@PathVariable("id") long id) {
		return new Contrato();
	}

	@RequestMapping(value = "/listarContratos", method = RequestMethod.GET)
	public List<Contrato> list() {
		return new ArrayList<Contrato>();
	}

	private HashMap<String, String> paramsBuilder(Contrato contrato) {
		HashMap<String, String> map = new HashMap<String, String>();
		contratoMapBuilder(map, contrato);
		return map;
	}

	private void contratoMapBuilder(HashMap<String, String> map, Contrato contrato) {

		contratadaMapBuilder(map, contrato.getContratada());
		contratanteMapBuilder(map, contrato.getContratante());

		enderecoMapBuilder(map, contrato.getEndereco());

		SimpleDateFormat sdfContrato = new SimpleDateFormat("dd/MM/yyyy");
		map.put("contratoData", sdfContrato.format(contrato.getDataContrato()));

		SimpleDateFormat sdfAgendamento = new SimpleDateFormat("dd/MM/yyyy  às HH:mm");
		map.put("contratoDataAgendamento", sdfAgendamento.format(contrato.getDataHoraAgendamento()));
	}

	private void enderecoMapBuilder(HashMap<String, String> map, Endereco endereco) {
		map.put("enderecoTipo", endereco.getTipoEndereco().toString());
		map.put("endereco", endereco.getEndereco());
		map.put("enderecoNumero", endereco.getNumero());
		map.put("enderecoCEP", endereco.getCep());
		map.put("enderecoCidade", endereco.getCidade());
		map.put("enderecoEstado", endereco.getEstado());
	}

	private void contratanteMapBuilder(HashMap<String, String> map, Contratante contratante) {
		map.put("contratanteNome", contratante.getNome());
		map.put("contratanteRG", contratante.getRg());
		map.put("contratanteNacionalidade", contratante.getNacionalidade());
		map.put("contratanteEstadoRG", contratante.getEstadoRg());
		map.put("contratanteCPF", contratante.getCPF());
		map.put("contratanteTelefone", contratante.getTelefone());
		map.put("contratanteEndereco", contratante.getEndereco().getEndereco());
		map.put("contratanteEnderecoTipo", contratante.getEndereco().getTipoEndereco().toString());
		map.put("contratanteEnderecoCEP", contratante.getEndereco().getCep());
		map.put("contratanteEnderecoNumero", contratante.getEndereco().getNumero());
		map.put("contratanteEnderecoCidade", contratante.getEndereco().getCidade());
		map.put("contratanteEnderecoEstado", contratante.getEndereco().getEstado());
	}

	private void contratadaMapBuilder(HashMap<String, String> map, Contratada contratada) {
		map.put("contratadaNome", contratada.getNome());
		map.put("contratadaNomeEmpresa", contratada.getNomeEmpresa());
		map.put("contratadaNacionalidade", contratada.getNacionalidade());
		map.put("contratadaCNPJ", contratada.getCNPJ());
		map.put("contratadaTelefone", contratada.getTelefone());
		map.put("contratadaEndereco", contratada.getEndereco().getEndereco());
		map.put("contratadaEnderecoTipo", contratada.getEndereco().getTipoEndereco().toString());
		map.put("contratadaEnderecoCEP", contratada.getEndereco().getCep());
		map.put("contratadaEnderecoNumero", contratada.getEndereco().getNumero());
		map.put("contratadaEnderecoCidade", contratada.getEndereco().getCidade());
		map.put("contratadaEnderecoEstado", contratada.getEndereco().getEstado());
	}

}
