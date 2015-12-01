package cc.os3.gts.domain;

import static org.junit.Assert.*;

import org.junit.Test;

public class MerkleDirectoryTest {

	@Test
	public void testMerkleDirectoryConstructorHappyPath() {
		try {
			Merkle root = new MerkleRoot();
			Merkle directory = new MerkleDirectory(root, "os3.cc");
			assertNotNull(directory);

			byte[] content = "fishface".toString().getBytes();
			Merkle file1 = new MerkleFile(root, "fishface.txt", content);
			directory.add(file1);

			content = "hermansherman".toString().getBytes();
			Merkle file2 = new MerkleFile(root, "hermansherman.txt", content);
			directory.add(file2);

			// Sha256sum of:
			// os3.cc
			// 21bebfe9e2722f15edb4930e0cbee4b36f4610bca59f836824c76a533565aa72
			// ca7c1242f41abc3c7aa4db80f7d4646a6a6f81c4c7a1d29fa218b98e57c31482
			String expected = "4efa95d4fcdb01a65531b2a1ba4662c1dc7c70db1970fae6476e9584e0a720fe";
			assertEquals(expected, directory.getHash());
		} catch (Exception e) {
			fail(e.toString());
		}
	}

}
