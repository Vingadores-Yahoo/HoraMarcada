package VingadoresDoYahoo.HoraMarcada.models;

import javax.persistence.*;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @Column(nullable = false)
    private String nome;

    private String email;

    @Column
    private String senha;
    private String telefone;
    
    @Enumerated(EnumType.STRING)
    private RoleType role;

    
    public Usuario () {}

    public Usuario (String nome, String email, String senha, String telefone, RoleType role) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.role = role;
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

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public RoleType getRole() {
        return this.role;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Usuario [email=" + email + ", id=" + id + ", nome=" + nome + ", role=" + role + ", senha=" + senha
                + ", telefone=" + telefone + "]";
    }
    
}