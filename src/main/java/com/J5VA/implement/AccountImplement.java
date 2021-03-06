package com.J5VA.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.J5VA.dao.AccountDao;
import com.J5VA.entity.Account;
import com.J5VA.service.AccountService;

@Service
public class AccountImplement implements AccountService {
	@Autowired
	AccountDao dao;

	@Override
	public List<Account> findAll() {
		return dao.findAll();
	}

	@Override
	public Account findById(String username) {

		return dao.findById(username).get();
	}

	@Override
	public Account create(Account account) {

		return dao.save(account);
	}

	@Override
	public Account update(Account account) {

		return dao.save(account);
	}

	@Override
	public void delete(String username) {

		dao.deleteById(username);
	}

	@Override
	public Integer findByAge(Integer age) {
		return dao.findByAge(age);
	}

	@Override
	public List<Integer> listAge() {
		return dao.listAgeAccount();
	}

	@Override
	public Account findByUsername(String username) {
		return dao.findByUsername(username);
	}

	@Override
	public List<Account> getMostBuy(Integer month, Integer year) {
		return dao.getMostBuy(month, year);
	}

	@Override
	public boolean checkByUsername(String username) {
		try {
			if (dao.findByUsername(username) == null) {
				return false;
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return true;
	}

	@Override
	public Account findByEmail(String email) {
		return dao.findByEmail(email);
	}

	@Override
	public boolean checkByEmail(String email) {
		try {
			if (dao.findByEmail(email) == null) {
				return false;
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return true;
	}

	@Override
	public Account checkByUser(String username) {
		try {
			return dao.findByUsername(username);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Account checkByMail(String email) {
		try {
			return dao.findByEmail(email);
		} catch (Exception e) {
			return null;
		}
	}
}
