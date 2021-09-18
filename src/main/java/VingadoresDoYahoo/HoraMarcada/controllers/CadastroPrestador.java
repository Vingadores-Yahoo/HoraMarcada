package VingadoresDoYahoo.HoraMarcada.controllers;

//import VingadoresDoYahoo.HoraMarcada.models.LocalTrabalho;

public class CadastroPrestador {
    private String nome;
    private String email;
    private String telefone;
    private String senha;
    private String confesenha;
    private Boolean termos;
    private String endereco;
    private String bairro;
    
//    private LocalTrabalho localtrabalho;

    public CadastroPrestador() {}

    
	public CadastroPrestador(String nome, String email, String telefone, String senha, String confesenha,
			Boolean termos, String endereco, String bairro) {
		
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.senha = senha;
		this.confesenha = confesenha;
		this.termos = termos;
		this.endereco = endereco;
		this.bairro = bairro;
		//this.localtrabalho = localtrabalho;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getConfesenha() {
		return this.confesenha;
	}

	public void setConfesenha(String confesenha) {
		this.confesenha = confesenha;
	}

	public Boolean getTermos() {
		return this.termos;
	}

	public void setTermos(Boolean termos) {
		this.termos = termos;
	}

	public String getEndereco() {
		return this.endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getBairro() {
		return this.bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

/*
	public LocalTrabalho getLocaltrabalho() {
		return localtrabalho;
	}

	public void setLocalTrabalho(LocalTrabalho localtrabalho) {
		this.localtrabalho = localtrabalho;
	}
*/
}