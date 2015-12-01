package cc.os3.gts.domain;

import java.util.LinkedHashSet;
import java.util.Set;

import cc.os3.gts.util.HashUtil;

public class MerkleDirectory extends MerkleRoot {
	private Merkle parent;
	private String path;

	public MerkleDirectory(Merkle parent, String path) {
		this.parent = parent;
		this.path = path;
		isDirty = true;
		children = new LinkedHashSet<Merkle>();
	}

	public MerkleDirectory(Merkle parent, String path, Set<Merkle> children) {
		this(parent, path);
		this.children = children;
	}

	@Override
	public String getHash() {
		if (isDirty) {
			hash = HashUtil.getDirectoryHash(path, children);
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
