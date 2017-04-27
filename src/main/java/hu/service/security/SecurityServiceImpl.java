package hu.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import hu.exception.security.AuthorizationException;
import hu.model.hospital.Appointment;
import hu.model.user.User;
import hu.repository.security.RoleGroupRepository;
import hu.repository.user.UserRepository;
import hu.service.interfaces.security.SecurityService;

@Service
public class SecurityServiceImpl implements SecurityService{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleGroupRepository roleGroupRepository;
	
	@Override
	public String getCurrentUserName(){
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if (principal instanceof UserDetails) {
			String username = ((UserDetails)principal).getUsername();
		
			return username;
		}
	
		return "";
	}
	
	@Override
	public User getCurrentUser(){
		return userRepository.findByUsername(getCurrentUserName());
	}

	@Override
	public void authorizeCurrenctUserToUpload(Appointment appointemnt) throws AuthorizationException{
		String patientUserName = appointemnt.getPatient().getUsername();
		
		authorizeOwnerOrDoctor(patientUserName);
	}
	
	@Override
	public void authorizeOwner(Appointment appointment) throws AuthorizationException {
		if (!getCurrentUser().equals(appointment.getPatient())){
			throw new AuthorizationException("A művelet elvégzéséhez nincs joga!");
		}
	}


	@Override
	public void authorizeOwnerByUserId(Long patientId) throws AuthorizationException {
		if (!getCurrentUser().getId().equals(patientId)){
			throw new AuthorizationException("A művelet elvégzéséhez nincs joga!");
		}
		
	}

	private void authorizeOwnerOrDoctor(String patientUserName) throws AuthorizationException {
		User currentUser = getCurrentUser();
		if (!currentUser.getUsername().equals(patientUserName)){
			
			int count = roleGroupRepository.countByUsersIdAndCode(currentUser.getId() , "DOCTOR");
			
			if (count == 0){
				throw new AuthorizationException("Az oldal megtekintéséhez nincs joga!");
			}
		}
	}
}
