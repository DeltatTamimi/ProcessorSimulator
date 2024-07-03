public class Task implements Comparable<Task> {
   private final int creationTime;
   private final int executionTime;
   private final int priority;
   private final int id;
   private int enterTime;

   public Task(int creationTime, int executionTime, int priority, int id){
       this.creationTime=creationTime;
       this.executionTime=executionTime;
       this.priority=priority;
       this.id=id;
   }
   public void setEnterTime(int enterTime){
       this.enterTime=enterTime;
   }
   public Boolean getExitTime(int currentCycle){
       return enterTime+executionTime-1==currentCycle;
   }
   public int getCreationTime(){
       return creationTime;
   }

   public int getExecutionTime() {
        return executionTime;
   }

   public int getPriority() {
        return priority;
   }

   public int getId() {
        return id;
   }
   @Override
    public int compareTo(Task o){
       if (getPriority()>o.getPriority()){
           return -1;
       }
       if(getPriority()<o.getPriority()){
           return 1;
       } else {
           return Integer.compare(o.getExecutionTime(),getExecutionTime());
       }
   }
}
