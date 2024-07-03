public class Clock {
    private int currentCycle;
    private final int numberOfCycles;

    Clock(int numberOfCycles){
        currentCycle=1;
        this.numberOfCycles=numberOfCycles;
    }

    public int getCurrentCycle(){
        return currentCycle;
    }

    public void increaseCurrentCycle(){
        currentCycle++;
    }

    public Boolean haveTime(){
        return currentCycle!=(numberOfCycles+1);
    }
}
