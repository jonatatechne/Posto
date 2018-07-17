package blockly;

import cronapi.*;
import cronapi.rest.security.CronappSecurity;
import java.util.concurrent.Callable;

@CronapiMetaData(type = "blockly")
@CronappSecurity
public class TelaCarro {

	public static final int TIMEOUT = 300;

	/**
	 *
	 * @param Entidade
	 * @return Var
	 */
	// TelaCarro
	public static Var calculaConsumoMedioCarro(Var Entidade) throws Exception {
		return new Callable<Var>() {

			private Var consultaAbastecimento = Var.VAR_NULL;
			private Var litros = Var.VAR_NULL;
			private Var consumo = Var.VAR_NULL;
			private Var somarConsumo = Var.VAR_NULL;
			private Var qtdeAbastecimentos = Var.VAR_NULL;

			public Var call() throws Exception {
				consultaAbastecimento = cronapi.database.Operations.query(Var.valueOf("app.entity.Abastecimento"),
						Var.valueOf("select a.valor, a.litro, a.km from Abastecimento a where a.carro.id = :carroId"),
						Var.valueOf("carroId", cronapi.object.Operations.getObjectField(Entidade, Var.valueOf("id"))));
				if (cronapi.database.Operations.hasElement(consultaAbastecimento).getObjectAsBoolean()) {
					while (cronapi.database.Operations.hasElement(consultaAbastecimento).getObjectAsBoolean()) {
						litros = cronapi.math.Operations.divisor(
								cronapi.database.Operations.getField(consultaAbastecimento, Var.valueOf("this[0]")),
								cronapi.database.Operations.getField(consultaAbastecimento, Var.valueOf("this[1]")));
						consumo = cronapi.math.Operations.divisor(
								cronapi.database.Operations.getField(consultaAbastecimento, Var.valueOf("this[2]")),
								litros);
						somarConsumo = cronapi.math.Operations.sum(somarConsumo, consumo);
						qtdeAbastecimentos = cronapi.math.Operations.sum(qtdeAbastecimentos, Var.valueOf(1));
						cronapi.database.Operations.next(litros);
					} // end while
				} else {
					somarConsumo = Var.valueOf(0);
					qtdeAbastecimentos = Var.valueOf(1);
				}
				return cronapi.math.Operations.divisor(somarConsumo, qtdeAbastecimentos);
			}
		}.call();
	}

}
