import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IDepartment } from 'app/shared/model/department.model';
import { DepartmentService } from './department.service';
import { IEmployee } from 'app/shared/model/employee.model';
import { EmployeeService } from 'app/entities/employee';

@Component({
    selector: 'jhi-department-update',
    templateUrl: './department-update.component.html'
})
export class DepartmentUpdateComponent implements OnInit {
    private _department: IDepartment;
    isSaving: boolean;

    heads: IEmployee[];

    representatives: IEmployee[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private departmentService: DepartmentService,
        private employeeService: EmployeeService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ department }) => {
            this.department = department;
        });
        this.employeeService.query({ filter: 'department-is-null' }).subscribe(
            (res: HttpResponse<IEmployee[]>) => {
                if (!this.department.head || !this.department.head.id) {
                    this.heads = res.body;
                } else {
                    this.employeeService.find(this.department.head.id).subscribe(
                        (subRes: HttpResponse<IEmployee>) => {
                            this.heads = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.employeeService.query({ filter: 'department-is-null' }).subscribe(
            (res: HttpResponse<IEmployee[]>) => {
                if (!this.department.representative || !this.department.representative.id) {
                    this.representatives = res.body;
                } else {
                    this.employeeService.find(this.department.representative.id).subscribe(
                        (subRes: HttpResponse<IEmployee>) => {
                            this.representatives = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.department.id !== undefined) {
            this.subscribeToSaveResponse(this.departmentService.update(this.department));
        } else {
            this.subscribeToSaveResponse(this.departmentService.create(this.department));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IDepartment>>) {
        result.subscribe((res: HttpResponse<IDepartment>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackEmployeeById(index: number, item: IEmployee) {
        return item.id;
    }
    get department() {
        return this._department;
    }

    set department(department: IDepartment) {
        this._department = department;
    }
}
