package com.example.querydsl.repository;



import com.example.querydsl.dto.CustomerDto;
import com.example.querydsl.entity.QCustomer;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.querydsl.entity.QCustomer.customer;

@Repository
@RequiredArgsConstructor
public class CustomerRepository {

    private final JPAQueryFactory jpaQueryFactory;

    QCustomer qcustomer = customer;

    public List<CustomerDto> getCustomerResult(){
        // TO-DO
        return null;
    }
}
