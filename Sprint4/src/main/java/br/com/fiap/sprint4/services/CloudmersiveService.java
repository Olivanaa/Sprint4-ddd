package br.com.fiap.sprint4.services;


import com.cloudmersive.client.RecognizeApi;
import com.cloudmersive.client.invoker.ApiClient;
import com.cloudmersive.client.invoker.ApiException;
import com.cloudmersive.client.invoker.Configuration;
import com.cloudmersive.client.invoker.auth.ApiKeyAuth;
import com.cloudmersive.client.model.ImageDescriptionResponse;
import com.cloudmersive.client.model.RecognitionOutcome;

import br.com.fiap.sprint4.entity.ReconhecimentoImagem;

import java.io.File;

public class CloudmersiveService {

    public static void main(String[] args) {
        // Substitua "YOUR_API_KEY" pela sua chave de API real
        String apiKey = "YOUR_API_KEY";
        ReconhecimentoImagem resultado = reconhecimentoImagem(apiKey);

        // Tratar o resultado na classe ReconhecimentoImagem
        if (resultado != null) {
            if (resultado.isBikeEncontrada()) {
                System.out.println("A palavra 'bike' foi encontrada na descrição da imagem.");
            } else {
                System.out.println("A palavra 'bike' não foi encontrada na descrição da imagem.");
            }
        } else {
            System.out.println("Erro ao processar o reconhecimento da imagem.");
        }
    }

    public static ReconhecimentoImagem reconhecimentoImagem(String apiKey) {
        // Configurar a chave de API
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        ApiKeyAuth apikey = (ApiKeyAuth) defaultClient.getAuthentication("Apikey");
        apikey.setApiKey(apiKey);

        // Criar uma instância da API de reconhecimento
        RecognizeApi apiInstance = new RecognizeApi();

        // Substituir pelo caminho do seu arquivo de imagem
        File imageFile = new File("/path/to/file");

        try {
            // Realizar a operação de reconhecimento
            ImageDescriptionResponse response = apiInstance.recognizeDescribe(imageFile);

            // Processar e retornar os resultados
            return processarResultado(response);
        } catch (ApiException e) {
            System.err.println("Exception when calling RecognizeApi#recognizeDescribe");
            e.printStackTrace();
            return null;
        }
    }

    public static ReconhecimentoImagem processarResultado(ImageDescriptionResponse response) {
        // Obter informações da resposta
        boolean operacaoBemSucedida = response.isSuccessful();
        boolean altaConfianca = response.isHighconfidence();

        RecognitionOutcome bestOutcome = response.getBestOutcome();
        RecognitionOutcome runnerUpOutcome = response.getRunnerUpOutcome();

        // Processar os melhores e segundos melhores resultados
        String descricaoMelhorResultado = processarOutcome("Melhor Resultado", bestOutcome);
        String descricaoSegundoMelhorResultado = processarOutcome("Segundo Melhor Resultado", runnerUpOutcome);

        // Verificar se a palavra "bike" está presente em qualquer uma das descrições
        boolean bikeEncontrada = descricaoMelhorResultado.toLowerCase().contains("bike") ||
                                 descricaoSegundoMelhorResultado.toLowerCase().contains("bike");

        // Criar e retornar um objeto ReconhecimentoImagem
        return new ReconhecimentoImagem(operacaoBemSucedida, altaConfianca, descricaoMelhorResultado,
                                        descricaoSegundoMelhorResultado, bikeEncontrada);
    }

    public static String processarOutcome(String tipo, RecognitionOutcome outcome) {
        if (outcome != null) {
            double pontuacaoConfianca = outcome.getConfidenceScore();
            String descricao = outcome.getDescription();

            System.out.println(tipo + ":");
            System.out.println(" - Pontuação de Confiança: " + pontuacaoConfianca);
            System.out.println(" - Descrição: " + descricao);

            return descricao;
        } else {
            System.out.println(tipo + ": Nenhum resultado disponível");
            return "";
        }
    }
}

    
    
	//API de renconhecimento de imagem
	
//	from flask import Flask, render_template, request
//	import tensorflow as tf
//	import numpy as np
//
//	app = Flask(__name__)
//
//	# Carregar o modelo TensorFlow Lite
//	interpreter = tf.lite.Interpreter(model_path="model_unquant.tflite")
//	interpreter.allocate_tensors()
//
//	# Carregar as labels
//	with open("labels.txt", "r") as f:
//	    labels = f.read().splitlines()
//
//	@app.route("/", methods=["GET", "POST"])
//	def index():
//	    if request.method == "POST":
//	        # Processar a imagem enviada pelo usuário
//	        img = tf.image.decode_image(request.files["image"].read(), channels=3)
//	        img = tf.image.resize(img, (224, 224))
//	        img = np.expand_dims(img, axis=0)
//	        img = img / 255.0  # Normalizar os valores para o intervalo [0, 1]
//
//	        # Executar a inferência no modelo
//	        input_details = interpreter.get_input_details()
//	        output_details = interpreter.get_output_details()
//
//	        interpreter.set_tensor(input_details[0]['index'], img)
//	        interpreter.invoke()
//	        prediction = interpreter.get_tensor(output_details[0]['index'])
//
//	        # Obter a classe prevista
//	        predicted_class = labels[np.argmax(prediction)]
//
//	        return render_template("index.html", prediction=predicted_class)
//
//	    return render_template("index.html", prediction=None)
//
//	if __name__ == "__main__":
//	    app.run(debug=True)

