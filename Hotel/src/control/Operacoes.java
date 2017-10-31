package control;

import java.text.DecimalFormat;
import java.util.ArrayList;

import model.Output;
import model.dao.HospedagemDao;

/**
 * Classe que guardarÃ¡ as operaÃ§Ãµes entre o banco e as views.
 * 
 * @author Emmanuel
 *
 */
public class Operacoes {

	// Criando e instanciando um formatador de float para depois ursamos. Ele estÃ¡
	// definido para arredondar atÃ© duas casas decimais.
	private static DecimalFormat df = new DecimalFormat("0.00");

	/**
	 * Esse mÃ©todo calcula as mÃ©dia de reservas de todos os meses.
	 * 
	 * @return A lista de {@code Output} pronta para ser inserida no banco.
	 */
	public static ArrayList<Output> reservaMensal() {

		// Criando e instanciando uma ArryList do tipo Output. Este serÃ¡ nosso retorno.
		ArrayList<Output> list = new ArrayList<Output>();
		// Criando e definindo um array de String que vai armazenar os meses. Depois
		// iremos inserir eles junto com os valores mensais.
		String[] meses = { "Janeiro", "Fevereiro", "MarÃ§o", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro",
				"Outubro", "Novembro", "Dezembro" };
		// Criando e instanciando o objeto que irÃ¡ manipular o banco para fazermos as
		// consultas.
		HospedagemDao hDao = HospedagemDao.getInstancia();

		// Iniciando um FOR que irÃ¡ rodar 12 vezes. A variÃ¡vel {i} Ã© igual ao nÃºmero
		// de
		// meses.
		for (int i = 1; i <= 12; i++) {
			// Crio um array list do tipo Object para receber a lista de resultados. Eu
			// chamo o mÃ©todo que retorna a lista passando como parametro a data prÃ©
			// formatada com a variÃ¡vel I no meio da String para indicar qual o mes que eu
			// quero.
			ArrayList<Object> mes = hDao.mediaReservasMes("'2017/" + i + "/01'", "'2017/" + i + "/31'");
			// Declarando a variÃ¡vel {numeroDeDiasNoMes} para guardarmos quantos dias tem o
			// mes. Sabemos que alguns tem 30 outros 31 e Fevereiro de 2017 tem 28, alÃ©m de
			// que, eles nÃ£o possuem uma forma de intercalar muito padronizada. Essa
			// variÃ¡vel vai nos ajudar a nÃ£o reescrever cÃ³digo.
			int numeroDeDiasNoMes = 0;
			// ******* INÃ�CIO DAS VERIFICAÃ‡Ã•ES *******
			// Verifico se o mÃªs Ã© menor ou igual a 6, ou seja, se ele estÃ¡ entre Janeiro
			// ou
			// Junho.
			if (i <= 6) {
				// Verifico se o mÃªs Ã© IMPAR.
				if (i % 2 != 0) {
					// Se for IMPAR e estiver entre Janeiro e Junho ele tem 31 dias.
					numeroDeDiasNoMes = 31;
					// Se ele nÃ£o Ã© IMPAR ele sÃ³ pode ser PAR. Verifico se ele Ã© Fevereiro, ou
					// seja,
					// 2.
				} else if (i == 2) {
					// Se for Fevereiro ele tem 28 dias.
					numeroDeDiasNoMes = 28;
					// Se nÃ£o for Fevereiro ele sÃ³ pode ser Abril ou Junho.
				} else {
					// Sendo esses, a variÃ¡vel recebe 30 dias.
					numeroDeDiasNoMes = 30;
				}
				// Caso o mÃªs esteja acima de 6, ou seja, a partir de Julho. Eu verifico...
			} else {
				// Se ele Ã© PAR.
				if (i % 2 == 0) {
					// Se ele for PAR sÃ³ pode ser IMPAR, ou seja, Agosto, Outubro ou Dezembro.
					numeroDeDiasNoMes = 31;
					// Se ele nÃ£o for PAR eu verifico se ele Ã© igual a 7, ou seja, se ele Ã© o
					// Julho.
				} else if (i == 7) {
					// Caso afirmativo eu atribuo 31 dias Ã  variÃ¡vel.
					numeroDeDiasNoMes = 31;
					// Se ele nÃ£o for o Julho e nem Ã© PAR entÃ£o ele sÃ³ pode ser Setembro ou
					// Novembro.
				} else {
					// Atribuo 30 dias para a variÃ¡vel.
					numeroDeDiasNoMes = 30;
				}
			}
			// *******FIM DAS VERIFICAÃ‡Ã•ES *******
			// Adiciono a nossa variÃ¡vel {list}, um novo objeto do tipo {Output}, primeiro
			// eu passo o array {meses} na posiÃ§Ã£o de {i} menos 1, ou seja, no mÃªs
			// correspondente ao mÃªs que nÃ³s buscamos. O segundo parÃ¢metro que passo Ã© o
			// resultado de uma conta, e Ã© aqui que estÃ¡ a mÃ©dia. Eu divÃ­do quantos
			// elementos temos no {ArrayList} {mes}, atravÃ©s do mÃ©todo {size()} que Ã© do
			// prÃ³prio {ArrayList}, pelo nÃºmero de dias que acabamos de verificar. Eu
			// preciso realizar um casting dos dois nÃºmeros para {float}, caso o contrÃ¡rio
			// o
			// resultado da conta serÃ¡ inteiro. Ainda nesse comando eu formato ele para
			// duas
			// casas decimais.
			list.add(new Output(meses[i - 1], df.format((float) mes.size() / (float) numeroDeDiasNoMes)));
			// Nessa linha Ã© a Ãºtima parte da iteraÃ§Ã£o do FOR. Depois de fazer isso ele
			// repetirÃ¡ essas aÃ§Ãµes mais 11 vezes atÃ© passar por todos os meses.
		}
		// \Agora com a {list} completa podemos retorna-la.
		return list;
	}

