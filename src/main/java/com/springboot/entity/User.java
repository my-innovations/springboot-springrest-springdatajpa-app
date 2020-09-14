package com.springboot.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

/**
 * use below annotations in your
 * project. @Query,@NamedQueries,@NamedNativeQueries,
 *
 */

//@NotNull,@NotEmpty,@NotBlank,@AssertTrue,@Min,@Max,@Positive,@Size,@Digits,@Pattern,@Email,@Past,@Future etc

@Entity
//@Table
@Table(name = "USERS")
//@EntityListeners(AuditingEntityListener.class)
//@JsonIgnoreProperties(value = {"name", "dob"},allowGetters = true)
public class User implements Serializable {

	private static final long serialVersionUID = -7869321226074832724L;

	@Id
	// @GeneratedValue
	@GeneratedValue(strategy = GenerationType.AUTO)
	// OR
	//@GeneratedValue(strategy = GenerationType.SEQUENCE)
	// OR
	
	/* @GeneratedValue(generator = "id_generator",strategy = GenerationType.SEQUENCE)
	 @SequenceGenerator( name = "id_generator", sequenceName = "user_seq", initialValue = 1 ,allocationSize = 1)*/
	private int id;

	// @NotBlank(message="plz provide name") //OK
	@NotEmpty(message = "plz provide your Firstname")
	@Size(max = 50, min = 5, message = "name must be between {min} and {max} characters")
	@Column(name = "firstname", nullable = false, length = 255)
	//@JsonProperty("firstname)")
	private String firstname;

	@NotEmpty(message = "plz provide your Lastname")
	@Size(max = 50, min = 5, message = "name must be between {min} and {max} characters")
	@Column(name = "lastname", nullable = false, length = 255)
	//@JsonProperty("lastname)")
	private String lastname;

	// @NotBlank(message="plz provide email")
	@NotEmpty(message = "{validation.mail.notEmpty}")
	@Size(max = 50, min = 5, message = "email must be between {min} and {max} characters")
	@Email(message = "{user.email.invalid}") // test@test.com
	@Column(unique = true)
	private String email;

	//@Min(value = 5, message = "Age must be greater than 10")
	//@Max(value = 101, message = "Age must be less than 101")
	@PositiveOrZero(message = "Age must be a positive or zero value")
	// @Positive //OK
	//@Transient
	//@JsonIgnore
	private int age;

	@Column(name = "dob", nullable = true, updatable = true)
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
	// @JsonFormat(pattern = "dd/MM/yyyy")
	// @DateTimeFormat(pattern="dd/MM/yyyy")
	// @Past
	@PastOrPresent
	// @Temporal(TemporalType.TIMESTAMP)
	private Date dob;

	@Column(nullable = true, updatable = true)
	@NotEmpty(message = "please provide mobile_no")
	// @Pattern(regexp = "\\d{3}-\\d{3}-\\d{4}", message = "please provide mobile no in xxx-xxx-xxxx format")
	private String mobile_no;

	@Column(nullable = true, updatable = false)
	// @Temporal(TemporalType.TIMESTAMP)
	// @JsonFormat(pattern = "dd/MM/yyyy")
	@CreatedDate
	private Date createdAtDate;

	@Column(nullable = true, updatable = true)
	// @Temporal(TemporalType.TIMESTAMP)
	// @JsonFormat(pattern = "dd/MM/yyyy")
	@LastModifiedDate
	// @Future
	// @FutureOrPresent
	private Date updatedAtDate;

	/**
	 * ###################################################################################################################################################
	 * constructors
	 * ###################################################################################################################################################
	 * 
	 */

	public User() {
	}

	public User(String firstname, int age) {
		this.firstname = firstname;
		this.age = age;
	}

	public User(String firstname, String lastname, int age, String email, Date dob, String mobile_no,
			Date createdAtDate, Date updatedAtDate) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.age = age;
		this.email = email;
		this.dob = dob;
		this.mobile_no = mobile_no;
		this.createdAtDate = createdAtDate;
		this.updatedAtDate = updatedAtDate;
	}

	public User(int id, String firstname, int age, String email, Date dob, String mobile_no, Date createdAtDate,
			Date updatedAtDate) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.age = age;
		this.email = email;
		this.dob = dob;
		this.mobile_no = mobile_no;
		this.createdAtDate = createdAtDate;
		this.updatedAtDate = updatedAtDate;
	}

	/**
	 * ###################################################################################################################################################
	 * getters and setters
	 * ###################################################################################################################################################
	 * 
	 */

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date userDob) {
		this.dob = userDob;
	}

	public String getMobile_no() {
		return mobile_no;
	}

	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}

	public Date getCreatedAtDate() {
		return createdAtDate;
	}

	public void setCreatedAtDate(Date createdAtDate) {
		this.createdAtDate = createdAtDate;
	}

	public Date getUpdatedAtDate() {
		return updatedAtDate;
	}

	public void setUpdatedAtDate(Date updatedAtDate) {
		this.updatedAtDate = updatedAtDate;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@Override
	public String toString() {
		//return return String.format("Item[%d, %s, %s, %s]", id, firstname, lastname, email);
		//OR
		return "User [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email + ", age="
				+ age + ", dob=" + dob + ", mobile_no=" + mobile_no + ", createdAtDate=" + createdAtDate
				+ ", updatedAtDate=" + updatedAtDate + "]";
	}
}
