package br.com.mamr.factory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;

import com.itextpdf.text.BadElementException;
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
import br.com.mamr.model.Estado;
import br.com.mamr.model.Servico;
import br.com.mamr.model.TipoEndereco;
import br.com.mamr.model.TipoServico;

public class ReportFactory {

	private static final String LOGO_FILE = ".//res//logo.jpg";
	
	public void formatDocument(Document document, Contrato contrato)
			throws DocumentException, MalformedURLException, IOException {

		logo(document);
		
		titulo(document);
		contratante(document, contrato);
	
		contratada(document, contrato);

		clausulaPrimeira(document, contrato);
		
		clausulaSegunda(document, contrato);
		
		clausulaTerceira(document, contrato);
		
		clausulaQuarta(document, contrato);
		
		clausulaQuinta(document);
		
		clausulaSexta(document);
		
		clausulaSetima(document);
		
		clausulaOitava(document);
		
		clausulaNona(document);
		
		data(document, contrato);
	}

	private void logo(Document document)
			throws BadElementException, MalformedURLException, IOException, DocumentException {
		Image image = Image.getInstance(LOGO_FILE);
		image.setAlignment(Image.ALIGN_CENTER);
		image.scaleAbsolute(300, 100);
		document.add(image);
	}


	private void contratante(Document document, Contrato contrato)
			throws DocumentException {
		
		Contratante contratante = contrato.getContratante();
		Endereco enderecoContratante = contratante.getEndereco();
		
		Font normal = new Font();
		normal.setStyle(Font.NORMAL);
		normal.setSize(11);
		
		Paragraph contratanteP = new Paragraph();
		contratanteP.setFont(normal);
		contratanteP.add("CONTRATANTE: " + contratante.getNome() + ", " + contratante.getNacionalidade()
				+ ", domiciliada em " + enderecoContratante.getEndereco() + ", " + enderecoContratante.getTipoEndereco().getNome()
				+ " " + enderecoContratante.getNumero() + ", " + enderecoContratante.getCidade() + "/ "
				+ enderecoContratante.getEstado().getValor() + ", CEP: " + enderecoContratante.getCep()
				+ ", portadora da cédula de identidade n° " + contratante.getRg() + " SSP/" + contratante.getEstadoRg().getValor()
				+ " e inscrita no CPF sob nº " + contratante.getCPF() + ", Telefone: " + contratante.getTelefone()
				+ ", E-mail: " + contratante.getEmail() + ".\r\n");
		document.add(contratanteP);
	}


	private void titulo(Document document) throws DocumentException {
		
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
	}


	private void contratada(Document document, Contrato contrato) throws DocumentException {
				
		Contratada contratada = contratadaBuilder();
		
		contrato.setContratada(contratada);

		Paragraph contratadaP = new Paragraph();
		contratadaP.setSpacingBefore(5);
		contratadaP.setFont(getFontNormal());
		contratadaP.add("CONTRATADA: " + contratada.getNomeEmpresa() + ", na pessoa de " + contratada.getNome()
				+ ", CNPJ: " + contratada.getCNPJ() + ", " + contratada.getEndereco().getEndereco() + ", "
				+ contratada.getEndereco().getTipoEndereco().getNome() + " " + contratada.getEndereco().getNumero() + ", "
				+ contratada.getEndereco().getCidade() + "/ " + contratada.getEndereco().getEstado().getValor() + ", CEP: "
				+ contratada.getEndereco().getCep() + ", Telefone: " + contratada.getTelefone() + ", E-mail: "
				+ contratada.getEmail()
				+ ", tem entre si justo e contratado a prestação de serviços autônomos, mediante os termos e condições seguintes:\r\n");
		document.add(contratadaP);
	}


	private void clausulaPrimeira(Document document, Contrato contrato)
			throws DocumentException {
		
		subtitulo(document, "CLÁUSULA PRIMEIRA:");
		
		paragrafoNormal(document, "1.1 O presente Contrato tem por objetivo a prestação de serviços de produção de penteado e maquiagem pela CONTRATADA à CONTRATANTE. A presente contratação destina-se a:\r\n");

		servicosBuilder(document, contrato);
		
		paragrafoNormal(document, "\r\n");
				
		paragrafoNormal(document, "1.2 Os serviços decorrentes do presente Contrato, a serem prestados pela CONTRATADA à CONTRATANTE, serão executados em ação coordenada por Elaine Serpa e assistentes.\r\n");
	}
	
