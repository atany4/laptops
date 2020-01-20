package io.laptops.ui.store;

import com.vaadin.navigator.View;
import com.vaadin.ui.Composite;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class StoreView extends VerticalLayout implements View {
    public StoreView() {
        Label label = new Label("Store under cunstraction");
        addComponent(label);
    }
}
