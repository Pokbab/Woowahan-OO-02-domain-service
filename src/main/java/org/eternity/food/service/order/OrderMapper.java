package org.eternity.food.service.order;

import org.eternity.food.domain.order.Order;
import org.eternity.food.domain.order.OrderLineItem;
import org.eternity.food.domain.order.OrderOption;
import org.eternity.food.domain.order.OrderOptionGroup;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Component
public class OrderMapper {
    public Order mapFrom(Cart cart) {
        return new Order(
                cart.getUserId(),
                cart.getShopId(),
                cart.getCartLineItems()
                        .stream()
                        .map(this::toOrderLineItem)
                        .collect(toList()));
    }

    private OrderLineItem toOrderLineItem(Cart.CartLineItem cartLineItem) {
        return new OrderLineItem(
                cartLineItem.getMenuId(),
                cartLineItem.getName(),
                cartLineItem.getCount(),
                cartLineItem.getGroups()
                        .stream()
                        .map(this::toOrderOptionGroup)
                        .collect(Collectors.toList()));
    }

    private OrderOptionGroup toOrderOptionGroup(Cart.CartOptionGroup cartOptionGroup) {
        return new OrderOptionGroup(
                cartOptionGroup.getName(),
                cartOptionGroup.getOptions()
                        .stream()
                        .map(this::toOrderOption)
                        .collect(Collectors.toList()));
    }

    private OrderOption toOrderOption(Cart.CartOption cartOption) {
        return new OrderOption(
                cartOption.getName(),
                cartOption.getPrice());
    }
}
