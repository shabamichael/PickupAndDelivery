/**
 * 
 */
package com.pick.up.delivery.customer;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * @author Michael Shaba
 *
 */
@Entity
@Table(
		name = "Customer",
		uniqueConstraints = {
		@UniqueConstraint(name="customer_email_unique", columnNames="email")
})
public class Customer implements Serializable  {
	@Id
	@SequenceGenerator(
			name = "customer_sequence",
			initialValue = 1,
			sequenceName = "customer_sequence",
			allocationSize = 1
			)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "customer_sequence")
	@Column(name="Id",nullable = false)
	private Long id;
	
	@Column(name = "businessName", nullable = false, columnDefinition = "TEXT" )
	private String businessName;
	
	@Column(name = "email", nullable = false, unique =true,columnDefinition = "TEXT" )
	private String email;
	
	@Column(name = "username", nullable = false, unique =true,columnDefinition = "TEXT" )
	private String username;
	
	@Column(name = "address", nullable = false,columnDefinition = "TEXT" )
	private String address;
	
	@Column(name = "telephone", nullable = false,columnDefinition = "TEXT" )
	private String telephone;
	private String imageUrl;
	
	@Column(updatable = false,unique = true, nullable = false)
	private String customerCode;
	
	private Boolean locked = false;
	private Boolean enabled = false;
	
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Customer(Long id, String businessName, String email, String username, String address, String telephone,
			String imageUrl, String customerCode) {
		super();
		this.id = id;
		this.businessName = businessName;
		this.email = email;
		this.username = username;
		this.address = address;
		this.telephone = telephone;
		this.imageUrl = imageUrl;
		this.customerCode = customerCode;
	}



	public Customer(String businessName, String email, String username, String address, String telephone,
			String imageUrl, String customerCode) {
		super();
		this.businessName = businessName;
		this.email = email;
		this.username = username;
		this.address = address;
		this.telephone = telephone;
		this.imageUrl = imageUrl;
		this.customerCode = customerCode;
	}



	public String getImageUrl() {
		return imageUrl;
	}



	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}



	public String getCustomerCode() {
		return customerCode;
	}



	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Boolean getLocked() {
		return locked;
	}

	public void setLocked(Boolean locked) {
		this.locked = locked;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", businessName=" + businessName + ", email=" + email + ", username=" + username
				+ ", address=" + address + ", telephone=" + telephone + ", imageUrl=" + imageUrl + ", customerCode="
				+ customerCode + ", locked=" + locked + ", enabled=" + enabled + "]";
	}
	
	
	
	

}
