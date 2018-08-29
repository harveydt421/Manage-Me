import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { humanResourceRoute } from 'app/dashboard/human-resource/human-resource.route';
import { HumanResourceComponent } from 'app/dashboard/human-resource/human-resource.component';
import { dashboardRoute } from 'app/dashboard/dashboard.route';
import { DashboardComponent } from 'app/dashboard/dashboard.component';
import { ManageMeSharedModule } from 'app/shared';
import { FunctionalRepresentativeComponent } from './functional-representative/functional-representative.component';

const LAYOUT_ROUTES = [humanResourceRoute];
const ENTITY_STATES = [...dashboardRoute];

@NgModule({
    imports: [ManageMeSharedModule, RouterModule.forChild([...ENTITY_STATES, ...LAYOUT_ROUTES])],
    declarations: [HumanResourceComponent, DashboardComponent, FunctionalRepresentativeComponent],
    exports: [RouterModule]
})
export class ManageMeDashboardModule {}
