package project;

/**
 *            TECHNOLOGICAL EDUCATIONAL INSTITUTE OF MESSOLONGHI.
 * 
 * HTML Optimization Statistics Algorithm by George Michalitsis & Kiriakos Mpiziklis 2014.
 * 
 * You may use this code any way you wish, private, educational, or commercial.
 * It's free. See: https://github.com/GM-Revan/PROJECT-HTML-OPTIMIZE/blob/master/HTML_PARSER2.java
 * 
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Comment;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.DocumentType;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.jsoup.select.NodeVisitor;
import org.apache.commons.io.output.TeeOutputStream;

public class HTML_PARSER {
	static int domcounter = 0;
	static int domav = 0;
	static int domax = 0;

	// MAIN PROGRAM
	public static void main(String[] args) {
		Document doc;

		try {
			// TeeoutputStream Filewriter
			File f = new File("c:/Output.txt");
			if (!f.exists()) {
				try {
					f.createNewFile();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			try {
				FileOutputStream fos = new FileOutputStream(f);
				// we will want to print in standard "System.out" and in "file"
				TeeOutputStream myOut = new TeeOutputStream(System.out, fos);
				PrintStream ps = new PrintStream(myOut);
				System.setOut(ps);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// TeeoutputStream Filewriter

			// Connect to TXT Link
			File link = new File("C:/links.txt");
			String line1 = null;
			try {
				Scanner scanner = new Scanner(link);
				line1 = scanner.nextLine();
				System.out.println("Link: " + line1);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			doc = Jsoup.connect(line1).get();
			// Connect to TXT Link
			int c = 0, commentsAmount = 0;

			// Version
			for (Element e : doc.getAllElements()) {
				for (Node n : e.childNodes()) {
					if (n instanceof Comment) {
						String o = n.toString();
						int intIndex = o.indexOf("WordPress");
						if (intIndex == -1) {
						} else {
							System.out.println("CMS :Wordpress ");
						}
						break;
					}
				}

			}
			for (Element vers : doc.select("[content*=Joomla!]")) {
				System.out.println("CMS: Joomla!");
				break;
			}
			for (Element vers : doc.select("[content*=Drupal]")) {
				System.out.println("CMS: Drupal");
				break;
			}
			List<Node> nods = doc.childNodes();
			for (Node node : nods) {
				if (node instanceof DocumentType) {
					DocumentType documentType = (DocumentType) node;
					if (documentType.attr("publicid").length() > 1) {
						System.out.println("\nVersion:"
								+ documentType.attr("publicid") + "\n");
					} else {
						System.out.println("\nVersion: HTML 5\n");

					}
				}
			}
			// Version

			// Comments
			int comchar = 0;
			for (Element e : doc.getAllElements()) {
				for (Node n : e.childNodes()) {
					if (n instanceof Comment) {
						comchar = comchar + n.toString().length();
						commentsAmount++;
						String o = n.toString();
						int intIndex = o.indexOf("WordPress");
						if (intIndex == -1) {

						} else {
							System.out.println("CMS :Wordpress ");
						}
					}
				}
			}
			// Comments

			// Duplicate IDs
			// String idname2;
			// List<String> idlist = new LinkedList<String>();
			// for (Element element2 : doc.getAllElements()) {
			// idname2 = element2.id();
			// idlist.add(idname2.toString());
			// }
			// Duplicate IDs

			// IdLength
			int tid = 0;
			List<String> idval = new ArrayList<String>();
			String idname;
			int l = 0, average2 = 0;
			for (Element element2 : doc.getAllElements()) {
				idname = element2.id();
				if (idname.length() > 0)
					idval.add(idname.toString());

			}

			for (String pointer : idval) {
				average2 = pointer.length() + average2;
				l++;
			}
			if (average2 != 0) {
				tid = average2 / l;
			}
			// IdLength

			// Classlength
			int g = 0, average = 0, tclass = 0;
			List<String> classval = new ArrayList<String>();
			for (Element element2 : doc.getAllElements()) {
				for (String attribute : element2.classNames()) {
					if (attribute.length() > 0)
						classval.add(attribute.toString());
				}
			}
			for (String pointer : classval) {
				average = pointer.length() + average;
				g++;
			}
			if (average != 0) {
				tclass = average / g;
			}
			// Classlength

			// Attribute values
			int totalval = 0;
			List<String> attval = new ArrayList<String>();
			for (Element element : doc.getAllElements()) {
				for (org.jsoup.nodes.Attribute attribute : element.attributes()) {
					String lol = attribute.getValue();
					attval.add(lol.toString());
					if (lol != null && !lol.isEmpty()) {
						totalval++;
					}
				}
			}
			int valuenum = new HashSet<String>(attval).size();
			// Attribute values

			// DOM
			doc.traverse(new NodeVisitor() {
				public void head(Node node, int depth) {
					if (!(node instanceof TextNode)) {
						domav = domav + depth;
						domcounter++;
						if (domax < depth) {
							domax = depth;
						}
					}
				}

				public void tail(Node node, int depth) {
				}
			});
			float totaldom = domav / domcounter;
			// DOM

			// External Css
			int countchars = 0;
			for (Element element : doc.select("[rel]")) {
				if (element.attr("rel").contains("stylesheet")) {
					countchars = element.toString().length() + countchars;
					c++;
				}
			}
			// external Css

			// Total and Distinct Attributes
			int att = 0;
			List<String> atts = new ArrayList<String>();
			for (Element element : doc.getAllElements()) {
				for (org.jsoup.nodes.Attribute attribute : element.attributes()) {
					attribute.setValue("0");
					atts.add(attribute.toString());
					att++;
				}
			}
			int da = new HashSet<String>(atts).size();
			// Total and Distinct Attributes

			// scripts external
			int se = 0;
			Elements src = doc.select("script");
			for (Element element : src.select("script[src]")) {
				{
					se++;
				}
			}
			// scripts external

			// css head
			Element s = doc.body();
			int d = 0, stylebody = s.getElementsByTag("style").size()
					+ s.getElementsByAttribute("style").size();
			for (Element element : s.select("[rel]")) {
				if (element.attr("rel").contains("stylesheet")) {
					d++;
				}
			}

			int total = d + stylebody;
			String name;
			if (total > 0) {
				name = "FALSE";
			} else {
				name = "TRUE";
			}
			// css head

			// HTML5 Tags
			File text = new File("C:/html5tags.txt");
			int h = 0;
			try {

				Scanner scanner = new Scanner(text);
				while (scanner.hasNextLine()) {
					String line = scanner.nextLine();
					h = doc.select(line).size() + h;
				}
				scanner.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			// HTML5 Tags

			// HTML5 Distinct Tags

			int dtags5 = 0;
			File tags5 = new File("C:/html5tags.txt");
			try {

				Scanner scanner = new Scanner(tags5);

				while (scanner.hasNextLine()) {
					String line = scanner.nextLine();
					if (doc.getElementsByTag(line).size() > 0)
						dtags5++;
				}
				scanner.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			// HTML5 Distinct Tags

			// styles
			int style = doc.getElementsByTag("style").size()
					+ doc.getElementsByAttribute("style").size();
			// styles

			// tags
			List<String> tags = new ArrayList<String>();
			for (Element e : doc.getAllElements()) {
				tags.add(e.tagName().toLowerCase());
			}
			int dt = new HashSet<String>(tags).size();
			// tags

			// Output
			System.out.println("Total Comments: " + commentsAmount + "\n");
			System.out.println("Styles inside HTML: " + style);
			System.out.println("Scripts inside HTML: "
					+ doc.getElementsByTag("script").size() + "\n");
			System.out.println("Total tags: " + doc.getAllElements().size());
			System.out.println("Distinct tags:  " + dt + "\n");
			System.out.println("HTML 5 Total tags: " + h);
			System.out.println("HTML 5 Distinct tags: " + dtags5 + "\n");
			System.out.println("external css files: " + c);
			System.out.println("CSSinside head: " + name);
			System.out.println("Styles(tags and atts) Inside Body: "
					+ stylebody);
			System.out.println("External Css Inside Body: " + d + "\n");
			System.out.println("External Scripts: " + se + "\n");
			System.out.println("total attributes " + att);
			System.out.println("Distinct Attributes: " + da);
			System.out.println("total attribute Values " + totalval);
			System.out.println("Distinct Attribute Values: " + valuenum + "\n");
			System.out.println("DOM average depth: " + totaldom);
			System.out.println("DOM max depth: " + domax + "\n");
			System.out.println("Class Average Length: " + tclass);
			System.out.println("ID Average Length: " + tid + "\n");
			// System.out.println("Duplicate IDs are " +
			// findDuplicates(idlist));
			// Output

			// Count Chars
			String doctoString = doc.toString();
			File file = new File("c:/HTML.txt");
			int Countchar = doctoString.length();
			Elements scriptcount = doc.getElementsByTag("script");
			int countscriptchar = scriptcount.toString().length();
			int countCss = doc.getElementsByTag("style").toString().length()
					+ doc.getElementsByAttribute("style").toString().length()
					+ countchars;
			System.out.println("Total characters: " + Countchar);
			System.out.println("Total Script characters: " + countscriptchar);
			System.out.println("Total Css characters: " + countCss);
			System.out.println("Total Comment characters: " + comchar);

			try (FileOutputStream fop = new FileOutputStream(file)) {
				// if file doesn't exists, then create it
				if (!file.exists()) {
					file.createNewFile();
				}
				byte[] contentInBytes = doctoString.getBytes();
				fop.write(contentInBytes);
				fop.flush();
				fop.close();
			}
			// Count chars

			// Halstead
			int voc = da + dt + valuenum, len = att + totalval
					+ doc.getAllElements().size();
			System.out.println("\n     Halstead Complexity Measures\n");
			System.out.println("Vocabulary= " + voc);
			System.out.println("Length= " + len);
			System.out.println("Volume= " + (len * (log2(voc))));
			// Halstead

			// Halstead HTML 5
			int voc5 = da + dtags5 + valuenum, len5 = totalval + att + h;
			System.out
					.println("\n     Halstead Complexity Measures <<HTml 5 tags>>\n");
			System.out.println("Vocabulary= " + voc5);
			System.out.println("Length= " + len5);
			System.out.println("Volume= " + (len5 * (log2(voc5))));
			// Halstead HTML 5

			// Page Ranking
			System.out.println("\n     PAGE RANKING\n");
			AlexaSEO obj = new AlexaSEO();
			System.out
					.println(" World Ranking : " + obj.getAlexaRanking(line1));
			GoogleSeoHelper obj2 = new GoogleSeoHelper();
			System.out.println("Google Page rank: " + obj2.getPR(line1));
			// Page Ranking
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// MAIN PROGRAM

	// Duplicate Variables in array Counter
	public static Set<String> findDuplicates(
			List<String> listContainingDuplicates) {
		final Set<String> setToReturn = new HashSet<String>();
		final Set<String> set1 = new HashSet<String>();
		for (String yourInt : listContainingDuplicates) {
			if (!set1.add(yourInt)) {
				setToReturn.add(yourInt);
			}
		}
		return setToReturn;
	}

	// Duplicate Variables in array Counter

	// Log Base 2
	public static double logb(double a, double b) {
		return Math.log(a) / Math.log(b);
	}

	public static double log2(double a) {
		return logb(a, 2);
	}
	// Log Base 2
}
