package model.dao;

import model.Output;

public class HotelDao extends DaoGenerico<Output> {

	private static HotelDao instancia;

	
	public static HotelDao getInstancia() {

		if (instancia == null) {
			instancia = new HotelDao();
			return instancia;
		} else {

			return instancia;
		}
	}

}
