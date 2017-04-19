package hu.service.interfaces;

import hu.exception.BasicServiceException;
import hu.exception.security.AuthorizationException;
import hu.model.user.PersonalData;

public interface UserService {

	public PersonalData updatePersonalDate(PersonalData data, String username);

	public PersonalData findPersonalDataByUserId(Long userId);
	/**
	 * Ha personalDataId létezik a DB-ben és ahozzá tartoóz user-nek a username = usernameParam => visszatért personalDataId-hoz tartozó personalDAta-val
	 * amúgy null
	 * 
	 * @param usernameParam
	 * @param personalDataId
	 * @return
	 * @throws BasicServiceException 
	 */
	public PersonalData getEditablePersonalData(String usernameParam, Long personalDataId) throws AuthorizationException, BasicServiceException;

	public PersonalData findPersonalDataByUsername(String username);
}
