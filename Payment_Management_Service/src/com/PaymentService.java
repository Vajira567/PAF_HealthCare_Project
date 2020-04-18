package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import beans.PaymentBean;
import model.Payment;

/**
 * 
 * @author Ishanka
 * Service of user's payments
 * 
 */
@Path("/Payment")
public class PaymentService {
	
	Payment payment = new Payment(); // Payment object 

	//insert Payment service
	@POST
	@Path("/insert")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPayment(String psData) {
				
		PaymentBean pBean = new PaymentBean();

		
		// Convert the input string to a JSON object
				JsonObject jsonObj = new JsonParser().parse(psData).getAsJsonObject();
				
				pBean.setAppointment_id(jsonObj.get("appointment_id").getAsInt());
				int schedule_id=jsonObj.get("schedule_id").getAsInt();
				String output = payment.insertPayment(pBean,schedule_id);
				
				
				return output;
		
		
	}
		
	// get all payments service
	@GET
	@Path("/get")
	@Produces(MediaType.TEXT_HTML)
	public String readAllPayments()
	{
	return payment.viewAllPayments();
	}

	
	//get payment by its id
	@GET
	@Path("/get/{payment_id}")
	// @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_HTML)
	public String ShowPaymentById(@PathParam("payment_id") int id) {
		return payment.viewPaymentsOfPaymentById(id);
	}
}
