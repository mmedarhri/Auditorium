/* Une classe pour tester les differentes technologies  a  utiliser pour le projet 3A Hbase*/
package datanucleusTutorial.core;

import javax.jdo.annotations.*;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)
@Discriminator(strategy=DiscriminatorStrategy.CLASS_NAME)
public class Product {

@Persistent(primaryKey = "true", valueStrategy = IdGeneratorStrategy.UUIDHEX)
protected String id;

@Persistent
protected String name = null;

@Persistent
protected String description = null;

@Persistent
protected double price = 0.0;

public Product(String name, String description,double price) {
	// TODO Auto-generated constructor stub
	this.name=name;
	this.description=description;
	this.price=price;
}

public String getId() {
	return id;
}

public void setId(String id) {
	this.id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}

public double getPrice() {
	return price;
}

public void setPrice(double price) {
	this.price = price;
}
}