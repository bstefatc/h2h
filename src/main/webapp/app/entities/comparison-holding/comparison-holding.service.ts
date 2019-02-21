import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IComparisonHolding } from 'app/shared/model/comparison-holding.model';

type EntityResponseType = HttpResponse<IComparisonHolding>;
type EntityArrayResponseType = HttpResponse<IComparisonHolding[]>;

@Injectable({ providedIn: 'root' })
export class ComparisonHoldingService {
    public resourceUrl = SERVER_API_URL + 'api/comparison-holdings';

    constructor(protected http: HttpClient) {}

    create(comparisonHolding: IComparisonHolding): Observable<EntityResponseType> {
        return this.http.post<IComparisonHolding>(this.resourceUrl, comparisonHolding, { observe: 'response' });
    }

    update(comparisonHolding: IComparisonHolding): Observable<EntityResponseType> {
        return this.http.put<IComparisonHolding>(this.resourceUrl, comparisonHolding, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IComparisonHolding>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IComparisonHolding[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
