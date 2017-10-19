package model.dao;

public class HospedagemDao {

private static HospedagemDao instancia;

	
	public static HospedagemDao getInstancia() {

		if (instancia == null) {
			instancia = new HospedagemDao();
			return instancia;
		} else {

			return instancia;
		}
	}

}
