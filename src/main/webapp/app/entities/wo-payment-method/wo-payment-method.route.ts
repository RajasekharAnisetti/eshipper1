import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { WoPaymentMethod } from 'app/shared/model/wo-payment-method.model';
import { WoPaymentMethodService } from './wo-payment-method.service';
import { WoPaymentMethodComponent } from './wo-payment-method.component';
import { WoPaymentMethodDetailComponent } from './wo-payment-method-detail.component';
import { WoPaymentMethodUpdateComponent } from './wo-payment-method-update.component';
import { WoPaymentMethodDeletePopupComponent } from './wo-payment-method-delete-dialog.component';
import { IWoPaymentMethod } from 'app/shared/model/wo-payment-method.model';

@Injectable({ providedIn: 'root' })
export class WoPaymentMethodResolve implements Resolve<IWoPaymentMethod> {
  constructor(private service: WoPaymentMethodService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IWoPaymentMethod> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<WoPaymentMethod>) => response.ok),
        map((woPaymentMethod: HttpResponse<WoPaymentMethod>) => woPaymentMethod.body)
      );
    }
    return of(new WoPaymentMethod());
  }
}

export const woPaymentMethodRoute: Routes = [
  {
    path: '',
    component: WoPaymentMethodComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'WoPaymentMethods'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: WoPaymentMethodDetailComponent,
    resolve: {
      woPaymentMethod: WoPaymentMethodResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'WoPaymentMethods'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: WoPaymentMethodUpdateComponent,
    resolve: {
      woPaymentMethod: WoPaymentMethodResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'WoPaymentMethods'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: WoPaymentMethodUpdateComponent,
    resolve: {
      woPaymentMethod: WoPaymentMethodResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'WoPaymentMethods'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const woPaymentMethodPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: WoPaymentMethodDeletePopupComponent,
    resolve: {
      woPaymentMethod: WoPaymentMethodResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'WoPaymentMethods'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
