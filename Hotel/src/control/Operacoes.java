package control;

import java.text.DecimalFormat;
import java.util.ArrayList;

import model.Output;
import model.dao.HospedagemDao;

/**
 * Classe que guardará as operações entre o banco e as views.
 * 
 * @author Emmanuel
 *
 */
public class Operacoes {

	// Criando e instanciando um formatador de float para depois ursamos. Ele está
	// definido para arredondar até duas casas decimais.
	private static DecimalFormat df = new DecimalFormat("0.00");

	/**
	 * Esse método calcula as média de reservas de todos os meses.
	 * 
	 * @return A lista de {@code Output} pronta para ser inserida no banco.
	 */
	public static ArrayList<Output> reservaMensal() {

		// Criando e instanciando uma ArryList do tipo Output. Este será nosso retorno.
		ArrayList<Output> list = new ArrayList<Output>();
		// Criando e definindo um array de String que vai armazenar os meses. Depois
		// iremos inserir eles junto com os valores mensais.
		String[] meses = { "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro",
				"Outubro", "Novembro", "Dezembro" };
		// Criando e instanciando o objeto que irá manipular o banco para fazermos as
		// consultas.
		HospedagemDao hDao = HospedagemDao.getInstancia();

		// Iniciando um FOR que irá rodar 12 vezes. A variável {i} é igual ao número de
		// meses.
		for (int i = 1; i <= 12; i++) {
			// Crio um array list do tipo Object para receber a lista de resultados. Eu
			// chamo o método que retorna a lista passando como parametro a data pré
			// formatada com a variável I no meio da String para indicar qual o mes que eu
			// quero.
			ArrayList<Object> mes = hDao.mediaReservasMes("'2017/" + i + "/01'", "'2017/" + i + "/31'");
			// Declarando a variável {numeroDeDiasNoMes} para guardarmos quantos dias tem o
			// mes. Sabemos que alguns tem 30 outros 31 e Fevereiro de 2017 tem 28, além de
			// que, eles não possuem uma forma de intercalar muito padronizada. Essa
			// variável vai nos ajudar a não reescrever código.
			int numeroDeDiasNoMes = 0;
			// ******* INÍCIO DAS VERIFICAÇÕES *******
			// Verifico se o mês é menor ou igual a 6, ou seja, se ele está entre Janeiro ou
			// Junho.
			if (i <= 6) {
				// Verifico se o mês é IMPAR.
				if (i % 2 != 0) {
					// Se for IMPAR e estiver entre Janeiro e Junho ele tem 31 dias.
					numeroDeDiasNoMes = 31;
					// Se ele não é IMPAR ele só pode ser PAR. Verifico se ele é Fevereiro, ou seja,
					// 2.
				} else if (i == 2) {
					// Se for Fevereiro ele tem 28 dias.
					numeroDeDiasNoMes = 28;
					// Se não for Fevereiro ele só pode ser Abril ou Junho.
				} else {
					// Sendo esses, a variável recebe 30 dias.
					numeroDeDiasNoMes = 30;
				}
				// Caso o mês esteja acima de 6, ou seja, a partir de Julho. Eu verifico...
			} else {
				// Se ele é PAR.
				if (i % 2 == 0) {
					// Se ele for PAR só pode ser IMPAR, ou seja, Agosto, Outubro ou Dezembro.
					numeroDeDiasNoMes = 31;
					// Se ele não for PAR eu verifico se ele é igual a 7, ou seja, se ele é o Julho.
				} else if (i == 7) {
					// Caso afirmativo eu atribuo 31 dias à variável.
					numeroDeDiasNoMes = 31;
					// Se ele não for o Julho e nem é PAR então ele só pode ser Setembro ou
					// Novembro.
				} else {
					// Atribuo 30 dias para a variável.
					numeroDeDiasNoMes = 30;
				}
			}
			// *******FIM DAS VERIFICAÇÕES *******
			// Adiciono a nossa variável {list}, um novo objeto do tipo {Output}, primeiro
			// eu passo o array {meses} na posição de {i} menos 1, ou seja, no mês
			// correspondente ao mês que nós buscamos. O segundo parâmetro que passo é o
			// resultado de uma conta, e é aqui que está a média. Eu divído quantos
			// elementos temos no {ArrayList} {mes}, através do método {size()} que é do
			// próprio {ArrayList}, pelo número de dias que acabamos de verificar. Eu
			// preciso realizar um casting dos dois números para {float}, caso o contrário o
			// resultado da conta será inteiro. Ainda nesse comando eu formato ele para duas
			// casas decimais.
			list.add(new Output(meses[i - 1], df.format((float) mes.size() / (float) numeroDeDiasNoMes)));
			// Nessa linha é a útima parte da iteração do FOR. Depois de fazer isso ele
			// repetirá essas ações mais 11 vezes até passar por todos os meses.
		}
		// \Agora com a {list} completa podemos retorna-la.
		return list;
	}

