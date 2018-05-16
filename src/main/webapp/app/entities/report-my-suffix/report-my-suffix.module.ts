import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ArgosSharedModule } from '../../shared';
import {
    ReportMySuffixService,
    ReportMySuffixPopupService,
    ReportMySuffixComponent,
    ReportMySuffixDetailComponent,
    ReportMySuffixDialogComponent,
    ReportMySuffixPopupComponent,
    ReportMySuffixDeletePopupComponent,
    ReportMySuffixDeleteDialogComponent,
    reportRoute,
    reportPopupRoute,
    ReportMySuffixResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...reportRoute,
    ...reportPopupRoute,
];

@NgModule({
    imports: [
        ArgosSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        ReportMySuffixComponent,
        ReportMySuffixDetailComponent,
        ReportMySuffixDialogComponent,
        ReportMySuffixDeleteDialogComponent,
        ReportMySuffixPopupComponent,
        ReportMySuffixDeletePopupComponent,
    ],
    entryComponents: [
        ReportMySuffixComponent,
        ReportMySuffixDialogComponent,
        ReportMySuffixPopupComponent,
        ReportMySuffixDeleteDialogComponent,
        ReportMySuffixDeletePopupComponent,
    ],
    providers: [
        ReportMySuffixService,
        ReportMySuffixPopupService,
        ReportMySuffixResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ArgosReportMySuffixModule {}
