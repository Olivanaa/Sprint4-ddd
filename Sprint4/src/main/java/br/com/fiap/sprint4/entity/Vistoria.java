package br.com.fiap.sprint4.entity;

import java.sql.Date;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import br.com.fiap.sprint4.enums.TipoStatusEnum;

public class Vistoria {
    private TipoStatusEnum status;
    private LocalDate dataVistoria;
    private int idVistoria;
    private ReconhecimentoImagem result;
    private Bicicleta bike;



	public void FazerVistoria() {
        if (result.resultado()) {
            this.dataVistoria = LocalDate.now();
            this.status = TipoStatusEnum.APROVADO;
        } else {
            this.dataVistoria = LocalDate.now();
            this.status = TipoStatusEnum.REPROVADO;
        }}

    
    public Date validaData(LocalDate dataVistoria) {
    	dataVistoria = LocalDate.now();
        return Date.valueOf(dataVistoria);
    }


    public TipoStatusEnum getStatus() {
        return status;
    }

    public void setStatus(TipoStatusEnum status) {
        this.status = status;
    }

    public LocalDate getDataVistoria() {
        return dataVistoria;
    }

    public void setDataVistoria(LocalDate dataVistoria) {
        this.dataVistoria = dataVistoria;
    }

    public int getIdVistoria() {
        return idVistoria;
    }

    public void setIdVistoria(int idVistoria) {
        this.idVistoria = idVistoria;
    }

    
    public Bicicleta getBike() {
		return bike;
	}

	public void setBike(Bicicleta bike) {
		this.bike = bike;
	}


}