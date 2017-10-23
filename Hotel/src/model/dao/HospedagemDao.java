package model.dao;

import java.util.ArrayList;

import javax.persistence.Query;

import model.Hospedagem;

public class HospedagemDao extends DaoGenerico<Hospedagem>{

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
	
	public ArrayList<Object> test() {
		Query q = entityManager.createQuery("SELECT h.gastoExtras FROM Hospedagem h");
		return (ArrayList<Object>) q.getResultList();
		
		
	}

}
