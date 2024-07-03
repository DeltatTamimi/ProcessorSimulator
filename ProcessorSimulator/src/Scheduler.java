import java.util.ArrayList;
import java.util.PriorityQueue;

public class Scheduler {
    private final PriorityQueue<Task> waitingTask;
    private final ArrayList<Processor> processors;

    public Scheduler(int numberOfProcessors) {
        waitingTask = new PriorityQueue<>();
        processors = new ArrayList<>();
        for (int i = 0; i < numberOfProcessors; ++i) {
            processors.add(new Processor(i + 1));
        }
    }

    public void setWaitingTask(Task task) {
        waitingTask.add(task);
    }

    public void moveToProccesor(int currentCycle) {
        for (Processor processor : processors) {
            if (processor.isAvailable() && !(waitingTask.isEmpty())) {
                processor.setWorkingTask(waitingTask.poll());
                System.out.println("Cycle " + currentCycle + ": Task " + processor.getWorkingTask().getId() + " moved to processor " + processor.getId());
                processor.getWorkingTask().setEnterTime(currentCycle);
            }
        }
    }

    public void removeFromProcessor(int currentCycle) {
        for (Processor processor : processors) {
            if (isTaskFinished(currentCycle, processor)) {
                System.out.println("Cycle " + currentCycle + ": Task " + processor.getWorkingTask().getId() + " completed on processor " + processor.getId());
                processor.setWorkingTask(null);
            }
        }
    }

    private Boolean isTaskFinished(int currentCycle, Processor processor) {
        return (processor.getWorkingTask() != null) && (processor.getWorkingTask().getExitTime(currentCycle));
    }

    public void printRunningTasks(int currentCycle) {
        for (Processor processor : processors) {
            if (!processor.isAvailable()) {
                System.out.println("Cycle " + currentCycle + ": Task " + processor.getWorkingTask().getId() + " running in processor " + processor.getId());
            }
        }
        System.out.println();
    }


    public boolean areAllTasksFinished() {
        if (!waitingTask.isEmpty()) {
            return false;
        }
        for (Processor processor : processors) {
            if (!processor.isAvailable()){
                return false;
            }
        }
        return true;
    }
}