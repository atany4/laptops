package io.laptops.ui.customers;

import com.sun.org.apache.bcel.internal.generic.ARETURN;
import com.vaadin.data.Binder;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.navigator.View;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.*;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;
import com.vaadin.ui.components.grid.HeaderRow;
import io.laptops.dao.CustomerDao;
import io.laptops.dao.CustomerDaoImpl;
import io.laptops.entity.Customer;
import io.laptops.services.CustomerService;
import io.laptops.services.CustomerServiceImpl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class CustomersView extends VerticalLayout implements View {
    CustomerService customerService = new CustomerServiceImpl();
    private Grid<Customer> mainGrid = new Grid<>();

    public CustomersView() {
        MenuBar.Command newUser = selectedItem -> {
            final Window window = new Window("Window");
            window.setWidth(350.0f, Sizeable.Unit.PIXELS);
            window.setModal(true);
            FormLayout formLayout = new FormLayout();

            TextField tfFullName = new TextField("Full Name");
            TextField tfLogin = new TextField("Login");
            TextField tfEmail = new TextField("Email");
            TextField tfPhoneNumber = new TextField("Phone Number");

            HorizontalLayout buttons = new HorizontalLayout();
            Component buttonSave = new Button("Save");
            buttonSave.addListener(event -> {
                Customer customer = new Customer();
                customer.setLogin((tfLogin.getValue()));
                customer.setFullName(tfFullName.getValue());
                customer.setEmail(tfEmail.getValue());
                customer.setPhoneNumber(tfPhoneNumber.getValue());
                customerService.create(customer);
                updateGrid();
                window.close();
            });

            Component buttonClose = new Button("Close");
            buttonClose.addListener(event -> window.close());

            buttons.addComponents(buttonSave, buttonClose);
            formLayout.addComponents(tfFullName, tfLogin, tfEmail, tfPhoneNumber);
            formLayout.addComponents(buttons);
            formLayout.setMargin(true);
            window.setContent(formLayout);
            getUI().getUI().addWindow(window);
        };

        MenuBar.Command editUser = selectedItem -> {
            final Window window = new Window("Window");
            window.setWidth(350.0f, Sizeable.Unit.PIXELS);
            window.setModal(true);
            FormLayout formLayout = new FormLayout();

            TextField tfId = new TextField();
            tfId.setVisible(false);
            TextField tfFullName = new TextField("Full Name");
            TextField tfLogin = new TextField("Login");
            TextField tfEmail = new TextField("Email");
            TextField tfPhoneNumber = new TextField("Phone Number");

            Optional<Customer> selectedCustomer = mainGrid.getSelectionModel().getFirstSelectedItem();
            if (selectedCustomer.isPresent()) {
                tfId.setValue(String.valueOf(selectedCustomer.get().getId()));
                tfFullName.setValue(selectedCustomer.get().getFullName());
                tfLogin.setValue(selectedCustomer.get().getLogin());
                tfEmail.setValue(selectedCustomer.get().getEmail());
                tfPhoneNumber.setValue(selectedCustomer.get().getPhoneNumber());
            }

            HorizontalLayout buttons = new HorizontalLayout();
            Component buttonSave = new Button("Save");
            buttonSave.addListener(event -> {
                Customer customer = customerService.read(Long.parseLong(tfId.getValue()));
                customer.setLogin((tfLogin.getValue()));
                customer.setFullName(tfFullName.getValue());
                customer.setEmail(tfEmail.getValue());
                customer.setPhoneNumber(tfPhoneNumber.getValue());
                customerService.update(customer);
                updateGrid();
                window.close();
            });

            Component buttonClose = new Button("Close");
            buttonClose.addListener(event -> window.close());

            buttons.addComponents(buttonClose, buttonSave);
            formLayout.addComponents(tfFullName, tfLogin, tfEmail, tfPhoneNumber);
            formLayout.addComponents(buttons);
            formLayout.setMargin(true);
            window.setContent(formLayout);
            getUI().getUI().addWindow(window);
        };

        MenuBar.Command deleteUser = selectedItem -> {
            Optional<Customer> blameCustomer = mainGrid.getSelectionModel().getFirstSelectedItem();
            if (blameCustomer.isPresent()) {
                customerService.delete(blameCustomer.get());
                updateGrid();
            }
        };

        MenuBar menuBar = new MenuBar();
        menuBar.addItem("Add New User", newUser);
        menuBar.addItem("Edit Selected User", editUser);
        menuBar.addItem("Delete Selected User", deleteUser);

//        customerDao.close();
//        FactoryManager.close();
        mainGrid.setSelectionMode(Grid.SelectionMode.SINGLE);
        mainGrid.setCaption("Cutomers");
        mainGrid.setWidth(100, Unit.PERCENTAGE);
        mainGrid.addColumn(Customer::getId).setCaption("Id");
        mainGrid.addColumn(Customer::getFullName).setCaption("Full Name");
        mainGrid.addColumn(Customer::getLogin).setCaption("Login");
        mainGrid.addColumn(Customer::getEmail).setCaption("Email");
        mainGrid.addColumn(Customer::getPhoneNumber).setCaption("Phone Number");
        updateGrid();

        setSizeFull();
        setMargin(false);
        setSpacing(false);

        TextField filter = new TextField("Filter by ID");
        filter.addValueChangeListener(e -> {
            List<Customer> customers = null;
            if (Objects.requireNonNull(filter.getValue()).isEmpty()) {
                customers = customerService.getAll();

            } else {
                customers = customerService.findById(Long.parseLong(filter.getValue()));
            }
            mainGrid.setItems(customers);
            mainGrid.getDataProvider().refreshAll();
        });


        addComponents(menuBar, filter, mainGrid);
        setComponentAlignment(menuBar, Alignment.MIDDLE_LEFT);
        setExpandRatio(menuBar, 0.05f);
        setExpandRatio(mainGrid, 0.8f);

// TEST
//        System.out.println("Creating new user sequence is started:");
//        Customer customer = new Customer();
//        customer.setFullName("Ivan Ivanovich Ivanov");
//        customer.setLogin("ivan");
//        customer.setEmail("ivanovII@m.com");
//        customer.setPhoneNumber("+380501112233");
//
//        CustomerDao customerDao = new CustomerDaoImpl();
//        customerDao.create(customer);
//        System.out.printf("Customer id is: %s\n",customer.getId());
//        System.out.println("Creating new user sequence is complete:");
//
//        List<Customer> customers = customerDao.getAll();
//        customers.forEach(System.out::println);

//        System.out.println("Readinguser from DB is started:");
//        Customer ivan = customerDao.read(1);
//
//        System.out.println("Reading complete:");
//        System.out.println("Updating user in DB is started:");
//        ivan.setEmail("ivan.ivanov@mail.ua");
//        customerDao.update(ivan);
//        System.out.println("Updating is complete:");
//
//        System.out.println("Delete is start:");
//        customerDao.delete(customer);
//        ivan = null;
//        System.out.println("Deleting is complete:");

//        System.out.println(ivan);
//        customerDao.close();
//        FactoryManager.close();
    }

    private void updateGrid() {
        List<Customer> customers = customerService.getAll();
        mainGrid.setItems(customers);
        mainGrid.getDataProvider().refreshAll();
    }
}
