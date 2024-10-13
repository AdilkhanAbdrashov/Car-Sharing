package controller.console.manager;

import businesslayer.Company;
import controller.console.ConsoleMainMenu;
import controller.console.ConsoleMenu;
import controller.console.SysoutScannerContainer;
import persistance.repository.JdbcCompanyRepository;

import java.util.Map;

public class ConsoleManagerMenu extends ConsoleMenu {

    private static final String[] MENU_OPTIONS_LIST = {
            "1. Company list",
            "2. Create a company",
            "0. Back"
    };

    public ConsoleManagerMenu() {
        super(MENU_OPTIONS_LIST, Map.of(
                0, ConsoleMainMenu::new,
                1, ConsoleCompanySelectionMenu::getConsoleCompanyListMenu,
                2, ConsoleManagerMenu::handleCreateCompanyOption
        ));
    }

    private static ConsoleManagerMenu handleCreateCompanyOption() {
        var company = new Company();
        company.setName(askCompanyName());
        JdbcCompanyRepository.getInstance().createCompany(company);
        return new ConsoleManagerMenu();
    }

    private static String askCompanyName() {
        System.out.println("Enter the company name:");
        var companyName = SysoutScannerContainer.getScanner().nextLine().trim();
        System.out.println();
        return companyName;
    }
}
