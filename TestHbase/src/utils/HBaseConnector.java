/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author mmedarhri
 */
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.ClusterStatus;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.io.Cell;
import org.apache.hadoop.hbase.io.RowResult;
import org.apache.hadoop.hbase.util.Bytes;

/*@authors Mohamed MEDARHRI
 * 
 */
public class HBaseConnector {

    private HTable htable;
    private HBaseAdmin hba;
	private Configuration cf;
	private HBaseConfiguration hc;
	private HTableDescriptor ht ;
  
    private Map map;

    public HBaseConnector() {
        init();
           }

    private void init() {
        map = new HashMap();
        cf = new Configuration();
		hc = new HBaseConfiguration(cf);
    }
    
    
    public String getlistTableNames(HTableDescriptor[] hld)
    {
    	StringBuffer buff = new StringBuffer();
    	buff.append(hld.length+" Tables\n");
    	for(int i=0;i<hld.length;i++)
		{
			buff.append(hld[i].getNameAsString()+"\n");
		}
    		 return buff.toString();
        }
    
    public ClusterStatus getStatus()
    {
    	try{
    	 hba = new HBaseAdmin( hc );
    	return hba.getClusterStatus();
    	 
    	}catch(Exception e)
    	{
    		System.out.println("HBaseConnector getStatus() "+e.getMessage());
    	}
    		return null;
    }
    
    public String getCelluleValue(HTable ht,String rowkey,String cf,String family)
	{
		try{
		Cell cell = ht.get(rowkey,cf+":"+family);
		if (cell != null) {
	         
	        return Bytes.toString(cell.getValue());
		}
		}catch(Exception e)
		{
			System.out.println("CreateHbaseTable getCelluleValue() "+e.getMessage());
		
		}
		return "";
	}
	
    public String getHbaseVersion(ClusterStatus cstatus)
    {
    	return cstatus.getHBaseVersion();
    }
    
    public double getAverageLoad(ClusterStatus cstatus)
    {
    	return cstatus.getAverageLoad();
    }
    
    public String getCellrowValue(HTable ht,String rowkey,String cf)
    {   
    	try{
    		Cell cell = ht.get(rowkey,cf);
    		if (cell != null) {
    	         
    	        return Bytes.toString(cell.getValue());
    		}
    		else System.out.println("cell null");
    			
    		}catch(Exception e)
    		{
    			System.out.println("HBaseConnector getCellrowValue() "+e.getMessage());
    		
    		}
    		return "";
    }
    
    public int getDeadServers(ClusterStatus cstatus)
    {
    	return cstatus.getDeadServers();
    }
    
    public HTableDescriptor[] listTables()
    {
    	try{
		 hba = new HBaseAdmin( hc );
	
		 return hba.listTables();
	}catch(Exception e)
		{
			System.out.println("HBaseConnector deleteTable() "+e.getMessage());
		}
		return null;
	}
	
	public void deleteTable(String name)
	{
		try{
		 hba = new HBaseAdmin( hc );
		 hba.disableTable(name);
		 hba.deleteTable(name);
	}catch(Exception e)
		{
			System.out.println("HBaseConnector deleteTable() "+e.getMessage());
		}
	}
	
	
 
    public HTable getHTable(String name) {
        try {
            htable = new HTable(new HBaseConfiguration(),name);
            //System.out.println("Table :"+name);
            return htable;
        } catch (Exception e) {
            System.out.println("HBaseConnetcor + getHTable() method " + e.getMessage());
            return htable;
        }
    }

    public Map retrieveColumn(HTable table,String keyrow) {

        try {

            RowResult result = table.getRow(keyrow);
             
            if(result!=null){

            for (byte[] column : result.keySet()) {
                map.put(new String(column), new String(result.get(column).getValue()));
            }

            return map;
            }
            else System.out.println("result is null");
            return null;
        } catch (Exception e) {
            System.out.println("HBaseConnector method retrieveColumn() " + e.getMessage());
            System.out.println("Utilisateur inconnu...");
            return null;
        }
    }
    
    public 	String getCellValue(Map map,String columnname)
    {
    	try {
    		 //Get Map in Set interface to get key and value
            Set s=map.entrySet();

            //Move next key and value of Map by iterator
            Iterator it=s.iterator();
            
    		/*while(it.hasNext())
            {
                // key=value separator this by Map.Entry to get key and value
                Map.Entry m =(Map.Entry)it.next();
*/
                // getKey is used to get key of Map
                //int key=(Integer)m.getKey();

                // getValue is used to get value of key in Map
                String value=s.toString();
                
  //      }
    		 return value;
    		 
    	}catch (Exception e) {
            System.out.println("HBaseConnector method getCellValue() " + e.getMessage());
            return null;
        }
    }

    public String getNom(Map map,String fc)
    {
       return map.get(fc+":nom").toString();
    }
    
    public String getPassword(Map map,String fc)
    {
       return map.get(fc+":password").toString();
    }
    
    public String getName(Map map,String fc)
    {
       return map.get(fc+":name").toString();
    }
    
    public String getDatestart(Map map,String fc)
    {
       return map.get(fc+":date").toString();
    }

    public String getDateend(Map map,String fc)
    {
       return map.get(fc+":date").toString();
    }

    public String getRole(Map map,String fc)
    {
       return map.get(fc+":role").toString();
    }


    public String getPrenom(Map map,String fc)
    {
       return map.get(fc+":prenom").toString();
    }


    public String getNum_Dossier(Map map,String fc)
    {
       return map.get(fc+":numero_dossier").toString();
    }

     public String getDate_Audition(Map map,String fc)
    {
       return map.get(fc+":date_audition").toString();
    }

     public String getHeure_Audition(Map map,String fc)
    {
       return map.get(fc+":heure_audition").toString();
    }
   
}
