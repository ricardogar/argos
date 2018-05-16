import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { ReportMySuffix } from './report-my-suffix.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<ReportMySuffix>;

@Injectable()
export class ReportMySuffixService {

    private resourceUrl =  SERVER_API_URL + 'api/reports';

    constructor(private http: HttpClient, private dateUtils: JhiDateUtils) { }

    create(report: ReportMySuffix): Observable<EntityResponseType> {
        const copy = this.convert(report);
        return this.http.post<ReportMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(report: ReportMySuffix): Observable<EntityResponseType> {
        const copy = this.convert(report);
        return this.http.put<ReportMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ReportMySuffix>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<ReportMySuffix[]>> {
        const options = createRequestOption(req);
        return this.http.get<ReportMySuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<ReportMySuffix[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: ReportMySuffix = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<ReportMySuffix[]>): HttpResponse<ReportMySuffix[]> {
        const jsonResponse: ReportMySuffix[] = res.body;
        const body: ReportMySuffix[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to ReportMySuffix.
     */
    private convertItemFromServer(report: ReportMySuffix): ReportMySuffix {
        const copy: ReportMySuffix = Object.assign({}, report);
        copy.datetimefalla = this.dateUtils
            .convertDateTimeFromServer(report.datetimefalla);
        copy.datetimereworkini = this.dateUtils
            .convertDateTimeFromServer(report.datetimereworkini);
        copy.datetimereworkfin = this.dateUtils
            .convertDateTimeFromServer(report.datetimereworkfin);
        return copy;
    }

    /**
     * Convert a ReportMySuffix to a JSON which can be sent to the server.
     */
    private convert(report: ReportMySuffix): ReportMySuffix {
        const copy: ReportMySuffix = Object.assign({}, report);

        copy.datetimefalla = this.dateUtils.toDate(report.datetimefalla);

        copy.datetimereworkini = this.dateUtils.toDate(report.datetimereworkini);

        copy.datetimereworkfin = this.dateUtils.toDate(report.datetimereworkfin);
        return copy;
    }
}
