package com.grupo4.gft.controllers;

import java.util.List;

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
import com.grupo4.gft.entities.GroupEvent;
import com.grupo4.gft.servicies.EventService;
import com.grupo4.gft.servicies.GroupService;
import com.grupo4.gft.servicies.GuestService;


@Controller
@RequestMapping("group")
public class GroupController {

	@Autowired
	private GroupService groupService;
	
	@Autowired
	private EventService eventService;
	
	@Autowired
	private GuestService guestService;
	
	@RequestMapping(path="edit")
	public ModelAndView groupEvent(@RequestParam (required=false) Long id, @RequestParam (required=false) Long idEvent ) {
		
		ModelAndView mv= new ModelAndView("group/formGroup.html");
		
		GroupEvent group;
		Event event;
		
		if(id == null) {
			group = new GroupEvent();			
			try {
				event = eventService.getEvent(idEvent);
				group.setEvent(event);
				
			}catch(Exception e){
				mv.addObject("message", e.getMessage());
			}			
		}else {
			try {
				group = groupService.getGroupEvent(id);
			}catch(Exception e){
				group =new GroupEvent();
				mv.addObject("message", e.getMessage());
			}
		}
		
		mv.addObject(group);
		mv.addObject("listGuest", guestService.listAllGuest());
		
		
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST, path="edit")
	public ModelAndView saveGroup(@Valid GroupEvent groupEvent, BindingResult bindingResult) {
		
		ModelAndView mv = new ModelAndView("group/formGroup.html");
		
		if(bindingResult.hasErrors()) {
			mv.addObject("groupEvent", groupEvent);
			return mv;
		}
		
		try {
			groupService.saveGroupEvent(groupEvent);
			mv.addObject("groupEvent", groupEvent);
			mv.addObject("message", "Grupo salvo com sucesso");
		}catch(Exception e) {
			mv.addObject("message", e.getMessage());			
		}
		
		mv.addObject("listGroup", groupService.listAllGroupEvent());
		mv.addObject("listGuest", guestService.listAllGuest());
		
		return mv;
		
	}
	
	@RequestMapping("/delete")
	public ModelAndView deleteGroup(@RequestParam Long id,@RequestParam Long idEvent, RedirectAttributes redirectAttributes) {
		
		ModelAndView mv= new ModelAndView("redirect:/event/edit?id="+idEvent);
		
		
		try {
			
		groupService.deleteGroupEvent(id);
		redirectAttributes.addFlashAttribute("messagem", "Grupo excluido com sucesso");
			
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("messagem", "Erro ao exluir grupo " +e.getMessage());
		}
		
		
		return mv;
	}
	
	@RequestMapping("/removeGuest")
	public ModelAndView removeGuest(@RequestParam Long id,@RequestParam Long idGuest, RedirectAttributes redirectAttributes) {
		
		ModelAndView mv= new ModelAndView("redirect:/group/edit?id="+id);
		
		
		try {
			
		groupService.removeGuest(id, idGuest);
		redirectAttributes.addFlashAttribute("messagem", "Participante removido com sucesso");
			
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("messagem", "Erro ao remover participante " +e.getMessage());
		}
		
		
		return mv;
		
	}
	
	@RequestMapping("/ranking")
	public ModelAndView ranking(RedirectAttributes redirectAttributes) {
		
		ModelAndView mv= new ModelAndView("group/ranking.html");
		List<GroupEvent> listGroups= groupService.listAllGroupEventByScore();
		
		mv.addObject("listGroups", listGroups);
		return mv;
		
	}
	
		
}
