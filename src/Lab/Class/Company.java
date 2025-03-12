package Lab.Class;

import java.io.Serializable;
import java.util.Date;

public class Company implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name;
	private String email;
	private String phone;
	private String address;
	private String postcode;
	private String country;
	private Date createdDate;

	public Company(String name, String email, String phone, String address, String postcode, String country) {
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.postcode = postcode;
		this.country = country;
		this.createdDate = new Date();
	}

	// Getter methods

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	public String getAddress() {
		return address;
	}

	public String getPostcode() {
		return postcode;
	}

	public String getCountry() {
		return country;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	@Override
	public String toString() {
		return String.format(
				"Company {name = %s, email = %s, phone = %s, address = %s, postcode = %s, country = %s, createdDate = %s}",
				name, email, phone, address, postcode, country, createdDate);
	}
}
