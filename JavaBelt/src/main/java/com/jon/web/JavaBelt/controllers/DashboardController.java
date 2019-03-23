package com.jon.web.JavaBelt.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jon.web.JavaBelt.models.Idea;
import com.jon.web.JavaBelt.models.Like;
import com.jon.web.JavaBelt.models.User;
import com.jon.web.JavaBelt.service.UserService;



@Controller
public class DashboardController {
	private final UserService userService;

	public DashboardController(UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping("/dashboard")
	public String index(@ModelAttribute("idea") Idea idea, HttpSession session,Model model) {
		if ( session.getAttribute("userid") != null) {
			model.addAttribute("curuser", userService.findOne((Long)session.getAttribute("userid")));
			model.addAttribute("ideas", userService.findAllIdeas());
			model.addAttribute("likes", userService.findAllLikes());
//			List<Idea> ideas = userService.findAllIdeas();
//			for( Idea i :ideas ) {
//				System.out.println(i.getLikes());
//			}
			return "dashboard";
		}else {
			return "redirect:/";
		}
	}
	@PostMapping("/addIdea")
	public String addIdea(@ModelAttribute("idea") Idea idea, BindingResult result) {
		if(result.hasErrors()) {
			return "redirect:/";
		}
		else {
			userService.addIdea(idea);
			return "redirect:/dashboard";
		}
		
	}
	
	@RequestMapping("/users/{id}")
	public String showUser(@PathVariable("id") Long id, Model model) {
//		Idea idea = userService.findOneIdea(id);
//		System.out.println(idea.getDescription() +id);
		User showUser = userService.findOneUser(id);
		model.addAttribute("showUser", showUser);
//		model.addAttribute("idea", idea);
		model.addAttribute("likes", userService.findAllLikesByUser(showUser));
		return "showUser";
	}
	
	@RequestMapping("/bright_ideas/{id}")
	public String showIdea(@PathVariable("id") Long id, Model model) {
		Idea idea = userService.findOneIdea(id);
		model.addAttribute("idea", idea); 
		model.addAttribute("likes", userService.findAllLikes());
		return "showIdea";
	}
	@RequestMapping("/like/{id}")
	public String likeIdea(@PathVariable("id") Long id, Model model, HttpSession session) {
		User user = userService.findOne((Long) session.getAttribute("userid"));
		Idea idea = userService.findOneIdea(id);
		userService.addLike(id, idea, user);
		return"redirect:/dashboard";
	}
	
	@RequestMapping("/delete/{id}")
	public String DeleteIdea(@PathVariable("id") Long id) {
		userService.delete(id);
		return "redirect:/dashboard";
	}
	
}
	