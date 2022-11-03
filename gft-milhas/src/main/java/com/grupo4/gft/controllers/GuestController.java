package com.grupo4.gft.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.grupo4.gft.entities.Guest;
import com.grupo4.gft.servicies.GuestService;

@Controller
@RequestMapping("guest")
public class GuestController {
	
	@Autowired
	private GuestService guestService;
	
	
	@RequestMapping(path="edit")
	public ModelAndView editGuest(@RequestParam (required=false) Long id) {
		
		ModelAndView mv= new ModelAndView("guest/formGuest.html");
		
		Guest guest;
		
		if(id == null) {
			guest=new Guest();
		}else {
			try {
				guest= guestService.getGuest(id);
			}catch(Exception e){
				guest=new Guest();
				mv.addObject("message", e.getMessage());
			}
		}
		mv.addObject(guest);
		
		return mv;
		
		
	}
	
	@RequestMapping(method = RequestMethod.POST, path="edit")
	public ModelAndView saveGuest(@Valid Guest guest, BindingResult bindingResult) {
		
		ModelAndView mv = new ModelAndView("guest/formGuest.html");
		
		boolean novo= true;
		
		if(guest.getId() != null) {
			novo= false;
		}
		
		if(bindingResult.hasErrors()) {
			mv.addObject("guest", guest);
			return mv;
		}
		try {
			guestService.saveGuest(guest);
			mv.addObject("message", "Participante salvo com sucesso");
		}catch(Exception e) {
			mv.addObject("message", e.getMessage());
		}
		
		
		if(novo) {
			mv.addObject("guest", new Guest());
		}else {
			mv.addObject("guest", guest);
		}
		
		mv.addObject("listGuest", guestService.listAllGuest());
		
		return mv;
		
	}
	

	@RequestMapping("list")
	public ModelAndView listAllGuest(String name, String fourletters) {
		ModelAndView mv = new ModelAndView("guest/listGuest.html");
		
		mv.addObject("listGuest", guestService.listGuest(name, fourletters));
		
		mv.addObject("name", name);
		mv.addObject("fourletters", fourletters);
		
		return mv;
	}
	

	@RequestMapping("/delete")
	public ModelAndView deleteGuest(@RequestParam Long id, RedirectAttributes redirectAttributes) {
		
		ModelAndView mv= new ModelAndView("redirect:/guest/list");
		
		
		try {
			
		guestService.deleteGuest(id);
		redirectAttributes.addFlashAttribute("messagem", "Participante excluido com sucesso");
			
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("messagem", "Erro ao exluir participante " +e.getMessage());
		}
		
		return mv;
		
	}
	
	

}
