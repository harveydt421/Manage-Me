import { Route } from '@angular/router';

import { HumanResourceComponent } from 'app/dashboard/human-resource/human-resource.component';

export const humanResourceRoute: Route = {
    path: '',
    component: HumanResourceComponent,
    outlet: 'human-resource'
};