	/**
	 * MÃ©todo que devolve o nÃºmero de reservas anual.
	 * 
	 * @return O nÃºmero mÃ©dio de reservas anual.
	 */
	public static String reservaAnual() {

		// Instanciando o objeto de acesso ao banco.
		HospedagemDao hDao = HospedagemDao.getInstancia();

		// Declarando o array de Object e populando ele com o valor de todos os
		// registros do ano.
		ArrayList<Object> list = hDao.mediaReservasMes("'2017-01-01'", "'2017-12-31'");

		// Executando e a conta do total de registros dividos pelo nÃºmero de dias do
		// ano, convertendo para duas casas decimais e retornando para a view.
		return df.format((float) list.size() / 365.0);
	}

	/**
	 * MÃ©todo que devolve a mÃ©dia de noites mensal.
	 * 
	 * @return A lista de {@code Output} pronta para ser inserida no banco.
	 */
	public static ArrayList<Output> noitesMensal() {
		// Criando e instanciando uma ArryList do tipo Output. Este serÃ¡ nosso retorno.
		ArrayList<Output> list = new ArrayList<Output>();
		// Criando e definindo um array de String que vai armazenar os meses. Depois
		// iremos inserir eles junto com os valores mensais.
		String[] meses = { "Janeiro", "Fevereiro", "MarÃ§o", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro",
				"Outubro", "Novembro", "Dezembro" };
		// Criando e instanciando o objeto que irÃ¡ manipular o banco para fazermos as
		// consultas.
		HospedagemDao hDao = HospedagemDao.getInstancia();

		// Iniciando um FOR que irÃ¡ rodar 12 vezes. A variÃ¡vel {i} Ã© igual ao nÃºmero
		// de
		// meses.
		for (int i = 1; i <= 12; i++) {
			// Crio um array list do tipo Integer para receber a lista de resultados. Eu
			// chamo o mÃ©todo que retorna a lista passando como parametro a data prÃ©
			// formatada com a variÃ¡vel I no meio da String para indicar qual o mes que eu
			// quero.
			ArrayList<Integer> mes = hDao.mediaNoitesMes("'2017/" + i + "/01'", "'2017/" + i + "/31'");
			// Declarando a variÃ¡vel {numeroDeDiasNoMes} para guardarmos quantos dias tem o
			// mes. Sabemos que alguns tem 30 outros 31 e Fevereiro de 2017 tem 28, alÃ©m de
			// que, eles nÃ£o possuem uma forma de intercalar muito padronizada. Essa
			// variÃ¡vel vai nos ajudar a nÃ£o reescrever cÃ³digo.
			int numeroDeDiasNoMes = 0;
			// ******* INÃ�CIO DAS VERIFICAÃ‡Ã•ES *******
			// Verifico se o mÃªs Ã© menor ou igual a 6, ou seja, se ele estÃ¡ entre Janeiro
			// ou
			// Junho.
			if (i <= 6) {
				// Verifico se o mÃªs Ã© IMPAR.
				if (i % 2 != 0) {
					// Se for IMPAR e estiver entre Janeiro e Junho ele tem 31 dias.
					numeroDeDiasNoMes = 31;
					// Se ele nÃ£o Ã© IMPAR ele sÃ³ pode ser PAR. Verifico se ele Ã© Fevereiro, ou
					// seja,
					// 2.
				} else if (i == 2) {
					// Se for Fevereiro ele tem 28 dias.
					numeroDeDiasNoMes = 28;
					// Se nÃ£o for Fevereiro ele sÃ³ pode ser Abril ou Junho.
				} else {
					// Sendo esses, a variÃ¡vel recebe 30 dias.
					numeroDeDiasNoMes = 30;
				}
				// Caso o mÃªs esteja acima de 6, ou seja, a partir de Julho. Eu verifico...
			} else {
				// Se ele Ã© PAR.
				if (i % 2 == 0) {
					// Se ele for PAR sÃ³ pode ser IMPAR, ou seja, Agosto, Outubro ou Dezembro.
					numeroDeDiasNoMes = 31;
					// Se ele nÃ£o for PAR eu verifico se ele Ã© igual a 7, ou seja, se ele Ã© o
					// Julho.
				} else if (i == 7) {
					// Caso afirmativo eu atribuo 31 dias Ã  variÃ¡vel.
					numeroDeDiasNoMes = 31;
					// Se ele nÃ£o for o Julho e nem Ã© PAR entÃ£o ele sÃ³ pode ser Setembro ou
					// Novembro.
				} else {
					// Atribuo 30 dias para a variÃ¡vel.
					numeroDeDiasNoMes = 30;
				}
			}
			// *******FIM DAS VERIFICAÃ‡Ã•ES *******
			// Declaro uma variÃ¡vel para guardar o conteudo da soma.
			// Inicio um FOR para somar todas as noites do mÃªs.
			int somaDasNoites = 0;
			for (int noites : mes) {
				somaDasNoites += noites;
			}
			// Adiciono a nossa variÃ¡vel {list}, um novo objeto do tipo {Output}, primeiro
			// eu passo o array {meses} na posiÃ§Ã£o de {i} menos 1, ou seja, no mÃªs
			// correspondente ao mÃªs que nÃ³s buscamos. O segundo parÃ¢metro que passo Ã© o
			// resultado de uma conta, e Ã© aqui que estÃ¡ a mÃ©dia. Eu divÃ­do o nÃºmero de
			// noites que foram reservados nesse mÃªs, pelo nÃºmero de dias que acabamos de
			// verificar. Eu preciso realizar um casting dos dois nÃºmeros para {float},
			// caso
			// o contrÃ¡rio o resultado da conta serÃ¡ inteiro. Ainda nesse comando eu
			// formato
			// ele para duas casas decimais.
			list.add(new Output(meses[i - 1], df.format((float) somaDasNoites / (float) numeroDeDiasNoMes)));
			// Nessa linha Ã© a Ãºtima parte da iteraÃ§Ã£o do FOR. Depois de fazer isso ele
			// repetirÃ¡ essas aÃ§Ãµes mais 11 vezes atÃ© passar por todos os meses.
		}
		// Agora com a {list} completa podemos retorna-la.
		return list;
	}

