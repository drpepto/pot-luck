package cc.os3.gts.domain;

import java.security.MessageDigest;
import java.util.LinkedHashSet;
import java.util.Set;
import cc.os3.gts.util.HashUtil;

/**
 * Roots implement the base functionality for Directories and
 * Files. They have a fixed path of '/' and return null for the
 * parent.
 * @Author: Andy Olsen (andy@59clouds.com)
 */
public class MerkleRoot implements Merkle, Comparable<Merkle> {
	protected boolean isDirty;
	protected Set<Merkle> children;
	protected MessageDigest digest;
	protected String hash;

	public static final String ROOT = "/";

	public MerkleRoot() {
		isDirty = true;
		children = new LinkedHashSet<Merkle>();
	}

	public MerkleRoot(Set<Merkle> children) {
		isDirty = true;
		this.children = children;
	}

	public boolean add(Merkle m) {
		children.add(m);
		isDirty = true;
		return true;
	}

	/**
	 * Compute the hash of all items in this Merkle Root. The hashing
	 * algorithm used is SHA256. The method to compute is to start
	 * with the absolute root of "/" and to add the hash of each child
	 * element in the order the child was added.  For example, if you
	 * had ["Waka", "Waja", "Walla"], you would not add [Waka, Waja
	 * and Walla] to the hash digest, but rather add the SHA256 hash
	 * of each value [SHA256("/"), SHA256(Waka), SHA256(Waja),
	 * SHA256(Walla)].
	 */
	@Override
	public String getHash() {
		if (isDirty) {
			hash = HashUtil.getDirectoryHash(ROOT, children);
			isDirty = false;
		}
		return hash;
	}

	@Override
	public Set<Merkle> getChildren() {
		return children;
	}

	@Override
	public Merkle getParent() {
		return null;
	}

	@Override
	public MerkleEnum getType() {
		return MerkleEnum.ROOT;
	}

	@Override
	public String getPath() {
		return ROOT;
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
