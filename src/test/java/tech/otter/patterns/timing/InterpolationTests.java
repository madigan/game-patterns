package tech.otter.patterns.timing;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author John Lynn <john@otter.tech>
 * @date 5/6/17
 */
public class InterpolationTests {
    public final Interpolation.Action<Float> LINEAR_FLOAT = (start, end, percentage) -> start + (end-start) * percentage;

    @Test
    public void testLinearProgress_zeroToHundred() {
        Float start = 0.00f;
        Float end = 1.00f;
        Float duration = 3f;
        // Create the interpolation
        Interpolation<Float> alphaFadeIn = new Interpolation<>(start, end, duration, LINEAR_FLOAT);

        // Should start at 0
        Assert.assertEquals("Should start at 0", start, alphaFadeIn.execute());

        // Advance time
        alphaFadeIn.update(1.5f);

        // Should reflect that time
        Assert.assertEquals("Should be half way through- 0.50f", new Float(end / 2), alphaFadeIn.execute());

        // Advance time to end
        alphaFadeIn.update(1.5f);

        // Should end at 1
        Assert.assertEquals("Should be 1.0", end, alphaFadeIn.execute());

        // Advance time further
        alphaFadeIn.update(5.0f);

        // Should remain at 1
        Assert.assertEquals("Should still be 1.0", end, alphaFadeIn.execute());
    }
}
