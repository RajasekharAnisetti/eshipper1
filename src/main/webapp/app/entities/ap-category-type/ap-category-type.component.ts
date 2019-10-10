import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IApCategoryType } from 'app/shared/model/ap-category-type.model';
import { AccountService } from 'app/core/auth/account.service';
import { ApCategoryTypeService } from './ap-category-type.service';

@Component({
  selector: 'jhi-ap-category-type',
  templateUrl: './ap-category-type.component.html'
})
export class ApCategoryTypeComponent implements OnInit, OnDestroy {
  apCategoryTypes: IApCategoryType[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected apCategoryTypeService: ApCategoryTypeService,
    protected jhiAlertService: JhiAlertService,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.apCategoryTypeService
      .query()
      .pipe(
        filter((res: HttpResponse<IApCategoryType[]>) => res.ok),
        map((res: HttpResponse<IApCategoryType[]>) => res.body)
      )
      .subscribe(
        (res: IApCategoryType[]) => {
          this.apCategoryTypes = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInApCategoryTypes();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IApCategoryType) {
    return item.id;
  }

  registerChangeInApCategoryTypes() {
    this.eventSubscriber = this.eventManager.subscribe('apCategoryTypeListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
