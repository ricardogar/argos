/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { ArgosTestModule } from '../../../test.module';
import { ReportMySuffixDetailComponent } from '../../../../../../main/webapp/app/entities/report-my-suffix/report-my-suffix-detail.component';
import { ReportMySuffixService } from '../../../../../../main/webapp/app/entities/report-my-suffix/report-my-suffix.service';
import { ReportMySuffix } from '../../../../../../main/webapp/app/entities/report-my-suffix/report-my-suffix.model';

describe('Component Tests', () => {

    describe('ReportMySuffix Management Detail Component', () => {
        let comp: ReportMySuffixDetailComponent;
        let fixture: ComponentFixture<ReportMySuffixDetailComponent>;
        let service: ReportMySuffixService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [ArgosTestModule],
                declarations: [ReportMySuffixDetailComponent],
                providers: [
                    ReportMySuffixService
                ]
            })
            .overrideTemplate(ReportMySuffixDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ReportMySuffixDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ReportMySuffixService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new ReportMySuffix(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.report).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
