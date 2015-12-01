package cc.os3.gts.domain;

import java.util.LinkedHashSet;
import java.util.Set;
import cc.os3.gts.util.HashUtil;

/**
 * Merkle Directories may contain either Merkle File or other Merkle
 * Directories. These classes know how to hash the contents of their
 * children nodes along with the path of the directory.
 * @Author:  Andy Olsen (andy@59clouds.com)
 * @See: MerkleRoot
 */
public class MerkleDirectory extends MerkleRoot {
	// All Merkle Directories should have a Merkle Root
	private Merkle parent;

	// Each Merkle Directory also contains a unique path relative to
	// the Merkle Root
	private String path;

	// Simple Constructor
	public MerkleDirectory(Merkle parent, String path) {
		this.parent = parent;
		this.path = path;
		isDirty = true;
		children = new LinkedHashSet<Merkle>();
	}

	// Constructor allowing custom children
	public MerkleDirectory(Merkle parent, String path, Set<Merkle> children) {
		this(parent, path);
		this.children = children;
	}

	/**
	 * Compute the hash of all items in this Merkle Directory. The
	 * hashing algorithm used is SHA256. The method to compute is to
	 * start with the path as the first thing hashed. Now iterated
	 * through the children in order and hash each child. Add the
	 * resulting hash to this hash. For example, if you had ["Waka",
	 * "Waja", "Walla"], you would not add [Waka, Waja and Walla] to
	 * the hash digest, but rather add the SHA256 hash of each value
	 * [SHA256(path), SHA256(Waka), SHA256(Waja), SHA256(Walla)].
	 */
	@Override
	public String getHash() {
		if (isDirty) {
			hash = HashUtil.getDirectoryHash(path, children);
			// Hash is now current. Set dirty to false
			isDirty = false;
		}
		return hash;
	}

	@Override
	public Merkle getParent() {
		return parent;
	}

	@Override
	public MerkleEnum getType() {
		return MerkleEnum.DIRECTORY;
	}

	@Override
	public String getPath() {
		return path;
	}

}
