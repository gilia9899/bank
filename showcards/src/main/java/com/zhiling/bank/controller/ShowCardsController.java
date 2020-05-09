package com.zhiling.bank.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhiling.bank.pojo.Account;
import com.zhiling.bank.service.AccountService;
import com.zhiling.bank.service.SendService;
import com.zhiling.bank.util.Constants;

@Controller
@RequestMapping("/showcards")
public class ShowCardsController {
	
	@Autowired
	AccountService accService;
	
	@Autowired
	SendService sendService;
	

	@RequestMapping("/listall")
	public String listall(@RequestParam(name = "pageindex",defaultValue = "0")Integer pageindex,ModelMap m) {
		
		PageHelper.startPage(pageindex, Constants.PAGESIZE);
		List<Account> accounts = accService.findAll();
		PageInfo<Account> pageinfo = new PageInfo<Account>(accounts);
		m.put("pageinfo", pageinfo);
		m.put("accounts", accounts);
		return "listall";
	}
	
	
	@RequestMapping("/addaccount")
	public String addaccount(Model m) {
		Account acc = new Account();
		m.addAttribute("account", acc);
		
		return "addacc";
	}
	
	@RequestMapping("/checkacc")
	public String checkacc(@Validated @ModelAttribute("account") Account account,BindingResult br) {
		
		if (br.hasErrors()) {
			return "addacc";
		}
		account.setCreatedate(new Date());
		sendService.sendMessage(account);
		return "main";
	}
	
	
	
	
	
	
	
}
