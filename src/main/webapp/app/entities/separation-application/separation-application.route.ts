import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { SeparationApplication } from 'app/shared/model/separation-application.model';
import { SeparationApplicationService } from './separation-application.service';
import { SeparationApplicationComponent } from './separation-application.component';
import { SeparationApplicationDetailComponent } from './separation-application-detail.component';
import { SeparationApplicationUpdateComponent } from './separation-application-update.component';
import { SeparationApplicationDeletePopupComponent } from './separation-application-delete-dialog.component';
import { ISeparationApplication } from 'app/shared/model/separation-application.model';

@Injectable({ providedIn: 'root' })
export class SeparationApplicationResolve implements Resolve<ISeparationApplication> {
    constructor(private service: SeparationApplicationService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service
                .find(id)
                .pipe(map((separationApplication: HttpResponse<SeparationApplication>) => separationApplication.body));
        }
        return of(new SeparationApplication());
    }
}

export const separationApplicationRoute: Routes = [
    {
        path: 'separation-application',
        component: SeparationApplicationComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'SeparationApplications'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'separation-application/:id/view',
        component: SeparationApplicationDetailComponent,
        resolve: {
            separationApplication: SeparationApplicationResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'SeparationApplications'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'separation-application/new',
        component: SeparationApplicationUpdateComponent,
        resolve: {
            separationApplication: SeparationApplicationResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'SeparationApplications'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'separation-application/:id/edit',
        component: SeparationApplicationUpdateComponent,
        resolve: {
            separationApplication: SeparationApplicationResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'SeparationApplications'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const separationApplicationPopupRoute: Routes = [
    {
        path: 'separation-application/:id/delete',
        component: SeparationApplicationDeletePopupComponent,
        resolve: {
            separationApplication: SeparationApplicationResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'SeparationApplications'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
