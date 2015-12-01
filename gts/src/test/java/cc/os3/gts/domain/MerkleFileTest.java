package cc.os3.gts.domain;

import static org.junit.Assert.*;

import org.junit.Test;

public class MerkleFileTest {

	@Test
	public void testMerkleFileConstructorHappyPath() {
		try {
			byte[] content = "fishface".toString().getBytes();
			Merkle root = new MerkleRoot();
			Merkle file = new MerkleFile(root, "waka", content);
			String expected = "ba377267b8cef422c0851b4215696d7a4756ec642c05f03caa050e7dc6854a44";
			assertNotNull(file);
			assertEquals(expected, file.getHash());
		} catch (Exception e) {
			fail(e.toString());
		}
	}

}
