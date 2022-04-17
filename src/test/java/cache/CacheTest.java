package cache;

import junit.framework.TestCase;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CacheTest {

    @Test
    public void whenUpdate() {
        Cache cache = new Cache();
        Base base = new Base(1, 0);
        Base newBase = new Base(1, 0);
        base.setName("base1");
        newBase.setName("base2");
        cache.add(base);
        assertTrue(cache.update(newBase));
        assertThat(cache.getMemory().get(1).getName(), equalTo("base2"));
        assertThat(cache.getMemory().get(1).getVersion(), equalTo(1));
    }

    @Test
    public void whenDelete() {
        Cache cache = new Cache();
        Base base1 = new Base(1, 0);
        Base base2 = new Base(2, 0);
        base1.setName("base1");
        base2.setName("base2");
        cache.add(base1);
        cache.add(base2);
        cache.delete(base2);
        assertThat(cache.getMemory().size(), equalTo(1));
    }
}