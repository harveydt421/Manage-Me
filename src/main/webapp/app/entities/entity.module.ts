import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { ManageMeEmployeeModule } from './employee/employee.module';
import { ManageMeDepartmentModule } from './department/department.module';
import { ManageMeLocationModule } from './location/location.module';
import { ManageMeAssetModule } from './asset/asset.module';
import { ManageMeSeparationApplicationModule } from './separation-application/separation-application.module';
import { ManageMeLineItemModule } from './line-item/line-item.module';
import { ManageMeNotificationModule } from './notification/notification.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        ManageMeEmployeeModule,
        ManageMeDepartmentModule,
        ManageMeLocationModule,
        ManageMeAssetModule,
        ManageMeSeparationApplicationModule,
        ManageMeLineItemModule,
        ManageMeNotificationModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ManageMeEntityModule {}