	private void clausulaSegunda(Document document, Contrato contrato)
			throws DocumentException {
		
		subtitulo(document, "CLÁUSULA SEGUNDA:");

		SimpleDateFormat sdfData = new SimpleDateFormat("dd/MM/yyyy");
		String dataCasamento = sdfData.format(contrato.getAgendamento().getData());
		
		SimpleDateFormat sdfHora = new SimpleDateFormat("HH:mm");
		String horaCasamento = sdfHora.format(contrato.getAgendamento().getData());
		
		paragrafoNormal(document, "2.1. A produção para a prévia de noiva fica agendada para o dia __/__/2021 às __:___, conforme estabelecido entre as partes, devendo ser finalizada às __:00. * \r\n");
		
		paragrafoNormal(document, "2.2. A produção para o dia do casamento fica agendada e confirmada para o dia " + dataCasamento + " às __:___, conforme estabelecido entre as partes, devendo ser finalizada às " + horaCasamento + ".\r\n");
		
		paragrafoNegrito(document, "Atendimento para prévia devem ser agendados para dias de segunda a quinta com antecedência mínima de 15 dias,  mediante disponibilidade de agenda da CONTRATADA.\r\n");
	}
	
	private void clausulaTerceira(Document document, Contrato contrato)
			throws DocumentException {
		subtitulo(document, "CLÁUSULA TERCEIRA:");

		paragrafoNormal(document, "3.1 Uma taxa adicional referente ao deslocamento ao local será cobrada a parte. Esse valor varia conforme a localidade onde a CONTRATANTE irá se arrumar. Conforme negociado entre CONTRATANTE e CONTRATADA esse valor será referente à:\r\n");
		
		paragrafoNegrito(document, "R$ __,00 - Para o dia da prévia de noiva, à definir.)\r\n");
		
		paragrafoNegrito(document, "R$ " + contrato.getValorDeslocamento() + ",00 - "
				+ "Para o dia da noiva, já incluso no contrato: "+ getEnderecoAtendimento(contrato));
		
		paragrafoNormal(document, "3.2. Os valores referentes a taxa de deslocamento serão pagas conforme a realização dos atendimentos à vista.\r\n");
	}
	
	private void clausulaQuarta(Document document, Contrato contrato)
			throws DocumentException {
		
		subtitulo(document, "CLÁUSULA QUARTA:");

		paragrafoNormal(document, "4.1. A CONTRATANTE pagará à CONTRATADA, pela prestação de serviços de produção de penteado e maquiagem, objeto do presente Contrato, a quantia de​ R$ "+ contrato.getValorTotal() +",00 à vista, ou:\r\n");
		
		int valorEntrada = (contrato.getValorTotal() / 100)*30;
		int valorRestante = contrato.getValorTotal() - valorEntrada;
		
		paragrafoNormal(document, "4.1.1 No valor de ​R$ " + contrato.getValorTotal() + ",00 será pago para a reserva da data e validação do contrato o valor de R$ " + valorEntrada + ",00 referente a 30% do valor do contrato.  O restante do valor R$ " + valorRestante + ",00 será pago 15 dias antes do casamento por depósito ou transferência bancária.\r\n");

		paragrafoNormal(document, "4.1.2. Pedimos o envio do comprovante de cada pagamento para o email: elaineserpa@outlook.com. \r\n" + 
				"ACEITAMOS SOMENTE PAGAMENTO A VISTA OU TRANSFERÊNCIA/DEPÓSITO EM CONTA.\r\n");
		
		paragrafoNormal(document, "4.1.3 Para o depósito em conta o valor deverá ser creditado em conta POUPANÇA, Banco do Brasil.\r\n");

		paragrafoNegrito(document, "BANCO DO BRASIL\r\n" + 
				"AG 3598-X\r\n" + 
				"CONTA POUPANÇA 29583-3\r\n" + 
				"Variação 51\r\n" + 
				"ELAINE RODRIGUES SERPA\r\n" + 
				"CPF 023.985.571-03\r\n"+
				"\r\n");
		
		paragrafoNormal(document, "4.2. A CONTRATANTE deverá reembolsar a CONTRATADA de todas as custas, despesas judiciais e extrajudiciais, bem como honorários advocatícios em que a CONTRATADA vier a incorrer para a cobrança do valor  não quitadas.\r\n");

		paragrafoNormal(document, "4.3. Serviços que não estão incluídos neste contrato serão cobrados à parte, tais como:  acessórios, arranjos e enchimentos para penteado. Tais valores deverão ser acordados entre CONTRATADA e CONTRATANTE antes da prestação dos serviços presentes neste documento.\r\n");

		paragrafoNormal(document, "4.4. Em caso de acréscimo de produções de cabelo e maquiagem, o valores cobrados serão de R$ "+TipoServico.MAQUIAGEM.getValor()+",00 para Maquiagem; R$ "+TipoServico.PENTEADO.getValor()+",00 para Penteado, e em caso do pacote promocional, o valor dos serviços fica por R$ "+TipoServico.PRODUCAO.getValor()+",00 para maquiagem e penteado.\r\n");

		paragrafoNormal(document, "4.4.1 O serviço de maquiagem já inclui a aplicação de cílios postiços.\r\n");
	}
	
