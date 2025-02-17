package com.bootcamp.core.controllers;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bootcamp.core.configuration.Paginas;
import com.bootcamp.core.model.PostModel;

@Controller
@RequestMapping("/home")
public class BasicController {

	public List<PostModel> getPosts() {
		ArrayList<PostModel> posts = new ArrayList<>();
		posts.add(new PostModel(1,
				"En un mundo cada vez más digital, el desarrollo web se ha convertido en una habilidad esencial para cualquier persona interesada en crear sitios web y aplicaciones online.",
				"http://localhost:8080/img/desarrrolloweb.jpeg", new Date(0), "Desarrollo web"));
		posts.add(new PostModel(2,
				"En un mundo cada vez más digital, el desarrollo web se ha convertido en una habilidad esencial para cualquier persona interesada en crear sitios web y aplicaciones online.",
				"http://localhost:8080/img/desarrrolloweb.jpeg", new Date(0), "Desarrollo web frontend"));
		posts.add(new PostModel(3,
				"En un mundo cada vez más digital, el desarrollo web se ha convertido en una habilidad esencial para cualquier persona interesada en crear sitios web y aplicaciones online.",
				"http://localhost:8080/img/desarrrolloweb.jpeg", new Date(0), "Desarrollo web backend"));
		posts.add(new PostModel(4,
				"En un mundo cada vez más digital, el desarrollo web se ha convertido en una habilidad esencial para cualquier persona interesada en crear sitios web y aplicaciones online.",
				"http://localhost:8080/img/desarrrolloweb.jpeg", new Date(0), "Desarrollo web UX/UI"));
		return posts;
	}

	@GetMapping(path = { "/posts", "/" })
	public String saludar(Model model) {
		model.addAttribute("posts", this.getPosts());
		return "index";
	}

	@GetMapping(path = "/public")
	public ModelAndView post() {
		ModelAndView modelAndView = new ModelAndView(Paginas.HOME);
		modelAndView.addObject("posts", this.getPosts());
		return modelAndView;
	}

	@GetMapping(path = {"/post"})
	public ModelAndView getPostIndividual(
				@RequestParam(defaultValue = "1", name="id", required = false) int id
			) {
		ModelAndView modelAndView = new ModelAndView(Paginas.POST);
		List<PostModel> postFiltrado = this.getPosts().stream().filter((p) -> {
			return p.getId() == id;
		}).collect(Collectors.toList());
		modelAndView.addObject("post", postFiltrado.get(0));
		return modelAndView;
	}
	
	@GetMapping(path = {"/post", "/post/p/{post}"})
	public ModelAndView getPostIndividualPath(
				@PathVariable(required = true, name = "post") int id
			) {
		ModelAndView modelAndView = new ModelAndView(Paginas.POST);
		List<PostModel> postFiltrado = this.getPosts().stream().filter((p) -> {
			return p.getId() == id;
		}).collect(Collectors.toList());
		modelAndView.addObject("post", postFiltrado.get(0));
		return modelAndView;
	}
	
	@GetMapping(path= {"/postnew"})
	public ModelAndView getForm() {
		return new ModelAndView("newpost").addObject("post", new PostModel());
	}
	
	@PostMapping("/addNewPost")
	public String addNewPost(PostModel post, Model model) {
		List<PostModel> posts = this.getPosts();
		posts.add(post);
		model.addAttribute("posts", posts);
		return "index";
		
	}
}
