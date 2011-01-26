/* Une classe pour tester les differentes technologies  a  utiliser pour le projet 3A Hbase*/

package datanucleusTutorial.core;

import javax.jdo.annotations.*;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
@Inheritance(strategy=InheritanceStrategy.SUPERCLASS_TABLE)
public class Book extends Product {



public Book(String name, String description, double price, String author, String isbn, String publisher ) {
		super(name, description, price);
	}

@Persistent
protected String author = null;

@Persistent
protected String isbn = null;

@Persistent
protected String publisher = null;



public String getAuthor() {
	return author;
}

public void setAuthor(String author) {
	this.author = author;
}

public String getIsbn() {
	return isbn;
}

public void setIsbn(String isbn) {
	this.isbn = isbn;
}

public String getPublisher() {
	return publisher;
}

public void setPublisher(String publisher) {
	this.publisher = publisher;
}

}