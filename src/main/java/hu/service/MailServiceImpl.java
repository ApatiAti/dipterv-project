package hu.service;

import java.io.IOException;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.core.io.Resource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import hu.component.util.EnviromentConstans;
import hu.exception.BasicServiceException;
import hu.service.interfaces.MailService;
import hu.util.EmailType;

@Service
public class MailServiceImpl implements MailService {

	public static final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);
	
	private static final String UTF_8 = "UTF-8";

	@Autowired
	Environment env;
	
	@Autowired
	ApplicationContext applicationContext;
	
	@Autowired
	@Qualifier("mailSender")
	JavaMailSenderImpl mailSender;

	@Autowired
	@Qualifier("mailTemplateEngine")
	SpringTemplateEngine templateEngine;

	/**
	 *  EmailType alapján megadott email sablon kiküldése.
	 * @param to Címzett
	 * @param emailType Email típus azaz hogy melyik sablont kell használni
	 * @param model Sablon feltöltéséhez szükséges adatok
	 * @throws BasicServiceException
	 */
	@Override
	public void sendTemplateEnginedMail(String to, EmailType emailType, Map<String, Object> model) throws BasicServiceException {
		if (isMailServiceDisabled()){
			return;
		}
		try{
		    final Context ctx = new Context();
		    ctx.setVariables(model);

		    final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
		    final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, UTF_8);
		    message.setSubject(emailType.getEmailSubject());
		    
		    boolean isTestServer = env.getProperty(EnviromentConstans.IS_TEST_SERVER, Boolean.class, true);
			if (Boolean.TRUE.equals(isTestServer)){
		    	message.setTo(env.getProperty(EnviromentConstans.MAIL_SERVIC_DEFAULT_EMAIL));
		    } else {
		    	message.setTo(to);
		    }
		    
		    // HTML üzenet létrehozása Thymeleaf-fel
		    final String htmlContent = this.templateEngine.process(emailType.getEmailTemplateName(), ctx);
		    message.setText(htmlContent, true);
	
		    addFooterImage(message);
	
		    this.mailSender.send(mimeMessage);
		    
		} catch (MailException | MessagingException | IOException e) {
			String errorMessage = "Hiba történt az email kiküldése során";
			logger.error(errorMessage, e);
			throw new BasicServiceException(errorMessage);
		}
	}

	private void addFooterImage(final MimeMessageHelper message) throws IOException, MessagingException {
		Resource resource = applicationContext.getResource("classpath:images/hospital-icon-7312.png");
		byte[] footerByteArray = FileUtils.readFileToByteArray(resource.getFile());
		
		addInlineImage(message, "footerImage","image/png", footerByteArray);
	}

	/**
	 *  Kép hozzáadása az email-hez. A html kódban így kel lrá hivatkozni "cid:${imageResourceName}"
	 * @param message
	 * @param imageResourceName
	 * @param imageContentType
	 * @param imageBytes
	 * @throws MessagingException
	 */
	private void addInlineImage(final MimeMessageHelper message, String imageResourceName, String imageContentType,  byte[] imageBytes) throws MessagingException {
		final InputStreamSource imageSource = new ByteArrayResource(imageBytes);
		message.addInline(imageResourceName, imageSource, imageContentType);
	}


	private boolean isMailServiceDisabled() {
		return Boolean.TRUE.equals(env.getProperty(EnviromentConstans.MAIL_SERVIC_DISABLED, Boolean.class, false));
	}
}
