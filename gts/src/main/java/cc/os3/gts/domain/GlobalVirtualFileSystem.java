package cc.os3.gts.domain;

import java.io.File;
import java.util.List;

public interface GlobalVirtualFileSystem {
	public NameSpace addNameSpace(String name);
	public List<NameSpace> searchNameSpace(String term);
	public NameSpace getNameSpace(String id);
	public MerkleRoot addRootFileSystem();
	public MerkleDirectory addDirectory(MerkleRoot root, File directory);
	public boolean addFile(Merkle parent, File file);
}
