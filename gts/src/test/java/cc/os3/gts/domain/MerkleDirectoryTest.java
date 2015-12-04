package cc.os3.gts.domain;

import static org.junit.Assert.*;

import org.junit.Test;

public class MerkleDirectoryTest {

	@Test
	public void testEmptyMerkleDirectoryHappyPath() {
		try {
			Merkle root = new MerkleRoot();
			assertNotNull(root);
			Merkle directory = new MerkleDirectory(root, "os3.cc");
			assertNotNull(directory);

			// Directory ignores root path
			// echo -n 'os3.cc' | sha256sum
			String expected = "78b999e982c995476f0e146b354fcc0f51ab086d4d2374dacd5b5ab6ba933e82";
			assertEquals(expected, directory.getHash());
		} catch (Exception e) {
			fail(e.toString());
		}
	}
	
	@Test
	public void testEmptyFileHappyPath() {
		try {
			Merkle root = new MerkleRoot();
			assertNotNull(root);
			
			Merkle directory = new MerkleDirectory(root, "os3.cc");
			assertNotNull(directory);

			Merkle file = new MerkleFile(directory, "README.txt", null);
			assertNotNull(file);
			directory.add(file);
			
			// Directory ignores root path
			// echo -n 'README.txt' | sha256sum = b01eeed0ec6fc4f4799e44c7b4084e649d6eee4f1aca52ac935553ff82108398
			// echo -n 'os3.ccb01eeed0ec6fc4f4799e44c7b4084e649d6eee4f1aca52ac935553ff82108398' | sha256sum
			String expected = "299e0c6e613458f45278a6ceb1e3899528188688719503a7cbe459fbe6009a6e";
			assertEquals(expected, directory.getHash());
		} catch (Exception e) {
			fail(e.toString());
		}
	}

	@Test
	public void testSingleFileWithContentHappyPath() {
		try {
			Merkle root = new MerkleRoot();
			assertNotNull(root);
			
			Merkle directory = new MerkleDirectory(root, "os3.cc");
			assertNotNull(directory);

			byte[] content = "fishface".toString().getBytes();
			Merkle file1 = new MerkleFile(root, "fishface.txt", content);
			directory.add(file1);
			// echo -n 'fishface.txtfishface' | sha256sum = 21bebfe9e2722f15edb4930e0cbee4b36f4610bca59f836824c76a533565aa72

			// echo -n 'os3.cc21bebfe9e2722f15edb4930e0cbee4b36f4610bca59f836824c76a533565aa72' | sha256sum
			String expected = "86ed53277a8f5105fe94d597674e4966fbf0dccbab23012b80566681f8eee875";
			assertEquals(expected, directory.getHash());
		} catch (Exception e) {
			fail(e.toString());
		}
	}

	@Test
	public void testOneFileEmptyOneFileWithContentHappyPath() {
		try {
			Merkle root = new MerkleRoot();
			assertNotNull(root);
			
			Merkle directory = new MerkleDirectory(root, "os3.cc");
			assertNotNull(directory);

			byte[] content = "fishface".toString().getBytes();
			Merkle file1 = new MerkleFile(root, "fishface.txt", content);
			directory.add(file1);
			// echo -n 'fishface.txtfishface' | sha256sum = 21bebfe9e2722f15edb4930e0cbee4b36f4610bca59f836824c76a533565aa72

			content = null;
			Merkle file2 = new MerkleFile(root, "hermansherman.txt", content);
			directory.add(file2);
			// echo -n 'hermansherman.txt' | sha256sum = 7eee7d9190e369f59bf2c07d470570e3627a5fe6e2304840778720ec6c6ea0a2

			// echo -n 'os3.cc\
			// 21bebfe9e2722f15edb4930e0cbee4b36f4610bca59f836824c76a533565aa72\
			// 7eee7d9190e369f59bf2c07d470570e3627a5fe6e2304840778720ec6c6ea0a2' | sha256sum
			String expected = "cbcdfc56fc7af39cc0e72c9341cdbb41dcfe0553cc9c642b95b3aafca7c651b2";
			assertEquals(expected, directory.getHash());
		} catch (Exception e) {
			fail(e.toString());
		}
	}

	@Test
	public void testTwoFilesWithContentHappyPath() {
		try {
			Merkle root = new MerkleRoot();
			assertNotNull(root);
			
			Merkle directory = new MerkleDirectory(root, "os3.cc");
			assertNotNull(directory);

			byte[] content = "fishface".toString().getBytes();
			Merkle file1 = new MerkleFile(root, "fishface.txt", content);
			directory.add(file1);
			// echo -n 'fishface.txtfishface' | sha256sum = 21bebfe9e2722f15edb4930e0cbee4b36f4610bca59f836824c76a533565aa72

			content = "hermansherman".toString().getBytes();
			Merkle file2 = new MerkleFile(root, "hermansherman.txt", content);
			directory.add(file2);
			// echo -n 'hermansherman.txthermansherman' | sha256sum = ca7c1242f41abc3c7aa4db80f7d4646a6a6f81c4c7a1d29fa218b98e57c31482

			// echo -n 'os3.cc\
			// 21bebfe9e2722f15edb4930e0cbee4b36f4610bca59f836824c76a533565aa72\
			// ca7c1242f41abc3c7aa4db80f7d4646a6a6f81c4c7a1d29fa218b98e57c31482' | sha256sum
			String expected = "4efa95d4fcdb01a65531b2a1ba4662c1dc7c70db1970fae6476e9584e0a720fe";
			assertEquals(expected, directory.getHash());
		} catch (Exception e) {
			fail(e.toString());
		}
	}


}
