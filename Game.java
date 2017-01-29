import java.util.Scanner;

public class Game {
    static Scanner coord = new Scanner(System.in);                          //Initialize + create the scanner
    static int maxX;
    static int maxY;
    static int  human = 0;                                                  //Initialize the hit scores
    static int ai = 0;
    static int[][] humanGrid;                                               //Initialize the grids
    static int[][] aiGrid;
/**
 * Player determines the size of the board
 */   
    public static void size(){

    System.out.println("Please enter the size of your game");
    while(true){                                                    //While statement's ensure you may try again if your number isn't between 5 and 20
        System.out.print("x size: ");
        maxX = coord.nextInt();
        if(maxX<5 || maxX>15){
            System.out.println("Please enter a number between 5 & 15");}
        else{break;}}
    while(true){                                                    //While statement's ensure you may try again if your number isn't between 5 and 20
        System.out.print("y size: ");
        maxY = coord.nextInt();
        if(maxY<5 || maxY>15){
            System.out.println("Please enter a number between 5 & 15");}
        else{break;}}
    humanGrid = new int[maxX][maxY];                                //Declare the size of the grids
    aiGrid = new int[maxX][maxY];

    }
/**
 * AI randomly selects location of battleships
 */
    public static void setAiGrid(){
        int x;
        int y;
        int i = 0;                                                          //Three ships will be placed
        while(i<3){
            x = (int) (Math.random() * maxX);                                   //Randomly selects a coordinate point for the ship
            y = (int) (Math.random() * maxY);
            if(aiGrid[x][y]!=1){                                            //If it equals 1 there is already a ship there
                aiGrid[x][y] = 1;
                i++;}
        }
    }
/**
 * Creates the AI's random guesses for player's ships
 */
    public static void aiGuess(){
        int x;
        int y;
        while(true){                                                        //The while lets the AI try again if it already guessed that space
            x = (int) (Math.random() * maxX);
            y = (int) (Math.random() * maxY);
            if(humanGrid[x][y]==1){
                System.out.println("The AI hit your battleship!");
                humanGrid[x][y] = 2;
                ai++;
                break;}
            else if(humanGrid[x][y]==0){
                System.out.println("The AI missed your battleship.");
                humanGrid[x][y] = 2;
                break;}
            else if(humanGrid[x][y]==2){}
            else{System.err.println("AI guess is confused");                //Something is wrong if this state is selected
            System.exit(1);}
        }
    }
/**
 * Print's the AI's field (avoiding any ship spots)
 */
    public static void printAiGrid(){
        for(int y=0; y<maxY; y++){
            for(int x=0; x<maxX; x++){
                int z = aiGrid[x][y];
                if(z==1){z = 0;}
                System.out.print(z + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
/**
 * Player selects the location of his battleships
 */
    public static void setHumanGrid(){
        int x;
        int y;
        int i = 0;
        while(i<3){
            System.out.println("Enter the location of your battleship");
            while(true){                                                    //While statement's ensure you may try again if your number isn't between 0 and 4
                System.out.print("x-coordinate: ");
                x = coord.nextInt();
                if(x<0 || x>=maxX){
                    System.out.println("Please Enter a number between 0 & " + (maxX - 1));}
                else{break;}}
            while(true){
                System.out.print("y-coordinate: ");
                y = coord.nextInt();
                if(y<0 || x>=maxY){
                    System.out.println("Please Enter a number between 0 & " + (maxY - 1));}
                else{break;}}
            if(humanGrid[x][y]==1){
                System.out.println("You already have a battleship there!");}
            else{
                humanGrid[x][y] = 1;
                i++;}
        }
    }
/**
 * Player guesses the location of the AI's battleships
 */
    public static void humanGuess(){
        int x;
        int y;
        while(true){
            System.out.println("Enter the location of your guess");
            while(true){
                System.out.print("x-coordinate: ");
                x = coord.nextInt();
                if(x<0 || x>=maxX){
                    System.out.println("Please Enter a number between 0 & " + (maxX - 1));}
                else{break;}}
            while(true){
                System.out.print("y-coordinate: ");
                y = coord.nextInt();
                if(y<0 || x>=maxY){
                    System.out.println("Please Enter a number between 0 & " + (maxX - 1));}
                else{break;}}
            if(aiGrid[x][y]==1){
                System.out.println("You hit the AI's battleship!");
                aiGrid[x][y] = 2;
                human++;
                break;}
            else if(aiGrid[x][y]==0){
                System.out.println("You missed the AI's battleship.");
                aiGrid[x][y] = 2;
                break;}
            else if(aiGrid[x][y]==2){
                System.out.println("You already guessed there n00b.");
            }
            else{System.err.println("Oops, something went wrong :(");
            System.exit(2);}
        }
    }
/**
 * Prints the player's field (not avoiding any ships)
 */
    public static void printHumanGrid(){
        for(int y=0; y<maxY; y++){
            for(int x=0; x<maxX; x++){
                int z = humanGrid[x][y];
                System.out.print(z + " ");
            }
            System.out.println();
        }
    }
/**
 * The main program
 * @param args
 */
    public static void main(String[] args){
        size();
        setHumanGrid();
        setAiGrid();
        while(true){
            aiGuess();
            printHumanGrid();                                           //Prints the player grid so you know where the AI guessed
            System.out.println("HU-▲|AI-▼");                            //The text differenciating between the two boards 
            printAiGrid();                                              //Prints the AI grid so the player knows where to guess
            if(ai >= 3){
                System.out.println("The AI won!");
                System.exit(0);}
            humanGuess();
            if(human >= 3){
                System.out.println("You won!");
                System.exit(0);}
            
        }

    }
}
