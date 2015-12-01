package cc.os3.gts.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class HashUtilTest {

	@Test
	public void testHashUtilGetPathHashGoldenPath() {
		try {
			String value = "fishface";
			String expected = "ffab25fea33e6610ad45210c08b2577339cec3e5df9fd0e9725aa876bb7dce3f";
			String result = HashUtil.getPathHash("/", value
					.getBytes(HashUtil.UTF8));
			assertEquals(result, expected);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
}
