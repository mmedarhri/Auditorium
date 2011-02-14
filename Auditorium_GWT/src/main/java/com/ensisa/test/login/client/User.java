/* Une classe pour tester les differentes technologies  a  utiliser pour le projet 3A Hbase*/
package com.ensisa.test.login.client;


import java.io.Serializable;

import javax.jdo.annotations.*;

import com.google.gwt.user.client.rpc.IsSerializable;



/**
*
* @author Mohamed MEDARHRI
*/


@PersistenceCapable(identityType = IdentityType.APPLICATION,detachable="true")
@Inheritance(strategy=InheritanceStrategy.UNSPECIFIED)
@Discriminator(strategy=DiscriminatorStrategy.NONE)
public class User implements IsSerializable {




@Persistent(primaryKey = "true", valueStrategy = IdGeneratorStrategy.UNSPECIFIED)
protected String login = null;

@Persistent
protected String password = null;

@Persistent
protected String role = null;


@Persistent
protected String name = null;
@Persistent
protected String firstname = null;
@Persistent
protected String email = null;
@Persistent
protected String address = null;
@Persistent
protected int age = 0;

public final static String AUDITOR = "auditeur";
public final static String SCHOOL_DIRECTION = "admin";
public final static String SYSADMIN = "super-admin";

protected static String roles[]={AUDITOR,SCHOOL_DIRECTION,SYSADMIN};

public User() {
	super();
}


public User(String login, String password,String role) {
	this.login=login;
	this.password=password;
	this.role=role;
}


public int getAge() {
	return age;
}



public void setAge(String age) {
	this.age = Integer.parseInt(age);
}



public String getName() {
	return name;
}



public void setName(String name) {
	this.name = name;
}



public String getFirstname() {
	return firstname;
}



public void setFirstname(String firstname) {
	this.firstname = firstname;
}



public String getEmail() {
	return email;
}



public void setEmail(String email) {
	this.email = email;
}



public String getAddress() {
	return address;
}



public void setAddress(String address) {
	this.address = address;
}


public static String[] getRoles() {
	return roles;
}



public static void setRoles(String[] roles) {
	User.roles = roles;
}



public String getLogin() {
	return login;
}



public void setLogin(String login) {
	this.login = login;
}



public String getPassword() {
	return password;
}



public void setPassword(String password) {
	this.password = password;
}



public String getRole() {
	return role;
}


public void setRole(String role) {
	this.role = role;
}
@Override
public String toString() {
	return "User [login=" + this.getLogin() + ", password=" + this.getPassword() + ", role="
			+ this.getRole() + ", name=" + name + ", firstname=" + firstname
			+ ", email=" + email + ", address=" + address + ", age=" + age
			+ "]";
}

}