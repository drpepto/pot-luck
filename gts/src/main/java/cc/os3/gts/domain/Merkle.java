package cc.os3.gts.domain;

import java.util.Set;

public interface Merkle {
	public boolean add(Merkle m);
	public String getPath();
	public String getHash();
	public Set<Merkle> getChildren();
	public Merkle getParent();
	public MerkleEnum getType();
}
