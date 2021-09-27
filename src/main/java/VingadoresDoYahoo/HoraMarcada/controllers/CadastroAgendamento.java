package VingadoresDoYahoo.HoraMarcada.controllers;

public class CadastroAgendamento {
    private String nome;
    private String telefone;
    private String data;
    private String modalidade;
    private String endereco;

    public CadastroAgendamento() {}

    public CadastroAgendamento(String nome, String telefone, String data, String modalidade, String endereco) {
        this.nome = nome;
        this.telefone = telefone;
        this.data = data;
        this.modalidade = modalidade;
        this.endereco = endereco;
    }


    public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getModalidade() {
        return modalidade;
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

}
