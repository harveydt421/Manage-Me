import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISeparationApplication } from 'app/shared/model/separation-application.model';

type EntityResponseType = HttpResponse<ISeparationApplication>;
type EntityArrayResponseType = HttpResponse<ISeparationApplication[]>;

@Injectable({ providedIn: 'root' })
export class SeparationApplicationService {
    private resourceUrl = SERVER_API_URL + 'api/separation-applications';

    constructor(private http: HttpClient) {}

    create(separationApplication: ISeparationApplication): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(separationApplication);
        return this.http
            .post<ISeparationApplication>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(separationApplication: ISeparationApplication): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(separationApplication);
        return this.http
            .put<ISeparationApplication>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ISeparationApplication>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ISeparationApplication[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(separationApplication: ISeparationApplication): ISeparationApplication {
        const copy: ISeparationApplication = Object.assign({}, separationApplication, {
            dateOfLeaving:
                separationApplication.dateOfLeaving != null && separationApplication.dateOfLeaving.isValid()
                    ? separationApplication.dateOfLeaving.toJSON()
                    : null,
            dateOfSubmission:
                separationApplication.dateOfSubmission != null && separationApplication.dateOfSubmission.isValid()
                    ? separationApplication.dateOfSubmission.toJSON()
                    : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.dateOfLeaving = res.body.dateOfLeaving != null ? moment(res.body.dateOfLeaving) : null;
        res.body.dateOfSubmission = res.body.dateOfSubmission != null ? moment(res.body.dateOfSubmission) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((separationApplication: ISeparationApplication) => {
            separationApplication.dateOfLeaving =
                separationApplication.dateOfLeaving != null ? moment(separationApplication.dateOfLeaving) : null;
            separationApplication.dateOfSubmission =
                separationApplication.dateOfSubmission != null ? moment(separationApplication.dateOfSubmission) : null;
        });
        return res;
    }
}
