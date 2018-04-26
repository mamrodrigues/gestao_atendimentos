package br.com.mamr.factory;

import java.io.IOException;
import java.net.MalformedURLException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;

import br.com.mamr.model.Contratada;
import br.com.mamr.model.Contratante;
import br.com.mamr.model.Contrato;
import br.com.mamr.model.Endereco;
import br.com.mamr.model.Servico;
import br.com.mamr.model.TipoServico;

public class ReportFactory {

	private static final String LOGO_FILE = ".//res//logo.jpg";

	public void formatDocument(Document document, Contrato contrato)
			throws DocumentException, MalformedURLException, IOException {

		Image image = Image.getInstance(LOGO_FILE);
		image.setAlignment(Image.ALIGN_CENTER);
		image.scaleAbsolute(300, 100);
		document.add(image);

		Font negrito = new Font();
		negrito.setStyle(Font.BOLD);
		negrito.setSize(11);

		Font normal = new Font();
		normal.setStyle(Font.NORMAL);
		normal.setSize(11);

		Font titulo = new Font();
		titulo.setStyle(Font.BOLD);
		titulo.setSize(12);

		Paragraph title = new Paragraph();
		title.add("CONTRATO DE PRESTAÇÃO DE SERVIÇO");
		title.setFont(titulo);
		title.setAlignment(Element.ALIGN_CENTER);
		title.setSpacingAfter(15);
		title.setSpacingBefore(15);
		document.add(title);

		Contratante contratante = contrato.getContratante();
		Endereco enderecoContratante = contratante.getEndereco();

		Paragraph contratanteP = new Paragraph();
		contratanteP.setFont(normal);
		contratanteP.add("CONTRATANTE: " + contratante.getNome() + ", " + contratante.getNacionalidade()
				+ ", domiciliada em " + enderecoContratante.getEndereco() + ", " + enderecoContratante.getTipoEndereco()
				+ " " + enderecoContratante.getNumero() + ", " + enderecoContratante.getCidade() + "/ "
				+ enderecoContratante.getEstado() + ", CEP: " + enderecoContratante.getCep()
				+ ", portadora da cédula de identidade n° " + contratante.getRg() + "/SSP " + contratante.getEstadoRg()
				+ " e inscrita no CPF sob nº " + contratante.getCPF() + ", Telefone: " + contratante.getTelefone()
				+ ", E-mail: " + contratante.getEmail() + ".\r\n");
		document.add(contratanteP);

		Contratada contratada = contrato.getContratada();
		Endereco enderecoContratada = contratada.getEndereco();

		Paragraph contratadaP = new Paragraph();
		contratadaP.setSpacingBefore(5);
		contratadaP.setFont(normal);
		contratadaP.add("CONTRATADA: " + contratada.getNomeEmpresa() + ", na pessoa de " + contratada.getNome()
				+ ", CNPJ: " + contratada.getCNPJ() + ", " + enderecoContratada.getEndereco() + ", "
				+ enderecoContratada.getTipoEndereco() + " " + enderecoContratada.getNumero() + ", "
				+ enderecoContratada.getCidade() + "/ " + enderecoContratada.getEstado() + ", CEP: "
				+ enderecoContratada.getCep() + ", Telefone: " + contratada.getTelefone() + ", E-mail: "
				+ contratada.getEmail()
				+ ", tem entre si justo e contratado a prestação de serviços autônomos, mediante os termos e condições seguintes:\r\n");
		document.add(contratadaP);

		Paragraph clausula1 = new Paragraph();
		clausula1.setSpacingBefore(7);
		clausula1.setSpacingAfter(7);
		clausula1.setFont(negrito);
		clausula1.add("CLÁUSULA PRIMEIRA:");
		document.add(clausula1);

		Paragraph desc1 = new Paragraph();
		desc1.setFont(normal);
		desc1.setSpacingAfter(4);
		desc1.add(
				"1.1 O presente Contrato tem por objetivo a prestação de serviços de produção de penteado e maquiagem pela CONTRATADA à CONTRATANTE. A presente contratação destina-se a:\r\n");
		document.add(desc1);

		List servicoList = new List(List.UNORDERED);
		servicoList.setIndentationLeft(20);

		for (Servico servico : contrato.getServicos()) {
			TipoServico tipoServico = servico.getTipoServico();

			ListItem listItem = null;
			switch (tipoServico) {
			case MAQUIAGEM:
				listItem = new ListItem("Maquiagem");
				break;
			case MAQUIAGEM_INFANTIL:
				listItem = new ListItem("Maquiagem para Dama de Honra");
				break;
			case MAQUIAGEM_NOIVA:
				listItem = new ListItem("Maquiagem de noiva para Dia do Casamento");
				break;
			case PENTEADO:
				listItem = new ListItem("Penteado");
				break;
			case PENTEADO_INFANTIL:
				listItem = new ListItem("Penteado para Dama de Honra");
				break;
			case PENTEADO_NOIVA:
				listItem = new ListItem("Penteado de noiva para Dia do Casamento");
				break;
			case PRODUCAO:
				listItem = new ListItem("Maquiagem e Penteado");
				break;
			case PRODUCAO_INFANTIL:
				listItem = new ListItem("Maquiagem e Penteado para Dama de Honra");
				break;
			case PRODUCAO_MAE_NOIVA:
				listItem = new ListItem("Maquiagem e Penteado para Mãe da Noiva");
				break;
			case PRODUCAO_NOIVA:
				listItem = new ListItem("Maquiagem e Penteado de noiva para Dia do Casamento");
				break;
			case PRODUCAO_NOIVA_PREVIA:
				servicoList.add(new ListItem("Maquiagem e Penteado de noiva para Prévia"));

				listItem = new ListItem("Maquiagem e Penteado de noiva para Dia do Casamento");
				break;
			case PRODUCAO_NOIVA_PREVIA_PREVIA_ROMANTICA:
				servicoList.add(new ListItem("Maquiagem para Prévia Romântica"));
				servicoList.add(new ListItem("Maquiagem e Penteado de noiva para Prévia"));

				listItem = new ListItem("Maquiagem e Penteado de noiva para Dia do Casamento");

				break;
			default:
				break;
			}
			servicoList.add(listItem);

		}

		document.add(servicoList);
	}
}
