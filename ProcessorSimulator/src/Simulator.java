import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Simulator {
    private final int numberOfProcessors;
    private final int numberOfCycles;
    private int numberOfTasks;
    private final String filePath;
    private final Clock clock;
    private final LinkedList<Task> tasks;
    private final Scheduler scheduler;

    public Simulator(int numberOfProcessors, int numberOfCycles, String filePath){
        this.numberOfProcessors=numberOfProcessors;
        this.numberOfCycles=numberOfCycles;
        this.filePath=filePath;
        tasks=new LinkedList<>();
        this.clock=new Clock(numberOfCycles);
        this.scheduler=new Scheduler(numberOfProcessors);
        readInput();
    }
    private void readInput(){
        try (BufferedReader br=new BufferedReader(new FileReader(filePath))) {
            String line= br.readLine();
            numberOfTasks=Integer.parseInt(line);
            for (int i=0;i<numberOfTasks;i++){
                line=br.readLine();
                String[] parts=line.split(" ",3);
                int creationTime= Integer.parseInt(parts[0].strip());
                int executionTime=Integer.parseInt(parts[1].strip());
                int priority=Integer.parseInt(parts[2].strip());
                Task task=new Task(creationTime,executionTime,priority,i+1);
                tasks.add(task);
            }
        }catch (IOException e){
            throw new RuntimeException("Enter an existing file");
        }
    }
    private void checkNewTask(){
        while (!tasks.isEmpty()&&tasks.getFirst().getCreationTime()==clock.getCurrentCycle()){
            scheduler.setWaitingTask(tasks.getFirst());
            System.out.println("Cycle "+clock.getCurrentCycle()+": Task "+tasks.getFirst().getId()+" created with priority "+tasks.getFirst().getPriority());
            tasks.removeFirst();
        }
    }
    public void simulate() throws InterruptedException{
        while (clock.haveTime()){
            int currentCycle= clock.getCurrentCycle();
            System.out.println("Cycle "+currentCycle+": ");
            System.out.println("--------------");
            checkNewTask();
            scheduler.moveToProccesor(currentCycle);
            scheduler.removeFromProcessor(currentCycle);
            scheduler.printRunningTasks(currentCycle);
            clock.increaseCurrentCycle();
            Thread.sleep(1000);
        }
        boolean allTasksFinished= scheduler.areAllTasksFinished();
        if(allTasksFinished){
        System.out.println("All tasks are finished");
        } else {
            System.out.println("Simulator is finished with unfinished tasks");}
        }
    }

