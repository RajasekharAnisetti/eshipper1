import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IApCategoryType } from 'app/shared/model/ap-category-type.model';

type EntityResponseType = HttpResponse<IApCategoryType>;
type EntityArrayResponseType = HttpResponse<IApCategoryType[]>;

@Injectable({ providedIn: 'root' })
export class ApCategoryTypeService {
  public resourceUrl = SERVER_API_URL + 'api/ap-category-types';

  constructor(protected http: HttpClient) {}

  create(apCategoryType: IApCategoryType): Observable<EntityResponseType> {
    return this.http.post<IApCategoryType>(this.resourceUrl, apCategoryType, { observe: 'response' });
  }

  update(apCategoryType: IApCategoryType): Observable<EntityResponseType> {
    return this.http.put<IApCategoryType>(this.resourceUrl, apCategoryType, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IApCategoryType>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IApCategoryType[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
