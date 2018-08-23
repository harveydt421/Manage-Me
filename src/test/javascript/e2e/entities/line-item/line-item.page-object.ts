import { element, by, promise, ElementFinder } from 'protractor';

export class LineItemComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    title = element.all(by.css('jhi-line-item div h2#page-heading span')).first();

    clickOnCreateButton(): promise.Promise<void> {
        return this.createButton.click();
    }

    getTitle(): any {
        return this.title.getText();
    }
}

export class LineItemUpdatePage {
    pageTitle = element(by.id('jhi-line-item-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    feedbackInput = element(by.id('field_feedback'));
    assetOwedSelect = element(by.id('field_assetOwed'));
    separationApplicationSelect = element(by.id('field_separationApplication'));

    getPageTitle() {
        return this.pageTitle.getText();
    }

    setFeedbackInput(feedback): promise.Promise<void> {
        return this.feedbackInput.sendKeys(feedback);
    }

    getFeedbackInput() {
        return this.feedbackInput.getAttribute('value');
    }

    assetOwedSelectLastOption(): promise.Promise<void> {
        return this.assetOwedSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    assetOwedSelectOption(option): promise.Promise<void> {
        return this.assetOwedSelect.sendKeys(option);
    }

    getAssetOwedSelect(): ElementFinder {
        return this.assetOwedSelect;
    }

    getAssetOwedSelectedOption() {
        return this.assetOwedSelect.element(by.css('option:checked')).getText();
    }

    separationApplicationSelectLastOption(): promise.Promise<void> {
        return this.separationApplicationSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    separationApplicationSelectOption(option): promise.Promise<void> {
        return this.separationApplicationSelect.sendKeys(option);
    }

    getSeparationApplicationSelect(): ElementFinder {
        return this.separationApplicationSelect;
    }

    getSeparationApplicationSelectedOption() {
        return this.separationApplicationSelect.element(by.css('option:checked')).getText();
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
