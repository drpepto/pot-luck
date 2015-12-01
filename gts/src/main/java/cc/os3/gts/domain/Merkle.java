package cc.os3.gts.domain;

import java.util.Set;

/**
 * An interface to define the carious operations a Merkle element must
 * implement.
 * @Author:  Andy Olsen (andy@59clouds.com)
 */
public interface Merkle {
	public boolean add(Merkle m);
	public String getPath();
	public String getHash();
	public Set<Merkle> getChildren();
	public Merkle getParent();
	public MerkleEnum getType();
}
