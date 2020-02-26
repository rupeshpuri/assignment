package com.assigment.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.assigment.constant.AuthProvider;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user", uniqueConstraints = { @UniqueConstraint(columnNames = "email") })
public class User extends Audit implements Serializable {

	private static final long serialVersionUID = 1253728506958364744L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;

	@Column(nullable = false)
	private String name;

	@JsonIgnore
	@Email
	@Column(nullable = false)
	private String email;

	private String imageUrl;
	
	@JsonIgnore
	private String password;

	@Size(max = 250)
	@Column(name = "subtitle", length = 250)
	private String subtitle;

	@Size(max = 500)
	@Column(name = "about", length = 500)
	private String about;

	@JsonIgnore
	@NotNull
	@Enumerated(EnumType.STRING)
	private AuthProvider provider;


	
	public User() {

	}

	public User(Long id) {
		this.id = id;
	}

	public User(Long id, String name, String imageUrl) {
		this.id = id;
		this.name = name;
		this.imageUrl = imageUrl;
	}


	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public AuthProvider getProvider() {
		return provider;
	}

	public void setProvider(AuthProvider provider) {
		this.provider = provider;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


}
