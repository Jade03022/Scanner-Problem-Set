import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ScannerDFA {
    public static class Token{
        String literal;
        String type;

        public Token(String literal, String type){
            this.literal = literal;
            this.type = type;
        }
    }

    public static char stateTransition(char currentState, char nextInput){
        switch(currentState){
            case 'A':
                switch(nextInput){
                    case 'D': return 'B';
                    case 'R': return 'L';
                    case '$': return 'M';
                    case 'F': return 'S';
                    default: return 'Z';
                }

            case 'B':
                switch(nextInput){
                    case 'A': return 'C';
                    case 'M': return 'G';
                    default: return 'Z';
                }
            
            case 'C':
                switch(nextInput){
                    case 'D': return 'D';
                    default: return 'Z';
                }

            case 'D':
                switch(nextInput){
                    case 'D': return 'E';
                    default: return 'Z';
                }

            case 'E':
                switch(nextInput){
                    case 'I': return 'F';
                    case 'U': return 'K';
                    default: return 'Z';
                }

            case 'F':
                switch(nextInput){
                    case 'U': return 'K';
                    default: return 'Z';
                }

            case 'G':
                switch(nextInput){
                    case 'U': return 'H';
                    default: return 'Z';
                }

            case 'H':
                switch(nextInput){
                    case 'L': return 'I';
                    default: return 'Z';
                }

            case 'I':
                switch(nextInput){
                    case 'T': return 'J';
                    default: return 'Z';
                }

            case 'J':
                switch(nextInput){
                    case 'U': return 'K';
                    default: return 'Z';
                }

            case 'K':
                switch(nextInput){
                    default: return 'Z';
                }

            case 'L':
                switch(nextInput){
                    case '0': return 'N';
                    case '1': return 'O';
                    case '2': return 'O';
                    case '3': return 'Q';
                    case '4': return 'N';
                    case '5': return 'N';
                    case '6': return 'N';
                    case '7': return 'N';
                    case '8': return 'N';
                    case '9': return 'N';
                    default: return 'Z';
                }
                
            case 'M':
                switch(nextInput){
                    case '0': return 'N';
                    case '1': return 'O';
                    case '2': return 'O';
                    case '3': return 'Q';
                    case '4': return 'N';
                    case '5': return 'N';
                    case '6': return 'N';
                    case '7': return 'N';
                    case '8': return 'N';
                    case '9': return 'N';
                    case 'F': return 'S';
                    default: return 'Z';
                }
                
            case 'N':
                switch(nextInput){
                    default: return 'Z';
                }

            case 'O':
                switch(nextInput){
                    case '0': return 'P';
                    case '1': return 'P';
                    case '2': return 'P';
                    case '3': return 'P';
                    case '4': return 'P';
                    case '5': return 'P';
                    case '6': return 'P';
                    case '7': return 'P';
                    case '8': return 'P';
                    case '9': return 'P';
                    default: return 'Z';
                }

            case 'P':
                switch(nextInput){
                    default: return 'Z';
                }

            case 'Q':
                switch(nextInput){
                    case '0': return 'R';
                    case '1': return 'R';
                    default: return 'Z';
                }

            case 'R':
                switch(nextInput){
                    default: return 'Z';
                }

            case 'S':
                switch(nextInput){
                    case '0': return 'T';
                    case '1': return 'U';
                    case '2': return 'U';
                    case '3': return 'W';
                    case '4': return 'T';
                    case '5': return 'T';
                    case '6': return 'T';
                    case '7': return 'T';
                    case '8': return 'T';
                    case '9': return 'T';
                    default: return 'Z';
                }

            case 'T':
                switch(nextInput){
                    default: return 'Z';
                }

            case 'U':
                switch(nextInput){
                    case '0': return 'V';
                    case '1': return 'V';
                    case '2': return 'V';
                    case '3': return 'V';
                    case '4': return 'V';
                    case '5': return 'V';
                    case '6': return 'V';
                    case '7': return 'V';
                    case '8': return 'V';
                    case '9': return 'V';
                    default: return 'Z';
                }

            case 'V':
                switch(nextInput){
                    default: return 'Z';
                }

            case 'W':
                switch(nextInput){
                    case '0': return 'X';
                    case '1': return 'X';
                    default: return 'Z';
                }

            case 'X':
                switch(nextInput){
                    default: return 'Z';
                }

            default: return 'Z';
        }
    }

    public static void main(String[] args){
        try{
            char state = 'A';

            ArrayList<Token> tokens = new ArrayList<>();

            BufferedReader reader = new BufferedReader(new FileReader("./Input.txt"));

            FileWriter writer = new FileWriter("./Output.txt");

            while(reader.ready()){
                String line = reader.readLine();
                String token = "";
                tokens.clear();

                boolean alreadyError = false;

                if(line.isBlank()){
                    writer.write(System.lineSeparator());
                }
                
                else{
                    for(int i = 0; i < line.length(); i++){
                        if(line.charAt(i) != ' ' && line.charAt(i) != ',' && line.charAt(i) != '\n'){
                            token += line.charAt(i);

                            if(i == line.length() - 1){
                                for(int j = 0; j < token.length(); j++){
                                    char c = token.charAt(j);
                                    state = stateTransition(state, c);
        
                                    if(state == 'Z'){
                                        tokens.add(new Token("ERROR", "ERROR"));

                                        alreadyError = true;
    
                                        break;
                                    }
                                }
    
                                if(state == 'J' || state == 'K')
                                    tokens.add(new Token(token, "KEYWORD"));
                                
                                else if(state == 'N' || state == 'O' || state == 'P' || state == 'Q' || state == 'R')
                                    tokens.add(new Token(token, "GPR"));
    
                                else if(state == 'T' || state == 'U' || state == 'V' || state == 'W' || state == 'X')
                                    tokens.add(new Token(token, "FPR"));

                                else if(alreadyError == false)
                                    tokens.add(new Token("ERROR", "ERROR"));
        
                                token = "";
                                state = 'A';
                                alreadyError = false;
                            }
                        }
    
                        else{
                            for(int j = 0; j < token.length(); j++){
                                char c = token.charAt(j);
                                state = stateTransition(state, c);
    
                                if(state == 'Z'){
                                    tokens.add(new Token("ERROR", "ERROR"));

                                    alreadyError = true;

                                    break;
                                }
                            }

                            if(state == 'J' || state == 'K')
                                tokens.add(new Token(token, "KEYWORD"));
                            
                            else if(state == 'N' || state == 'O' || state == 'P' || state == 'Q' || state == 'R')
                                tokens.add(new Token(token, "GPR"));

                            else if(state == 'T' || state == 'U' || state == 'V' || state == 'W' || state == 'X')
                                tokens.add(new Token(token, "FPR"));

                            else if(token.length() != 0 && alreadyError == false)
                                tokens.add(new Token("ERROR", "ERROR"));
    
                            token = "";
                            state = 'A';
                            alreadyError = false;
                        }
                    }

                    for(int k = 0; k < tokens.size(); k++){
                        if(k != tokens.size() - 1)
                            writer.write(tokens.get(k).type + " ");                        

                        else{
                            writer.write(tokens.get(k).type);
                            writer.write(System.lineSeparator());
                        }
                    }

                    if(tokens.size() == 0)
                        writer.write(System.lineSeparator());
                }
            }

            reader.close();
            writer.close();

        } catch(FileNotFoundException e){
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}