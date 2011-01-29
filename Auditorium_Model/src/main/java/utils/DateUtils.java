package utils;


import java.util.Date;
import java.util.Locale;
import java.util.Calendar;
import java.sql.Time;
import java.text.SimpleDateFormat;

public class DateUtils {

 public static Calendar parseTimestamp(String timestamp)
    throws Exception {
   /*
   ** we specify Locale.US since months are in english
   */
   SimpleDateFormat sdf = new SimpleDateFormat
     ("dd-MMM-yyyy HH:mm:ss", Locale.US);
   Date d = sdf.parse(timestamp);
   Calendar cal = Calendar.getInstance();
   cal.setTime(d);
   return cal;
 }

 public static void main (String a[]) throws Exception{
	 
   String timestampToParse = "24-Feb-1998 17:39:35";
   System.out.println("Timestamp : " +  timestampToParse);

   SimpleDateFormat sdf =
          new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
   //System.out.println("Calendar : "
    //  + sdf.format(parseTimestamp(timestampToParse).getTime()));
   
   Date date = new Date();
   date.setHours(8);
   date.setMinutes(30);
   date.setSeconds(0);
  // date.setTime(new Time(hour, minute, second))
   
   System.out.println(date.toLocaleString());
 }
 
}