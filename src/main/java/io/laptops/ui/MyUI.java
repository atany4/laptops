package io.laptops.ui;

import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.server.SpringVaadinServlet;
import com.vaadin.ui.*;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;
import io.laptops.services.CustomerService;
import io.laptops.ui.customers.CustomersView;
import io.laptops.ui.orders.OrdersView;
import io.laptops.ui.store.StoreView;
import io.laptops.ui.suppliers.SuppliersView;
import org.springframework.beans.factory.annotation.Autowired;
import com.vaadin.spring.annotation.EnableVaadin;
import com.vaadin.spring.annotation.SpringUI;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ContextLoaderListener;

@Theme("mytheme")
@SpringUI
@SuppressWarnings("serial")
public class MyUI extends UI {
    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends SpringVaadinServlet {
    }

    @WebListener
    public static class MyContextLoaderListener extends ContextLoaderListener {
    }

    @Configuration
    @EnableVaadin
    public static class MyConfiguration {
    }

    @Autowired
    public CustomerService customerService;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        CssLayout viewContainer = new CssLayout();
        viewContainer.setSizeFull();
        CssLayout sideMenu = new CssLayout();

        Navigator navigator = new Navigator(this, viewContainer);
        navigator.addView("", CustomersView.class);
        navigator.addView("store", StoreView.class);
        navigator.addView("users", CustomersView.class);
        navigator.addView("supliers", SuppliersView.class);
        navigator.addView("orders", OrdersView.class);

        Label title = new Label("Laptop SHOP");
        title.addStyleName(ValoTheme.MENU_TITLE);

        Button btnStore = new Button("Store", e -> getNavigator().navigateTo("store"));
        btnStore.addStyleNames(ValoTheme.BUTTON_LINK, ValoTheme.MENU_ITEM);
        btnStore.setIcon(VaadinIcons.TABLE);

        Button btnOrders = new Button("Orders", e -> getNavigator().navigateTo("orders"));
        btnOrders.addStyleNames(ValoTheme.BUTTON_LINK, ValoTheme.MENU_ITEM);
        btnOrders.setIcon(VaadinIcons.SHOP);

        Button btnUsers = new Button("Customers", e -> getNavigator().navigateTo("users"));
        btnUsers.addStyleNames(ValoTheme.BUTTON_LINK, ValoTheme.MENU_ITEM);
        btnUsers.setIcon(VaadinIcons.USERS);

        Button btnSupliers = new Button("Suppliers", e -> getNavigator().navigateTo("supliers"));
        btnSupliers.addStyleNames(ValoTheme.BUTTON_LINK, ValoTheme.MENU_ITEM);
        btnSupliers.setIcon(VaadinIcons.USERS);

        sideMenu.addComponent(title);
        sideMenu.addComponent(btnUsers);
        sideMenu.addComponent(btnOrders);
        sideMenu.addComponent(btnSupliers);
        sideMenu.addComponent(btnStore);
        sideMenu.addStyleName(ValoTheme.MENU_ROOT);

        HorizontalLayout mainLayout = new HorizontalLayout();
        mainLayout.setSizeFull();
        mainLayout.addComponent(sideMenu);
        mainLayout.addComponent(viewContainer);
        mainLayout.setExpandRatio(viewContainer, 1.0F);
        setContent(mainLayout);
    }
}
