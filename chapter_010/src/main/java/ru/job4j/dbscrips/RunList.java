package ru.job4j.dbscrips;

import java.util.List;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public interface RunList {
    List<VulnerabilityScript> runListInWide(List<VulnerabilityScript> vulnerabilityScriptList);
    List<VulnerabilityScript> runListInDepth(List<VulnerabilityScript> vulnerabilityScriptList);
}
