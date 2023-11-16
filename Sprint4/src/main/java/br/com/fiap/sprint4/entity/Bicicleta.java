package br.com.fiap.sprint4.entity;

import javax.xml.bind.annotation.XmlRootElement;

import br.com.fiap.sprint4.enums.TipoSeguroEnum;

@XmlRootElement
public class Bicicleta {

    private String marca;
    private String modelo;
    private String numeroDeSerie;
    private double valorNota;
    private double valorAtual;
    private int id;
    private TipoSeguroEnum tipoSeguro;
	private Cliente cliente;
	
	
	
    public TipoSeguroEnum getTipoSeguro() {
		return tipoSeguro;
	}

	public void setTipoSeguro(TipoSeguroEnum tipoSeguro) {
		this.tipoSeguro = tipoSeguro;
	}

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }


    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getNumeroDeSerie() {
        return numeroDeSerie;
    }

    public void setNumeroDeSerie(String numeroDeSerie) {
        this.numeroDeSerie = numeroDeSerie;
    }

    public double getValorNota() {
        return valorNota;
    }

    public void setValorNota(double valorNota) {
        this.valorNota = valorNota;
    }

    public double getValorAtual() {
        return valorAtual;
    }

    public void setValorAtual(double valorAtual) {
        this.valorAtual = valorAtual;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



}
