import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.File;

public class App {
    
    public static int[] createRandomArray(int arrayLength){
        
        Random random = new Random();
        int[] array = new int[arrayLength];

        for(int i = 0; i < arrayLength; i++){
            array[i] = random.nextInt(101);
        }
        
        return array;
    }

    public static int[] readFileToArray(String filename){

        ArrayList<Integer> read = new ArrayList<>();

        try{
            Scanner reader = new Scanner(new File(filename));
            while(reader.hasNextLine()){
                String data = reader.nextLine();
                read.add(Integer.parseInt(data.trim()));
            }
            reader.close();
        }catch(FileNotFoundException e){
            System.out.println("An error has occured.");
            e.printStackTrace();
        }

        int[] array = new int[read.size()];
        for(int i = 0; i < read.size(); i++){
            array[i] = read.get(i);
        }

        return array;
    }

    public static void writeArrayToFile(int[] array, String filename){

        try{
            FileWriter writer = new FileWriter(filename + ".txt");
            for(int i : array){
                writer.write(String.valueOf(i));
                writer.write("\n");
            }
            writer.close();
            System.out.println("Successfuly written to: " + filename);
        }catch(IOException e){
            System.out.println("An error has Occured.");
            e.printStackTrace();
        }
    }

    public static void bubbleSort(int[] array){

        int l = array.length;
        boolean moved;

        for(int i = 0; i < l-1; i++){
            moved = false;
            for(int j = 0; j < l-1; j++){
                if(array[j] > array[j+1]){
                    int swapping = array[j];
                    array[j] = array[j+1];
                    array[j+1] = swapping;
                    moved = true;
                }
            }
            if(!moved){
                break;
            }
        }
    }
    
    public static void main(String[] args) throws Exception {
       
        boolean using = true;

        Scanner main = new Scanner(System.in);

        while(using){
        String input;

        System.out.println("Please select a command from the list below\nand type the word in parenthesis ;\n\nWrite a new array to file (write)\nRead a file into an array(read)\nStop (stop)");

        String filename;
        int arrayLength;

        input = main.nextLine();

        switch(input){
            
            case "write":
            System.out.print("Please enter the length of the array: ");
            arrayLength = Integer.parseInt(main.nextLine());

            System.out.print("Please enter desired file name: ");
            filename = main.nextLine();

            int[] randomArray = createRandomArray(arrayLength);
            bubbleSort(randomArray);
            writeArrayToFile(randomArray, filename);

            break;

            case "read":
            System.out.print("Enter the file to be read: ");
            filename = main.nextLine() + ".txt";

            int[] readArray = readFileToArray(filename);
            bubbleSort(readArray);
            for (int i : readArray){
                System.out.println(i);
            }

            break;

            case "stop":
            using = false;

            break;

            default:
            System.out.println("An error has occured.");

            break;

        }
        }
        main.close();
    }
}
