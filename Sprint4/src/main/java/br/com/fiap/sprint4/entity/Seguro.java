package br.com.fiap.sprint4.entity;

import java.time.LocalDate;
import java.util.Random;

import br.com.fiap.sprint4.enums.TipoSeguroEnum;
import br.com.fiap.sprint4.enums.TipoStatusEnum;



public class Seguro extends Vistoria{

    private int idSeguro;
    private int nroApolice;
    private LocalDate dataVigencia;
    private LocalDate dataContratacao;
    private TipoSeguroEnum tipoSeguro;
    private Cliente cliente;
    private Bicicleta bike;
    private Vistoria vistoria;
    
    public void cadastrarSeguro() {
        this.nroApolice++;

        LocalDate dataContratacao = LocalDate.now();

        TipoStatusEnum statusVistoria = super.getStatus();

        if (statusVistoria.equals(TipoStatusEnum.REPROVADO)) {
 
            return;
        }
        
        
        this.dataVigencia = dataContratacao.plusYears(1);

        this.nroApolice = gerarNumeroApoliceAleatorio();
    }
    
	private int gerarNumeroApoliceAleatorio() {
        Random random = new Random();
        return random.nextInt(900000) + 100000;
    }

    public Vistoria getVistoria() {
		return vistoria;
	}

	public void setVistoria(Vistoria vistoria) {
		this.vistoria = vistoria;
	}



	public int getIdSeguro() {
		return idSeguro;
	}

	public void setIdSeguro(int idSeguro) {
		this.idSeguro = idSeguro;
	}

	public int getNroApolice() {
		return nroApolice;
	}

	public void setNroApolice(int nroApolice) {
		this.nroApolice = nroApolice;
	}

	public LocalDate getDataVigencia() {
		return dataVigencia;
	}

	public void setDataVigencia(LocalDate dataVigencia) {
		this.dataVigencia = dataVigencia;
	}

	public LocalDate getDataContratacao() {
		return dataContratacao;
	}

	public void setDataContratacao(LocalDate dataContratacao) {
		this.dataContratacao = dataContratacao;
	}

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

	public Bicicleta getBike() {
		return bike;
	}

	public void setBike(Bicicleta bike) {
		this.bike = bike;
	}





}