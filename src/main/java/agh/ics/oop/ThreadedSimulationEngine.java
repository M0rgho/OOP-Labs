package agh.ics.oop;

public class ThreadedSimulationEngine extends SimulationEngine implements Runnable{

    private int moveDelay = 0; // milliseconds

    public ThreadedSimulationEngine(MoveDirection[] directions, AbstractWorldMap map, Vector2d[] positions) {
        super(directions, map, positions);
    }

    public ThreadedSimulationEngine(MoveDirection[] directions, AbstractWorldMap map, Vector2d[] positions, int moveDelay) {
        super(directions, map, positions);
        this.moveDelay = moveDelay;
    }

    @Override
    public void run() {
        for (int i = 0; i < super.directions.length; ++i) {
            animals.get(i % animals.size()).move(directions[i]);
            try {
                Thread.sleep(moveDelay);
            } catch (InterruptedException e) {
                System.err.println("Thread execution was abrupted");
            }
        }
    }
}
