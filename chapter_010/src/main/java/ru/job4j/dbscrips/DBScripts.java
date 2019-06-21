package ru.job4j.dbscrips;

import java.util.*;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class DBScripts implements RunList {
    private Queue<VulnerabilityScript> vSQueue = new LinkedList<>();
    private List<VulnerabilityScript> vSList = new ArrayList<>();
    private List<VulnerabilityScript> rowVSList;
    private Stack<VulnerabilityScript> vSStack = new Stack<>();

    /**
     * Script searching in the visitedScriptList.
     * @param id Script id.
     * @return is script in the VisitedScriptList.
     */
    private VulnerabilityScript findById(int id) {
        VulnerabilityScript result = null;
        for (VulnerabilityScript vsTemp: rowVSList) {
            if (id == vsTemp.getScriptId()) {
                result = vsTemp;
                break;
            }
        }
        return result;
    }

    /**
     * Script searching in the visitedScriptList.
     * @param vScript script that need to be checked in the List.
     * @return
     */
    private boolean isScriptInList(VulnerabilityScript vScript) {
        boolean result = false;
        for (VulnerabilityScript vsTemp: vSList) {
            if (vScript.getScriptId() == vsTemp.getScriptId()) {
                result = true;
                break;
            }
        }
        return !result;
    }

    /**
     * Search for optimal solutions (script execution) in wide.
     * @param vulnerabilityScriptList original list.
     * @return optimal sorted list.
     */
    @Override
    public List<VulnerabilityScript> runListInWide(List<VulnerabilityScript> vulnerabilityScriptList) {
        boolean isDone;
        vSList.clear();
        rowVSList = vulnerabilityScriptList;
        for (VulnerabilityScript vScript: vulnerabilityScriptList) {
            if (isScriptInList(vScript)) {
                vSQueue.offer(vScript);
                while (!vSQueue.isEmpty()) {
                    isDone = true;
                    VulnerabilityScript item = vSQueue.poll();
                    assert item != null;
                    if (item.getDependencies().isEmpty() && isScriptInList(item)) {
                        vSList.add(item);
                    } else {
                        for (Integer id : item.getDependencies()) {
                            VulnerabilityScript v = findById(id);
                            if (isScriptInList(v)) {
                                vSQueue.offer(v);
                                isDone = false;
                            }
                        }
                        if (isScriptInList(item) && !isDone) {
                            vSQueue.offer(item);
                        }
                        if (isScriptInList(item) && isDone) {
                            vSList.add(item);
                        }
                    }
                }
            }
        }
        return this.vSList;
    }

    /**
     * Search for optimal solutions (script execution) in depth.
     * @param vulnerabilityScriptList original list.
     * @return optimal sorted list.
     */
    @Override
    public List<VulnerabilityScript> runListInDepth(List<VulnerabilityScript> vulnerabilityScriptList) {
        boolean isDone;
        vSList.clear();
        rowVSList = vulnerabilityScriptList;
        for (VulnerabilityScript vScript: vulnerabilityScriptList) {
            if (isScriptInList(vScript)) {
                vSStack.push(vScript);
                while (!vSStack.isEmpty()) {
                    isDone = true;
                    VulnerabilityScript item = vSStack.pop();
                    if (item.getDependencies().isEmpty() && isScriptInList(item)) {
                        vSList.add(item);
                    } else {
                        for (int i = 0; i < item.getDependencies().size(); i++) {
                            VulnerabilityScript v = findById(item.getDependencies().get(item.getDependencies().size() - 1 - i));
                            if (isScriptInList(v)) {
                                isDone = false;
                            }
                        }
                        if (isScriptInList(item) && !isDone) {
                            vSStack.push(item);
                            for (int i = 0; i < item.getDependencies().size(); i++) {
                                VulnerabilityScript v = findById(item.getDependencies().get(item.getDependencies().size() - 1 - i));
                                vSStack.push(v);
                            }
                        }
                        if (isScriptInList(item) && isDone) {
                            vSList.add(item);
                        }
                    }
                }
            }
        }
        return this.vSList;
    }
}
