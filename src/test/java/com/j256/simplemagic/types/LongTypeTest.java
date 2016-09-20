package com.j256.simplemagic.types;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.j256.simplemagic.endian.EndianType;
import com.j256.simplemagic.entries.MagicMatcher.MutableOffset;

public class LongTypeTest {

	@Test
	public void testLittleEndianNumber() {
		LongType longType = new LongType(EndianType.LITTLE);
		String hexBytes = "0xc3cbc6c5c7b3a1";
		Object testString = longType.convertTestString("lelong", hexBytes);
		Object value = longType.extractValueFromBytes(0, new byte[] { hexToByte("0xa1"), hexToByte("0xb3"),
				hexToByte("0xc7"), hexToByte("0xc5"), hexToByte("0xc6"), hexToByte("0xcb"), hexToByte("0xc3"), 0 });
		assertNotNull(longType.isMatch(testString, null, false, value, new MutableOffset(0), null /* unused */));
	}

	@Test
	public void testBigEndianNumber() {
		LongType longType = new LongType(EndianType.BIG);
		String hexBytes = "0xc3cbc6c5c7b3a1";
		Object testString = longType.convertTestString("lelong", hexBytes);
		Object value = longType.extractValueFromBytes(0, new byte[] { 0, hexToByte("0xc3"), hexToByte("0xcb"),
				hexToByte("0xc6"), hexToByte("0xc5"), hexToByte("0xc7"), hexToByte("0xb3"), hexToByte("0xa1") });
		assertNotNull(longType.isMatch(testString, null, false, value, new MutableOffset(0), null /* unused */));
	}

	private byte hexToByte(String hex) {
		return Integer.decode(hex).byteValue();
	}
}
