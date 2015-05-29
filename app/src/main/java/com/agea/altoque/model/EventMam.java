package com.agea.altoque.model;

/**
 * Created by mdinoto on 28/05/2014.
 */
public class EventMam {
	public static final String AJUSTE_MAM = "mam";
	private int id;
	private String id_resultado;
	private String tipo;
	private String campeonato;
	private String fecha;
	private String generatedDate;
	private String equipo1;
	private String equipo2;
	private String estadoEvento;
	private String horaEstadoEvento;
	private String goles1;
	private String goles2;
	private String minutoIncidencia;

	public EventMam() {
	}

	public EventMam(String id_resultado, String tipo, String campeonato,
					String fecha, String generatedDate, String equipo1, String equipo2,
					String estadoEvento, String horaEstadoEvento, String goles1,
					String goles2, String minutoIncidencia) {
		this.id_resultado = id_resultado;
		this.tipo = tipo;
		this.campeonato = campeonato;
		this.fecha = fecha;
		this.generatedDate = generatedDate;
		this.equipo1 = equipo1;
		this.equipo2 = equipo2;
		this.estadoEvento = estadoEvento;
		this.horaEstadoEvento = horaEstadoEvento;
		this.goles1 = goles1;
		this.goles2 = goles2;
		this.minutoIncidencia = minutoIncidencia;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getId_resultado() {
		return id_resultado;
	}

	public void setId_resultado(String id_resultado) {
		this.id_resultado = id_resultado;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getCampeonato() {
		return campeonato;
	}

	public void setCampeonato(String campeonato) {
		this.campeonato = campeonato;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getEquipo1() {
		return equipo1;
	}

	public void setEquipo1(String equipo1) {
		this.equipo1 = equipo1;
	}

	public String getEquipo2() {
		return equipo2;
	}

	public void setEquipo2(String equipo2) {
		this.equipo2 = equipo2;
	}

	public String getEstadoEvento() {
		return estadoEvento;
	}

	public void setEstadoEvento(String estadoEvento) {
		this.estadoEvento = estadoEvento;
	}

	public String getHoraEstadoEvento() {
		return horaEstadoEvento;
	}

	public void setHoraEstadoEvento(String horaEstadoEvento) {
		this.horaEstadoEvento = horaEstadoEvento;
	}

	public String getGeneratedDate()
    {
		return generatedDate;
	}

	public void setGeneratedDate(String generatedDate) {
		this.generatedDate = generatedDate;
	}

	public String getGoles1() {
		return goles1;
	}

	public void setGoles1(String goles1) {
		this.goles1 = goles1;
	}

	public String getGoles2() {
		return goles2;
	}

	public void setGoles2(String goles2) {
		this.goles2 = goles2;
	}

	public String getMinutoIncidencia() {
		return minutoIncidencia;
	}

	public void setMinutoIncidencia(String minutoIncidencia) {
		this.minutoIncidencia = minutoIncidencia;
	}

}
