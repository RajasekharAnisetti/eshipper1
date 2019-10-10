import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IApPayableCreditNotesTrans } from 'app/shared/model/ap-payable-credit-notes-trans.model';

type EntityResponseType = HttpResponse<IApPayableCreditNotesTrans>;
type EntityArrayResponseType = HttpResponse<IApPayableCreditNotesTrans[]>;

@Injectable({ providedIn: 'root' })
export class ApPayableCreditNotesTransService {
  public resourceUrl = SERVER_API_URL + 'api/ap-payable-credit-notes-trans';

  constructor(protected http: HttpClient) {}

  create(apPayableCreditNotesTrans: IApPayableCreditNotesTrans): Observable<EntityResponseType> {
    return this.http.post<IApPayableCreditNotesTrans>(this.resourceUrl, apPayableCreditNotesTrans, { observe: 'response' });
  }

  update(apPayableCreditNotesTrans: IApPayableCreditNotesTrans): Observable<EntityResponseType> {
    return this.http.put<IApPayableCreditNotesTrans>(this.resourceUrl, apPayableCreditNotesTrans, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IApPayableCreditNotesTrans>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IApPayableCreditNotesTrans[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
