
public class IndexHTML {
	private String html, head, body;

	public IndexHTML() {
		super();
		this.head = "<head>\r\n"
				+ "    <title>Web server</title>\r\n"
				+ "    <meta charset=\"utf-8\">\r\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n"
				+ "    <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\">\r\n"
				+ "    <link rel=\"stylesheet\" href=\"https://use.fontawesome.com/releases/v5.6.3/css/all.css\" integrity=\"sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/\" crossorigin=\"anonymous\">\r\n"
				+ "    <link href=\"https://fonts.googleapis.com/icon?family=Material+Icons\" rel=\"stylesheet\">\r\n"
				+ "    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js\"></script>\r\n"
				+ "    <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js\"></script>\r\n"
				+ "    <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js\"></script>\r\n"
				+ "    <link rel=\"icon\" href=\"https://upload.wikimedia.org/wikipedia/commons/thumb/7/74/Internet-web-browser.svg/1024px-Internet-web-browser.svg.png\">\r\n"
				+ "    <style>\r\n"
				+ "    .col-sm-3{\r\n"
				+ "        margin-bottom: 50px;\r\n"
				+ "    }\r\n"
				+ "    .background {\r\n"
				+ "        background-color:  #00CCCC;\r\n"
				+ "        border-radius:  5px;\r\n"
				+ "        width: 150px;\r\n"
				+ "        height:150px;\r\n"
				+ "        line-height: 150px;\r\n"
				+ "        margin:0 auto;\r\n"
				+ "        text-align: center;\r\n"
				+ "    }\r\n"
				+ "    .abbre {\r\n"
				+ "        color: #000;\r\n"
				+ "        font-size: 5rem;\r\n"
				+ "        text-transform: uppercase;\r\n"
				+ "        vertical-align: middle;\r\n"
				+ "        display: inline-block;\r\n"
				+ "        line-height: normal;\r\n"
				+ "    }\r\n"
				+ "    .divname {\r\n"
				+ "        margin:0 auto;\r\n"
				+ "        text-align: center;\r\n"
				+ "    }\r\n"
				+ "    .name {\r\n"
				+ "        font-size: 1.2rem;\r\n"
				+ "        text-transform: capitalize;\r\n"
				+ "    }"
				+ "    .link {\r\n"
				+ "        font-size: 1rem;\r\n"
				+ "        color: #8d95af;\r\n"
				+ "        align-items: center;\r\n"
				+ "        text-decoration: none;\r\n"
				+ "        display: block;\r\n"
				+ "    }\r\n"
				+ "    .link-icon {\r\n"
				+ "        font-size: 1rem;\r\n"
				+ "    }\r\n"
				+ "    .link:hover {\r\n"
				+ "        text-decoration: none;\r\n"
				+ "    }\r\n"
				+ "  </style>\r\n"
				+ "</head>";
		this.body = "<body>\r\n"
				+ "\r\n"
				+ "<div class=\"jumbotron text-center\">\r\n"
				+ "  <h1>WEB SERVER</h1>\r\n"
				+ "  <h6>Select the website you want to visit!</h6> \r\n"
				+ "</div>\r\n"
				+ "  \r\n"
				+ "<div class=\"container\">\r\n"
				+ "  <div class=\"row\">";
	}
	
	public void setWebsite(String name, String url) {
		String abbre = String.valueOf(name.charAt(0));
		String link = "<div class=\"col-sm-3\">\r\n"
				+ "      <div class=\"background\">\r\n"
				+ "          <span class=\"abbre\">"+abbre+"</span>\r\n"
				+ "      </div>\r\n"
				+ "      <div class=\"divname\">\r\n"
				+ "            <span class=\"name\">"+name+"</span>\r\n"
				+ "            <a href=\'"+url+"' class=\"link\" target=\"_blank\">Visit website\r\n"
				+ "               <i class=\"link-icon material-icons\">arrow_forward</i>\r\n"
				+ "            </a>\r\n"
				+ "      </div>\r\n"
				+ "    </div>";
		this.body += link;
	}
	
	public String getHTML() {
		this.body += "  </div>\r\n"
				+ "</div>\r\n"
				+ "</body>";
		this.html = "<!DOCTYPE html>\r\n"
				+ "<html lang=\"en\">"
				+this.head
				+this.body
				+"</html>";
		return html;
	}
	
}
