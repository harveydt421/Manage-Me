import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';
import { ISeparationApplication } from 'app/shared/model/separation-application.model';
import { Principal } from 'app/core';
import { SeparationApplicationService } from 'app/entities/separation-application';
import { LineItemService } from 'app/entities/line-item/line-item.service';
import { ILineItem } from 'app/shared/model/line-item.model';

@Component({
    selector: 'jhi-functional-representative',
    templateUrl: './functional-representative.component.html',
    styles: []
})
export class FunctionalRepresentativeComponent implements OnInit, OnDestroy {
    separationApplications: ISeparationApplication[];
    currentAccount: any;
    eventSubscriber: Subscription;
    lineItems: ILineItem[];

    constructor(
        private separationApplicationService: SeparationApplicationService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal,
        private lineItemService: LineItemService
    ) {}

    loadAll() {
        this.separationApplicationService.query().subscribe(
            (res: HttpResponse<ISeparationApplication[]>) => {
                this.separationApplications = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    getLineItem() {
        this.lineItemService.query().subscribe(
        (res: HttpResponse<ISeparationApplication[]>) => {
                this.lineItems = res.body;
        },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInSeparationApplications();
        this.getLineItem();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ISeparationApplication) {
        return item.id;
    }

    registerChangeInSeparationApplications() {
        this.eventSubscriber = this.eventManager.subscribe('separationApplicationListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
