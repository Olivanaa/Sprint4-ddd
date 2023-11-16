package br.com.fiap.sprint4.entity;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.xml.bind.annotation.XmlRootElement;

import org.mindrot.jbcrypt.BCrypt;

import com.fasterxml.jackson.annotation.JsonProperty;


import br.com.fiap.sprint4.dao.ClienteDao;



@XmlRootElement
public class Cliente {

    private int id;
    private String nome;
    private String sobrenome;
    private String cpf;
	private String genero;
    private String dtaNasc;
    private String cep;    
    private String logradouro;
	private String complemento;
    private String bairro;
    @JsonProperty("cidade")
    private String localidade;
    @JsonProperty("estado")
    private String uf;
    private String numero;
    private String telefone;
    private String email;
    private String login;
    private String senha;



    public boolean validaCPF(String cpf) {
        if (cpf.length() != 11) {
            return false;
        }
        if (cpf.equals("00000000000") || cpf.equals("11111111111") || cpf.equals("22222222222") || cpf.equals("33333333333") ||
                cpf.equals("44444444444") || cpf.equals("55555555555") || cpf.equals("66666666666") || cpf.equals("77777777777") ||
                cpf.equals("88888888888") || cpf.equals("99999999999") ){
            return false;
        }
        int[] digitos = new int[11];
        for( int i = 0; i< 11; i++){
            digitos[i] = cpf.charAt(i) - '0';
        }
        int soma1 = 0;
        int soma2 = 0;
        int peso1 = 10;
        int peso2 = 11;

        for (int i = 0; i < 9; i++) {
            int num = cpf.charAt(i) - '0';
            soma1 = soma1 + (num * peso1);
            peso1 = peso1 - 1;
        }
        for (int i = 0; i < 10; i++) {
            int num = cpf.charAt(i) - '0';
            soma2 = soma2 + (num * peso2);
            peso2 = peso2 - 1;
        }

        int resto1 = (soma1 % 11);
        int digUm = (resto1 < 2) ? 0 : (11 - resto1);

        int resto2 = soma2 % 11;
        int digDois = (resto2 < 2) ? 0 : (11 - resto2);

        return (digUm == digitos[9] && digDois == digitos[10]);
    }

    public LocalDate validaData(String dtaNasc) throws ParseException, DateTimeParseException {
    	DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dataNascimento;
        dataNascimento = LocalDate.parse(dtaNasc, date);
        return dataNascimento;
    }

    public boolean validaIdade(LocalDate dataNascimento)throws ParseException{
//        dataNascimento = validaData(this.getDtaNasc());
        LocalDate dtaAtual = LocalDate.now();
        Period periodo = Period.between(dataNascimento, dtaAtual);

        int idade = periodo.getYears();

        return idade >= 18;
    }
    
    public static String senhaCripto(String senha) {
        return BCrypt.hashpw(senha, BCrypt.gensalt());
    }
    
    public boolean verificaSenha(String senhaDigitada, String senhaBanco) {
		return BCrypt.checkpw(senhaDigitada, senhaBanco);
	}
    
    public boolean verificaLogin(String login) {
        ClienteDao dao = new ClienteDao();
        return dao.verificaLogin(this.getLogin());
    }
     

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

 

	public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDtaNasc() {
        return dtaNasc;
    }

    public void setDtaNasc(String dtaNasc) {
        this.dtaNasc = dtaNasc;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}





}
