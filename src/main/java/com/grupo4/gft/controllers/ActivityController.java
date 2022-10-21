package com.grupo4.gft.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.grupo4.gft.entities.Activity;
import com.grupo4.gft.servicies.ActivityService;

@Controller
@RequestMapping("activity")
public class ActivityController {

	@Autowired
	private ActivityService activityService;
	
	@RequestMapping(path = "new")
	public ModelAndView newActivity() {

		ModelAndView mv = new ModelAndView("activity/formActivity.html");
		mv.addObject(new Activity());
		return mv;
	}
	
	@RequestMapping(path = "edit")
	public ModelAndView editActivity(@RequestParam Long id) {

		ModelAndView mv = new ModelAndView("activity/formActivity.html");
		Activity activity;

		if (id == null) {
			activity = new Activity();
		} else {
			try {
				activity = activityService.getActivity(id);
			} catch (Exception e) {
				activity = new Activity();
				mv.addObject("message", e.getMessage());
			}
		}
		mv.addObject(activity);
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST, path = "save")
	public ModelAndView saveActivity(@Valid Activity activity, BindingResult bindingResult) {

		ModelAndView mv = new ModelAndView("activity/formActivity.html");

		boolean novo = true;

		if (activity.getId() != null) {
			novo = false;
		}

		if (bindingResult.hasErrors()) {
			mv.addObject("activity", activity);
			return mv;
		}

		activityService.saveActivity(activity);

		if (novo) {
			mv.addObject("activity", new Activity());
		} else {
			mv.addObject("activity", activity);
		}

		mv.addObject("message", "Atividade salva com sucesso");
		mv.addObject("listActivity", activityService.listAllActivities());
		return mv;
	}
}