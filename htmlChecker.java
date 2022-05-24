import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;
import java.awt.EventQueue;

public class htmlChecker {
    static Stack<String> htmlCheckList = new Stack<String>();
    static Stack<String> currentTags = new Stack<String>();
    static htmlCheckerGUI frame = new htmlCheckerGUI();

    public void htmlParse() {
        int currentLine = 1;

        Scanner htmlsc = new Scanner(frame.HTMLText.getText());
        System.out.println("----------------------------------------------");
        frame.errorLogs.setText("");

        while (htmlsc.hasNextLine()) {
            System.out.println("Checking line: " + currentLine);
            String currentLineString = htmlsc.nextLine();

            getCurrentTag(currentLineString);

            for (String currTags : currentTags) {
                if (!currTags.contains(">")) {
                    frame.errorLogs.setText(frame.errorLogs.getText() + "Error: Missing '>' at line " + currentLine + "\r\n");
                }
    
                checkNotClosed(currTags);
            }

            currentTags.clear();

            currentLine++;
        }
        htmlsc.close();

        System.out.println("HTML file checked!");

        if (!htmlCheckList.empty()) {
            frame.errorLogs.setText(frame.errorLogs.getText() + "The tags that are not closed: " + htmlCheckList);
        } else if (frame.errorLogs.getText().equals("")) {
            frame.errorLogs.setText("No errors found in the HTML file!");
        }
        htmlCheckList.clear();
    }

    public static void main(String[] args) throws IOException {
        EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
    }

    public static void getCurrentTag(String line) {
        String tag = "";
        boolean beginConCat = false;

        for(int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '<') {
                tag = "";
                beginConCat = true;
            } 

            if (beginConCat == true) {
                tag = tag + line.charAt(i);
            }

            if (line.charAt(i) == '>') {
                currentTags.push(tag);
                tag = "";
                beginConCat = false;
            }
        }

        if (tag != "") {
            currentTags.push(tag);
        }
    }

    public static void checkNotClosed(String chosenTag) {
        String formattedTag = "";
        if (!chosenTag.contains(">"))
            return;

        if (chosenTag.contains("<!")) 
            return;

        if (!chosenTag.contains("</")) {
            formattedTag = chosenTag.substring(chosenTag.indexOf("<") + 1, chosenTag.indexOf(">"));
            htmlCheckList.push(formattedTag);
            
        } else {
            formattedTag = "";
            for(int i = chosenTag.indexOf("/") + 1; i < chosenTag.length(); i++) {
                if (chosenTag.charAt(i) == '>')
                    break;
                    formattedTag = formattedTag + chosenTag.charAt(i);
            } 

            if (htmlCheckList.contains(formattedTag)) {
                htmlCheckList.remove(formattedTag);
            }
        }
    }
}