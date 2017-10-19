package model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Hospedagem {

	@Id
	@GeneratedValue
	private int hospedagem_id;
	@Column
	private String cliente;
	@Column
	private int qntHospede;
	@Column
	private Date chekin;
	@Column
	private Date checkout;
	@Column
	private int noites;
	@Column
	private float tipoUh;
	@Column
	private float valor;
	@Column
	private float gastoExtras;

	public Hospedagem(int hospedagem_id, String cliente, int qntHospede, Date chekin, Date checkout, int noites,
			float tipoUh, float valor, float gastoExtras) {
		super();
		this.hospedagem_id = hospedagem_id;
		this.cliente = cliente;
		this.qntHospede = qntHospede;
		this.chekin = chekin;
		this.checkout = checkout;
		this.noites = noites;
		this.tipoUh = tipoUh;
		this.valor = valor;
		this.gastoExtras = gastoExtras;
	}

	public int getHospedagem_id() {
		return hospedagem_id;
	}

	public void setHospedagem_id(int hospedagem_id) {
		this.hospedagem_id = hospedagem_id;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public int getQntHospede() {
		return qntHospede;
	}

	public void setQntHospede(int qntHospede) {
		this.qntHospede = qntHospede;
	}

	public Date getChekin() {
		return chekin;
	}

	public void setChekin(Date chekin) {
		this.chekin = chekin;
	}

	public Date getCheckout() {
		return checkout;
	}

	public void setCheckout(Date checkout) {
		this.checkout = checkout;
	}

	public int getNoites() {
		return noites;
	}

	public void setNoites(int noites) {
		this.noites = noites;
	}

	public float getTipoUh() {
		return tipoUh;
	}

	public void setTipoUh(float tipoUh) {
		this.tipoUh = tipoUh;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public float getGastoExtras() {
		return gastoExtras;
	}

	public void setGastoExtras(float gastoExtras) {
		this.gastoExtras = gastoExtras;
	}

}
