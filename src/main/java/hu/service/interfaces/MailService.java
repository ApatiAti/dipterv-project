package hu.service.interfaces;

import java.util.Map;

import hu.exception.BasicServiceException;
import hu.util.EmailType;

public interface MailService {

	void sendTemplateEnginedMail(String to, EmailType emailType, Map<String, Object> model) throws BasicServiceException;
}
