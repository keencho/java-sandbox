package com.keencho.application;

import com.keencho.application.dto.OrderDTO;
import com.keencho.application.model.*;
import com.keencho.application.repository.CustomerRepository;
import com.keencho.application.repository.OrderRepository;
import com.keencho.application.repository.ProductRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Expression;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.querydsl.QSort;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
class CustomJpaQueryDSLRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    @BeforeAll
    public void setup() {
        var num = 100;
        var rand = new Random();
        var customerList = new ArrayList<Customer>();
        var productList = new ArrayList<Product>();

        IntStream.range(1, num + 1).forEach(idx -> {
            var customer = new Customer();
            customer.setName("고객" + idx);
            customer.setGender(idx % 2 == 0 ? Gender.MAN : Gender.WOMAN);
            customer.setAge(idx / 2);

            customerList.add(customerRepository.save(customer));
        });

        IntStream.range(1, num + 1).forEach(idx -> {
            var product = new Product();
            product.setName("상품" + idx);
            product.setPrice(ThreadLocalRandom.current().nextInt(1, 11) * 1000L);

            productList.add(productRepository.save(product));
        });

        IntStream.range(1, num + 1).forEach(idx -> {
            var order = new Order();
            order.setName("주문" + idx);
            order.setProduct(productList.get(rand.nextInt(productList.size())));
            order.setCustomerId(customerList.get(rand.nextInt(productList.size())).getId());

            orderRepository.save(order);
        });
    }

    private Map<String, Expression<?>> buildBindings() {
        var oq = QOrder.order;
        var cq = QCustomer.customer;
        var bindings = new HashMap<String, Expression<?>>();

        bindings.put("id", oq.id);
        bindings.put("name", oq.name);
        bindings.put("dtCreatedAt", oq.dtCreatedAt);

        bindings.put("productId", oq.product.id);
        bindings.put("productName", oq.product.name);
        bindings.put("productPrice", oq.product.price);

        bindings.put("customerId", cq.id);
        bindings.put("customerName", cq.name);
        bindings.put("customerAge", cq.age);
        bindings.put("customerGender", cq.gender);

        return bindings;
    }

    @Test
    @Transactional
    void selectList() {
        // Custom Repository select
        var oq = QOrder.order;
        var cq = QCustomer.customer;

        var predicate = new BooleanBuilder();
        predicate.and(oq.product.price.gt(3000L));

        var customRepositorySelectList = orderRepository.selectList(
                predicate,
                OrderDTO.class,
                this.buildBindings(),
                query -> query.leftJoin(cq).on(cq.id.eq(oq.customerId)),
                new QSort(oq.name.desc())
        );

        Assertions.assertTrue(customRepositorySelectList.stream().allMatch(order -> order.getProductPrice() > 3000L));

        // Spring Data JPA Repository select
        var jpaRepositorySelectList = orderRepository.findByProductPriceGreaterThanOrderByNameDesc(3000L);

        for (var i = 0; i < customRepositorySelectList.size(); i ++) {
            var customRepositoryItem = customRepositorySelectList.get(i);
            var jpaRepositoryItem = jpaRepositorySelectList.get(i);

            Assertions.assertEquals(customRepositoryItem.getId(), jpaRepositoryItem.getId());
            Assertions.assertEquals(customRepositoryItem.getName(), jpaRepositoryItem.getName());
            Assertions.assertEquals(customRepositoryItem.getProductPrice(), jpaRepositoryItem.getProduct().getPrice());
            Assertions.assertEquals(customRepositoryItem.getProductName(), jpaRepositoryItem.getProduct().getName());
        }
    }

}
