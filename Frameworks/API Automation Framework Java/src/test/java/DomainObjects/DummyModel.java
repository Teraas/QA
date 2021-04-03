package DomainObjects;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonProcessingException;

import static Utilities.Util.OBJECT_MAPPER;

/**
 * Implement a dummy object class using builder pattern
 */
public class DummyModel {

	public static final String ID =UUID.randomUUID().toString();
	@JsonValue
	private Map<String, Object> fields;
	@JsonCreator
	public DummyModel(Map<String, Object> fields) {
		this.fields = fields;
	}

	private DummyModel() {
	}

	public Map<String, Object> getFields() {
		return fields;
	}

	/**
	 * Serialize a domain object to be sent over http using an API
	 * @return
	 */
	public String toString() {
		try {
			return OBJECT_MAPPER.writer().withDefaultPrettyPrinter().writeValueAsString(this);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		public static final String ClassAttributeName = "name";
		public static  String Name = "dummy";
		public static final String IDAttributeName = "id";

		private Map<String, Object> fields = new HashMap<>();

		public Builder() {
			withDefaultAttributes();
		}

		public Builder withName(String Name) {
			this.Name = Name;
			withDefaultAttributes();
			return this;
		}

		public Builder withDefaultAttributes() {
			fields.put(IDAttributeName, ID);
			fields.put(ClassAttributeName, Name);
			return this;
		}


		public DummyModel build() {
			DummyModel dummy = new DummyModel();
			dummy.fields = Collections.unmodifiableMap(new HashMap<>(fields));
			return dummy;
		}
	}
}