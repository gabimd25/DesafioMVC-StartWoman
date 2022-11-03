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

import com.grupo4.gft.entities.Event;
import com.grupo4.gft.servicies.EventService;

@Controller
@RequestMapping("event")
public class EventController {
	
	@Autowired
	private EventService eventService;
	
	
	@RequestMapping(path="edit")
	public ModelAndView editEvent(@RequestParam (required=false) Long id) {
		
		ModelAndView mv= new ModelAndView("event/formEvent.html");
		
		Event event;
		
		if(id == null) {
			event=new Event();
		}else {
			try {
				event= eventService.getEvent(id);
			}catch(Exception e){
				event=new Event();
				mv.addObject("message", e.getMessage());
			}
		}		
		mv.addObject(event);

		return mv;

	}
	
	@RequestMapping(method = RequestMethod.POST, path="edit")
	public ModelAndView saveEvent(@Valid Event event, BindingResult bindingResult){
		
		ModelAndView mv = new ModelAndView("event/formEvent.html");
		
		try{
			eventService.saveEvent(event);
			if(event.getId() != null)
				mv = new ModelAndView("redirect:/event/list");
			mv.addObject("message", "Evento salvo com sucesso");
		}catch(Exception e){
			
			mv.addObject("message", e.getMessage());
		}
		if(bindingResult.hasErrors()) {
			mv.addObject("event", event);
			return mv;
		}
		if(event.getId()!=null) {
			try {
				event.setStartDate(eventService.getEvent(event.getId()).getStartDate());
				event.setEndDate(eventService.getEvent(event.getId()).getEndDate());
			} catch (Exception e) {
				mv.addObject("message", e.getMessage());	
			}
		}
		
		mv.addObject("event", event);
		
		return mv;
		
	}
	
	@RequestMapping("list")
	public ModelAndView listEvent(String name) {
		ModelAndView mv = new ModelAndView("event/menuPage.html");
		
		mv.addObject("listEvent", eventService.findEvent(name));
		
		return mv;
	}
	

	@RequestMapping("delete")
	public ModelAndView deleteEvent(@RequestParam Long id, RedirectAttributes redirectAttributes) {
		
		ModelAndView mv= new ModelAndView("redirect:/event/list");
		
		try {
			eventService.deleteEvent(id);
			redirectAttributes.addFlashAttribute("messagem", "Evento excluido com sucesso");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("messagem", "Erro ao exluir evento " +e.getMessage());
		}
		return mv;
	}

}
