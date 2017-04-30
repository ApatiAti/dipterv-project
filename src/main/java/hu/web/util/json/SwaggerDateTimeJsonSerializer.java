package hu.web.util.json;

import java.io.IOException;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/*
 * Custom Json Serializer for Date.
 * Can be used in @JsonSerialize( using = CustomDateJsonSerializer.class)
 */
public class SwaggerDateTimeJsonSerializer extends JsonSerializer<DateTime> {
	
	@Override
	public void serialize(DateTime value, JsonGenerator jgen, SerializerProvider provider)
			throws IOException, JsonProcessingException {
		
		if (value == null){
			return;
		}
		
		DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
		String dateString = dtf.print(value);
		
		jgen.writeString(dateString);
		
	}}