	/**
	 * Método que devolve o número de reservas anual.
	 * 
	 * @return O número médio de reservas anual.
	 */
	public static String reservaAnual() {

		// Instanciando o objeto de acesso ao banco.
		HospedagemDao hDao = HospedagemDao.getInstancia();

		// Declarando o array de Object e populando ele com o valor de todos os
		// registros do ano.
		ArrayList<Object> list = hDao.mediaReservasMes("'2017-01-01'", "'2017-12-31'");

		// Executando e a conta do total de registros dividos pelo número de dias do
		// ano, convertendo para duas casas decimais e retornando para a view.
		return df.format((float) list.size() / 365.0);
	}

	/**
	 * Método que devolve a média de noites mensal.
	 * 
	 * @return A lista de {@code Output} pronta para ser inserida no banco.
	 */
	public static ArrayList<Output> noitesMensal() {
		// Criando e instanciando uma ArryList do tipo Output. Este será nosso retorno.
		ArrayList<Output> list = new ArrayList<Output>();
		// Criando e definindo um array de String que vai armazenar os meses. Depois
		// iremos inserir eles junto com os valores mensais.
		String[] meses = { "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro",
				"Outubro", "Novembro", "Dezembro" };
		// Criando e instanciando o objeto que irá manipular o banco para fazermos as
		// consultas.
		HospedagemDao hDao = HospedagemDao.getInstancia();

		// Iniciando um FOR que irá rodar 12 vezes. A variável {i} é igual ao número de
		// meses.
		for (int i = 1; i <= 12; i++) {
			// Crio um array list do tipo Integer para receber a lista de resultados. Eu
			// chamo o método que retorna a lista passando como parametro a data pré
			// formatada com a variável I no meio da String para indicar qual o mes que eu
			// quero.
			ArrayList<Integer> mes = hDao.mediaNoitesMes("'2017/" + i + "/01'", "'2017/" + i + "/31'");
			// Declarando a variável {numeroDeDiasNoMes} para guardarmos quantos dias tem o
			// mes. Sabemos que alguns tem 30 outros 31 e Fevereiro de 2017 tem 28, além de
			// que, eles não possuem uma forma de intercalar muito padronizada. Essa
			// variável vai nos ajudar a não reescrever código.
			int numeroDeDiasNoMes = 0;
			// ******* INÍCIO DAS VERIFICAÇÕES *******
			// Verifico se o mês é menor ou igual a 6, ou seja, se ele está entre Janeiro ou
			// Junho.
			if (i <= 6) {
				// Verifico se o mês é IMPAR.
				if (i % 2 != 0) {
					// Se for IMPAR e estiver entre Janeiro e Junho ele tem 31 dias.
					numeroDeDiasNoMes = 31;
					// Se ele não é IMPAR ele só pode ser PAR. Verifico se ele é Fevereiro, ou seja,
					// 2.
				} else if (i == 2) {
					// Se for Fevereiro ele tem 28 dias.
					numeroDeDiasNoMes = 28;
					// Se não for Fevereiro ele só pode ser Abril ou Junho.
				} else {
					// Sendo esses, a variável recebe 30 dias.
					numeroDeDiasNoMes = 30;
				}
				// Caso o mês esteja acima de 6, ou seja, a partir de Julho. Eu verifico...
			} else {
				// Se ele é PAR.
				if (i % 2 == 0) {
					// Se ele for PAR só pode ser IMPAR, ou seja, Agosto, Outubro ou Dezembro.
					numeroDeDiasNoMes = 31;
					// Se ele não for PAR eu verifico se ele é igual a 7, ou seja, se ele é o Julho.
				} else if (i == 7) {
					// Caso afirmativo eu atribuo 31 dias à variável.
					numeroDeDiasNoMes = 31;
					// Se ele não for o Julho e nem é PAR então ele só pode ser Setembro ou
					// Novembro.
				} else {
					// Atribuo 30 dias para a variável.
					numeroDeDiasNoMes = 30;
				}
			}
			// *******FIM DAS VERIFICAÇÕES *******
			// Declaro uma variável para guardar o conteudo da soma.
			// Inicio um FOR para somar todas as noites do mês.
			int somaDasNoites = 0;
			for (int noites : mes) {
				somaDasNoites += noites;
			}
			// Adiciono a nossa variável {list}, um novo objeto do tipo {Output}, primeiro
			// eu passo o array {meses} na posição de {i} menos 1, ou seja, no mês
			// correspondente ao mês que nós buscamos. O segundo parâmetro que passo é o
			// resultado de uma conta, e é aqui que está a média. Eu divído o número de
			// noites que foram reservados nesse mês, pelo número de dias que acabamos de
			// verificar. Eu preciso realizar um casting dos dois números para {float}, caso
			// o contrário o resultado da conta será inteiro. Ainda nesse comando eu formato
			// ele para duas casas decimais.
			list.add(new Output(meses[i - 1], df.format((float) somaDasNoites / (float) numeroDeDiasNoMes)));
			// Nessa linha é a útima parte da iteração do FOR. Depois de fazer isso ele
			// repetirá essas ações mais 11 vezes até passar por todos os meses.
		}
		// Agora com a {list} completa podemos retorna-la.
		return list;
	}

