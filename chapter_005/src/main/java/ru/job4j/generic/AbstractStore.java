package ru.job4j.generic;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 *
 * abstract class that implements "Store" interface methods
 */
public abstract class AbstractStore<T extends Base> implements Store<T> {


    public  SimpleArray<T> list;

    AbstractStore(SimpleArray<T> list) {
        this.list = list;
    }

    /**
     * This method replaces model in the SimpleArray
     * @param id - identify number
     * @param model
     * @return
     */
    @Override
    public boolean replace(String id, T model) {
        boolean result = false;
        int index = getIndex(id);
        if (index != -1) {
            if (list.set(index, model)) {
                result = true;
            }
        }
        return result;
    }

    /**
     * Method adds new object(model) in the SimpleArray
     * @param model
     */
    @Override
    public void add(T model) {
        list.add(model);
    }

    /**
     * Method deletes object(model) into the SimpleArray with specific identify number
     * @param id
     * @return
     */
    public boolean delete(String id) {
        boolean result = false;
        int index = getIndex(id);
        if (index != -1) {
            if (list.delete(index)) {
                result = true;
            }
        }
        return result;
    }

    /**
     * Method finds object(model) in the SimpleArray by Identify number
     * @param id
     * @return
     */
    public T findById(String id) {
        T model = null;
        int index = getIndex(id);
        if (index != -1) {
            model = list.get(index);
        }
        return model;
    }

    /**
     * Method finds index of Object(model) with specific identify number in the SimpleArray
     * @param id
     * @return
     */
    private int getIndex(String id) {
        int result = -1;
        for (int i = 0; i < list.arraySize; i++) {
            if ((list.get(i) != null) && (list.get(i).getId() == id)) {
                result = i;
                break;
            }
        }
        return result;
    }

}
