package com.productmanagement.Product.Managemnt.service;


import com.productmanagement.Product.Managemnt.dto.OrderResponseDto;
import com.productmanagement.Product.Managemnt.models.Bill;
import com.productmanagement.Product.Managemnt.models.Customer;
import com.productmanagement.Product.Managemnt.models.Order;
import com.productmanagement.Product.Managemnt.models.Product;
import com.productmanagement.Product.Managemnt.repositiries.CustomerRepo;
import com.productmanagement.Product.Managemnt.repositiries.OrderRepo;
import com.productmanagement.Product.Managemnt.repositiries.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private ProductRepo productRepo;


    @Autowired
    private BillService billService;


    //this method is for add order
    public OrderResponseDto addOrder(Order order){
        OrderResponseDto orderResponseDto=new OrderResponseDto();
        try{
            Customer customer=customerRepo.findById(order.getCustomer().getCustomerId()).get();
            Product product=productRepo.findById(order.getProduct().getProductId()).get();

            //check whether though quantity of product is available
         if(order.getQuantity()>product.getInventoryCount()){
             orderResponseDto.setResponse(null);
             orderResponseDto.setMessage("Order not placed due insufficient stock");
             orderResponseDto.setHttpStatus(HttpStatus.BAD_REQUEST);
                 return orderResponseDto;
         }

         //check whether payment is done or not
        if(payment()){
            orderRepo.save(order);

            //by direction relationship  between customer and order
            customer.getOrderList().add(order);

            //by direction relationship  between product and order
            product.getOrderList().add(order);


            //by direction relationship  between customer and product
            customer.getProductList().add(product);
            product.getCustomerList().add(customer);


            customerRepo.save(customer);
            productRepo.save(product);


            orderResponseDto=new OrderResponseDto();
            orderResponseDto.setResponse(billService.createBill(order));
            orderResponseDto.setMessage("Order added");
            orderResponseDto.setHttpStatus(HttpStatus.OK);
        }else{

            //order is not placed whether payment is not done
            orderResponseDto=new OrderResponseDto();
            orderResponseDto.setResponse(null);
            orderResponseDto.setMessage("Payment failed");
            orderResponseDto.setHttpStatus(HttpStatus.BAD_REQUEST);
        }


        }catch (Exception e){
            e.printStackTrace();
        }

        return orderResponseDto;
    }

    //this method return whether payment is done or not
    public boolean payment(){
        return new Random().nextBoolean();
        //return true;
    }
}
