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
	 * @return Lista de Object. Coloque Object por ser mais genérico e eu posso
	 *         converto-lo depois,
	 */
	public ArrayList<Object> mediaReservasMes(String dataInicial, String dataFinal) {
		// Declarando uma {Query} e criando a instrução que será inserido no banco.
		Query q = entityManager.createQuery("SELECT h.cliente FROM Hospedagem h WHERE h.checkin >= " + dataInicial
				+ " AND h.checkin <= " + dataFinal);
		// Retornando a lista de resultados. Preciso fazer o casting para ArrayList de
		// Object pois o método por padrão retorna um {List}.
		return (ArrayList<Object>) q.getResultList();

	}

}
