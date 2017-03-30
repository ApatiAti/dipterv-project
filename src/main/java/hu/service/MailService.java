package hu.service;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import hu.exception.BasicServiceException;
import hu.util.EmailType;

@Service
public class MailService {

	public static final Logger logger = Logger.getLogger(MailService.class);
	
	private static final String UTF_8 = "UTF-8";

	@Autowired
	@Qualifier("mailSender")
	JavaMailSenderImpl mailSender;

	@Autowired
	@Qualifier("mailTemplateEngine")
	SpringTemplateEngine templateEngine;
	
	@Deprecated
	public void sendMail(String to, String subject, String msg) {
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setTo(to);
		message.setSubject(subject);
		message.setText(msg);
		
		mailSender.send(message);
	}
	
	@Deprecated
	public void sendMimeMessageMail(String to, String subject, String msg) throws MailException, MessagingException {
		final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
		final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, UTF_8);
		
		message.setFrom(new InternetAddress("sender@example.com"));
		message.setTo(new InternetAddress(to));
		message.setSubject(subject);
		
		message.setText(msg, true);
				
		mailSender.send(mimeMessage);
	}

	
	public void sendTemplateEnginedMail(String to, EmailType emailType, Map<String, Object> model) throws BasicServiceException {
		try{
			// Prepare the evaluation context
		    final Context ctx = new Context();
		    for (Entry<String, Object> entry : model.entrySet()){
		    	ctx.setVariable(entry.getKey(), entry.getValue());
		    }
		    
		    
		    // Prepare message using a Spring helper
		    final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
		    final MimeMessageHelper message =
		        new MimeMessageHelper(mimeMessage, true, UTF_8); // true = multipart
		    message.setSubject(emailType.getEmailSubject());
		    message.setFrom("thymeleaf@example.com");
		    message.setTo("tntiti@hotmail.com");
	//	    message.setTo(to);
		    
		    // Create the HTML body using Thymeleaf
		    final String htmlContent = this.templateEngine.process(emailType.getEmailTemplateName(), ctx);
		    message.setText(htmlContent, true); // true = isHtml
	
//		    addInlineImage(message, "Valami kép","image/jpeg", new Byte[]{});
	
		    // Send mail
		    this.mailSender.send(mimeMessage);
		    
		} catch (MailException | MessagingException e) {
			String errorMessage = "Hiba történt az email kiküldése során";
			logger.error(errorMessage);
			throw new BasicServiceException(errorMessage);
		}
	}

	public void addInlineImage(final MimeMessageHelper message, String imageResourceName, String imageContentType,  byte[] imageBytes) throws MessagingException {
		// Add the inline image, referenced from the HTML code as "cid:${imageResourceName}"
		final InputStreamSource imageSource = new ByteArrayResource(imageBytes);
		message.addInline(imageResourceName, imageSource, imageContentType);
	}

	@Deprecated
	public void sendTemplateEngineMailTest(String to, String subject, String msg) throws MailException, MessagingException {
		// Prepare the evaluation context
	    final Context ctx = new Context();
	    ctx.setVariable("name", "tesztasdfgjkléoiuztrew");
	    ctx.setVariable("subscriptionDate", new Date());
	    ctx.setVariable("hobbies", Arrays.asList("Cinema", "Sports", "Music"));

	    // Prepare message using a Spring helper
	    final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
	    final MimeMessageHelper message =
	        new MimeMessageHelper(mimeMessage, true, UTF_8); // true = multipart
	    message.setSubject(subject);
	    message.setFrom("thymeleaf@example.com");
	    message.setTo(to);
	    
	    // Create the HTML body using Thymeleaf
	    final String htmlContent = this.templateEngine.process("NewFile", ctx);
	    message.setText(htmlContent, true); // true = isHtml

	    // Add the inline image, referenced from the HTML code as "cid:${imageResourceName}"
//	    final InputStreamSource imageSource = new ByteArrayResource(imageBytes);
//	    message.addInline(imageResourceName, imageSource, imageContentType);

	    // Send mail
	    this.mailSender.send(mimeMessage);
	}
	
}
