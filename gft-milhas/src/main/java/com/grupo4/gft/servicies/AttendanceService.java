package com.grupo4.gft.servicies;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo4.gft.entities.Attendance;
import com.grupo4.gft.entities.Event;
import com.grupo4.gft.repositories.AttendanceRepository;

@Service
public class AttendanceService {

	@Autowired
	private AttendanceRepository attendanceRepository;
	
	public void saveAttendance(Attendance attendance) throws Exception {
		Event event = attendance.getEvent();
		
		if(attendance.getDateAttendance().before(event.getStartDate()) || attendance.getDateAttendance().after(event.getEndDate()))
			throw new Exception("Presença não pode ser salva");
		attendanceRepository.save(attendance);
	}
	
	public void saveAttendances(List<Attendance> attendances) {
		attendanceRepository.saveAll(attendances);
	}

	public void deleteAttendance(Long id) {
		attendanceRepository.deleteById(id);
	}

	public Attendance getAttendance(Long id) throws Exception {

		Optional<Attendance> attendance = attendanceRepository.findById(id);

		if (attendance.isEmpty()) 
			throw new Exception("Presença não encontrado");

		return attendance.get();
	}

	public List<Attendance> listAllAttendance() {
		return attendanceRepository.findAll();
	}
	
	public List<Attendance> findAttendance(String name) {

		return listAllAttendance();
	}

}