	private void clausulaQuinta(Document document)
			throws DocumentException {
		subtitulo(document, "CLÁUSULA QUINTA:");

		paragrafoNormal(document, "5.1 A desistência deverá ser comunicada pela CONTRATANTE, por escrito, à CONTRATADA.");
		
		paragrafoNormal(document, "5.2 Na hipótese de desistência ser comunicada com antecedência de até 30 (trinta) dias antes da data do casamento, a CONTRATADA restituirá à CONTRATANTE o montante pago, deduzida a importância dos 30% equivalentes a reserva da data, a título de ressarcimento de despesas já incorridas, Em caso de desistência, a CONTRATADA não restituirá os 30% do valor integral pago no fechamento do contrato.  Após este prazo o valor restante previsto deverá ser pago integralmente.");
	}
	
	private void clausulaSexta(Document document)
			throws DocumentException {
		subtitulo(document, "CLÁUSULA SEXTA:");

		paragrafoNormal(document, "6.1. O presente Contrato só poderá ser rescindido nas hipóteses de falecimento ou incapacidade comprovada, caso em que a CONTRATANTE não será obrigada a pagar as prestações vincendas, mas perderá em favor da CONTRATADA as prestações já pagas.");
		
		paragrafoNormal(document, "6.2. Se a CONTRATANTE requisitar a rescisão contratual, poderá reaver o valor eventualmente pago aos serviços não prestados, mediante aviso por escrito até 30 dias antes da data do evento, deduzido o valor de 30% referente a reserva dos serviços e acrescentada ao valor de 10% do valor total do contrato como multa rescisória.");
		
		paragrafoNormal(document, "6.3. Se for a CONTRATADA quem requisitar a rescisão, devolverá a quantia paga pela CONTRATANTE referente aos serviços que não mais serão prestados, acrescentada ao valor de 10% do valor total do contrato como multa rescisória.");

		paragrafoNormal(document, "6.4. Em casos de adoecimento, enfermidades ou situações alheias a vontade da CONTRATADA, esta poderá consultar a CONTRATANTE a possibilidade enviar um profissional da mesma competência para realizar o atendimento, caso a CONTRATANTE não esteja de acordo, ficará a sujeito a CONTRATADA estornar os valores pagos (se for o caso) de serviços que não foram realizados, tendo em vista os encargos na Cláusula 5.2.");
		
	}
	
	private void clausulaSetima(Document document)
			throws DocumentException {
		subtitulo(document, "CLÁUSULA SÉTIMA:");
		
		paragrafoNormal(document, "7.1. Fica expressamente entendido que os materiais utilizados destinam-se à utilização exclusiva para as pessoas inclusas no contrato, que poderão registrá-los, de qualquer forma fotográfica.");

		paragrafoNormal(document, "7.2. A CONTRATADA poderá usar para divulgação de seu trabalho fotos dos serviços realizados no dia, em suas redes sociais e demais meios de divulgação de seu trabalho.");

	}
	
	private void clausulaOitava(Document document)
			throws DocumentException {
		subtitulo(document, "CLÁUSULA OITAVA:");

		paragrafoNormal(document, "8.1. Atrasos só serão permitidos até 20 minutos após o horário marcado, esta norma vale para ambas as partes tanto para a CONTRATANTE quanto para a CONTRATADA.");

		paragrafoNormal(document, "8.1.1 Caso a CONTRATADA se atrase mais 30 minutos fica obrigada a esta a remarcar outro horário ou reembolsar a CONTRATANTE o valor do serviço deste dia.");
		
		paragrafoNormal(document, "8.2. Caso a CONTRATANTE solicite remarcação de horário, só será aceito com no mínimo 72 horas de antecedência do horário do evento. Após este prazo fica a critério da CONTRATADA realizar a remarcação sem nenhum custo adicional. Caso a remarcação ocorrer após as 72 horas e antes do horário marcado, será cobrada 50% do valor do serviço pela CONTRATADA a CONTRATANTE.");
	}

	
	private void clausulaNona(Document document)
			throws DocumentException {
		subtitulo(document, "CLÁUSULA NONA:");

		paragrafoNormal(document, "9.1. A vigência do presente coincidirá com o período de vigência da data do casamento.  E por estarem justos e contratados, assinam o presente em duas vias de igual teor.  ");
	}