	/**
	 * Método que devolve a média de noites anual.
	 * 
	 * @return O número médio de noites anual.
	 */
	public static String noitesAnual() {
		// Instanciando o objeto de acesso ao banco.
		HospedagemDao hDao = HospedagemDao.getInstancia();

		// Declarando o array de Integer e populando ele com o valor de todos os
		// registros do ano.
		ArrayList<Integer> list = hDao.mediaNoitesMes("'2017-01-01'", "'2017-12-31'");

		// Declaro uma variável para guardar o conteudo da soma.
		// Inicio um FOR para somar todas as noites do mês.
		int somaDasNoites = 0;
		for (int noites : list) {
			somaDasNoites += noites;
		}
		// Executando a conta do total de noites dividos pelo número de dias do
		// ano, convertendo para duas casas decimais e retornando para a view.
		return df.format((float) somaDasNoites / 365.0);
	}

	/**
	 * Método que devolve a média de diaria mensal.
	 * 
	 * @return A lista de {@code Output} pronta para ser inserida no banco.
	 */
	public static ArrayList<Output> diariaMensal() {
		// Criando e instanciando uma ArryList do tipo Output. Este será nosso retorno.
		ArrayList<Output> list = new ArrayList<Output>();
		// Criando e definindo um array de String que vai armazenar os meses. Depois
		// iremos inserir eles junto com os valores mensais.
		String[] meses = { "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro",
				"Outubro", "Novembro", "Dezembro" };
		// Criando e instanciando o objeto que irá manipular o banco para fazermos as
		// consultas.
		HospedagemDao hDao = HospedagemDao.getInstancia();

		// Iniciando um FOR que irá rodar 12 vezes. A variável {i} é igual ao número de
		// meses.
		for (int i = 1; i <= 12; i++) {
			// Crio um array list do tipo Integer para receber a lista de resultados. Eu
			// chamo o método que retorna a lista passando como parametro a data pré
			// formatada com a variável I no meio da String para indicar qual o mes que eu
			// quero.
			ArrayList<Float> mes = hDao.mediaDiariaMes("'2017/" + i + "/01'", "'2017/" + i + "/31'");
			// Declarando a variável {numeroDeDiasNoMes} para guardarmos quantos dias tem o
			// mes. Sabemos que alguns tem 30 outros 31 e Fevereiro de 2017 tem 28, além de
			// que, eles não possuem uma forma de intercalar muito padronizada. Essa
			// variável vai nos ajudar a não reescrever código.
			int numeroDeDiasNoMes = 0;
			// ******* INÍCIO DAS VERIFICAÇÕES *******
			// Verifico se o mês é menor ou igual a 6, ou seja, se ele está entre Janeiro ou
			// Junho.
			if (i <= 6) {
				// Verifico se o mês é IMPAR.
				if (i % 2 != 0) {
					// Se for IMPAR e estiver entre Janeiro e Junho ele tem 31 dias.
					numeroDeDiasNoMes = 31;
					// Se ele não é IMPAR ele só pode ser PAR. Verifico se ele é Fevereiro, ou seja,
					// 2.
				} else if (i == 2) {
					// Se for Fevereiro ele tem 28 dias.
					numeroDeDiasNoMes = 28;
					// Se não for Fevereiro ele só pode ser Abril ou Junho.
				} else {
					// Sendo esses, a variável recebe 30 dias.
					numeroDeDiasNoMes = 30;
				}
				// Caso o mês esteja acima de 6, ou seja, a partir de Julho. Eu verifico...
			} else {
				// Se ele é PAR.
				if (i % 2 == 0) {
					// Se ele for PAR só pode ser IMPAR, ou seja, Agosto, Outubro ou Dezembro.
					numeroDeDiasNoMes = 31;
					// Se ele não for PAR eu verifico se ele é igual a 7, ou seja, se ele é o Julho.
				} else if (i == 7) {
					// Caso afirmativo eu atribuo 31 dias à variável.
					numeroDeDiasNoMes = 31;
					// Se ele não for o Julho e nem é PAR então ele só pode ser Setembro ou
					// Novembro.
				} else {
					// Atribuo 30 dias para a variável.
					numeroDeDiasNoMes = 30;
				}
			}
			// *******FIM DAS VERIFICAÇÕES *******
			// Declaro uma variável para guardar o conteudo da soma.
			// Inicio um FOR para somar todas as noites do mês.
			float somaDasDiarias = 0.0F;
			for (float diaria : mes) {
				somaDasDiarias += diaria;
			}
			// Adiciono a nossa variável {list}, um novo objeto do tipo {Output}, primeiro
			// eu passo o array {meses} na posição de {i} menos 1, ou seja, no mês
			// correspondente ao mês que nós buscamos. O segundo parâmetro que passo é o
			// resultado de uma conta, e é aqui que está a média. Eu divído o total de
			// diarias que foram registradas esse mês, pelo número de dias que acabamos de
			// verificar. Eu preciso realizar um casting dos dois números para {float}, caso
			// o contrário o resultado da conta será inteiro. Ainda nesse comando eu formato
			// ele para duas casas decimais.
			list.add(new Output(meses[i - 1], df.format((float) somaDasDiarias / (float) numeroDeDiasNoMes)));
			// Nessa linha é a útima parte da iteração do FOR. Depois de fazer isso ele
			// repetirá essas ações mais 11 vezes até passar por todos os meses.
		}
		// Agora com a {list} completa podemos retorna-la.
		return list;
	}

