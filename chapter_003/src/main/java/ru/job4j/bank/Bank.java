package ru.job4j.bank;

import java.util.*;
import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class Bank {

    private TreeMap<User, ArrayList<Account>> treeMap = new TreeMap<>();

    /**
     * Метод получает список пользователей и счетов.
     * @param
     */
    public TreeMap getUserList() {
        return this.treeMap;
    }

    /**
     * Метод добавляет пользователя.
     * @param
     */
    public void addUser(User user) {
        this.treeMap.putIfAbsent(user, new ArrayList<Account>());
    }

    /**
     * Метод удаляет пользователя.
     * @param user
     */
    public void deleteUser(User user) {
        this.treeMap.remove(user);
    }

    /**
     * Метод добавляет счёт пользователю.
     * @param passport
     * @param account
     */
    public void addAccountToUser(String passport, Account account) {
            for (User e : treeMap.keySet()) {
            if (e.getPassport().equals(passport)) {
                treeMap.get(e).add(account);
            }
        }
    }

    /**
     * Метод удаляет один счёт пользователя.
     * @param passport
     * @param account
     */
    public void deleteAccountFromUser(String passport, Account account) {
        for (User e : treeMap.keySet()) {
            if (e.getPassport().equals(passport)) {
                treeMap.get(e).remove(account);
            }
        }

    }

    /**
     * Метод получает список счетов для пользователя.
     * @param passport
     * @return
     */
    public List<Account> getUserAccounts(String passport) {
        List<Account> result = new ArrayList<>();
        User user;
        for (Map.Entry e : treeMap.entrySet()) {
            user = (User) e.getKey();
            if (user.getPassport().equals(passport)) {
                result = (List) e.getValue();
            }
        }
        return result;
    }

    /**
     * Метод перечисляет деньги с одного счёта на другой счёт
     * @param srcPassport
     * @param srcRequisite
     * @param dstPassport
     * @param dstRequisite
     * @param amount
     * @return
     */
    public boolean transferMoney(String srcPassport, String srcRequisite, String dstPassport, String dstRequisite, double amount) {

        boolean result = false;
        User user, srcUser, dstUser;
        Account srcAccount = null, dstAccount = null;

        for (Account account : getUserAccounts(srcPassport)) {
            if (account.getRequisites().equals(srcRequisite)) {
                srcAccount = account;
            }
        }
        for (Account account : getUserAccounts(dstPassport)) {
            if (account.getRequisites().equals(dstRequisite)) {
                dstAccount = account;
            }
        }
        if (srcAccount != null && dstAccount != null && srcAccount.getValue() >= amount) {
            srcAccount.setValue(srcAccount.getValue() - amount);
            dstAccount.setValue(dstAccount.getValue() + amount);
            result = true;
        }
        return result;
    }
}
