import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IApPayee } from 'app/shared/model/ap-payee.model';

type EntityResponseType = HttpResponse<IApPayee>;
type EntityArrayResponseType = HttpResponse<IApPayee[]>;

@Injectable({ providedIn: 'root' })
export class ApPayeeService {
  public resourceUrl = SERVER_API_URL + 'api/ap-payees';

  constructor(protected http: HttpClient) {}

  create(apPayee: IApPayee): Observable<EntityResponseType> {
    return this.http.post<IApPayee>(this.resourceUrl, apPayee, { observe: 'response' });
  }

  update(apPayee: IApPayee): Observable<EntityResponseType> {
    return this.http.put<IApPayee>(this.resourceUrl, apPayee, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IApPayee>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IApPayee[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
