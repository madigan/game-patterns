package tech.otter.patterns.timing;

/**
 * @author John Lynn <john@otter.tech>
 * @date 5/6/17
 */
public class Cooldown {
    private float duration;
    private float remainder;
    private Action action;

    public Cooldown(float duration, Action action) {
        this.duration = duration;
        this.action = action;
        this.remainder = 0;
    }

    // Decrement the cooldown until it reaches 0
    public void update(float delta) {
        if(this.remainder > 0)
            this.remainder = this.remainder - delta > 0 ? this.remainder - delta : 0;
    }

    /**
     * Executes the cooldown action and sets the cooldown (but only if the cooldown is not in effect).
     */
    public void execute() {
        if(this.remainder == 0) {
            this.remainder = this.duration;
            this.action.execute();
        }
    }

    /**
     * Get the remaining length of the cooldown; helpful for UI elements.
     * @return Remaining cooldown time in seconds.
     */
    public float getRemainder() {
        return this.remainder;
    }

    interface Action {
        void execute();
    }
}
