package cc.os3.gts.repo.redis;

import cc.os3.gts.repo.NamespaceAdapter;
import cc.os3.gts.repo.VirtualFilesystemException;

public class RedisNamespaceAdapter implements NamespaceAdapter {

	@Override
	public boolean exists(String path) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean put(String path, byte[] contents) throws VirtualFilesystemException {
		return false;
	}

	@Override
	public byte[] get(String path) throws VirtualFilesystemException {
		// TODO Auto-generated method stub
		return null;
	}

}
