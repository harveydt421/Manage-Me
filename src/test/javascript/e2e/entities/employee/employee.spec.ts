import { browser } from 'protractor';
import { NavBarPage } from './../../page-objects/jhi-page-objects';
import { EmployeeComponentsPage, EmployeeUpdatePage } from './employee.page-object';

describe('Employee e2e test', () => {
    let navBarPage: NavBarPage;
    let employeeUpdatePage: EmployeeUpdatePage;
    let employeeComponentsPage: EmployeeComponentsPage;

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load Employees', () => {
        navBarPage.goToEntity('employee');
        employeeComponentsPage = new EmployeeComponentsPage();
        expect(employeeComponentsPage.getTitle()).toMatch(/Employees/);
    });

    it('should load create Employee page', () => {
        employeeComponentsPage.clickOnCreateButton();
        employeeUpdatePage = new EmployeeUpdatePage();
        expect(employeeUpdatePage.getPageTitle()).toMatch(/Create or edit a Employee/);
        employeeUpdatePage.cancel();
    });

    it('should create and save Employees', () => {
        employeeComponentsPage.clickOnCreateButton();
        employeeUpdatePage.setFirstNameInput('firstName');
        expect(employeeUpdatePage.getFirstNameInput()).toMatch('firstName');
        employeeUpdatePage.setLastNameInput('lastName');
        expect(employeeUpdatePage.getLastNameInput()).toMatch('lastName');
        employeeUpdatePage.setEmailInput('email');
        expect(employeeUpdatePage.getEmailInput()).toMatch('email');
        employeeUpdatePage.setPhoneNumberInput('phoneNumber');
        expect(employeeUpdatePage.getPhoneNumberInput()).toMatch('phoneNumber');
        employeeUpdatePage.userSelectLastOption();
        employeeUpdatePage.save();
        expect(employeeUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});
