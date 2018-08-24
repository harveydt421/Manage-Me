import { Component, OnDestroy, OnInit } from '@angular/core';
import { Principal } from 'app/core';

@Component({
    selector: 'jhi-dashboard',
    templateUrl: './dashboard.component.html',
    styles: []
})
export class DashboardComponent implements OnInit, OnDestroy {
    currentAccount: any;

    constructor(private principal: Principal) {}

    // private getPageTitle(routeSnapshot: ActivatedRouteSnapshot) {
    //     let title: string = routeSnapshot.data && routeSnapshot.data['pageTitle'] ? routeSnapshot.data['pageTitle'] : 'manageMeApp';
    //     if (routeSnapshot.firstChild) {
    //         title = this.getPageTitle(routeSnapshot.firstChild) || title;
    //     }
    //     return title;
    // }

    ngOnInit() {
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        // this.router.events.subscribe(event => {
        //     if (event instanceof NavigationEnd) {
        //         this.titleService.setTitle(this.getPageTitle(this.router.routerState.snapshot.root));
        //     }
        // });
    }

    ngOnDestroy(): void {}
}
