//https://howtodoinjava.com/java/io/java-read-file-to-string-examples/

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse {

    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then read link upto next )
        int currentIndex = 0;
        while(currentIndex < markdown.length()) {
            int openBracket = markdown.indexOf("[", currentIndex);
            int closeBracket = markdown.indexOf("]", openBracket);
            if (markdown.charAt(closeBracket + 1) == ':') {    
                while(currentIndex<markdown.length()){
                    openBracket = markdown.indexOf("[", currentIndex);
                    // System.out.println(openBracket);
                    closeBracket = markdown.indexOf("]", openBracket);
                    // System.out.println(closeBracket);
                    
                    int openParen = markdown.indexOf(" ");
                    // System.out.println(openParen);
        
                    int closeParen = markdown.indexOf(" ", openParen+1);
                    // System.out.println(closeParen);
                    
                    toReturn.add(markdown.substring(openParen, closeParen));
                    break;
        
                }
        
                return toReturn;
                
            
            }
            if (openBracket != -1 ) {
                if (markdown.charAt(openBracket - 1) == '!') {
                    currentIndex = openBracket + 2;
                    openBracket = markdown.indexOf("[", currentIndex);
                    closeBracket = markdown.indexOf("]", openBracket);
                }
            }
            
            if (markdown.charAt(closeBracket + 1) != '(') {
                currentIndex = closeBracket + 1;
                openBracket = markdown.indexOf("[", currentIndex);
                closeBracket = markdown.indexOf("]", openBracket);
            }
            int openParen = markdown.indexOf("(", closeBracket);
            int closeParen = markdown.indexOf(")", openParen);
            if (closeParen == -1 || openBracket == -1 || openParen == -1 || closeBracket == -1) {
                return toReturn;
            }
            else {
                currentIndex = closeParen + 1;
            }
            toReturn.add(markdown.substring(openParen + 1, closeParen));
            
         currentIndex = closeParen + 1;
            //System.out.println(currentIndex);
            
        }

        return toReturn;
    }

    


    public static void main(String[] args) throws IOException {
        Path fileName = Path.of(args[0]);
        String content = Files.readString(fileName);
        ArrayList<String> links = getLinks(content);
	    System.out.println(links);
        
    }
}
