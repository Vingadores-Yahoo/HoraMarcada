package VingadoresDoYahoo.HoraMarcada.controllers;

public class CadastroConsumidor {
    private String nome;
    private String email;
    private String telefone;
    private String endereco;
    private String senha;
    private String confsenha;
    private Boolean termos;

    public CadastroConsumidor() {}

    public CadastroConsumidor(String nome, String email, String telefone, String endereco, String senha, String confsenha, Boolean termos) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
        this.senha = senha;
        this.confsenha = confsenha;
        this.termos = termos;
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getConfsenha() {
        return this.confsenha;
    }

    public void setConfsenha(String confsenha) {
        this.confsenha = confsenha;
    }

    public Boolean getTermos() {
        return this.termos;
    }

    public void setTermos(Boolean termos) {
        this.termos = termos;
    }

}
