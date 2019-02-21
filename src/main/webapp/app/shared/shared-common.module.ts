import { NgModule } from '@angular/core';

import { H2HSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
    imports: [H2HSharedLibsModule],
    declarations: [JhiAlertComponent, JhiAlertErrorComponent],
    exports: [H2HSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class H2HSharedCommonModule {}
