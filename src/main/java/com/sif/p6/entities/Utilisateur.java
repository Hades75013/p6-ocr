package com.sif.p6.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import com.sif.p6.security.BCryptManagerUtil;
import com.sif.p6.security.RoleEnum;


@SuppressWarnings("serial")
@Entity

public class Utilisateur implements Serializable, UserDetails{
	
	@Id@GeneratedValue
	private Long id;
	
	@NotNull
	@Size(min=2)
	private String nom;
	
	@NotNull
	@Size(min=2)
	private String prenom;
	
	@NotNull
	private String pseudo;
	
	@NotNull
	@Size(min=5)
	private String motDePasse;
	
	@NotNull
	private String email;
	
	@OneToMany
	private Set<Spot> spots;
	
	@OneToMany
	private Set<Topo> topos;
	
	@OneToMany
	private Set<Commentaire> commentaires;
	
	@OneToMany
	private Set<Emprunt> emprunts;
	
	@ElementCollection(targetClass = RoleEnum.class, fetch = FetchType.EAGER)
	@Cascade(value = CascadeType.REMOVE)
    @JoinTable(
            indexes = {@Index(name = "INDEX_UTILISATEUR_ROLE", columnList = "id_utilisateur")},
            name = "roles",
            joinColumns = @JoinColumn(name = "id_utilisateur")
    )
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Collection<RoleEnum> roles;
	
	@NotNull
    private boolean accountNonExpired;

	@NotNull
    private boolean accountNonLocked;

    @NotNull
    private boolean credentialsNonExpired;

    @NotNull
	private boolean active;

    
	public Utilisateur(Long id, @NotNull @Size(min = 2) String nom, @NotNull @Size(min = 2) String prenom,
			@NotNull String pseudo, @NotNull @Size(min = 5) String motDePasse, @NotNull String email,
			Set<Topo> topos, Set<Commentaire> commentaires, Set<Emprunt> emprunts, Collection <RoleEnum> roles) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.pseudo = pseudo;
		this.motDePasse = motDePasse;
		this.email = email;
		this.topos = topos;
		this.commentaires = commentaires;
		this.emprunts = emprunts;
		this.roles = roles;
		this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.active = true;
	}

	public Utilisateur() {
		this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.active = true;
        this.roles = Collections.singletonList(RoleEnum.USER);
	}
	
	public Utilisateur(Collection<RoleEnum> roles) {
		super();
		this.roles = roles;
	}
    
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	
	 public void setMotDePasse(String motDePasse) {
	        if (!motDePasse.isEmpty()) {
	            this.motDePasse = BCryptManagerUtil.passwordencoder().encode(motDePasse);
	        }
	    }
	   
	    public String getMotDePasse() {
			return motDePasse;
		}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Set<Spot> getSpots() {
		return spots;
	}

	public void setSpots(Set<Spot> spots) {
		this.spots = spots;
	}

	public Set<Topo> getTopos() {
		return topos;
	}

	public void setTopos(Set<Topo> topos) {
		this.topos = topos;
	}

	public Set<Commentaire> getCommentaires() {
		return commentaires;
	}

	public void setCommentaires(Set<Commentaire> commentaires) {
		this.commentaires = commentaires;
	}

	public Set<Emprunt> getEmprunts() {
		return emprunts;
	}

	public void setEmprunts(Set<Emprunt> emprunts) {
		this.emprunts = emprunts;
	}

	public Collection<RoleEnum> getRoles() {
		return roles;
	}

	public void setRoles(Collection<RoleEnum> roles) {
		this.roles = roles;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
        String roles = StringUtils.collectionToCommaDelimitedString(getRoles().stream()
                .map(Enum::name).collect(Collectors.toList()));
        return AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
    }
	
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return motDePasse;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return pseudo;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
}
