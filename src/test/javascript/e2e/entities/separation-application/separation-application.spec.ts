import { browser, protractor } from 'protractor';
import { NavBarPage } from './../../page-objects/jhi-page-objects';
import { SeparationApplicationComponentsPage, SeparationApplicationUpdatePage } from './separation-application.page-object';

describe('SeparationApplication e2e test', () => {
    let navBarPage: NavBarPage;
    let separationApplicationUpdatePage: SeparationApplicationUpdatePage;
    let separationApplicationComponentsPage: SeparationApplicationComponentsPage;

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load SeparationApplications', () => {
        navBarPage.goToEntity('separation-application');
        separationApplicationComponentsPage = new SeparationApplicationComponentsPage();
        expect(separationApplicationComponentsPage.getTitle()).toMatch(/Separation Applications/);
    });

    it('should load create SeparationApplication page', () => {
        separationApplicationComponentsPage.clickOnCreateButton();
        separationApplicationUpdatePage = new SeparationApplicationUpdatePage();
        expect(separationApplicationUpdatePage.getPageTitle()).toMatch(/Create or edit a Separation Application/);
        separationApplicationUpdatePage.cancel();
    });

    it('should create and save SeparationApplications', () => {
        separationApplicationComponentsPage.clickOnCreateButton();
        separationApplicationUpdatePage.setDateOfLeavingInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
        expect(separationApplicationUpdatePage.getDateOfLeavingInput()).toContain('2001-01-01T02:30');
        separationApplicationUpdatePage.setDateOfSubmissionInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
        expect(separationApplicationUpdatePage.getDateOfSubmissionInput()).toContain('2001-01-01T02:30');
        separationApplicationUpdatePage.setReasonForLeavingInput('reasonForLeaving');
        expect(separationApplicationUpdatePage.getReasonForLeavingInput()).toMatch('reasonForLeaving');
        separationApplicationUpdatePage
            .getCompletedInput()
            .isSelected()
            .then(selected => {
                if (selected) {
                    separationApplicationUpdatePage.getCompletedInput().click();
                    expect(separationApplicationUpdatePage.getCompletedInput().isSelected()).toBeFalsy();
                } else {
                    separationApplicationUpdatePage.getCompletedInput().click();
                    expect(separationApplicationUpdatePage.getCompletedInput().isSelected()).toBeTruthy();
                }
            });
        separationApplicationUpdatePage.employeeSelectLastOption();
        // separationApplicationUpdatePage.functionalRepresentativeSelectLastOption();
        separationApplicationUpdatePage.save();
        expect(separationApplicationUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});
