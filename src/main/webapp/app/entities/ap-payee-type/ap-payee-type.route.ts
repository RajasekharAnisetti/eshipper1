import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ApPayeeType } from 'app/shared/model/ap-payee-type.model';
import { ApPayeeTypeService } from './ap-payee-type.service';
import { ApPayeeTypeComponent } from './ap-payee-type.component';
import { ApPayeeTypeDetailComponent } from './ap-payee-type-detail.component';
import { ApPayeeTypeUpdateComponent } from './ap-payee-type-update.component';
import { ApPayeeTypeDeletePopupComponent } from './ap-payee-type-delete-dialog.component';
import { IApPayeeType } from 'app/shared/model/ap-payee-type.model';

@Injectable({ providedIn: 'root' })
export class ApPayeeTypeResolve implements Resolve<IApPayeeType> {
  constructor(private service: ApPayeeTypeService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IApPayeeType> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<ApPayeeType>) => response.ok),
        map((apPayeeType: HttpResponse<ApPayeeType>) => apPayeeType.body)
      );
    }
    return of(new ApPayeeType());
  }
}

export const apPayeeTypeRoute: Routes = [
  {
    path: '',
    component: ApPayeeTypeComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ApPayeeTypes'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ApPayeeTypeDetailComponent,
    resolve: {
      apPayeeType: ApPayeeTypeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ApPayeeTypes'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ApPayeeTypeUpdateComponent,
    resolve: {
      apPayeeType: ApPayeeTypeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ApPayeeTypes'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ApPayeeTypeUpdateComponent,
    resolve: {
      apPayeeType: ApPayeeTypeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ApPayeeTypes'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const apPayeeTypePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: ApPayeeTypeDeletePopupComponent,
    resolve: {
      apPayeeType: ApPayeeTypeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ApPayeeTypes'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
