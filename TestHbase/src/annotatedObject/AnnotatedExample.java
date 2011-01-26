package annotatedObject;

import org.apache.hadoop.hbase.hbql.client.Column;
import org.apache.hadoop.hbase.hbql.client.Mapping;

/*@authors Mohamed MEDARHRI
 * 
 */

@Mapping(name = "demo2")

public class AnnotatedExample {
	
	    @Column
	    public String keyval;

	    @Column
	    public String val1;

	    @Column
	    public int val2;

	    @Column
	    public String val3;
	}


