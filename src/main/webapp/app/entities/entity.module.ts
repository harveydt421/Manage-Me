import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { ManageMeEmployeeModule } from './employee/employee.module';
import { ManageMeDepartmentModule } from './department/department.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        ManageMeEmployeeModule,
        ManageMeDepartmentModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ManageMeEntityModule {}
