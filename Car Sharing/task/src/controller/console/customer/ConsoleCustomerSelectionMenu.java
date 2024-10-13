package controller.console.customer;

import controller.AppState;
import controller.console.ConsoleMainMenu;
import controller.console.ConsoleMenu;
import controller.console.ConsoleUtils;
import persistance.repository.JdbcCustomerRepository;

import java.util.AbstractMap;
import java.util.Map;

public class ConsoleCustomerSelectionMenu extends ConsoleMenu {

    public static AppState getConsoleCustomerListMenu() {
        final var customers = JdbcCustomerRepository.getInstance().readCustomers();

        if (customers.isEmpty()) {
            return () -> {
                System.out.println("The customer list is empty!");
                System.out.println();
                return new ConsoleMainMenu();
            };
        }

        final var menuOptionsList = ConsoleUtils.generateMenuOptionsList(
                customers,
                (idx) -> customers.get(idx).getName(),
                "0. Back"
        );
        final var menuOptionTransitions = ConsoleUtils.generateMenuOptionTransitions(
                customers,
                idx -> new ConsoleCustomerMenu(customers.get(idx)),
                new AbstractMap.SimpleImmutableEntry<Integer, AppState>(0, ConsoleMainMenu::new)
        );
        return new ConsoleCustomerSelectionMenu(menuOptionsList, menuOptionTransitions);
    }

    protected ConsoleCustomerSelectionMenu(String[] menuOptionsList, Map<Integer, AppState> menuOptionTransitions) {
        super(menuOptionsList, menuOptionTransitions);
    }

    @Override
    public void showMenu() {
        System.out.println("Customer list:");
        super.showMenu();
    }
}
