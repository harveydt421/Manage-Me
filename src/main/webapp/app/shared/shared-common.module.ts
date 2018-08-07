import { NgModule } from '@angular/core';

import { ManageMeSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
    imports: [ManageMeSharedLibsModule],
    declarations: [JhiAlertComponent, JhiAlertErrorComponent],
    exports: [ManageMeSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class ManageMeSharedCommonModule {}
