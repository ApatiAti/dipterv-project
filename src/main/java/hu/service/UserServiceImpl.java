package hu.service;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.exception.BasicServiceException;
import hu.exception.security.AuthorizationException;
import hu.model.user.PersonalData;
import hu.model.user.User;
import hu.repository.user.PersonalDataRepository;
import hu.repository.user.UserRepository;
import hu.service.interfaces.UserService;

@Service
public class UserServiceImpl implements UserService{

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	PersonalDataRepository personalDataRepository;
	
	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional
	public PersonalData updatePersonalDate(PersonalData data, String username){
		User user = userRepository.findByUsername(username);
		

		data.setUser(user);
		
		return personalDataRepository.save(data);
	}

	@Override
	public PersonalData findPersonalDataByUserId(Long userId) {
		return personalDataRepository.findByUserId(userId);
	}

	@Override
	public PersonalData getEditablePersonalData(String usernameParam, Long personalDataId) throws AuthorizationException, BasicServiceException {
		PersonalData personalData = personalDataRepository.findOne(personalDataId);
		
		if (personalData == null){
			String message = "Megadott personalDataId nem létezik a DB-ben. Id " + personalDataId.toString();
			logger.error(message);
			throw new BasicServiceException(message);
		}
	
		if (usernameParam.equals(personalData.getUser().getUsername())){
			return personalData;
		} else {
			throw new AuthorizationException("Csak a saját adataidanak a módosításához van engedélye.");
		}
		
	}

	@Override
	public PersonalData findPersonalDataByUsername(String username) {
		return personalDataRepository.findByUser_Username(username);
		
	}
}
