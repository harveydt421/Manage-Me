import { browser } from 'protractor';
import { NavBarPage } from './../../page-objects/jhi-page-objects';
import { AssetComponentsPage, AssetUpdatePage } from './asset.page-object';

describe('Asset e2e test', () => {
    let navBarPage: NavBarPage;
    let assetUpdatePage: AssetUpdatePage;
    let assetComponentsPage: AssetComponentsPage;

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load Assets', () => {
        navBarPage.goToEntity('asset');
        assetComponentsPage = new AssetComponentsPage();
        expect(assetComponentsPage.getTitle()).toMatch(/Assets/);
    });

    it('should load create Asset page', () => {
        assetComponentsPage.clickOnCreateButton();
        assetUpdatePage = new AssetUpdatePage();
        expect(assetUpdatePage.getPageTitle()).toMatch(/Create or edit a Asset/);
        assetUpdatePage.cancel();
    });

    it('should create and save Assets', () => {
        assetComponentsPage.clickOnCreateButton();
        assetUpdatePage.setNameInput('name');
        expect(assetUpdatePage.getNameInput()).toMatch('name');
        assetUpdatePage.setValueInput('5');
        expect(assetUpdatePage.getValueInput()).toMatch('5');
        assetUpdatePage.save();
        expect(assetUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});
