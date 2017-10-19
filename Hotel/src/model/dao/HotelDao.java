package model.dao;

import model.Hotel;

public class HotelDao extends DaoGenerico<Hotel> {

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
