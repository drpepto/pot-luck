package cc.os3.gts.domain;

/**
 * Implement a single Block within a BlockChain.
 * @Author: Andy Olsen (andy@59clouds.com)
 */
public class Block {

	// Each block must have the id of the previous block to use in the
	// block hashing
	private byte[] previousId;

	// This id will be the result of the previousId and the hash of
	// the Merkle Root.
	private byte[] thisId;

	// Think of this like an isolated filesystem with a mount point of
	// /
	private Merkle root;

	// Minimal constructor
	public Block(byte[] previousId) {
		this.previousId = previousId;
		this.root = new MerkleRoot();
	}

	// Constructor with a custom Merkle root
	public Block(byte[] previousId, Merkle root) {
		this(previousId);
		this.root = root;
	}

	// add an item to this root. It may be a Merkle Directory or a
	// Merkle File but should not be another Merkle Root.
	public boolean add(Merkle m) {
		boolean result = false;
		if (m.getType() != MerkleEnum.ROOT) {
			root.add(m);
			result = true;
		}
		return result;
	}

	// Getter / Setters
	public byte[] getPreviousId() {
		return previousId;
	}

	public void setPreviousId(byte[] previousId) {
		this.previousId = previousId;
	}

	public byte[] getThisId() {
		return thisId;
	}

	public void setThisId(byte[] thisId) {
		this.thisId = thisId;
	}
}
