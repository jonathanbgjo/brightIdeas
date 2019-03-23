package com.jon.web.JavaBelt.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="ideas")
public class Idea {
	@Id
    @GeneratedValue
    private Long id;
	
	@Size(min = 1, message = "idea cant be empty")
    private String description;
    @Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
   

    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
    private User user;
    
    @OneToMany(mappedBy="idea", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Like> likes;
	
    
    
    public Idea() {
        
    }

	public Long getId() {
		return id;
	}

	public List<Like> getLikes() {
		return likes;
	}

	public void setLikes(List<Like> likes) {
		this.likes = likes;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatedAt() {
		return createdAt;
	}


	public Date getUpdatedAt() {
		return updatedAt;
	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

    

}
