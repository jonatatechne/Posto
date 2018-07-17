window.blockly = window.blockly || {};
window.blockly.js = window.blockly.js || {};
window.blockly.js.blockly = window.blockly.js.blockly || {};
window.blockly.js.blockly.TelaAbastecimento = window.blockly.js.blockly.TelaAbastecimento
		|| {};

/**
 * TelaAbastecimento
 */
window.blockly.js.blockly.TelaAbastecimento.validarDuzentosLitros = function() {

	var item, litros;
	litros = this.cronapi.screen.getValueOfField("Abastecimento.active.valor")
			/ this.cronapi.screen.getValueOfField("Abastecimento.active.litro");
	if (litros > 200) {
		this.cronapi.screen.notify('error',
				'Valor abastecido maior que 200 Litros');
		this.cronapi.screen.hideComponent("botao-salvar");
	} else {
		this.cronapi.screen.showComponent("botao-salvar");
	}
}
