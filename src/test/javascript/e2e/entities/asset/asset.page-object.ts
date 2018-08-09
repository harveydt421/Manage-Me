import { element, by, promise, ElementFinder } from 'protractor';

export class AssetComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    title = element.all(by.css('jhi-asset div h2#page-heading span')).first();

    clickOnCreateButton(): promise.Promise<void> {
        return this.createButton.click();
    }

    getTitle(): any {
        return this.title.getText();
    }
}

export class AssetUpdatePage {
    pageTitle = element(by.id('jhi-asset-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    nameInput = element(by.id('field_name'));
    valueInput = element(by.id('field_value'));
    employeeSelect = element(by.id('field_employee'));

    getPageTitle() {
        return this.pageTitle.getText();
    }

    setNameInput(name): promise.Promise<void> {
        return this.nameInput.sendKeys(name);
    }

    getNameInput() {
        return this.nameInput.getAttribute('value');
    }

    setValueInput(value): promise.Promise<void> {
        return this.valueInput.sendKeys(value);
    }

    getValueInput() {
        return this.valueInput.getAttribute('value');
    }

    employeeSelectLastOption(): promise.Promise<void> {
        return this.employeeSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    employeeSelectOption(option): promise.Promise<void> {
        return this.employeeSelect.sendKeys(option);
    }

    getEmployeeSelect(): ElementFinder {
        return this.employeeSelect;
    }

    getEmployeeSelectedOption() {
        return this.employeeSelect.element(by.css('option:checked')).getText();
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
