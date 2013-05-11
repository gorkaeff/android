package com.example.ejemplolistview;

public class Dia {
	private String dia;
	private int horario;
	
	public Dia(String _d){
		dia = new String(_d);
		horario = 0;
	}
	
	public String getDia() {
		return dia;
	}
	public void setDia(String dia) {
		this.dia = dia;
	}
	public int getHorario() {
		return horario;
	}
	public void setHorario(int horario) {
		this.horario = horario;
	}
}
