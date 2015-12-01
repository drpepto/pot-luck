package cc.os3.gts.domain;

import java.util.ArrayList;
import java.util.List;

public class BlockChain {
	private NameSpace namespace;
	private List<Block> blocks;

	public BlockChain(NameSpace namespace) {
		this.namespace = namespace;
		try {
			this.blocks = new ArrayList<Block>();
			Block root = new Block(namespace.getNameSpace().getBytes());
			blocks.add(root);
		} catch (Exception e) {

		}
	}

	public NameSpace getNamespace() {
		return namespace;
	}

	public void setNamespace(NameSpace namespace) {
		this.namespace = namespace;
	}
}