	/**
	 * MÃ©todo que devolve a mÃ©dia de noites anual.
	 * 
	 * @return O nÃºmero mÃ©dio de noites anual.
	 */
	public static String noitesAnual() {
		// Instanciando o objeto de acesso ao banco.
		HospedagemDao hDao = HospedagemDao.getInstancia();

		// Declarando o array de Integer e populando ele com o valor de todos os
		// registros do ano.
		ArrayList<Integer> list = hDao.mediaNoitesMes("'2017-01-01'", "'2017-12-31'");

		// Declaro uma variÃ¡vel para guardar o conteudo da soma.
		// Inicio um FOR para somar todas as noites do mÃªs.
		int somaDasNoites = 0;
		for (int noites : list) {
			somaDasNoites += noites;
		}
		// Executando a conta do total de noites dividos pelo nÃºmero de dias do
		// ano, convertendo para duas casas decimais e retornando para a view.
		return df.format((float) somaDasNoites / 365.0);
	}

	/**
	 * MÃ©todo que devolve a mÃ©dia de diaria mensal.
	 * 
	 * @return A lista de {@code Output} pronta para ser inserida no banco.
	 */
	public static ArrayList<Output> diariaMensal() {
		// Criando e instanciando uma ArryList do tipo Output. Este serÃ¡ nosso retorno.
		ArrayList<Output> list = new ArrayList<Output>();
		// Criando e definindo um array de String que vai armazenar os meses. Depois
		// iremos inserir eles junto com os valores mensais.
		String[] meses = { "Janeiro", "Fevereiro", "MarÃ§o", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro",
				"Outubro", "Novembro", "Dezembro" };
		// Criando e instanciando o objeto que irÃ¡ manipular o banco para fazermos as
		// consultas.
		HospedagemDao hDao = HospedagemDao.getInstancia();

		// Iniciando um FOR que irÃ¡ rodar 12 vezes. A variÃ¡vel {i} Ã© igual ao nÃºmero
		// de
		// meses.
		for (int i = 1; i <= 12; i++) {
			// Crio um array list do tipo Integer para receber a lista de resultados. Eu
			// chamo o mÃ©todo que retorna a lista passando como parametro a data prÃ©
			// formatada com a variÃ¡vel I no meio da String para indicar qual o mes que eu
			// quero.
			ArrayList<Float> mes = hDao.mediaDiariaMes("'2017/" + i + "/01'", "'2017/" + i + "/31'");
			// Declarando a variÃ¡vel {numeroDeDiasNoMes} para guardarmos quantos dias tem o
			// mes. Sabemos que alguns tem 30 outros 31 e Fevereiro de 2017 tem 28, alÃ©m de
			// que, eles nÃ£o possuem uma forma de intercalar muito padronizada. Essa
			// variÃ¡vel vai nos ajudar a nÃ£o reescrever cÃ³digo.
			int numeroDeDiasNoMes = 0;
			// ******* INÃ�CIO DAS VERIFICAÃ‡Ã•ES *******
			// Verifico se o mÃªs Ã© menor ou igual a 6, ou seja, se ele estÃ¡ entre Janeiro
			// ou
			// Junho.
			if (i <= 6) {
				// Verifico se o mÃªs Ã© IMPAR.
				if (i % 2 != 0) {
					// Se for IMPAR e estiver entre Janeiro e Junho ele tem 31 dias.
					numeroDeDiasNoMes = 31;
					// Se ele nÃ£o Ã© IMPAR ele sÃ³ pode ser PAR. Verifico se ele Ã© Fevereiro, ou
					// seja,
					// 2.
				} else if (i == 2) {
					// Se for Fevereiro ele tem 28 dias.
					numeroDeDiasNoMes = 28;
					// Se nÃ£o for Fevereiro ele sÃ³ pode ser Abril ou Junho.
				} else {
					// Sendo esses, a variÃ¡vel recebe 30 dias.
					numeroDeDiasNoMes = 30;
				}
				// Caso o mÃªs esteja acima de 6, ou seja, a partir de Julho. Eu verifico...
			} else {
				// Se ele Ã© PAR.
				if (i % 2 == 0) {
					// Se ele for PAR sÃ³ pode ser IMPAR, ou seja, Agosto, Outubro ou Dezembro.
					numeroDeDiasNoMes = 31;
					// Se ele nÃ£o for PAR eu verifico se ele Ã© igual a 7, ou seja, se ele Ã© o
					// Julho.
				} else if (i == 7) {
					// Caso afirmativo eu atribuo 31 dias Ã  variÃ¡vel.
					numeroDeDiasNoMes = 31;
					// Se ele nÃ£o for o Julho e nem Ã© PAR entÃ£o ele sÃ³ pode ser Setembro ou
					// Novembro.
				} else {
					// Atribuo 30 dias para a variÃ¡vel.
					numeroDeDiasNoMes = 30;
				}
			}
			// *******FIM DAS VERIFICAÃ‡Ã•ES *******
			// Declaro uma variÃ¡vel para guardar o conteudo da soma.
			// Inicio um FOR para somar todas as noites do mÃªs.
			float somaDasDiarias = 0.0F;
			for (float diaria : mes) {
				somaDasDiarias += diaria;
			}
			// Adiciono a nossa variÃ¡vel {list}, um novo objeto do tipo {Output}, primeiro
			// eu passo o array {meses} na posiÃ§Ã£o de {i} menos 1, ou seja, no mÃªs
			// correspondente ao mÃªs que nÃ³s buscamos. O segundo parÃ¢metro que passo Ã© o
			// resultado de uma conta, e Ã© aqui que estÃ¡ a mÃ©dia. Eu divÃ­do o total de
			// diarias que foram registradas esse mÃªs, pelo nÃºmero de dias que acabamos de
			// verificar. Eu preciso realizar um casting dos dois nÃºmeros para {float},
			// caso
			// o contrÃ¡rio o resultado da conta serÃ¡ inteiro. Ainda nesse comando eu
			// formato
			// ele para duas casas decimais.
			list.add(new Output(meses[i - 1], df.format((float) somaDasDiarias / (float) numeroDeDiasNoMes)));
			// Nessa linha Ã© a Ãºtima parte da iteraÃ§Ã£o do FOR. Depois de fazer isso ele
			// repetirÃ¡ essas aÃ§Ãµes mais 11 vezes atÃ© passar por todos os meses.
		}
		// Agora com a {list} completa podemos retorna-la.
		return list;
	}

