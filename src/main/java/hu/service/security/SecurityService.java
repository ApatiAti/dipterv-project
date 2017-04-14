package hu.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import hu.exception.security.AuthorizationException;
import hu.model.document.DocumentFileAppointment;
import hu.model.user.User;
import hu.repository.security.RoleGroupRepository;
import hu.repository.user.UserRepository;

@Service
public class SecurityService {
/*
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	RoleGroupRepository roleGroupRepository;
	
	@Autowired
	SessionRegistry sessionRegistry;
	*/
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleGroupRepository roleGroupRepository;
	
	public String getCurrentUserName(){
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if (principal instanceof UserDetails) {
			String username = ((UserDetails)principal).getUsername();
		
			return username;
		}
	
		return "";
	}
	
	public User getCurrentUser(){
		return userRepository.findByUsername(getCurrentUserName());
	}

	/**
	 * Fájl letöltés authorizálása.
	 * Beteg csak a saját fájlját tudja letölteni.
	 * Orvos minden betegét letudja tölteni.
	 * @param docFileApp
	 * @throws AuthorizationException
	 */
	public void authorizeCurrentUserToDownload(DocumentFileAppointment docFileApp) throws AuthorizationException {
		String patientUserName = docFileApp.getAppointment().getPatient().getUsername();
		User currentUser = getCurrentUser();
		
		if (!currentUser.getUsername().equals(patientUserName)){
			
			int count = roleGroupRepository.countByUsersIdAndCode(currentUser.getId() , "DOCTOR");
			
			if (count == 0){
				throw new AuthorizationException("Az oldal megtekintéséhez nincs joga!");
			}
		}
	}
	
	
	/**
	 * Grant the given role group to the give user and to the user, who's request is currently invoke this method.
	 * This two user is always the same.
	 * @param user
	 * @param roleGroupCode
	 */
	/*
	@Transactional
	// TODO Transaction propragation-t megoldani
	public void grantRoleGroupToCurrentUser(User user, String roleGroupCode){
		RoleGroup roleGroup = grantRoleGroup(user, roleGroupCode);

		grantRoleGroupToCurrentUser(roleGroup);
	}

	@Transactional
	public void revokeRoleGroupFromCitizen(User user, String roleGroupCode){
		RoleGroup roleGroup = roleGroupRepository.findByCodeWithUsers(roleGroupCode);
		
		if (roleGroup != null){
			roleGroup.getUsers().remove(user);
			roleGroupRepository.save(roleGroup);
		}

		forceLogoutUser(user); 
	}
	
	@Transactional
	public void grantRoleGroupToCitizen(User user, String roleGroupCode) {
		grantRoleGroup(user, roleGroupCode);
		
		forceLogoutUser(user); 		
	}

	private RoleGroup grantRoleGroup(User user, String roleGroupCode) {
		RoleGroup roleGroup = roleGroupRepository.findByCodeWithUsers(roleGroupCode);
		
		if (roleGroup == null){
			roleGroup = roleGroupRepository.findByCode(roleGroupCode);
		}
		
		List<User> users = roleGroup.getUsers();
		if (users == null){
			users = new ArrayList<>();
		}
		
		users.add(user);

		return roleGroupRepository.save(roleGroup);
	}

	// TODO x kitesztelni, hogy ez mit csinál pontosan. 
	private void forceLogoutUser(User user) {
		List<Object> loggedUsers = sessionRegistry.getAllPrincipals();
		for (Object principal : loggedUsers) {
		    if(principal instanceof UserDetails) {
		    	
		    	final String loggedUsername = ((UserDetails) principal).getUsername();

		        if(user.getUsername().equals(loggedUsername)) {
		            System.out.println("user invalidete!");
		        	List<SessionInformation> sessionsInfo = sessionRegistry.getAllSessions(principal, false);
		            if(null != sessionsInfo && sessionsInfo.size() > 0) {
		                for (SessionInformation sessionInformation : sessionsInfo) {
		                    sessionInformation.expireNow();
		                    //sessionRegistry.removeSessionInformation(sessionInformation.getSessionId());
		                    // User is not forced to re-logging
		                }
		            }
//		            break;
		        }
		    }
		}
	}
	
	/**
	 * Grant RoleGroup to the user, who's request is currently invoke this method.
	 * @param roleGroup
	 */
	// TODO 1Springes user-t elkérő srpingsecurits metódust át kell írni hogy azonnal a userrel térjen 
	// vissza és akkor nincs ezzel ennyi szöszmötölés
	/*
	private void grantRoleGroupToCurrentUser(RoleGroup roleGroup) {
		//http://forum.spring.io/forum/spring-projects/security/101786-spring-security-how-to-add-remove-authorities-of-the-user-after-he-logs-in
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){
			List<GrantedAuthority> updatedAuthorities = new ArrayList<GrantedAuthority>(auth.getAuthorities());
			
			for (Role roles : roleGroup.getRoles()) {
				updatedAuthorities.add(new SimpleGrantedAuthority(roles.getCode()));
			}
			
			Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(),auth.getCredentials(),updatedAuthorities);
			SecurityContextHolder.getContext().setAuthentication(newAuth);
		}
	}
	*/
}
