package com.devsuperior.hrpayroll.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.hrpayroll.entities.Payment;
import com.devsuperior.hrpayroll.services.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping(value="/payments")
public class PaymentResource {

	@Autowired
	private PaymentService paymentService;
	@HystrixCommand(fallbackMethod = "getPaymentAlternative")
	@GetMapping("/{workerId}/days/{days}")
	public ResponseEntity<Payment>getPayment(@PathVariable Long workerId, @PathVariable Integer days){
		Payment p=paymentService.getPayment(workerId, days);
		return ResponseEntity.ok(p);
		
	}
	public ResponseEntity<Payment>getPaymentAlternative(Long workerId,Integer days){
		Payment p=new Payment("Brann Pag Mock",400.0, days);
		return ResponseEntity.ok(p);
		
	}
}
