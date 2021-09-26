package VingadoresDoYahoo.HoraMarcada.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "Agendamento")
public class Agendamento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String cliente;

    @NotBlank
    @Column(nullable = false)
    private String data;

    @NotBlank
    @Column(nullable = false)
    private String modalidade;

    @NotBlank
    @Column(nullable = false)
    private String endereco;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    public Agendamento() {}

    public Agendamento(Long id, String cliente, String data, String modalidade, String endereco, Usuario usuario) {
        this.id = id;
        this.cliente = cliente;
        this.data = data;
        this.modalidade = modalidade;
        this.endereco = endereco;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Agendamento [cliente=" + cliente + ", data=" + data + ", endereco=" + endereco + ", id=" + id
                + ", modalidade=" + modalidade + ", usuario=" + usuario + "]";
    }

}