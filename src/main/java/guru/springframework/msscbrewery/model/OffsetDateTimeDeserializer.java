package guru.springframework.msscbrewery.model;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class OffsetDateTimeDeserializer
		extends
			StdDeserializer<OffsetDateTime> {

	private static final long serialVersionUID = 6243674307572354998L;

	protected OffsetDateTimeDeserializer() {
		super(OffsetDateTime.class);
	}

	@Override
	public OffsetDateTime deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		return OffsetDateTime.parse(p.readValueAs(String.class),
				DateTimeFormatter.ISO_OFFSET_DATE_TIME);
	}

}
