import { browser } from 'protractor';
import { NavBarPage } from './../../page-objects/jhi-page-objects';
import { LineItemComponentsPage, LineItemUpdatePage } from './line-item.page-object';

describe('LineItem e2e test', () => {
    let navBarPage: NavBarPage;
    let lineItemUpdatePage: LineItemUpdatePage;
    let lineItemComponentsPage: LineItemComponentsPage;

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load LineItems', () => {
        navBarPage.goToEntity('line-item');
        lineItemComponentsPage = new LineItemComponentsPage();
        expect(lineItemComponentsPage.getTitle()).toMatch(/Line Items/);
    });

    it('should load create LineItem page', () => {
        lineItemComponentsPage.clickOnCreateButton();
        lineItemUpdatePage = new LineItemUpdatePage();
        expect(lineItemUpdatePage.getPageTitle()).toMatch(/Create or edit a Line Item/);
        lineItemUpdatePage.cancel();
    });

    it('should create and save LineItems', () => {
        lineItemComponentsPage.clickOnCreateButton();
        lineItemUpdatePage.setFeedbackInput('feedback');
        expect(lineItemUpdatePage.getFeedbackInput()).toMatch('feedback');
        lineItemUpdatePage.assetOwedSelectLastOption();
        lineItemUpdatePage.save();
        expect(lineItemUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});
