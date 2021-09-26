package VingadoresDoYahoo.HoraMarcada.controllers;

public class CadastroAgendamento {
    private String cliente;
    private String data;
    private String modalidade;
    private String endereco;

    public CadastroAgendamento() {}

    public CadastroAgendamento(String cliente, String data, String modalidade, String endereco) {
        this.cliente = cliente;
        this.data = data;
        this.modalidade = modalidade;
        this.endereco = endereco;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
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
