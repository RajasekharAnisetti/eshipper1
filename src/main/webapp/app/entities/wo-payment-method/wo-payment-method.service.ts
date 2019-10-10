import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IWoPaymentMethod } from 'app/shared/model/wo-payment-method.model';

type EntityResponseType = HttpResponse<IWoPaymentMethod>;
type EntityArrayResponseType = HttpResponse<IWoPaymentMethod[]>;

@Injectable({ providedIn: 'root' })
export class WoPaymentMethodService {
  public resourceUrl = SERVER_API_URL + 'api/wo-payment-methods';

  constructor(protected http: HttpClient) {}

  create(woPaymentMethod: IWoPaymentMethod): Observable<EntityResponseType> {
    return this.http.post<IWoPaymentMethod>(this.resourceUrl, woPaymentMethod, { observe: 'response' });
  }

  update(woPaymentMethod: IWoPaymentMethod): Observable<EntityResponseType> {
    return this.http.put<IWoPaymentMethod>(this.resourceUrl, woPaymentMethod, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IWoPaymentMethod>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IWoPaymentMethod[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
