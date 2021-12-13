package VingadoresDoYahoo.HoraMarcada.models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Table;

import lombok.NoArgsConstructor;

@Entity
@Table(name = "Servico")
@NoArgsConstructor
public class Servico { 
    	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@Enumerated(EnumType.STRING)
    private Modalidade modalidade;
	
	@Enumerated(EnumType.STRING)
    private LocalTrabalho localtrabalho;

	private String UrlFotos;
	
	@ManyToOne()
    @JoinColumn(name = "prestador_id", referencedColumnName = "id")
    private Prestador prestador;

	public Servico(Modalidade modalidade, LocalTrabalho localtrabalho,String url, Prestador prestador) {
		this.modalidade = modalidade;
		this.localtrabalho = localtrabalho;
		this.UrlFotos = url;
		this.prestador = prestador;
	}

	public Long getId() {
		return id;
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

	public String getUrlFotos() {
		return UrlFotos;
	}

	public void setUrlFotos(String urlFotos) {
		UrlFotos = urlFotos;
	}

	public Prestador getPrestador() {
		return prestador;
	}

	public void setPrestador(Prestador prestador) {
		this.prestador = prestador;
	}

}