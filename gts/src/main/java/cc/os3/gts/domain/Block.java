package cc.os3.gts.domain;

public class Block {
	private byte[] previousId;
	private byte[] thisId;
	private Merkle root;

	public Block(byte[] previousId) {
		this.previousId = previousId;
		this.root = new MerkleRoot();
	}

	public Block(byte[] previousId, Merkle root) {
		this(previousId);
		this.root = root;
	}

	public boolean add(Merkle m) {
		root.add(m);
		return true;
	}

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
