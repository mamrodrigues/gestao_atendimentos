package br.com.mamr.controler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import br.com.mamr.model.Contratada;
import br.com.mamr.model.Contratante;
import br.com.mamr.model.Contrato;
import br.com.mamr.model.Endereco;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

public class ReportGenerator {

	private static final String JRXML = ".//jasper//report.jrxml";
	private static final String PDF_PATH = "C://Users//marco//Desktop//";

	public void generate(Contrato contrato) {
		JasperReport jasperReport;
		JasperPrint jasperPrint;
		try {
			jasperReport = JasperCompileManager.compileReport(JRXML);

			HashMap<String, String> map = paramsBuilder(contrato);

			jasperPrint = JasperFillManager.fillReport(jasperReport, map, new JREmptyDataSource());

			String filePathPDF = getFilePath(new Date(), contrato.getContratante().getNome().toUpperCase());
			JasperExportManager.exportReportToPdfFile(jasperPrint, filePathPDF);
		} catch (JRException e) {
			e.printStackTrace();
		}
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

	private String getFilePath(Date date, String nome) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HHmm");
		String formatDate = sdf.format(date);
		return PDF_PATH + "[" + formatDate + "] " + nome + ".pdf";
	}

}
