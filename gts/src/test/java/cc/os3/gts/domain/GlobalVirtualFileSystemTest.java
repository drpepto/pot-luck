package cc.os3.gts.domain;

import static org.junit.Assert.*;

import org.junit.Test;

public class GlobalVirtualFileSystemTest {

	@Test
	public void testGlobalVirtualFileSystemConstructorHappyPath() {
		GlobalVirtualFileSystem gvfs = new GlobalVirtualFileSystemImpl();
		assertNotNull(gvfs);
	}

}
