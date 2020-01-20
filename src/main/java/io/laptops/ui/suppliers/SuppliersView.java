package io.laptops.ui.suppliers;

import com.vaadin.navigator.View;
import com.vaadin.ui.Composite;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class SuppliersView extends VerticalLayout implements View {
    public SuppliersView() {
        Label label = new Label("Suppliers under cunstraction");
        addComponent(label);
    }
}
