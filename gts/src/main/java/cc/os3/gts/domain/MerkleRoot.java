package cc.os3.gts.domain;

import java.security.MessageDigest;
import java.util.LinkedHashSet;
import java.util.Set;

import cc.os3.gts.util.HashUtil;

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

	public int compareTo(Merkle m) {
		if (this.getHash().equals(m.getHash())) {
			return 0;
		} else {
			return -1;
		}
	}
}
