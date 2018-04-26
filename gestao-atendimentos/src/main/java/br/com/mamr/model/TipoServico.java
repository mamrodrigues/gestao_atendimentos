package br.com.mamr.model;

import java.util.HashMap;
import java.util.Map;

public enum TipoServico {

	PENTEADO("PENTEADO", 160), MAQUIAGEM("MAQUIAGEM", 170), PRODUCAO("PRODUCAO", 280), PENTEADO_NOIVA("PENTEADO_NOIVA",
			350), MAQUIAGEM_NOIVA("MAQUIAGEM_NOIVA", 450), PRODUCAO_NOIVA("PRODUCAO_NOIVA",
					600), PRODUCAO_NOIVA_PREVIA("PRODUCAO_NOIVA_PREVIA", 1100), PRODUCAO_NOIVA_PREVIA_PREVIA_ROMANTICA(
							"PRODUCAO_NOIVA_PREVIA_PREVIA_ROMANTICA", 1260), PRODUCAO_MAE_NOIVA("PRODUCAO_MAE_NOIVA",
									240), PENTEADO_INFANTIL("PENTEADO_INFANTIL", 120), MAQUIAGEM_INFANTIL(
											"MAQUIAGEM_INFANTIL", 80), PRODUCAO_INFANTIL("PRODUCAO_INFANTIL", 180);

	private final String tipo;
	private final Integer valor;
	private static Map<String, Integer> map;

	private TipoServico(String tipo, Integer valor) {
		this.tipo = tipo;
		this.valor = valor;
	}

	public Integer getValor() {
		return valor;
	}

	public static Integer getValor(String tipo) {
		if (map == null) {
			map = initializeMapping();
		}
		if (map.containsKey(tipo)) {
			return map.get(tipo);
		}
		return null;
	}

	private static Map<String, Integer> initializeMapping() {
		Map<String, Integer> enumTipo = new HashMap<String, Integer>();
		for (TipoServico ep : TipoServico.values()) {
			enumTipo.put(ep.tipo, ep.valor);
		}
		return enumTipo;
	}

}
