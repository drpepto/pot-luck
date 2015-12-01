package cc.os3.gts.util;

import java.security.MessageDigest;
import java.util.Set;
import cc.os3.gts.domain.Merkle;

public class HashUtil {

	public static final String SHA_256 = "SHA-256";
	public static final String UTF8 = "UTF-8";
	final protected static char[] hexArray = "0123456789abcdef".toCharArray();

	public static String getDirectoryHash(String path, Set<Merkle> children) {
		String result = null;
		try {
			MessageDigest digest = MessageDigest.getInstance(SHA_256);
			digest.update(path.getBytes(UTF8));
			for (Merkle m : children) {
				String h = m.getHash();
				digest.update(h.getBytes(UTF8));
			}
			result = bytesToHex(digest.digest());
		} catch (Exception e) {

		}
		return result;
	}

	public static String getPathHash(String path, byte[] content) {
		String result = null;
		try {
			MessageDigest digest = MessageDigest.getInstance(SHA_256);
			digest.update(path.getBytes(UTF8));
			digest.update(content);
			result = bytesToHex(digest.digest());
		} catch (Exception e) {

		}
		return result;
	}

	public static String bytesToHex(byte[] bytes) {
		char[] hexChars = new char[bytes.length * 2];
		for (int j = 0; j < bytes.length; j++) {
			int v = bytes[j] & 0xFF;
			hexChars[j * 2] = hexArray[v >>> 4];
			hexChars[j * 2 + 1] = hexArray[v & 0x0F];
		}
		return new String(hexChars);
	}
}
