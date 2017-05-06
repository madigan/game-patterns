package tech.otter.patterns.timing;

/**
 * @author John Lynn <john@otter.tech>
 * @date 5/6/17
 */
public class Interpolation<T> {
    private Action<T> action;
    private T start, end;
    private float duration;
    private float current;

    public Interpolation(T start, T end, float duration, Action<T> action) {
        this.start = start;
        this.end = end;
        this.duration = duration;
        this.action = action;
    }

    public T execute() {
        return action.execute(start, end, current/duration);
    }

    public void update(float delta) {
        if(this.current < this.duration)
            this.current = this.current + delta > this.duration ? this.duration : this.current + delta;
    }

    interface Action<N> {
        N execute(N start, N end, float percentage);
    }
}
