package hu.service.interfaces.security;

import hu.exception.security.AuthorizationException;
import hu.model.hospital.Appointment;
import hu.model.user.User;

public interface SecurityService {

	/**
	 *  Az éppen bejelentkezett felhasználó felhasználó nevét adja vissza
	 */
	public String getCurrentUserName();
	
	/**
	 *  Az éppen bejelentkezett felhasználót adja vissza
	 */
	public User getCurrentUser();
	
	/**
	 * Fájl feltöltés authorizálása.
	 * 	Beteg csak a saját fájlját tudja letölteni.
	 * 	Orvos minden betegét letudja tölteni.
	 * @param appointemnt
	 * @throws AuthorizationException
	 */
	public void authorizeCurrenctUserToUpload(Appointment appointemnt) throws AuthorizationException;


	public void authorizeOwner(Appointment a) throws AuthorizationException;

	public void authorizeOwnerByUserId(Long patientId) throws AuthorizationException;;
}
