package io.laptops.ui.orders;

import com.vaadin.navigator.View;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import io.laptops.entity.Customer;

public class OrdersView extends VerticalLayout implements View {
    public OrdersView() {
        Label label = new Label("Orders under cunstraction");
        addComponent(label);
    }
}
