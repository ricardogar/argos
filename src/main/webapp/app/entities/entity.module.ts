import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { ArgosRegionMySuffixModule } from './region-my-suffix/region-my-suffix.module';
import { ArgosCountryMySuffixModule } from './country-my-suffix/country-my-suffix.module';
import { ArgosLocationMySuffixModule } from './location-my-suffix/location-my-suffix.module';
import { ArgosDepartmentMySuffixModule } from './department-my-suffix/department-my-suffix.module';
import { ArgosTaskMySuffixModule } from './task-my-suffix/task-my-suffix.module';
import { ArgosEmployeeMySuffixModule } from './employee-my-suffix/employee-my-suffix.module';
import { ArgosJobMySuffixModule } from './job-my-suffix/job-my-suffix.module';
import { ArgosJobHistoryMySuffixModule } from './job-history-my-suffix/job-history-my-suffix.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        ArgosRegionMySuffixModule,
        ArgosCountryMySuffixModule,
        ArgosLocationMySuffixModule,
        ArgosDepartmentMySuffixModule,
        ArgosTaskMySuffixModule,
        ArgosEmployeeMySuffixModule,
        ArgosJobMySuffixModule,
        ArgosJobHistoryMySuffixModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ArgosEntityModule {}
