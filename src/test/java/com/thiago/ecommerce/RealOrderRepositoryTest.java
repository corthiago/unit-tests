package com.thiago.ecommerce;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RealOrderRepositoryTest {

    @Spy
    private HashMap<Integer, Order> ordersSpy;

    @Captor
    private ArgumentCaptor<Order> orderArgumentCaptor;

    @InjectMocks
    private RealOrderRepository realOrderRepository;

    @Nested
    class save {
        @Test
        @DisplayName("Should save order")
        void shouldSaveOrder(){
            // Arrange
            var dummyOrder = new Order(1, "Thiago", 4000.0);

            // Act
            realOrderRepository.save(dummyOrder);

            // Assert
            verify(ordersSpy, times(1)).put(eq(dummyOrder.getId()), orderArgumentCaptor.capture());
            var orderCaptured = orderArgumentCaptor.getValue();
            assertSame(dummyOrder, orderCaptured);
        }
    }

    @Nested
    class findById{
        @Test
        @DisplayName("Should find by id when order exists")
        void shouldFindByIdWhenOrderExists(){
            int id = 1;
            var dummyOrder = new Order(1, "Thiago", 4000.0);
            doReturn(dummyOrder).when(ordersSpy).get(eq(1)); // stub

            var order = realOrderRepository.findById(id);

            assertNotNull(order);
            assertEquals(dummyOrder, order);
            verify(ordersSpy, times(1)).get(eq(1));
        }

        @Test
        @DisplayName("Should return null when order does not exist")
        void shouldReturnNullWhenOrderDoesNotExist(){
            var id = 1;
            doReturn(null).when(ordersSpy).get(eq(id));

            var order = realOrderRepository.findById(1);

            assertNull(order);
            verify(ordersSpy, times(1)).get(eq(1));
        }
    }

}