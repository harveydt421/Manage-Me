import { element, by, promise, ElementFinder } from 'protractor';

export class DepartmentComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    title = element.all(by.css('jhi-department div h2#page-heading span')).first();

    clickOnCreateButton(): promise.Promise<void> {
        return this.createButton.click();
    }

    getTitle(): any {
        return this.title.getText();
    }
}

export class DepartmentUpdatePage {
    pageTitle = element(by.id('jhi-department-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    nameInput = element(by.id('field_name'));
    headSelect = element(by.id('field_head'));
    representativeSelect = element(by.id('field_representative'));

    getPageTitle() {
        return this.pageTitle.getText();
    }

    setNameInput(name): promise.Promise<void> {
        return this.nameInput.sendKeys(name);
    }

    getNameInput() {
        return this.nameInput.getAttribute('value');
    }

    headSelectLastOption(): promise.Promise<void> {
        return this.headSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    headSelectOption(option): promise.Promise<void> {
        return this.headSelect.sendKeys(option);
    }

    getHeadSelect(): ElementFinder {
        return this.headSelect;
    }

    getHeadSelectedOption() {
        return this.headSelect.element(by.css('option:checked')).getText();
    }

    representativeSelectLastOption(): promise.Promise<void> {
        return this.representativeSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    representativeSelectOption(option): promise.Promise<void> {
        return this.representativeSelect.sendKeys(option);
    }

    getRepresentativeSelect(): ElementFinder {
        return this.representativeSelect;
    }

    getRepresentativeSelectedOption() {
        return this.representativeSelect.element(by.css('option:checked')).getText();
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
