package VingadoresDoYahoo.HoraMarcada.models;

//import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
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
	
	//@ManyToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "prestador_id", referencedColumnName = "id")
    private Long prestador_id;

	public Servico(Modalidade modalidade, LocalTrabalho localtrabalho, Long prestador_id) {
		this.modalidade = modalidade;
		this.localtrabalho = localtrabalho;
		this.prestador_id = prestador_id;
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

	public Long getPrestador_id() {
		return prestador_id;
	}

	public void setPrestador_id(Long prestador_id) {
		this.prestador_id = prestador_id;
	}
}