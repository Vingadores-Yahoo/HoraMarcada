package VingadoresDoYahoo.HoraMarcada.controllers;

import javax.persistence.CascadeType;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import VingadoresDoYahoo.HoraMarcada.models.*;

public class CadastroServico { 
    	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@Enumerated(EnumType.STRING)
    private Modalidade modalidade;
	
	@Enumerated(EnumType.STRING)
    private LocalTrabalho localtrabalho;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "prestador_id", referencedColumnName = "id")
    private Prestador prestador;
	
	public CadastroServico () {}
	
	public CadastroServico (long id, Modalidade modalidade, LocalTrabalho localtrabalho, Prestador prestador) {
		
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