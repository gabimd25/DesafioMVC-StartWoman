package com.grupo4.gft.servicies;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo4.gft.entities.Activity;
import com.grupo4.gft.repositories.ActivityRepository;

@Service
public class ActivityService {
	@Autowired
	private ActivityRepository activityRepository;

	public void saveActivity(Activity activity) {
		activityRepository.save(activity);
	}

	public void deleteActivity(Long id) {
		activityRepository.deleteById(id);
	}

	public Activity getActivity(Long id) throws Exception {

		Optional<Activity> activity = activityRepository.findById(id);

		if (activity.isEmpty()) 
			throw new Exception("Atividade não encontrado");

		
		return activity.get();
	}

	public List<Activity> listAllActivities() {
		return activityRepository.findAll();
	}

	public List<Activity> findActivityByName(String name) {
		return activityRepository.findByNameContains(name);

	}

	public List<Activity> findActivity(String name) {

		if (name != null)
			return findActivityByName(name);

		return listAllActivities();
	}
}
