import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IApPayeeType } from 'app/shared/model/ap-payee-type.model';

type EntityResponseType = HttpResponse<IApPayeeType>;
type EntityArrayResponseType = HttpResponse<IApPayeeType[]>;

@Injectable({ providedIn: 'root' })
export class ApPayeeTypeService {
  public resourceUrl = SERVER_API_URL + 'api/ap-payee-types';

  constructor(protected http: HttpClient) {}

  create(apPayeeType: IApPayeeType): Observable<EntityResponseType> {
    return this.http.post<IApPayeeType>(this.resourceUrl, apPayeeType, { observe: 'response' });
  }

  update(apPayeeType: IApPayeeType): Observable<EntityResponseType> {
    return this.http.put<IApPayeeType>(this.resourceUrl, apPayeeType, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IApPayeeType>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IApPayeeType[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
