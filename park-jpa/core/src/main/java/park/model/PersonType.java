package park.model;

/**
 * Enum for Person Type.
 * 
 * @author
 * @version
 * @since
 */
public enum PersonType {
	NATURAL_PERSON("NP"), 
	LEGAL_ENTITY("LE");
	
	private final String type;

	public String getType() {return type;}
	PersonType(String type) {this.type = type;}
}