	/**
	 * MÃ©todo que devolve a mÃ©dia de diÃ¡rias anual.
	 * 
	 * @return O nÃºmero mÃ©dio de diÃ¡rias anual.
	 */
	public static String diariaAnual() {
		// Instanciando o objeto de acesso ao banco.
		HospedagemDao hDao = HospedagemDao.getInstancia();

		// Declarando o array de Float e populando ele com o valor de todos os
		// registros do ano.
		ArrayList<Float> list = hDao.mediaDiariaMes("'2017-01-01'", "'2017-12-31'");

		// Declaro uma variÃ¡vel para guardar o conteudo da soma.
		// Inicio um FOR para somar todas as noites do mÃªs.
		float somaDasDiarias = 0.0F;
		for (float diaria : list) {
			somaDasDiarias += diaria;
		}
		// Executando a conta do total de diarias dividos pelo nÃºmero de dias do
		// ano, convertendo para duas casas decimais e retornando para a view.
		return df.format((float) somaDasDiarias / 365.0);
	}

	/**
	 * MÃ©todo que devolve a mÃ©dia de gastos extras mensal.
	 * 
	 * @return A lista de {@code Output} pronta para ser inserida no banco.
	 */
	public static ArrayList<Output> gastosMensal() {
		// Criando e instanciando uma ArryList do tipo Output. Este serÃ¡ nosso retorno.
		ArrayList<Output> list = new ArrayList<Output>();
		// Criando e definindo um array de String que vai armazenar os meses. Depois
		// iremos inserir eles junto com os valores mensais.
		String[] meses = { "Janeiro", "Fevereiro", "MarÃ§o", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro",
				"Outubro", "Novembro", "Dezembro" };
		// Criando e instanciando o objeto que irÃ¡ manipular o banco para fazermos as
		// consultas.
		HospedagemDao hDao = HospedagemDao.getInstancia();

		// Iniciando um FOR que irÃ¡ rodar 12 vezes. A variÃ¡vel {i} Ã© igual ao nÃºmero
		// de
		// meses.
		for (int i = 1; i <= 12; i++) {
			// Crio um array list do tipo Integer para receber a lista de resultados. Eu
			// chamo o mÃ©todo que retorna a lista passando como parametro a data prÃ©
			// formatada com a variÃ¡vel I no meio da String para indicar qual o mes que eu
			// quero.
			ArrayList<Float> mes = hDao.mediaGastosMes("'2017/" + i + "/01'", "'2017/" + i + "/31'");
			// Declarando a variÃ¡vel {numeroDeDiasNoMes} para guardarmos quantos dias tem o
			// mes. Sabemos que alguns tem 30 outros 31 e Fevereiro de 2017 tem 28, alÃ©m de
			// que, eles nÃ£o possuem uma forma de intercalar muito padronizada. Essa
			// variÃ¡vel vai nos ajudar a nÃ£o reescrever cÃ³digo.
			int numeroDeDiasNoMes = 0;
			// ******* INÃ�CIO DAS VERIFICAÃ‡Ã•ES *******
			// Verifico se o mÃªs Ã© menor ou igual a 6, ou seja, se ele estÃ¡ entre Janeiro
			// ou
			// Junho.
			if (i <= 6) {
				// Verifico se o mÃªs Ã© IMPAR.
				if (i % 2 != 0) {
					// Se for IMPAR e estiver entre Janeiro e Junho ele tem 31 dias.
					numeroDeDiasNoMes = 31;
					// Se ele nÃ£o Ã© IMPAR ele sÃ³ pode ser PAR. Verifico se ele Ã© Fevereiro, ou
					// seja,
					// 2.
				} else if (i == 2) {
					// Se for Fevereiro ele tem 28 dias.
					numeroDeDiasNoMes = 28;
					// Se nÃ£o for Fevereiro ele sÃ³ pode ser Abril ou Junho.
				} else {
					// Sendo esses, a variÃ¡vel recebe 30 dias.
					numeroDeDiasNoMes = 30;
				}
				// Caso o mÃªs esteja acima de 6, ou seja, a partir de Julho. Eu verifico...
			} else {
				// Se ele Ã© PAR.
				if (i % 2 == 0) {
					// Se ele for PAR sÃ³ pode ser IMPAR, ou seja, Agosto, Outubro ou Dezembro.
					numeroDeDiasNoMes = 31;
					// Se ele nÃ£o for PAR eu verifico se ele Ã© igual a 7, ou seja, se ele Ã© o
					// Julho.
				} else if (i == 7) {
					// Caso afirmativo eu atribuo 31 dias Ã  variÃ¡vel.
					numeroDeDiasNoMes = 31;
					// Se ele nÃ£o for o Julho e nem Ã© PAR entÃ£o ele sÃ³ pode ser Setembro ou
					// Novembro.
				} else {
					// Atribuo 30 dias para a variÃ¡vel.
					numeroDeDiasNoMes = 30;
				}
			}
			// *******FIM DAS VERIFICAÃ‡Ã•ES *******
			// Declaro uma variÃ¡vel para guardar o conteudo da soma.
			// Inicio um FOR para somar todas as noites do mÃªs.
			float somaDasDiarias = 0.0F;
			for (float diaria : mes) {
				somaDasDiarias += diaria;
			}
			// Adiciono a nossa variÃ¡vel {list}, um novo objeto do tipo {Output}, primeiro
			// eu passo o array {meses} na posiÃ§Ã£o de {i} menos 1, ou seja, no mÃªs
			// correspondente ao mÃªs que nÃ³s buscamos. O segundo parÃ¢metro que passo Ã© o
			// resultado de uma conta, e Ã© aqui que estÃ¡ a mÃ©dia. Eu divÃ­do o total de
			// gastos extras que foram registradas esse mÃªs, pelo nÃºmero de dias que
			// acabamos de verificar. Eu preciso realizar um casting dos dois nÃºmeros para
			// {float}, caso o contrÃ¡rio o resultado da conta serÃ¡ inteiro. Ainda nesse
			// comando eu formato ele para duas casas decimais.
			list.add(new Output(meses[i - 1], df.format((float) somaDasDiarias / (float) numeroDeDiasNoMes)));
			// Nessa linha Ã© a Ãºtima parte da iteraÃ§Ã£o do FOR. Depois de fazer isso ele
			// repetirÃ¡ essas aÃ§Ãµes mais 11 vezes atÃ© passar por todos os meses.
		}
		// Agora com a {list} completa podemos retorna-la.
		return list;
	}

