package project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
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

public class HTML_PARSER {
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

	public static double logb(double a, double b) {
		return Math.log(a) / Math.log(b);
	}

	public static double log2(double a) {
		return logb(a, 2);
	}

	static int domcounter = 0;
	static int domav = 0;

	public static void main(String[] args) {

		Document doc;
		try {
			doc = Jsoup.connect("http://revan.byethost12.com/").get();
			int c = 0, commentsAmount = 0;

			
			
			// Duplicate IDs
			String idname2;
			List<String> idlist = new LinkedList<String>();
			for (Element element2 : doc.getAllElements()) {
				idname2 = element2.id();
				if (idname2.length() > 0) {
					idlist.add(idname2.toString());
				}
			}
			// Duplicate IDs
			
			
			// IdLength
			   int tid=0;
			   List<String> idval = new ArrayList<String>();
			   String idname;
			   int l = 0, average2 = 0;
			   for (Element element2 : doc.getAllElements()) {
			    idname = element2.id();
			    if (idname.length() > 0)
			     idval.add(idname.toString());
			    
			   }

			   for (String pointer :idval) {

			    average2 = pointer.length() + average2;
			    l++;
			   }
			   if(average2!=0)
			   {
			   tid=average2/ l;}
			   // IdLength

			   
			
			// Classlength
			   int g = 0, average = 0,tclass = 0;
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
			   if(average!=0)
			   {
			   tclass=average / g;}
			   // Classlength

			
			// Attribute Distinct values
			List<String> attval = new ArrayList<String>();
			for (Element element : doc.getAllElements()) {
				for (org.jsoup.nodes.Attribute attribute : element.attributes()) {
					String lol = attribute.getValue();
					attval.add(lol.toString());

				}

			}
			int valuenum = new HashSet<String>(attval).size();
			// Attribute Distinct values
			
			
			// DOM Depth
			doc.traverse(new NodeVisitor() {
				public void head(Node node, int depth) {
					if (!(node instanceof TextNode)) {
						domav = domav + depth;
						domcounter++;
					}
				}
				public void tail(Node node, int depth) {
				}
			});
			// DOM Depth

			
			// External Css
			for (Element element : doc.select("[rel]")) {
				if (element.attr("rel").contains("stylesheet")) {

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
			Elements select = src.select("script[src]");
			for (int i = 0; i < select.size(); i++) {
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
			}

			else {
				name = "TRUE";
			}
			// css head

			
			
			// HTML5 Tags
			int h = (doc.getElementsByTag("canvas").size()
					+ doc.getElementsByTag("audio").size()
					+ doc.getElementsByTag("embed").size()
					+ doc.getElementsByTag("source").size()
					+ doc.getElementsByTag("track").size()
					+ doc.getElementsByTag("video").size()
					+ doc.getElementsByTag("datalist").size()
					+ doc.getElementsByTag("keygen").size()
					+ doc.getElementsByTag("output").size()
					+ doc.getElementsByTag("article").size()
					+ doc.getElementsByTag("aside").size()
					+ doc.getElementsByTag("bdi").size()
					+ doc.getElementsByTag("details").size()
					+ doc.getElementsByTag("dialog").size()
					+ doc.getElementsByTag("figcaption").size()
					+ doc.getElementsByTag("figure").size()
					+ doc.getElementsByTag("footer").size()
					+ doc.getElementsByTag("header").size()
					+ doc.getElementsByTag("main").size()
					+ doc.getElementsByTag("mark").size()
					+ doc.getElementsByTag("menuitem").size()
					+ doc.getElementsByTag("meter").size()
					+ doc.getElementsByTag("nav").size()
					+ doc.getElementsByTag("progress").size()
					+ doc.getElementsByTag("rp").size()
					+ doc.getElementsByTag("rt").size()
					+ doc.getElementsByTag("ruby").size()
					+ doc.getElementsByTag("section").size()
					+ doc.getElementsByTag("summary").size()
					+ doc.getElementsByTag("time").size() + doc
					.getElementsByTag("wbr").size());
			// HTML5 Tags

			
			// styles
			int style = doc.getElementsByTag("style").size()
					+ doc.getElementsByAttribute("style").size();
			// styles

			
			
			// Comments
			for (Element e : doc.getAllElements()) {
				for (Node n : e.childNodes()) {
					if (n instanceof Comment) {
						commentsAmount++;
					}
				}
			}
			// Comments

			
			// Version
			List<Node> nods = doc.childNodes();
			for (Node node : nods) {
				if (node instanceof DocumentType) {
					DocumentType documentType = (DocumentType) node;
					System.out.println("\nVersion:"
							+ documentType.attr("publicid") + "\n");
				}
			}
			// Version

			
			// tags
			List<String> tags = new ArrayList<String>();
			for (Element e : doc.getAllElements()) {
				tags.add(e.tagName().toLowerCase());
			}
			int dt = new HashSet<String>(tags).size();
			// tags

			
			// Output
			System.out.println("Total Comments: " + commentsAmount);
			System.out.println("Styles inside HTML: " + style);
			System.out.println("Scripts inside HTML: "
					+ doc.getElementsByTag("script").size());
			System.out.println("Total tags: " + doc.getAllElements().size());
			System.out.println("Distinct tags:  " + dt);
			System.out.println("HTML 5 tags " + h);
			System.out.println("external css files: " + c);
			System.out.println("CSSinside head: " + name);
			System.out.println("External Scripts: " + se);
			System.out.println("total attributes " + att);
			System.out.println("Distinct Attributes: " + da);
			System.out.println("DOM average depth: " + domav / domcounter);
			System.out.println("Distinct Att values: " + valuenum);
			System.out.println("Class Average Length: " + (tclass));
			System.out.println("ID Average Length: " + (tid));
			System.out.println("Dublicate IDs are " + findDuplicates(idlist));
			// Output

			
			// Halstead
			int voc = da + dt + valuenum, len = att
					+ +doc.getAllElements().size();
			System.out.println("\n     Halstead Complexity Measures    \n");
			System.out.println("Vocabulary= " + voc);
			System.out.println("Length= " + len);
			System.out.println("Volume= " + (len * (log2(voc))));
			// Halstead
			
			
		}

		catch (IOException e) {
			e.printStackTrace();
		}

	}
}
