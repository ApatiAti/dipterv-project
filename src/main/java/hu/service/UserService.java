package hu.service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.model.user.PersonalData;
import hu.model.user.User;
import hu.repository.user.PersonalDataRepository;
import hu.repository.user.UserRepository;

@Service
public class UserService {

	private static final Logger logger = Logger.getLogger(UserService.class);
	
	@Autowired
	PersonalDataRepository personalDataRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Transactional
	public PersonalData updatePersonalDate(PersonalData data, String username){
		User user = userRepository.findByUsername(username);
		

		data.setUser(user);
		
		return personalDataRepository.save(data);
	}


	public PersonalData findPersonalDataByUserId(Long userId) {
		return personalDataRepository.findByUserId(userId);
	}

	/**
	 * Ha personalDataId létezik a DB-ben és ahozzá tartoóz user-nek a username = usernameParam => visszatért personalDataId-hoz tartozó personalDAta-val
	 * amúgy null
	 * 
	 * @param usernameParam
	 * @param personalDataId
	 * @return
	 */
	public PersonalData getEditablePersonalData(String usernameParam, Long personalDataId) {
		PersonalData personalData = personalDataRepository.findOne(personalDataId);
		
		if (personalData == null){
			String message = "Megadott personalDataId nem létezik a DB-ben. Id " + personalDataId.toString();
			logger.error(message);
			throw new EntityNotFoundException(message);
		}
		
		if (usernameParam.equals(personalData.getUser().getUsername())){
			return personalData;
		}
		
		return null;
	}


	public PersonalData findPersonalDataByUsername(String username) {
		return personalDataRepository.findByUser_Username(username);
		
	}
}
