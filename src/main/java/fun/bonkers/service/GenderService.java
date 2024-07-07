package fun.bonkers.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fun.bonkers.model.Gender;
import fun.bonkers.repository.GenderRepository;

@Service
public class GenderService {
	
	@Autowired
	GenderRepository genderRepository;
	
	public List<Gender> getAllGender() {
		return genderRepository.findAll();
	}
	
	public void deleteById(int id) {
		genderRepository.deleteById(id);
	}
	
	public Optional<Gender> findGenderById(int id) {
		return genderRepository.findById(id);
	}
	
	public Optional<Gender> findGenderByName(String name) {
		return genderRepository.findGenderByName(name);
	}
	
	public void addGender(Gender gender) {
		genderRepository.save(gender);
	}
}
