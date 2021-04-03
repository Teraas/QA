package Utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class Util {

	public static String BASE_URL = "http://automation-url.com";

	public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper().
			registerModule(new JavaTimeModule()).
			configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
}
