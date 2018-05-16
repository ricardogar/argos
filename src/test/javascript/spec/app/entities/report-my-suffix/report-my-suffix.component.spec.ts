/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ArgosTestModule } from '../../../test.module';
import { ReportMySuffixComponent } from '../../../../../../main/webapp/app/entities/report-my-suffix/report-my-suffix.component';
import { ReportMySuffixService } from '../../../../../../main/webapp/app/entities/report-my-suffix/report-my-suffix.service';
import { ReportMySuffix } from '../../../../../../main/webapp/app/entities/report-my-suffix/report-my-suffix.model';

describe('Component Tests', () => {

    describe('ReportMySuffix Management Component', () => {
        let comp: ReportMySuffixComponent;
        let fixture: ComponentFixture<ReportMySuffixComponent>;
        let service: ReportMySuffixService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [ArgosTestModule],
                declarations: [ReportMySuffixComponent],
                providers: [
                    ReportMySuffixService
                ]
            })
            .overrideTemplate(ReportMySuffixComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ReportMySuffixComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ReportMySuffixService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new ReportMySuffix(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.reports[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
