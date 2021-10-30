
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Vector;

public class AlgEnigma {

    public static void  encrypt(){
        Scanner input = new Scanner(System.in);
        Vector<String> frases=new Vector();
        HashMap<String, String> map1 = new HashMap<>();
        HashMap<String, String> map2 = new HashMap<>();
        HashMap<String, String> map3 = new HashMap<>();

        //User input--Choosing files and Maps
        System.out.println("Write the name of the file you want to encrypt.");
        String filename = input.nextLine();
        System.out.println("There are 4 maps for encrypt. Choose 3 in any order separated by an ifan.  Ex:3-2-4");
        System.out.println("4-2-1 por exemplo,  funciona  a 100%..");
        String mapas = input.nextLine();
        String[] splittedMaps = mapas.split("-");

        //Load choosen maps
        map1 = FileToMap(map1, splittedMaps[0],false);
        map2 = FileToMap(map2, splittedMaps[1],false);
        map3 = FileToMap(map3, splittedMaps[2],false);


        //Load Text
        frases=FileToVector(filename);
        System.out.println("Write the name of a target file for the encrypted text.");
        filename = input.next();
        //Encryptar
        CypherXDCypher(filename,frases,mapas,map1,map2,map3);

    }

    public static  void  decrypt(){

        Scanner input = new Scanner(System.in);
        Vector<String> frases=new Vector();
        String mapas;
        HashMap<String, String> map1 = new HashMap<>();
        HashMap<String, String> map2 = new HashMap<>();
        HashMap<String, String> map3 = new HashMap<>();

        //Load text
        System.out.println("Write the name of the file you want to decrypt.");
        String filename = input.next();
        System.out.println("Write the name of a target file for the decrypted text.");
        String target = input.next();
        frases=FileToVector(filename);
        String maps=frases.elementAt(0);
        String[] splittedMaps = maps.split("-");

        //Reverse map for decrypt
        map1 = FileToMap(map1, splittedMaps[0],true);
        map2 = FileToMap(map2, splittedMaps[1],true);
        map3 = FileToMap(map3, splittedMaps[2],true);


        //Decrypt
        CypherXDCypher(target, frases,"",map3,map2,map1);
    }




    public static HashMap<String, String> FileToMap(HashMap<String, String> map, String choosenmap,boolean reverse) {
        try {
            String filename = "Mapa" + choosenmap + ".txt";
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] swap = data.split("-");
                if(reverse)
                    map.put(swap[1], swap[0]);
                else
                    map.put(swap[0], swap[1]);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return map;
    }

    public static Vector<String> FileToVector( String filename) {
        Vector<String> frases= new Vector<>();
        try
        {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                frases.add(data);
            }
            myReader.close();
        } catch(FileNotFoundException e)
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return frases;
    }



    public static void
    CypherXDCypher(String filename, Vector<String> frases, String ChoosenMaps, HashMap<String,String> map1,HashMap<String,String> map2,HashMap<String,String> map3){
        int i=0;
        boolean isDecrypt=false;
        if(ChoosenMaps.equals("")) {
            i = 1;
            isDecrypt=true;
        }

        for(i=i; i< frases.size(); i++){
            String binary=StringToBinary(frases.elementAt(i));
            String[] binaryArray=binary.split("\\s");

            //Map1
            for(int k =0; k< binaryArray.length;k++){
                if (map1.containsKey(binaryArray[k])){
                    binaryArray[k]= map1.get(binaryArray[k]);
                }
            }
            //Map2
            for(int k =0; k< binaryArray.length;k++){
                if (map2.containsKey(binaryArray[k])){
                    binaryArray[k]= map2.get(binaryArray[k]);
                }
            }
            //Map3
            for(int k =0; k< binaryArray.length;k++){
                if (map3.containsKey(binaryArray[k])){
                    binaryArray[k]= map3.get(binaryArray[k]);
                }
            }
            binary="";
            for(int g=0; g<binaryArray.length;g++){
                binary+=binaryArray[g] + " ";
            }

            //New binary after using the maps
            frases.set(i,BinaryToString(binary));
        }

        // Write it to a file
        try {
            FileWriter myWriter = new FileWriter(filename);
            if(! isDecrypt) {
                myWriter.write(ChoosenMaps);
                myWriter.write("\n");
            }
            for( i= 0 ; i< frases.size(); i++){
                myWriter.write(frases.elementAt(i) );
                myWriter.write("\n");
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }




    public static String BinaryToString(String binary){
        String[] singleBinaryArray = binary.split("\\s");
        String finalResult = "";
        for (String string : singleBinaryArray) {
            Character c = (char) Integer.parseInt(string, 2);
            finalResult += c.toString()  ;

        }
        return finalResult;
    }

    public static String StringToBinary(String s){
        char[] ss =s.toCharArray();
        StringBuilder binary = new StringBuilder();
        for(Character c : ss) {
            byte[] bytes = c.toString().getBytes();
            int val = bytes[0];
            for (int i = 0; i < 8; i++) {
                binary.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
            binary.append(' ');
        }
        return binary.toString();
    }
}
