package ev.api.examples;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


/**
 * This example will search a specified Engineering Village database and return
 * the top keywords by result count
 * 
 * Input args:
 * database=[cpx|ins|nti|pch|chm|cbn|elt|ept|geo|grf|upa|eup|wop|kna|knc] count
 * = <integer > 0, max 100>
 *
 */
public class TopControlledTerms {
	private static String EV_API_KEY = "yourkeyhere";
	private static String EV_API_INSTTOKEN = "yourtokenhere";
	private static String EV_API_URL = "https://api.elsevier.com/content/ev/results?sortField=relevance&navigator=true&navigatorDataCount=[COUNT]&apiKey=[APIKEY]&insttoken=[INSTTOKEN]&query=[QUERY]";

	public static void main(String[] args) {
		// Default and then parse arguments
		String database = "cpx";
		String count = "10";
		for (String arg : args) {
			Pattern r = Pattern
					.compile("(database|count)=(cpx|ins|nti|pch|chm|cbn|elt|ept|geo|grf|upa|eup|wop|kna|knc|\\d{1,3})");
			Matcher m = r.matcher(arg);
			if (m.find()) {
				if (m.group(1).equals("database"))
					database = m.group(2);
				else
					count = m.group(2);
			}
		}

		new TopControlledTerms().invokeXML(database, count);
		
	}

	/**
	 * Call the API to get JSON-formatted string of top terms
	 * 
	 * @param database
	 * @param count
	 */
	private void invokeXML(String database, String count) {
		HttpsURLConnection conn = null;
		try {
			// Formulate API URL and get connection
			URL url = new URL(EV_API_URL
					.replace("[APIKEY]", EV_API_KEY)
					.replace("[QUERY]", database + "%20WN%20db")
					.replace("[COUNT]", count)
					.replace("[INSTTOKEN]", EV_API_INSTTOKEN));
			conn = getHttpsURLConnection(url);

			// Parse the XML to find "cvnav" entries in NAVIGATOR element
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(conn.getInputStream());
			XPath xPath =  XPathFactory.newInstance().newXPath();
			String expression = "/PAGE/NAVIGATORS/NAVIGATOR[@NAME='cvnav']/MODIFIER/LABEL";	        
			NodeList nodelist = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);
			if (nodelist.getLength() > 0) {
				System.out.println(nodelist.getLength() + " terms found: ");
				for (int i=0; i<nodelist.getLength(); i++) {
					Element e = (Element)nodelist.item(i);
					System.out.println(e.getTextContent() + " (" + ((Element)nodelist.item(i).getParentNode()).getAttribute("COUNT") + ")");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) conn.disconnect();
		}

	}
	
	/**
	 * Create a new HTTPS connection for the API call
	 * 
	 * @param url The URL to connect to
	 * @return The HTTPSURLConnection
	 * @throws IOException if something goes wrong in reading the file.
	 */
	private HttpsURLConnection getHttpsURLConnection(URL url) throws IOException {
		URLConnection urlConn = url.openConnection();
		if (! ( urlConn instanceof HttpsURLConnection )) {
			throw new IOException("URL is not an Https URL");
		}
		
		HttpsURLConnection httpConn = (HttpsURLConnection) urlConn;
		httpConn.setAllowUserInteraction(false);
		httpConn.setInstanceFollowRedirects(true);
		httpConn.setRequestMethod("GET");
		httpConn.setRequestProperty("Accept", "application/xml");
		httpConn.connect();
		
		return httpConn;
	}}
