package cc.os3.gts.domain;

import java.util.Set;

import cc.os3.gts.util.HashUtil;

public class MerkleFile implements Merkle, Comparable<Merkle> {
	private Merkle parent;
	private String path;
	private byte[] content;

	public MerkleFile(Merkle parent, String path, byte[] content) {
		this.parent = parent;
		this.path = path;
		this.content = content;
	}

	@Override
	public Set<Merkle> getChildren() {
		// This is a file. It has no children.
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
		// Cannot add files to files.
		return false;
	}

	@Override
	public String getHash() {
		return HashUtil.getPathHash(path, content);
	}

	public int compareTo(Merkle m) {
		if (this.getHash().equals(m.getHash())) {
			return 0;
		} else {
			return -1;
		}
	}
}