	/**
	 * MÃ©todo que devolve a mÃ©dia de gastos extras anual.
	 * 
	 * @return O nÃºmero mÃ©dio de gastos extras anual.
	 */
	public static String gastosAnual() {
		// Instanciando o objeto de acesso ao banco.
		HospedagemDao hDao = HospedagemDao.getInstancia();

		// Declarando o array de Float e populando ele com o valor de todos os
		// registros do ano.
		ArrayList<Float> list = hDao.mediaGastosMes("'2017-01-01'", "'2017-12-31'");

		// Declaro uma variÃ¡vel para guardar o conteudo da soma.
		// Inicio um FOR para somar todas as noites do mÃªs.
		float somaDasDiarias = 0.0F;
		for (float diaria : list) {
			somaDasDiarias += diaria;
		}
		// Executando a conta do total de gastos extras dividos pelo nÃºmero de dias do
		// ano, convertendo para duas casas decimais e retornando para a view.
		return df.format((float) somaDasDiarias / 365.0);
	}

	public static String gastosTotalAnual() {
		// Instanciando o objeto de acesso ao banco.
		HospedagemDao hDao = HospedagemDao.getInstancia();

		// Declarando o array de Float e populando ele com o valor de todos os
		// registros do ano.
		ArrayList<Float> list = hDao.mediaDiariaMes("'2017-01-01'", "'2017-12-31'");

		// Declaro uma variÃ¡vel para guardar o conteudo da soma.
		// Inicio um FOR para somar todas as noites do mÃªs.
		float somaDasDiarias = 0.0F;
		for (float diaria : list) {
			somaDasDiarias += diaria;
		}
		// Executando a conta do total de gastos extras dividos pelo nÃºmero de dias do
		// ano, convertendo para duas casas decimais e retornando para a view.
		return df.format((float) somaDasDiarias / 365.0);
	}

