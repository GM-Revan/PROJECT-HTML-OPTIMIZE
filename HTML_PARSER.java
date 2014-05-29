package project;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Comment;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.DocumentType;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import java.util.List;
import java.io.IOException;



public class HTML_PARSER {

 public static void main(String[] args) {

  Document doc;
  try {
   doc = Jsoup.connect("https://openeclass.teimes.gr/").get();
   
   
   int commentsAmount=0;
   
//styles
   int style=doc.getElementsByTag("style").size()+doc.getElementsByAttribute("style").size();

   
//styles
   
//Comments
      for(Element e : doc.getAllElements()){
          for(Node n: e.childNodes()){
              if(n instanceof Comment){
               commentsAmount++;
              }
          }
      }
//Comments
      
 //Version
      List<Node> nods = doc.childNodes();
      for (Node node : nods) {
         if (node instanceof DocumentType) {
             DocumentType documentType = (DocumentType)node;
               System.out.println("Version:"+documentType.attr("publicid"));
         }
     }
      
//Version
      
      
//Output
   System.out.println("Total Comments " + commentsAmount);
   System.out.println("Styles inside HTML " + style);
   System.out.println("Scripts inside HTML " + doc.getElementsByTag("script").size());
//Output   
  }

  catch (IOException e) {
   e.printStackTrace();
  }

 }

}
