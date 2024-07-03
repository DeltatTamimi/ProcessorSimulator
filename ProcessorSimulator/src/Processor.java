public class Processor {
    private final int id;
    private Task workingTask;

    public Processor(int id){
        this.id=id;
    }
    public int getId(){
        return id;
    }
    public Task getWorkingTask(){
        return workingTask;
    }

    public void setWorkingTask(Task workingTask) {
        this.workingTask = workingTask;
    }
    public Boolean isAvailable(){
        return workingTask==null;
    }
}