	public static ArrayList<Output> gastosTotalMes() {
		// Criando e instanciando uma ArryList do tipo Output. Este serÃ¡ nosso retorno.
		ArrayList<Output> list = new ArrayList<Output>();
		// Criando e definindo um array de String que vai armazenar os meses. Depois
		// iremos inserir eles junto com os valores mensais.
		String[] meses = { "Janeiro", "Fevereiro", "MarÃ§o", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro",
				"Outubro", "Novembro", "Dezembro" };
		// Criando e instanciando o objeto que irÃ¡ manipular o banco para fazermos as
		// consultas.
		HospedagemDao hDao = HospedagemDao.getInstancia();

		// Iniciando um FOR que irÃ¡ rodar 12 vezes. A variÃ¡vel {i} Ã© igual ao nÃºmero
		// de
		// meses.
		for (int i = 1; i <= 12; i++) {
			// Crio um array list do tipo Integer para receber a lista de resultados. Eu
			// chamo o mÃ©todo que retorna a lista passando como parametro a data prÃ©
			// formatada com a variÃ¡vel I no meio da String para indicar qual o mes que eu
			// quero.
			ArrayList<Float> mes = hDao.mediaDiariaMes("'2017/" + i + "/01'", "'2017/" + i + "/31'");
			// Declarando a variÃ¡vel {numeroDeDiasNoMes} para guardarmos quantos dias tem o
			// mes. Sabemos que alguns tem 30 outros 31 e Fevereiro de 2017 tem 28, alÃ©m de
			// que, eles nÃ£o possuem uma forma de intercalar muito padronizada. Essa
			// variÃ¡vel vai nos ajudar a nÃ£o reescrever cÃ³digo.
			int numeroDeDiasNoMes = 0;
			// ******* INÃ�CIO DAS VERIFICAÃ‡Ã•ES *******
			// Verifico se o mÃªs Ã© menor ou igual a 6, ou seja, se ele estÃ¡ entre Janeiro
			// ou
			// Junho.
			if (i <= 6) {
				// Verifico se o mÃªs Ã© IMPAR.
				if (i % 2 != 0) {
					// Se for IMPAR e estiver entre Janeiro e Junho ele tem 31 dias.
					numeroDeDiasNoMes = 31;
					// Se ele nÃ£o Ã© IMPAR ele sÃ³ pode ser PAR. Verifico se ele Ã© Fevereiro, ou
					// seja,
					// 2.
				} else if (i == 2) {
					// Se for Fevereiro ele tem 28 dias.
					numeroDeDiasNoMes = 28;
					// Se nÃ£o for Fevereiro ele sÃ³ pode ser Abril ou Junho.
				} else {
					// Sendo esses, a variÃ¡vel recebe 30 dias.
					numeroDeDiasNoMes = 30;
				}
				// Caso o mÃªs esteja acima de 6, ou seja, a partir de Julho. Eu verifico...
			} else {
				// Se ele Ã© PAR.
				if (i % 2 == 0) {
					// Se ele for PAR sÃ³ pode ser IMPAR, ou seja, Agosto, Outubro ou Dezembro.
					numeroDeDiasNoMes = 31;
					// Se ele nÃ£o for PAR eu verifico se ele Ã© igual a 7, ou seja, se ele Ã© o
					// Julho.
				} else if (i == 7) {
					// Caso afirmativo eu atribuo 31 dias Ã  variÃ¡vel.
					numeroDeDiasNoMes = 31;
					// Se ele nÃ£o for o Julho e nem Ã© PAR entÃ£o ele sÃ³ pode ser Setembro ou
					// Novembro.
				} else {
					// Atribuo 30 dias para a variÃ¡vel.
					numeroDeDiasNoMes = 30;
				}
			}
			// *******FIM DAS VERIFICAÃ‡Ã•ES *******
			// Declaro uma variÃ¡vel para guardar o conteudo da soma.
			// Inicio um FOR para somar todas as noites do mÃªs.
			float somaDasDiarias = 0.0F;
			for (float diaria : mes) {
				somaDasDiarias += diaria;
			}
			// Adiciono a nossa variÃ¡vel {list}, um novo objeto do tipo {Output}, primeiro
			// eu passo o array {meses} na posiÃ§Ã£o de {i} menos 1, ou seja, no mÃªs
			// correspondente ao mÃªs que nÃ³s buscamos. O segundo parÃ¢metro que passo Ã© o
			// resultado de uma conta, e Ã© aqui que estÃ¡ a mÃ©dia. Eu divÃ­do o total de
			// gastos extras que foram registradas esse mÃªs, pelo nÃºmero de dias que
			// acabamos de verificar. Eu preciso realizar um casting dos dois nÃºmeros para
			// {float}, caso o contrÃ¡rio o resultado da conta serÃ¡ inteiro. Ainda nesse
			// comando eu formato ele para duas casas decimais.
			list.add(new Output(meses[i - 1], df.format((float) somaDasDiarias / (float) numeroDeDiasNoMes)));
			// Nessa linha Ã© a Ãºtima parte da iteraÃ§Ã£o do FOR. Depois de fazer isso ele
			// repetirÃ¡ essas aÃ§Ãµes mais 11 vezes atÃ© passar por todos os meses.
		}
		// Agora com a {list} completa podemos retorna-la.
		return list;

	}

	public static String mediaPessoasAnual() {
		// Instanciando o objeto de acesso ao banco.
		HospedagemDao hDao = HospedagemDao.getInstancia();

		// Declarando o array de Float e populando ele com o valor de todos os
		// registros do ano.
		ArrayList<Float> list = hDao.mediaQtdHospedes("'2017-01-01'", "'2017-12-31'");

		// Declaro uma variÃ¡vel para guardar o conteudo da soma.
		// Inicio um FOR para somar todas as noites do mÃªs.
		float somaDasDiarias = 0.0F;
		for (float diaria : list) {
			somaDasDiarias += diaria;
		}
		// Executando a conta do total de gastos extras dividos pelo nÃºmero de dias do
		// ano, convertendo para duas casas decimais e retornando para a view.
		return df.format((float) somaDasDiarias / 365.0);

	}

