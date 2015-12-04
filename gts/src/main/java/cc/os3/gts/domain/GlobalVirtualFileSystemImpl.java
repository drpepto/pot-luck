package cc.os3.gts.domain;

import java.io.File;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


public class GlobalVirtualFileSystemImpl implements GlobalVirtualFileSystem {

	private Set<NameSpace> namespaces;

	public GlobalVirtualFileSystemImpl() {
		namespaces = new LinkedHashSet<NameSpace>();
	}
	
	@Override
	public NameSpace addNameSpace(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MerkleRoot addRootFileSystem() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MerkleDirectory addDirectory(MerkleRoot root, File directory) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addFile(Merkle parent, File file) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<NameSpace> searchNameSpace(String term) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NameSpace getNameSpace(String id) {
		return null;
	}

}
