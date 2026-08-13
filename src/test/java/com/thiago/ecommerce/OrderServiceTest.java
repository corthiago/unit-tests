package com.thiago.ecommerce;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository repository;

    @Captor
    private ArgumentCaptor<Order> orderArgumentCaptor;

    @InjectMocks
    private OrderService orderService;

    @Nested
    class placeOrder{

        @Test
        @DisplayName("Should place order with success")
        void shouldPlaceOrderWithSuccess(){
            var dummyOrder = new Order(1, "Thiago", 200.0);

            orderService.placeOrder(dummyOrder);

            verify(repository, times(1)).save(orderArgumentCaptor.capture());
            var orderCaptured = orderArgumentCaptor.getValue();
            assertEquals(dummyOrder, orderCaptured);
        }

        @ParameterizedTest
        @ValueSource(doubles = {0, -2.0, -50.0})
        @DisplayName("Should throw exception when order total is bellow or equals zero")
        void shouldThrowExceptionWhenTotalIsBellowOrEqualsZero(double total){
            var dummyOrder = new Order(1, "Thiago", total);

            assertThrows(IllegalArgumentException.class, () -> {
                orderService.placeOrder(dummyOrder);
            });

            verify(repository, times(0)).save(any());
        }

        @Test
        @DisplayName("Should throw exception when place order")
        void shouldThrowExceptionWhenPlaceOrder(){
            var dummyOrder = new Order(1, "Thiago", 200.0);
            doThrow(RuntimeException.class).when(repository).save(any());

            assertThrows(RuntimeException.class, () -> {
                orderService.placeOrder(dummyOrder);
            });
        }

    }

    @Nested
    class getOrder {

        @Test
        @DisplayName("Should return order when exists")
        void shouldReturnOrderWhenExists(){
            var orderId = 1;
            var dummyOrder = new Order(1, "Thiago", 300.0);
            doReturn(dummyOrder).when(repository).findById(eq(orderId));

            var order = orderService.getOrder(orderId);

            assertNotNull(order);
            assertEquals(dummyOrder.getId(), order.getId());
            assertEquals(dummyOrder.getCustomer(), order.getCustomer());
            assertEquals(dummyOrder.getTotal(), order.getTotal());
            verify(repository, times(1)).findById(eq(orderId));
        }

        @Test
        @DisplayName("Should return null when order does not exists")
        void shouldReturnNullWhenOrderDoesNotExists(){
            var orderId = 1;
            doReturn(null).when(repository).findById(eq(orderId));

            var order = orderService.getOrder(orderId);

            assertNull(order);
            verify(repository, times(1)).findById(eq(orderId));
        }

    }

}