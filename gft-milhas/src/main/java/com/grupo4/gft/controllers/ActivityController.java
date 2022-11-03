package com.grupo4.gft.controllers;

import java.util.ArrayList;
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

import com.grupo4.gft.entities.Activity;
import com.grupo4.gft.entities.Event;
import com.grupo4.gft.entities.GroupEvent;
import com.grupo4.gft.entities.Guest;
import com.grupo4.gft.servicies.ActivityService;
import com.grupo4.gft.servicies.EventService;

@Controller
@RequestMapping("activity")
public class ActivityController {

	@Autowired
	private ActivityService activityService;

	@Autowired
	private EventService eventService;


	@RequestMapping(path = "new")
	public ModelAndView newActivity() {

		ModelAndView mv = new ModelAndView("activity/formActivity.html");
		mv.addObject(new Activity());
		return mv;
	}

	@RequestMapping(path = "/edit")
    public ModelAndView editActivity(@RequestParam(required = false) Long id, @RequestParam(required = false) Long idEvent) {

       ModelAndView mv = new ModelAndView("activity/formActivity.html");

       Activity activity;
        Event event;
        List<Guest> listGuestsEvent = new ArrayList<>();
        
        try {
            List<GroupEvent> listGroupEvent = eventService.getEvent(idEvent).getGroups();
           listGroupEvent.forEach((groupEvent) -> {
                listGuestsEvent.addAll(groupEvent.getGuests());
            });
        }catch (Exception e) {
            mv.addObject("message", e.getMessage());
        }

       if (id == null) {
            activity = new Activity();
            try {
                event = eventService.getEvent(idEvent);
                activity.setEvent(event);
                
            } catch (Exception e) {
                mv.addObject("message", e.getMessage());
            }
        } else {
            try {
                activity = activityService.getActivity(id);
            } catch (Exception e) {
                activity = new Activity();
                mv.addObject("message", e.getMessage());
            }
        }
        mv.addObject(activity);
        mv.addObject("listGuestsEvent", listGuestsEvent);

       return mv;
    }

	@RequestMapping(method = RequestMethod.POST, path = "edit")
    public ModelAndView saveActivity(@Valid Activity activity, BindingResult bindingResult) {

       ModelAndView mv = new ModelAndView("activity/formActivity.html");

       if (bindingResult.hasErrors()) {
            mv.addObject("activity", activity);
            return mv;
        }
        
        try {
            activityService.saveActivity(activity);
            eventService.addFinishedActivityScoreInGrupo(activity.getEvent());
            mv = new ModelAndView("redirect:/event/edit?id="+activity.getEvent().getId());
        }catch(Exception e){
            mv.addObject("message", e.getMessage());
        }
 
        mv.addObject(activity);

       return mv;
    }
	@RequestMapping(path = "list")
	public ModelAndView listActivity(String name) {
		ModelAndView mv = new ModelAndView("activity/listActivity.html");

		mv.addObject("list", activityService.findActivity(name));

		return mv;
	}

	@RequestMapping("delete")
	public ModelAndView deleteActivity(@RequestParam Long id,@RequestParam Long idEvent, RedirectAttributes redirectAttributes) {

		ModelAndView mv = new ModelAndView("redirect:/event/edit?id="+idEvent);

		try {
			activityService.deleteActivity(id);

			redirectAttributes.addFlashAttribute("messagem", "Atividade excluido com sucesso");

		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("messagem", "Erro ao exluir Atividade " + e.getMessage());

		}

		return mv;

	}
}
