import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ApPayable } from 'app/shared/model/ap-payable.model';
import { ApPayableService } from './ap-payable.service';
import { ApPayableComponent } from './ap-payable.component';
import { ApPayableDetailComponent } from './ap-payable-detail.component';
import { ApPayableUpdateComponent } from './ap-payable-update.component';
import { ApPayableDeletePopupComponent } from './ap-payable-delete-dialog.component';
import { IApPayable } from 'app/shared/model/ap-payable.model';

@Injectable({ providedIn: 'root' })
export class ApPayableResolve implements Resolve<IApPayable> {
  constructor(private service: ApPayableService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IApPayable> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<ApPayable>) => response.ok),
        map((apPayable: HttpResponse<ApPayable>) => apPayable.body)
      );
    }
    return of(new ApPayable());
  }
}

export const apPayableRoute: Routes = [
  {
    path: '',
    component: ApPayableComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ApPayables'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ApPayableDetailComponent,
    resolve: {
      apPayable: ApPayableResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ApPayables'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ApPayableUpdateComponent,
    resolve: {
      apPayable: ApPayableResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ApPayables'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ApPayableUpdateComponent,
    resolve: {
      apPayable: ApPayableResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ApPayables'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const apPayablePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: ApPayableDeletePopupComponent,
    resolve: {
      apPayable: ApPayableResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ApPayables'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
