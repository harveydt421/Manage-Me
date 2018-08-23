import { element, by, promise, ElementFinder } from 'protractor';

export class SeparationApplicationComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    title = element.all(by.css('jhi-separation-application div h2#page-heading span')).first();

    clickOnCreateButton(): promise.Promise<void> {
        return this.createButton.click();
    }

    getTitle(): any {
        return this.title.getText();
    }
}

export class SeparationApplicationUpdatePage {
    pageTitle = element(by.id('jhi-separation-application-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    dateOfLeavingInput = element(by.id('field_dateOfLeaving'));
    dateOfSubmissionInput = element(by.id('field_dateOfSubmission'));
    reasonForLeavingInput = element(by.id('field_reasonForLeaving'));
    completedInput = element(by.id('field_completed'));
    employeeSelect = element(by.id('field_employee'));

    getPageTitle() {
        return this.pageTitle.getText();
    }

    setDateOfLeavingInput(dateOfLeaving): promise.Promise<void> {
        return this.dateOfLeavingInput.sendKeys(dateOfLeaving);
    }

    getDateOfLeavingInput() {
        return this.dateOfLeavingInput.getAttribute('value');
    }

    setDateOfSubmissionInput(dateOfSubmission): promise.Promise<void> {
        return this.dateOfSubmissionInput.sendKeys(dateOfSubmission);
    }

    getDateOfSubmissionInput() {
        return this.dateOfSubmissionInput.getAttribute('value');
    }

    setReasonForLeavingInput(reasonForLeaving): promise.Promise<void> {
        return this.reasonForLeavingInput.sendKeys(reasonForLeaving);
    }

    getReasonForLeavingInput() {
        return this.reasonForLeavingInput.getAttribute('value');
    }

    getCompletedInput() {
        return this.completedInput;
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
