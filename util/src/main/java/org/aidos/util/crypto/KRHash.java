package org.aidos.util.crypto;

/**
 * An implementation of the {@code djb2} hash function.
 * @author Graham
 * @author `Discardedx2
 */
public final class KRHash {

	/**
	 * A simplified version of the KRHash.
	 * @param str The string to hash.
	 * @return The hash code.
	 */
	public static int krHash(String str) {
		int hash = 0;
		for (int i = 0; i < str.length(); i++) {
			hash = str.charAt(i) + ((hash << 5) - hash);
		}
		return hash;
	}

	/**
	 * Default private constructor to prevent instantiation.
	 */
	private KRHash() {
		
	}

}
