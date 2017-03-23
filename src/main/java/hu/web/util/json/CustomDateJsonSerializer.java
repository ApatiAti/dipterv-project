package hu.web.util.json;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import hu.web.util.CustomDateFormatter;

/*
 * Custom Json Serializer for Date.
 * Can be used in @JsonSerialize( using = CustomDateJsonSerializer.class)
 */
public class CustomDateJsonSerializer extends JsonSerializer<Date> {

	@Autowired
	MessageSource messageSource;
	
	@Override
	public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider)
			throws IOException, JsonProcessingException {
		
		SimpleDateFormat dateFormat = CustomDateFormatter.createDateFormatStatic(messageSource, provider.getLocale());
		String dateString = dateFormat.format(value);
		
		jgen.writeString(dateString);
		
	}}