	private void paragrafoNormal(Document document, String paragrafo) throws DocumentException {
				
		Paragraph desc1 = new Paragraph();
		desc1.setFont(getFontNormal());
		desc1.setIndentationLeft(10);
		desc1.setSpacingAfter(10);
		desc1.add(paragrafo);
		document.add(desc1);
	}
	
	private void data(Document document, Contrato contrato) throws DocumentException {
		
		SimpleDateFormat sdfData = new SimpleDateFormat("dd/MM/yyyy");
		String dataContrato = sdfData.format(contrato.getDataContrato());
				
		Paragraph desc1 = new Paragraph();
		desc1.setFont(getFontNegrito());
		desc1.setSpacingBefore(70);
		desc1.setSpacingAfter(50);
		desc1.setIndentationLeft(50);
		desc1.add("Brasília, " + dataContrato);
		document.add(desc1);
		
		Paragraph contratanteLinha = new Paragraph();
		contratanteLinha.setFont(getFontNormal());
		contratanteLinha.setIndentationLeft(50);
		contratanteLinha.setSpacingAfter(3);
		contratanteLinha.add("__________________________________________________________");
		document.add(contratanteLinha);
		
		Paragraph contratanteAssinatura = new Paragraph();
		contratanteAssinatura.setFont(getFontNormal());
		contratanteAssinatura.setIndentationLeft(50);
		contratanteAssinatura.setSpacingAfter(70);
		contratanteAssinatura.add("Contratante: "+contrato.getContratante().getNome());
		document.add(contratanteAssinatura);
		
		Paragraph contratadaLinha = new Paragraph();
		contratadaLinha.setFont(getFontNormal());
		contratadaLinha.setIndentationLeft(50);
		contratadaLinha.setSpacingAfter(3);
		contratadaLinha.add("__________________________________________________________");
		document.add(contratadaLinha);
		
		Paragraph contratadaAssinatura = new Paragraph();
		contratadaAssinatura.setFont(getFontNormal());
		contratadaAssinatura.setIndentationLeft(50);
		contratadaAssinatura.setSpacingAfter(50);
		contratadaAssinatura.add("Contratada: "+contrato.getContratada().getNome());
		document.add(contratadaAssinatura);
	}
	
	private void paragrafoNegrito(Document document, String paragrafo) throws DocumentException {
		
		Paragraph desc1 = new Paragraph();
		desc1.setFont(getFontNegrito());
		desc1.setSpacingAfter(4);
		desc1.setIndentationLeft(40);
		desc1.add(paragrafo);
		document.add(desc1);
	}
	
	private void subtitulo(Document document, String subtitulo) throws DocumentException {
		
		Paragraph clausula1 = new Paragraph();
		clausula1.setSpacingBefore(7);
		clausula1.setSpacingAfter(7);
		clausula1.setFont(getFontNegrito());
		clausula1.add(subtitulo);
		document.add(clausula1);	
	}
	
	private Font getFontNormal() {
		Font normal = new Font();
		normal.setStyle(Font.NORMAL);
		normal.setSize(11);
		return normal;
	}
	
	private Font getFontNegrito() {
		Font normal = new Font();
		normal.setStyle(Font.BOLD);
		normal.setSize(11);
		return normal;
	}
	