	/**
	 * Método que devolve a média de diárias anual.
	 * 
	 * @return O número médio de diárias anual.
	 */
	public static String diariaAnual() {
		// Instanciando o objeto de acesso ao banco.
		HospedagemDao hDao = HospedagemDao.getInstancia();

		// Declarando o array de Float e populando ele com o valor de todos os
		// registros do ano.
		ArrayList<Float> list = hDao.mediaDiariaMes("'2017-01-01'", "'2017-12-31'");

		// Declaro uma variável para guardar o conteudo da soma.
		// Inicio um FOR para somar todas as noites do mês.
		float somaDasDiarias = 0.0F;
		for (float diaria : list) {
			somaDasDiarias += diaria;
		}
		// Executando a conta do total de diarias dividos pelo número de dias do
		// ano, convertendo para duas casas decimais e retornando para a view.
		return df.format((float) somaDasDiarias / 365.0);
	}

	/**
	 * Método que devolve a média de gastos extras mensal.
	 * 
	 * @return A lista de {@code Output} pronta para ser inserida no banco.
	 */
	public static ArrayList<Output> gastosMensal() {
		// Criando e instanciando uma ArryList do tipo Output. Este será nosso retorno.
		ArrayList<Output> list = new ArrayList<Output>();
		// Criando e definindo um array de String que vai armazenar os meses. Depois
		// iremos inserir eles junto com os valores mensais.
		String[] meses = { "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro",
				"Outubro", "Novembro", "Dezembro" };
		// Criando e instanciando o objeto que irá manipular o banco para fazermos as
		// consultas.
		HospedagemDao hDao = HospedagemDao.getInstancia();

		// Iniciando um FOR que irá rodar 12 vezes. A variável {i} é igual ao número de
		// meses.
		for (int i = 1; i <= 12; i++) {
			// Crio um array list do tipo Integer para receber a lista de resultados. Eu
			// chamo o método que retorna a lista passando como parametro a data pré
			// formatada com a variável I no meio da String para indicar qual o mes que eu
			// quero.
			ArrayList<Float> mes = hDao.mediaGastosMes("'2017/" + i + "/01'", "'2017/" + i + "/31'");
			// Declarando a variável {numeroDeDiasNoMes} para guardarmos quantos dias tem o
			// mes. Sabemos que alguns tem 30 outros 31 e Fevereiro de 2017 tem 28, além de
			// que, eles não possuem uma forma de intercalar muito padronizada. Essa
			// variável vai nos ajudar a não reescrever código.
			int numeroDeDiasNoMes = 0;
			// ******* INÍCIO DAS VERIFICAÇÕES *******
			// Verifico se o mês é menor ou igual a 6, ou seja, se ele está entre Janeiro ou
			// Junho.
			if (i <= 6) {
				// Verifico se o mês é IMPAR.
				if (i % 2 != 0) {
					// Se for IMPAR e estiver entre Janeiro e Junho ele tem 31 dias.
					numeroDeDiasNoMes = 31;
					// Se ele não é IMPAR ele só pode ser PAR. Verifico se ele é Fevereiro, ou seja,
					// 2.
				} else if (i == 2) {
					// Se for Fevereiro ele tem 28 dias.
					numeroDeDiasNoMes = 28;
					// Se não for Fevereiro ele só pode ser Abril ou Junho.
				} else {
					// Sendo esses, a variável recebe 30 dias.
					numeroDeDiasNoMes = 30;
				}
				// Caso o mês esteja acima de 6, ou seja, a partir de Julho. Eu verifico...
			} else {
				// Se ele é PAR.
				if (i % 2 == 0) {
					// Se ele for PAR só pode ser IMPAR, ou seja, Agosto, Outubro ou Dezembro.
					numeroDeDiasNoMes = 31;
					// Se ele não for PAR eu verifico se ele é igual a 7, ou seja, se ele é o Julho.
				} else if (i == 7) {
					// Caso afirmativo eu atribuo 31 dias à variável.
					numeroDeDiasNoMes = 31;
					// Se ele não for o Julho e nem é PAR então ele só pode ser Setembro ou
					// Novembro.
				} else {
					// Atribuo 30 dias para a variável.
					numeroDeDiasNoMes = 30;
				}
			}
			// *******FIM DAS VERIFICAÇÕES *******
			// Declaro uma variável para guardar o conteudo da soma.
			// Inicio um FOR para somar todas as noites do mês.
			float somaDasDiarias = 0.0F;
			for (float diaria : mes) {
				somaDasDiarias += diaria;
			}
			// Adiciono a nossa variável {list}, um novo objeto do tipo {Output}, primeiro
			// eu passo o array {meses} na posição de {i} menos 1, ou seja, no mês
			// correspondente ao mês que nós buscamos. O segundo parâmetro que passo é o
			// resultado de uma conta, e é aqui que está a média. Eu divído o total de
			// gastos extras que foram registradas esse mês, pelo número de dias que
			// acabamos de verificar. Eu preciso realizar um casting dos dois números para
			// {float}, caso o contrário o resultado da conta será inteiro. Ainda nesse
			// comando eu formato ele para duas casas decimais.
			list.add(new Output(meses[i - 1], df.format((float) somaDasDiarias / (float) numeroDeDiasNoMes)));
			// Nessa linha é a útima parte da iteração do FOR. Depois de fazer isso ele
			// repetirá essas ações mais 11 vezes até passar por todos os meses.
		}
		// Agora com a {list} completa podemos retorna-la.
		return list;
	}

	/**
	 * Método que devolve a média de gastos extras anual.
	 * 
	 * @return O número médio de gastos extras anual.
	 */
	public static String gastosAnual() {
		// Instanciando o objeto de acesso ao banco.
		HospedagemDao hDao = HospedagemDao.getInstancia();

		// Declarando o array de Float e populando ele com o valor de todos os
		// registros do ano.
		ArrayList<Float> list = hDao.mediaGastosMes("'2017-01-01'", "'2017-12-31'");

		// Declaro uma variável para guardar o conteudo da soma.
		// Inicio um FOR para somar todas as noites do mês.
		float somaDasDiarias = 0.0F;
		for (float diaria : list) {
			somaDasDiarias += diaria;
		}
		// Executando a conta do total de gastos extras dividos pelo número de dias do
		// ano, convertendo para duas casas decimais e retornando para a view.
		return df.format((float) somaDasDiarias / 365.0);
	}
}
