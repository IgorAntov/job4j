package ru.job4j.streamapi.bankonstream;

import org.junit.Test;
import ru.job4j.bank.Account;
import ru.job4j.bank.User;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class BankOnStreamTest {

    /**
     * Test добавление User
     */
    @Test
    public void whenAddNewUserThenTrackerHasSameUsers() {
        BankOnStream bank = new BankOnStream();
        User user = new User("igor", "AS12345");
        bank.addUser(user);
        assertThat(bank.getUserList().containsKey(user), is(true));
    }

    /**
     * Test Удаление User
     */
    @Test
    public void whenDeleteUserThenTrackerHasNoUser() {
        BankOnStream bank = new BankOnStream();
        User user = new User("igor", "AS12345");
        bank.addUser(user);
        bank.deleteUser(user);
        assertThat(bank.getUserList().containsKey(user), is(false));
    }

    /**
     * Test Добваить счет для пользователя
     */
    @Test
    public void whenAddUserAccountThenHasUserAccount() {
        BankOnStream bank = new BankOnStream();
        User user = new User("igor", "AS12345");
        bank.addUser(user);
        Account account = new Account(100.0, "123");
        bank.addAccountToUser("AS12345", account);
        bank.getUserAccounts("AS12345").get(0).getRequisites();
        assertThat(bank.getUserAccounts("AS12345").get(0).getRequisites(), is("123"));
    }

    /**
     * Test Удалить счет пользователя
     */
    @Test
    public void whenDeleteUserAccountThenNoUserAccount() {
        BankOnStream bank = new BankOnStream();
        User user = new User("igor", "AS12345");
        bank.addUser(user);
        Account account = new Account(100.0, "123");
        bank.deleteAccountFromUser("AS12345", account);
        assertThat(bank.getUserAccounts("AS12345").size(), is(0));
    }

    /**
     * Test Перечисление средств с одного счета на другой пользователя
     */
    @Test
    public void whenTransferMoneyFomUserToUserThanManyIsTransfered() {
        BankOnStream bank = new BankOnStream();
        User srcUser = new User("igor", "AS12345");
        User dstUser = new User("ivan", "IS98745");
        bank.addUser(srcUser);
        bank.addUser(dstUser);
        Account srcAccount = new Account(200.0, "123");
        Account dstAccount = new Account(100.0, "456");
        bank.addAccountToUser("AS12345", srcAccount);
        bank.addAccountToUser("IS98745", dstAccount);
        assertThat(bank.transferMoney("AS12345", "123", "IS98745", "456", 100.0), is(true));
    }

    /**
     * Test Перечисление средств при недостатке средств
     */
    @Test
    public void whenTransferMoneyFomUserToUserAndNotEnoughMoneyThanManyIsNotTransfered() {
        BankOnStream bank = new BankOnStream();
        User srcUser = new User("igor", "AS12345");
        User dstUser = new User("ivan", "IS98745");
        bank.addUser(srcUser);
        bank.addUser(dstUser);
        Account srcAccount = new Account(50.0, "123");
        Account dstAccount = new Account(200.0, "456");
        bank.addAccountToUser("AS12345", srcAccount);
        bank.addAccountToUser("IS98745", dstAccount);
        assertThat(bank.transferMoney("AS12345", "123", "IS98745", "456", 100.0), is(false));
    }
}