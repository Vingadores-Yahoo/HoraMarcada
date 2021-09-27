package src.main.java.VingadoresDoYahoo.HoraMarcada.controllers;

public class CadastroServico {

package VingadoresDoYahoo.HoraMarcada.models;

import java.util.Collection;
import java.util.Collections;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class Servico { 
    	
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@NotBlank
	@Enumerated(EnumType.STRING)
    private Modalidade modalidade;
	
	@NotBlank
	@Enumerated(EnumType.STRING)
    private LocalTrabalho localtrabalho;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "prestador_id", referencedColumnName = "id")
    private Prestador prestador;
	
	public Servico () {}
	
	public Servico (long id, Modalidade modalidade, LocalTrabalho localtrabalho, Prestador prestador) {
		
		this.id = id;
		this.modalidade = modalidade;
		this.localtrabalho = localtrabalho;
		this.prestador = prestador;
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Modalidade getModalidade() {
		return modalidade;
	}

	public void setModalidade(Modalidade modalidade) {
		this.modalidade = modalidade;
	}

	public LocalTrabalho getLocaltrabalho() {
		return localtrabalho;
	}

	public void setLocaltrabalho(LocalTrabalho localtrabalho) {
		this.localtrabalho = localtrabalho;
	}

	public Prestador getPrestador() {
		return prestador;
	}

	public void setPrestador(Prestador prestador) {
		this.prestador = prestador;
	}
	
	

	

}

}
