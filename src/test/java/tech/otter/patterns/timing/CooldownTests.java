package tech.otter.patterns.timing;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author John Lynn <john@otter.tech>
 * @date 5/6/17
 */
public class CooldownTests {
    @Test
    public void testCooldownActivation() {
        // Create a cooldown
        final int[] counter = {0};
        Cooldown cd = new Cooldown(1f, () -> counter[0]++);

        // First time should succeed
        cd.execute();
        Assert.assertEquals("The cooldown should have executed on the first call.", 1, counter[0]);


        // Second time should fail
        cd.execute();
        Assert.assertEquals("The cooldown should have not done anything on the second call.", 1, counter[0]);
    }

    @Test
    public void testCooldownCompletion() {
        // Create a cooldown
        final int[] counter = {0};
        Cooldown cd = new Cooldown(1f, () -> counter[0]++);

        // First time should succeed
        cd.execute();
        Assert.assertEquals("The cooldown should have executed on the first call.", 1, counter[0]);

        // Second Time should succeed if time has passed
        cd.update(1f);
        cd.execute();
        Assert.assertEquals("The cooldown should have executed on the second call since time has passed.", 2, counter[0]);
    }

    @Test
    public void testCooldownCompletion_multipleUpdates() {
        // Create a cooldown
        final int[] counter = {0};
        Cooldown cd = new Cooldown(1f, () -> counter[0]++);

        // First time should succeed
        cd.execute();
        Assert.assertEquals("The cooldown should have executed on the first call.", 1, counter[0]);

        // Advance time
        cd.update(0.3f);
        cd.update(0.6f);
        cd.execute();
        Assert.assertEquals("The cooldown should have failed on the second call since time has passed.", 1, counter[0]);

        // Third time's the charm
        cd.update(0.3f);
        cd.execute();
        Assert.assertEquals("The cooldown should have executed on the third call since time has passed.", 2, counter[0]);
    }
}
