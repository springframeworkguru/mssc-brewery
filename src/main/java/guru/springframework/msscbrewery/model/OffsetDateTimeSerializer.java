package guru.springframework.msscbrewery.model;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class OffsetDateTimeSerializer extends StdSerializer<OffsetDateTime> {

	private static final long serialVersionUID = -4129252753674146887L;

	protected OffsetDateTimeSerializer() {
		super(OffsetDateTime.class);
	}

	@Override
	public void serialize(OffsetDateTime value, JsonGenerator gen,
			SerializerProvider provider) throws IOException {
		gen.writeString(value.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
	}

}
