package org.aidos.util;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.zip.CRC32;
import java.util.zip.Checksum;

import org.aidos.util.crypto.Whirlpool;

/**
 * Contains {@link ByteBuffer}-related utility methods.
 * @author Graham
 */
public final class ByteBufferUtils {

	/**
	 * Reads a 'tri-byte' from the specified buffer.
	 * @param buf The buffer.
	 * @return The value.
	 */
	public static int getTriByte(ByteBuffer buf) {
		return ((buf.get() & 0xFF) << 16) | ((buf.get() & 0xFF) << 8) | (buf.get() & 0xFF);
	}

	/**
	 * Writes a 'tri-byte' to the specified buffer.
	 * @param buf The buffer.
	 * @param value The value.
	 */
	public static void putTriByte(ByteBuffer buf, int value) {
		buf.put((byte) (value >> 16));
		buf.put((byte) (value >> 8));
		buf.put((byte) value);
	}

	/**
	 * Calculates the CRC32 checksum of the specified buffer.
	 * @param buffer The buffer.
	 * @return The CRC32 checksum.
	 */
	public static int getCrcChecksum(ByteBuffer buffer) {
		Checksum crc = new CRC32();
		for (int i = 0; i < buffer.limit(); i++) {
			crc.update(buffer.get(i));
		}
		return (int) crc.getValue();
	}

	/**
	 * Calculates the whirlpool digest of the specified buffer.
	 * @param buf The buffer.
	 * @return The 64-byte whirlpool digest.
	 */
	public static byte[] getWhirlpoolDigest(ByteBuffer buf) {
		byte[] bytes = new byte[buf.limit()];
		buf.get(bytes);
		return Whirlpool.whirlpool(bytes, 0, bytes.length);
	}

	/**
	 * Converts the contents of the specified byte buffer to a string, which is
	 * formatted similarly to the output of the {@link Arrays#toString()}
	 * method.
	 * @param buffer The buffer.
	 * @return The string.
	 */
	public static String toString(ByteBuffer buffer) {
		StringBuilder builder = new StringBuilder("[");
		for (int i = 0; i < buffer.limit(); i++) {
			String hex = Integer.toHexString(buffer.get(i) & 0xFF).toUpperCase();
			if (hex.length() == 1)
				hex = "0" + hex;

			builder.append("0x").append(hex);
			if (i != buffer.limit() - 1) {
				builder.append(", ");
			}
		}
		builder.append("]");
		return builder.toString();
	}

	/**
	 * Default private constructor to prevent instantiation.
	 */
	private ByteBufferUtils() {
		
	}

}
