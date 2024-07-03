import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the number of processors: ");
        int numberOfProcessors=sc.nextInt();
        System.out.println("Enter the number of cycles: ");
        int numberOfCycles=sc.nextInt();
        System.out.println("Enter the file path: ");
        String filePath=sc.next();
        Simulator simulator=new Simulator(numberOfProcessors,numberOfCycles,filePath);
        simulator.simulate();
    }
}
