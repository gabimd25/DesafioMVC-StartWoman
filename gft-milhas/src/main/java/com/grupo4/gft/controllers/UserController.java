package com.grupo4.gft.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.grupo4.gft.entities.User;
import com.grupo4.gft.servicies.UserService;

@Controller
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;
	
	
	

	@RequestMapping(path = "new") 
	public ModelAndView newUser() {

		ModelAndView mv = new ModelAndView("user/formUser.html");
		mv.addObject("user", new User());

		return mv;
	}

	@RequestMapping(method = RequestMethod.POST, path = "new")
	public ModelAndView registerUser(@Valid User user, BindingResult bindingResult) throws Exception {

		ModelAndView mv = new ModelAndView("user/formUser.html");

		boolean newUser = true;

		if (user.getId() != null) {
			newUser = false;
		}

		if (bindingResult.hasErrors()) {
			mv.addObject("user", user);
			return mv;
		}

		User userSave = userService.saveUser(user);

		if (newUser) {
			mv.addObject("user", new User());
		} else {

			mv.addObject("user", userSave);
		}

		mv.addObject("mensagem", "Usuario salvo com sucesso");
		
		return mv;

	}
	

    //não implementado em botão
	@RequestMapping(path = "edit")
	public ModelAndView editUser(@RequestParam(required = false) Long id) {

		ModelAndView mv = new ModelAndView("user/formUser.html");

		User user;

		if (id == null) {
			user = new User();
		} else {
			try {
				user = userService.getUser(id);
				mv.addObject("mensagem", "Usuario atualizado com sucesso");
			} catch (Exception e) {
				user = new User();
				mv.addObject("mensagem", e.getMessage());
			}

		}

		mv.addObject("user", user);

		return mv;
	}

}
