import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;

import core.Website;
import http.HttpMethod;
import http.HttpParser;
import http.HttpParsingException;
import http.HttpRequest;

public class WebConnectionWorkerThread implements Runnable{
	private WebServer webServer;
	private Socket socket;
	private HttpParser httpParser;
	private HttpRequest request;
	private HashMap<String, String> redirect;

	public WebConnectionWorkerThread(WebServer webServer,Socket socket) {
		this.webServer = webServer;
		this.socket = socket;
		this.httpParser = new HttpParser();
		this.redirect = new HashMap<>();
		
//        redirect.put("/", "index");
//        redirect.put("/index.htm", "index.html");
//        redirect.put("/index", "index.html");
	}
	
	private void parsedRequest() throws HttpParsingException, IOException{
		this.request = this.httpParser.parseHttpRequest(this.socket.getInputStream());
	}
	
	private void sendResponse() throws IOException {
		if (this.request.getMethod() == HttpMethod.GET) {
			DataOutputStream outStream = new DataOutputStream(socket.getOutputStream());
			
			String resourcePath = request.getRequestTarget();
//			str.indexOf("CD")
			System.out.println("req target:  "+resourcePath);
			if(resourcePath.equals("/")) {
				IndexHTML indexHTML = new IndexHTML();
				List<Website> websites = this.webServer.database.getAllWebsite();
				System.out.println(websites);
				for(Website website : websites){
					indexHTML.setWebsite(website.getName(), website.getUrl());
				}
				String  html = indexHTML.getHTML();
		
				final String CRLF = "\n\r" ; // 13, 10
				
				String response = 
						"HTTP / 1.1 404 Not Found" + CRLF + 
						"Content-Length: " + html.getBytes().length + CRLF +
						CRLF +
						html +
						CRLF + CRLF; 
				
				outStream.write(response.getBytes("UTF-8"));
			} else {
				String target = resourcePath.substring(1);
				int index = target.indexOf("/");
				System.out.println("index "+index);
				String domain = "";
				if(index > 0) {
					 domain = target.substring(0, index);
					 target = target.substring(index);
				}  else {
					domain = target.substring(0);
					target = target.replace(domain, "");
				}
				
				System.out.println("domain: "+domain);
				System.out.println("target: "+target);
				
				Website website = this.webServer.database.getWebsitebyName(domain);
				System.out.println(website.toString());
				if(website.getDomain() == null) {
					 String path = "f:\\Web Server\\pagenotfound.html";
					 File file = new File(path);
					 FileInputStream fileStream = new FileInputStream(file);
			         String contentType = Files.probeContentType(file.toPath());
			         BufferedInputStream bufInputStream = new BufferedInputStream(fileStream);
			         byte[] bytes = new byte[(int) file.length()];
			         outStream.writeBytes("HTTP/1.1 200 OK\r\nContent-Type: " + contentType + "\r\n\r\n");
			         bufInputStream.read(bytes);
			         outStream.write(bytes);
			         outStream.flush();
			         bufInputStream.close();
			         System.out.println("else");
				}  else {
			        redirect.put("", domain+"/index.html");
					String path = website.getDirectory();
					
					File file = new File(path + target);
			        System.out.println(file.getAbsolutePath());
			        
			        if (redirect.get(target) != null) {
			            // Send the client a HTTP 301 request and redirect to new address
			            outStream.writeBytes("HTTP/1.1 301 Moved Permanently\n" +
			                    "Location: " + redirect.get(target));
			            System.out.println("first");
			        }
			        else if (!file.exists()) {
						String html = "<!DOCTYPE html>\n" +
			                    "<html>\n" +
			                    "\n" +
			                    "<head>\n" +
			                    "    <title>Page Not Found</title>\n" +
			                    "</head>\n" +
			                    "\n" +
			                    "<body><h1>\n" +
			                    "404 Error: Page Not Found\n" +
			                    "</h1></body>\n" +
			                    "\n" +
			                    "</html>";
				
						final String CRLF = "\n\r" ; // 13, 10
						
						String response = 
								"HTTP / 1.1 404 Not Found" + CRLF + 
								"Content-Length: " + html.getBytes().length + CRLF +
								CRLF +
								html +
								CRLF + CRLF; 
						
						outStream.write(response.getBytes("UTF-8"));
			        } else {
						 FileInputStream fileStream = new FileInputStream(file);
				         String contentType = Files.probeContentType(file.toPath());
				         BufferedInputStream bufInputStream = new BufferedInputStream(fileStream);
				         byte[] bytes = new byte[(int) file.length()];
				         outStream.writeBytes("HTTP/1.1 200 OK\r\nContent-Type: " + contentType + "\r\n\r\n");
				         bufInputStream.read(bytes);
				         outStream.write(bytes);
				         outStream.flush();
				         bufInputStream.close();
				         System.out.println("else");
			        }
					outStream.close();
					System.out.println("out closed");
				}
			}
		}
	}
	
	@Override
	public void run() {
		try {
			parsedRequest();
			sendResponse();
			if (this.socket != null) {
				this.socket.close();
				System.out.println("soc closed");
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (HttpParsingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}

}
