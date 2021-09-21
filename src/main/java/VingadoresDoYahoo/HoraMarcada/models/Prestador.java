package VingadoresDoYahoo.HoraMarcada.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "Prestador")
public class Prestador {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    private LocalTrabalho localtrabalho;

    @NotBlank
    @Column(nullable = false)
    private String endereco;

    @NotBlank
    @Column(nullable = false)
    private String bairro;

    public Prestador() {}


    public Prestador(String endereco, String bairro, Usuario usuario) {

        this.usuario = usuario;
        this.endereco = endereco;
        this.bairro = bairro;
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


    @Override
    public String toString() {
        return "Prestador [bairro=" + bairro + ", endereco=" + endereco + ", id=" + id + ", localtrabalho="
                + localtrabalho + ", usuario=" + usuario + "]";
    }

    

}