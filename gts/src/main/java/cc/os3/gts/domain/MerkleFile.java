package cc.os3.gts.domain;

import java.util.Set;
import cc.os3.gts.util.HashUtil;

/**
 * A leaf element in a Merkle Tree. This class only contains the name
 * of the file (in this case still called path) and the contents of
 * that file. It knows how to hash these together to produce an SHA256
 * sum.
 * @Author:  Andy Olsen (andy@59clouds.com)
 */
public class MerkleFile implements Merkle, Comparable<Merkle> {
	// Every Merkle File must have either a Merkle Root or Merkle
	// Directory as a parent.
	private Merkle parent;

	// In this context, path is actually filename
	private String path;

	// The contents of the file as bytes.
	private byte[] content;

	// The hash of this file and it contents
	private String hash;

	// Constructor
	public MerkleFile(Merkle parent, String path, byte[] content) {
		this.parent = parent;
		this.path = path;
		this.content = content;
		hash = HashUtil.getPathHash(path, content);
	}

	@Override
	public Set<Merkle> getChildren() {
		// This is a Merkle File. It has no children.
		return null;
	}

	@Override
	public Merkle getParent() {
		return parent;
	}

	@Override
	public MerkleEnum getType() {
		return MerkleEnum.FILE;
	}

	@Override
	public String getPath() {
		return path;
	}

	@Override
	public boolean add(Merkle m) {
		// Cannot add files to Merkle Files.
		return false;
	}

	/**
	 * The algorithm used to hash this is SHA256. The first item added
	 * to the digest is the path (in this case, the path is the
	 * filename). The second and last item added is the content of the
	 * file as a byte[] array expressed as UTF8. The resulting hash is
	 * then converted to a string representation of hexidecimal.
	 */
	@Override
	public String getHash() {
		return hash;
	}

	/**
	 * Compare items together based on hash
	 */
	public int compareTo(Merkle m) {
		if (this.getHash().equals(m.getHash())) {
			return 0;
		} else {
			return -1;
		}
	}

	public String toString() {
		return hash;
	}
}