	public static ArrayList<Output> mediaPessoaMes() {
		// Criando e instanciando uma ArryList do tipo Output. Este serÃ¡ nosso retorno.
		ArrayList<Output> list = new ArrayList<Output>();
		// Criando e definindo um array de String que vai armazenar os meses. Depois
		// iremos inserir eles junto com os valores mensais.
		String[] meses = { "Janeiro", "Fevereiro", "MarÃ§o", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro",
				"Outubro", "Novembro", "Dezembro" };
		// Criando e instanciando o objeto que irÃ¡ manipular o banco para fazermos as
		// consultas.
		HospedagemDao hDao = HospedagemDao.getInstancia();

		// Iniciando um FOR que irÃ¡ rodar 12 vezes. A variÃ¡vel {i} Ã© igual ao nÃºmero
		// de
		// meses.
		for (int i = 1; i <= 12; i++) {
			// Crio um array list do tipo Integer para receber a lista de resultados. Eu
			// chamo o mÃ©todo que retorna a lista passando como parametro a data prÃ©
			// formatada com a variÃ¡vel I no meio da String para indicar qual o mes que eu
			// quero.
			ArrayList<Float> mes = hDao.mediaQtdHospedes("'2017/" + i + "/01'", "'2017/" + i + "/31'");
			// Declarando a variÃ¡vel {numeroDeDiasNoMes} para guardarmos quantos dias tem o
			// mes. Sabemos que alguns tem 30 outros 31 e Fevereiro de 2017 tem 28, alÃ©m de
			// que, eles nÃ£o possuem uma forma de intercalar muito padronizada. Essa
			// variÃ¡vel vai nos ajudar a nÃ£o reescrever cÃ³digo.
			int numeroDeDiasNoMes = 0;
			// ******* INÃ�CIO DAS VERIFICAÃ‡Ã•ES *******
			// Verifico se o mÃªs Ã© menor ou igual a 6, ou seja, se ele estÃ¡ entre Janeiro
			// ou
			// Junho.
			if (i <= 6) {
				// Verifico se o mÃªs Ã© IMPAR.
				if (i % 2 != 0) {
					// Se for IMPAR e estiver entre Janeiro e Junho ele tem 31 dias.
					numeroDeDiasNoMes = 31;
					// Se ele nÃ£o Ã© IMPAR ele sÃ³ pode ser PAR. Verifico se ele Ã© Fevereiro, ou
					// seja,
					// 2.
				} else if (i == 2) {
					// Se for Fevereiro ele tem 28 dias.
					numeroDeDiasNoMes = 28;
					// Se nÃ£o for Fevereiro ele sÃ³ pode ser Abril ou Junho.
				} else {
					// Sendo esses, a variÃ¡vel recebe 30 dias.
					numeroDeDiasNoMes = 30;
				}
				// Caso o mÃªs esteja acima de 6, ou seja, a partir de Julho. Eu verifico...
			} else {
				// Se ele Ã© PAR.
				if (i % 2 == 0) {
					// Se ele for PAR sÃ³ pode ser IMPAR, ou seja, Agosto, Outubro ou Dezembro.
					numeroDeDiasNoMes = 31;
					// Se ele nÃ£o for PAR eu verifico se ele Ã© igual a 7, ou seja, se ele Ã© o
					// Julho.
				} else if (i == 7) {
					// Caso afirmativo eu atribuo 31 dias Ã  variÃ¡vel.
					numeroDeDiasNoMes = 31;
					// Se ele nÃ£o for o Julho e nem Ã© PAR entÃ£o ele sÃ³ pode ser Setembro ou
					// Novembro.
				} else {
					// Atribuo 30 dias para a variÃ¡vel.
					numeroDeDiasNoMes = 30;
				}
			}
			// *******FIM DAS VERIFICAÃ‡Ã•ES *******
			// Declaro uma variÃ¡vel para guardar o conteudo da soma.
			// Inicio um FOR para somar todas as noites do mÃªs.
			float somaDasDiarias = 0.0F;
			for (float diaria : mes) {
				somaDasDiarias += diaria;
			}
			// Adiciono a nossa variÃ¡vel {list}, um novo objeto do tipo {Output}, primeiro
			// eu passo o array {meses} na posiÃ§Ã£o de {i} menos 1, ou seja, no mÃªs
			// correspondente ao mÃªs que nÃ³s buscamos. O segundo parÃ¢metro que passo Ã© o
			// resultado de uma conta, e Ã© aqui que estÃ¡ a mÃ©dia. Eu divÃ­do o total de
			// gastos extras que foram registradas esse mÃªs, pelo nÃºmero de dias que
			// acabamos de verificar. Eu preciso realizar um casting dos dois nÃºmeros para
			// {float}, caso o contrÃ¡rio o resultado da conta serÃ¡ inteiro. Ainda nesse
			// comando eu formato ele para duas casas decimais.
			list.add(new Output(meses[i - 1], df.format((float) somaDasDiarias / (float) numeroDeDiasNoMes)));
			// Nessa linha Ã© a Ãºtima parte da iteraÃ§Ã£o do FOR. Depois de fazer isso ele
			// repetirÃ¡ essas aÃ§Ãµes mais 11 vezes atÃ© passar por todos os meses.
		}
		// Agora com a {list} completa podemos retorna-la.
		return list;

	}

