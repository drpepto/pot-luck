package cc.os3.gts.util;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;

import cc.os3.gts.xsd.RegisterNameSpaceRequest;
import cc.os3.gts.xsd.RegisterNameSpaceResponse;

public class XMLUtil {

	private final static Logger logger = Logger.getLogger(XMLUtil.class);

	public static RegisterNameSpaceRequest getRegisterNameSpaceRequest(
			InputStream is) {
		RegisterNameSpaceRequest request = null;
		if (is != null) {
			try {
				JAXBContext jaxbContext = JAXBContext
						.newInstance(RegisterNameSpaceRequest.class);
				Unmarshaller jaxbUnmarshaller = jaxbContext
						.createUnmarshaller();
				request = (RegisterNameSpaceRequest) jaxbUnmarshaller
						.unmarshal(is);
			} catch (Exception e) {
				logger.error(e);
			}
		}
		return request;
	}

	public static RegisterNameSpaceResponse getRegisterNameSpaceResponse(
			InputStream is) {
		RegisterNameSpaceResponse response = null;
		if (is != null) {
			try {
				JAXBContext jaxbContext = JAXBContext
						.newInstance(RegisterNameSpaceResponse.class);
				Unmarshaller jaxbUnmarshaller = jaxbContext
						.createUnmarshaller();
				response = (RegisterNameSpaceResponse) jaxbUnmarshaller
						.unmarshal(is);
			} catch (Exception e) {
				logger.error(e);
			}
		}
		return response;
	}
}
