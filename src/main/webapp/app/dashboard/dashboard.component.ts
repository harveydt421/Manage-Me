import { Component, OnDestroy, OnInit } from '@angular/core';
import { Principal } from 'app/core';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';
import { IEmployee } from 'app/shared/model/employee.model';
import { EmployeeService } from 'app/entities/employee/employee.service';
import { ISeparationApplication } from 'app/shared/model/separation-application.model';
import { SeparationApplicationService } from 'app/entities/separation-application/separation-application.service';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';

@Component({
    selector: 'jhi-dashboard',
    templateUrl: './dashboard.component.html',
    styles: []
})
export class DashboardComponent implements OnInit, OnDestroy {
    employees: IEmployee[];
    employee: IEmployee;
    separationApplications: ISeparationApplication[];
    separationApplication: ISeparationApplication;
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(private principal: Principal,
                private jhiAlertService: JhiAlertService,
                private employeeService: EmployeeService,
                private separationApplicationService: SeparationApplicationService) {}

    // private getPageTitle(routeSnapshot: ActivatedRouteSnapshot) {
    //     let title: string = routeSnapshot.data && routeSnapshot.data['pageTitle'] ? routeSnapshot.data['pageTitle'] : 'manageMeApp';
    //     if (routeSnapshot.firstChild) {
    //         title = this.getPageTitle(routeSnapshot.firstChild) || title;
    //     }
    //     return title;
    // }

    loadUser() {
        this.employeeService.query().subscribe(
            (res: HttpResponse<IEmployee[]>) => {
                this.employees = res.body;
                this.employee = this.employees.filter(employee => employee.userId === this.currentAccount.id)[0];
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    checkResignation() {
        this.separationApplicationService.query().subscribe(
            (res: HttpResponse<ISeparationApplication[]>) => {
                this.separationApplications = res.body;
                this.separationApplication = this.separationApplications.filter(
                    (separationApplication: ISeparationApplication) => separationApplication.employeeId === this.currentAccount.id
                )[0];
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.principal.identity().then(account => {
            this.currentAccount = account;
            this.loadUser();
            this.checkResignation();
        });
        // this.router.events.subscribe(event => {
        //     if (event instanceof NavigationEnd) {
        //         this.titleService.setTitle(this.getPageTitle(this.router.routerState.snapshot.root));
        //     }
        // });
    }

    hasSubmitResignation() {
        return this.separationApplication != null;
    }

    ngOnDestroy(): void { }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
