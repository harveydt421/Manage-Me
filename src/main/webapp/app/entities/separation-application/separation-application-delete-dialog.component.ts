import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISeparationApplication } from 'app/shared/model/separation-application.model';
import { SeparationApplicationService } from './separation-application.service';

@Component({
    selector: 'jhi-separation-application-delete-dialog',
    templateUrl: './separation-application-delete-dialog.component.html'
})
export class SeparationApplicationDeleteDialogComponent {
    separationApplication: ISeparationApplication;

    constructor(
        private separationApplicationService: SeparationApplicationService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.separationApplicationService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'separationApplicationListModification',
                content: 'Deleted an separationApplication'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-separation-application-delete-popup',
    template: ''
})
export class SeparationApplicationDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ separationApplication }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(SeparationApplicationDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.separationApplication = separationApplication;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
