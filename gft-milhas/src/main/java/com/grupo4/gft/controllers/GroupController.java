package com.grupo4.gft.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.grupo4.gft.servicies.GroupService;

@Controller
@RequestMapping("group")
public class GroupController {
	
	@Autowired
	private GroupService groupService;
	
	@RequestMapping(path="ranking")
	public ModelAndView showRanking() {
		
		ModelAndView mv= new ModelAndView("group/ranking.html");
		return mv;
	}

}
