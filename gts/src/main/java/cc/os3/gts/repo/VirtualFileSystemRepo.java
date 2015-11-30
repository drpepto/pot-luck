package cc.os3.gts.repo;

public interface VirtualFileSystemRepo {
	public boolean exists(String path);
	public boolean put(String path, byte[] contents) throws VirtualFilesystemException;
	public byte[] get(String path) throws VirtualFilesystemException;
}
