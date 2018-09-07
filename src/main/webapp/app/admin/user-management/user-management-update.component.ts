import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { User, UserService } from 'app/core';

@Component({
    selector: 'jhi-user-mgmt-update',
    templateUrl: './user-management-update.component.html'
})
export class UserMgmtUpdateComponent implements OnInit {
    user: User;
    error: any;
    languages: any[];
    authorities: any[];
    isSaving: boolean;
    confirmPassword: string;
    doNotMatch: string;

    constructor(private userService: UserService, private route: ActivatedRoute, private router: Router) {}

    ngOnInit() {
        this.isSaving = false;
        this.route.data.subscribe(({ user }) => {
            this.user = user.body ? user.body : user;
        });
        this.authorities = [];
        this.userService.authorities().subscribe(authorities => {
            this.authorities = authorities;
        });
    }

    previousState() {
        this.router.navigate(['/admin/user-management']);
    }

    save() {
        if (this.user.password !== this.confirmPassword) {
            this.doNotMatch = 'ERROR';
        } else {
            this.doNotMatch = null;
            this.isSaving = true;
            this.error = null;
            if (this.user.id !== null) {
                this.userService.update(this.user).subscribe(response => this.onSaveSuccess(response), () => this.onSaveError());
            } else {
                this.user.langKey = 'en';
                this.userService.create(this.user).subscribe(response => this.onSaveSuccess(response), () => this.onSaveError());
            }
        }
    }

    private onSaveSuccess(result) {
        this.isSaving = false;
        this.error = null;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
        this.error = 'ERROR';
    }
}
