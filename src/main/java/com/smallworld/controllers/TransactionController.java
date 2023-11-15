package com.smallworld.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.smallworld.model.Transaction;
import com.smallworld.transactions.TransactionDataFetcher;

@Controller
public class TransactionController {

    private TransactionDataFetcher dataFetcher;

    public TransactionController() throws IOException {
    	this.dataFetcher = null;
    	  String jsonContent;
    	  try {
    	        this.dataFetcher = new TransactionDataFetcher(
    	            getClass().getClassLoader().getResourceAsStream("transactions.json")
    	        );
    	    } catch (IOException e) {
    	        // Log the exception or handle it accordingly
    	        e.printStackTrace();
    	    }
		
    }
    
	@RequestMapping("/")
	public ModelAndView home() {
	    List<Transaction> transactions = dataFetcher.getTransactions();
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("transactions", transactions);
		return mav;
	}

	  
		@RequestMapping("/transactions")
		public ModelAndView transactions() {
		    List<Transaction> transactions = dataFetcher.getTransactions();
			ModelAndView mav = new ModelAndView("transactions");
			mav.addObject("transactions", transactions);
			return mav;
		}

  
}
