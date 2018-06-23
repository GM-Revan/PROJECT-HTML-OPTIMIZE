package music;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;



public class FileList{	
    private String list;
    private String getFile(String fileName) {

	StringBuilder result = new StringBuilder("");

	//Get file from resources folder
	ClassLoader classLoader = getClass().getClassLoader();
	File file = new File(classLoader.getResource(fileName).getFile());

	try (Scanner scanner = new Scanner(file)) {

		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			result.append(line).append("\n");
		}

		scanner.close();

	} catch (IOException e) {
		e.printStackTrace();
	}
		
	return result.toString();

  }



    public FileList(){
	this.list = "Empty";
    }
    public String getList(){
        return this.getFile("./list.txt");
    }
}
