package br.com.senai.entity;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Component
@Table(name = "motoristas")
@Entity(name = "Motorista")
public class Motorista {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer id;
	
	@Size(min = 3, max = 45, message = "O nome da motorista deve conter entre 3 e 45 caracteres")
	@NotBlank(message = "O nome do motorista é obrigatório")
	@Column(name = "nome")
	private String nome;
	
	@NotNull(message = "O numero da CNH não pode ser nulo")
	@Column(name = "cnh")
	private Integer cnh;
	
	@ToString.Exclude
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_transportadora")
	@NotNull(message = "A transportadora é obrigatória")
	private Transportadora transportadora;
	
}
