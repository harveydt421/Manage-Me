import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { ILineItem } from 'app/shared/model/line-item.model';
import { LineItemService } from './line-item.service';
import { IAsset } from 'app/shared/model/asset.model';
import { AssetService } from 'app/entities/asset';
import { ISeparationApplication } from 'app/shared/model/separation-application.model';
import { SeparationApplicationService } from 'app/entities/separation-application';

@Component({
    selector: 'jhi-line-item-update',
    templateUrl: './line-item-update.component.html'
})
export class LineItemUpdateComponent implements OnInit {
    private _lineItem: ILineItem;
    isSaving: boolean;

    assetoweds: IAsset[];

    separationapplications: ISeparationApplication[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private lineItemService: LineItemService,
        private assetService: AssetService,
        private separationApplicationService: SeparationApplicationService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ lineItem }) => {
            this.lineItem = lineItem;
        });
        this.assetService.query({ filter: 'lineitem-is-null' }).subscribe(
            (res: HttpResponse<IAsset[]>) => {
                if (!this.lineItem.assetOwedId) {
                    this.assetoweds = res.body;
                } else {
                    this.assetService.find(this.lineItem.assetOwedId).subscribe(
                        (subRes: HttpResponse<IAsset>) => {
                            this.assetoweds = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.separationApplicationService.query().subscribe(
            (res: HttpResponse<ISeparationApplication[]>) => {
                this.separationapplications = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.lineItem.id !== undefined) {
            this.subscribeToSaveResponse(this.lineItemService.update(this.lineItem));
        } else {
            this.subscribeToSaveResponse(this.lineItemService.create(this.lineItem));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ILineItem>>) {
        result.subscribe((res: HttpResponse<ILineItem>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackAssetById(index: number, item: IAsset) {
        return item.id;
    }

    trackSeparationApplicationById(index: number, item: ISeparationApplication) {
        return item.id;
    }
    get lineItem() {
        return this._lineItem;
    }

    set lineItem(lineItem: ILineItem) {
        this._lineItem = lineItem;
    }
}
