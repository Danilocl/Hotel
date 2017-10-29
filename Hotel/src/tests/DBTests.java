package tests;

import java.util.ArrayList;

import model.Hospedagem;
import model.dao.HospedagemDao;

public class DBTests {
	
	public static void main(String[] args) {
		
		HospedagemDao hDao = HospedagemDao.getInstancia();

		ArrayList<Object> data = hDao.mediaReservasMes("'2017-5-1'", "'2017-5-31'");
		System.out.println(data.size());
		for (Object h : data) {
			System.out.println(h);
		}
		
		
	}

}
