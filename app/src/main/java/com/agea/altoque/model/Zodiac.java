package com.agea.altoque.model;

import java.io.Serializable;

public class Zodiac implements Serializable {
	public static final String AJUSTE_HOROSCOPO = "horoscopo";
	private int id;
	private long fechaValidezDesde;
	private long fechaValidezHasta;
	private String sign;
	private String since;
	private String until;
	private String data;
	private String date;

	// constructors
	public Zodiac() {
	}

	public Zodiac(int id, long fechaValidezDesde, long fechaValidezHasta, String sign, String since, String until, String data, String date) {
		this.id = id;
		this.fechaValidezDesde = fechaValidezDesde;
		this.fechaValidezHasta = fechaValidezHasta;
		this.sign = sign;
		this.since = since;
		this.until = until;
		this.data = data;
		this.date = date;
	}

	// getters
	public int getId() {
		return id;
	}

	// setters
	public void setId(int id) {
		this.id = id;
	}

	public long getFechaValidezDesde() {
		return fechaValidezDesde;
	}

	public void setFechaValidezDesde(long fechaValidezDesde) {
		this.fechaValidezDesde = fechaValidezDesde;
	}

	public long getFechaValidezHasta() {
		return fechaValidezHasta;
	}

	public void setFechaValidezHasta(long fechaValidezHasta) {
		this.fechaValidezHasta = fechaValidezHasta;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getSince() {
		return since;
	}

	public void setSince(String since) {
		this.since = since;
	}

	public String getUntil() {
		return until;
	}

	public void setUntil(String until) {
		this.until = until;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
