package model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Hotel {

	@Id
	@GeneratedValue
	private int idHotel;
	@Column
	private int mediaReservas;
	@Column
	private int mediaNoites;
	@Column
	private int mediaDiaria;
	@Column
	private int gastoExtra;
	@Column
	private int gastoTotal;
	@Column
	private int mediaPessoas;
	@Column
	private int mediaOcupacao;

	public Hotel(int mediaReservas, int mediaNoites, int mediaDiaria, int gastoExtra, int gastoTotal, int mediaPessoas,
			int mediaOcupacao) {
		super();
		this.mediaReservas = mediaReservas;
		this.mediaNoites = mediaNoites;
		this.mediaDiaria = mediaDiaria;
		this.gastoExtra = gastoExtra;
		this.gastoTotal = gastoTotal;
		this.mediaPessoas = mediaPessoas;
		this.mediaOcupacao = mediaOcupacao;
	}

	public int getMediaReservas() {
		return mediaReservas;
	}

	public void setMediaReservas(int mediaReservas) {
		this.mediaReservas = mediaReservas;
	}

	public int getMediaNoites() {
		return mediaNoites;
	}

	public void setMediaNoites(int mediaNoites) {
		this.mediaNoites = mediaNoites;
	}

	public int getMediaDiaria() {
		return mediaDiaria;
	}

	public void setMediaDiaria(int mediaDiaria) {
		this.mediaDiaria = mediaDiaria;
	}

	public int getGastoExtra() {
		return gastoExtra;
	}

	public void setGastoExtra(int gastoExtra) {
		this.gastoExtra = gastoExtra;
	}

	public int getGastoTotal() {
		return gastoTotal;
	}

	public void setGastoTotal(int gastoTotal) {
		this.gastoTotal = gastoTotal;
	}

	public int getMediaPessoas() {
		return mediaPessoas;
	}

	public void setMediaPessoas(int mediaPessoas) {
		this.mediaPessoas = mediaPessoas;
	}

	public int getMediaOcupacao() {
		return mediaOcupacao;
	}

	public void setMediaOcupacao(int mediaOcupacao) {
		this.mediaOcupacao = mediaOcupacao;
	}

}
