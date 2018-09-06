import { browser } from 'protractor';
import { NavBarPage } from './../../page-objects/jhi-page-objects';
import { NotificationComponentsPage, NotificationUpdatePage } from './notification.page-object';

describe('Notification e2e test', () => {
    let navBarPage: NavBarPage;
    let notificationUpdatePage: NotificationUpdatePage;
    let notificationComponentsPage: NotificationComponentsPage;

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load Notifications', () => {
        navBarPage.goToEntity('notification');
        notificationComponentsPage = new NotificationComponentsPage();
        expect(notificationComponentsPage.getTitle()).toMatch(/Notifications/);
    });

    it('should load create Notification page', () => {
        notificationComponentsPage.clickOnCreateButton();
        notificationUpdatePage = new NotificationUpdatePage();
        expect(notificationUpdatePage.getPageTitle()).toMatch(/Create or edit a Notification/);
        notificationUpdatePage.cancel();
    });

    it('should create and save Notifications', () => {
        notificationComponentsPage.clickOnCreateButton();
        notificationUpdatePage.setMessageInput('message');
        expect(notificationUpdatePage.getMessageInput()).toMatch('message');
        notificationUpdatePage.employeeSelectLastOption();
        notificationUpdatePage.save();
        expect(notificationUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});
