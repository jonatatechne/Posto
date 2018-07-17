package blockly;

import cronapi.*;
import cronapi.rest.security.CronappSecurity;
import java.util.concurrent.Callable;

@CronapiMetaData(type = "blockly")
@CronappSecurity
public class TelaPosto {

	public static final int TIMEOUT = 300;

	/**
	 *
	 * @return Var
	 */
	// TelaPosto
	public static Var buscarCep() throws Exception {
		return new Callable<Var>() {

			private Var url = Var.VAR_NULL;
			private Var dadosCep = Var.VAR_NULL;

			public Var call() throws Exception {
				url = Var.valueOf(Var.valueOf("https://viacep.com.br/ws/").toString()
						+ cronapi.screen.Operations.getValueOfField(Var.valueOf("Posto.active.cep")).toString()
						+ Var.valueOf("/json").toString());
				System.out.println(url.getObjectAsString());
				dadosCep = cronapi.util.Operations.getURLFromOthers(Var.valueOf("GET"), Var.valueOf("application/json"),
						url, Var.VAR_NULL, Var.VAR_NULL);
				dadosCep = cronapi.json.Operations.toJson(dadosCep);
				cronapi.util.Operations.callClientFunction(Var.valueOf("cronapi.screen.changeValueOfField"),
						Var.valueOf("Posto.active.logradouro"),
						cronapi.json.Operations.getJsonOrMapField(dadosCep, Var.valueOf("logradouro")));
				cronapi.util.Operations.callClientFunction(Var.valueOf("cronapi.screen.changeValueOfField"),
						Var.valueOf("Posto.active.bairro"),
						cronapi.json.Operations.getJsonOrMapField(dadosCep, Var.valueOf("bairro")));
				cronapi.util.Operations.callClientFunction(Var.valueOf("cronapi.screen.changeValueOfField"),
						Var.valueOf("Posto.active.cidade"),
						cronapi.json.Operations.getJsonOrMapField(dadosCep, Var.valueOf("cidade")));
				cronapi.util.Operations.callClientFunction(Var.valueOf("cronapi.screen.changeValueOfField"),
						Var.valueOf("Posto.active.uf"),
						cronapi.json.Operations.getJsonOrMapField(dadosCep, Var.valueOf("uf")));
				return Var.VAR_NULL;
			}
		}.call();
	}

}
