package cc.os3.gts.repo;

import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.easymock.Mock;
import org.junit.Test;

import cc.os3.gts.repo.redis.RedisNamespaceAdapter;

public class NamespaceRepoTest {

	@Mock
	private RedisNamespaceAdapter redis;
	
	@Test
	public void testAddNamespaceGoldenPath() {
		try {
			// Don't actually connect to Redis for this test. 
			// Instead mock this adapater and tell the test what 
			// result we expect the Redis adapter to respond with 
			// in this context 
			redis = EasyMock.mock(RedisNamespaceAdapter.class);
			EasyMock.expect(redis.exists("/os3.cc")).andReturn(false);
			EasyMock.expect(redis.put("/os3.cc", null)).andReturn(true);
			EasyMock.replay(redis);
			
			// Now create the repo object and pass in the redis adapter
			NamespaceRepo repo = new NamespaceRepo(redis);
			
			// Test creating our namespace
			boolean result = repo.createNamespace("/os3.cc");
			assertTrue(result);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testAddNamespaceNamespaceTaken() {
		try {
			redis = EasyMock.mock(RedisNamespaceAdapter.class);
			// Simulate namespace already taken
			EasyMock.expect(redis.exists("/os3.cc")).andReturn(true);
			EasyMock.replay(redis);
			
			// Now create the repo object and pass in the redis adapter
			NamespaceRepo repo = new NamespaceRepo(redis);
			
			// Test creating our namespace
			boolean result = repo.createNamespace("/os3.cc");
			
			// Since the namespace was already taken, the call should fail
			assertFalse(result);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
