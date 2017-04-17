package hu.web.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.format.Formatter;

import hu.util.MessageConstants;

/*
 * Különböző a felületen megjelenő dátumok formázására 
 * forrás:
 * http://www.thymeleaf.org/doc/tutorials/2.1/thymeleafspring.html
 */
public class CustomDateFormatter implements Formatter<Date> {

	@Autowired
	private MessageSource messageSource;

	public CustomDateFormatter() {
		super();
	}

	public Date parse(final String text, final Locale locale) throws ParseException {
		final SimpleDateFormat dateFormat = createDateFormat(locale);
		return dateFormat.parse(text);
	}

	public String print(final Date object, final Locale locale) {
		final SimpleDateFormat dateFormat = createDateFormat(locale);
		return dateFormat.format(object);
	}

	/* {classpath}/messages_{LOCALE}.properties file alapján formázza a dátumot */
	private SimpleDateFormat createDateFormat(final Locale locale) {
		return createDateFormatStatic(messageSource, locale);
	}
	
	public static SimpleDateFormat createDateFormatStatic(final MessageSource messageSource, final Locale locale) {
		final String format = messageSource.getMessage(MessageConstants.DATE_FORMAT, null, locale);
		final SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		dateFormat.setLenient(false);
		return dateFormat;
	}


}