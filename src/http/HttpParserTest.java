package http;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class HttpParserTest {
	private static HttpParser httpParser;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		httpParser = new HttpParser();
	}

	@Test
	void test() throws BadHttpVersionException {
		HttpRequest request = null;
		try {
			request = httpParser.parseHttpRequest(generateValidGETTestCase());
//		fail();
		} catch (HttpParsingException e) {
//			assertEquals(e.getErrCode(), HttpStatusCode.SERVER_ERROR_501_NOT_IMPLEMENTED);
			e.printStackTrace();
			
		}
		
//		assertEquals(request.getMethod(), HttpMethod.GET);
	}
	
//	@Test
//	void testBadMoethod() {
//		try {
//			HttpRequest request = httpParser.parseHttpRequest(generateValidBADTestCaseMethodName());
//			fail();
//		} catch (HttpParsingException e) {
//			// TODO Auto-generated catch block
//			assertEquals(e.getErrCode(), HttpStatusCode.SERVER_ERROR_501_NOT_IMPLEMENTED);
//		}
//		
//	}
//	
//	@Test
//	void testBadMoethod2() {
//		try {
//			HttpRequest request = httpParser.parseHttpRequest(generateValidBADTestCaseMethodName2());
//			fail();
//		} catch (HttpParsingException e) {
//			// TODO Auto-generated catch block
//			assertEquals(e.getErrCode(), HttpStatusCode.SERVER_ERROR_501_NOT_IMPLEMENTED);
//		}
//		
//	}
//	
//	@Test
//	void testBadRequestLine() {
//		try {
//			HttpRequest request = httpParser.parseHttpRequest(generateBADTestRequestLine());
//			fail();
//		} catch (HttpParsingException e) {
//			// TODO Auto-generated catch block
//			assertEquals(e.getErrCode(), HttpStatusCode.SERVER_ERROR_501_NOT_IMPLEMENTED);
//		}
//		
//	}
	
	private InputStream generateValidGETTestCase() {
		String rawData = "GET / HTTP/1.1\r\n"
				+ "Host: localhost:8000\r\n"
				+ "Connection: keep-alive\r\n"
				+ "sec-ch-ua: \" Not;A Brand\";v=\"99\", \"CocCoc\";v=\"91\", \"Chromium\";v=\"91\"\r\n"
				+ "sec-ch-ua-mobile: ?0\r\n"
				+ "Upgrade-Insecure-Requests: 1\r\n"
				+ "User-Agent: Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) coc_coc_browser/97.0.206 Chrome/91.0.4472.206 Safari/537.36\r\n"
				+ "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9\r\n"
				+ "Sec-Fetch-Site: none\r\n"
				+ "Sec-Fetch-Mode: navigate\r\n"
				+ "Sec-Fetch-User: ?1\r\n"
				+ "Sec-Fetch-Dest: document\r\n"
				+ "Accept-Encoding: gzip, deflate, br\r\n"
				+ "Accept-Language: vi-VN,vi;q=0.9,fr-FR;q=0.8,fr;q=0.7,en-US;q=0.6,en;q=0.5\r\n"
				+ "Cookie: _xsrf=2|dc8f995f|547dfa2488771d01b1ffc33071613f4c|1630985069; username-localhost-8888=\"2|1:0|10:1630985132|23:username-localhost-8888|44:NDYyNTA2ZjNiMTQ4NGRhNThlYjZjOTljMjYzODcyMzY=|850e059f0accd4c912024089905d7630ad05a6c7a16509d32d2c3b7b6d6aaaa9\"";
		InputStream inputStream = new ByteArrayInputStream(rawData.getBytes(StandardCharsets.US_ASCII));
		return inputStream;
	}
	
	private InputStream generateValidBADTestCaseMethodName() {
		String rawData = "Get / HTTP/1.1\r\n"
				+ "Host: localhost:8000\r\n"
				+ "Accept-Language: vi-VN,vi;q=0.9,fr-FR;q=0.8,fr;q=0.7,en-US;q=0.6,en;q=0.5\r\n"
				+ "Cookie: _xsrf=2|dc8f995f|547dfa2488771d01b1ffc33071613f4c|1630985069; username-localhost-8888=\"2|1:0|10:1630985132|23:username-localhost-8888|44:NDYyNTA2ZjNiMTQ4NGRhNThlYjZjOTljMjYzODcyMzY=|850e059f0accd4c912024089905d7630ad05a6c7a16509d32d2c3b7b6d6aaaa9\"";
		InputStream inputStream = new ByteArrayInputStream(rawData.getBytes(StandardCharsets.US_ASCII));
		return inputStream;
	}

	private InputStream generateValidBADTestCaseMethodName2() {
		String rawData = "GETTT / HTTP/1.1\r\n"
				+ "Host: localhost:8000\r\n"
				+ "Accept-Language: vi-VN,vi;q=0.9,fr-FR;q=0.8,fr;q=0.7,en-US;q=0.6,en;q=0.5\r\n"
				+ "Cookie: _xsrf=2|dc8f995f|547dfa2488771d01b1ffc33071613f4c|1630985069; username-localhost-8888=\"2|1:0|10:1630985132|23:username-localhost-8888|44:NDYyNTA2ZjNiMTQ4NGRhNThlYjZjOTljMjYzODcyMzY=|850e059f0accd4c912024089905d7630ad05a6c7a16509d32d2c3b7b6d6aaaa9\"";
		InputStream inputStream = new ByteArrayInputStream(rawData.getBytes(StandardCharsets.US_ASCII));
		return inputStream;
	}
	
	private InputStream generateBADTestRequestLine() {
		String rawData = "GET/ AAAAA / HTTP/1.1\r\n"
				+ "Host: localhost:8000\r\n"
				+ "Accept-Language: vi-VN,vi;q=0.9,fr-FR;q=0.8,fr;q=0.7,en-US;q=0.6,en;q=0.5\r\n"
				+ "Cookie: _xsrf=2|dc8f995f|547dfa2488771d01b1ffc33071613f4c|1630985069; username-localhost-8888=\"2|1:0|10:1630985132|23:username-localhost-8888|44:NDYyNTA2ZjNiMTQ4NGRhNThlYjZjOTljMjYzODcyMzY=|850e059f0accd4c912024089905d7630ad05a6c7a16509d32d2c3b7b6d6aaaa9\"";
		InputStream inputStream = new ByteArrayInputStream(rawData.getBytes(StandardCharsets.US_ASCII));
		return inputStream;
	}
	
}

