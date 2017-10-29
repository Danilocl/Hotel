package model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Hospedagem {

	@Id
	@GeneratedValue
	@Column(name="hospedagem_id")
	private int hospedagem_id;
	@Column(name="cliente")
	private String cliente;
	@Column(name="quantidade_de_hospede")
	private int qntHospede;
	@Column(name="checkin")
	private Date checkin;
	@Column(name="checkout")
	private Date checkout;
	@Column(name="noites")
	private int noites;
	@Column(name="tipo_Uh")
	private float tipoUh;
	@Column(name="R$UH")
	private float valor;
	@Column(name="gastos_extras")
	private float gastosExtras;
	
	public Hospedagem() {
		
	}

	public Hospedagem(int hospedagem_id, String cliente, int qntHospede, Date chekin, Date checkout, int noites,
			float tipoUh, float valor, float gastoExtras) {
		super();
		this.hospedagem_id = hospedagem_id;
		this.cliente = cliente;
		this.qntHospede = qntHospede;
		this.checkin = chekin;
		this.checkout = checkout;
		this.noites = noites;
		this.tipoUh = tipoUh;
		this.valor = valor;
		this.gastosExtras = gastoExtras;
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
		return checkin;
	}

	public void setChekin(Date chekin) {
		this.checkin = chekin;
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
		return gastosExtras;
	}

	public void setGastoExtras(float gastoExtras) {
		this.gastosExtras = gastoExtras;
	}

}
