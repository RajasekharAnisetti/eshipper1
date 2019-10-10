import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ApPayableCreditNotesTrans } from 'app/shared/model/ap-payable-credit-notes-trans.model';
import { ApPayableCreditNotesTransService } from './ap-payable-credit-notes-trans.service';
import { ApPayableCreditNotesTransComponent } from './ap-payable-credit-notes-trans.component';
import { ApPayableCreditNotesTransDetailComponent } from './ap-payable-credit-notes-trans-detail.component';
import { ApPayableCreditNotesTransUpdateComponent } from './ap-payable-credit-notes-trans-update.component';
import { ApPayableCreditNotesTransDeletePopupComponent } from './ap-payable-credit-notes-trans-delete-dialog.component';
import { IApPayableCreditNotesTrans } from 'app/shared/model/ap-payable-credit-notes-trans.model';

@Injectable({ providedIn: 'root' })
export class ApPayableCreditNotesTransResolve implements Resolve<IApPayableCreditNotesTrans> {
  constructor(private service: ApPayableCreditNotesTransService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IApPayableCreditNotesTrans> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<ApPayableCreditNotesTrans>) => response.ok),
        map((apPayableCreditNotesTrans: HttpResponse<ApPayableCreditNotesTrans>) => apPayableCreditNotesTrans.body)
      );
    }
    return of(new ApPayableCreditNotesTrans());
  }
}

export const apPayableCreditNotesTransRoute: Routes = [
  {
    path: '',
    component: ApPayableCreditNotesTransComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ApPayableCreditNotesTrans'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ApPayableCreditNotesTransDetailComponent,
    resolve: {
      apPayableCreditNotesTrans: ApPayableCreditNotesTransResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ApPayableCreditNotesTrans'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ApPayableCreditNotesTransUpdateComponent,
    resolve: {
      apPayableCreditNotesTrans: ApPayableCreditNotesTransResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ApPayableCreditNotesTrans'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ApPayableCreditNotesTransUpdateComponent,
    resolve: {
      apPayableCreditNotesTrans: ApPayableCreditNotesTransResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ApPayableCreditNotesTrans'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const apPayableCreditNotesTransPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: ApPayableCreditNotesTransDeletePopupComponent,
    resolve: {
      apPayableCreditNotesTrans: ApPayableCreditNotesTransResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ApPayableCreditNotesTrans'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
