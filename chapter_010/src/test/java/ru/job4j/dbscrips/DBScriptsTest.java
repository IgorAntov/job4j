package ru.job4j.dbscrips;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class DBScriptsTest {

    @Test
    public void whenInputRowListThenGetRunList() {
        DBScripts dbScripts = new DBScripts();
        VulnerabilityScript vScript1 = new VulnerabilityScript(1, Arrays.asList(new Integer[]{2}));
        VulnerabilityScript vScript2 = new VulnerabilityScript(2, Arrays.asList(new Integer[]{3}));
        VulnerabilityScript vScript3 = new VulnerabilityScript(3, new ArrayList<>());
        List<VulnerabilityScript> rowList = new ArrayList<>();
        rowList.add(vScript1);
        rowList.add(vScript2);
        rowList.add(vScript3);
        List<VulnerabilityScript> result = dbScripts.runListInWide(rowList);
        Assert.assertThat(result.get(0), is(vScript3));
        Assert.assertThat(result.get(1), is(vScript2));
        Assert.assertThat(result.get(2), is(vScript1));
        List<VulnerabilityScript> result2 = dbScripts.runListInDepth(rowList);
        Assert.assertThat(result2.get(0), is(vScript3));
        Assert.assertThat(result2.get(1), is(vScript2));
        Assert.assertThat(result2.get(2), is(vScript1));
    }
}