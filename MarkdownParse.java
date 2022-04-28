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
        // System.out.println("FILE LENGTH: " + markdown.length());
        while(currentIndex < markdown.length() && markdown.indexOf("[", currentIndex) >= currentIndex) {
            int openBracket = markdown.indexOf("[", currentIndex);
            int closeBracket = markdown.indexOf("]", openBracket);
            int openParen = markdown.indexOf("(", closeBracket);
            int closeParen = markdown.indexOf(")", openParen);
            // System.out.println("CURRENT STRING " + markdown.substring(openParen + 1, closeParen));
            if (markdown.indexOf("(", currentIndex) == -1) {
                break;
            }
            if (markdown.indexOf(")", currentIndex) == -1) {
                break;
            }
            if (markdown.indexOf("[", currentIndex) == -1) {
                break;
            }
            if (markdown.indexOf("]", currentIndex) == -1) {
                break;
            }
            if (openParen == closeBracket + 1) {
                if (markdown.indexOf("!", currentIndex) + 1 != markdown.indexOf("[", currentIndex)) {
                    // System.out.println("NOT IMAGE, ADDING INDEX " + currentIndex);
                    toReturn.add(markdown.substring(openParen + 1, closeParen));
                }
                else {
                    // System.out.println("IMAGE DETECTED INDEX " + currentIndex);
                }
                
                
                currentIndex = closeParen + 1;
                // COMMENT TO COMMIT NEW FILE
                // System.out.println("CURRENT INDEX: " + currentIndex);
            }
            else {
                currentIndex = closeParen;
            }
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
