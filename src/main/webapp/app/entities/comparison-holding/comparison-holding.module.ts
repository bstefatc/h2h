import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { H2HSharedModule } from 'app/shared';
import {
    ComparisonHoldingComponent,
    ComparisonHoldingDetailComponent,
    ComparisonHoldingUpdateComponent,
    ComparisonHoldingDeletePopupComponent,
    ComparisonHoldingDeleteDialogComponent,
    comparisonHoldingRoute,
    comparisonHoldingPopupRoute
} from './';

const ENTITY_STATES = [...comparisonHoldingRoute, ...comparisonHoldingPopupRoute];

@NgModule({
    imports: [H2HSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ComparisonHoldingComponent,
        ComparisonHoldingDetailComponent,
        ComparisonHoldingUpdateComponent,
        ComparisonHoldingDeleteDialogComponent,
        ComparisonHoldingDeletePopupComponent
    ],
    entryComponents: [
        ComparisonHoldingComponent,
        ComparisonHoldingUpdateComponent,
        ComparisonHoldingDeleteDialogComponent,
        ComparisonHoldingDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class H2HComparisonHoldingModule {}
