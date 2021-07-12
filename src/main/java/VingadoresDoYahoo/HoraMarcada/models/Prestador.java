package VingadoresDoYahoo.HoraMarcada.models;

import javax.persistence.*;

@Entity
@Table(name = "prestadorServico")
public class Prestador {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    private LocalTrabalho localtrabalho;

    @Enumerated(EnumType.STRING)
    private RoleType roletype;

    private String endereco;
    private String bairro;
    private String cidade;
    private String estado;

    public Prestador() {}


    public Prestador(String endereco, String bairro, String cidade, String estado,Usuario usuario) {

        this.usuario = usuario;
        this.endereco = endereco;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalTrabalho getLocaltrabalho() {
        return this.localtrabalho;
    }

    public void setLocaltrabalho(LocalTrabalho localtrabalho) {
        this.localtrabalho = localtrabalho;
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

    public String getCidade() {
        return this.cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }


}