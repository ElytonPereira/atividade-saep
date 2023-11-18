package br.com.senai.entity;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Component
@Table(name = "transportadoras")
@Entity(name = "Transportadora")
public class Transportadora {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer id;
	
	@Size(max = 45, message = "O nome da motorista não deve conter mais de 45 caracteres")
	@NotBlank(message = "O nome do motorista é obrigatório")
	@Column(name = "nome")
	private String nome;
	
}
