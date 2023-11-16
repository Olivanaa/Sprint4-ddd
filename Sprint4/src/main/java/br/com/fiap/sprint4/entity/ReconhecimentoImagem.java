package br.com.fiap.sprint4.entity;

import java.io.File;

import com.cloudmersive.client.model.ImageDescriptionResponse;



public class ReconhecimentoImagem {

    private boolean operacaoBemSucedida;
    private boolean altaConfianca;
    private String descricaoMelhorResultado;
    private String descricaoSegundoMelhorResultado;
    private boolean bikeEncontrada;
    

    public ReconhecimentoImagem(boolean operacaoBemSucedida, boolean altaConfianca,
                                String descricaoMelhorResultado, String descricaoSegundoMelhorResultado,
                                boolean bikeEncontrada) {
        this.operacaoBemSucedida = operacaoBemSucedida;
        this.altaConfianca = altaConfianca;
        this.descricaoMelhorResultado = descricaoMelhorResultado;
        this.descricaoSegundoMelhorResultado = descricaoSegundoMelhorResultado;
        this.bikeEncontrada = bikeEncontrada;
    }
    
    public boolean resultado() {
    	if (bikeEncontrada) {
    		return true;
    	}
		return false;
    	
    }
    
    

    public boolean isOperacaoBemSucedida() {
        return operacaoBemSucedida;
    }

    public boolean isAltaConfianca() {
        return altaConfianca;
    }

    public String getDescricaoMelhorResultado() {
        return descricaoMelhorResultado;
    }

    public String getDescricaoSegundoMelhorResultado() {
        return descricaoSegundoMelhorResultado;
    }

    public boolean isBikeEncontrada() {
        return bikeEncontrada;
    }
}



//	public Boolean integrarReconhecimentoImagem(File imagem) {
//	    try {
//	        // Chame o método da API para processar a imagem
//	        ImageDescriptionResponse resultado = CloudmersiveService.reconhecimentoImagem(imagem);
//
//	        // Verifique o resultado do reconhecimento
//	        if (resultado != null && resultado.getDescription() != null) {
//	            String descricao = resultado.getDescription().toLowerCase();
//	            String bike = "bike";
//
//	            // Verifique se a descrição contém a palavra "bike"
//	            if (descricao.contains(bike.toLowerCase())) {
//	                System.out.println("Resultado do Reconhecimento: " + descricao);
//	                return true;
//	            }
//	        }
//
//	    } catch (Exception e) {
//	        e.printStackTrace();
//	    }
//
//	    // Se o processo falhar, não houver resultados ou a palavra "bike" não for encontrada, retorne false
//	    return false;
//	}



