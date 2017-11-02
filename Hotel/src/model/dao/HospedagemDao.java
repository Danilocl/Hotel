package model.dao;

import java.util.ArrayList;

import javax.persistence.Query;

import model.Hospedagem;

public class HospedagemDao extends DaoGenerico<Hospedagem> {

	private static HospedagemDao instancia;

	private HospedagemDao() {
		super();
	}

	public static HospedagemDao getInstancia() {

		if (instancia == null) {
			instancia = new HospedagemDao();
			return instancia;
		} else {

			return instancia;
		}
	}

	/**
	 * Método que retorna o todos os nomes dos clientes que fizeram reserva no
	 * período selecionado.
	 * 
	 * @param dataInicial
	 *            Data inicial da contagem.
	 * @param dataFinal
	 *            Data final da contagem.
	 * @return Lista de Object. Coloquei Object por ser mais genérico e eu posso
	 *         converto-lo depois.
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Object> mediaReservasMes(String dataInicial, String dataFinal) {
		// Declarando uma {Query} e criando a instrução que será inserido no banco.
		Query q = entityManager.createQuery("SELECT h.cliente FROM Hospedagem h WHERE h.checkin >= " + dataInicial
				+ " AND h.checkin <= " + dataFinal);
		// Retornando a lista de resultados. Preciso fazer o casting para ArrayList de
		// Object pois o método por padrão retorna um {List}.
		return (ArrayList<Object>) q.getResultList();

	}

	/**
	 * Método que retorna o número de noites que cada reserva possui no período
	 * selecionado.
	 * 
	 * @param dataInicial
	 *            Data inicial da contagem.
	 * @param dataFinal
	 *            Data final da contagem.
	 * @return Lista de Integer. Coloquei Integer por ser mais apropriado.
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Integer> mediaNoitesMes(String dataInicial, String dataFinal) {
		// Declarando uma {Query} e criando a instrução que será inserido no banco.
		Query q = entityManager.createQuery("SELECT h.noites FROM Hospedagem h WHERE h.checkin >= " + dataInicial
				+ " AND h.checkin <= " + dataFinal);
		// Retornando a lista de resultados. Preciso fazer o casting para ArrayList de
		// Object pois o método por padrão retorna um {List}.
		return (ArrayList<Integer>) q.getResultList();
	}

	/**
	 * Método que retorna o valor das diarias que cada reserva possui no período
	 * selecionado.
	 * 
	 * @param dataInicial
	 *            Data inicial da contagem.
	 * @param dataFinal
	 *            Data final da contagem.
	 * @return Lista de Float. Coloquei Float por ser mais apropriado.
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Float> mediaDiariaMes(String dataInicial, String dataFinal) {
		// Declarando uma {Query} e criando a instrução que será inserido no banco.
		Query q = entityManager.createQuery("SELECT h.valor FROM Hospedagem h WHERE h.checkin >= " + dataInicial
				+ " AND h.checkin <= " + dataFinal);
		// Retornando a lista de resultados. Preciso fazer o casting para ArrayList de
		// Object pois o método por padrão retorna um {List}.
		return (ArrayList<Float>) q.getResultList();
	}

	/**
	 * Método que retorna o valor dos gastos extras que cada reserva possui no
	 * período selecionado.
	 * 
	 * @param dataInicial
	 *            Data inicial da contagem.
	 * @param dataFinal
	 *            Data final da contagem.
	 * @return Lista de Float. Coloquei Float por ser mais apropriado.
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Float> mediaGastosMes(String dataInicial, String dataFinal) {
		// Declarando uma {Query} e criando a instrução que será inserido no banco.
		Query q = entityManager.createQuery("SELECT h.gastosExtras FROM Hospedagem h WHERE h.checkin >= " + dataInicial
				+ " AND h.checkin <= " + dataFinal);
		// Retornando a lista de resultados. Preciso fazer o casting para ArrayList de
		// Object pois o método por padrão retorna um {List}.
		return (ArrayList<Float>) q.getResultList();
	}

	/*
	 * m�todo que retorna a quantidade de hospedes para fazer o c�lculo da m�dia de
	 * pessoas no ano e no m�s
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Integer> mediaQtdHospedes(String dataInicial, String dataFinal) {
		// Declarando uma {Query} e criando a instrução que será inserido no banco.
		Query q = entityManager.createQuery("SELECT h.qntHospede FROM Hospedagem h WHERE h.checkin >= "
				+ dataInicial + " AND h.checkin <= " + dataFinal);
		// Retornando a lista de resultados. Preciso fazer o casting para ArrayList de
		// Object pois o método por padrão retorna um {List}.
		return (ArrayList<Integer>) q.getResultList();
	}
	
	

}
