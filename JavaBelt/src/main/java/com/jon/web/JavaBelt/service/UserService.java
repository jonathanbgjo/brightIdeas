package com.jon.web.JavaBelt.service;


import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.jon.web.JavaBelt.models.Idea;
import com.jon.web.JavaBelt.models.Like;
import com.jon.web.JavaBelt.models.User;
import com.jon.web.JavaBelt.repositories.IdeaRepository;
import com.jon.web.JavaBelt.repositories.LikeRepository;
import com.jon.web.JavaBelt.repositories.UserRepository;

@Service
public class UserService {
	
	final UserRepository userRepository;
	final IdeaRepository ideaRepository;
	final LikeRepository likeRepository;
	

	
	public UserService(UserRepository userRepository, IdeaRepository ideaRepository, LikeRepository likeRepository) {
		this.userRepository = userRepository;
		this.ideaRepository = ideaRepository;
		this.likeRepository = likeRepository;
	}
	
	
	public String saveUser(User user, String c_password, HttpSession session) {
		User UserEmail = userRepository.findByEmail(user.getEmail());
		if(!user.getPassword().equals(c_password) ) {
			System.out.println("password dont match");
			return "password dont match";
		}
		if( UserEmail != null ){
			System.out.println("Email Exist");
			return "Email Exist";			
		}
		else {
			System.out.println("UserEmail : " + UserEmail);
			user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10)));
			userRepository.save(user);
			System.out.println("User id : " +  user.getId());
			System.out.println("User name : " +  user.getAlias());
			session.setAttribute("userid", user.getId());
			session.setAttribute("username", user.getAlias());		
			return "User Created";			
		}	
		
	}
	
	
	@SuppressWarnings("unused")
	public boolean login(String email, String password, HttpSession session) {
		User user = userRepository.findByEmail(email);
		if( user != null) {
			if( BCrypt.checkpw(password, user.getPassword()) ) {
				session.setAttribute("userid", user.getId());
				session.setAttribute("username", user.getAlias());	
				return true;
			}
			else {
				System.out.println("Password not correct");
				return false;
			}
		}
		System.out.println("Email & password not correct");
		return false;
	}


	public List<Idea> findAllIdeas() {
		// TODO Auto-generated method stub
		return ideaRepository.findAll();
	}


	public User findOne(Long username) {
		// TODO Auto-generated method stub
		return userRepository.findOne(username);
	}


	public void addIdea(Idea idea) {
		// TODO Auto-generated method stub
		ideaRepository.save(idea);
		
	}


	public Idea findOneIdea(Long id) {
		// TODO Auto-generated method stub
		return ideaRepository.findOne(id);
	}


	public void delete(Long id) {
		// TODO Auto-generated method stub
		
		ideaRepository.delete(id);
	}


	public void addLike(Long id, Idea idea , User user) {
		// TODO Auto-generated method stub
		List<Like> what = findAllLikes();
		Like newLike = new Like();
		newLike.setUser(user);
		newLike.setIdea(idea);
		boolean inside = false; 
		System.out.println(newLike.getIdea().getDescription()); System.out.println(newLike.getUser().getName());
		if(what.isEmpty()) {
			likeRepository.save(newLike);
		}
		else {
			for(Like like : what) {
				if((like.getIdea() != null) &&(like.getIdea().getId() == newLike.getIdea().getId()))
				{
					if(like.getUser().getEmail() == newLike.getUser().getEmail()) {
						inside = true;
					}
				}
			}
			if(!inside) {
					likeRepository.save(newLike);
			}
		}
	}


	public List<Like> findAllLikes() {
		// TODO Auto-generated method stub
		return likeRepository.findAll();
	}


	public List<Like> findAllLikesByUser(User showUser) {
		// TODO Auto-generated method stub
		return likeRepository.findAllByUser(showUser);
	}


	public User findOneUser(Long id) {
		// TODO Auto-generated method stub
		return userRepository.findOne(id);
	}


}
