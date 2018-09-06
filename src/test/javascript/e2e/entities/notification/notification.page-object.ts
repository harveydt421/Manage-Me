import { element, by, promise, ElementFinder } from 'protractor';

export class NotificationComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    title = element.all(by.css('jhi-notification div h2#page-heading span')).first();

    clickOnCreateButton(): promise.Promise<void> {
        return this.createButton.click();
    }

    getTitle(): any {
        return this.title.getText();
    }
}

export class NotificationUpdatePage {
    pageTitle = element(by.id('jhi-notification-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    messageInput = element(by.id('field_message'));
    employeeSelect = element(by.id('field_employee'));

    getPageTitle() {
        return this.pageTitle.getText();
    }

    setMessageInput(message): promise.Promise<void> {
        return this.messageInput.sendKeys(message);
    }

    getMessageInput() {
        return this.messageInput.getAttribute('value');
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
