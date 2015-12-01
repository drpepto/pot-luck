package cc.os3.gts.util;

import java.io.InputStream;

import org.junit.Test;

import cc.os3.gts.xsd.RegisterNameSpaceRequest;
import cc.os3.gts.xsd.RegisterNameSpaceResponse;
import junit.framework.TestCase;

public class XMLUtilTest extends TestCase {

	@Test
	public void testRegisterNameSpaceRequestGoldenPath() {
		InputStream xml = this.getClass().getResourceAsStream(
				"/register-name-space-request.xml");
		RegisterNameSpaceRequest request = XMLUtil
				.getRegisterNameSpaceRequest(xml);
		assertNotNull(request);
		assertEquals(request.getNamespace(), "os3.cc");
	}

	@Test
	public void testRegisterNameSpaceRequestPassNull() {
		RegisterNameSpaceRequest request = XMLUtil
				.getRegisterNameSpaceRequest(null);
		assertNull(request);
	}

	@Test
	public void testRegisterNameSpaceRequestBogusXML() {
		InputStream xml = this.getClass().getResourceAsStream("/bogus-xml.xml");
		RegisterNameSpaceRequest request = XMLUtil
				.getRegisterNameSpaceRequest(xml);
		assertNull(request);
	}

	@Test
	public void testRegisterNameSpaceResponseGoldenPath() {
		InputStream xml = this.getClass().getResourceAsStream(
				"/register-name-space-response.xml");
		RegisterNameSpaceResponse response = XMLUtil
				.getRegisterNameSpaceResponse(xml);
		assertNotNull(response);
		assertEquals(response.getStatus().getError(), "");
		assertEquals(response.getStatus().getStatus(), "SUCCESS");
	}

	@Test
	public void testRegisterNameSpaceResponsePassNull() {
		RegisterNameSpaceResponse response = XMLUtil
				.getRegisterNameSpaceResponse(null);
		assertNull(response);
	}

	@Test
	public void testRegisterNameSpaceResponseBogusXML() {
		InputStream xml = this.getClass().getResourceAsStream("/bogus-xml.xml");
		RegisterNameSpaceResponse response = XMLUtil
				.getRegisterNameSpaceResponse(xml);
		assertNull(response);
	}
}
