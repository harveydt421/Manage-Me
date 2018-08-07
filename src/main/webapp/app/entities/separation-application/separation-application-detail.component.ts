import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISeparationApplication } from 'app/shared/model/separation-application.model';

@Component({
    selector: 'jhi-separation-application-detail',
    templateUrl: './separation-application-detail.component.html'
})
export class SeparationApplicationDetailComponent implements OnInit {
    separationApplication: ISeparationApplication;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ separationApplication }) => {
            this.separationApplication = separationApplication;
        });
    }

    previousState() {
        window.history.back();
    }
}
