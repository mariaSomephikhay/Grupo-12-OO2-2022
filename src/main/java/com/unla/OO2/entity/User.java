package com.unla.OO2.entity;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Getter @Setter @NoArgsConstructor
@Table(name="user")
public class User{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Getter(value = AccessLevel.NONE)
	@Column(name="username", nullable=false)
	private String username;
	@Column(name="password", nullable=false)
	private String password;
	@Column(name="nombre")
	private String nombre;
	@Column(name="apellido")
	private String apellido;
	@Column(name="tipo")
	private String tipo;
	@Column(name="dni")
	private int dni;
	@Column(name="email")
	private String email;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="departamento_id", nullable=true)
	private Departamento departamento;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="carrera_id", nullable=true)
	private Carrera carrera;
	@Size(min=1)
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles", joinColumns=@JoinColumn(name="user_id"),
	inverseJoinColumns=@JoinColumn(name="role_id"))
	private Set<Role> roles;
	public Set<Role> getRoles() {
		return roles;
	}
	public String getUsername() {
		return username;
	}
	
}