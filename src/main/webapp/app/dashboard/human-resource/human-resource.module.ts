import { NgModule } from '@angular/core';
import { ManageMeAdminModule } from 'app/admin/admin.module';
import { ManageMeSharedModule } from 'app/shared';
import { HumanResourceComponent } from './human-resource.component';

@NgModule({
    imports: [ManageMeSharedModule, ManageMeAdminModule],
    declarations: [HumanResourceComponent]
})
export class HumanResourceModule {}
