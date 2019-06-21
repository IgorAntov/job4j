package ru.job4j.search;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
import java.util.LinkedList;
public class PriorityQueue {
    private LinkedList<Task> tasks = new LinkedList<>();

    /**
     * Метод вставляет в нужную позицию элемент.
     * Позиция определять по полю приоритет.
     * Для вставик использовать add(int index, E value)
     * Если все задания имеют одинаковый приоритет, то задание добавляется в конец списка
     * @param task задача
     */
    public void put(Task task) {
        int index = 0;
        boolean isPut = false;
        if (this.tasks.size() != 0) {
            for (Task value: this.tasks) {
                if (value.getPriority() > task.getPriority()) {
                    tasks.add(index, task);
                    isPut = true;
                    break;
                }
                index++;
            }
            if (!isPut) {
                tasks.add(task);
            }
        } else {
            tasks.add(task);
        }
    }

    public Task take() {
        return this.tasks.poll();
    }
}