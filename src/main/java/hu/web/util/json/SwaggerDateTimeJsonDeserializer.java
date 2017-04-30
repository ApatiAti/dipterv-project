package hu.web.util.json;

import java.io.IOException;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/*
 * Custom Json Serializer for Date.
 * Can be used in @JsonSerialize( using = CustomDateJsonSerializer.class)
 */
public class SwaggerDateTimeJsonDeserializer extends JsonDeserializer<DateTime> {


	@Override
	public DateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		String valueAsString = p.getValueAsString();
		if (StringUtils.isEmpty(valueAsString)){
			return null;
		}
		DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		DateTime dateTime = dtf.parseDateTime(valueAsString);
		return dateTime;
	}}