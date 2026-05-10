package exceptionSimulation;
import java.util.ArrayList;
import java.util.Date;

public class Person {
	
	private int id;
	private String name;
	private Date dob;
	private int fatherId;
	private int motherId;
	private char gender;
	private char eyeColor;
	private char hairColor;
	private ArrayList distinguishingMarks;
	
	public Person() {
		super();
	}

		/**
	 * @return Date
	 */
	public Date getDob() {
		return dob;
	}

	/**
	 * @return char
	 */
	public char getEyeColor() {
		return eyeColor;
	}

	/**
	 * @return int
	 */
	public int getFatherId() {
		return fatherId;
	}

	/**
	 * @return char
	 */
	public char getGender() {
		return gender;
	}

	/**
	 * @return char
	 */
	public char getHairColor() {
		return hairColor;
	}

	/**
	 * @return int
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return int
	 */
	public int getMotherId() {
		return motherId;
	}

	/**
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the dob.
	 * @param dob The dob to set
	 */
	public void setDob(Date dob) {
		this.dob = dob;
	}

	/**
	 * Sets the eyeColor.
	 * @param eyeColor The eyeColor to set
	 */
	public void setEyeColor(char eyeColor) {
		this.eyeColor = eyeColor;
	}

	/**
	 * Sets the fatherId.
	 * @param fatherId The fatherId to set
	 */
	public void setFatherId(int fatherId) {
		this.fatherId = fatherId;
	}

	/**
	 * Sets the gender.
	 * @param gender The gender to set
	 */
	public void setGender(char gender) {
		this.gender = gender;
	}

	/**
	 * Sets the hairColor.
	 * @param hairColor The hairColor to set
	 */
	public void setHairColor(char hairColor) {
		this.hairColor = hairColor;
	}

	/**
	 * Sets the id.
	 * @param id The id to set
	 */
	public void setId(String id) throws InvalidIdException{
		int newId = Integer.parseInt(id);
		if(newId >= Integer.MIN_VALUE && newId <= Integer.MAX_VALUE){
			this.id = newId;
		}else{
			throw new InvalidIdException("The id being passed must be between 2^31 -1 and -2^31");
		}
	}

	/**
	 * Sets the motherId.
	 * @param motherId The motherId to set
	 */
	public void setMotherId(int motherId) {
		this.motherId = motherId;
	}

	/**
	 * Sets the name.
	 * @param name The name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return ArrayList
	 */
	public ArrayList getDistinguishingMarks() {
		return distinguishingMarks;
	}

	/**
	 * Sets the distinguishingMarks.
	 * @param distinguishingMarks The distinguishingMarks to set
	 */
	public void setDistinguishingMarks(ArrayList distinguishingMarks) {
		this.distinguishingMarks = distinguishingMarks;
	}

}
