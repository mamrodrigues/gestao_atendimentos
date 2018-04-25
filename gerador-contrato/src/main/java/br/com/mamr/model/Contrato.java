package br.com.mamr.model;

import java.util.Date;
import java.util.List;

public class Contrato {

	private Contratada contratada;
	private Contratante contratante;
	private Endereco endereco;
	private List<Servico> servicos;
	private Date dataContrato;
	private Date dataHoraAgendamento;

	public Contratada getContratada() {
		return contratada;
	}

	public void setContratada(Contratada contratada) {
		this.contratada = contratada;
	}

	public Contratante getContratante() {
		return contratante;
	}

	public void setContratante(Contratante contratante) {
		this.contratante = contratante;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}

	public Date getDataContrato() {
		return dataContrato;
	}

	public void setDataContrato(Date dataContrato) {
		this.dataContrato = dataContrato;
	}

	public Date getDataHoraAgendamento() {
		return dataHoraAgendamento;
	}

	public void setDataHoraAgendamento(Date dataHoraAgendamento) {
		this.dataHoraAgendamento = dataHoraAgendamento;
	}

}
