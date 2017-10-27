package tests;

import java.util.ArrayList;

import model.Hospedagem;
import model.dao.HospedagemDao;

public class DBTests {
	
	public static void main(String[] args) {
		
		HospedagemDao hDao = HospedagemDao.getInstancia();

		ArrayList<Object> data = hDao.test();
		System.out.println(data);
		for (Object h : data) {
			System.out.println(h);
		}
		
		
	}

}
