package io.github.alxiw.hello.service.account;

import io.github.alxiw.hello.model.Account;

import java.util.List;

public interface AccountDao {

    int addAccount(String uin, String name);

    Account getAccountById(int id);

    List<Account> getAllAccounts();

    int updateAccount(Account account);

    int deleteAccount(int id);
}