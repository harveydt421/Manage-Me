import { element, by, promise, ElementFinder } from 'protractor';

export class LocationComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    title = element.all(by.css('jhi-location div h2#page-heading span')).first();

    clickOnCreateButton(): promise.Promise<void> {
        return this.createButton.click();
    }

    getTitle(): any {
        return this.title.getText();
    }
}

export class LocationUpdatePage {
    pageTitle = element(by.id('jhi-location-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    streetAddressInput = element(by.id('field_streetAddress'));
    postalCodeInput = element(by.id('field_postalCode'));
    cityInput = element(by.id('field_city'));
    stateInput = element(by.id('field_state'));
    countryInput = element(by.id('field_country'));

    getPageTitle() {
        return this.pageTitle.getText();
    }

    setStreetAddressInput(streetAddress): promise.Promise<void> {
        return this.streetAddressInput.sendKeys(streetAddress);
    }

    getStreetAddressInput() {
        return this.streetAddressInput.getAttribute('value');
    }

    setPostalCodeInput(postalCode): promise.Promise<void> {
        return this.postalCodeInput.sendKeys(postalCode);
    }

    getPostalCodeInput() {
        return this.postalCodeInput.getAttribute('value');
    }

    setCityInput(city): promise.Promise<void> {
        return this.cityInput.sendKeys(city);
    }

    getCityInput() {
        return this.cityInput.getAttribute('value');
    }

    setStateInput(state): promise.Promise<void> {
        return this.stateInput.sendKeys(state);
    }

    getStateInput() {
        return this.stateInput.getAttribute('value');
    }

    setCountryInput(country): promise.Promise<void> {
        return this.countryInput.sendKeys(country);
    }

    getCountryInput() {
        return this.countryInput.getAttribute('value');
    }

    save(): promise.Promise<void> {
        return this.saveButton.click();
    }

    cancel(): promise.Promise<void> {
        return this.cancelButton.click();
    }

    getSaveButton(): ElementFinder {
        return this.saveButton;
    }
}
