package com.grupo4.gft.servicies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo4.gft.repositories.GroupRepository;

@Service
public class GroupService {
	
	@Autowired
	private GroupRepository groupRepository;

}
