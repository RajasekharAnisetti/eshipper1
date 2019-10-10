import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ApPayee } from 'app/shared/model/ap-payee.model';
import { ApPayeeService } from './ap-payee.service';
import { ApPayeeComponent } from './ap-payee.component';
import { ApPayeeDetailComponent } from './ap-payee-detail.component';
import { ApPayeeUpdateComponent } from './ap-payee-update.component';
import { ApPayeeDeletePopupComponent } from './ap-payee-delete-dialog.component';
import { IApPayee } from 'app/shared/model/ap-payee.model';

@Injectable({ providedIn: 'root' })
export class ApPayeeResolve implements Resolve<IApPayee> {
  constructor(private service: ApPayeeService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IApPayee> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<ApPayee>) => response.ok),
        map((apPayee: HttpResponse<ApPayee>) => apPayee.body)
      );
    }
    return of(new ApPayee());
  }
}

export const apPayeeRoute: Routes = [
  {
    path: '',
    component: ApPayeeComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ApPayees'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ApPayeeDetailComponent,
    resolve: {
      apPayee: ApPayeeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ApPayees'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ApPayeeUpdateComponent,
    resolve: {
      apPayee: ApPayeeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ApPayees'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ApPayeeUpdateComponent,
    resolve: {
      apPayee: ApPayeeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ApPayees'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const apPayeePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: ApPayeeDeletePopupComponent,
    resolve: {
      apPayee: ApPayeeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ApPayees'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
