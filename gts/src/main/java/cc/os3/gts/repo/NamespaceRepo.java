package cc.os3.gts.repo;

public class NamespaceRepo {
	private NamespaceAdapter adapter;

	public NamespaceRepo(NamespaceAdapter adapter) {
		this.adapter = adapter;
	}
	
	public boolean createNamespace(String path) throws VirtualFilesystemException {
		boolean result = false;
		if(path != null) {
			if(! this.adapter.exists(path)) {
				this.adapter.put(path, null);
				result = true;
			}
		}
		return result;
	}
	
	public boolean addToNamespace(String path, byte[] contents) throws VirtualFilesystemException {
		boolean result = false;
		if(path != null) {
			if(this.adapter.exists(path)) {
				if(null == contents) {
					this.adapter.put(path, contents);
					result = true;
				}
			}
		}
		return result;
	}
}
