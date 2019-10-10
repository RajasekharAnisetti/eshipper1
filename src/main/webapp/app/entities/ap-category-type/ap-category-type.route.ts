import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ApCategoryType } from 'app/shared/model/ap-category-type.model';
import { ApCategoryTypeService } from './ap-category-type.service';
import { ApCategoryTypeComponent } from './ap-category-type.component';
import { ApCategoryTypeDetailComponent } from './ap-category-type-detail.component';
import { ApCategoryTypeUpdateComponent } from './ap-category-type-update.component';
import { ApCategoryTypeDeletePopupComponent } from './ap-category-type-delete-dialog.component';
import { IApCategoryType } from 'app/shared/model/ap-category-type.model';

@Injectable({ providedIn: 'root' })
export class ApCategoryTypeResolve implements Resolve<IApCategoryType> {
  constructor(private service: ApCategoryTypeService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IApCategoryType> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<ApCategoryType>) => response.ok),
        map((apCategoryType: HttpResponse<ApCategoryType>) => apCategoryType.body)
      );
    }
    return of(new ApCategoryType());
  }
}

export const apCategoryTypeRoute: Routes = [
  {
    path: '',
    component: ApCategoryTypeComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ApCategoryTypes'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ApCategoryTypeDetailComponent,
    resolve: {
      apCategoryType: ApCategoryTypeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ApCategoryTypes'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ApCategoryTypeUpdateComponent,
    resolve: {
      apCategoryType: ApCategoryTypeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ApCategoryTypes'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ApCategoryTypeUpdateComponent,
    resolve: {
      apCategoryType: ApCategoryTypeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ApCategoryTypes'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const apCategoryTypePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: ApCategoryTypeDeletePopupComponent,
    resolve: {
      apCategoryType: ApCategoryTypeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ApCategoryTypes'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
