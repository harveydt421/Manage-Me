import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISeparationApplication } from 'app/shared/model/separation-application.model';
import { LineItemService } from 'app/entities/line-item/line-item.service';
import { ILineItem } from 'app/shared/model/line-item.model';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

@Component({
    selector: 'jhi-separation-application-detail',
    templateUrl: './separation-application-detail.component.html'
})
export class SeparationApplicationDetailComponent implements OnInit {
    separationApplication: ISeparationApplication;
    lineItems: ILineItem[];

    constructor(private activatedRoute: ActivatedRoute,
                private jhiAlertService: JhiAlertService,
                private lineItemService: LineItemService) {}

    getLineItem() {
        this.lineItemService.query().subscribe(
        (res: HttpResponse<ISeparationApplication[]>) => {
                this.lineItems = res.body;
        },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ separationApplication }) => {
            this.separationApplication = separationApplication;
        });

        this.getLineItem();
    }

     private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    previousState() {
        window.history.back();
    }
}
