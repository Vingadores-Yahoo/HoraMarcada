
package VingadoresDoYahoo.HoraMarcada.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.NoArgsConstructor;

@Entity
@Table(name = "Servico")
@NoArgsConstructor
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
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "prestador_id", referencedColumnName = "id")
    private Prestador prestador;

	public Servico(@NotBlank Modalidade modalidade, @NotBlank LocalTrabalho localtrabalho) {
		this.modalidade = modalidade;
		this.localtrabalho = localtrabalho;
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

	/*public Prestador getPrestador() {
		return prestador;
	}

	public void setPrestador(Prestador prestador) {
		this.prestador = prestador; 
	}
	*/
	

}
