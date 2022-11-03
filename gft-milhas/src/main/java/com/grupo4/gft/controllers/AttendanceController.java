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

import com.grupo4.gft.entities.GroupEvent;
import com.grupo4.gft.entities.Attendance;
import com.grupo4.gft.entities.Event;
import com.grupo4.gft.servicies.AttendanceService;
import com.grupo4.gft.servicies.EventService;


@Controller
@RequestMapping("attendance")
public class AttendanceController {

	@Autowired
	private AttendanceService attendanceService;
	
	@Autowired
	private EventService eventService;

	
	@RequestMapping(path="edit")
	public ModelAndView attendanceEvent(@RequestParam (required=false) Long id, @RequestParam (required=false) Long idEvent ) {
		
		ModelAndView mv= new ModelAndView("attendance/formAttendance.html");
		
		Attendance attendance;
		List<GroupEvent> groupsEvent= new ArrayList<>();
		Event event;
		
		if(id == null) {
			attendance = new Attendance();
			try {
				event = eventService.getEvent(idEvent);
				attendance.setEvent(event);
				groupsEvent = eventService.getEvent(idEvent).getGroups();
			} catch (Exception e1) {
				mv.addObject("message", e1.getMessage());
			}
		}else {
			
			try {
				attendance = attendanceService.getAttendance(id);
				groupsEvent = eventService.getEvent(idEvent).getGroups();
			}catch(Exception e){
				attendance = new Attendance();
				mv.addObject("message", e.getMessage());
			}
		}
		mv.addObject("attendance",attendance);
		mv.addObject("groups", groupsEvent);
		if(attendance.getGuestPresent()==null) {
			mv.addObject("listPresent", "");
		}
		else {
			mv.addObject("listPresent", attendance.getGuestPresent());
		}
		if(attendance.getGuestLate()==null) {
			mv.addObject("listLate", "");
		}
		else {
			mv.addObject("listLate", attendance.getGuestLate());
		}
		
		mv.addObject("idEvent", idEvent);
		
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST, path="edit")
	public ModelAndView saveAttendance(@Valid Attendance attendance, BindingResult bindingResult) {
		
		ModelAndView mv = new ModelAndView("attendance/formAttendance.html");
		
		if(bindingResult.hasErrors()) {
			mv.addObject("attendance", attendance);
			return mv;
		}
		
		try {
			
			attendanceService.saveAttendance(attendance);
			eventService.updateScore(attendance.getEvent());
			mv = new ModelAndView("redirect:/event/edit?id="+attendance.getEvent().getId());
		}catch(Exception e) {
			mv.addObject("message", e.getMessage());			
		}
		if(attendance.getId() != null) {
			try {
				attendance.setDateAttendance(attendanceService.getAttendance(attendance.getId()).getDateAttendance());
			} catch (Exception e) {
				mv.addObject("message", e.getMessage());	
			}
		}
		mv.addObject(attendance);
		return mv;
		
	}
	
	
	@RequestMapping("/delete")
	public ModelAndView deleteAttendance(@RequestParam Long id,@RequestParam Long idEvent, RedirectAttributes redirectAttributes) {
		
		try {
			Event event=eventService.getEvent(idEvent);
			attendanceService.deleteAttendance(id);
			eventService.updateScore(event);
			redirectAttributes.addFlashAttribute("messagem", "Grupo excluido com sucesso");	
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("messagem", "Erro ao exluir grupo " +e.getMessage());
		}
		
		ModelAndView mv= new ModelAndView("redirect:/event/edit?id="+idEvent);
		return mv;
		
	}
}
