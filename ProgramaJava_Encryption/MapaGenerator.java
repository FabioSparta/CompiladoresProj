package A;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class MapaGenerator {

    public static void main(String[] args) {
        int[] arr1=new int[128];
        int[] arr2=new int[128];

        // Just filling the 2 arrays
        for (int i = 0; i < 128; i++) {
            arr1[i]=i;
            arr2[i]=127-i;
        }

        // Mix all the numbers of arr2 randomly
        Random rand=new Random();
        for (int i=0 ; i<1000; i++){
            int r1 = rand.nextInt(128);
            int r2 = rand.nextInt(128);
            int temp=arr2[r1];
            arr2[r1]=arr2[r2];
            arr2[r2]=temp;
        }

        //write to file   (arr1[i] - arr2[i] for each element)
        try {
            FileWriter myWriter = new FileWriter("Mapa4.txt");
            for (int i=0;i<128;i++) {
                String num1=Integer.toBinaryString(arr1[i]);
                String num2=Integer.toBinaryString(arr2[i]);

                //padding with ZEROS to keep it at a 8 bit size
                while(num1.toCharArray().length<8){
                    num1="0"+ num1;
                }
                while(num2.toCharArray().length<8){
                    num2="0"+ num2;
                }
                //------

                myWriter.write(num1 + "-" +num2 + "\n");
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
