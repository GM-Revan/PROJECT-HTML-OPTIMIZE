package project;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;





public class HTML_PARSER
{

public static void main(String[] args) 
 {
	

 Document doc;
 try {

  doc = Jsoup.connect("http://www.geekbuying.com/").get();
  
  int ntmAmount=0;
 
Elements divs = doc.getElementsByTag("div");

for (int i = 0; i < divs.size(); i++) 
{
	ntmAmount++;
}
  
  System.out.println("Divs "+ ntmAmount);

 }
 catch (IOException e) 
 {
  e.printStackTrace();
 }

 }

}
