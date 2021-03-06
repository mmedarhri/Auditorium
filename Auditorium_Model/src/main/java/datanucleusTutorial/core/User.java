/* Une classe pour tester les differentes technologies  a  utiliser pour le projet 3A Hbase*/
package datanucleusTutorial.core;

import javax.jdo.annotations.*;



/**
*
* @author Mohamed MEDARHRI
*/


@PersistenceCapable(identityType = IdentityType.APPLICATION)
@Inheritance(strategy=InheritanceStrategy.UNSPECIFIED)
@Discriminator(strategy=DiscriminatorStrategy.NONE)
public class User {

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

public final static String STUDENT = "etudiant";
public final static String AUDITOR = "auditeur";
public final static String SCHOOL_DIRECTION = "admin";
public final static String SYSADMIN = "super-admin";

protected static String roles[]={STUDENT,AUDITOR,SCHOOL_DIRECTION,SYSADMIN};


public User(String login, String password,String role) {
    super();
	this.login=login;
	this.password=password;
	this.role=role;
}


public int getAge() {
	return age;
}



public void setAge(int age) {
	this.age = age;
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

public void setAffiliation(String name){
	this.name=name;
}

public String getAffiliation()
{
	return name;
	
}

}