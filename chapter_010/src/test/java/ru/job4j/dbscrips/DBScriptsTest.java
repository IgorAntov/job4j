package ru.job4j.dbscrips;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
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
        List<Integer> vList1 = new ArrayList<>();
        vList1.add(3);
        vList1.add(2);
        vList1.add(4);
        VulnerabilityScript vulnerabilityScript1 = new VulnerabilityScript(1, vList1);
        List<Integer> vList2 = new ArrayList<>();
        vList2.add(4);
        vList2.add(3);
        VulnerabilityScript vulnerabilityScript2 = new VulnerabilityScript(2, vList2);
        List<Integer> vList3 = new ArrayList<>();
        vList3.add(4);
        vList3.add(5);
        VulnerabilityScript vulnerabilityScript3 = new VulnerabilityScript(3, vList3);
        List<Integer> vList4 = new ArrayList<>();
        VulnerabilityScript vulnerabilityScript4 = new VulnerabilityScript(4, vList4);
        List<Integer> vList5 = new ArrayList<>();
        VulnerabilityScript vulnerabilityScript5 = new VulnerabilityScript(5, vList5);
        List<VulnerabilityScript> rowList = new ArrayList<>();
        rowList.add(vulnerabilityScript1);
        rowList.add(vulnerabilityScript2);
        rowList.add(vulnerabilityScript3);
        rowList.add(vulnerabilityScript4);
        rowList.add(vulnerabilityScript5);
        DBScripts dbScripts = new DBScripts();
        List<VulnerabilityScript> result = dbScripts.runListInWide(rowList);
        Assert.assertThat(result.get(0), is(vulnerabilityScript4));
        Assert.assertThat(result.get(1), is(vulnerabilityScript5));
        Assert.assertThat(result.get(2), is(vulnerabilityScript3));
        Assert.assertThat(result.get(3), is(vulnerabilityScript2));
        Assert.assertThat(result.get(4), is(vulnerabilityScript1));
        List<VulnerabilityScript> result2 = dbScripts.runListInDepth(rowList);
        Assert.assertThat(result2.get(0), is(vulnerabilityScript4));
        Assert.assertThat(result2.get(1), is(vulnerabilityScript5));
        Assert.assertThat(result2.get(2), is(vulnerabilityScript3));
        Assert.assertThat(result2.get(3), is(vulnerabilityScript2));
        Assert.assertThat(result2.get(4), is(vulnerabilityScript1));
    }
}