	public static ArrayList<Output> mediaOcupacaoMes() {
		// Criando e instanciando uma ArryList do tipo Output. Este serÃ¡ nosso retorno.
		ArrayList<Output> list = new ArrayList<Output>();
		// Criando e definindo um array de String que vai armazenar os meses. Depois
		// iremos inserir eles junto com os valores mensais.
		String[] meses = { "Janeiro", "Fevereiro", "MarÃ§o", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro",
				"Outubro", "Novembro", "Dezembro" };
		// Criando e instanciando o objeto que irÃ¡ manipular o banco para fazermos as
		// consultas.
		HospedagemDao hDao = HospedagemDao.getInstancia();

		// Iniciando um FOR que irÃ¡ rodar 12 vezes. A variÃ¡vel {i} Ã© igual ao nÃºmero
		// de
		// meses.
		for (int i = 1; i <= 12; i++) {
			// Crio um array list do tipo Integer para receber a lista de resultados. Eu
			// chamo o mÃ©todo que retorna a lista passando como parametro a data prÃ©
			// formatada com a variÃ¡vel I no meio da String para indicar qual o mes que eu
			// quero.
			ArrayList<Float> mes = hDao.mediaQtdHospedes("'2017/" + i + "/01'", "'2017/" + i + "/31'");
			// Declarando a variÃ¡vel {numeroDeDiasNoMes} para guardarmos quantos dias tem o
			// mes. Sabemos que alguns tem 30 outros 31 e Fevereiro de 2017 tem 28, alÃ©m de
			// que, eles nÃ£o possuem uma forma de intercalar muito padronizada. Essa
			// variÃ¡vel vai nos ajudar a nÃ£o reescrever cÃ³digo.
			int numeroDeDiasNoMes = 0;
			// ******* INÃ�CIO DAS VERIFICAÃ‡Ã•ES *******
			// Verifico se o mÃªs Ã© menor ou igual a 6, ou seja, se ele estÃ¡ entre Janeiro
			// ou
			// Junho.
			if (i <= 6) {
				// Verifico se o mÃªs Ã© IMPAR.
				if (i % 2 != 0) {
					// Se for IMPAR e estiver entre Janeiro e Junho ele tem 31 dias.
					numeroDeDiasNoMes = 31;
					// Se ele nÃ£o Ã© IMPAR ele sÃ³ pode ser PAR. Verifico se ele Ã© Fevereiro, ou
					// seja,
					// 2.
				} else if (i == 2) {
					// Se for Fevereiro ele tem 28 dias.
					numeroDeDiasNoMes = 28;
					// Se nÃ£o for Fevereiro ele sÃ³ pode ser Abril ou Junho.
				} else {
					// Sendo esses, a variÃ¡vel recebe 30 dias.
					numeroDeDiasNoMes = 30;
				}
				// Caso o mÃªs esteja acima de 6, ou seja, a partir de Julho. Eu verifico...
			} else {
				// Se ele Ã© PAR.
				if (i % 2 == 0) {
					// Se ele for PAR sÃ³ pode ser IMPAR, ou seja, Agosto, Outubro ou Dezembro.
					numeroDeDiasNoMes = 31;
					// Se ele nÃ£o for PAR eu verifico se ele Ã© igual a 7, ou seja, se ele Ã© o
					// Julho.
				} else if (i == 7) {
					// Caso afirmativo eu atribuo 31 dias Ã  variÃ¡vel.
					numeroDeDiasNoMes = 31;
					// Se ele nÃ£o for o Julho e nem Ã© PAR entÃ£o ele sÃ³ pode ser Setembro ou
					// Novembro.
				} else {
					// Atribuo 30 dias para a variÃ¡vel.
					numeroDeDiasNoMes = 30;
				}
			}
			// *******FIM DAS VERIFICAÃ‡Ã•ES *******
			// Declaro uma variÃ¡vel para guardar o conteudo da soma.
			// Inicio um FOR para somar todas as noites do mÃªs.
			float somaDasDiarias = 0.0F;
			for (float diaria : mes) {
				somaDasDiarias += diaria;
			}
			// Adiciono a nossa variÃ¡vel {list}, um novo objeto do tipo {Output}, primeiro
			// eu passo o array {meses} na posiÃ§Ã£o de {i} menos 1, ou seja, no mÃªs
			// correspondente ao mÃªs que nÃ³s buscamos. O segundo parÃ¢metro que passo Ã© o
			// resultado de uma conta, e Ã© aqui que estÃ¡ a mÃ©dia. Eu divÃ­do o total de
			// gastos extras que foram registradas esse mÃªs, pelo nÃºmero de dias que
			// acabamos de verificar. Eu preciso realizar um casting dos dois nÃºmeros para
			// {float}, caso o contrÃ¡rio o resultado da conta serÃ¡ inteiro. Ainda nesse
			// comando eu formato ele para duas casas decimais.
			list.add(new Output(meses[i - 1], df.format((float) somaDasDiarias / (float) numeroDeDiasNoMes)));
			// Nessa linha Ã© a Ãºtima parte da iteraÃ§Ã£o do FOR. Depois de fazer isso ele
			// repetirÃ¡ essas aÃ§Ãµes mais 11 vezes atÃ© passar por todos os meses.
		}
		// Agora com a {list} completa podemos retorna-la.
		return list;

	}

	public static String mediaOcupacaoAnual() {
		// Instanciando o objeto de acesso ao banco.
		HospedagemDao hDao = HospedagemDao.getInstancia();

		// Declarando o array de Float e populando ele com o valor de todos os
		// registros do ano.
		ArrayList<Float> list = hDao.mediaQtdHospedes("'2017-01-01'", "'2017-12-31'");

		// Declaro uma variÃ¡vel para guardar o conteudo da soma.
		// Inicio um FOR para somar todas as noites do mÃªs.
		float somaDasDiarias = 0.0F;
		for (float diaria : list) {
			somaDasDiarias += diaria;
		}
		// Executando a conta do total de gastos extras dividos pelo nÃºmero de dias do
		// ano, convertendo para duas casas decimais e retornando para a view.
		return df.format((float) somaDasDiarias / 365.0);

	}
}