	private void servicosBuilder(Document document, Contrato contrato) throws DocumentException {
		List servicoList = new List(List.UNORDERED);
		servicoList.setIndentationLeft(20);

		for (Servico servico : contrato.getServicos()) {
			TipoServico tipoServico = servico.getTipo();

			ListItem listItem = null;
			switch (tipoServico) {
			case MAQUIAGEM:
				listItem = new ListItem(getDescricaoServico(tipoServico, "Maquiagem"));
				break;
			case MAQUIAGEM_INFANTIL:
				listItem = new ListItem(getDescricaoServico(tipoServico, "Maquiagem para Dama de Honra"));
				break;
			case MAQUIAGEM_NOIVA:
				listItem = new ListItem(getDescricaoServico(tipoServico, "Maquiagem de noiva para Dia do Casamento"));
				break;
			case PENTEADO:
				listItem = new ListItem(getDescricaoServico(tipoServico, "Penteado"));
				break;
			case PENTEADO_INFANTIL:
				listItem = new ListItem(getDescricaoServico(tipoServico, "Penteado para Dama de Honra"));
				break;
			case PENTEADO_NOIVA:
				listItem = new ListItem(getDescricaoServico(tipoServico, "Penteado de noiva para Dia do Casamento"));
				break;
			case PRODUCAO:
				listItem = new ListItem(getDescricaoServico(tipoServico, "Maquiagem e Penteado"));
				break;
			case PRODUCAO_INFANTIL:
				listItem = new ListItem(getDescricaoServico(tipoServico, "Maquiagem e Penteado para Dama de Honra"));
				break;
			case PRODUCAO_MAE_NOIVA:
				listItem = new ListItem(getDescricaoServico(tipoServico, "Maquiagem e Penteado para Mãe da Noiva"));
				break;
			case PRODUCAO_NOIVA:
				listItem = new ListItem(getDescricaoServico(tipoServico, "Maquiagem e Penteado de noiva para Dia do Casamento"));
				break;
			case PRODUCAO_NOIVA_PREVIA:
				servicoList.add(new ListItem(getDescricaoServico(tipoServico, "Maquiagem e Penteado de noiva para Prévia")));

				listItem = new ListItem(getDescricaoServico(tipoServico, "Maquiagem e Penteado de noiva para Dia do Casamento"));
				break;
			case PRODUCAO_NOIVA_PREVIA_PREVIA_ROMANTICA:
				servicoList.add(new ListItem("Maquiagem para Prévia Romântica"));
				servicoList.add(new ListItem("Maquiagem e Penteado de noiva para Prévia"));
				servicoList.add(new ListItem("Maquiagem e Penteado de noiva para Dia do Casamento"));

				listItem = new ListItem("Totalizando o valor de R$" + tipoServico.getValor()+",00");
				break;
			default:
				break;
			}
			
			contrato.setValorTotal( contrato.getValorTotal() + tipoServico.getValor() );
			
			servicoList.add(listItem);

		}
		
		if(contrato.getValorDeslocamento() != 0) {
			servicoList.add(new ListItem("Deslocamento para atendimento em " + getEnderecoAtendimento(contrato) + ", no valor de R$ "+contrato.getValorDeslocamento()+",00"));
		}

		document.add(servicoList);
	}
	
	private String getDescricaoServico(TipoServico tipoServico, String titulo) {
		return titulo + ", no valor de R$ " + tipoServico.getValor() + ",00";

	}
	
	private String getEnderecoAtendimento(Contrato contrato) {
		return contrato.getEndereco().getEndereco() + ", "
				+ contrato.getEndereco().getTipoEndereco().getNome() + " " + contrato.getEndereco().getNumero() + ", "
				+ contrato.getEndereco().getCidade() + " - " + contrato.getEndereco().getEstado().getValor();
	}

	
	private Contratada contratadaBuilder() {
		
		Contratada contratada = new Contratada();
		contratada.setCNPJ("312312312323");
		contratada.setEmail("elaineserpa@outlook.com");
		contratada.setNome("Elaine Rodrigues Serpa");
		contratada.setNomeEmpresa("Elaine Serpa Make Up");
		contratada.setTelefone("(61) 98167-6499");
		contratada.setNacionalidade("Brasileira");
		
		Endereco enderecoContratada = new Endereco();
		enderecoContratada.setCep("71880-038");
		enderecoContratada.setCidade("Riacho Fundo II");
		
		TipoEndereco tipoEndereco = new TipoEndereco();
		tipoEndereco.setNome("Casa");
		tipoEndereco.setValor("CASA");
		enderecoContratada.setTipoEndereco(tipoEndereco);
		
		Estado estadoContratada = new Estado();
		estadoContratada.setNome("Brasília");
		estadoContratada.setValor("DF");
		enderecoContratada.setEstado(estadoContratada);
		
		enderecoContratada.setEndereco("QN 7C Conjunto 08");
		enderecoContratada.setNumero("14");
		contratada.setEndereco(enderecoContratada);
		
		return contratada;
	}
}
