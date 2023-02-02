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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.querydsl.QSort;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
@Transactional
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
    void findList() {

        var targetProductNameContaining = "1";

        // Custom Repository find
        var oq = QOrder.order;
        var cq = QCustomer.customer;

        var predicate = new BooleanBuilder();
        predicate.and(oq.product.name.contains(targetProductNameContaining));

        var list1 = orderRepository.findList(predicate, query -> query.leftJoin(cq).on(cq.id.eq(oq.customerId)), null);

        Assertions.assertTrue(list1.stream().allMatch(order -> order.getProduct().getName().contains(targetProductNameContaining)));

        // Spring Data JPA Repository find
        var list2 = orderRepository.findByProductNameContaining(targetProductNameContaining);

        list1.sort(Comparator.comparing(Order::getName));
        list2.sort(Comparator.comparing(Order::getName));

        for (var i = 0 ; i < list1.size(); i ++) {
            var item1 = list1.get(i);
            var item2 = list2.get(i);

            Assertions.assertEquals(item1.getId(), item2.getId());
            Assertions.assertEquals(item1.getName(), item2.getName());
            Assertions.assertEquals(item1.getProduct().getPrice(), item2.getProduct().getPrice());
            Assertions.assertEquals(item1.getProduct().getName(), item2.getProduct().getName());
        }
    }

    @Test
    void findPage() {

        var targetOrderNameContaining = "1";

        var oq = QOrder.order;
        var cq = QCustomer.customer;

        var predicate = new BooleanBuilder();
        predicate.and(oq.name.contains(targetOrderNameContaining));

        var page = 0;
        var number = 0;
        while(true) {
            var pageable = PageRequest.of(page, 3, new QSort(oq.product.price.desc(), oq.name.desc()));

            var list1 = orderRepository.findPage(
                    predicate,
                    query -> query.leftJoin(cq).on(cq.id.eq(oq.customerId)),
                    pageable
            );

            var list2 = orderRepository.findByNameContaining(targetOrderNameContaining, pageable);

            var contents1 = list1.getContent();
            var contents2 = list2.getContent();

            for (var i = 0; i < contents1.size(); i ++) {
                var item1 = contents1.get(i);
                var item2 = contents2.get(i);

                Assertions.assertEquals(item1.getId(), item2.getId());
                Assertions.assertEquals(item1.getName(), item2.getName());
                Assertions.assertEquals(item1.getProduct().getPrice(), item2.getProduct().getPrice());
                Assertions.assertEquals(item1.getProduct().getName(), item2.getProduct().getName());
            }

            number += list1.getNumberOfElements();

            if (number >= list1.getTotalElements()) break;

            page ++;
        }
    }

    @Test
    void selectList() {

        var targetProductPrice = 3000L;

        // Custom Repository select
        var oq = QOrder.order;
        var cq = QCustomer.customer;

        var predicate = new BooleanBuilder();
        predicate.and(oq.product.price.gt(targetProductPrice));

        var list1 = orderRepository.selectList(
                predicate,
                OrderDTO.class,
                this.buildBindings(),
                query -> query.leftJoin(cq).on(cq.id.eq(oq.customerId)),
                new QSort(oq.name.desc())
        );

        Assertions.assertTrue(list1.stream().allMatch(order -> order.getProductPrice() > targetProductPrice));

        // Spring Data JPA Repository select
        var list2 = orderRepository.findByProductPriceGreaterThanOrderByNameDesc(targetProductPrice);

        for (var i = 0; i < list1.size(); i ++) {
            var item1 = list1.get(i);
            var item2 = list2.get(i);

            Assertions.assertEquals(item1.getId(), item2.getId());
            Assertions.assertEquals(item1.getName(), item2.getName());
            Assertions.assertEquals(item1.getProductPrice(), item2.getProduct().getPrice());
            Assertions.assertEquals(item1.getProductName(), item2.getProduct().getName());
        }
    }

    @Test
    void selectPage() {
        var targetProductPrice = 5000L;

        var oq = QOrder.order;
        var cq = QCustomer.customer;

        var predicate = new BooleanBuilder();
        predicate.and(oq.product.price.gt(targetProductPrice));

        var page = 0;
        var number = 0;
        while(true) {
            var pageable = PageRequest.of(page, 3, new QSort(oq.product.price.desc(), oq.name.desc()));

            var list1 = orderRepository.selectPage(
                    predicate,
                    OrderDTO.class,
                    this.buildBindings(),
                    query -> query.leftJoin(cq).on(cq.id.eq(oq.customerId)),
                    pageable
            );

            var list2 = orderRepository.findByProductPriceGreaterThan(targetProductPrice, pageable);

            var contents1 = list1.getContent();
            var contents2 = list2.getContent();

            for (var i = 0; i < contents1.size(); i ++) {
                var item1 = contents1.get(i);
                var item2 = contents2.get(i);

                Assertions.assertEquals(item1.getId(), item2.getId());
                Assertions.assertEquals(item1.getName(), item2.getName());
                Assertions.assertEquals(item1.getProductPrice(), item2.getProduct().getPrice());
                Assertions.assertEquals(item1.getProductName(), item2.getProduct().getName());
            }

            number += list1.getNumberOfElements();

            if (number >= list1.getTotalElements()) break;

            page ++;
        }
    }

}
