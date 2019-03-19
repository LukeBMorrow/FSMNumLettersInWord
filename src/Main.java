import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    private static final int START_STATE = 0;
    private static final int STEP_ONE_STATE = 1;
    private static final int DELIM_STATE = 2;

    public static void main(String args[]){
        Scanner inReader = new Scanner(System.in);
        System.out.println("please enter a file name: ");
        printAllThreeLetterWords(processFile(inReader.nextLine()));

    }

    private static String processFile(String fileName){
        String result="";
        Scanner reader=null;
        try {
            reader = new Scanner(new File(fileName));
        }catch(FileNotFoundException e){
            System.out.println("file: "+fileName+"\n could not be found.");
        }
        if(reader!=null){
            while (reader.hasNext()) {
                result+= " "+reader.next();
            }
            result = result.replaceAll("[^a-zA-Z]", " ");
        }
        return result;
    }

    private static void printAllThreeLetterWords(String input){
        int counter=0;
        String word = "";
        int state = START_STATE;
        boolean eof = false;
        while(!eof){
            switch(state){
                case START_STATE:
                    if(counter>=input.length()){
                        eof=true;
                    }else if(input.charAt(counter)!=' '){//move to step one
                        state = STEP_ONE_STATE;
                        word+=input.charAt(counter);
                        counter++;
                    }else{
                        state = DELIM_STATE;
                        if(word.length()>1) {
                            System.out.println(word);
                        }
                        counter++;
                    }
                    break;
                case STEP_ONE_STATE:
                    if(counter>=input.length()){
                        eof=true;
                    }else if(input.charAt(counter)!=' '){//move to step two
                        state = START_STATE;
                        word+=input.charAt(counter);
                        counter++;
                    }else{
                        state = DELIM_STATE;
                        counter++;
                    }
                    break;
                case DELIM_STATE:
                    if(counter>=input.length()){
                        eof=true;
                    }else if(input.charAt(counter)!=' '){//move to step two
                        state = START_STATE;
                        word="";
                        word+=input.charAt(counter);
                        counter++;
                    }else{
                        counter++;
                    }
                    break;
            }
        }

    }
}
