package utils;


import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*@authors Mohamed MEDARHRI
 * 
 */

public class Sha1 {
    public static String hash(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        if (str == null) {
            return "";
        }
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] hash = new byte[40];
        md.update(str.getBytes("iso-8859-1"), 0, str.length());
        hash = md.digest();
        StringBuffer hexString = new StringBuffer();
        for (int i=0;i<hash.length;i++) {
            if ((0xFF & hash[i]) < 0x10) {
                hexString.append("0");
            }
            hexString.append(Integer.toHexString(0xFF & hash[i]));
        }
        return hexString.toString();
    }
}

