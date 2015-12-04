package cc.os3.gts.domain;

import static org.junit.Assert.*;

import org.junit.Test;

public class MerkleRootTest {

	@Test
	public void testEmptyMerkleRootHappyPath() {
		try {
			Merkle root = new MerkleRoot();
			assertNotNull(root);
			// echo -n '/' | sha256sum
			String expected = "8a5edab282632443219e051e4ade2d1d5bbc671c781051bf1437897cbdfea0f1";
			assertEquals(expected, root.getHash());
		} catch (Exception e) {
			fail(e.toString());
		}
	}
	
	@Test
	public void testMerkleRootWithEmptyDirectoryHappyPath() {
		try {
			Merkle root = new MerkleRoot();
			assertNotNull(root);
			
			MerkleDirectory dir = new MerkleDirectory(root, "os3.cc");
			root.add(dir);
			
			// NOT echo -n '/os3.cc' | sha256sum but instead is:
			// echo -n 'os3.cc' = 78b999e982c995476f0e146b354fcc0f51ab086d4d2374dacd5b5ab6ba933e82
			// echo -n '/78b999e982c995476f0e146b354fcc0f51ab086d4d2374dacd5b5ab6ba933e82' | sha256sum
			String expected = "8ba5f102015d78d8c781239339e31285cc3fc4193aaa37134e2b6988f6b69643";
			assertEquals(expected, root.getHash());
		} catch (Exception e) {
			fail(e.toString());
		}
	}
}
