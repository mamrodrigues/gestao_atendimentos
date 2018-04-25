package br.com.mamr;

import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.mamr.controler.ReportResource;
import br.com.mamr.model.Contratada;
import br.com.mamr.model.Contratante;
import br.com.mamr.model.Contrato;
import br.com.mamr.model.Endereco;
import br.com.mamr.model.TipoEndereco;

@SpringBootApplication
public class ReportView {

	public static void main(String[] args) {
		SpringApplication.run(ReportView.class, args);
		ReportResource reportGenerator = new ReportResource();

		ReportView reportView = new ReportView();
		reportGenerator.generate(reportView.factoryContrato());
	}

	private Contrato factoryContrato() {
		Contrato contrato = new Contrato();
		contrato.setDataContrato(new Date());
		contrato.setDataHoraAgendamento(new Date());

		Contratada contratada = contratadaBuilder();
		Contratante contratante = contratanteBuilder();
		Endereco endereco = enderecoBuilder();

		contrato.setEndereco(endereco);
		contrato.setContratante(contratante);
		contrato.setContratada(contratada);

		return contrato;
	}

	private Endereco enderecoBuilder() {
		Endereco enderecoAtendimento = new Endereco();
		enderecoAtendimento.setTipoEndereco(TipoEndereco.APARTAMENTO);
		enderecoAtendimento.setEndereco("QS 07, Avenida das Araucárias");
		enderecoAtendimento.setNumero("47");
		enderecoAtendimento.setCep("71880-100");
		enderecoAtendimento.setCidade("Águas Claras");
		enderecoAtendimento.setEstado("DF");
		return enderecoAtendimento;
	}

	private Contratante contratanteBuilder() {
		Contratante contratante = new Contratante();
		contratante.setNome("Marcos");
		contratante.setNacionalidade("Brasileiro");

		Endereco enderecoContratante = new Endereco();
		enderecoContratante.setTipoEndereco(TipoEndereco.CASA);
		enderecoContratante.setEndereco("QN 8B CONJUNTO 02");
		enderecoContratante.setNumero("29");
		enderecoContratante.setCep("71880-122");
		enderecoContratante.setCidade("Riacho Fundo II");
		enderecoContratante.setEstado("DF");

		contratante.setEndereco(enderecoContratante);
		contratante.setRg("2822652");
		contratante.setEstadoRg("DF");
		contratante.setCPF("031.425.281-99");
		contratante.setTelefone("(61) 982423667");
		return contratante;
	}

	private Contratada contratadaBuilder() {
		Contratada contratada = new Contratada();
		contratada.setNome("ELAINE RODRIGUES SERPA");
		contratada.setNomeEmpresa("ELAINE SERPA CABELO E MAQUIAGEM");
		contratada.setNacionalidade("Brasileira");

		Endereco enderecoContratada = new Endereco();
		enderecoContratada.setTipoEndereco(TipoEndereco.CASA);
		enderecoContratada.setEndereco("Qn7C Conjunto 08, Riacho Fundo II");
		enderecoContratada.setNumero("14");
		enderecoContratada.setCep("71.880-038");
		enderecoContratada.setCidade("Brasília");
		enderecoContratada.setEstado("DF");
		contratada.setEndereco(enderecoContratada);

		contratada.setTelefone("(61) 98167-6499");
		contratada.setEmail("elaineserpa@outlook.com");
		contratada.setCNPJ("27.324..476/000134");
		return contratada;
	}

}
