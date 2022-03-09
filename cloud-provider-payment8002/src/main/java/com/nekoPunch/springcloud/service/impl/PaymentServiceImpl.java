package com.nekoPunch.springcloud.service.impl;

import com.nekoPunch.springcloud.dao.PaymentDao;
import com.nekoPunch.springcloud.entities.Payment;
import com.nekoPunch.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;
    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}