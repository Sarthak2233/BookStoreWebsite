package com.bookstore.entity;
// Generated Dec 6, 2020, 5:28:21 PM by Hibernate Tools 5.2.12.Final

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * Customer generated by hbm2java
 */
@Entity
@Table(name = "customer", catalog = "bookstoredb", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@NamedQueries({
	@NamedQuery(name="Customer.findAll", query="SELECT c FROM Customer c ORDER BY c.registerDate DESC"),
	@NamedQuery(name="Customer.findByEmail",query="Select c  from Customer c  where c.email= :email"),
	@NamedQuery(name="Customer.countAll",query="SELECT COUNT(c.email) FROM Customer c"),
	@NamedQuery(name="Customer.checkLogin", query="SELECT c  FROM Customer c Where c.email=:email AND  c.password =:password")
	})
public class Customer implements java.io.Serializable {

	private int customerId;
	private String email;
	private String fullname;
	private String address;
	private String city;
	private String country;
	private String phone;
	private String zipcode;
	private String password;
	private Date registerDate;
	private Set reviews = new HashSet(0);
	private Set bookOrders = new HashSet(0);

	public Customer() {
	}

	public Customer(int customerId, String email, String fullname, String address, String city, String country,
			String phone, String zipcode, String password, Date registerDate) {
		this.customerId = customerId;
		this.email = email;
		this.fullname = fullname;
		this.address = address;
		this.city = city;
		this.country = country;
		this.phone = phone;
		this.zipcode = zipcode;
		this.password = password;
		this.registerDate = registerDate;
	}

	public Customer(int customerId, String email, String fullname, String address, String city, String country,
			String phone, String zipcode, String password, Date registerDate, Set reviews, Set bookOrders) {
		this.customerId = customerId;
		this.email = email;
		this.fullname = fullname;
		this.address = address;
		this.city = city;
		this.country = country;
		this.phone = phone;
		this.zipcode = zipcode;
		this.password = password;
		this.registerDate = registerDate;
		this.reviews = reviews;
		this.bookOrders = bookOrders;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "customer_id", unique = true, nullable = false)
	public int getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	@Column(name = "email", unique = true, nullable = false, length = 64)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "fullname", nullable = false, length = 30)
	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	@Column(name = "address", nullable = false, length = 128)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "city", nullable = false, length = 32)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "country", nullable = false, length = 64)
	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Column(name = "phone", nullable = false, length = 15)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "zipcode", nullable = false, length = 24)
	public String getZipcode() {
		return this.zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	@Column(name = "password", nullable = false, length = 16)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "register_date", nullable = false, length = 19)
	public Date getRegisterDate() {
		return this.registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	public Set<Review> getReviews() {
		return this.reviews;
	}

	public void setReviews(Set reviews) {
		this.reviews = reviews;
	}


	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	public Set<BookOrder> getBookOrders() {
		return this.bookOrders;
	}

	public void setBookOrders(Set bookOrders) {
		this.bookOrders = bookOrders;
	}

}
