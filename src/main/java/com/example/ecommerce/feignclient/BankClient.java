package com.example.ecommerce.feignclient;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "http://BANK-SERVICE/bank/account")
public interface BankClient {

	@PostMapping("/transfer")
	public ResponseEntity<Object> transferFund(@Valid @RequestParam long accountNumber1, @Valid @RequestParam long accountNumber2, @RequestParam double amount);
}
