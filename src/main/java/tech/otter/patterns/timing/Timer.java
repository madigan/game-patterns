package tech.otter.patterns.timing;

/**
 * @author John Lynn <john@otter.tech>
 * @date 5/6/17
 */
public class Timer {
    private long interval;
    private long current;
    private Action action;

    public Timer(long interval, Action action) {
        this.interval = interval;
        this.current = 0;
        this.action = action;
    }

    public void update(float delta) {
        this.current += delta;

        while(this.current >= this.interval) {
            this.current -= this.interval;
            this.action.execute();
        }
    }

    interface Action {
        void execute();
    }
}
