package cc.os3.gts.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Implement a BlockChain.
 * @Author: Andy Olsen (andy@59clouds.com)
 */
public class BlockChain {
	// Each block chain will belond to a namespace
	private NameSpace namespace;

	// Each block chain will contain an array of blocks
	private List<Block> blocks;

	// Constructor 
	public BlockChain(NameSpace namespace) {
		this.namespace = namespace;
		try {
			this.blocks = new ArrayList<Block>();
			Block root = new Block(namespace.getNameSpace().getBytes());
			blocks.add(root);
		} catch (Exception e) {

		}
	}

	// Getters / Setters
	public NameSpace getNamespace() {
		return namespace;
	}

	public void setNamespace(NameSpace namespace) {
		this.namespace = namespace;
	}
}
