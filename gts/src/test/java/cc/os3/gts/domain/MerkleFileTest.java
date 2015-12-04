package cc.os3.gts.domain;

import static org.junit.Assert.*;

import org.junit.Test;

public class MerkleFileTest {

	@Test
	public void testMerkleFileConstructorHappyPath() {
		try {
			String fileName = "waka";
			String fileContent = "fishface";
			
			byte[] bytes = fileContent.toString().getBytes();
			Merkle root = new MerkleRoot();
			Merkle file = new MerkleFile(root, fileName, bytes);
			assertNotNull(file);

			// echo -n 'wakafishface' | sha256sum 
			String expectedHashCode = "ba377267b8cef422c0851b4215696d7a4756ec642c05f03caa050e7dc6854a44";
			assertEquals(expectedHashCode, file.getHash());
			
			// To string on the object ought to return the hash
			assertEquals(file.toString(), file.getHash());
		} catch (Exception e) {
			fail(e.toString());
		}
	}

}
